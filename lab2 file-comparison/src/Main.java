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
}
