package com.example.appep.Data.Local;

public class DBUtilities {

    //Names in database
    public static final String DB_NOMBRE = "DB_APPEP";
    public static final int DB_VERSION = 3;
    public static final String TABLA_ID = "id";
    public static final String TABLA_POZO = "Pozo";
        public static final String TABLA_ABIERTO = "abierto";
        public static final String TABLA_NOMBRE = "nombre";
        public static final String TABLA_CAMPO = "campo";
        public static final String TABLA_FECHA_AP = "fecha_ap";
        public static final String TABLA_VERTICAL = "vertical";
    public static final String TABLA_EVENTO = "Evento";
        public static final String EVENTO_POZO = "pozo_fk";
        public static final String EVENTO_FECHA_CR = "fecha_cr";
        public static final String EVENTO_PESO_LODO = "peso_lodo";
        public static final String EVENTO_TABLA_ESTR = "tabla_estr";
        public static final String EVENTO_VOL_TOTAL = "vol_total";
        public static final String EVENTO_LNG_TOTAL = "lng_total";


    //SQL sentences on database
    public static final  String TABLE_CREATE_POZO_SQL = "CREATE TABLE "+ TABLA_POZO +" (" + TABLA_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                                                                                            TABLA_NOMBRE +" TEXT, "+
                                                                                            TABLA_CAMPO +" TEXT, "+
                                                                                            TABLA_FECHA_AP + " TEXT, " +
                                                                                            TABLA_ABIERTO + " INTEGER, " +
                                                                                            TABLA_VERTICAL + " INTEGER)";

    public static final  String TABLE_CREATE_EVENTO_SQL = "CREATE TABLE "+ TABLA_EVENTO +" ("+TABLA_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                                                                                            EVENTO_POZO +" INTEGER, " +
                                                                                            EVENTO_FECHA_CR +" TEXT, " +
                                                                                            EVENTO_TABLA_ESTR +" TEXT, " +
                                                                                            EVENTO_PESO_LODO + " REAL, " +
                                                                                            EVENTO_LNG_TOTAL + " REAL, " +
                                                                                            EVENTO_VOL_TOTAL + " REAL, " +
                                                                                            "FOREIGN KEY (" + EVENTO_POZO + ") REFERENCES " + TABLA_POZO + "("+ TABLA_ID +"))";

    public static final  String TABLE_DELETE_SQL = "DROP TABLE IF EXISTS " + TABLA_POZO;
    public static final  String TABLE_GET_SQL = "SELECT * FROM " + TABLA_POZO;
}
