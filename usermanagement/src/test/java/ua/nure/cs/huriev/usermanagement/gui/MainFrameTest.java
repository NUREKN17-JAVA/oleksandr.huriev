package ua.nure.cs.huriev.usermanagement.gui;


import com.mockobjects.dynamic.Mock;
import junit.extensions.jfcunit.JFCTestCase;
import junit.extensions.jfcunit.JFCTestHelper;
import junit.extensions.jfcunit.eventdata.JTableMouseEventData;
import junit.extensions.jfcunit.eventdata.MouseEventData;
import junit.extensions.jfcunit.eventdata.StringEventData;
import junit.extensions.jfcunit.finder.NamedComponentFinder;
import ua.nure.cs.huriev.usermanagement.User;
import ua.nure.cs.huriev.usermanagement.db.DaoFactory;
import ua.nure.cs.huriev.usermanagement.db.MockDaoFactory;
import ua.nure.cs.huriev.usermanagement.util.Messages;

import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

public class MainFrameTest extends JFCTestCase {
	private static final String AGE_LABEL = "ageLabel";
	private static final String BIRTH_DATE_LABEL = "birthDateLabel";
	private static final String LAST_NAME_LABEL = "lastNameLabel";
	private static final String FIRST_NAME_LABEL = "firstNameLabel";
	private static final String ID_LABEL = "idLabel";
	private static final String DETAILS_PANEL = "detailsPanel";
	private static final String DATE_OF_BIRTH_FIELD_COMPONENT_NAME = "dateOfBirthField";
	private static final String LAST_NAME_FIELD_COMPONENT_NAME = "lastNameField";
	private static final String FIRST_NAME_FIELD_COMPONENT_NAME = "firstNameField";
	private static final String ADD_PANEL_COMPONENT_NAME = "addPanel";
	private static final String DETAILS_BUTTON_COMPONENT_NAME = "detailsButton";
	private static final String EDIT_BUTTON_COMPONENT_NAME = "editButton";
	private static final String ADD_BUTTON_COMPONENT_NAME = "addButton";
	private static final String DELETE_BUTTON_COMPONENT_NAME = "deleteButton";
	private static final String USER_TABLE_COMPONENT_NAME = "userTable";
	private static final String BROWSE_PANEL_COMPONENT_NAME = "browsePanel";
	private static final String OK_BUTTON_COMPONENT_NAME = "okButton";
	private static final String CANCEL_BUTTON_COMPONENT_NAME = "cancelButton";
	private static final String FIRST_NAME = "John";
	private static final String LAST_NAME = "Doe";
	private static final String FIND_ALL_COMMAND = "findAll";
	private static final Date DATE_OF_BIRTH = new Date();
	private MainFrame mainFrame;
	private Mock mockUserDao;

	protected void setUp() throws Exception {
		super.setUp();


		try {
			Properties properties = new Properties();
			properties.setProperty("dao.factory", MockDaoFactory.class.getName());
			DaoFactory.init(properties);
			mockUserDao = ((MockDaoFactory) DaoFactory.getInstance()).getMockUserDao();
			mockUserDao.expectAndReturn(FIND_ALL_COMMAND, new ArrayList());
			setHelper(new JFCTestHelper());
			mainFrame = new MainFrame();
		} catch (Exception e) {
			e.printStackTrace();
		}
		mainFrame.setVisible(true);
	}

