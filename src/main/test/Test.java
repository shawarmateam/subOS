import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
    public static void main(String[] args) {
        String input = "Это 'пример строки' с \"разными пробелами\" и обычными пробелами";
        String regex = "\"([^\"]*)\"|'([^']*)'|\\S+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        List<String> resultList = new ArrayList<>();

        while (matcher.find()) {
            resultList.add(matcher.group());
        }

        // Преобразуем список в массив
        String[] resultArray = resultList.toArray(new String[0]);

        // Выводим массив
        for (String str : resultArray) {
            System.out.println(str);
        }
    }
}
