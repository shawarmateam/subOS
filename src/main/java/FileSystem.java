import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileSystem {
    public static String fs_str = "";

    public FileSystem(String img_path) {
        try {
            fs_str = new String(Files.readAllBytes(Paths.get(img_path)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean findFolder(String path) {
        if (path.charAt(path.length()-1) == '\\') return false;
        if (path.charAt(0) != '^') return false;
        if (path.contains(".")) return false;

        return fs_str.contains(path + "^") && !fs_str.contains("\\" + path + "^");
    }

    public static boolean findFile(String path) {
        if (path.charAt(path.length()-1) == '\\') return false;
        if (path.charAt(0) != '^') return false;
        if (!path.contains(".")) return false;

        return fs_str.contains(path + "/") && !fs_str.contains("\\" + path + "/");
    }

    public static String getFile(String fp) {
        if (!findFile(fp)) return "";
        String s = "";

        for (int i = fs_str.indexOf(fp)+fp.length()+1; fs_str.charAt(i) != '^'; i++) {
            char c = fs_str.charAt(i);

            if (c == '\\' && fs_str.charAt(i+1) == '^') i++;
            s+=fs_str.charAt(i);
        }
        return s;
    }
}