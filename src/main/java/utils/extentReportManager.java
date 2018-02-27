package utils;

import com.relevantcodes.extentreports.ExtentReports;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class extentReportManager extends Utils {


    static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
    static Date date = new Date();
    public static String currentDate = dateFormat.format(date);
    public static String reportDir = "";


    public static ExtentReports Instance(String timeStamp) throws ParserConfigurationException, IOException
    {

        ExtentReports extent;
        // This indicated where the HTML report is created
        extent = new ExtentReports("/Users/jonathan.chen/Desktop/Automation/Reports/extent.html" , true);
        return extent;
    }
}
