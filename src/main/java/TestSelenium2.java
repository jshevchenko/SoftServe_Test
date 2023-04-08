import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;


public class TestSelenium2 {

    private static final String BASE_URL = "http://taqc-opencart.epizy.com/";
    private static final Long IMPLICITLY_WAIT_SECONDS = 10L;
    private static final Long ONE_SECOND_DELAY = 1000L;
    private static final int FIVE_SECONDS_WAIT = 5;

    private  WebDriver driver = new ChromeDriver();

    private void presentationSleep(int seconds) {
        try {
            Thread.sleep(seconds * ONE_SECOND_DELAY);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void takeScreenShot() {
        String currentTime = LocalDateTime.now().toString();
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File("./screenshots/" + currentTime + "_screenshot.png"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        }
    }

    @Test
    public void checkDraftSelenium()  {
        // given
        WebDriverManager.chromedriver().setup();
        //    ChromeOptions options = new ChromeOptions();
        //   options.addArguments("--remote-allow-origins=*");

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICITLY_WAIT_SECONDS));
        driver.manage().window().maximize();
        presentationSleep(FIVE_SECONDS_WAIT);
        // when
        driver.get(BASE_URL);
        presentationSleep(FIVE_SECONDS_WAIT);

        driver.findElement(By.cssSelector("a[title='My Account']")).click();

        presentationSleep(FIVE_SECONDS_WAIT);

        driver.findElement(By.linkText("Register")).click();
        presentationSleep(FIVE_SECONDS_WAIT);

        driver.findElement(By.name("firstname")).sendKeys("John", Keys.TAB, "Lennon");
        presentationSleep(FIVE_SECONDS_WAIT);
        takeScreenShot();
        presentationSleep(FIVE_SECONDS_WAIT);
        driver.quit();
    }
}
