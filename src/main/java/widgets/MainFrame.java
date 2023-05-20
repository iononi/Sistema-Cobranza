package widgets;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private JPanel panel1;
    private JLabel helloWorldLabel;

    public MainFrame () {
        super ();
        setTitle ("Sistema de cobranza");
        setSize (800, 600);
        setDefaultCloseOperation (EXIT_ON_CLOSE);
        // setLayout (new BorderLayout ());

        createUIComponents ();
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater (() -> new MainFrame ().setVisible (true));
    }

    private void createUIComponents() {
        helloWorldLabel = new JLabel ("Hello, World!");
        helloWorldLabel.setHorizontalAlignment (SwingConstants.CENTER);
        helloWorldLabel.setVerticalAlignment (SwingConstants.CENTER);
        helloWorldLabel.setFont (new Font ("Inter", Font.BOLD, 30));

        add (helloWorldLabel);
    }
}
