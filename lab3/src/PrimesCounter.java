import java.util.ArrayList;
import java.util.List;

public class PrimesCounter {

    public List<Integer> firstPrimes(int count) {

        List<Integer> primes = new ArrayList<>();
        if (count > 0) {
            primes.add(2);
        }

        for (int i = 3; primes.size() < count; i += 2) {
            if (isPrime(i, primes)) {
                primes.add(i);
            }
        }

        return primes;
    }

    private boolean isPrime(int number, List<Integer> primesBeforeNumber) {

        /* если проверяемое число состоит хотя бы из двух множителей,
         * то ни одно из них не может превышать двоичный корень: */
        double sqrtedNumber = Math.sqrt(number);

        for (int prime : primesBeforeNumber) {

            if (prime > sqrtedNumber) {
                return true;
            }

            if (number % prime == 0) {
                return false;
            }
        }

        return true;
    }
}
