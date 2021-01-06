import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;
public class Gui extends JPanel{
    private JLabel label;
    private JPanel box;
    private InputBox object;
    
    public Gui(){
        setLayout(new FlowLayout());
         
        /*
        field = new JTextField(25);
        field.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                label.setText(Processor.process(Cleaner.highLevelClean(field.getText())));
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                label.setText(Processor.process(Cleaner.highLevelClean(field.getText())));
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                label.setText(Processor.process(Cleaner.highLevelClean(field.getText())));
            }
        });
        add(field);
         */
        JPanel main = new JPanel(new BorderLayout());
        
        object = new InputBox();
        box = object;
        main.add(box, BorderLayout.NORTH);
         
        label = new JLabel("0.0");
        label.setFont(new Font("Serif", Font.BOLD, 30));
        main.add(label, BorderLayout.SOUTH);
        
        add(main);
    }
    
    public void Update() {
       label.setText(Processor.process(Cleaner.highLevelClean(object.getNum())));
    }
}
