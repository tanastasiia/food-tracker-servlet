package ua.training.model;

import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.DataSource;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class ConnectionPoolHolder {
    private static volatile DataSource dataSource;

    public static DataSource getDataSource() {

        if (dataSource == null) {
            synchronized (ConnectionPoolHolder.class) {

                Properties property = new Properties();
                try (InputStream in = Files.newInputStream(Paths.get("src/main/resources/db.properties"))) {
                    property.load(in);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (dataSource == null) {
                    Logger.getGlobal().info("creating data source");

                    BasicDataSource ds = new BasicDataSource();
                    ds.setUrl(property.getProperty("url"));
                    ds.setUsername(property.getProperty("username"));
                    ds.setPassword(property.getProperty("password"));
                    ds.setMinIdle(Integer.parseInt(property.getProperty("minIdle")));
                    ds.setMaxIdle(Integer.parseInt(property.getProperty("maxIdle")));
                    ds.setMaxOpenPreparedStatements(Integer.parseInt(property.getProperty("maxOpenPreparedStatements")));
                    dataSource = ds;

                    Logger.getGlobal().info("data source created");
                }
            }
        }
        return dataSource;

    }


}