	protected void tearDown() throws Exception {
		try {
			mockUserDao.verify();
			mainFrame.setVisible(false);
			getHelper().cleanUp(this);
			super.tearDown();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected Component find(Class<?> componentClass, String componentName) {
		NamedComponentFinder finder = new NamedComponentFinder(componentClass, componentName);
		finder.setWait(0);
		Component component = finder.find(mainFrame, 0);
		assertNotNull("Could not find component '" + componentName + "'");
		return component;
	}

	public void testBrowseControls() {
		find(JPanel.class, BROWSE_PANEL_COMPONENT_NAME);
		JTable table = (JTable)find(JTable.class, USER_TABLE_COMPONENT_NAME);
		assertEquals(3, table.getColumnCount());
		assertEquals(Messages.getString("UserTableModel.id"), table.getColumnName(0));
		assertEquals(Messages.getString("UserTableModel.first_name"), table.getColumnName(1));
		assertEquals(Messages.getString("UserTableModel.last_name"), table.getColumnName(2));

		find(JButton.class, ADD_BUTTON_COMPONENT_NAME);
		find(JButton.class, EDIT_BUTTON_COMPONENT_NAME);
		find(JButton.class, DETAILS_BUTTON_COMPONENT_NAME);
		find(JButton.class, DELETE_BUTTON_COMPONENT_NAME);
	}


	public void testAddUser() {

		try {
			User user = new User(FIRST_NAME, LAST_NAME, DATE_OF_BIRTH);

			User expectedUser = new User(new Long(1), FIRST_NAME, LAST_NAME, DATE_OF_BIRTH);
			mockUserDao.expectAndReturn("create", user, expectedUser);

			ArrayList users = new ArrayList();
			users.add(expectedUser);
			mockUserDao.expectAndReturn("findAll", users);

			JTable table = (JTable) find(JTable.class, "userTable");
			assertEquals(0, table.getRowCount());

			JButton addButton = (JButton) find(JButton.class, "addButton");
			getHelper().enterClickAndLeave(new MouseEventData(this, addButton));

			find(JPanel.class, "addPanel");

			JTextField firstNameField = (JTextField) find(JTextField.class, "firstNameField");
			JTextField lastNameField = (JTextField) find(JTextField.class, "lastNameField");
			JTextField dateOfBirthField = (JTextField) find(JTextField.class, "dateOfBirthField");

			JButton okButton = (JButton) find(JButton.class, "okButton");
			find(JButton.class, "cancelButton");


			getHelper().sendString(new StringEventData(this, firstNameField, FIRST_NAME));

			getHelper().sendString(new StringEventData(this, lastNameField, LAST_NAME));
			DateFormat formatter = DateFormat.getDateInstance();

			String date = formatter.format(DATE_OF_BIRTH);
			getHelper().sendString(new StringEventData(this, dateOfBirthField, date));

			getHelper().enterClickAndLeave(new MouseEventData(this, okButton));

			find(JPanel.class, "browsePanel");
			table = (JTable) find(JTable.class, "userTable");
			assertEquals(1, table.getRowCount());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	public void testEditUser() {
		User expectedUser = new User(new Long(1), FIRST_NAME, LAST_NAME, DATE_OF_BIRTH);

		//mockUserDao.expectAndReturn("update", expectedUser);
		mockUserDao.expect("update", expectedUser);
		ArrayList users = new ArrayList();
		users.add(expectedUser);
		mockUserDao.expectAndReturn("findAll", users);

		JTable table = (JTable) find(JTable.class, "userTable");
		assertEquals(1, table.getRowCount());


		JButton editButton = (JButton) find(JButton.class, "editButton");
		getHelper().enterClickAndLeave(new JTableMouseEventData(this, table, 0, 0, 1));
		getHelper().enterClickAndLeave(new MouseEventData(this, editButton));

		find(JPanel.class, "editPanel");

		JTextField firstNameField = (JTextField) find(JTextField.class, "firstNameField");
		JTextField lastNameField = (JTextField) find(JTextField.class, "lastNameField");

		getHelper().sendString(new StringEventData(this, firstNameField, FIRST_NAME));
		getHelper().sendString(new StringEventData(this, lastNameField, LAST_NAME));

		JButton okButton = (JButton) find(JButton.class, "okButton");
		getHelper().enterClickAndLeave(new MouseEventData(this, okButton));

		find(JPanel.class, "browsePanel");

		table = (JTable) find(JTable.class, "userTable");
		assertEquals(1, table.getRowCount());
	}

	public void testDeletePanel() {
		User expectedUser = new User(new Long(1), FIRST_NAME, LAST_NAME, DATE_OF_BIRTH);
		mockUserDao.expect("delete", expectedUser);
		ArrayList users = new ArrayList<User>();
		mockUserDao.expectAndReturn("findAll", users);
		JTable table = (JTable) find(JTable.class, "userTable");
		assertEquals(1, table.getRowCount());
		JButton deleteButton = (JButton) find(JButton.class, "deleteButton");
		getHelper().enterClickAndLeave(new JTableMouseEventData(this, table, 0, 0, 1));
		getHelper().enterClickAndLeave(new MouseEventData(this, deleteButton));
		find(JPanel.class, "browsePanel");
		table = (JTable) find(JTable.class, "userTable");
		assertEquals(0, table.getRowCount());
	}

	public void testDetailsUser() {
		User expectedUser = new User(new Long(1), FIRST_NAME, LAST_NAME, DATE_OF_BIRTH);

		ArrayList<User> users = new ArrayList<User>();
		mockUserDao.expectAndReturn(FIND_ALL_COMMAND, users);

		JTable table = (JTable) this.find(JTable.class, USER_TABLE_COMPONENT_NAME);
		assertEquals(1, table.getRowCount());

		JButton detailsButton = (JButton) this.find(JButton.class, DELETE_BUTTON_COMPONENT_NAME);
		getHelper().enterClickAndLeave(new JTableMouseEventData(this, table, 0, 0, 1));
		getHelper().enterClickAndLeave(new MouseEventData(this, detailsButton));

		this.find(JPanel.class, DETAILS_PANEL);

		this.find(JLabel.class, ID_LABEL);
		this.find(JLabel.class, FIRST_NAME_LABEL);
		this.find(JLabel.class, LAST_NAME_LABEL);
		this.find(JLabel.class, BIRTH_DATE_LABEL);
		this.find(JLabel.class, AGE_LABEL);

		JButton cancelButton = (JButton) this.find(JButton.class, CANCEL_BUTTON_COMPONENT_NAME);
		getHelper().enterClickAndLeave(new MouseEventData(this, cancelButton));

		this.find(JPanel.class, BROWSE_PANEL_COMPONENT_NAME);
		table = (JTable) this.find(JTable.class, USER_TABLE_COMPONENT_NAME);
		assertEquals(1, table.getRowCount());
	}

	private void fillFields(String firstName, String lastName, Date dateOfBirth) {
		JTextField firstNameField = (JTextField) find(JTextField.class, FIRST_NAME_FIELD_COMPONENT_NAME);
		JTextField lastNameField = (JTextField) find(JTextField.class, LAST_NAME_FIELD_COMPONENT_NAME);
		JTextField dateOfBirthField = (JTextField) find(JTextField.class, DATE_OF_BIRTH_FIELD_COMPONENT_NAME);

		getHelper().sendString(new StringEventData(this, firstNameField, firstName));
		getHelper().sendString(new StringEventData(this, lastNameField, lastName));
		String dateString = DateFormat.getInstance().format(dateOfBirth);
		getHelper().sendString(new StringEventData(this, dateOfBirthField, dateString));
	}


}