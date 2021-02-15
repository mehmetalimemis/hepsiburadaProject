package hepsiburadaProject;

import static org.testng.Assert.assertEquals;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import AndroidElement;
import bsh.util.Util;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.offset.PointOption;
import junit.framework.Assert;

public class hepsiburadaApp {

	public AndroidDriver<MobileElement> driver;
	public WebDriverWait wait;

	@BeforeMethod
	public void setup() throws MalformedURLException {
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("deviceName", "Pixel_4_API_30");
		caps.setCapability("udid", "emulator-5554");
		caps.setCapability("platformName", "Android");
		caps.setCapability("platformVersion", "11");
		caps.setCapability("appPackage", "com.pozitron.hepsiburada");
		caps.setCapability("appActivity", "com.hepsiburada.ui.startup.SplashActivity");

		caps.setCapability("noReset", "false");
		driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
		wait = new WebDriverWait(driver, 10);
	}

	@Test
	public void loginTest() throws InterruptedException {
		loginAccount();
		changeUsername();
		productDetails();
	}

	public void loginAccount() {
		WebElement account = wait.until(ExpectedConditions.elementToBeClickable(By.id("account_icon")));
		account.click();

		WebElement userLogin = wait.until(ExpectedConditions.elementToBeClickable(By.id("llUserAccountLogin")));
		userLogin.click();

		WebElement loginEmail = wait.until(ExpectedConditions.elementToBeClickable(By.id("etLoginEmail")));
		loginEmail.sendKeys("mmtalii86@gmail.com");

		WebElement loginPassword = wait.until(ExpectedConditions.elementToBeClickable(By.id("etLoginPassword")));
		loginPassword.sendKeys("Girissifresi1");

		WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnLoginLogin")));
		loginButton.click();
	}

	public void changeUsername() throws InterruptedException {
		WebElement okButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("android:id/button1")));
		okButton.click();

		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.id("tvUserAccountUsername")));
		element.click();

		WebElement userName = wait.until(ExpectedConditions.elementToBeClickable(By.id("etUserFirstName")));
		userName.clear();
		userName.sendKeys("Mehmet Ali");

		WebElement updateButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnOkSend")));
		updateButton.click();

		WebElement okeyButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("android:id/button1")));
		okeyButton.click();
	}

	public void productDetails() throws InterruptedException {

		WebElement navigateButton = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//android.widget.FrameLayout[@content-desc=\"Keşfet\"]")));
		navigateButton.click();

		MobileElement superPrice = (MobileElement) driver.findElementByAndroidUIAutomator(
				"new UiScrollable(" + "new UiSelector().scrollable(true)).scrollIntoView("
						+ "new UiSelector().textContains(\"Süper fiyat, süper teklif\"));");
		assert (superPrice.isDisplayed());
		superPrice.click();
		Thread.sleep(2000L);

		WebElement itemName = wait.until(ExpectedConditions
				.elementToBeClickable(By.id("com.pozitron.hepsiburada:id/ll_product_item_info_container")));
		itemName.click();

		WebElement productImage = wait
				.until(ExpectedConditions.elementToBeClickable(By.id("com.pozitron.hepsiburada:id/productImage")));
		productImage.click();
		Thread.sleep(2000L);
		driver.pressKey(new KeyEvent().withKey(AndroidKey.BACK));

		WebElement addToBasket = wait.until(ExpectedConditions.elementToBeClickable(By.name("Sepete Ekle")));
		addToBasket.click();

		Thread.sleep(2000L);
		driver.pressKey(new KeyEvent().withKey(AndroidKey.BACK));

		WebElement basketButton = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//android.widget.FrameLayout[@content-desc=\"Sepet\"]")));
		basketButton.click();

		assertEquals("Sepetim", driver.findElement(By.id("com.pozitron.hepsiburada:id/cartWebView")));
	}

	@AfterMethod
	public void teardown() {
		driver.quit();
	}

}
