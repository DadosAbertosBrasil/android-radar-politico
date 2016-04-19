package br.edu.ifce.engcomp.francis.radarpolitico.controllers

import android.content.res.ColorStateList
import android.os.Bundle
import android.support.v4.app.FragmentTabHost
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.TabHost
import android.widget.TabWidget
import android.widget.TextView

import br.edu.ifce.engcomp.francis.radarpolitico.R
import br.edu.ifce.engcomp.francis.radarpolitico.controllers.PoliticosFragment
import br.edu.ifce.engcomp.francis.radarpolitico.controllers.VotacoesFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initToolbar()
        initTabHost()
    }

    private fun initToolbar() {
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
    }

    private fun initTabHost() {
        val mainTabHost = findViewById(R.id.main_tabHost) as FragmentTabHost
        mainTabHost.setup(this, supportFragmentManager, android.R.id.tabcontent)

        val votacoesTab = mainTabHost.newTabSpec("votacoes")
        val politicosTab = mainTabHost.newTabSpec("politicos")

        votacoesTab.setIndicator("VOTAÇÕES")
        politicosTab.setIndicator("POLÍTICOS")

        mainTabHost.addTab(votacoesTab, VotacoesFragment::class.java, null)
        mainTabHost.addTab(politicosTab, PoliticosFragment::class.java, null)

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
