import java.nio.charset.StandardCharsets;

public class Test {
    public static void main(String[] args) {
        String text = "$2a$10$dZNRvCf7kTs04lRea3hOIelkERxtN8/M4ywWWxiEH1uk6qAp.bTPe";

        // Конвертация строки в байты
        byte[] bytes = text.getBytes(StandardCharsets.UTF_16);

        // Вывод байтов
        System.out.println("Байты: " + java.util.Arrays.toString(bytes));
        String text_fr = new String(bytes, StandardCharsets.UTF_16);
        System.out.println(text_fr);
    }
}
