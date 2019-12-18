package ua.nure.cs.huriev.usermanagement.web;

import com.mockobjects.dynamic.Mock;
import com.mockrunner.servlet.BasicServletTestCaseAdapter;
import ua.nure.cs.huriev.usermanagement.db.DaoFactory;
import ua.nure.cs.huriev.usermanagement.db.MockDaoFactory;

import java.util.Properties;

public abstract class MockServletTestCase extends BasicServletTestCaseAdapter {

    private Mock mockUserDao;

    public Mock getMockUserDao() {
        return mockUserDao;
    }

    public void setMockUserDao(Mock mockUserDao) {
        this.mockUserDao = mockUserDao;
    }

    protected void setUp() throws Exception{
        super.setUp();
        Properties properties = new Properties();
        properties.setProperty("dao.factory", MockDaoFactory.class.getName());
        DaoFactory.init(properties);
        setMockUserDao(((MockDaoFactory) DaoFactory.getInstance()).getMockUserDao());
    }

    protected  void tearDown() throws Exception{
        getMockUserDao().verify();
        super.tearDown();
    }
}
