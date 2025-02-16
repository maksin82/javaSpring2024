package javaAutomatioCourse.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.remote.service.DriverFinder;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.nio.file.Path;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class ScooterTest {
    private WebDriver driver;

    @BeforeEach
    public void initDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
//        FirefoxOptions options = new FirefoxOptions();
//        options.setBinary(getFirefoxLocation()).configureFromEnv();
//        driver = new FirefoxDriver(options);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

    }

    @AfterEach
    public void closeDriver() {
        driver.quit();
    }

    @Test
    void invalidOrderNumber() {
        String value = "123";
        driver.get("https://qa-scooter.praktikum-services.ru/");
        driver.findElement(By.className("Header_Link__1TAG7")).click();
//        driver.findElement(By.className("Input_Input__1_xIoUqiN_Z Header_Input__xIoUq")).sendKeys("");
//        _xIoUq - переменная
//        driver.findElement(By.xpath("//button[contains(@class, 'Header_Link_')]")).click();
        driver.findElement(By.xpath("//button[contains(@class, 'Header_Link_')]")).click();

        driver.findElement(By.cssSelector("input[class*='Input_Input_']")).sendKeys(value);
        driver.findElement(By.xpath("//input[contains(@class, 'Input_Input_')]/parent::div/following-sibling::button")).click();

        String data = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[value='" + value + "']"))).getAttribute("class");
        Assertions.assertTrue(data.contains("Filled"));
    }

    private Path getFirefoxLocation() {
        FirefoxOptions options = new FirefoxOptions();
        options.setBrowserVersion("stable");
        DriverFinder finder = new DriverFinder(GeckoDriverService.createDefaultService(), options);
        return Path.of(finder.getBrowserPath());
    }
}
