package ua.nure.cs.huriev.usermanagement.db;

import junit.framework.TestCase;

public class DaoFactoryTest extends TestCase {

    public void testGetUserDao() {
        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            assertNotNull("DaoFactory instance is null", daoFactory);
            Dao userDao = daoFactory.getUserDao();
            assertNotNull("UserDao instance is null", userDao);
        } catch (RuntimeException e) {
            e.printStackTrace();
            fail(e.toString());
        }
    }

}