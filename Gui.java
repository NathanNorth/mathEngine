import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;
public class Gui extends JPanel{
    JTextField field;
    JButton button;
    JLabel label;
    public Gui(){
        setLayout(new FlowLayout());

        field = new JTextField(37);
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
        field.addActionListener(new Listener1());
        add(field);

        button = new JButton("exit");
        button.addActionListener(new Listener());
        add(button);

        label = new JLabel("LOL!");
        add(label);
    }
private class Listener implements ActionListener{
    public void actionPerformed(ActionEvent e){
        System.exit(0);
    }
}
private class Listener1 implements ActionListener{
        public void actionPerformed(ActionEvent e){
            label.setText(Processor.process(Cleaner.highLevelClean(field.getText())));
        }
    }

}
