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
}