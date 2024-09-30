import java.util.Objects;

public class CurrentUser {
    public static String username;
    private static String password;

    public CurrentUser(String username, String password) {
        CurrentUser.username = username;
        CurrentUser.password = password;
    }

    public void setPassword(String password, String old_password) {
        CurrentUser.password = (Objects.equals(CurrentUser.password, old_password)) ? password : CurrentUser.password;
    }
}
