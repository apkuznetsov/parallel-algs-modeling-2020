package nbodygui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import static nbodygui.Surfaces.*;

public class Surface extends JPanel implements ActionListener {

    private final Point[] point;
    private final Timer timer;

    public Surface() {
        point = new Point[MAX_POINTS_NUM];

        timer = new Timer(DELAY, this);
        timer.start();

        initPoint();
    }
    
    public Timer timer() {
        return timer;
    }

    private void drawRandomPoints(Graphics gr) {
        Graphics2D graphics = (Graphics2D) gr;
        graphics.setPaint(POINT_COLOR);

        int width = getWidth();
        int height = getHeight();

        Random random = new Random();

        int x, y;
        for (int i = 0; i < MAX_POINTS_NUM; i++) {
            x = Math.abs(random.nextInt()) % width;
            y = Math.abs(random.nextInt()) % height;
            graphics.fillOval(x, y, POINT_SIZE, POINT_SIZE);
        }
    }

    @Override
    public void paintComponent(Graphics gr) {
        super.paintComponent(gr);
        drawRandomPoints(gr);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        repaint();
    }
}
