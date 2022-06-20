package org.isj.ing3.isi.webservice.webservicerest.utils;

public class ParametresConnection {

    public static boolean connexionDistante=false;

    public static final String serverDatabaseAddress="85.146.7.48";
    //public static final String serverDatabaseAddress="localhost";
    public static final String serverDatabaseName="isj";
    public static final String serverDatabaseUsername="nkot";
    public static final String serverDatabasePassword="Gestionotes@123";
    public static final String serverDatabaseConnectionOptions="?serverTimezone=UTC";
    public static final int serverDatabasePort=3306;

    public static final String clientDatabaseAddress="localhost";
    public static final String clientDatabaseName="isj2";
    public static final String clientDatabaseUsername="root";
    public static final String clientDatabasePassword="coco@2000";
    public static final String clientDatabaseConnectionOptions="?serverTimezone=UTC";
    public static final int clientDatabasePort=3306;

    public static boolean isConnexionDistante() {
        return connexionDistante;
    }

    public static String getDatabaseAddress() {
        return connexionDistante?serverDatabaseAddress:clientDatabaseAddress;
    }

    public static String getDatabaseName() {
        return connexionDistante?serverDatabaseName:clientDatabaseName;
    }

    public static String getDatabaseUsername() {
        return clientDatabaseUsername;
    }

    public static String getDatabasePassword() {
        return clientDatabasePassword;
    }
    public static int getDatabasePort() {
        return connexionDistante?serverDatabasePort:serverDatabasePort;
    }

    public static  String getDatabaseConnectionOptions() {
        return connexionDistante?serverDatabaseConnectionOptions:serverDatabaseConnectionOptions;
    }

    public static String getURL() {
        return "jdbc:mysql://"+getDatabaseAddress()+":"+getDatabasePort()+"/"+getDatabaseName()+"?zeroDateTimeBehavior=convertToNull&autoReconnect=true&serverTimezone=UTC";
    }
    public static String getURLLocal() {
        return "jdbc:mysql://"+clientDatabaseAddress+":"+clientDatabasePort+"/"+clientDatabaseName+"?zeroDateTimeBehavior=convertToNull&autoReconnect=true&serverTimezone=UTC";
    }
}
