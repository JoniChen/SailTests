package tests;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.relevantcodes.extentreports.LogStatus;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;
import pageObjects.*;
import utils.Utils;
import utils.commonOps;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class  Test1 extends Utils {



    File attachment = new File("/Users/jonathan.chen/Downloads/privacy_policy.pdf");
    File attachment2 = new File("/Users/jonathan.chen/Desktop/JPGs/balance.png");
    File attachment3 = new File("/Users/jonathan.chen/Desktop/JPGs/BankStatementChequing.png");
    private static commonOps comOps = new commonOps();



    @Test(priority = 0 , groups = {"report"})
    public void criticalPath_01() throws Exception {

            initTest("Critical Path 01" , "Regular loan approval and disburse");
        // Log in as Admin
            comOps.LoginAdmin();
            // go to contacts
            Thread.sleep(1000);
            $(By.linkText("Contacts")).shouldHave(text("Contacts")).click();
            //Create new user from ADD button
            Contacts.createNewContact();
            //Create new loan after contact create
            Loans.createNewLoan();
            // Approve new loan
            Loans.approveNewLoan();
            //Verify disburse for new loan
            Loans.disburseNewLoan("InsideLoan");


    }


    @Test (priority = 1)
    public void criticalPath_02() throws Exception , ParserConfigurationException, SAXException, IOException {

            initTest("Critical Path 02" , "Confirm Decline loan - veda < 300");
            //Login As admin
            comOps.LoginAdmin();
            //Create a new loan for existing contact with veda<300
            Loans.createNewLoanFromContactEdit();
            // confirm CLOSED_REJECTED status
            Loans.confirmDeclinedLoan();


    }


    @Test (priority = 2)
    public void criticalPath_03() throws Exception {


            comOps.LoginAdmin();
            Loans.createNewLoanFromContactEdit();
            //Wait for company name
            $(By.xpath("//div[contains(.,'FLYING SOLO PROPERTIES')]")).waitUntil(Condition.visible, 5000, 100);
            Loans.submitApplication();
            //Approve loan as admin
            Loans.approveNewLoan();

            //Log in as sales and open current loan
            Selenide.clearBrowserLocalStorage();
            comOps.LoginSales();
            Thread.sleep(3000);
            Selenide.open("http://staging.sail.com.au/admin/loans/" + Loans.currentLoan);



            //Verify Sales permissions - loan amount too high
            LoansInfoTab.loanInfo().waitUntil(Condition.visible, 12000, 5000);
            LoansInfoTab.loanAmount().click();
            LoansInfoTab.loanAmountInput().doubleClick();
            LoansInfoTab.loanAmountInput().sendKeys("15000");
            LoansInfoTab.loanAmountInput().sendKeys(Keys.ENTER);
            LoansInfoTab.errorBox().waitUntil(visible, 7000, 5000);
            LoansInfoTab.errorBox()
                    .shouldHave(text("You are not allowed to increase the amount after approval. " +
                            "Either resubmit for approval or contact a Sales Admin for help"));
            LoansInfoTab.loanErrorOkButton().click();
            //Decrease loan amount
            LoansInfoTab.loanAmount().click();
            LoansInfoTab.loanAmountInput().doubleClick().sendKeys("8000" , Keys.ENTER);
            LoansInfoTab.loanInfo().waitUntil(Condition.visible, 12000, 5000);
            //Verify loan amount modified
            $(By.xpath("//div[2]/div[5]/div[2]/div/a")).shouldHave(text("$8,000.00edit"));

            //Increase rate amount
            LoansInfoTab.rateAmount().click();
            LoansInfoTab.rateAmountInput().doubleClick().sendKeys("1.00", Keys.ENTER);
            LoansInfoTab.loanInfoTab().waitUntil(Condition.visible, 12000, 5000);
            LoansInfoTab.rateAmount().shouldHave(text("1.00"));

            //Rate increase is too high
            LoansInfoTab.rateAmount().click();
            LoansInfoTab.rateAmountInput()
                    .doubleClick()
                    .sendKeys("3", Keys.ENTER);
            LoansInfoTab.errorBox().waitUntil(visible, 7000, 5000);
            LoansInfoTab.errorBox()
                    .shouldHave(text("You are not allowed to update the interest rate " +
                            "outside therange 0.8-1.5%"));
            LoansInfoTab.loanErrorOkButton().click();


    }


    @Test (priority = 3)
    public void criticalPath_04() {


        try {

            //Log in as SalesAdmin
            comOps.LoginSalesAdmin();
            Thread.sleep(2000);
            Selenide.open("http://staging.sail.com.au/admin/loans/" + Loans.currentLoan);
            //Increase loan amount more than 5%
            LoansInfoTab.loanAmount().waitUntil(visible,10000).click();
            LoansInfoTab.loanAmountInput().doubleClick().sendKeys("10600", Keys.ENTER);
            LoansInfoTab.errorBox().waitUntil(visible,10000);
            LoansInfoTab.errorBox()
                    .shouldHave(text("You are not allowed to update the amount more than " +
                            "5% of the loan amount after approval. Please resubmit for approval"));
            LoansInfoTab.loanErrorOkButton().click();

            //Increase amount in 5%
            LoansInfoTab.loanAmount().click();
            LoansInfoTab.loanAmountInput().doubleClick().sendKeys("10500",Keys.ENTER);
            LoansInfoTab.loanAmount().waitUntil(Condition.visible, 10000);
            //Verify modified amount
            $(By.xpath("//div[2]/div[5]/div[2]/div/a")).shouldHave(text("$10,500.00edit"));
            //Verify upload documents

            LoansInfoTab.uploadPNLreport().uploadFile(attachment);
            LoansInfoTab.uploadBalance().uploadFile(attachment2);
            LoansInfoTab.uploadStatement().uploadFile(attachment3);
            LoansInfoTab.PNL_ViewButton().shouldBe(visible);
            LoansInfoTab.PNL_deleteButton().shouldBe(visible);
            LoansInfoTab.balanceViewButton().shouldBe(visible);
            LoansInfoTab.balanceDeleteButton().shouldBe(visible);
            LoansInfoTab.statementsViewButton().shouldBe(visible);
            LoansInfoTab.statementsDeleteButton().shouldBe(visible);


//            Verify remittance tab
            RemiitanceTab.remittanceTab().click();
            RemiitanceTab.disburseAmount().shouldHave(Condition.text("10237.5"));
            RemiitanceTab.totalAmount().shouldHave(Condition.text("10500"));
            RemiitanceTab.payeeType().shouldHave(Condition.text("Client"));
            RemiitanceTab.addNewRow().click();
            $("td[id*='payee_type_1'] div div div[style^='color: rgba']").click();
            RemiitanceTab.payeeTypeList("Client").click();




            //Verify disbursements not allowed for sales admin

            $(By.linkText("Admin")).shouldHave(text("Admin")).click();
            $(By.linkText("Disbursements")).shouldHave(text("Disbursements")).click();
            Alert myAlert = Selenide.switchTo().alert();
            myAlert.accept();
            comOps.errorBox().waitUntil(visible,7000);
            comOps.errorBoxOKbutton().click();



        }

        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //Affordability loan critical path
    @Test (priority = 4)
    public void criticalPath_05() throws Exception {


        try {
            comOps.LoginAdmin();
            Loans.createNewLoanFromContactEdit();
            $(By.xpath("//div[contains(.,'FLYING SOLO PROPERTIES')]")).waitUntil(Condition.visible, 5000);
            Loans.typePicker().click();
            Loans.typeList("Affordability").click();
            Loans.submitApplication();
            LoansInfoTab.loanInfo().waitUntil(visible,10000);
            LoansInfoTab.loanAmount().waitUntil(visible,11000);
            LoansInfoTab.loanAmount().shouldBe(text("$10,000.00edit"));
            Loans.requestApprovalButton().click();
            LoansInfoTab.loanInfo().waitUntil(Condition.visible, 12000, 4000);
            LoansInfoTab.preApprovedAmount().waitUntil(visible,15000)
                    .doubleClick().sendKeys("8000");
            //Pre approve loan
            Loans.approveButton().click();
            LoansInfoTab.loanInfo().waitUntil(visible,10000);
            LoansInfoTab.loanAmount().shouldBe(text("$8,000.00edit"));
            ApplicantTab.applicantTab().click();
            ApplicantTab.creditReportTab().click();
            ApplicantTab.scoreProgress().waitUntil(visible,5000).shouldHave(text("N/A"));
            LoansInfoTab.loanInfoTab().click();
            LoansInfoTab.loanInfo().waitUntil(visible,10000);
            //Run checks for loan
            Loans.runChecksButton().shouldHave(text("Run Checks")).doubleClick();
            LoansInfoTab.loanInfo().waitUntil(visible,8000);
            ApplicantTab.applicantTab().click();
            ApplicantTab.creditReportTab().click();
            ApplicantTab.scoreProgress().waitUntil(visible,5000).shouldHave(text("793"));



        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test (priority = 5)
    public void criticalPath_06() {


        try {
            //Verify disburse loans works from Admin section
            comOps.LoginAdmin();
            Loans.createNewLoanFromContactEdit();
            $(By.xpath("//div[contains(.,'FLYING SOLO PROPERTIES')]")).waitUntil(Condition.visible, 5000, 100);
            Loans.submitApplication();
            Loans.approveNewLoan();
            Loans.disburseNewLoan("fromAdmin");




        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Test (priority = 6)
    public void criticalPath_07() throws Exception {

        try {
            comOps.LoginAdmin();
            $(By.linkText("Contacts")).shouldHave(text("Contacts")).click();
            $$("table tr").shouldHave(CollectionCondition.size(21));
            $(By.linkText("Loans")).shouldHave(text("Loans")).click();
            $$("table tr").shouldHave(CollectionCondition.size(21));
            $(By.linkText("Companies")).click();
            $$("table tr").shouldHave(CollectionCondition.size(21));


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @BeforeClass(alwaysRun = true)
    public synchronized void setUp() throws Exception {
//        Configuration.browser = "marionette";
        Configuration.baseUrl = "http://staging.sail.com.au/admin/login";
        Configuration.browser = "chrome";
        ChromeDriverManager.getInstance().setup();
        initExtentReport();

    }

    @AfterMethod
        protected void afterMethod(ITestResult result) {
            if (result.getStatus() == ITestResult.FAILURE) {
                test.log(LogStatus.FAIL, "Error with test: "+ result.getMethod()
                        + test.addScreenCapture("//Desktop/Automation/Reports/"));
            } else if (result.getStatus() == ITestResult.SKIP) {
                test.log(LogStatus.SKIP, "Test skipped " + result.getThrowable());
            } else {
                test.log(LogStatus.PASS, "Test passed");
            }

            extent.endTest(test);
            extent.flush();
        }


    @AfterClass
    public static void finishTests()
    {
        finalizeExtentReport();
    }


}