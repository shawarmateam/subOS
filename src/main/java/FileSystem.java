import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class FileSystem {
    public static String fs_str = "";

    public FileSystem(String img_path) {
        try {
            fs_str = new String(Files.readAllBytes(Paths.get(img_path)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(fs_str);
    }

    public static boolean findFolder(String path) {
        if (path.charAt(0) != '^') return false;
        if (path.contains(".")) return false;

        return fs_str.contains(path + "^");
    }

    public static boolean findFile(String path) {
        if (path.charAt(0) != '^') return false;
        if (!path.contains(".")) return false;

        return fs_str.contains(path + "/");
    }

    public static String getFile(String fp) {
        System.out.println(fp);
        if (!findFile(fp)) return "";
        String s = "";

        System.out.println(fs_str.indexOf(fp)+fp.length());
        for (int i = fs_str.indexOf(fp)+fp.length()+1; fs_str.charAt(i) != '^'; i++) {
            char c = fs_str.charAt(i);

            if (c == '\\' && fs_str.charAt(i+1) == '^') i++;
            s+=fs_str.charAt(i);
        }
        return s;
    }

    public static void main(String[] args) {
        System.out.println(findFile("^bin/pswrd_root."));
    }

    public static boolean isEntryFile(String name) {
        for (char c : name.toCharArray()) if (c == '.') return true;
        return false;
    }
}