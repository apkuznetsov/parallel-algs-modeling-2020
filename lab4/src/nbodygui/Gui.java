package nbodygui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import static nbodygui.Guis.DELAY;
import static nbodygui.Guis.MAX_POINTS_NUM;

public class Gui extends JPanel implements ActionListener {
    private JPanel panel;
    private Timer timer;

    public Gui() {
        initTimer();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("n-body problem solver");
        frame.setContentPane(new Gui().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        frame.setVisible(true);
    }

    private void initTimer() {
        timer = new Timer(DELAY, this);
        timer.start();
    }

    private void drawRandomPoints(Graphics gr) {
        Graphics2D graphics = (Graphics2D) gr;
        graphics.setPaint(Color.blue);

        int width = getWidth();
        int height = getHeight();

        Random random = new Random();

        int x, y;
        for (int i = 0; i < MAX_POINTS_NUM; i++) {
            x = Math.abs(random.nextInt()) % width;
            y = Math.abs(random.nextInt()) % height;
            graphics.drawLine(x, y, x, y);
        }
    }

    @Override
    public void paintComponent(Graphics gr) {
        paintComponent(gr);
        drawRandomPoints(gr);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        repaint();
    }
}
