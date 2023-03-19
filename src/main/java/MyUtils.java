import com.sun.jdi.CharValue;

import java.util.regex.Pattern;
/*Завдання тестування*/

public class MyUtils {
    public static boolean checkEmail(String text) {
        if (!text.contains("@") || text == null) {
            return false;
        }
        var arg1 = text.split("@");

        if (arg1.length > 2) {
            return false;
        }

        String username = arg1[0];
        if (!Character.isLetter(username.charAt(0))) {
            return false;
        }

        boolean test1 = Pattern.matches("[A-Za-z0-9\\.]+", username);

        if (!test1) return false;

        var domains = arg1[1].split("\\.");
        if (domains[0].length() < 2 || domains.length < 2 ) {
            return false;
        }
        for (int i = 0; i <domains.length; i++) {
            if (!Pattern.matches("[A-Za-z0-9]+", domains[i])) {
                return false;
            }
        }

        return true;
    }
}
