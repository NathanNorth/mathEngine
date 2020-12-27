import javax.swing.*;
public class GuiDriver {
    public static void main(String args[]) {
        JFrame frame = new JFrame("My First GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 100);
        frame.getContentPane().add(new Gui()); // Adds Button to content pane of frame
        frame.setVisible(true);
    }
}
