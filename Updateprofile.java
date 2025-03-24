package Shellapk.loginpage;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class Updateprofile {

    @Test
    public void AppiumTest() throws MalformedURLException, URISyntaxException, InterruptedException {
        // BrowserStack Credentials
        String USERNAME = "oshinanwar1";
        String ACCESS_KEY = "1eZ1NFQw9riLqAESX8ze";

        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("Google Pixel 7");
        options.setPlatformName("Android");
        options.setPlatformVersion("13.0");
        options.setApp("bs://d01532849d57b0df0406be7b22dc1380048c0bd0");
        options.setAutoGrantPermissions(true);

        // Initialize AndroidDriver with URI
        AndroidDriver driver = new AndroidDriver(
            new URI("http://" + USERNAME + ":" + ACCESS_KEY + "@hub-cloud.browserstack.com/wd/hub").toURL(), options
        );

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        try {
            // Select Country
            wait.until(ExpectedConditions.elementToBeClickable(By.id("com.shell.goplus.dev:id/tvCountryName"))).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//android.view.ViewGroup[@resource-id='com.shell.goplus.dev:id/country_cell'])[5]"))).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.id("com.shell.goplus.dev:id/btnLetsGo"))).click();

            // Enter phone number
            wait.until(ExpectedConditions.elementToBeClickable(By.id("com.shell.goplus.dev:id/edPhoneNumberLayout"))).click();
            WebElement phoneInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.shell.goplus.dev:id/edPhoneNumber")));
            phoneInput.sendKeys("+65 94211722");
            driver.hideKeyboard();
            wait.until(ExpectedConditions.elementToBeClickable(By.id("com.shell.goplus.dev:id/generate_otp_btn"))).click();

            // Enter OTP
            WebElement otpInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.shell.goplus.dev:id/customOtpView")));
            otpInput.sendKeys("999999");
            wait.until(ExpectedConditions.elementToBeClickable(By.id("com.shell.goplus.dev:id/btnNextOTP"))).click();

            Thread.sleep(3000);

            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout")));
            System.out.println("Successfully landed on the target page!");

            // Click on profile icon
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.ProgressBar[@resource-id='com.shell.goplus.dev:id/progressBar']"))).click();

            // Click first name field
            WebElement firstNameField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.EditText[@resource-id='com.shell.goplus.dev:id/edit_text' and @text='Vijay']")));
            firstNameField.click();

            // Edit first name to "Kumar"
            WebElement editFirstNameField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.EditText[@resource-id='com.shell.goplus.dev:id/edFirstName']")));
            editFirstNameField.clear();
            editFirstNameField.sendKeys("Kumar");

            // Click update button
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.Button[@resource-id='com.shell.goplus.dev:id/update_btn']"))).click();

            System.out.println("First name updated successfully!");

        } catch (Exception e) {
            System.err.println("Error encountered: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }
}
