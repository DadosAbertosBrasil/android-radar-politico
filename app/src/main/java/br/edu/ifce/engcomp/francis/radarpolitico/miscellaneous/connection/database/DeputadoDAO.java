package br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.connection.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import br.edu.ifce.engcomp.francis.radarpolitico.models.Deputado;

/**
 * Created by francisco on 24/03/16.
 */
public class DeputadoDAO {
    private SQLiteDatabase database;

    public DeputadoDAO(Context context) {
        this.database = DBManager.getManager(context).getWritableDatabase();
    }

    public void create(Deputado deputado){
        ContentValues deputadoValues = new ContentValues();

        deputadoValues.put("idCadastro", Integer.valueOf(deputado.getIdCadastro()));
        deputadoValues.put("matricula",  deputado.getMatricula());
        deputadoValues.put("idParlamentar", deputado.getIdParlamentar());

        deputadoValues.put("condicao", deputado.getCondicao());
        deputadoValues.put("nome", deputado.getNome());
        deputadoValues.put("nomeParlamentar", deputado.getNomeParlamentar());
        deputadoValues.put("urlFoto", deputado.getUrlFoto());

        deputadoValues.put("partido", deputado.getPartido());

        deputadoValues.put("gabinete", deputado.getGabinete());
        deputadoValues.put("anexo", deputado.getAnexo());

        deputadoValues.put("uf", deputado.getUf());
        deputadoValues.put("fone", deputado.getFone());
        deputadoValues.put("email", deputado.getEmail());

        deputadoValues.put("dataNascimento", deputado.getDataNascimento());
        deputadoValues.put("situacaoLegislaturaAtual", deputado.getSituacaoLegislaturaAtual());
        deputadoValues.put("ufRepresentacaoAtual", deputado.getUfRepresentacaoAtual());
        deputadoValues.put("nomeProfissao", deputado.getNomeProfissao());

        long id = database.insert("deputado", null, deputadoValues);

        Log.i("DATABASE-DEP", String.valueOf(id));
    }

    public ArrayList<Deputado> listAll(){
        ArrayList<Deputado> deputados = new ArrayList<>();

        String query = "select * from deputado";
        Cursor cursor = database.rawQuery(query, null);

        if (cursor.moveToFirst()){

            do {
                Deputado deputado = new Deputado();

                deputado.setIdCadastro(cursor.getString(0));
                deputado.setMatricula(cursor.getString(1));
                deputado.setIdParlamentar(cursor.getString(2));

                deputado.setCondicao(cursor.getString(3));
                deputado.setNome(cursor.getString(4));
                deputado.setNomeParlamentar(cursor.getString(5));
                deputado.setUrlFoto(cursor.getString(6));

                deputado.setPartido(cursor.getString(7));

                deputado.setGabinete(cursor.getString(8));
                deputado.setAnexo(cursor.getString(9));

                deputado.setUf(cursor.getString(10));
                deputado.setFone(cursor.getString(11));
                deputado.setEmail(cursor.getString(12));

                deputado.setDataNascimento(cursor.getString(13));
                deputado.setSituacaoLegislaturaAtual(cursor.getString(14));
                deputado.setUfRepresentacaoAtual(cursor.getString(15));
                deputado.setNomeProfissao(cursor.getString(16));

                deputados.add(deputado);

            }while (cursor.moveToNext());
        }

        return deputados;

    }

    public Deputado queryById(String idCadastro){
        String query = "select * from deputado where idCadastro = " + idCadastro;
        Cursor cursor = database.rawQuery(query, null);

        if (cursor.moveToFirst()){
            Deputado deputado = new Deputado();

            deputado.setIdCadastro(cursor.getString(0));
            deputado.setMatricula(cursor.getString(1));
            deputado.setIdParlamentar(cursor.getString(2));

            deputado.setCondicao(cursor.getString(3));
            deputado.setNome(cursor.getString(4));
            deputado.setNomeParlamentar(cursor.getString(5));
            deputado.setUrlFoto(cursor.getString(6));

            deputado.setPartido(cursor.getString(7));

            deputado.setGabinete(cursor.getString(8));
            deputado.setAnexo(cursor.getString(9));

            deputado.setUf(cursor.getString(10));
            deputado.setFone(cursor.getString(11));
            deputado.setEmail(cursor.getString(12));

            deputado.setDataNascimento(cursor.getString(13));
            deputado.setSituacaoLegislaturaAtual(cursor.getString(14));
            deputado.setUfRepresentacaoAtual(cursor.getString(15));
            deputado.setNomeProfissao(cursor.getString(16));

            return deputado;
        }

        return null;
    }

    public void delete(String idCadastro){
        String tableName = "deputados";
        String whereClause = "idCadastro=?";
        String[] whereArgs = new String[] {idCadastro};

        database.delete(tableName, whereClause, whereArgs);
    }
}
