package utils;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Utils {


    public static String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(Calendar.getInstance().getTime());
    public static ExtentReports extent;
    public static ExtentTest test;




    public void initTest(String sTestName, String sTestDescription) throws ParserConfigurationException, SAXException, IOException

    {

        test = extent.startTest(sTestName, sTestDescription);

    }

    public static void initExtentReport() throws ParserConfigurationException, SAXException, IOException

    {
        extent = extentReportManager.Instance(timeStamp);
    }

    // This Function Finalize The Extent Test
//    public void finilizeExtentReportTest() throws ParserConfigurationException, SAXException, IOException
//    {
//        extent.endTest(test);
//    }

    // This Function Finalize The Extent Report
    public static void finalizeExtentReport()
    {
        extent.flush();
        extent.close();
    }
}
