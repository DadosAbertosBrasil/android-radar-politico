package br.edu.ifce.engcomp.francis.radarpolitico.controllers

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v4.app.FragmentTabHost
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.View
import android.widget.TabWidget
import android.widget.TextView

import br.edu.ifce.engcomp.francis.radarpolitico.R
import br.edu.ifce.engcomp.francis.radarpolitico.helpers.VolleySharedQueue
import br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.CDUrlFormatter
import br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.adapters.VotosDeputadosRecyclerViewAdapter
import br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.connection.database.DeputadoDAO
import br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.connection.parsers.VotacaoParser
import br.edu.ifce.engcomp.francis.radarpolitico.models.Proposicao
import br.edu.ifce.engcomp.francis.radarpolitico.models.Votacao
import br.edu.ifce.engcomp.francis.radarpolitico.models.Voto
import com.android.volley.Request
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import kotlinx.android.synthetic.main.activity_add_politicians.*
import kotlinx.android.synthetic.main.content_proposicao_votada.*
import kotlinx.android.synthetic.main.content_proposicao_votada.view.*
import java.util.*

class ProposicaoVotadaActivity : AppCompatActivity() {
    lateinit var proposicao: Proposicao


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_proposicao_votada)

        proposicao = intent.getParcelableExtra<Proposicao>("PROPOSICAO_EXTRA")

        initToolbar();
        initTabHost();
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    fun initToolbar() {
        if(toolbar != null) {
            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayShowHomeEnabled(true)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun initTabHost() {
        val mainTabHost = findViewById(R.id.votacoes_tabHost) as FragmentTabHost
        mainTabHost.setup(this, supportFragmentManager, android.R.id.tabcontent)

        val detalhesTab = mainTabHost.newTabSpec("votacoes")
        val votosTab    = mainTabHost.newTabSpec("politicos")

        detalhesTab.setIndicator("DETALHES")
        votosTab.setIndicator("LISTA DE VOTOS")

        mainTabHost.addTab(detalhesTab, DetalheProposicaoFragment::class.java, null)
        mainTabHost.addTab(votosTab, VotosDeputadosFragment::class.java, null)

        mainTabHost.currentTab = 0

        stylizeTabs(mainTabHost)
    }

    private fun stylizeTabs(tabHost: FragmentTabHost) {
        val tabTextColors: ColorStateList
        val tabWidget: TabWidget
        var tabTextView: TextView
        var tabView: View

        val tabAmount: Int

        tabWidget = tabHost.tabWidget
        tabTextColors = this.resources.getColorStateList(R.color.tab_text_selector)
        tabAmount = tabWidget.tabCount

        for (i in 0..tabAmount - 1) {
            tabView = tabWidget.getChildTabViewAt(i)
            tabTextView = tabView.findViewById(android.R.id.title) as TextView
            tabTextView.setTextColor(tabTextColors)
        }
    }

}
