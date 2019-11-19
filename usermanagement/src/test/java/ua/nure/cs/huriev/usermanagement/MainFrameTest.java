package ua.nure.cs.huriev.usermanagement;

import junit.extensions.jfcunit.JFCTestCase;
import junit.extensions.jfcunit.JFCTestHelper;
import junit.extensions.jfcunit.TestHelper;
import junit.extensions.jfcunit.finder.NamedComponentFinder;

import javax.swing.*;
import java.awt.*;

public class MainFrameTest extends JFCTestCase {
    public static final String BROWSE_PANEL_COMPONENT_NAME = "browsePanel";
    public static final String USER_TABLE_COMPONENT_NAME = "userTable";
    public static final String ADD_BUTTON_COMPONENT_NAME = "addButton";
    public static final String EDIT_BUTTON_COMPONENT_NAME = "editButton";
    public static final String DELETE_BUTTON_COMPONENT_NAME = "deleteButton";
    public static final String DETAIL_BUTTON_COMPONENT_NAME = "detailButton";
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


    protected Component find(Class<?> componentClass, String componentName){
        NamedComponentFinder finder = new NamedComponentFinder(componentClass,componentName);
        finder.setWait(0);
        Component component = finder.find(mainFrame, 0);
        assertNotNull("Could not find component '" +  componentName +"'", component);
        return component;
    }
}
