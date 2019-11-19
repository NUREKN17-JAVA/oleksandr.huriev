package ua.nure.cs.huriev.usermanagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BrowsePanel extends JPanel implements ActionListener {

    public static final String BROWSE_PANEL_COMPONENT_NAME = "browsePanel";
    public static final String USER_TABLE_COMPONENT_NAME = "userTable";
    public static final String ADD_BUTTON_COMPONENT_NAME = "addButton";
    public static final String EDIT_BUTTON_COMPONENT_NAME = "editButton";
    public static final String DELETE_BUTTON_COMPONENT_NAME = "deleteButton";
    public static final String DETAIL_BUTTON_COMPONENT_NAME = "detailButton";
    public static final String ADD_COMMAND = "add";
    private static final String EDIT_COMMAND = "edit";
    private static final String DELETE_COMMAND = "delete";
    private static final String DETAIL_COMMAND = "detail";

    private MainFrame parent;
    private JScrollPane tablePanel;
    private JTable userTable;
    private JPanel buttonPanel;
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
        this.add(getButtonsPanel(),BorderLayout.SOUTH);
    }

    private JPanel getButtonsPanel() {
        if (buttonPanel == null){
            buttonPanel = new JPanel();
            buttonPanel.add(getAddButton(),null);
            buttonPanel.add(getEditButton(),null);
            buttonPanel.add(getDeleteButton(),null);
            buttonPanel.add(getDetailsButton(),null);
        }
        return buttonPanel;
    }

    private JButton getDetailsButton() {
        if(detailsButton == null){
            detailsButton = new JButton();
            detailsButton.setText("Подробнее"); //loc
            detailsButton.setName(DETAIL_BUTTON_COMPONENT_NAME);
            detailsButton.setActionCommand(DETAIL_COMMAND);
            detailsButton.addActionListener(this);
        }
        return detailsButton;
    }

    private JButton getDeleteButton() {
        if(deleteButton == null){
            deleteButton = new JButton();
            deleteButton.setText("Удалить"); //loc
            deleteButton.setName(DELETE_BUTTON_COMPONENT_NAME);
            deleteButton.setActionCommand(DELETE_COMMAND);
            deleteButton.addActionListener(this);
        }
        return deleteButton;
    }

    private JButton getEditButton() {
        if(editButton == null){
            editButton = new JButton();
            editButton.setText("Добавить"); //loc
            editButton.setName(EDIT_BUTTON_COMPONENT_NAME);
            editButton.setActionCommand(EDIT_COMMAND);
            editButton.addActionListener(this);
        }
        return editButton;
    }

    private JButton getAddButton() {
        if(addButton == null){
            addButton = new JButton();
            addButton.setText("Добавить"); //loc
            addButton.setName(ADD_BUTTON_COMPONENT_NAME);
            addButton.setActionCommand(ADD_COMMAND);
            addButton.addActionListener(this);
        }
        return addButton;
    }

    private JScrollPane getTablePanel() {
        if(tablePanel == null){
            tablePanel = new JScrollPane(getUserTable());
        }
        return tablePanel;
    }

    private JTable getUserTable() {
        if (userTable == null){
            userTable = new JTable();
            userTable.setName(USER_TABLE_COMPONENT_NAME);
        }
        return userTable;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
