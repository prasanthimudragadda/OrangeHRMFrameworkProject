package listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import driver.DriverFactory;
import java.io.File;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
    ExtentReports extent;
    ThreadLocal <ExtentTest> test = new ThreadLocal<>();
    public TestListener(){
        ExtentSparkReporter spark = new ExtentSparkReporter("reports/ExtentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
    }

    public void onTestStart(ITestResult result){
        test.set(extent.createTest(result.getTestContext().getName()+ " - " + result.getName()));
    }
    public void onTestSuccess(ITestResult result){
        test.get().pass("test passed");
    }
    public void onTestFailure(ITestResult result) {
        test.get().fail(result.getThrowable());
        String screenshotPath = takeScreenshot(result.getTestContext().getName() + "-" + result.getName());
        test.get().addScreenCaptureFromPath(screenshotPath);
    }
    public void onTestSkipped(ITestResult result){
        test.get().skip("test skipped");
    }

    public void onFinish(ITestContext context){
        extent.flush();
    }

    public String takeScreenshot(String testName) {
        try {
            File screenshot = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.FILE);
            new File("reports/screenshots/").mkdirs();
            FileHandler.copy(screenshot, new File("reports/screenshots/" + testName + ".png"));
        } catch (Exception e) {
            System.out.println("Screenshot failed: " + e.getMessage());
        }
        return "screenshots/" + testName + ".png";
    }
}
