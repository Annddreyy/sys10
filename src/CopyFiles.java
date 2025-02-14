import java.io.*;

public class CopyFiles {
    // Реализация паттерна Одиночка
    private static CopyFiles Instance;

    private CopyFiles() {}
    public static CopyFiles getInstance() {
        if (Instance == null)
            Instance = new CopyFiles();
        return Instance;
    }

    // Копирование файлов
    public void copyFileUsingStream(File source, File dest) throws IOException {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(source);
            os = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } finally {
            assert is != null;
            is.close();
            assert os != null;
            os.close();
        }
    }
}
