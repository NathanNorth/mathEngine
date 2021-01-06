import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;

//maybe this could be static lmao
public class Gui extends JPanel{
    private JLabel label;
    private InputBox box;
    
    public Gui(){
        setLayout(new FlowLayout());
        
        //main panel everything connects to
        JPanel main = new JPanel(new BorderLayout());
        
        //default text box to start
        box = new InputBox("");
        main.add(box, BorderLayout.NORTH);
        
        //answer label
        label = new JLabel("0.0");
        label.setFont(new Font("Serif", Font.BOLD, 30));
        main.add(label, BorderLayout.SOUTH);
        
        //add main panel to the gui panel
        add(main);
        
        //for debuging and testing
        //box.addDivBox();
    }
    
    //generic update that pings the first box for its number
    public void update() {
       label.setText(Processor.process(Cleaner.highLevelClean(box.getNum())));
    }
}
