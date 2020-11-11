package nbody;

import nbody.exceptions.NOutOfBoundsException;

import static nbody.Gravity.G;

public class Solver {

    public static final int MIN_N = 1;
    public static final int MAX_N = 5;

    private final Body[] b;
    private final long dt;

    public Solver(final int N, final long DT) {

        if (N < MIN_N || N > MAX_N) {
            throw new NOutOfBoundsException();
        }

        b = new Body[N];
        for (int i = 0; i < N; i++) {
            b[i] = new Body();
        }

        dt = DT;
    }

    private static double distance(Body b1, Body b2) {
        return Math.sqrt(
                Math.pow(b1.p().x() - b2.p().x(), 2) + Math.pow(b1.p().y() - b2.p().y(), 2)
        );
    }

    private static double magnitude(Body b1, Body b2, double b1b2distance) {
        return G * b1.m() * b2.m() / Math.pow(b1b2distance, 2);
    }

    private static Point direction(Body b1, Body b2) {
        return new Point(
                b2.p().x() - b1.p().x(),
                b2.p().y() - b1.p().y()
        );
    }

    private static Point dv(Body b, long dt) {
        return new Point(
                b.f().x() / b.m() * dt,
                b.f().y() / b.m() * dt
        );
    }

    private static Point dp(Body b, long dt, Point dv) {
        return new Point(
                (b.v().x() + dv.x() / 2) * dt,
                (b.v().y() + dv.y() / 2) * dt
        );
    }

    public int N() {
        return b.length;
    }

    public void calcNForces() {
        double distance;
        double magnitude;
        Point direction;

        final int N = b.length;
        for (int i = 1; i <= N - 1; i++) {
            for (int j = i + 1; j <= N; j++) {
                distance = distance(b[i], b[j]);
                magnitude = magnitude(b[i], b[j], distance);
                direction = direction(b[i], b[j]);

                b[i].setF(
                        b[i].f().x() + magnitude * direction.x() / distance,
                        b[i].f().y() + magnitude * direction.y() / distance
                );

                b[j].setF(
                        b[i].f().x() - magnitude * direction.x() / distance,
                        b[i].f().y() - magnitude * direction.y() / distance
                );
            }
        }
    }

    public void moveNBodies() {
        Point dv; // dv = f/m * dt
        Point dp; // dp = (v + dv/2) * dt

        for (int i = 1; i <= b.length; i++) {
            dv = dv(b[i], dt);
            dp = dp(b[i], dt, dv);

            b[i].setV(
                    b[i].v().x() + dv.x(),
                    b[i].v().y() + dv.y()
            );

            b[i].setP(
                    b[i].p().x() + dp.x(),
                    b[i].p().y() + dp.y()
            );

            b[i].setF(0.0, 0.0);
        }
    }
}
