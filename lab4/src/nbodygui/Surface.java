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

        initPoints();
    }

    private void initPoints() {

        int x, y;
        Random random = new Random();

        for (int i = 0; i < point.length; i++) {
            x = Math.abs(random.nextInt()) % Surfaces.WIDTH;
            y = Math.abs(random.nextInt()) % Surfaces.HEIGHT;
            point[i] = new Point(x, y);
        }
    }

    public Timer timer() {
        return timer;
    }

    private void drawRandomPoints(Graphics gr) {
        Graphics2D graphics = (Graphics2D) gr;
        graphics.setPaint(POINT_COLOR);

        for (Point value : point) {
            graphics.fillOval(value.x, value.y, POINT_SIZE, POINT_SIZE);
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
