package com.example.amoredapp.Bancodedados;

public class Constants {


    public static final String BD_NAME = "PASSWORD_BD";


    public static final int BD_VERSION = 1;


    public static final String TABLE_NAME = "PASSWORD_TABLE";

    public static final String C_ID = "ID";
    public static final String C_TITULO = "TITULO";
    public static final String C_CONTA = "CONTA";
    public static final String C_USUARIO = "USUARIO";
    public static final String C_SENHA = "SENHA";
    public static final String C_SITE = "SITE";
    public static final String C_NOTA = "NOTA";
    public static final String C_TEMPO_REGISTRO = "TEMPO_REGISTRO";
    public static final String C_TEMPO_ATUALIZACAO = "TEMPO_ATUALIZACAO";


    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME
            + "("
            + C_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + C_TITULO + " TEXT,"
            + C_CONTA + " TEXT,"
            + C_USUARIO + " TEXT,"
            + C_SENHA + " TEXT,"
            + C_SITE + " TEXT,"
            + C_NOTA + " TEXT,"
            + C_TEMPO_REGISTRO + " TEXT,"
            + C_TEMPO_ATUALIZACAO + " TEXT"
            + ")";



}
