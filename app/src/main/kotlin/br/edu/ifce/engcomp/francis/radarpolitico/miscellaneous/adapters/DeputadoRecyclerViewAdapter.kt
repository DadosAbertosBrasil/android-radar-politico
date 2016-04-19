package br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.adapters

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView

import org.apache.commons.lang3.text.WordUtils

import java.util.ArrayList

import br.edu.ifce.engcomp.francis.radarpolitico.R
import br.edu.ifce.engcomp.francis.radarpolitico.controllers.DeputadoActivity
import br.edu.ifce.engcomp.francis.radarpolitico.models.Deputado
import com.squareup.picasso.Picasso

/**
 * Created by francisco on 11/03/16.
 */
class DeputadoRecyclerViewAdapter(private val dataSource: ArrayList<Deputado>) : RecyclerView.Adapter<DeputadoRecyclerViewAdapter.ViewHolder>() {

    fun getItem(position: Int): Deputado {
        return this.dataSource[position]
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeputadoRecyclerViewAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.adapter_politico, parent, false)

        return ViewHolder(itemView)
    }

    fun ImageView.loadImage(url: String?){
        if (!url.isNullOrBlank()) {
            Picasso.with(this.context).load(url).into(this)
        }
        else {
            this.setImageDrawable(resources.getDrawable(R.drawable.ic_smile))
        }
    }

    override fun onBindViewHolder(holder: DeputadoRecyclerViewAdapter.ViewHolder, position: Int) {
        val deputado = this.dataSource[position]

        holder.nomePoliticoTextView.text = WordUtils.capitalize(deputado.nomeParlamentar!!.toLowerCase())
        holder.partidoPoliticoTextView.text = deputado.partido
        holder.fotoPoliticoImageView.loadImage(deputado.urlFoto)

        if (deputado.frequencia != null) {
            val percentualFrequencia = deputado.frequencia!!.percentualPresenca
            holder.frasePresencasTextView.text = String.format("%4.1f%% de Presença no mês atual", percentualFrequencia)
            holder.presencaMensalProgressBar.progress = Math.round(percentualFrequencia).toInt()
            holder.numeroVotacoesTextView.text = deputado.frequencia!!.numeroSessoes.toString()
        }
    }

    override fun getItemCount(): Int {
        return this.dataSource.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val nomePoliticoTextView: TextView
        val partidoPoliticoTextView: TextView
        val fotoPoliticoImageView: ImageView

        val numeroVotacoesTextView: TextView
        val frasePresencasTextView: TextView
        val presencaMensalProgressBar: ProgressBar

        init {

            this.nomePoliticoTextView = itemView.findViewById(R.id.politico_nome_text_view) as TextView
            this.partidoPoliticoTextView = itemView.findViewById(R.id.politico_part_text_view) as TextView
            this.fotoPoliticoImageView = itemView.findViewById(R.id.politico_image_view) as ImageView

            this.numeroVotacoesTextView = itemView.findViewById(R.id.total_votacoes_text_view) as TextView
            this.frasePresencasTextView = itemView.findViewById(R.id.politico_percentual_presenca_text_view) as TextView
            this.presencaMensalProgressBar = itemView.findViewById(R.id.politico_presenca_progressbar) as ProgressBar

            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            val intent = Intent(v.context, DeputadoActivity::class.java)
            v.context.startActivity(intent)
        }
    }
}
