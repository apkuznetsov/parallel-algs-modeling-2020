
public class Main {

    public static final String FILE1_PATH = "File1.txt";
    public static final String FILE2_PATH = "File2.txt";

    public static void main(String[] args) {

        FileReaderThread frt1 = new FileReaderThread(FILE1_PATH);
        FileReaderThread frt2 = new FileReaderThread(FILE2_PATH);
        FilesComparatorThread fct = new FilesComparatorThread(frt1, frt2, false);

        frt1.start();
        frt2.start();
        fct.start();
    }
}
