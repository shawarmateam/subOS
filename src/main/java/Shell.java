import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Shell {
    static String currentDir = "^users/"+CurrentUser.username;

    public static int main() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            System.out.print(CurrentUser.username+" > "+currentDir+" -> ");
            String cmd = reader.readLine();
            execCmd(cmd);
        }
    }

    public static void execCmd(String cmd) {
        String regex = "\"([^\"]*)\"|'([^']*)'|\\S+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(cmd);

        List<String> resultList = new ArrayList<>();

        while (matcher.find()) {
            resultList.add(matcher.group());
        }

        execCmd(resultList.toArray(new String[0]));
    }

    public static void kill() {
        System.out.print("logout...");
        System.exit(0);
    }

    public static String getPathWithDot(String path) {
        if (path.charAt(0) == '.' && path.charAt(1) != '.') {
            path=path.substring(1);
            path=currentDir+path;
            return path;
        } else if (path.charAt(0) == '.') {
            String temp = path;
            path=currentDir;

            for (int i = 0; i < currentDir.length(); i++) {
                if (path.charAt(path.length()-1) != '/') path=path.substring(0, path.length()-1);
                else break;
            }
            path=path.substring(0, path.length()-1);
        }
        return path;
    }

    public static void execCmd(String[] cmd) {
        if (cmd.length == 0) return;

        switch (cmd[0]) {
            case "cd":
                if (cmd.length == 1) currentDir = "^users/"+CurrentUser.username;
                else if (FileSystem.findFolder(getPathWithDot(cmd[1]))) currentDir = getPathWithDot(cmd[1]);
                else System.out.println("Directory '"+getPathWithDot(cmd[1])+"' does not exist.");
                break;
            case "clear":
                System.out.print("\033[H\033[2J");
                System.out.flush();
                break;
            case "exit":
                kill();
                break;
            case "getf":
                System.out.println("'"+FileSystem.getFile(cmd[1])+"'");
                break;
            case "exec":
                ExecParser.main(new String[] {cmd[1]});
                break;
            default:
                if (FileSystem.findFile("^bin/"+cmd[0]+".b")) ExecParser.main(new String[]{"^bin/"+cmd[0]+".b"});
                else System.out.println("Not a command. ("+ Arrays.toString(cmd) +")");
                break;
        }
    }
}
