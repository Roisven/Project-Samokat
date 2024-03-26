package praktikum;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SetupBrowser {
    @Before
    public void before() {
        setupBrowser("Firefox");
    }
    public WebDriver driver;

    public void setupBrowser (String browser) {
        if (browser.equals("Firefox")) {
            System.setProperty("webdriver.gecko.driver", "/Users/svetlana/Documents/WebDriver/bin/geckodriver");
            driver = new FirefoxDriver();
        }
        if (browser.equals("Chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--remote-allow-origins=*");
            driver = new ChromeDriver(chromeOptions);
        }
        //когда Хром - найдена ошибка, указанная в задании, в FireFox все тесты проходят корректно!
    }
}
