package com.example.tfg2.database.modelos;

public class ConfigurationDB {
   // public static final String HOSTDB = "localhost";
    public static final String NOMBREDB = "mydb";
    public static final String USUARIODB = "root";
    public static final String CLAVEDB = "Chema-dam10";
    public static final String PUERTOMYSQL = "3306";
    public static final String HOSTDB = "10.0.2.2";
    private static final String OPCIONESHORA = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    // las opciones de hora también las puedes poner en mysql
    // SET GLOBAL time_zone = '+1:00';
    public static final String URLMYSQL = "jdbc:mysql://"+ HOSTDB + ":" +  PUERTOMYSQL + "/" + NOMBREDB + OPCIONESHORA;

}
