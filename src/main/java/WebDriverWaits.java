import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
public class WebDriverWaits {

    private static final String BASE_URL = "https://devexpress.github.io/devextreme-reactive/react/grid/docs/guides/filtering/";
    private static final Long IMPLICITLY_WAIT_SECONDS = 10L;
    private static final Long ONE_SECOND_DELAY = 1000L;
    private static final int ONE_SECOND_WAIT = 1;
    private WebDriver driver;

    private void presentationSleep(int seconds) throws InterruptedException {
        Thread.sleep(seconds * ONE_SECOND_DELAY);
    }

    @BeforeSuite
    public void beforeSuite() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeClass
    public void beforeClass() throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICITLY_WAIT_SECONDS));
        driver.manage().window().maximize();
        presentationSleep(2);
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() throws InterruptedException {
        if (driver != null) {
            driver.quit();
        }
    }

    @BeforeMethod
    public void beforeMethod()throws InterruptedException {
        driver.get(BASE_URL);
        closePopupImplicitWay();
    }

    @AfterMethod
    public void afterMethod() {
    }
    private void closePopupImplicitWay()throws InterruptedException {
        presentationSleep(2);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        long timeStart = System.currentTimeMillis();
        List<WebElement> footerButtonsList = driver
                .findElements(By.xpath("//footer[contains(@class,'cookie')]//button"));
        if (!footerButtonsList.isEmpty()) {
            footerButtonsList.get(0).click();
            presentationSleep(2);
        }

    }
    @Test
    public void WaitTesting()throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        WebDriverWait wdw = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement we = wdw.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//iframe")
        ));
        driver.switchTo().frame(we);
        we = driver.findElement(By.xpath("(//input)[3]"));
        we.sendKeys("L", Keys.ENTER);
        presentationSleep(2);
        List<String> cities = driver.
                findElements(By.xpath("//table//td[3]"))
                .stream()
                .map(WebElement::getText)
                .distinct()
                .collect(Collectors.toList());
        Assert.assertTrue(cities.contains("Las Vegas"));
        Assert.assertTrue(cities.contains("London"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

    }



}
