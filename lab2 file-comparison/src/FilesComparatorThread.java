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

        long startMillis = System.currentTimeMillis();

        if (shouldItWorkInParallel) {
            runParallel();
        } else {
            runSequential();
        }

        file1.finish();
        file2.finish();

        long finishMillis = System.currentTimeMillis();

        long consumedMillis = finishMillis - startMillis;
        System.out.println("Consumed millis: " + consumedMillis);
    }

    private void runParallel() {
        String file1Line;
        String file2Line;
        boolean isEndOfFiles;
        int currStringsNum = 1;

        while (true) {
            file1.runReadLine();
            file2.runReadLine();

            file1Line = file1.currLine();
            file2Line = file2.currLine();

            isEndOfFiles = file1Line == null & file2Line == null;
            if (isEndOfFiles) {
                break;
            }

            if (!Objects.equals(file1Line, file2Line)) {
                System.out.println("#" + currStringsNum + ":\n" +
                        "file 1 line = " + file1Line + "\n" +
                        "file 2 line = " + file2Line + "\n");
            }

            currStringsNum++;
        }
    }

    private void runSequential() {
        String file1Line;
        String file2Line;
        boolean isEndOfFiles;
        int currStringsNum = 1;

        while (true) {
            file1.runReadLine();
            file1Line = file1.currLine();
            file2.runReadLine();
            file2Line = file2.currLine();

            isEndOfFiles = file1Line == null & file2Line == null;
            if (isEndOfFiles) {
                break;
            }

            if (!Objects.equals(file1Line, file2Line)) {
                System.out.println("#" + currStringsNum + ":\n" +
                        "file 1 line = " + file1Line + "\n" +
                        "file 2 line = " + file2Line + "\n");
            }

            currStringsNum++;
        }
    }
}