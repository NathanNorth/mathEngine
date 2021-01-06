import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;

public class InputBox extends JPanel { 

   public String number = ""; //defaults to nothing because passing in null is cringe and crashes the code when we are .getNum*()-ed w/out any text being entered
   public JTextField field;
   private boolean host = false; //represents whether the panel is a host of a divbox panel or not
   private DivBox dividePanel;
   
   public InputBox(String start) {
      System.out.println("InputBoxCreated");
      field = new JTextField(25);
      
      //disgusting code implements a fully custom document listener class inline because of couse it does. would be cool to implement individually
      field.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
               number = field.getText(); //updates our number
               
               if(number.contains("/")) {
                  String[] split = number.split("/");
                  addDivBox(split[0], "1"); //SCUFFED DOESNT WORK FOR MID LINE INSERTION
               }
               else GuiDriver.guiObj.update(); //calls our guidriver object and tells it to start updating. probably bad bug solving w/ if statement

               //for debug
               System.out.println(field.getText());

            }

            @Override
            public void removeUpdate(DocumentEvent e) {
               number = field.getText();
               GuiDriver.guiObj.update();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
               number = field.getText();
               GuiDriver.guiObj.update();
            }
      });
      field.setText(start);
      add(field);

   }
   
   //method to remove existing box and insert a divbox
   public void addDivBox(String topStart, String bottomStart) {
      //get rid of old crap
      host = true;
      remove(field);
      revalidate();
      
      //add new crap
      dividePanel = new DivBox(topStart, bottomStart);
      add(dividePanel);
      revalidate(); //may not be nessesary NEEDS TESTING
   }
   
   public String getNum() {
      if(!host) return number; //just return our own number if we aren't a host
      else if(dividePanel != null) return dividePanel.getNum(); //get da hosts num if we need dat
      return ""; //should only run for like half a second
   }

}