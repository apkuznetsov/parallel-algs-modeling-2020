package nbody;

public class Body {

    private final Point p;
    private double v;
    private double m;

    public Body() {
        p = new Point();
        v = 0.0;
        m = 0.0;
    }

    public Point p() {
        return  p;
    }

    public double v() {
        return  v;
    }

    public double m() {
        return v;
    }

    public void setV(double v) {
        this.v = v;
    }

    public void setM(double m) {
        this.m = m;
    }
}
