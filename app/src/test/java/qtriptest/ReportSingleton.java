package qtriptest;

import java.io.File;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;



public class ReportSingleton {

    private static ReportSingleton instance;
    public ExtentReports reports;
    public ExtentTest test;

    private ReportSingleton() {
        reports = new ExtentReports(System.getProperty("user.dir") + "/ExtentReportResults.html");
        test = reports.startTest("Regression Testing");
        reports.loadConfig(new File(System.getProperty("user.dir")+"extent_customization_configs.xml"));
    }

    public static ReportSingleton getExtentReportInstance() {
        if (instance == null) {
            synchronized (ReportSingleton.class) {
                if (instance == null) {
                    instance = new ReportSingleton();
                }
            }
        }
        return instance;
    }
}