package br.edu.ifce.engcomp.francis.radarpolitico.controllers;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;

import br.edu.ifce.engcomp.francis.radarpolitico.R;

public class SearchViewActivity extends Activity {
    private SearchView searchView;
    private Menu mMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        handleIntent(getIntent());

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);

            showResults(query);
        }
    }

    private void showResults(String query) {
        // Query your data set and show results
        // ...
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        mMenu = menu;
        getMenuInflater().inflate(R.menu.menu_main, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        MenuItem searchItem = menu.findItem(R.id.search_deputado);

        if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB ){
            searchView = (SearchView) searchItem.getActionView();
        }
        else{
            searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        }

        searchView.setQueryHint("procurar deputado");

        //ComponentName cn = new ComponentName(this, AddPoliticiansActivity.class);
        //searchView.setSearchableInfo(searchManager.getSearchableInfo(cn));
        return onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search_deputado:
                onSearchRequested();
                return true;
            default:
                return false;
        }
    }

    @Override
    public boolean onSearchRequested() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            MenuItem mi = mMenu.findItem(R.id.search_deputado);
            if(mi.isActionViewExpanded()){
                mi.collapseActionView();
            } else{
                mi.expandActionView();
            }
        } else{
            //onOptionsItemSelected(mMenu.findItem(R.id.search));
        }
        return super.onSearchRequested();
    }

}
