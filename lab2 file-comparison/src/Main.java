import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws InterruptedException, IOException {

        System.out.println("Лабораторная работа №.\n" +
                "Синхронизация параллельных процессов. Сравнение файлов\n" +
                "Выполнил студент группы 6138 Кузнецов А.П.\n");

        FileReaderThread frt1 = new FileReaderThread(filesNames[0]);
        FileReaderThread frt2 = new FileReaderThread(filesNames[1]);
        FilesComparatorThread fct = new FilesComparatorThread(frt1, frt2, SHOULD_IT_WORK_IN_PARALLEL);

        frt1.start();
        frt2.start();
        fct.start();

        fct.join();
    }

    private static String[] createFiles(int linesNum, int symbolsNum) {

        final String file1Name = "file1-" + linesNum + "x" + symbolsNum + ".txt";
        final String file2Name = "file2-" + linesNum + "x" + symbolsNum + ".txt";

        try (PrintWriter writer1 = new PrintWriter(file1Name, StandardCharsets.UTF_8);
             PrintWriter writer2 = new PrintWriter(file2Name, StandardCharsets.UTF_8)) {

            String strToWrite = "a".repeat(symbolsNum);

            for (int rowIndex = 0; rowIndex < linesNum - 1; ++rowIndex) {
                writer1.println(strToWrite);
                writer2.println(strToWrite);
            }

            writer1.println("b");
            writer2.println("c");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return new String[]{file1Name, file2Name};
    }
}
