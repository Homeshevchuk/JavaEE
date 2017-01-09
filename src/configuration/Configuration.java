package configuration;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Properties;

public class Configuration {

    private static BasicDataSource ds;

    static {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("D:\\Bob\\Lab1\\my.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ds = new BasicDataSource();
        ds.setDriverClassName(properties.getProperty("driver"));
        ds.setUsername(properties.getProperty("username"));
        ds.setPassword(properties.getProperty("password"));
        ds.setUrl(properties.getProperty("url"));

    }

    public static DataSource getDataSource() throws SQLException {
        return ds;
    }

}