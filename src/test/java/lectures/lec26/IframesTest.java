package lectures.lec26;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class IframesTest {

	WebDriver webDriver = new ChromeDriver();

	public void goToIframe() {
		webDriver.findElement(By.id("iframe1")).click();
	}

	@Test
	public void testElementInFrame() {
		webDriver.get("https://www.selenium.dev/selenium/web/iframes.html");
		goToIframe();

		try {
			webDriver.findElement(By.id("email"));
			Assert.fail("here you are");
		}
		catch (NoSuchElementException e) {
			System.out.println("No element and that's correct");
		}

		WebElement frames = webDriver.findElement(By.id("iframe1"));
		webDriver.switchTo().frame(frames);
		Assert.assertEquals(webDriver.findElement(By.id("email")).getTagName(), "input");

		// webDriver.switchTo().parentFrame(); //одно вложение вверх
		webDriver.switchTo().defaultContent(); // на начальную страницу
		try {
			webDriver.findElement(By.id("email"));
			Assert.fail("here you are");
		}
		catch (NoSuchElementException e) {
			System.out.println("No element and that's correct");
		}
	}

	@Test
	public void testSwitchBetweenIFrames() {
		webDriver.get("https://www.selenium.dev/selenium/web/slow_loading_iframes.html");

		webDriver.switchTo().frame("noSrc");
		webDriver.switchTo().defaultContent();
		webDriver.switchTo().frame(0);

		Assert.assertEquals(webDriver.findElement(By.id("announcement-banner")).getTagName(), "section");
	}

	@AfterClass
	public void afterClass() {
		webDriver.quit();
	}

}