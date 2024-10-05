public class Boot {
    public static FileSystem fileSystem = new FileSystem("./src/main/resources/test_img.imgv");

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
