package br.edu.ifce.engcomp.francis.radarpolitico.dummy;

import java.util.ArrayList;

import br.edu.ifce.engcomp.francis.radarpolitico.models.Deputado;

/**
 * Created by francisco on 11/03/16.
 */
public class DeputadosMock {
    public static ArrayList<Deputado> makeDeputados() {
        Deputado d0 = new Deputado("Francisco Souza", "some url", "PPR", "CE");
        Deputado d1 = new Deputado("Antonio Vieira",  "some url", "PT", "AL");
        Deputado d2 = new Deputado("João Veríssimo", "some url", "PN", "CE");
        Deputado d3 = new Deputado("Firmino Dias", "some url", "PSDB", "CE");
        Deputado d4 = new Deputado("Sander Allan", "some url", "SLD", "CE");
        Deputado d5 = new Deputado("Sérgio Silva", "some url", "PMDB", "CE");
        Deputado d6 = new Deputado("Severo Cunha", "some url", "PPR", "CE");

        ArrayList<Deputado> deputados = new ArrayList<>();
        deputados.add(d0);
        deputados.add(d1);
        deputados.add(d2);
        deputados.add(d3);
        deputados.add(d4);
        deputados.add(d5);
        deputados.add(d6);

        return deputados;
    }
}
