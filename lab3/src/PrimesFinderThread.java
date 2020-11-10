import java.util.Set;
import java.util.SortedSet;

public class PrimesFinderThread extends Thread {

    private final SortedSet<Integer> primes;
    private final int leftBound;
    private final int rightBound;

    public PrimesFinderThread(SortedSet<Integer> primes, int leftBound, int rightBound) {
        this.primes = primes;
        this.leftBound = leftBound;
        this.rightBound = rightBound;
    }
}
