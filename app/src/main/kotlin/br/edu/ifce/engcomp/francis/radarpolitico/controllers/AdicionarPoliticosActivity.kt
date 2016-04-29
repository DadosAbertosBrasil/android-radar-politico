package br.edu.ifce.engcomp.francis.radarpolitico.controllers

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.v4.view.MenuCompat
import android.support.v4.view.MenuItemCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.Menu
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

class AdicionarPoliticosActivity : AppCompatActivity(), SearchView.OnQueryTextListener, SwipeRefreshLayout.OnRefreshListener{

    var datasource: ArrayList<Deputado>

    lateinit var adapter: AddDeputadoRecyclerViewAdapter

    init {
        this.datasource = ArrayList()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_politicians)

        if (toolbar != null ) {
            setSupportActionBar(toolbar)
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            supportActionBar!!.setDisplayShowHomeEnabled(true)
        }

        initRecyclerView()
        initSwipeRefreshLayout()

        Toast.makeText(this, "Baixando lista de Deputados...", Toast.LENGTH_SHORT).show()
        retrieveDeputiesFromServer()
    }

    override fun onBackPressed() {
        val intent = Intent()
        intent.putExtra("DEPUTADO_ADDED", adapter.deputadoAdded)
        setResult(RESULT_OK, intent)

        super.onBackPressed()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_add_deputado, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchItem = menu.findItem(R.id.action_search)
        var searchView: SearchView?

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
            searchView = searchItem?.actionView as SearchView?
        }
        else {
            searchView = MenuItemCompat.getActionView(searchItem) as SearchView?
        }

        searchView?.queryHint = "Pesquisar"
        searchView?.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView?.setOnQueryTextListener(this)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home){
            onBackPressed()
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String): Boolean {

        if(newText.length > 3){
            val politicos = datasource.filter { it.nomeParlamentar!!.contains(newText, true) }
            adapter.changeDatasource(politicos as ArrayList<Deputado>                                                                                                                                   )
        }
        else {
            adapter.changeDatasource(datasource)
        }

        Log.i("SEARCH", datasource.count().toString())

        return false
    }

    override fun onRefresh() {
        adapter.changeDatasource(ArrayList())
        retrieveDeputiesFromServer()
    }

    private fun initRecyclerView() {
        val layoutManager = LinearLayoutManager(this)

        this.adapter = AddDeputadoRecyclerViewAdapter(datasource, this)

        addDeputadoRecyclerView.setHasFixedSize(false)
        addDeputadoRecyclerView.layoutManager = layoutManager
        addDeputadoRecyclerView.adapter = adapter
        addDeputadoRecyclerView.itemAnimator = DefaultItemAnimator()
    }

    private fun initSwipeRefreshLayout(){
        swipeRefreshLayout.setOnRefreshListener(this)
        swipeRefreshLayout.setColorSchemeColors(R.color.colorPrimaryDark, R.color.colorPrimary, R.color.colorAccent)
    }

    private fun retrieveDeputiesFromServer() {
        val requestUrl = CDUrlFormatter.obterDeputados()

        val request = StringRequest(Request.Method.GET, requestUrl, {
            stringResponse: String ->

            val deputados = CDXmlParser.parseDeputadosFromXML(stringResponse.byteInputStream())
            deputados.sortBy { it.nomeParlamentar }

            datasource.clear()
            datasource.addAll(deputados)
            adapter.changeDatasource(deputados)
            swipeRefreshLayout.isRefreshing = false

        }, {
            volleyError: VolleyError ->
            Toast.makeText(this, "Ocorreu algum erro de rede. Tente mais tarde. :/", Toast.LENGTH_SHORT).show()

        })

        VolleySharedQueue.getQueue(this)?.add(request)
    }
}
