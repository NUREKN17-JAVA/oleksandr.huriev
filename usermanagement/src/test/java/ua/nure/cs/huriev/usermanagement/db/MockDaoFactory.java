package ua.nure.cs.huriev.usermanagement.db;


import com.mockobjects.dynamic.Mock;

public class MockDaoFactory extends DaoFactory {

    private Mock mockUserDao;

    public MockDaoFactory() {
        mockUserDao = new Mock(Dao.class);
    }

    public Mock getMockUserDao() {
        return mockUserDao;
    }

    public Dao getUserDao() {
        return (Dao) mockUserDao.proxy();
    }

}