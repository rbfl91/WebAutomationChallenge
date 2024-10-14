package WebAutomationChallenge.WebAutomationChallenge;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ReportManager {
    private static ExtentReports extent;
    private static ExtentTest test;

    public static void initReport() {
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter("test-output/ExtentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    public static ExtentTest startTest(String testName) {
        test = extent.createTest(testName);
        return test;
    }

    public static void endReport() {
        extent.flush();
    }
}