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
    public static int main() throws IOException {
        String currentDir = "users/"+CurrentUser.username;
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

    public static void execCmd(String[] cmd) {
        switch (cmd[0]) {
            case "cd":
                break;
            case "clear":
                System.out.print("\033[H\033[2J");
                System.out.flush();
                break;
            case "exit":
                System.out.print("logout...");
                System.exit(0);
                break;
            default:
                System.out.println("Not a command. ("+ Arrays.toString(cmd) +")");
                break;
        }
    }
}
