package ua.nure.cs.huriev.usermanagement.web;

import ua.nure.cs.huriev.usermanagement.User;

import java.text.DateFormat;
import java.util.Date;

public class AddServletTest extends MockServletTestCase {

    private static final String OK_BUTTON = "Ok";
    private static final String OK_BUTTON_PARAMETER = "okButton";
    private static final String DATE_PARAMETER = "date";
    private static final String LAST_NAME_PARAMETER = "lastName";
    private static final String FIRST_NAME_PARAMETER = "firstName";
    private static final String CREATE_QUERY = "create";
    private static final String LAST_NAME = "Doe";
    private static final String FIRST_NAME = "John";

    protected void setUp() throws Exception {
        super.setUp();
        createServlet(AddServlet.class);
    }

    public void testAdd() {
        Date date = new Date();
        User newUser = new User(FIRST_NAME, LAST_NAME, new Date());
        User user = new User(new Long(1000), FIRST_NAME, LAST_NAME, new Date());
        getMockUserDao().expectAndReturn(CREATE_QUERY, newUser, user);

        addRequestParameter(FIRST_NAME_PARAMETER, FIRST_NAME);
        addRequestParameter(LAST_NAME_PARAMETER, LAST_NAME);
        addRequestParameter(DATE_PARAMETER, DateFormat.getInstance().format(date));
        addRequestParameter(OK_BUTTON_PARAMETER, OK_BUTTON);
        doPost();
    }

    public void testAddEmptyFirstName() {
        Date date = new Date();
        addRequestParameter(LAST_NAME_PARAMETER, LAST_NAME);
        addRequestParameter(DATE_PARAMETER, DateFormat.getInstance().format(date));
        addRequestParameter(OK_BUTTON_PARAMETER, OK_BUTTON);
        doPost();
        String errorMessage = (String)getWebMockObjectFactory().getMockRequest().getAttribute("error");
        assertNotNull("Could not find error message in session scope", errorMessage);
    }

    public void testEditEmptyLastName() {
        Date date = new Date();
        addRequestParameter(FIRST_NAME_PARAMETER, FIRST_NAME);
        addRequestParameter(DATE_PARAMETER, DateFormat.getInstance().format(date));
        addRequestParameter(OK_BUTTON_PARAMETER, OK_BUTTON);
        doPost();
        String errorMessage = (String)getWebMockObjectFactory().getMockRequest().getAttribute("error");
        assertNotNull("Could not find error message in session scope", errorMessage);
    }

    public void testAddEmptyDate() {
        Date date = new Date();
        addRequestParameter(FIRST_NAME_PARAMETER, FIRST_NAME);
        addRequestParameter(LAST_NAME_PARAMETER, LAST_NAME);
        addRequestParameter(OK_BUTTON_PARAMETER, OK_BUTTON);
        doPost();
        String errorMessage = (String)getWebMockObjectFactory().getMockRequest().getAttribute("error");
        assertNotNull("Could not find error message in session scope", errorMessage);
    }

    public void testAddDateIncorrect() {
        Date date = new Date();
        addRequestParameter(FIRST_NAME_PARAMETER, FIRST_NAME);
        addRequestParameter(LAST_NAME_PARAMETER, LAST_NAME);
        addRequestParameter(DATE_PARAMETER, "lkjghdsfkhdfkjghfdl");
        addRequestParameter(OK_BUTTON_PARAMETER, OK_BUTTON);
        doPost();
        String errorMessage = (String)getWebMockObjectFactory().getMockRequest().getAttribute("error");
        assertNotNull("Could not find error message in session scope", errorMessage);
    }
}