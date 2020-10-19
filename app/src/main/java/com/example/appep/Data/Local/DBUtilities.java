package com.example.appep.Data.Local;

public class DBUtilities {

    //Names in database
    public static final String DB_NOMBRE = "DB_APPEP";
    public static final int DB_VERSION = 2;
    public static final String TABLA_POZO = "Pozo";
        public static final String TABLA_ID = "id";
        public static final String TABLA_ABIERTO = "abierto";
        public static final String TABLA_NOMBRE = "nombre";
        public static final String TABLA_CAMPO = "campo";
        public static final String TABLA_FECHA_AP = "fecha_ap";


    //SQL sentences on database
    public static final  String TABLE_CREATE_SQL = "CREATE TABLE "+ TABLA_POZO +" ("+ TABLA_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "+ TABLA_NOMBRE +" TEXT, "+ TABLA_CAMPO +" TEXT, "+ TABLA_FECHA_AP + " TEXT, " + TABLA_ABIERTO + " INTEGER)";
    public static final  String TABLE_DELETE_SQL = "DROP TABLE IF EXISTS " + TABLA_POZO;
    public static final  String TABLE_GET_SQL = "SELECT * FROM " + TABLA_POZO;
}
