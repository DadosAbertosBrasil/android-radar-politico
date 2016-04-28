package br.edu.ifce.engcomp.francis.radarpolitico.controllers


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import br.edu.ifce.engcomp.francis.radarpolitico.R
import br.edu.ifce.engcomp.francis.radarpolitico.helpers.VolleySharedQueue
import br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.CDUrlFormatter
import br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.adapters.ProposicoesVotadasRecyclerViewAdapter
import br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.connection.parsers.CDXmlParser
import br.edu.ifce.engcomp.francis.radarpolitico.models.Proposicao
import com.android.volley.Request
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import java.util.*

class VotacoesTabFragment : Fragment() {
    lateinit var adapter: ProposicoesVotadasRecyclerViewAdapter
    lateinit var votacoesRecyclerView: RecyclerView

    internal var votacoesProgressBar: ProgressBar? = null
    internal var datasource: ArrayList<Proposicao>

    init {
        datasource = ArrayList<Proposicao>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.adapter = ProposicoesVotadasRecyclerViewAdapter(activity, this.datasource)
        retrieveVotedPropositionsOfCurrentYearFromServer()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater!!.inflate(R.layout.fragment_votacoes, container, false)

        this.votacoesRecyclerView = rootView.findViewById(R.id.votacoesRecyclerView) as RecyclerView
        this.votacoesProgressBar  = rootView.findViewById(R.id.votacoesProgressBar) as ProgressBar

        if(datasource.size > 0) {
            votacoesProgressBar?.visibility = View.INVISIBLE
        }

        this.initRecyclerView()

        return rootView
    }

    private fun initRecyclerView() {
        val layoutManager = LinearLayoutManager(activity)

        this.votacoesRecyclerView.setHasFixedSize(false)
        this.votacoesRecyclerView.layoutManager = layoutManager
        this.votacoesRecyclerView.adapter = adapter
        this.votacoesRecyclerView.itemAnimator = DefaultItemAnimator()
    }

    private fun retrieveVotedPropositionsOfCurrentYearFromServer() {
        val year = Calendar.getInstance().get(Calendar.YEAR);
        val requestUrl = CDUrlFormatter.listarProposicoesVotadasEmPlenario(year.toString(), "")

        val request = StringRequest(Request.Method.GET, requestUrl, {
            stringRespose: String ->
            val proposicoes = CDXmlParser.parseProposicoesFromXML(stringRespose.byteInputStream())

            proposicoes.sortByDescending { it.dataVotacao }
            datasource.clear()
            datasource.addAll(proposicoes)

            adapter.notifyDataSetChanged()

            this.votacoesProgressBar?.visibility = View.INVISIBLE

        }, {
            volleyError: VolleyError ->
            Toast.makeText(activity, "Ocorreu algum erro de rede. Tente mais tarde. :/", Toast.LENGTH_SHORT).show()
        })

        VolleySharedQueue.getQueue(activity)?.add(request)
    }
}
