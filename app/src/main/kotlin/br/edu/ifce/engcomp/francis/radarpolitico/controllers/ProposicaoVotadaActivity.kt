package br.edu.ifce.engcomp.francis.radarpolitico.controllers

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.View

import br.edu.ifce.engcomp.francis.radarpolitico.R
import br.edu.ifce.engcomp.francis.radarpolitico.helpers.VolleySharedQueue
import br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.CDUrlFormatter
import br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.adapters.VotosDeputadosRecyclerViewAdapter
import br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.connection.database.DeputadoDAO
import br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.connection.parsers.VotacaoParser
import br.edu.ifce.engcomp.francis.radarpolitico.models.Proposicao
import br.edu.ifce.engcomp.francis.radarpolitico.models.Votacao
import br.edu.ifce.engcomp.francis.radarpolitico.models.Voto
import com.android.volley.Request
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import kotlinx.android.synthetic.main.activity_add_politicians.*
import kotlinx.android.synthetic.main.content_proposicao_votada.*
import kotlinx.android.synthetic.main.content_proposicao_votada.view.*
import java.util.*

class ProposicaoVotadaActivity : AppCompatActivity() {
    lateinit var proposicao: Proposicao
    val votosDatasource:ArrayList<Voto>
    val adapter: VotosDeputadosRecyclerViewAdapter

    init {
        votosDatasource = ArrayList()
        adapter = VotosDeputadosRecyclerViewAdapter(this, votosDatasource)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_proposicao_votada)

        proposicao = intent.getSerializableExtra("PROPOSICAO_EXTRA") as Proposicao

        resultadosLinearLayout.hideView()
        votosDeputadosRecyclerView.hideView()

        initToolbar()
        initProposicaoComponents()
        initVotosRecyclerView()
        requestVotacaoAndInitVotacaoComponents()
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    fun initToolbar() {
        if(toolbar != null) {
            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayShowHomeEnabled(true)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun initProposicaoComponents(){
        nomeProposicaoTextView.text = proposicao.nome
        ementaProposicaoTextView.text = proposicao.ementa
        dataVotacaoTextView.text    = proposicao.dataVotacao
        politicoNomeTextView.text   = proposicao.nomeAutor
        temaProposicaoTextView.text = proposicao.tema
        tipoProposicaoTextView.text = proposicao.tipoProposicao
        urlInteiroTeorTextView.text = proposicao.urlInteiroTeor
    }
    
    private fun requestVotacaoAndInitVotacaoComponents() {
        val urlString = CDUrlFormatter.obterVotacaoProposicao(proposicao.sigla, proposicao.numero, proposicao.ano)

        val request = StringRequest(Request.Method.GET, urlString, {
            stringResponse: String ->

            val votacao = VotacaoParser.parseVotacaoFromXML(stringResponse.byteInputStream())
            Log.i("Volley", votacao.toString())

            votosSimTextView.text = votacao.totalVotosSim
            votosNaoTextView.text = votacao.totalVotosNao

            abstencoesTextView.text = votacao.totalVotosAbstencao
            totalVotosTextView.text = votacao.totalVotosSessao

            val deputados = DeputadoDAO(this).listAll()
            val filteredVotos = ArrayList<Voto>()

            for(deputado in deputados){
                val votos = votacao.votos.filter { it.idCadastro.equals(deputado.idCadastro) }
                filteredVotos.addAll(votos)
            }

            votosDatasource.clear()
            votosDatasource.addAll(filteredVotos)
            adapter.notifyDataSetChanged()

            loadingResultadoProgressBar.hideView()
            resultadosLinearLayout.showView()

            loadingVotosProgressBar.hideView()
            votosDeputadosRecyclerView.showView()

        },{
            volleyError: VolleyError ->
            volleyError.printStackTrace()
        })

        VolleySharedQueue.getQueue(this)?.add(request)
    }
    
    private fun initVotosRecyclerView() {
        val layoutManager = LinearLayoutManager(this)

        votosDeputadosRecyclerView.setHasFixedSize(true)
        votosDeputadosRecyclerView.layoutManager = layoutManager
        votosDeputadosRecyclerView.adapter = adapter
        votosDeputadosRecyclerView.itemAnimator = DefaultItemAnimator()
    }

    fun View.hideView() {
        this.visibility = View.GONE
    }

    fun View.showView() {
        this.visibility = View.VISIBLE
    }

}
