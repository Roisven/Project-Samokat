package praktikum;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

import java.time.Duration;


public class HomePage {

    //переход на страницу
    private static final String PAGE_URL = "https://qa-scooter.praktikum-services.ru/";

    //верхняя кнопка "Заказать"
    private static final By FIRST_ORDER_BUTTON =
            By.xpath(".//button[@class='Button_Button__ra12g']");

    //нижняя кнопка "заказать"
    private static final By SECOND_ORDER_BUTTON =
            By.xpath(".//div[@class='Home_FinishButton__1_cWm']/button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    private final WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get(PAGE_URL);
    }

    public void scroll(By element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(element));
    }

    public void clickButton(By button) {
        driver.findElement(button).click();
    }

    public void isVisible(By element) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(element));
        driver.findElement(element).isDisplayed();
    }

    public void clickVisibleFirstOrderButton () {
        isVisible(FIRST_ORDER_BUTTON);
        clickButton(FIRST_ORDER_BUTTON);
    }

    //шаг доскроллить до нижней кнопки "Заказать", проверить видимость кнопки и нажать на нее
    public void scrollAndClickSecondOrderButton(){
        scroll(SECOND_ORDER_BUTTON);
        isVisible(SECOND_ORDER_BUTTON);
        clickButton(SECOND_ORDER_BUTTON);
    }

    private By questionButton;
    public void visibleQuestionButton(int number){
        open();
        questionButton = By.id("accordion__heading-" + number);
        scroll(questionButton);
        isVisible(questionButton);
    }


    public String getTextOfQuestion(int number){
        visibleQuestionButton (number);
        return driver.findElement(questionButton).getText();
    }

    public void clickQuestionsAboutImportant(int number) {
        visibleQuestionButton (number);
        driver.findElement(questionButton).click();
    }

    //Метод взятия текста из ответа на вопросы о важном
    public String getTextFromAnswer(int number) {
        By textFromAnswer = By.xpath(".//div[@id='accordion__panel-" + number + "']/p");
        scroll(textFromAnswer);
        return driver.findElement(textFromAnswer).getText();
    }

}