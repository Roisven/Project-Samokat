package praktikum;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class SetupBrowser {
    public WebDriver driver;
    @Before
    public void before() {
        setupBrowser("Firefox");
    }
    public WebDriver setupBrowser (String browser) {
        if (browser.equals("Firefox")) {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }
        if (browser.equals("Chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
        return driver;
    }
}
// }
//когда Хром - найдена ошибка, указанная в задании, в FireFox все тесты проходят корректно!
