package nbody;

import nbody.exceptions.NOutOfBoundsException;

public class Solver {

    public static int MIN_N = 1;
    public static int MAX_N = 5;

    public final Body[] b;

    public Solver(final int N) {

        if (N < MIN_N || N > MAX_N) {
            throw new NOutOfBoundsException();
        }

        b = new Body[N];
        for (int i = 0; i < N; i++) {
            b[i] = new Body();
        }
    }

    public int N() {
        return b.length;
    }

    private static double distance(Body b1, Body b2) {
        return Math.sqrt(
                Math.pow(b1.p().x() - b2.p().x(), 2) + Math.pow(b1.p().y() - b2.p().y(), 2)
        );
    }
}
