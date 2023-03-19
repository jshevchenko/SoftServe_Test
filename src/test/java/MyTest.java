import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.ParameterizedTest;
import static org.junit.jupiter.api.Assertions.*;

public class MyTest {
    @ParameterizedTest
    @ValueSource(strings = {
            "username@gmail.com",
            "Username@gmail.com",
            "user.name@gmail.com",
            "user.name.login@gmail.com",
            "user1.name@gmail.com",
            "user.name@Gmail.com",
            "user.name@edu.dp.ua",
            "user.name@nmu.one",
            "user.name@name.edu.dp.ua",
            "user.name@nmu.org"
    })
    void checkOkEmail(String email) {
        assertTrue(MyUtils.checkEmail(email));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "",
            "username#gmail.com",
            "username.gmail.com",
            "1username@gmail.com",
            ".user.name@gmail.com",
            "user_name@gmail.com",
            "user name@gmail.com",
            "user-name@gmail.com",
            "user$name@gmail.com",
            "user.name@gmail-com",
            "user.name@gmail com",
            "username@gmail,com"
    })
    void checkWrongEmail(String email) {
        assertFalse(MyUtils.checkEmail(email));
    }
}
