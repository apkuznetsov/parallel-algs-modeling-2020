package nbody;

import nbody.exceptions.NOutOfBoundsException;

public class Solver {

    public static int MIN_N = 1;
    public static int MAX_N = 5;

    public final Body[] body;

    public Solver(final int N) {

        if (N < MIN_N || N > MAX_N) {
            throw new NOutOfBoundsException();
        }

        body = new Body[N];
        for (int i = 0; i < N; i++) {
            body[i] = new Body();
        }
    }

    public int N() {
        return body.length;
    }
}
