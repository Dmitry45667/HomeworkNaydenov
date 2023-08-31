package DBconfig;

import org.h2.jdbcx.JdbcDataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DataBaseConfig {
    public JdbcDataSource getDataSource() {
        JdbcDataSource dataSource = new JdbcDataSource();

        // Загружаю конфигурацию из файла
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream("src/test/resources/dbconfig.properties")) {
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }

        dataSource.setUrl(properties.getProperty("db.url"));
        dataSource.setUser(properties.getProperty("db.user"));
        dataSource.setPassword(properties.getProperty("db.password"));
        return dataSource;
    }
}


