package ua.training.model;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.training.model.jdbc.JDBCMealDao;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class ConnectionPoolHolder {
    private static volatile DataSource dataSource;
    private static Logger logger = LogManager.getLogger(ConnectionPoolHolder.class.getName());

    public static DataSource getDataSource() {

        if (dataSource == null) {
            synchronized (ConnectionPoolHolder.class) {

                Properties property = new Properties();
                try (InputStream in = Files.newInputStream(Paths.get("src/main/resources/db.properties"))) {
                    property.load(in);
                } catch (IOException e) {
                    logger.fatal("Error finding db.properties: " + e);
                }

                if (dataSource == null) {
                    logger.info("creating data source");

                    BasicDataSource ds = new BasicDataSource();
                    ds.setUrl(property.getProperty("url"));
                    ds.setUsername(property.getProperty("username"));
                    ds.setPassword(property.getProperty("password"));
                    ds.setMinIdle(Integer.parseInt(property.getProperty("minIdle")));
                    ds.setMaxIdle(Integer.parseInt(property.getProperty("maxIdle")));
                    ds.setMaxOpenPreparedStatements(Integer.parseInt(property.getProperty("maxOpenPreparedStatements")));
                    dataSource = ds;

                    logger.info("data source created");
                }
            }
        }
        return dataSource;

    }


}