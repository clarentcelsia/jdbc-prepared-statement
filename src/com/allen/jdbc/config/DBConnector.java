package com.allen.jdbc.config;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnector {

    private String driver;
    private String url;
    private String user;
    private String pass;

    Properties props;
    FileReader fileReader;

    public DBConnector() throws IOException {
        props = new Properties();
        fileReader = new FileReader("jdbcsettings.properties");
        props.load(fileReader);

        driver = props.getProperty("db.driver.class");
        url = props.getProperty("db.conn.url");
        user = props.getProperty("db.username");
        pass = props.getProperty("db.password");
    }

    public Connection connect() {
        Connection connection = null;
        try {
            if(driver != null && url != null) {
                /* Register jdbc driver class */
                Class.forName(driver);
                connection = DriverManager.getConnection(url, user, pass);
                connection.setAutoCommit(false);

                DatabaseMetaData metaData = connection.getMetaData();
                String dbName = metaData.getDatabaseProductName();
                String dbVersion = metaData.getDatabaseProductVersion();
                System.out.printf("Connected to %s %s successfully!", dbName, dbVersion);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}
