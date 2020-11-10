import java.util.ArrayList;

public class PrimesFinderThread extends Thread {

    private final ArrayList<Integer> primes;
    private final int leftBound;
    private final int rightBound;

    public PrimesFinderThread(ArrayList<Integer> primes, int leftBound, int rightBound) {

        if (leftBound > rightBound) {
            throw new IllegalArgumentException();
        }

        this.primes = primes;
        this.leftBound = leftBound;
        this.rightBound = rightBound;
    }

    @Override
    public void run() {

        int startNumber = (leftBound % 2 == 0) ? leftBound + 1 : leftBound;

        for (int i = startNumber; i <= rightBound; i += 2) {
            if (isPrime(i)) {
                synchronized (primes) {
                    primes.add(i);
                    primes.notifyAll();
                }
            }
        }

        System.out.println(Thread.currentThread().getName() + " finished " + "[" + leftBound + "; " + rightBound + "]");
    }

    private boolean isPrime(int number) {

        /* если проверяемое число состоит хотя бы из двух множителей,
         * то ни одно из них не может превышать двоичный корень: */
        double sqrtedNumber = Math.sqrt(number);

        synchronized (primes) {
            while (primes.get(primes.size() - 1) < (int) sqrtedNumber) {
                try {
                    primes.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        // НЕ ПЕРЕПИСЫВАТЬ НА for (declaration : expression), А ТО ВЫБРОСИТСЯ CONCURRMODEXCEPTION:
        for (int i = 0; i < primes.size(); i++) {
            if (primes.get(i) > sqrtedNumber) {
                return true;
            }

            if (number % primes.get(i) == 0) {
                return false;
            }
        }

        return true;
    }
}
