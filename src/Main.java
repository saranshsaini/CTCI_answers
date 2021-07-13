import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        String test = "abcd";
        List<String> ans = stringPerms(test);
        for (String s : ans) {
            System.out.println(s);
        }
    }

    public static List<String> stringPerms(String input) {
        if (input.length() == 1) {
            List<String> p = new ArrayList<>();
            p.add(input);
            return p;
        }
        char last = input.charAt(input.length() - 1);
        String withoutLast = input.substring(0, input.length() - 1);
        List<String> woLast = stringPerms(withoutLast);
        List<String> withLast = new ArrayList<>();
        for (String s : woLast) {
            for (int i = 0; i < s.length(); i++) {
                String newString = s.substring(0, i) + last + s.substring(i);
                withLast.add(newString);
            }
            withLast.add(s+last);
        }
        return withLast;

    }
}
