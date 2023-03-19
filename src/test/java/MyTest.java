import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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
    void checkValidEmail(String email) {
        assertTrue(MyUtils.checkEmail(email));
    }
    @ParameterizedTest
    @ValueSource(strings = {
            "",
            "username#gmail.com", // @ absent
            "username.gmail.com",  // @ absent
            "1username@gmail.com",  // begins from number
            ".user.name@gmail.com", // begins from dot
            "user_name@gmail.com",  // underscore in username
            "user-name@gmail.com",   // -  in username
            "user$name@gmail.com",   // $  in username
            "user name@gmail.com",    //  space   in username
            "user.name@gmail-com",  // - in domains instead of dot
            "user.name@gmail com",  // space domains instead of dot
            "username@gmail,com"    // coma in domains instead of dot
    })
    void checkWrongEmail(String email) {
        assertFalse(MyUtils.checkEmail(email));
    }



}
