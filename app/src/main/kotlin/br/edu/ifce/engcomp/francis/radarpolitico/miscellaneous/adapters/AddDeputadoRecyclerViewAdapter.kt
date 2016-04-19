package br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

import org.apache.commons.lang3.text.WordUtils

import java.util.ArrayList

import br.edu.ifce.engcomp.francis.radarpolitico.R
import br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.connection.database.DeputadoDAO
import br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.helpers.IndexPath
import br.edu.ifce.engcomp.francis.radarpolitico.models.Deputado
import com.squareup.picasso.Picasso

/**
 * Created by Joamila on 16/03/2016.
 */
class AddDeputadoRecyclerViewAdapter(private val dataSource: ArrayList<Deputado>, private val context: Context) : RecyclerView.Adapter<AddDeputadoRecyclerViewAdapter.ViewHolder>() {
    fun getItem(position: Int): Deputado {
        return this.dataSource[position]
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.adapter_add_politico, parent, false)

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

    override fun onBindViewHolder(holder: AddDeputadoRecyclerViewAdapter.ViewHolder, position: Int) {
        val deputado = this.dataSource[position]

        holder.nomePoliticoTextView.text = WordUtils.capitalize(deputado.nomeParlamentar!!.toLowerCase())
        holder.partidoPoliticoTextView.text = "${deputado.partido} - ${deputado.uf}"
        holder.fotoPoliticoImageView.loadImage(deputado.urlFoto)
        holder.indexPath.setPath(0, position)
    }

    override fun getItemCount(): Int {
        return this.dataSource.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val nomePoliticoTextView: TextView
        val partidoPoliticoTextView: TextView
        val addPoliticoImageButton: ImageButton
        val fotoPoliticoImageView: ImageView

        val indexPath: IndexPath

        init {

            this.nomePoliticoTextView    = itemView.findViewById(R.id.add_politico_nome_text_view) as TextView
            this.partidoPoliticoTextView = itemView.findViewById(R.id.add_politico_partido_text_view) as TextView
            this.fotoPoliticoImageView   = itemView.findViewById(R.id.add_politico_image_view) as ImageView
            this.addPoliticoImageButton  = itemView.findViewById(R.id.add_politico_button) as ImageButton
            this.indexPath = IndexPath()

            this.addPoliticoImageButton.setOnClickListener(this.makeAddPoliticoListener())
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            Toast.makeText(context, "Cliked", Toast.LENGTH_SHORT).show()
        }

        private fun makeAddPoliticoListener(): View.OnClickListener {
            return View.OnClickListener { v ->
                val deputado = dataSource[indexPath.row]
                val deputadoDAO = DeputadoDAO(v.context)

                deputadoDAO.create(deputado)

                Toast.makeText(v.context, "Seguindo Deputado " + deputado.nomeParlamentar!!, Toast.LENGTH_SHORT).show()
            }
        }
    }
}
