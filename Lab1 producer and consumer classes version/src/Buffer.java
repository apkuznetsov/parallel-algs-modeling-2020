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

    public void write(int item) throws InterruptedException {
        locker.lock();
        try {

            // ждать пока буфера не освободится:
            if (bufferItemsNum == buffer.length) {
                bufferNotEmpty.await();
            }

            buffer[writingIndex] = item;
            writingIndex++;
            bufferItemsNum++;

            // для цикличности буфера:
            if (writingIndex >= buffer.length) {
                writingIndex = 0;
            }

            bufferNotFull.signalAll();

        } finally {
            locker.unlock();
        }
    }

    public int read() throws InterruptedException {
        locker.lock();
        try {

            int item;

            // ждать пока буфер не заполнится:
            if (bufferItemsNum == 0) {
                bufferNotFull.await();
            }

            item = buffer[readingIndex];
            readingIndex++;
            bufferItemsNum--;

            // для цикличности буфера:
            if (readingIndex >= buffer.length) {
                readingIndex = 0;
            }

            bufferNotEmpty.signalAll();

            return item;

        } finally {
            locker.unlock();
        }
    }
}
