public class Main {

    private static final int LAST_NUMBER = 100_000;

    public static void main(String[] args) {

        SequentialFindingPrimes.primes(LAST_NUMBER);
        ParallelFindingPrimes.primes(LAST_NUMBER, 2);
    }
}
