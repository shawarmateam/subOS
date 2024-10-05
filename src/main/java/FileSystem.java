import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class FileSystem {

    private final String fs_path;

    public FileSystem(String fs_path) {
        this.fs_path = fs_path;
    }

    public static String xorEncryptDecrypt(String input, String key) {
        StringBuilder output = new StringBuilder();
        int keyLength = key.length();

        for (int i = 0; i < input.length(); i++) {
            output.append((char) (input.charAt(i) ^ key.charAt(i % keyLength)));
        }

        return output.toString();
    }

    public static void decrypt_fs() {

    }

    public byte[] getFile(String filePath) throws IOException {
        String[] files = filePath.split("/");
        String fs_contains = "";
        Folder filesystem = new Folder("fs");

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine();
            while (line != null) {
                line = br.readLine();
                fs_contains += '\n' + line;
            }
        }
        return null;
    }

    public class Folder {
        public String name;
        public ArrayList<File> files;
        public ArrayList<Folder> folders;

        public Folder(String name, File[] files, Folder[] folders) {
            this.name = name;
            this.files.addAll(Arrays.asList(files));
            this.folders.addAll(Arrays.asList(folders));
        }

        public Folder(String name) {
            this.name = name;
        }
    }

    public class File {
        public String name;
        public byte[] data;

        public File(String name, byte[] data) {
            this.name = name;
            this.data = data;
        }

        public File(String name) {
            this.name = name;
        }
    }

    private static byte[] readInputStream(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) != -1) {
            byteArrayOutputStream.write(buffer, 0, length);
        }
        return byteArrayOutputStream.toByteArray();
    }
}
