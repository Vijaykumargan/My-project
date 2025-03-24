package Shellapk.loginpage;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class Register {

    // Function to generate random mobile number
    public static String generateMobileNumber() {
        Random rand = new Random();
        int number = 80000000 + rand.nextInt(10000000);
        return "+65 " + number;
    }

    @Test
    public void AppiumTest() throws MalformedURLException, URISyntaxException, InterruptedException {
        // BrowserStack Credentials
        String USERNAME = "oshinanwar1";
        String ACCESS_KEY = "1eZ1NFQw9riLqAESX8ze";

        // Set capabilities for BrowserStack
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("Google Pixel 7");
        options.setPlatformName("Android");
        options.setPlatformVersion("13.0");
        options.setApp("bs://d01532849d57b0df0406be7b22dc1380048c0bd0");
        options.setAutoGrantPermissions(true);

        // Initialize AndroidDriver with BrowserStack URL
        AndroidDriver driver = new AndroidDriver(
                new URI("http://" + USERNAME + ":" + ACCESS_KEY + "@hub-cloud.browserstack.com/wd/hub").toURL(), options
        );
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // Select Country
            driver.findElement(By.id("com.shell.goplus.dev:id/tvCountryName")).click();
            driver.findElement(By.xpath("(//android.view.ViewGroup[@resource-id='com.shell.goplus.dev:id/country_cell'])[5]"))
                    .click();
            driver.findElement(By.id("com.shell.goplus.dev:id/register_btn")).click();

            // Skip Intro Screens
            wait.until(ExpectedConditions.elementToBeClickable(
                    By.id("com.shell.goplus.dev:id/bottomView"))).click();

            // Enter Phone Number and Generate OTP
            String randomNumber = generateMobileNumber();
            driver.findElement(By.id("com.shell.goplus.dev:id/edPhoneNumber")).sendKeys(randomNumber);
            System.out.println("Generated Mobile Number: " + randomNumber);
            wait.until(ExpectedConditions.elementToBeClickable(
                    By.id("com.shell.goplus.dev:id/generate_otp_btn"))).click();

            // Enter OTP
            driver.findElement(By.id("com.shell.goplus.dev:id/customOtpView")).sendKeys("999999");
            driver.findElement(By.id("com.shell.goplus.dev:id/btnNextOTP")).click();

            // Enter User Details
            driver.findElement(By.xpath("//android.widget.EditText[@text='First name*']")).sendKeys("Vijay");
            driver.findElement(By.xpath("//android.widget.EditText[@text='Last name*']")).sendKeys("Kumar");
            driver.findElement(By.xpath("(//android.widget.EditText[@resource-id='com.shell.goplus.dev:id/edit_text'])[3]"))
                    .sendKeys("11/11/1997");
            driver.findElement(By.id("com.shell.goplus.dev:id/next_btn")).click();

            // Select Notification Preferences
            driver.findElement(By.xpath("//android.widget.CheckBox[@text='SMS']")).click();
            driver.findElement(By.xpath("//android.widget.CheckBox[@text='Email']")).click();
            driver.findElement(By.xpath("//android.widget.CheckBox[@text='Push notification']")).click();
            driver.findElement(By.id("com.shell.goplus.dev:id/next_btn")).click();

            // Skip Introduction
            wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//android.widget.TextView[@resource-id='com.shell.goplus.dev:id/tvSkip']"))).click();

            // Open the dropdown menu
            driver.findElement(By.id("com.shell.goplus.dev:id/text_input_end_icon")).click();

            // Use sendKeys to type the value
            WebElement inputField = driver.findElement(By.id("com.shell.goplus.dev:id/ddVehicle")); // Replace with correct ID
            inputField.sendKeys("Car / Commercial vehicle");

            // Wait and confirm
            Thread.sleep(2000);

            // Click the confirm button
            driver.findElement(By.id("com.shell.goplus.dev:id/btnNextVehicle")).click();

            // Navigate to the next page
            wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout")));

            // Click "Skip" on the new page
            WebElement skipButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//android.widget.TextView[@resource-id='com.shell.goplus.dev:id/tvSkip']")));
            skipButton.click();

            // Final navigation to the new page
            wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout")));
            System.out.println("Final Page Reached");

        } finally {
            // Quit driver
            driver.quit();
        }
    }
}
