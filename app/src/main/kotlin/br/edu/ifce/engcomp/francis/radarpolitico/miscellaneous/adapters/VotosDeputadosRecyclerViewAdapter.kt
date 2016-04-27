package br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import br.edu.ifce.engcomp.francis.radarpolitico.R
import br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.connection.database.DeputadoDAO
import br.edu.ifce.engcomp.francis.radarpolitico.models.Voto
import com.squareup.picasso.Picasso
import java.util.*

/**
 * Created by francisco on 21/04/16.
 */
class VotosDeputadosRecyclerViewAdapter(private val context: Context, private val datasource: ArrayList<Voto>) : RecyclerView.Adapter<VotosDeputadosRecyclerViewAdapter.ViewHolder>() {
    override fun getItemCount(): Int {
        return datasource.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder? {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.adapter_voto_proposicao, parent, false)

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val voto = datasource[position]

        val deputado = DeputadoDAO(context).queryById(voto.idCadastro)

        holder.nomeDeputadoTextView.text = voto.nome
        holder.votoDeputadoTextView.text = voto.voto?.toUpperCase()
        holder.setBackgroundColorWithVote(voto.voto)

        if (deputado != null) {
            holder.fotoDeputadoTextView.loadImage(deputado.urlFoto)
        }
    }

    fun ImageView.loadImage(url: String?){
        if (!url.isNullOrBlank()) {
            Picasso.with(this.context).load(url).into(this)
        }
        else {
            this.setImageDrawable(resources.getDrawable(R.drawable.ic_smile))
        }
    }


    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view){
        val nomeDeputadoTextView: TextView
        val votoDeputadoTextView: TextView
        val fotoDeputadoTextView: ImageView

        init {
            nomeDeputadoTextView = view.findViewById(R.id.nomeDeputadoTextView) as TextView
            votoDeputadoTextView = view.findViewById(R.id.votoDeputadoTextView) as TextView
            fotoDeputadoTextView = view.findViewById(R.id.fotoDeputadoImageView) as ImageView
        }

        fun setBackgroundColorWithVote(voto: String?) {
            when (voto?.toLowerCase()) {
                "sim" -> view.setBackgroundColor(context.resources.getColor(R.color.colorVoteYes))

                "não" -> view.setBackgroundColor(context.resources.getColor(R.color.colorVoteNo))
            }
        }
    }
}