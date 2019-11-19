package ua.nure.cs.huriev.usermanagement;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private static final int HEIGHT = 600;
    private static final int WIDTH = 800;
    private BrowsePanel browsePanel;
private JPanel contentPanel;
    public MainFrame(){
        super();
        //dao
        initialize();
    }

    private void initialize() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(WIDTH,HEIGHT);
        this.setTitle("Управление пользователями"); //Localize
        this.setContentPane(getContentPanel());
    }

    private JPanel  getContentPanel() {
        if (contentPanel == null){
            contentPanel = new JPanel();
            contentPanel.setLayout(new BorderLayout());
            contentPanel.add(getBrowsePanel(), BorderLayout.CENTER);
        }
        return contentPanel;
    }

    private BrowsePanel getBrowsePanel() {
        if (browsePanel == null){
            browsePanel = new BrowsePanel(this);
        }
        return browsePanel;
    }
}
