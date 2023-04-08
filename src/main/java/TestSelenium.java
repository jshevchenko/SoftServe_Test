import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;
import org.testng.Assert;
import java.time.Duration;

/*
Prepare a test method with the following scenario.
Go to https://demo.opencart.com/
Select currency Euro.
Click on the Desktops and Mac menu.
Please check whether the product "iMac" at the price of 111.55 euros is present on the page.
 */

public class TestSelenium {

    private static final String BASE_URL = "http://taqc-opencart.epizy.com/";
    private static final Long IMPLICITLY_WAIT_SECONDS = 10L;
    private static final Long ONE_SECOND_DELAY = 1000L;
    private static final int ONE_SECOND_WAIT = 1;

    private void presentationSleep(int seconds) {
        try {
            Thread.sleep(seconds * ONE_SECOND_DELAY);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void checkMacBook()  {
        // given
        WebDriverManager.chromedriver().setup();
        //    ChromeOptions options = new ChromeOptions();
        //   options.addArguments("--remote-allow-origins=*");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICITLY_WAIT_SECONDS));
        driver.manage().window().maximize();
        presentationSleep(ONE_SECOND_WAIT);
        // when
        driver.get(BASE_URL);
        presentationSleep(ONE_SECOND_WAIT);
        driver.findElement(By.cssSelector("button.btn.btn-link.dropdown-toggle")).click();
        presentationSleep(ONE_SECOND_WAIT);
        //
        driver.findElement(By.cssSelector("button[name='EUR']")).click();
        presentationSleep(ONE_SECOND_WAIT);
        driver.findElement(By.name("search")).click();
        driver.findElement(By.name("search")).clear();
        driver.findElement(By.name("search")).sendKeys("mac", Keys.ENTER);
        presentationSleep(ONE_SECOND_WAIT);
        //then
        WebElement price = driver.findElement(By.xpath("//a[text()='iMac']/../following-sibling::p[@class='price']"));
        Assert.assertFalse(price.getText().contains("111.55€"));
        Assert.assertTrue(price.getText().contains("95.72€"));

        // tears down
        driver.quit();
    }
}
