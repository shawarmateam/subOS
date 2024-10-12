public class Boot {
    public static FileSystem fileSystem = new FileSystem("./src/main/resources/test_img.imgvimgv");

    public static void main(String[] args) {
        while (true) {
            if (Login.main())
                break;
        } // ^users/root^

        try {
            Shell.main();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void kill() {
        System.out.println("BOOT KILLED.");
        System.exit(0);
    }
}
