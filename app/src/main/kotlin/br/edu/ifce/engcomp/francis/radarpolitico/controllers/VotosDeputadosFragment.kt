package br.edu.ifce.engcomp.francis.radarpolitico.controllers


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar

import br.edu.ifce.engcomp.francis.radarpolitico.R
import br.edu.ifce.engcomp.francis.radarpolitico.helpers.VolleySharedQueue
import br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.CDUrlFormatter
import br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.adapters.VotosDeputadosRecyclerViewAdapter
import br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.connection.database.DeputadoDAO
import br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.connection.parsers.VotacaoParser
import br.edu.ifce.engcomp.francis.radarpolitico.models.Proposicao
import br.edu.ifce.engcomp.francis.radarpolitico.models.Voto
import com.android.volley.Request
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class VotosDeputadosFragment : Fragment() {

    private lateinit var votosDeputadosRecyclerView: RecyclerView
    private lateinit var loadingVotosProgressBar: ProgressBar
    private lateinit var proposicao: Proposicao
    private lateinit var adapter:VotosDeputadosRecyclerViewAdapter

    val votosDatasource: ArrayList<Voto>

    init {
        votosDatasource = ArrayList()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val parentActivity = activity as ProposicaoVotadaActivity

        proposicao = parentActivity.proposicao
        adapter = VotosDeputadosRecyclerViewAdapter(parentActivity, votosDatasource)

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_votos_deputados, container, false)

        loadingVotosProgressBar = view.findViewById(R.id.loadingVotosProgressBar) as ProgressBar
        votosDeputadosRecyclerView = view.findViewById(R.id.votosDeputadosRecyclerView) as RecyclerView

        initVotosRecyclerView()
        requestVotosIfNeeded()

        return view
    }

    private fun initVotosRecyclerView() {
        val layoutManager = LinearLayoutManager(activity)

        votosDeputadosRecyclerView.setHasFixedSize(true)
        votosDeputadosRecyclerView.layoutManager = layoutManager
        votosDeputadosRecyclerView.adapter = adapter
        votosDeputadosRecyclerView.itemAnimator = DefaultItemAnimator()
    }

    private fun requestVotosIfNeeded(){
        if (votosDatasource.isEmpty()) {
            votosDeputadosRecyclerView.hideView()
            requestVotosDosDeputados()
        }
        else {
            loadingVotosProgressBar.hideView()
        }
    }

    private fun requestVotosDosDeputados() {
        val urlString = CDUrlFormatter.obterVotacaoProposicao(proposicao.sigla, proposicao.numero, proposicao.ano)

        val request = StringRequest(Request.Method.GET, urlString, {
            stringResponse: String ->

            val votacao = VotacaoParser.parseVotacaoFromXML(stringResponse.byteInputStream())

            val deputados = DeputadoDAO(activity).listAll()
            val filteredVotos = ArrayList<Voto>()

            for (deputado in deputados) {
                val votos = votacao.votos.filter { it.idCadastro.equals(deputado.idCadastro) }
                filteredVotos.addAll(votos)
            }

            Log.i("VOTOS DEPUTADOS", filteredVotos.toString())

            votosDatasource.clear()
            votosDatasource.addAll(filteredVotos)

            adapter.notifyDataSetChanged()
            loadingVotosProgressBar.hideView()
            votosDeputadosRecyclerView.showView()

        }, {
            volleyError: VolleyError ->
            volleyError.printStackTrace()
        })

        VolleySharedQueue.getQueue(activity)?.add(request)
    }

    fun View.hideView() {
        this.visibility = View.GONE
    }

    fun View.showView() {
        this.visibility = View.VISIBLE
    }

}
