import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;

public class DivBox extends JPanel {
   JLabel spacer;
   InputBox top;
   InputBox bottom;
   
   public DivBox(String topStart, String bottomStart) {
      top = new InputBox(topStart);
      add(top, BorderLayout.NORTH);
      
      spacer = new JLabel("/");
      add(spacer, BorderLayout.CENTER);
      
      bottom = new InputBox(bottomStart);
      add(bottom, BorderLayout.SOUTH);
   }
   
   public String getNum() {
      //if check to avoid passing in garbage
      if(top.getNum().equals("") || bottom.getNum().equals("")) return "";
      else return "(" + top.getNum() + ") / (" + bottom.getNum() + ")";
   }
   
}