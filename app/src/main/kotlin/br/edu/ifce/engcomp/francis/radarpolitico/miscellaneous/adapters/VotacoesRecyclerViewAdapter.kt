package br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.adapters

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

import java.util.ArrayList
import java.util.HashMap

import br.edu.ifce.engcomp.francis.radarpolitico.R
import br.edu.ifce.engcomp.francis.radarpolitico.controllers.DeputadoActivity
import br.edu.ifce.engcomp.francis.radarpolitico.helpers.VolleySharedQueue
import br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.CDUrlFormatter
import br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.connection.database.DeputadoDAO
import br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.connection.parsers.ProposicaoParser
import br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.connection.parsers.VotacaoParser
import br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.helpers.IndexPath
import br.edu.ifce.engcomp.francis.radarpolitico.models.Proposicao
import br.edu.ifce.engcomp.francis.radarpolitico.models.Votacao
import br.edu.ifce.engcomp.francis.radarpolitico.models.Voto
import com.android.volley.Request
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.squareup.picasso.Picasso

/**
 * Created by francisco on 12/03/16.
 */
class VotacoesRecyclerViewAdapter(private val context: Context, private val datasource: ArrayList<Proposicao>) : RecyclerView.Adapter<VotacoesRecyclerViewAdapter.ViewHolder>() {
    override fun getItemCount(): Int {
        return datasource.size
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val proposicao = datasource[position]

        if(proposicao.nome.isNullOrBlank()) {
            retrievePropositionFromServer(proposicao, holder!!)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VotacoesRecyclerViewAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.list_item_header, parent, false)
        val holder = ViewHolder(itemView, viewType)

        return holder
    }

    private fun retrievePropositionFromServer(proposicao: Proposicao, holder: ViewHolder){
        val requestUrl = CDUrlFormatter.obterProposicao(proposicao.id)
        val request = StringRequest(Request.Method.GET, requestUrl, {
            stringResponse: String ->

            val p = ProposicaoParser.parserProposicaoFromXML(stringResponse.byteInputStream())
            proposicao.merge(p)

            holder.title.text    = proposicao.nome
            holder.subtitle.text = proposicao.dataVotacao
            holder.view.visibility = View.VISIBLE
        },{
            volleyError: VolleyError ->
            volleyError.printStackTrace()
        } )

        VolleySharedQueue.getQueue(context)?.add(request)
    }

    inner class ViewHolder(val view: View, val viewType: Int) : RecyclerView.ViewHolder(view), View.OnClickListener {
        val indexPath: IndexPath
        val title: TextView
        val subtitle: TextView

        init {

            this.title     = view.findViewById(R.id.title) as TextView
            this.subtitle  = view.findViewById(R.id.subtitle) as TextView
            this.indexPath = IndexPath()

            this.view.setOnClickListener(this)
            this.view.visibility = View.INVISIBLE
        }

        override fun onClick(v: View) {

        }
    }

}
