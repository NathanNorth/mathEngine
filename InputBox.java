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
               onListen();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
               onListen();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
               onListen();;
            }
      });
      field.setText(start);
      add(field);

   }
   
   //this runs everytime the text box gets updated, calls a normal update if there is no slash, helps create the division object if there is a /
   private void onListen() {
      this.number = this.field.getText();
      
      //when we have a slash, depending on where its located we create our division panel with different starting values
      if(number.contains("/")) {
         String[] split = number.split("/");
         if (split.length == 2 && number.charAt(0) == '/') addDivBox("0", split[1]); //check if user enters in smth like /123 and make it 0/123
         else if (split.length == 2) addDivBox(split[0], split[1]);
         else if (split.length == 1 && number.charAt(0) != '/') addDivBox(split[0], "1");
         }
      else GuiDriver.guiObj.update(); //calls our guidriver object and tells it to start updating.

      //for debug
      System.out.println(field.getText());
      
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
      revalidate(); //may or may not be nessesary NEEDS TESTING
   }
   
   public String getNum() {
      if(!host) return number; //just return our own number if we aren't a host
      else if(dividePanel != null) return dividePanel.getNum(); //get da hosts num if we need dat
      return ""; //should only run for like half a second
   }

}