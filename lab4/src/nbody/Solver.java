package nbody;

import nbody.exceptions.NOutOfBoundsException;

import static nbody.Bodies.*;

public class Solver {

    private final Body[] b;
    private final long millis;
    private final long dt;

    public Solver(final int N, final long MILLIS, final long DT) {

        if (N < MIN_N || N > MAX_N) {
            throw new NOutOfBoundsException();
        }

        b = new Body[N];
        for (int i = 0; i < N; i++) {
            b[i] = new Body();
        }

        millis = MILLIS;
        dt = DT;
    }

    public int N() {
        return b.length;
    }

    public void recalcNBodiesCoords() {
        calcNForces();
        moveNBodies();
    }

    private void calcNForces() {
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

    private void moveNBodies() {
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
