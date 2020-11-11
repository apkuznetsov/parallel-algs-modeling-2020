package nbody;

import nbody.exceptions.NOutOfBoundsException;

public class Solver {

    public static int MIN_N = 1;
    public static int MAX_N = 5;

    public final int N; // публичное поле, потому что константа

    public Solver(final int N) {

        if (N < MIN_N || N > MAX_N) {
            throw new NOutOfBoundsException();
        }

        this.N = N;
    }
}
