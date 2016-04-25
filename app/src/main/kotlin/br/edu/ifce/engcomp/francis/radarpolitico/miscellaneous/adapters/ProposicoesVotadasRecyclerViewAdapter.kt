package br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.adapters

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.widget.*

import java.util.ArrayList
import java.util.HashMap

import br.edu.ifce.engcomp.francis.radarpolitico.R
import br.edu.ifce.engcomp.francis.radarpolitico.controllers.DeputadoActivity
import br.edu.ifce.engcomp.francis.radarpolitico.controllers.ProposicaoVotadaActivity
import br.edu.ifce.engcomp.francis.radarpolitico.helpers.VolleySharedQueue
import br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.CDUrlFormatter
import br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.connection.database.DeputadoDAO
import br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.connection.parsers.ProposicaoParser
import br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.connection.parsers.VotacaoParser
import br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.helpers.IndexPath
import br.edu.ifce.engcomp.francis.radarpolitico.models.Deputado
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
class ProposicoesVotadasRecyclerViewAdapter(private val context: Context, private val datasource: ArrayList<Proposicao>) : RecyclerView.Adapter<ProposicoesVotadasRecyclerViewAdapter.ViewHolder>() {

    fun getItem(position: Int): Proposicao {
        return datasource[position]
    }

    override fun getItemCount(): Int {
        Log.i("VOLLEY SIZE", datasource.size.toString())
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
            holder.subtitle.text = proposicao.dataVotacao
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

            val p = ProposicaoParser.parserProposicaoFromXML(stringResponse.byteInputStream())
            proposicao.merge(p)

            Log.i("VOLLEY", proposicao.toString())


            holder.title.text    = proposicao.nome
            holder.subtitle.text = proposicao.dataVotacao
            holder.ementa.text   = if (proposicao.ementa.isNullOrBlank()) "Ementa Indisponível" else proposicao.ementa
        },{
            volleyError: VolleyError ->
            volleyError.printStackTrace()
        } )

        VolleySharedQueue.getQueue(context)?.add(request)
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
            Log.i("ITEM_CLIK", "Item clicked @ ${indexPath.toString()}")

            val intent = Intent(v.context, ProposicaoVotadaActivity::class.java)
            intent.putExtra("PROPOSICAO_EXTRA", datasource[indexPath.row])

            v.context.startActivity(intent)
        }
    }

}
