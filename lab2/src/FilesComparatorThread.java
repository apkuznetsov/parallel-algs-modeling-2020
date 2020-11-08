import java.util.Objects;

public class FilesComparatorThread extends Thread {

    private final FileReaderThread file1;
    private final FileReaderThread file2;

    private final boolean shouldItWorkInParallel;

    public FilesComparatorThread(FileReaderThread file1, FileReaderThread file2, boolean shouldItWorkInParallel) {
        this.file1 = file1;
        this.file2 = file2;
        this.shouldItWorkInParallel = shouldItWorkInParallel;
    }

    public void run() {

        long start = System.currentTimeMillis();

        if (shouldItWorkInParallel) {
            runParallel();
        } else {
            runSequential();
        }

        file1.finish();
        file2.finish();

        long finish = System.currentTimeMillis();

        long timeConsumedMillis = finish - start;
        System.out.println("Time: " + timeConsumedMillis);
    }
}
