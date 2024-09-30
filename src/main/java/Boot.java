public class Boot {
    public static void main(String[] args) {
        while (true) {
            if (Login.main())
                break;
        }

        try {
            Shell.main();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
