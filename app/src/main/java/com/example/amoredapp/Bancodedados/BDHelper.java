package com.example.amoredapp.Bancodedados;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.amoredapp.Modelo.Senha;

import java.util.ArrayList;

public class BDHelper extends SQLiteOpenHelper {
    public BDHelper(@Nullable Context context) {
        super(context, Constants.BD_NAME, null, Constants.BD_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Constants.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME);
        onCreate(db);
    }

    public long inserirregistro (String titulo, String conta, String usuario, String senha, String site,
                                  String nota, String T_registro, String T_atualizacao){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();


        values.put(Constants.C_TITULO, titulo);
        values.put(Constants.C_CONTA, conta);
        values.put(Constants.C_USUARIO, usuario);
        values.put(Constants.C_SENHA, senha);
        values.put(Constants.C_SITE, site);
        values.put(Constants.C_NOTA, nota);
        values.put(Constants.C_TEMPO_REGISTRO, T_registro);
        values.put(Constants.C_TEMPO_ATUALIZACAO, T_atualizacao);


        long id = db.insert(Constants.TABLE_NAME, null, values);

        db.close();

        return id;
    }

    public void atualziarregistro (String id, String titulo, String conta, String usuario, String senha, String site,
                                 String nota, String T_registro, String T_atualizacao){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();


        values.put(Constants.C_TITULO, titulo);
        values.put(Constants.C_CONTA, conta);
        values.put(Constants.C_USUARIO, usuario);
        values.put(Constants.C_SENHA, senha);
        values.put(Constants.C_SITE, site);
        values.put(Constants.C_NOTA, nota);
        values.put(Constants.C_TEMPO_REGISTRO, T_registro);
        values.put(Constants.C_TEMPO_ATUALIZACAO, T_atualizacao);


        db.update(Constants.TABLE_NAME, values, Constants.C_ID + "=? ", new String[]{id});

        db.close();


    }

    public ArrayList<Senha> Obterregistros(String orderby){
        ArrayList<Senha> passwordList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + Constants.TABLE_NAME + " ORDER BY " + orderby;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()){
            do {
                @SuppressLint("Range") Senha mod_senha = new Senha(
                        ""+cursor.getInt(cursor.getColumnIndex(Constants.C_ID)),
                        ""+cursor.getString(cursor.getColumnIndex(Constants.C_TITULO)),
                        ""+cursor.getString(cursor.getColumnIndex(Constants.C_CONTA)),
                        ""+cursor.getString(cursor.getColumnIndex(Constants.C_USUARIO)),
                        ""+cursor.getString(cursor.getColumnIndex(Constants.C_SENHA)),
                        ""+cursor.getString(cursor.getColumnIndex(Constants.C_SITE)),
                        ""+cursor.getString(cursor.getColumnIndex(Constants.C_NOTA)),
                        ""+cursor.getString(cursor.getColumnIndex(Constants.C_TEMPO_REGISTRO)),
                        ""+cursor.getString(cursor.getColumnIndex(Constants.C_TEMPO_ATUALIZACAO)));

                passwordList.add(mod_senha);

            }while (cursor.moveToNext());
        }

        db.close();

        return passwordList;
    }

    public int NumerosdoRegistro(){
        String countquery = "SELECT * FROM " + Constants.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countquery, null);

        int contador = cursor.getCount();

        cursor.close();

        return contador;

    }

    public void Exlucluir_registro(String id){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(Constants.TABLE_NAME, Constants.C_ID+ " = ?", new String[]{id});
        db.close();
    }

}

