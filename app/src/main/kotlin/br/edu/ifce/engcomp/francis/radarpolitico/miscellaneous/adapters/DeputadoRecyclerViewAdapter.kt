package br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.adapters

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView

import org.apache.commons.lang3.text.WordUtils

import br.edu.ifce.engcomp.francis.radarpolitico.R
import br.edu.ifce.engcomp.francis.radarpolitico.controllers.DeputadoActivity
import br.edu.ifce.engcomp.francis.radarpolitico.helpers.VolleySharedQueue
import br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.CDUrlFormatter
import br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.connection.parsers.FrequenciaParser
import br.edu.ifce.engcomp.francis.radarpolitico.models.Deputado
import com.android.volley.Request
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by francisco on 11/03/16.
 */
class DeputadoRecyclerViewAdapter(val context: Context, private val dataSource: ArrayList<Deputado>) : RecyclerView.Adapter<DeputadoRecyclerViewAdapter.ViewHolder>() {

    fun getItem(position: Int): Deputado {
        return this.dataSource[position]
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeputadoRecyclerViewAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.adapter_politico, parent, false)

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DeputadoRecyclerViewAdapter.ViewHolder, position: Int) {
        val deputado = this.dataSource[position]

        holder.nomePoliticoTextView.text = WordUtils.capitalize(deputado.nomeParlamentar!!.toLowerCase())
        holder.partidoPoliticoTextView.text = deputado.partido
        holder.fotoPoliticoImageView.loadImage(deputado.urlFoto)

        val currentCalendar  = Calendar.getInstance()
        val firstDayCalendar = Calendar.getInstance()
        val formatter   = SimpleDateFormat("dd/MM/yyyy")

        firstDayCalendar.set(Calendar.DAY_OF_MONTH, 1)

        val dataInicio = formatter.format(firstDayCalendar.time)
        val dataFim = formatter.format(currentCalendar.time)

        val urlRequest = CDUrlFormatter.listarPresencasParlamentar(dataInicio, dataFim, deputado.matricula)
        val request = StringRequest(Request.Method.GET, urlRequest, {
            stringResponse: String ->

            val dias = FrequenciaParser.parseFrequenciaFromXML(stringResponse.byteInputStream())
            val diasPresente = dias.filter { it.frequencia!!.equals("Presença") }
            val percentualFrequencia = (diasPresente.size.toFloat() / dias.size) * 100

            holder.frasePresencasTextView.text = String.format("%4.1f%% de Presença no mês atual", percentualFrequencia)
            holder.presencaMensalProgressBar.isIndeterminate = false
            holder.presencaMensalProgressBar.progress = percentualFrequencia.toInt()
            holder.numeroVotacoesTextView.text = diasPresente.size.toString()


        }, {
            volleyError: VolleyError ->
            volleyError.printStackTrace()
        })

        VolleySharedQueue.getQueue(context)?.add(request)
    }

    override fun getItemCount(): Int {
        return this.dataSource.size
    }

    fun ImageView.loadImage(url: String?){
        if (!url.isNullOrBlank()) {
            Picasso.with(this.context).load(url).into(this)
        }
        else {
            this.setImageDrawable(resources.getDrawable(R.drawable.ic_smile))
        }
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
