package ua.nure.cs.huriev.usermanagement.gui;

import ua.nure.cs.huriev.usermanagement.User;
import ua.nure.cs.huriev.usermanagement.db.DatabaseException;
import ua.nure.cs.huriev.usermanagement.util.Messages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;

public class AddPanel extends JPanel implements ActionListener {
	private MainFrame parent;
	private JPanel buttonPanel;
	private JPanel fieldPanel;
	private JButton cancelButton;
	private JButton okButton;
	private JTextField dateOfBirthField;
	private JTextField lastNameField;
	private JTextField firstNameField;
	private Color bgcolor;

	public AddPanel(MainFrame parent) {
		this.parent = parent;
		initialize();
	}

	private void initialize(){
		this.setName("addPanel");
		this.setLayout(new BorderLayout());
		this.add(getFieldPanel(),BorderLayout.NORTH);
		this.add(getButtonPanel(),BorderLayout.SOUTH);

	}
	private  JPanel getButtonPanel(){
		if (buttonPanel == null){
			buttonPanel = new JPanel();
			buttonPanel.add(getOkButton(),null);
			buttonPanel.add(getCancelButton(),null);
		}
		return buttonPanel;
	}

	private JButton getCancelButton() {
		if(cancelButton == null){
			cancelButton = new JButton();
			cancelButton.setText(Messages.getString("AddPanel.cancel"));
			cancelButton.setName("cancelButton");
			cancelButton.setActionCommand("cancel");
			cancelButton.addActionListener(this);
		}
		return cancelButton;
	}

	private JButton getOkButton() {
		if(okButton == null){
			okButton = new JButton();
			okButton.setText(Messages.getString("AddPanel.ok"));
			okButton.setName("okButton");
			okButton.setActionCommand("ok");
			okButton.addActionListener(this);
		}
		return okButton;
	}

	private JPanel getFieldPanel(){
		if(fieldPanel == null){
			fieldPanel = new JPanel();
			fieldPanel.setLayout(new GridLayout(3,2));
			addLabeledField(fieldPanel, Messages.getString("AddPanel.first_name"), getFirstNameField()); //$NON-NLS-1$
			addLabeledField(fieldPanel, Messages.getString("AddPanel.last_name"), getLastNameField()); //$NON-NLS-1$
			addLabeledField(fieldPanel, Messages.getString("AddPanel.date_of_birth"), getDateOfBirthField()); //$NON-NLS-1$
		}
		return fieldPanel;
	}

	private JTextField getDateOfBirthField() {
		if (dateOfBirthField == null){
			dateOfBirthField = new JTextField();
			dateOfBirthField.setName("dateOfBirthField");
		}
		return dateOfBirthField;
	}

	private JTextField getLastNameField() {
		if (lastNameField == null){
			lastNameField = new JTextField();
			lastNameField.setName("lastNameField");
		}
		return lastNameField;
	}

	private void addLabeledField(JPanel panel, String labelText, JTextField textField) {
		JLabel label = new JLabel(labelText);
		label.setLabelFor(textField);
		panel.add(label);
		label.add(textField);
	}

	private JTextField getFirstNameField() {
		if (firstNameField == null){
			firstNameField = new JTextField();
			firstNameField.setName("firstNameField");
		}
		return firstNameField;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if ("ok".equalsIgnoreCase(e.getActionCommand())) {
			User user = new User();
			user.setFirstName(getFirstNameField().getText());
			user.setLastName(getLastNameField().getText());
			DateFormat format = DateFormat.getDateInstance();

			try {
				user.setDateOfBirth(format.parse(getDateOfBirthField().getText()));
			} catch (ParseException e1) {
				getDateOfBirthField().setBackground(Color.RED);
				return;
			}


			try {
				parent.getDao().create(user);
			} catch (DatabaseException e1) {
				JOptionPane.showMessageDialog(this, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		clearFields();
		this.setVisible(false);
		parent.showBrowsePanel();

	}

	private void clearFields() {
		getFirstNameField().setText("");
		getFirstNameField().setBackground(bgcolor);

		getLastNameField().setText("");
		getLastNameField().setBackground(bgcolor);

		getDateOfBirthField().setText("");
		getDateOfBirthField().setBackground(bgcolor);
	}
}