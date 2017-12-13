package dao;

import config.GlobalConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AnotherConnectionBuilder implements ConnectionBuilder
{
    public AnotherConnectionBuilder() {
        try {
            Class.forName(GlobalConfig.getProperty("db.driver.class.sef"));
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        String url = GlobalConfig.getProperty("db.url.sef");
        String login = GlobalConfig.getProperty("db.login");
        String password = GlobalConfig.getProperty("db.password");
        return DriverManager.getConnection(url, login, password);
    }
}