package br.edu.ifce.engcomp.francis.radarpolitico.controllers;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.edu.ifce.engcomp.francis.radarpolitico.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class VotacoesFragment extends Fragment {
    
    public VotacoesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_votacoes, container, false);
    }

}
