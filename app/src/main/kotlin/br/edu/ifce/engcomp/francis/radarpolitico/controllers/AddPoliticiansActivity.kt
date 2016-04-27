package br.edu.ifce.engcomp.francis.radarpolitico.controllers

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import br.edu.ifce.engcomp.francis.radarpolitico.R
import br.edu.ifce.engcomp.francis.radarpolitico.helpers.VolleySharedQueue
import br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.CDUrlFormatter
import br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.adapters.AddDeputadoRecyclerViewAdapter
import br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.connection.parsers.CDXmlParser
import br.edu.ifce.engcomp.francis.radarpolitico.models.Deputado
import com.android.volley.Request
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import kotlinx.android.synthetic.main.activity_add_politicians.*
import kotlinx.android.synthetic.main.content_add_politicians.*
import java.util.*

class AddPoliticiansActivity : AppCompatActivity() {
    var datasource: ArrayList<Deputado>
    lateinit var adapter: AddDeputadoRecyclerViewAdapter

    init {
        this.datasource = ArrayList<Deputado>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_politicians)

        if (toolbar != null ) {
            setSupportActionBar(toolbar)
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            supportActionBar!!.setDisplayShowHomeEnabled(true)
        }

        Toast.makeText(this, "Baixando lista de Deputados...", Toast.LENGTH_SHORT).show()
        retrieveDeputiesFromServer()
        initRecyclerView()
    }

    override fun onBackPressed() {
        val intent = Intent()
        intent.putExtra("DEPUTADO_ADDED", adapter.deputadoAdded)
        setResult(RESULT_OK, intent)

        super.onBackPressed()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home){
            onBackPressed()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun initRecyclerView() {
        val layoutManager = LinearLayoutManager(this)
        this.adapter = AddDeputadoRecyclerViewAdapter(this.datasource, this)

        addDeputadoRecyclerView.setHasFixedSize(false)
        addDeputadoRecyclerView.layoutManager = layoutManager
        addDeputadoRecyclerView.adapter = adapter
        addDeputadoRecyclerView.itemAnimator = DefaultItemAnimator()
    }

    private fun retrieveDeputiesFromServer() {
        val requestUrl = CDUrlFormatter.obterDeputados()

        val request = StringRequest(Request.Method.GET, requestUrl, {
            stringResponse: String ->

            val deputados = CDXmlParser.parseDeputadosFromXML(stringResponse.byteInputStream())
            deputados.sortBy { it.nomeParlamentar }

            datasource.clear()
            datasource.addAll(deputados)
            adapter.notifyDataSetChanged()
            addDeputadoProgressBar.visibility = View.INVISIBLE

        }, {
            volleyError: VolleyError ->
            Toast.makeText(this, "Ocorreu algum erro de rede. Tente mais tarde. :/", Toast.LENGTH_SHORT).show()
        })

        VolleySharedQueue.getQueue(this)?.add(request)
    }
}
