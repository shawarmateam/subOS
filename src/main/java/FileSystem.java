import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class FileSystem {

    private static final String fs_path = "./src/main/resources/test_img.imgv";

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

    public static byte[] getFile(String filePathInZip) throws IOException {
        try (ZipInputStream zipInputStream = new ZipInputStream(Files.newInputStream(Paths.get(fs_path)))) {
            ZipEntry entry;
            while ((entry = zipInputStream.getNextEntry()) != null) {
                if (entry.getName().equals(filePathInZip)) {
                    return readInputStream(zipInputStream);
                }
            }
        }
        return null;
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
