package ua.nure.cs.huriev.usermanagement;

import junit.extensions.jfcunit.JFCTestCase;
import junit.extensions.jfcunit.JFCTestHelper;
import junit.extensions.jfcunit.TestHelper;
import junit.extensions.jfcunit.eventdata.MouseEventData;
import junit.extensions.jfcunit.eventdata.StringEventData;
import junit.extensions.jfcunit.finder.NamedComponentFinder;

import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.util.Date;

public class MainFrameTest extends JFCTestCase {
    public static final String BROWSE_PANEL_COMPONENT_NAME = "browsePanel";
    public static final String USER_TABLE_COMPONENT_NAME = "userTable";
    public static final String ADD_BUTTON_COMPONENT_NAME = "addButton";
    public static final String EDIT_BUTTON_COMPONENT_NAME = "editButton";
    public static final String DELETE_BUTTON_COMPONENT_NAME = "deleteButton";
    public static final String DETAIL_BUTTON_COMPONENT_NAME = "detailButton";
    public static final String ADD_PANEL_COMPONENT_NAME = "addPanel";
    private static final String FIRST_NAME = "John";
    private static final String LAST_NAME = "Doe";
    private static final Date DATE_OF_BIRTH = new Date();
    private static final String OK_BUTTON_COMPONENT_NAME = "okButton";
    public static final String FIRST_NAME_FIELD_COMPONENT_NAME = "firstNameField";
    public static final String LAST_NAME_FIELD_COMPONENT_NAME = "lastNameField";
    public static final String DATE_OF_BIRTH_FIELD_COMPONENT_NAME = "dateOfBirthField";
    private MainFrame mainFrame;

    protected void  setUp() throws Exception{
        super.setUp();
        setHelper(new JFCTestHelper());
        mainFrame = new MainFrame();
        mainFrame.setVisible(true);
    }
    protected void  tearDown() throws Exception{
        mainFrame.setVisible(false);
        getHelper();
        TestHelper.cleanUp(this);
        super.tearDown();
    }

    public void testBrowseControls(){
        find(JPanel.class, BROWSE_PANEL_COMPONENT_NAME);
        find(JTable.class, USER_TABLE_COMPONENT_NAME);
        find(JButton.class, ADD_BUTTON_COMPONENT_NAME);
        find(JButton.class, EDIT_BUTTON_COMPONENT_NAME);
        find(JButton.class, DELETE_BUTTON_COMPONENT_NAME);
        find(JButton.class, DETAIL_BUTTON_COMPONENT_NAME);
    }

    public void testAddUserOk() {
        find(JPanel.class, BROWSE_PANEL_COMPONENT_NAME);
        JButton addButton = (JButton) find(JButton.class, ADD_BUTTON_COMPONENT_NAME);
        getHelper().enterClickAndLeave(new MouseEventData(this,addButton));
        find(JPanel.class, ADD_PANEL_COMPONENT_NAME);
        fillFields(FIRST_NAME,LAST_NAME,DATE_OF_BIRTH);
        JButton okButton = (JButton) find(JButton.class,OK_BUTTON_COMPONENT_NAME);
        getHelper().enterClickAndLeave(new MouseEventData(this,okButton));
        find(JPanel.class,BROWSE_PANEL_COMPONENT_NAME);
    }

    private void fillFields(String firstName, String lastName, Date dateOfBirth) {
        JTextField firstNameField = (JTextField) find(JTextField.class, FIRST_NAME_FIELD_COMPONENT_NAME);
        JTextField lastNameField = (JTextField) find(JTextField.class, LAST_NAME_FIELD_COMPONENT_NAME);
        JTextField dateOfBirthField = (JTextField) find(JTextField.class, DATE_OF_BIRTH_FIELD_COMPONENT_NAME);
        getHelper().sendString(new StringEventData(this,firstNameField,firstName));
        getHelper().sendString(new StringEventData(this,lastNameField,lastName));
        String dateString = DateFormat.getInstance().format(dateOfBirth);
        getHelper().sendString(new StringEventData(this,dateOfBirthField,dateString));


    }

    protected Component find(Class<?> componentClass, String componentName){
        NamedComponentFinder finder = new NamedComponentFinder(componentClass,componentName);
        finder.setWait(0);
        Component component = finder.find(mainFrame, 0);
        assertNotNull("Could not find component '" +  componentName +"'", component);
        return component;
    }
}
