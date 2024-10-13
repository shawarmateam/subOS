import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class Login {
    public static boolean main() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter username: ");
        String username = sc.nextLine();

        System.out.print("Enter password: ");
        String password = sc.nextLine();
        String checkPwd = FileSystem.getFile("^bin/pswrd_"+username+".");

        if (BCrypt.checkpw(password, checkPwd)) {
            System.out.println("success.");
            System.out.println("creating user...");
            CurrentUser currentUser = new CurrentUser(username, password);
            System.out.print("\033[H\033[2J");
            System.out.flush();
            return true;
        } else {
            System.out.println("fail. incorrect password or username.\n");
        }
        return false;
    }
}
