package br.edu.ifce.engcomp.francis.radarpolitico.controllers


import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.edu.ifce.engcomp.francis.radarpolitico.R
import br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.adapters.DeputadoRecyclerViewAdapter
import br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.connection.database.DeputadoDAO
import br.edu.ifce.engcomp.francis.radarpolitico.models.Deputado
import java.util.*

class PoliticiansTabFragment : Fragment() {
    lateinit var politicosRecyclerView: RecyclerView
    lateinit var fabAddDeputado: FloatingActionButton

    internal var datasource: ArrayList<Deputado>

    init {
        datasource = ArrayList<Deputado>()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val rootView = inflater!!.inflate(R.layout.fragment_politicos, container, false)

        this.politicosRecyclerView = rootView.findViewById(R.id.politicos_recyler_view) as RecyclerView
        this.fabAddDeputado = rootView.findViewById(R.id.fab_add_politico) as FloatingActionButton

        this.initFabAddDeputado();
        this.initRecyclerView();

        return rootView
    }

    private fun initRecyclerView() {
        val layoutManager = LinearLayoutManager(activity)
        val adapter = DeputadoRecyclerViewAdapter(this.datasource, activity)

        this.politicosRecyclerView.setHasFixedSize(false)
        this.politicosRecyclerView.layoutManager = layoutManager
        this.politicosRecyclerView.adapter = adapter
        this.politicosRecyclerView.itemAnimator = DefaultItemAnimator()
    }

    private fun initFabAddDeputado() {
        this.fabAddDeputado.setOnClickListener {
            val intentAddPoliticosActivity = Intent(activity, AddPoliticiansActivity::class.java)
            startActivity(intentAddPoliticosActivity)
        }
    }
}
