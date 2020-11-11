package nbody;

import nbody.exceptions.MOutOfBoundsException;

public class Body {

    private static final double MIN_M = 0.1;
    private static final double MAX_M = 100;

    private final double m;
    private Point p;
    private Point v;
    private Point f;

    public Body() {
        p = new Point(0.1, 0.1);
        v = new Point(0.1, 0.1);
        f = new Point(0.1, 0.1);
        m = MIN_M;
    }

    public Body(double m) {

        if (m < MIN_M || m > MAX_M) {
            throw new MOutOfBoundsException();
        }

        p = new Point(0.1, 0.1);
        v = new Point(0.1, 0.1);
        f = new Point(0.1, 0.1);
        this.m = m;
    }

    public Point p() {
        return p;
    }

    public Point v() {
        return v;
    }

    public Point f() {
        return f;
    }

    public void setP(double x, double y) {
        this.p = new Point(x, y);
    }

    public void setV(double x, double y) {
        this.v = new Point(x, y);
    }

    public void setF(double x, double y) {
        this.f = new Point(x, y);
    }

    public double m() {
        return m;
    }
}
