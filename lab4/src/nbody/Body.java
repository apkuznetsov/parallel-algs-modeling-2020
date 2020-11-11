package nbody;

public class Body {

    private final Point p;
    private final Point v;
    private Point f;
    private final double m;

    public Body() {
        p = new Point(0.1, 0.1);
        v = new Point(0.1, 0.1);
        f = new Point(0.1, 0.1);
        m = 0.1;
    }

    public Body(double m) {
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
        return  f;
    }

    public void setF(double x, double y) {
        this.f = new Point(x, y);
    }

    public double m() {
        return m;
    }
}
