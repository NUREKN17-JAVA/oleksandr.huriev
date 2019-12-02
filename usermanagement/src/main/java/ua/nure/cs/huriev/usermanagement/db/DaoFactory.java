package ua.nure.cs.huriev.usermanagement.db;

import java.io.IOException;
import java.util.Properties;


public abstract class DaoFactory {
    private static final String SETTING_PROPERTIES = "settings.properties";

    protected static final String USER_DAO = "dao.UserDao";
    private static final String DAO_FACTORY = "dao.factory";

    protected static Properties properties;
    protected static DaoFactory instance;


    protected DaoFactory() {
        properties = new Properties();
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream(SETTING_PROPERTIES));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    static {
        properties = new Properties();
        try {
            properties.load(DaoFactory.class.getClassLoader().getResourceAsStream(SETTING_PROPERTIES));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static synchronized DaoFactory getInstance() {
        if (instance == null) {
            Class factoryClass;
            try {
                factoryClass = Class.forName(properties.getProperty(DAO_FACTORY));
                instance = (DaoFactory) factoryClass.newInstance();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return instance;
    }


    public static void init(Properties prop) {
        properties = prop;
        instance = null;
    }

    protected ConnectionFactory getConnectionFactory() {
        return new ConnectionFactoryImpl(properties);
    }

    public abstract Dao getUserDao();
}