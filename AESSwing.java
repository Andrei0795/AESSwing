import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class AESSwing extends JPanel {


    public AESSwing() {
        super(new GridLayout(1, 1));

        JTabbedPane tabbedPane = new JTabbedPane();

        JComponent panel1 = makeTextPanel();
        tabbedPane.addTab("Encryption", null, panel1,
                "Does nothing");
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

        JComponent panel2 = makeTextPanel2();
        tabbedPane.addTab("Decryption", null, panel2,
                "Does twice as much nothing");
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

        JComponent panel3 = makeTextPanel3();
        tabbedPane.addTab("About", null, panel3,
                "Still does nothing");
        tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);

        //Add the tabbed pane to this panel.
        add(tabbedPane);

        //The following line enables to use scrolling tabs.
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
    }

    protected JComponent makeTextPanel() {
      JPanel mainPanel = new CustomPanel(1);
        return mainPanel;
    }

    protected JComponent makeTextPanel2() {
      JPanel mainPanel = new CustomPanel(2);
        return mainPanel;
}

    protected JComponent makeTextPanel3() {
    JPanel panel = new JPanel(false);
    JTextArea textArea = new JTextArea(5, 10);
    textArea.setLineWrap(true);
    textArea.setEditable(false);
    String about = "This is a simple app that helps encrypt/decrypt plain/cryptographic text by using AES."
                  + " AES is refered to as the Advanced Encryption Standard."
                  + " Credits for understanding AES in java go to aesencryption.net"
                  + " and lazicbrano.wordpress.com for the placeholder text field."
                  + " Tutorials from Oracle and Oracle examples were used as well."
                  + " Thanks for using the program! :)";
    textArea.setText(about);
    panel.setLayout(new GridLayout(1, 1));
    panel.add(textArea);
    return panel;
    }


    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("AESSwing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add contents to the window.
        frame.add(new AESSwing());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
