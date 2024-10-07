import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        // Добавление создание объектов файлов
        File source1 = new File("IN1.txt");
        File dest1 = new File("OUT1.txt");

        File source2 = new File("IN2.txt");
        File dest2 = new File("OUT2.txt");

        // Получение объекта для копирования файлов
        CopyFiles copyObject = CopyFiles.getInstance();

        // Время перед запуском на копирование
        long start = System.nanoTime();

        // Копирование файлов
        copyObject.copyFileUsingStream(source1, dest1);
        copyObject.copyFileUsingStream(source2, dest2);

        // Получение времени выполнения
        System.out.println("Time1: " + (System.nanoTime() - start) / 1_000_000.0 + " мс.");

        // Создание процессов
        FileCopyThread copyThread1 = new FileCopyThread("IN1.txt", "OUT1.txt");
        FileCopyThread copyThread2 = new FileCopyThread("IN2.txt", "OUT2.txt");

        start = System.nanoTime();

        // Запуск потоков
        copyThread1.start();
        copyThread2.start();

        copyThread1.join();
        copyThread2.join();

        System.out.println("Time2: " + (System.nanoTime() - start) / 1_000_000.0 + " мс.");
    }
}

class FileCopyThread extends Thread {
    private final String firstFileName;
    private final String secondFileName;
    private final CopyFiles copyObject = CopyFiles.getInstance();
    FileCopyThread(String firstFileName, String secondFileName) {
        this.firstFileName = firstFileName;
        this.secondFileName = secondFileName;
    }

    @Override
    public void run() {
        try {
            File source = new File(firstFileName);
            File dest = new File(secondFileName);
            copyObject.copyFileUsingStream(source, dest);
        } catch (IOException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

}