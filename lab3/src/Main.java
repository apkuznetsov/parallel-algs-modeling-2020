import java.util.SortedSet;

public class Main {

    public static void main(String[] args) {

        SortedSet<Integer> primes = PrimesCounter.firstPrimes(101);
        System.out.println(primes);
    }
}
