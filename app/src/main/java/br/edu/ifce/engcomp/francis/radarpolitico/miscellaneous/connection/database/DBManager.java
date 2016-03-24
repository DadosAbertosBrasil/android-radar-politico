package br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.connection.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by francisco on 24/03/16.
 */
public class DBManager extends SQLiteOpenHelper{
    private static DBManager sharedInstance = null;
    private static final String DB_NAME = "RADAR_POLITICO";
    private static final int DB_VERSION = 1;

    private DBManager(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    public static DBManager getManager(Context context){
        if(sharedInstance == null){
            sharedInstance = new DBManager(context);
        }

        return sharedInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table deputado(" +
                "idCadastro integer primary key, " +
                "matricula text, " +
                "idParlamentar text, " +
                "condicao text, " +
                "nome text, " +
                "nomeParlamentar text, " +
                "urlFoto text, " +
                "partido text, " +
                "gabinete text, " +
                "anexo text, " +
                "uf text, " +
                "fone text, " +
                "email text, " +
                "dataNascimento text, " +
                "situacaoLegislaturaAtual text, " +
                "ufRepresentacaoAtual text, " +
                "nomeProfissao text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table deputado");
        this.onCreate(db);
    }
}
