package praktikum;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


@RunWith(Parameterized.class)

public class OrderTests extends SetupBrowser {

    private WebDriver driver;

    @Before
    public void before() {
        this.driver = setupBrowser("Firefox");
    }


    private final String name;
    private final String lastName;
    private final String adress;
    private final String metroStation;
    private final String phoneNumber;
    private final String date;
    private final String howLong;
    private final String color;

    public OrderTests(String name, String lastName, String adress, String metroStation,
                      String phoneNumber, String date, String howLong, String color) {
        this.name = name;
        this.lastName = lastName;
        this.adress = adress;
        this.metroStation = metroStation;
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.howLong = howLong;
        this.color = color;
    }

    @Parameterized.Parameters
    public static Object[][] myOrderTests() {
        return new Object[][]{

                {"Артас", "Менетил", "г. Штормград, ул. Орка, д. 1", "Черкизовская",
                        "+88005553535", "11.08.2023", "сутки", "черный жемчуг"},
                {"Рагнар", "Рыжий", "г. Хельхейм ул. Чушкина д. 123", "Павелецкая",
                        "+79260234597", "17.08.2023", "трое суток", "серая безысходность"},
        };
    }

    @Test
    public void checkMadeOrderFirstButton(){
        OrderPage orderPage = new OrderPage(driver);
        HomePage homePage = new HomePage(driver);
        homePage.open();
        homePage.clickVisibleFirstOrderButton();
        orderPage.makeOrder(name, lastName, adress, metroStation, phoneNumber, date, howLong, color);

        Assert.assertTrue("Сообщение о успешном заказе не появляется", orderPage.getActualText().contains("Заказ оформлен"));
    }

    @Test
    public void checkMadeOrderSecondButton(){
        OrderPage orderPage = new OrderPage(driver);
        HomePage homePage = new HomePage(driver);
        homePage.open();
        homePage.scrollAndClickSecondOrderButton();
        orderPage.makeOrder(name, lastName, adress, metroStation, phoneNumber, date, howLong, color);
        Assert.assertTrue("Сообщение о успешном заказе не появляется", orderPage.getActualText().contains("Заказ оформлен"));
    }

    @After
    public void after() {
        driver.quit();
    }

}
