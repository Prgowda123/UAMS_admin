package utiles;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;

import base_class.Base_class;

public class ListerImplementation extends Base_class implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        captureScreenshot(result.getName(), "Failure");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        captureScreenshot(result.getName(), "Skipped");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        captureScreenshot(result.getName(), "Success");
    }

    private void captureScreenshot(String testName, String status) {
        try {
            // Generate timestamp
            String timestamp = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss").format(new Date());

            // Take screenshot
            TakesScreenshot tss = (TakesScreenshot) driver;
            File src = tss.getScreenshotAs(OutputType.FILE);

            // Create destination file with timestamp
            File dest = new File("./Screenshot/" + testName + "_" + status + "_" + timestamp + ".png");

            // Copy screenshot to destination
            FileUtils.copyFile(src, dest);

            System.out.println("Screenshot saved: " + dest.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Screenshot capture failed!");
        } catch (NullPointerException e) {
            System.out.println("WebDriver is not initialized!");
        }
    }
}
