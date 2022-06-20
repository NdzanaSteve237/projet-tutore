package org.isj.ing3.isi.webservice.webservicerest.utils;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class Bd {

    static DriverManagerDataSource driverManager = new DriverManagerDataSource();

    public static Connection getConnection() throws SQLException {

        String url=ParametresConnection.getURL();
        String username=ParametresConnection.getDatabaseUsername();
        String pwd=ParametresConnection.getDatabasePassword();
        driverManager.setUrl(url);
        return driverManager.getConnection(username, pwd);
    }


}
