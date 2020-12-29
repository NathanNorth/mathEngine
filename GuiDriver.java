import javax.swing.*;
public class GuiDriver {
    public static void main(String args[]) {
        JFrame frame = new JFrame("Math Engine (v0.3141592653589793238462643383279502)");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 130);
        frame.getContentPane().add(new Gui()); // Adds Button to content pane of frame
        frame.setVisible(true);
    }
}
