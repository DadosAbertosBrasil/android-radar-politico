package br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import br.edu.ifce.engcomp.francis.radarpolitico.R
import br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.connection.database.DeputadoDAO
import br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.helpers.IndexPath
import br.edu.ifce.engcomp.francis.radarpolitico.models.Deputado
import com.squareup.picasso.Picasso
import org.apache.commons.lang3.text.WordUtils
import java.util.*

/**
 * Created by Joamila on 16/03/2016.
 */
class AddDeputadoRecyclerViewAdapter(private val dataSource: ArrayList<Deputado>, private val context: Context) : RecyclerView.Adapter<AddDeputadoRecyclerViewAdapter.ViewHolder>(){

    fun getItem(position: Int): Deputado {
        return this.dataSource[position]
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.adapter_add_politico, parent, false)

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: AddDeputadoRecyclerViewAdapter.ViewHolder, position: Int) {
        val deputado = this.dataSource[position]
        val deputadoDAO = DeputadoDAO(context)

        holder.nomePoliticoTextView.text = WordUtils.capitalize(deputado.nomeParlamentar!!.toLowerCase())
        holder.partidoPoliticoTextView.text = "${deputado.partido} - ${deputado.uf}"
        holder.fotoPoliticoImageView.loadImage(deputado.urlFoto)

        holder.indexPath.setPath(0, position)

        if(deputadoDAO.queryById(deputado.idCadastro)!=null){
            holder.followPoliticoSwitch.isChecked = true
        }
        else{
            holder.followPoliticoSwitch.isChecked = false
        }
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

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), CompoundButton.OnCheckedChangeListener {

        val nomePoliticoTextView: TextView
        val partidoPoliticoTextView: TextView
        val followPoliticoSwitch: Switch
        val fotoPoliticoImageView: ImageView

        val indexPath: IndexPath

        init {

            this.nomePoliticoTextView    = itemView.findViewById(R.id.add_politico_nome_text_view) as TextView
            this.partidoPoliticoTextView = itemView.findViewById(R.id.add_politico_partido_text_view) as TextView
            this.fotoPoliticoImageView   = itemView.findViewById(R.id.add_politico_image_view) as ImageView
            this.followPoliticoSwitch = itemView.findViewById(R.id.seguir_switch) as Switch
            this.indexPath = IndexPath()

            followPoliticoSwitch.setOnCheckedChangeListener(this);
        }

        override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
            val deputado = dataSource[indexPath.row]
            val deputadoDAO = DeputadoDAO(itemView.context)
            if(isChecked){
                deputadoDAO.create(deputado)
            }
            else{
                deputadoDAO.delete(deputado.idCadastro)
            }
        }
    }
}
