package br.edu.ifce.engcomp.francis.radarpolitico.controllers


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import br.edu.ifce.engcomp.francis.radarpolitico.R
import br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.adapters.DeputadoRecyclerViewAdapter
import br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.connection.database.DeputadoDAO
import br.edu.ifce.engcomp.francis.radarpolitico.models.Deputado
import java.util.*

class PoliticosFragment : Fragment() {
    lateinit var politicosRecyclerView: RecyclerView
    lateinit var fabAddDeputado: FloatingActionButton
    lateinit var adapter: DeputadoRecyclerViewAdapter

    internal var datasource: ArrayList<Deputado>
    private val addPoliticoIntentCode = 2011

    init {
        datasource = ArrayList<Deputado>()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val rootView = inflater!!.inflate(R.layout.fragment_politicos, container, false)

        this.politicosRecyclerView = rootView.findViewById(R.id.politicos_recyler_view) as RecyclerView
        this.fabAddDeputado = rootView.findViewById(R.id.fab_add_politico) as FloatingActionButton

        this.initFabAddDeputado()
        this.initRecyclerView()
        this.retrieveDeputiesFromInternalDatabase()

        return rootView
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == addPoliticoIntentCode && resultCode == Activity.RESULT_OK){
            val deputadoAdded = data?.getBooleanExtra("DEPUTADO_ADDED", false)

            if (deputadoAdded == true){
                retrieveDeputiesFromInternalDatabase()
            }
        }
    }

    private fun initRecyclerView() {
        val layoutManager = LinearLayoutManager(activity)
        adapter = DeputadoRecyclerViewAdapter(activity, this.datasource)

        this.politicosRecyclerView.setHasFixedSize(false)
        this.politicosRecyclerView.layoutManager = layoutManager
        this.politicosRecyclerView.adapter = adapter
        this.politicosRecyclerView.itemAnimator = DefaultItemAnimator()
    }

    private fun initFabAddDeputado() {
        this.fabAddDeputado.setOnClickListener {
            val intentAddPoliticosActivity = Intent(activity, AddPoliticiansActivity::class.java)
            startActivityForResult(intentAddPoliticosActivity, addPoliticoIntentCode)
        }
    }

    fun retrieveDeputiesFromInternalDatabase() {
        val deputyDAO = DeputadoDAO(activity)
        val deputies  = deputyDAO.listAll()

        this.datasource.clear()
        this.datasource.addAll(deputies)

        this.adapter.notifyDataSetChanged()
    }
}
