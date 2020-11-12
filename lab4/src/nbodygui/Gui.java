package nbodygui;

import javax.swing.*;

import static nbodygui.Guis.*;

public class Gui {
    private JPanel panel;

    public static void main(String[] args) {
        JFrame frame = new JFrame("n-body problem solver");
        frame.setContentPane(new Gui().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        frame.setVisible(true);
    }
}
