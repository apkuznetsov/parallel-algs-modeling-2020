import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class FileReaderThread extends Thread {

    private final String filePathName;

    private String currLine;
    private boolean isMyTurn = false;
    private boolean isMyWorkFinished = false;

    private final Lock locker = new ReentrantLock();
    private final Condition readingNextLine = locker.newCondition();
    private final Condition readingNextLineIsFinished = locker.newCondition();


    public FileReaderThread(String filePathName) {
        this.filePathName = filePathName;
    }

    public void run() {

        /* для получения блокировки вызывается метод lock(),
         * а после окончания работы с общими ресурсами вызывается метод unlock(), который снимает блокировку: */
        locker.lock();
        try {

            final Scanner scanner = new Scanner(new File(filePathName));
            while (true) {
                if (!isMyTurn) {
                    /* поток ожидает, пока не будет выполнено некоторое условие
                     * и пока другой поток не вызовет методы signal/signalAll: */
                    readingNextLine.await();
                }

                if (scanner.hasNextLine()) {
                    currLine = scanner.nextLine();
                } else {
                    currLine = null;
                }

                isMyTurn = false;

                if (isMyWorkFinished) {
                    scanner.close();
                    break;
                }

                /* сигнализирует, что поток, у которого ранее был вызван метод await(), может продолжить работу,
                 * важно в конце вызвать метод signal/signalAll, чтобы избежать возможности взаимоблокировки потоков: */
                readingNextLineIsFinished.signal();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            locker.unlock();
        }
    }
}