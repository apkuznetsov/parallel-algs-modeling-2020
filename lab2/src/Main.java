
public class Main {

    public static void main(String[] args) {

        File1Thread f1 = new File1Thread();
        File2Thread f2 = new File2Thread();
        FilesStringsComparatorThread fsc = new FilesStringsComparatorThread(f1, f2, false);

        f1.start();
        f2.start();
        fsc.start();

    }
}
