import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;

public class InputBox extends JPanel { 

   public String number;

   public JPanel InputBox() {
      JTextField field = new JTextField(25);
      field.getDocument().addDocumentListener(new DocumentListener() {
         @Override
            public void insertUpdate(DocumentEvent e) {
               number = field.getText();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
               number = field.getText();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
               number = field.getText();
            }
      });
      
      JPanel panel = new JPanel(new BorderLayout());
      panel.setBackground(Color.red);
      panel.add(field, BorderLayout.CENTER);
      return panel;
   }

}