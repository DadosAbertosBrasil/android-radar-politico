package br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.adapters

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import br.edu.ifce.engcomp.francis.radarpolitico.R
import br.edu.ifce.engcomp.francis.radarpolitico.controllers.ProposicaoVotadaActivity
import br.edu.ifce.engcomp.francis.radarpolitico.helpers.VolleySharedQueue
import br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.CDUrlFormatter
import br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.connection.parsers.CDXmlParser
import br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.helpers.IndexPath
import br.edu.ifce.engcomp.francis.radarpolitico.models.Proposicao
import com.android.volley.Request
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import kotlinx.android.synthetic.main.adapter_proposicao_votada.view.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by francisco on 12/03/16.
 */
class ProposicoesVotadasRecyclerViewAdapter(private val context: Context, private val datasource: ArrayList<Proposicao>) : RecyclerView.Adapter<ProposicoesVotadasRecyclerViewAdapter.ViewHolder>() {

    private val formatter = SimpleDateFormat("dd/MM/yyyy")

    fun getItem(position: Int): Proposicao {
        return datasource[position]
    }

    override fun getItemCount(): Int {
        return datasource.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val proposicao = datasource[position]

        holder.indexPath.section = 0
        holder.indexPath.row = position

        if(proposicao.nome.isNullOrBlank()) {
            retrievePropositionFromServer(proposicao, holder)
        }
        else {
            holder.title.text    = proposicao.nome
            holder.subtitle.text = formatter.format(proposicao.dataVotacao)
            holder.ementa.text   = if (proposicao.ementa.isNullOrBlank()) "Ementa Indisponível" else proposicao.ementa
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProposicoesVotadasRecyclerViewAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.adapter_proposicao_votada, parent, false)

        return ViewHolder(itemView)
    }

    private fun retrievePropositionFromServer(proposicao: Proposicao, holder: ViewHolder){
        val requestUrl = CDUrlFormatter.obterProposicao(proposicao.id)
        val request = StringRequest(Request.Method.GET, requestUrl, {
            stringResponse: String ->

            val p = CDXmlParser.parseProposicaoFromXML(stringResponse.byteInputStream())
            proposicao.merge(p)

            holder.title.text    = proposicao.nome
            holder.subtitle.text = formatter.format(proposicao.dataVotacao)
            holder.ementa.text   = if (proposicao.ementa.isNullOrBlank()) "Ementa Indisponível" else proposicao.ementa
        },{
            volleyError: VolleyError ->
            volleyError.printStackTrace()

            holder.title.text = "Erro de Rede"
            holder.ementa.text = "Não foi possível carregar proposição..."

            holder.subtitle.hideView()
            holder.seeMoreButton.hideView()
        } )

        VolleySharedQueue.getQueue(context)?.add(request)
    }

    fun View.hideView(){
        this.visibility = View.GONE
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        val title: TextView
        val subtitle: TextView
        val ementa: TextView
        val seeMoreButton: Button
        val indexPath: IndexPath

        init {

            this.title    = view.findViewById(R.id.title) as TextView
            this.subtitle = view.findViewById(R.id.subtitle) as TextView
            this.ementa   = view.findViewById(R.id.ementaTextView) as TextView
            this.seeMoreButton = view.findViewById(R.id.seeMoreButton) as Button
            this.indexPath = IndexPath()

            this.seeMoreButton.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            val intent = Intent(v.context, ProposicaoVotadaActivity::class.java)
            val proposicao = datasource[indexPath.row]

            intent.putExtra("PROPOSICAO_EXTRA", proposicao)

            v.context.startActivity(intent)
        }
    }

}
