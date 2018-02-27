package pageObjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoansInfoTab {


    public static SelenideElement loanInfoTab() {

        return $(By.xpath("//*[@id=\"loan-tabs\"]/ul/li[1]"));

    }

    public static SelenideElement requestedAmount() {

        return $(By.xpath("//*[@id=\"loan-tabs-pane-loanInfo\"]/div/div[1]/div[1]/div[2]/div[3]"));
    }

    public static SelenideElement loanInfo(){

        return $("#loan-tabs-pane-loanInfo");
    }

    public static SelenideElement LoanDetails() {

        return $("#loan-tabs-pane-loanInfo div.loan-title");

    }

    public static SelenideElement approvedAmount() {

        return $(By.xpath("//div/div[1]/div[1]/div[2]/div[4]"));
    }

    public static SelenideElement loanAmount() {

        return $(By.xpath("//div[2]/div[5]/div[2]/div"));
    }

    public static SelenideElement loanAmountInput() {

        return $(By.xpath("//form/div/input"));
    }

    public static SelenideElement repaymentAmount() {

        return $(By.xpath("//div[1]/div[1]/div[2]/div[6]"));

    }
    
    public static SelenideElement totalRepaymentAmount() {
    
        return $(By.xpath("//div[1]/div[1]/div[2]/div[7]"));
        
    }

    public static SelenideElement purposeField() {

        return $(By.xpath("//div[1]/div[1]/div[2]/div[8]"));

    }

    public static SelenideElement loanTypeText(){

        return $(By.xpath("//div[2]/div/div/div[1]/p[1]"));
    }

    public static SelenideElement statusTypeText() {

        return $(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div/div[1]/div[2]"));

    }

    public static SelenideElement businessNameText() {

        return $(By.xpath("//div[2]/div/div/div[1]/p[3]"));

    }

    public static SelenideElement abnText() {

        return $(By.xpath("//div[2]/div/div/div[1]/p[4]"));

    }

    public static SelenideElement businessTypeText() {

        return $(By.xpath("//div[2]/div/div/div[1]/p[5]"));

    }

    public static SelenideElement getLoanID() {

        return $(By.xpath("//div[1]/ol/li[3]/span"));
    }

    public static SelenideElement errorBox() {

        return $("div[style*='font-size: 16px; color']");

    }

    public static SelenideElement loanErrorOkButton() {

        return $("div span[style*='position: relative; padding']");

    }

    public static SelenideElement rateAmount() {

        return $(By.xpath("//div[13]/div[2]/div"));

    }

    public static SelenideElement rateAmountInput() {

        return $("input[id*='interest_rate']");

    }

    public static SelenideElement uploadPNLreport() {

        return $("label[id='PNL_REPORT_upload'] div div input");

    }
    
    public static SelenideElement uploadBalance() {
    
        return $("label[id='BALANCE_SHEET_REPORT_upload'] div div input");
        
    }

    public static SelenideElement uploadStatement() {

        return $("label[id='BANK_STATEMENTS_upload'] div div input");

    }

    public  static SelenideElement PNL_ViewButton() {

        return $("button[id='PNL_REPORT_view']");

    }

    public static SelenideElement PNL_deleteButton() {

        return $("button[id='PNL_REPORT_delete']");

    }

    public static SelenideElement balanceViewButton() {

        return $("button[id='BALANCE_SHEET_REPORT_view']");

    }

    public static SelenideElement balanceDeleteButton() {

        return $("button[id='BALANCE_SHEET_REPORT_delete']");

    }

    public static SelenideElement statementsViewButton() {

        return $("button[id='BANK_STATEMENTS_view']");

    }

    public static SelenideElement statementsDeleteButton() {

        return $("button[id='BANK_STATEMENTS_delete']");

    }


    public static SelenideElement preApprovedAmount() {

        return $(By.name("loan.amount"));

    }


    public static SelenideElement checkStatus(String status) throws InterruptedException {

        int count = 0;

        while (!statusTypeText().getText().contains(status)) {

            Selenide.refresh();
            Thread.sleep(7000);
            count++;

        if (count >10) {

            break;
        }
        }

        return statusTypeText();
    }

    public void loanInfoVerify () {

        open("http://staging.sail.com.au/admin/loans/59e8b3143206e90034e53387");
        $(By.name("username")).sendKeys("Yoni+1@sail.com.au");
        $(By.name("password")).sendKeys("yoni1234");
        $(By.cssSelector("button[type=submit")).shouldHave(text("SIGN IN")).click();
        LoansInfoTab.loanInfo().waitUntil(Condition.visible, 5000, 400);
        LoansInfoTab.loanTypeText().shouldHave(Condition.text("REGULAR"));
        LoansInfoTab.statusTypeText().shouldHave(Condition.text("PARTIAL_APPLICATION"));
        LoansInfoTab.businessNameText().shouldHave(Condition.text("SAIL FUNDING PTY LIMITED"));
        LoansInfoTab.abnText().shouldHave(Condition.text("610803604"));
        LoansInfoTab.businessTypeText().shouldHave(Condition.text("SOLE_TRAD    ER"));

        LoansInfoTab.requestedAmount().shouldHave(Condition.text("10,000"));
        LoansInfoTab.approvedAmount().shouldHave(Condition.text("0.00"));
        LoansInfoTab.loanAmount().shouldBe(Condition.enabled);
        LoansInfoTab.loanAmount().click();
        Selenide.actions().doubleClick(LoansInfoTab.loanAmountInput()).perform();
        LoansInfoTab.loanAmountInput().sendKeys("10000");
        LoansInfoTab.loanAmountInput().sendKeys(Keys.ENTER);
        LoansInfoTab.loanInfo().waitUntil(Condition.visible, 5000, 400);


    }
}
