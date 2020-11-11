public class Main {

    private static final int LAST_NUMBER = 200_000_000;
    private static final int THREADS_NUM = 56;

    public static void main(String[] args) {

        SequentialFindingPrimes.primes(LAST_NUMBER);
        ParallelFindingPrimes.primes(LAST_NUMBER, THREADS_NUM);
    }
}
