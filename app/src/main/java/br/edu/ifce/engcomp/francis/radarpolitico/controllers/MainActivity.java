package br.edu.ifce.engcomp.francis.radarpolitico.controllers;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import br.edu.ifce.engcomp.francis.radarpolitico.R;
import br.ifce.engcomp.francis.radarpolitico.controllers.PoliticosFragment;
import br.ifce.engcomp.francis.radarpolitico.controllers.VotacoesFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolbar();
        initTabHost();
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void initTabHost(){
        FragmentTabHost mainTabHost = (FragmentTabHost) findViewById(R.id.main_tabHost);
        mainTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);

        TabHost.TabSpec votacoesTab  = mainTabHost.newTabSpec("votacoes");
        TabHost.TabSpec politicosTab = mainTabHost.newTabSpec("politicos");

        votacoesTab.setIndicator("VOTAÇÕES");
        politicosTab.setIndicator("POLÍTICOS");

        mainTabHost.addTab(votacoesTab, VotacoesFragment.class, null);
        mainTabHost.addTab(politicosTab, PoliticosFragment.class, null);

        mainTabHost.setCurrentTab(0);

        stylizeTabs(mainTabHost);
    }

    private void stylizeTabs(FragmentTabHost tabHost){
        ColorStateList tabTextColors;
        TabWidget tabWidget;
        TextView tabTextView;
        View tabView;

        int tabAmount;

        tabWidget     = tabHost.getTabWidget();
        tabTextColors = this.getResources().getColorStateList(R.color.tab_text_selector);
        tabAmount     = tabWidget.getTabCount();

        for (int i = 0; i < tabAmount; i++){
            tabView = tabWidget.getChildTabViewAt(i);
            //tabView.setBackgroundResource(R.drawable.tab_indicator);

            tabTextView = (TextView) tabView.findViewById(android.R.id.title);
            tabTextView.setTextColor(tabTextColors);
        }
    }
}
