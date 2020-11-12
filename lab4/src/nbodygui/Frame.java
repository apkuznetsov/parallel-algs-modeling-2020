package nbodygui;

import nbody.Bodies;
import nbody.NbodySolver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static nbodygui.Surfaces.TITLE;

public class Frame extends JFrame {

    public Frame() {
        super(TITLE);
        initGui();
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            Frame frame = new Frame();
            frame.setVisible(true);
        });
    }

    private void initGui() {

        final NbodySolver solver = new NbodySolver(Bodies.MAX_N, Bodies.MIN_DT);
        final Surface surface = new Surface(solver);
        add(surface);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Timer timer = surface.timer();
                timer.stop();
            }
        });

        setSize(Surfaces.WIDTH, Surfaces.HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
