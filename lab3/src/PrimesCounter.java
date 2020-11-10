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
}
