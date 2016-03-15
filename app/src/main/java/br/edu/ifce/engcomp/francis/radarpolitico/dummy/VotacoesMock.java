package br.edu.ifce.engcomp.francis.radarpolitico.dummy;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

import br.edu.ifce.engcomp.francis.radarpolitico.models.Deputado;
import br.edu.ifce.engcomp.francis.radarpolitico.models.Votacao;
import br.edu.ifce.engcomp.francis.radarpolitico.models.Voto;

/**
 * Created by francisco on 12/03/16.
 */
public class VotacoesMock {
    public static HashMap<Votacao, ArrayList<Voto>> makeVotacao(){
        Votacao votacao = new Votacao();
        votacao.setResumo("PDC 315/2016");
        votacao.setData("25/02/2016");

        Votacao votacao2 = new Votacao();
        votacao2.setResumo("PDC 696/2015");
        votacao2.setData("18/02/2016");

        Votacao votacao3 = new Votacao();
        votacao3.setResumo("PDC 697/2015");
        votacao3.setData("18/02/2016");

        Deputado d1 = new Deputado("Afonso Mota", "", "PDC", "CE");
        Deputado d2 = new Deputado("Carlos Henrique Gaguim", "", "PDC", "CE");

        Voto v1 = new Voto("Sim", d1);
        Voto v2 = new Voto("Não", d2);

        Voto v3 = new Voto("Sim", d1);
        Voto v4 = new Voto("Sim", d2);

        ArrayList<Voto> votos1 = new ArrayList<>();
        votos1.add(v1);
        votos1.add(v2);

        ArrayList<Voto> votos2 = new ArrayList<>();
        votos2.add(v3);
        votos2.add(v4);

        HashMap<Votacao, ArrayList<Voto>> votacaoHashMap = new HashMap<>();
        votacaoHashMap.put(votacao, votos1);
        votacaoHashMap.put(votacao2, votos2);
        votacaoHashMap.put(votacao3, votos2);

        for (Votacao v : votacaoHashMap.keySet()) {
            Log.i("MOCK VOTACAO: " , v.toString());
        }

        return votacaoHashMap;
    }
}
