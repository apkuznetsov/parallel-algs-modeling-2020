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
}
