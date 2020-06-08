package ua.training.model;

import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.DataSource;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class ConnectionPoolHolder {
    private static volatile DataSource dataSource;
    public static DataSource getDataSource(){

        if (dataSource == null){
            synchronized (ConnectionPoolHolder.class) {
                if (dataSource == null) {
                    Logger.getGlobal().info("creating data source");
                    BasicDataSource ds = new BasicDataSource();
                    ds.setUrl("jdbc:mysql://localhost:3306/food_tracker");
                    ds.setUsername("root");
                    ds.setPassword("root");
                    ds.setMinIdle(5);
                    ds.setMaxIdle(10);
                    ds.setMaxOpenPreparedStatements(100);
                    dataSource = ds;

                    Logger.getGlobal().info("created");
                }
            }
        }
        return dataSource;

    }


}