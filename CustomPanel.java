import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class CustomPanel extends JPanel implements ActionListener {

  protected CustomTextField textField;
  protected CustomTextField textField2;
  protected JTextArea textArea;
  protected JButton button;
  private final static String newline = "\n";

  private int mode; //1 for encryption, 2 for decryption

  public CustomPanel(int mode) {
      this.mode = mode;

      textField = new CustomTextField(20);
      textField.addActionListener(this);

      textField2 = new CustomTextField(20);
      textField2.addActionListener(this);

      switch (this.mode) {
            case 1:
                    textField.setPlaceholder("Text to encrypt");
                    break;
            case 2:
                    textField.setPlaceholder("Text to decrypt");
                    break;
            default:
                    break;
        }

      textField2.setPlaceholder("Password");

      textArea = new JTextArea(5, 20);
      textArea.setEditable(false);
      JScrollPane scrollPane = new JScrollPane(textArea);

      button = new JButton("Compute");
      button.addActionListener(this);

      this.add(textField);
      this.add(textField2);
      this.add(button);
      this.add(scrollPane);

      this.setLayout(new GridLayout(4, 1));


  }

  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == textField) {
        String text = textField.getText();
        textArea.setText(String.format("You've introduced %s as your text", text));
        textField.selectAll();

        //Make sure the new text is visible, even if there
        //was a selection in the text area.
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }
    else if (e.getSource() == textField2){
      String text = textField2.getText();
      textArea.setText(String.format("You've introduced %s as your password", text));
      textField2.selectAll();

      //Make sure the new text is visible, even if there
      //was a selection in the text area.
      textArea.setCaretPosition(textArea.getDocument().getLength());
    }
    else if (e.getSource() == button){
      AES aes = new AES();

      switch (this.mode) {
            case 1:  textArea.setText(aes.getEncryptionStatus(textField.getText(), textField2.getText()));
                     break;
            case 2:  textArea.setText(aes.getDecryptionStatus(textField.getText(), textField2.getText()));
                     break;
            default:
                     break;
        }

    }
    else {
      textArea.setText("There was a problem. Please check that both text fields have text.");
    }

  }

}
