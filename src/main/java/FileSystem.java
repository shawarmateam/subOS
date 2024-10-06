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

    public File getFile(String filePath) throws IOException {
        String[] path = filePath.split("/");
        String fs_contains = "";
        Folder filesystem = new Folder("fs");


        return null;
    }

    public Folder getFolder(String folderPath) throws IOException {
        Folder filesystem = new Folder("^");
        int[] line = new int[0];

        try (FileInputStream fis = new FileInputStream(fs_path)) {
            ArrayList<Integer> bytes = new ArrayList<>();
            int byteRead;
            while ((byteRead = fis.read()) != -1) {
                bytes.add(byteRead);
            }

            line = new int[bytes.size()];
            for (int i = 0; i < bytes.size(); i++) {
                line[i] = bytes.get(i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (line.length == 0) return null;

        while (true)
        {
            int i = 0;
            if (line[i] == 0x01)
            {
                String sectorName = readSectorName(i, line);
                assert sectorName != null;
                if (isSectorFile(sectorName))
                {
                    filesystem.files.add(new File(sectorName));
                } else
                {
                    filesystem.folders.add(new Folder(sectorName));
                }
            }
        }


        return null;
    }

    private String readSectorName(int i, int[] line) {
        if (i != 0x02) return null;
        String name = "";

        while (true)
        {
            i++;
            if (line[i] == 0x02) break;
            name += line[i];
        }
        return (!name.isEmpty()) ? name : null;
    }

    private boolean isSectorFile(String sectorName)
    {
        for (int i = 0; i < sectorName.length(); i++) if (sectorName.charAt(i) == '.') return true;
        return false;
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
