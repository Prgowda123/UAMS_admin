package base_class;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class Base_class {
    public static WebDriver driver; // Static WebDriver

    public static WebDriver getDriver() {
        return driver;
    }

    @BeforeTest
    public void open() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://172.10.1.159:9052/Application/JobPosting");
        new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterTest
    public void close() {
        if (driver != null) {
            driver.quit();
            // Close the WebDriver session
        }
    }


}

















