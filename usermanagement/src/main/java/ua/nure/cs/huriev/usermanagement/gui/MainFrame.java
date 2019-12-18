package ua.nure.cs.huriev.usermanagement.gui;


import ua.nure.cs.huriev.usermanagement.User;
import ua.nure.cs.huriev.usermanagement.db.Dao;
import ua.nure.cs.huriev.usermanagement.db.DaoFactory;
import ua.nure.cs.huriev.usermanagement.util.Messages;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
	private static final int HEIGHT = 600;
	private static final int WIDTH = 800;
	private JPanel contentPanel;
	private BrowsePanel browsePanel;
	private AddPanel addPanel;
	private Dao dao;
	private EditPanel editPanel;
	private DetailsPanel detailsPanel;
	private DeletePanel deletePanel;

	public MainFrame() {
		super();
		dao = DaoFactory.getInstance().getUserDao();
		initialize();
	}

	public Dao getDao() {
		return dao;
	}

	private void initialize() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(WIDTH, HEIGHT);
		this.setTitle(Messages.getString("MainFrame.user_management")); // localize
		this.setContentPane(getContentPanel());

	}

	private JPanel getContentPanel() {
		if (contentPanel == null) {
			contentPanel = new JPanel();
			contentPanel.setLayout(new BorderLayout());
			contentPanel.add(getBrowsePanel(), BorderLayout.CENTER);
		}
		return contentPanel;
	}

	private BrowsePanel getBrowsePanel() {
		if (browsePanel == null) {
			browsePanel = new BrowsePanel(this);
		}
		((BrowsePanel) browsePanel).initTable();
		return browsePanel;
	}
	private AddPanel getAddPanel() {
		if (addPanel == null) {
			addPanel = new AddPanel(this);
		}
		return addPanel;
	}

	public void showBrowsePanel() {
		showPanel(getBrowsePanel());
	}

	public void showAddPanel() {
		showPanel(getAddPanel());
	}

	public void showDetailsPanel(User user) {
		getDetailsPanel().setUser(user);
		showPanel(getDetailsPanel());
	}

	public void showDeletePanel(User user) {
		getDeletePanel().setUser(user);
		showPanel(getDeletePanel());
	}

	public void showEditPanel(User user) {
		getEditPanel().setUser(user);
		showPanel(getEditPanel());

	}

	private EditPanel getEditPanel() {
		if (editPanel == null) {
			editPanel = new EditPanel(this);
		}
		return editPanel;
	}

	private DetailsPanel getDetailsPanel() {
		if (detailsPanel == null) {
			detailsPanel = new DetailsPanel(this);
		}
		return detailsPanel;
	}

	private DeletePanel getDeletePanel() {
		if (deletePanel == null) {
			deletePanel = new DeletePanel(this);
		}
		return deletePanel;
	}

	private void showPanel(JPanel panel) {
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setVisible(true);
		panel.repaint();
	}

	public static void main(String[] args){
		MainFrame frame = new MainFrame();
		frame.setVisible(true);
	}
}