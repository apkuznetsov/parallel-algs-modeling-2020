import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Buffer {

    private final int[] buffer;

    private final Lock locker = new ReentrantLock();
    private final Condition bufferNotEmpty = locker.newCondition();
    private final Condition bufferNotFull = locker.newCondition();

    private int bufferItemsNum = 0;
    private int writingIndex = 0;
    private int readingIndex = 0;

    public Buffer(int bufferSize) {
        buffer = new int[bufferSize];
    }
}
