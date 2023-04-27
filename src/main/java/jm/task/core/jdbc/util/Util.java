package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    private static SessionFactory sessionFactory;
    private static Connection connection;

    //Hibernate
    static {
        Configuration configuration = new Configuration().addAnnotatedClass(User.class);
        sessionFactory = configuration.buildSessionFactory();
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    //JDBC
    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                Properties props = getProps();
                Class.forName(props.getProperty("driver"));
                connection = DriverManager
                        .getConnection(
                                props.getProperty("url"),
                                props.getProperty("username"),
                                props.getProperty("password")
                        );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    private static Properties getProps() {
        Properties properties = new Properties();
        try (InputStream in = Util.class.getClassLoader().getResourceAsStream("database.properties")) {
            properties.load(in);

        } catch (
                IOException ex) {
            ex.printStackTrace();
        }

        return properties;
    }


}
