import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;

public class InputBox extends JPanel { 

   public String number;
   public JTextField field;
   
   public InputBox() {
      System.out.println("bro");
      field = new JTextField(25);
      field.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
               number = field.getText();
               GuiDriver.guiObj.Update();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
               number = field.getText();
               GuiDriver.guiObj.Update();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
               number = field.getText();
               GuiDriver.guiObj.Update();
            }
      });
      add(field);

   }
   public String getNum() {
      return number;
   }

}