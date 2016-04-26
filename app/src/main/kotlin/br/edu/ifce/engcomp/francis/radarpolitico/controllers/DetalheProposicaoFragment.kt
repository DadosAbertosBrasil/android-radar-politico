package br.edu.ifce.engcomp.francis.radarpolitico.controllers


import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView

import br.edu.ifce.engcomp.francis.radarpolitico.R
import br.edu.ifce.engcomp.francis.radarpolitico.helpers.VolleySharedQueue
import br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.CDUrlFormatter
import br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.connection.database.DeputadoDAO
import br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.connection.parsers.VotacaoParser
import br.edu.ifce.engcomp.francis.radarpolitico.models.Proposicao
import br.edu.ifce.engcomp.francis.radarpolitico.models.Votacao
import br.edu.ifce.engcomp.francis.radarpolitico.models.Voto
import com.android.volley.Request
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class DetalheProposicaoFragment : Fragment() {
    private lateinit var proposicao: Proposicao

    private lateinit var nomeProposicaoTextView: TextView
    private lateinit var ementaProposicaoTextView: TextView
    private lateinit var dataVotacaoTextView: TextView
    private lateinit var politicoNomeTextView: TextView
    private lateinit var temaProposicaoTextView: TextView
    private lateinit var tipoProposicaoTextView: TextView
    private lateinit var urlInteiroTeorTextView: TextView

    private lateinit var politicoImageView: ImageView

    private lateinit var resultadosLinearLayout: LinearLayout

    private lateinit var votosSimTextView: TextView
    private lateinit var votosNaoTextView: TextView
    private lateinit var abstencoesTextView: TextView
    private lateinit var totalVotosTextView: TextView
    private lateinit var loadingResultadoProgressBar: ProgressBar

    private var votacao: Votacao? = null
    private val formatter = SimpleDateFormat("dd/MM/yyyy")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val parentActivity = activity as ProposicaoVotadaActivity

        proposicao = parentActivity.proposicao
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_detalhe_proposicao, container, false)

        nomeProposicaoTextView   = view.findViewById(R.id.nomeProposicaoTextView) as TextView
        ementaProposicaoTextView = view.findViewById(R.id.ementaProposicaoTextView) as TextView
        dataVotacaoTextView      = view.findViewById(R.id.dataVotacaoTextView) as TextView
        politicoNomeTextView     = view.findViewById(R.id.politicoNomeTextView) as TextView

        temaProposicaoTextView = view.findViewById(R.id.temaProposicaoTextView) as TextView
        tipoProposicaoTextView = view.findViewById(R.id.tipoProposicaoTextView) as TextView
        urlInteiroTeorTextView = view.findViewById(R.id.urlInteiroTeorTextView) as TextView

        votosSimTextView    = view.findViewById(R.id.votosSimTextView) as TextView
        votosNaoTextView    = view.findViewById(R.id.votosNaoTextView) as TextView
        abstencoesTextView  = view.findViewById(R.id.abstencoesTextView) as TextView
        totalVotosTextView  = view.findViewById(R.id.totalVotosTextView) as TextView

        politicoImageView   = view.findViewById(R.id.politicoImageView) as ImageView

        loadingResultadoProgressBar = view.findViewById(R.id.loadingResultadoProgressBar) as ProgressBar

        resultadosLinearLayout = view.findViewById(R.id.resultadosLinearLayout) as LinearLayout
        resultadosLinearLayout.hideView()

        populateProposicaoComponents()
        requestVotacaoIfNeeded()

        return view
    }

    private fun requestVotacaoIfNeeded(){
        if (votacao == null) {
            requestVotacaoAndInitVotacaoComponents()
        }
        else {
            populateVotacaoCompoenents()
        }
    }

    private fun requestVotacaoAndInitVotacaoComponents() {
        val urlString = CDUrlFormatter.obterVotacaoProposicao(proposicao.sigla, proposicao.numero, proposicao.ano)

        val request = StringRequest(Request.Method.GET, urlString, {
            stringResponse: String ->

            votacao = VotacaoParser.parseVotacaoFromXML(stringResponse.byteInputStream())
            populateVotacaoCompoenents()

        }, {
            volleyError: VolleyError ->
            volleyError.printStackTrace()
        })

        VolleySharedQueue.getQueue(activity)?.add(request)
    }

    private fun populateVotacaoCompoenents(){
        votosSimTextView.text   = votacao?.totalVotosSim
        votosNaoTextView.text   = votacao?.totalVotosNao
        abstencoesTextView.text = votacao?.totalVotosAbstencao
        totalVotosTextView.text = votacao?.totalVotosSessao

        loadingResultadoProgressBar.hideView()
        resultadosLinearLayout.showView()
    }

    private fun populateProposicaoComponents(){
        val urlFoto = "http://www.camara.gov.br/internet/deputado/bandep/${proposicao.idAutor}.jpg"
        politicoImageView.loadImage(urlFoto)

        nomeProposicaoTextView.text   = proposicao.nome
        ementaProposicaoTextView.text = proposicao.ementa
        dataVotacaoTextView.text    = formatter.format(proposicao.dataVotacao)
        politicoNomeTextView.text   = proposicao.nomeAutor
        temaProposicaoTextView.text = proposicao.tema
        tipoProposicaoTextView.text = proposicao.tipoProposicao
        urlInteiroTeorTextView.text = proposicao.urlInteiroTeor
    }

    fun ImageView.loadImage(url: String?){
        if (!url.isNullOrBlank()) {
            Picasso.with(this.context).load(url).into(this)
        }
        else {
            this.setImageDrawable(resources.getDrawable(R.drawable.ic_smile))
        }
    }

    fun View.hideView() {
        this.visibility = View.GONE
    }

    fun View.showView() {
        this.visibility = View.VISIBLE
    }

}
