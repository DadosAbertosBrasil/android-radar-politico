package br.edu.ifce.engcomp.francis.radarpolitico.controllers

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

import java.util.ArrayList

import br.edu.ifce.engcomp.francis.radarpolitico.R
import br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.adapters.AddDeputadoRecyclerViewAdapter
import br.edu.ifce.engcomp.francis.radarpolitico.models.Deputado
import kotlinx.android.synthetic.main.activity_add_politicians.*
import kotlinx.android.synthetic.main.content_add_politicians.*

class AddPoliticiansActivity : AppCompatActivity() {
    var datasource: ArrayList<Deputado>
    lateinit var adapter: AddDeputadoRecyclerViewAdapter


    init {
        this.datasource = ArrayList<Deputado>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_politicians)

        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        initRecyclerView()
    }

    override fun onBackPressed() {
        super.onBackPressed()

        val resultIntent = Intent()
        resultIntent.putExtra("DEPUTADO_ADD", adapter.deputadoFoiAdicioando)

        setResult(RESULT_OK, resultIntent)
    }


    private fun initRecyclerView() {
        val layoutManager = LinearLayoutManager(this)
        this.adapter = AddDeputadoRecyclerViewAdapter(this.datasource, this)

        addDeputadoRecyclerView.setHasFixedSize(false)
        addDeputadoRecyclerView.layoutManager = layoutManager
        addDeputadoRecyclerView.adapter = adapter
        addDeputadoRecyclerView.itemAnimator = DefaultItemAnimator()
    }
}
