package nbodygui;

import nbody.NbodySolver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import static nbodygui.Surfaces.*;

public class Surface extends JPanel implements ActionListener {

    private final Point[] point;
    private final Timer timer;

    public Surface(NbodySolver solver) {
        point = new Point[MAX_POINTS_NUM];

        timer = new Timer(solver.DT(), this);
        timer.start();
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
