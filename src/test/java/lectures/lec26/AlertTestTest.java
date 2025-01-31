package lectures.lec26;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.util.function.Consumer;

import static org.testng.AssertJUnit.fail;

public class AlertTestTest {

    WebDriver webDriver = new ChromeDriver();

    public void goToAlert() {
        webDriver.get("https://www.selenium.dev/selenium/web/alerts.html");
    }

    public void doSomethingWithAlert(Consumer<Alert> whatToClick, Runnable assertion) {
        goToAlert();
        webDriver.findElement(By.id("confirm")).click();
        Alert alert = webDriver.switchTo().alert();
        whatToClick.accept(alert);
        assertion.run();
    }

    @Test
    void testDismissAlertFun() {
        doSomethingWithAlert((Alert alert) -> alert.dismiss(), () -> {
            String text = webDriver.findElement(By.tagName("h1")).getText();
            Assert.assertEquals("Testing Alerts and Stuff", text);
        });
    }

    @Test
    void testAcceptAlertFun() {
        doSomethingWithAlert((Alert alert) -> alert.accept(), () -> {
            String text = webDriver.findElement(By.tagName("h1")).getText();
            Assert.assertEquals("Heading", text);
        });
    }

    @Test
    public void testTryCatchTest() throws Exception {
        goToAlert();

        webDriver.findElement(By.id("alert")).click();
        webDriver.switchTo().alert().accept();
        try {
            webDriver.switchTo().alert();
            fail("Ошибка, алерт не закрылся");
        } catch (NoAlertPresentException e) {
            // pass
        }
    }

    @AfterClass
    public void afterClass() {
        webDriver.quit();
    }

}