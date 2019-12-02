package ua.nure.cs.huriev.usermanagement.gui;

import ua.nure.cs.huriev.usermanagement.db.DatabaseException;
import ua.nure.cs.huriev.usermanagement.util.Messages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BrowsePanel extends JPanel implements ActionListener {

	private static final String ADD_COMMAND = "add";
	private static final String EDIT_COMMAND = "edit";
	private static final String DELETE_COMMAND = "delete";
	private static final String DETAILS_COMMAND = "details";
	private static final String DETAILS_BUTTON_COMPONENT_NAME = "detailsButton";
	private static final String EDIT_BUTTON_COMPONENT_NAME = "editButton";
	private static final String DELETE_BUTTON_COMPONENT_NAME = "deleteButton";
	private static final String ADD_BUTTON_COMPONENT_NAME = "addButton";
	private static final String USER_TABLE_COMPONENT_NAME = "userTable";
	private static final String BROWSE_PANEL_COMPONENT_NAME = "browsePanel";

	private MainFrame parent;
	private JScrollPane tablePanel;
	private JTable userTable;
	private JPanel buttonsPanel;
	private JButton addButton;
	private JButton editButton;
	private JButton deleteButton;
	private JButton detailsButton;

	public BrowsePanel(MainFrame mainFrame) {
		parent = mainFrame;
		initialize();
	}
	private void initialize() {
		this.setName(BROWSE_PANEL_COMPONENT_NAME);
		this.setLayout(new BorderLayout());
		this.add(getTablePanel(), BorderLayout.CENTER);
		this.add(getButtonsPanel(), BorderLayout.SOUTH);
	}
	private JPanel getButtonsPanel() {
		if (buttonsPanel == null) {
			buttonsPanel = new JPanel();
			buttonsPanel.add(getAddButton(), null);
			buttonsPanel.add(getEditButton(), null);
			buttonsPanel.add(getDeleteButton(), null);
			buttonsPanel.add(getDetailsButton(), null);
		}
		return buttonsPanel;
	}
	private JButton getEditButton() {
		if (editButton == null) {
			editButton = new JButton();
			editButton.setText(Messages.getString("BrowsePanel.edit")); //localize
			editButton.setName(EDIT_BUTTON_COMPONENT_NAME);
			editButton.setActionCommand(EDIT_COMMAND);
			editButton.addActionListener(this);
		}
		return editButton;
	}

	private JButton getDeleteButton() {
		if (deleteButton == null) {
			deleteButton = new JButton();
			deleteButton.setText(Messages.getString("BrowsePanel.delete")); //localize
			deleteButton.setName(DELETE_BUTTON_COMPONENT_NAME);
			deleteButton.setActionCommand(DELETE_COMMAND);
			deleteButton.addActionListener(this);
		}
		return deleteButton;
	}

	private JButton getDetailsButton() {
		if (detailsButton == null) {
			detailsButton = new JButton();
			detailsButton.setText(Messages.getString("BrowsePanel.details")); //localize
			detailsButton.setName(DETAILS_BUTTON_COMPONENT_NAME);
			detailsButton.setActionCommand(DETAILS_COMMAND);
			detailsButton.addActionListener(this);
		}
		return detailsButton;
	}

	private JButton getAddButton() {
		if (addButton == null) {
			addButton = new JButton();
			addButton.setText(Messages.getString("BrowsePanel.add")); //localize
			addButton.setName(ADD_BUTTON_COMPONENT_NAME);
			addButton.setActionCommand(ADD_COMMAND);
			addButton.addActionListener(this);
		}
		return addButton;
	}
	private JScrollPane getTablePanel() {
		if (tablePanel == null) {
			tablePanel = new JScrollPane(getUserTable());
		}
		return tablePanel;
	}
	private JTable getUserTable() {
		if (userTable == null) {
			userTable = new JTable();
			userTable.setName(USER_TABLE_COMPONENT_NAME);
		}
		return userTable;
	}

	public void initTable() {
		UserTableModel model;
		try {
			model = new UserTableModel(parent.getDao().findAll());
		} catch (DatabaseException e) {
			model = new UserTableModel(new ArrayList());
			JOptionPane.showMessageDialog(this,e.getMessage(),"Error",
					JOptionPane.ERROR_MESSAGE);
		}
		getUserTable().setModel(model);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		if ("add".equalsIgnoreCase(actionCommand)){
			this.setVisible(false);
			parent.showAddPanel();
		}

	}
}