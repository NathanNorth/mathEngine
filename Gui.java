import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Gui extends JPanel{
    public Gui(){
        setLayout(new FlowLayout());

        JTextField field = new JTextField(35);
        field.addActionListener(new Listener1());
        add(field);

        JButton button = new JButton("exit");
        button.addActionListener(new Listener());
        add(button);

        JLabel label = new JLabel("LOL!");
        add(label);
    }
private class Listener implements ActionListener{
    public void actionPerformed(ActionEvent e){
        System.exit(0);
    }
}
private class Listener1 implements ActionListener{
        public void actionPerformed(ActionEvent e){
            System.exit(0);
        }
    }

}
