package ua.nure.cs.huriev.usermanagement.web;

import ua.nure.cs.huriev.usermanagement.User;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class BrowseServletTest extends MockServletTestCase {

    private static final String USERS_ATTRIBUTE_NAME = "users";
    private static final String USER_ID_VALUE = "1000";
    private static final String ID_PARAMETER = "id";
    private static final String EDIT_BUTTON = "Edit";
    private static final String EDIT_BUTTON_PARAMETER = "editButton";
    private static final String FIND_QUERY = "find";
    private static final String FIND_ALL_QUERY = "findAll";
    private static final String LAST_NAME = "Doe";
    private static final String FIRST_NAME = "John";

    protected void setUp() throws Exception {
        super.setUp();
        createServlet(BrowseServlet.class);
    }

    public void testBrowse() {
        User user = new User(new Long(1000), FIRST_NAME, LAST_NAME, new Date());
        List list = Collections.singletonList(user);
        getMockUserDao().expectAndReturn(FIND_ALL_QUERY, list);
        doGet();
        Collection collection = (Collection) getWebMockObjectFactory().getMockSession().getAttribute(USERS_ATTRIBUTE_NAME);
        assertNotNull("Could not find list of users in session", collection);
        assertSame(list, collection);
    }

    public void testEdit() {
        User user = new User (new Long(1000), FIRST_NAME, LAST_NAME, new Date());
        getMockUserDao().expectAndReturn(FIND_QUERY, new Long(1000), user);
        addRequestParameter(EDIT_BUTTON_PARAMETER, EDIT_BUTTON);
        addRequestParameter(ID_PARAMETER, USER_ID_VALUE);
        doPost();
        User userInSession = (User) getWebMockObjectFactory().getMockSession().getAttribute("user");
        assertNotNull("Could not find user in the session", user);
        assertSame(user, userInSession);
    }
}
