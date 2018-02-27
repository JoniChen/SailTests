package pageObjects;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import utils.Utils;
import utils.commonOps;

import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class Loans extends Utils {

    public static String currentLoan;


    public static SelenideElement loansTab() {

        return $(By.id("account-tabs-tab-loans"));

    }

    public static SelenideElement createNewLoanButton() {

        return $("a[href*='application']");

    }

    public static SelenideElement typePicker() {

        return $("div[id*='undefined-undefined-Type']");

    }

    public static void searchForCompany(String companyNumber) {

        $(By.name("application.company.company_number")).sendKeys(companyNumber);
        $(By.xpath("//div[2]/span/span/div/button")).click();
    }

    public static SelenideElement industryPicker() {

        return $(By.xpath("//div/div[2]/div/form/div[1]/div/div[4]/div/div"));
    }


    public static SelenideElement dateofBirthPicker() {

        return $(By.xpath("//div[2]/div/form/div[2]/div[2]/div[1]/div"));
    }

    public static SelenideElement yearPicker() {

//        return $(By.xpath("//body/div[3]/div/div/div[1]/div[1]"));
        return $("div[style='cursor: pointer;']");
    }

    public static SelenideElement dateOKbutton() {

        return $(By.xpath("//div/div[2]/div[2]/button[2]/div"));
    }

    public static SelenideElement idDocStatePicker() {

        return $(By.xpath("//div/div/div[2]/div/form/div[2]/div[3]/div[2]/div"));
    }

    public static SelenideElement idNumberInput() {

        return $(By.name("application.stakeholder.id_document_number"));
    }

    public static SelenideElement fullAdress() {

        return $(By.name("application.stakeholder.home_address.street"));
    }

    public static SelenideElement loanAmount() {

        return $(By.name("application.loan.amount"));
    }

    public static SelenideElement installmentsNumber() {

        return $(By.name("application.loan.repayment_installments"));
    }

    public static SelenideElement purposePicker() {

        return $("div[id*='undefined-undefined-Purpose']");
    }


    public static SelenideElement submitButton() {

        return $(By.xpath("//div/form/div[4]/div/div[2]/button"));
    }

    public static SelenideElement applicationSavedMessage() {

        return $("div[style*='font-size: 16px; color']");
    }

    public static SelenideElement applicationSavedOKbutton() {

        return $(By.xpath("//div[1]/div/div/div[2]/button"));
    }

    public static SelenideElement applicationSubmittedMessage() {

        return $("div[style*='font-size: 16px; color");

    }

    public static SelenideElement applicationSubmittedOkButton() {

        return $("div span[style*='position: relative; padding']");

    }

    public static SelenideElement requestApprovalButton() {

        return $(By.xpath("//div[3]/div/div/div[1]/button"));

    }

    public static SelenideElement disabledButtonsDiv() {

        return $("div[class*='disabled']");

    }

    public static SelenideElement approveButton() {

        return $("form div:nth-child(2)  div:nth-child(1)  button  div  div");
    }

    public static SelenideElement disburseButton() {

        return $(By.xpath("//div[3]/div/div/div[1]/button/div/div"));

    }

    public static SelenideElement disburseButtonAdmin() {

        return $("button div div span[style*='rgb(2']");

    }

    public static SelenideElement runChecksButton() {

        return $(By.xpath("//div[3]/div/form/div/div[1]/button/div/div"));

    }


    public static SelenideElement industryList(String sIndustry) {

        List<SelenideElement> listIndustry = $$("div div[style*='margin-left']");

        for (SelenideElement itemElement : listIndustry) {

            if (itemElement.getText().equals(sIndustry)) {
                return itemElement;
            }
        }

        return listIndustry.get(0);
    }

    public static SelenideElement docStateList(String sDocState) {

        List<SelenideElement> listDocState = $$("div[role='menu'] div div div div");

        for (SelenideElement itemElement : listDocState) {

            if (itemElement.getText().equals(sDocState)) {
                return itemElement;
            }
        }

        return listDocState.get(1);
    }

    public static SelenideElement purposeList(String sPurpose) {

        List<SelenideElement> listPurpose = $$("div span[role='menuitem']");

        for (SelenideElement itemElement : listPurpose) {

            if (itemElement.getText().equals(sPurpose)) {
                return itemElement;
            }
        }

        return listPurpose.get(0);
    }

    public static SelenideElement typeList(String sType) {

        List<SelenideElement> listType = $$("div span[role='menuitem']");

        for (SelenideElement itemElement : listType) {

            if (itemElement.getText().equals(sType)) {
                return itemElement;
            }
        }

        return listType.get(0);
    }

    public static SelenideElement yearList(String sYear) {

        List<SelenideElement> listYear = $$("span[style*='align-self']");

        for (SelenideElement itemElement : listYear) {

            if (itemElement.getText().equals(sYear)) {

                return itemElement;
            }
        }

        return listYear.get(0);
    }

    public static SelenideElement dayList(String sDay) {

        List<SelenideElement> dayList = $$(By.xpath("//div[2]/div[1]/div[3]/div/div/div"));

        for (SelenideElement itemElement : dayList) {

            if (itemElement.getText().contains(sDay)) {

                return itemElement;
            }
        }

        return dayList.get(0);
    }

//    public static SelenideElement monthPicker(String sMonth) {
//
//         List<SelenideElement> monthList = $$(By.xpath("//div[3]/div/div/div[2]/div[1]/div[1]/div"));
//
//         for (SelenideElement itemElement:monthList) {
//
//             if(itemElement.getText().contains(sMonth)) {
//
//                 return itemElement;
//             }
//
//             else {
//
//                 $(By.xpath("//div[2]/div[1]/div[1]/button[2]")).click();
//             }
//         }
//
//
//         return monthList.get(0);
//    }

    public static SelenideElement monthPicker(String sMonth) {

        SelenideElement currentMonthDiv = $("div[style*='height: inherit; padding']");


        while (!currentMonthDiv.getText().contains(sMonth)) {

            $(By.xpath("//div[2]/div[1]/div[1]/button[2]")).click();
        }

        return currentMonthDiv;
    }

    public static SelenideElement checkUnderwriteStatus() {

        SelenideElement currentStatus = $("p[style*='font']");

        while (!currentStatus.getText().contains("DONE")) {

            Selenide.refresh();
            currentStatus.waitUntil(Condition.visible, 8000);
//            System.out.println("I went into status while");
        }

        return currentStatus;
    }

    public static void createNewLoan() throws InterruptedException {


            //Create new loan
            Loans.searchForCompany("116213748");
            $(By.xpath("//div[contains(.,'FLYING SOLO PROPERTIES')]")).waitUntil(Condition.visible, 6000, 100);
            Loans.industryPicker().click();
            Loans.industryList("Financial and Insurance Services").click();

            //Fill Date of birth
            Loans.dateofBirthPicker().click();
            Loans.yearPicker().doubleClick();
            Loans.yearList("1983").shouldBe(visible).click();
            Thread.sleep(1000);
            Loans.monthPicker("August").click();
            Loans.dayList("5").click();
            Loans.dateOKbutton().click();

            //ID Document state
            Loans.idDocStatePicker().click();
            Loans.docStateList("Tasmania").click();

            //ID document number input
            Thread.sleep(1000);
            Loans.idNumberInput().sendKeys("5234NK");
            Selenide.actions().click();
            Loans.idNumberInput().shouldHave(value("5234NK"));

            //fill address
            Loans.fullAdress().sendKeys("Alumim 11 ramat gan");

            //Fill loan information
            Loans.loanAmount().sendKeys("10000");
            Loans.installmentsNumber().sendKeys("13");
            Loans.purposePicker().click();
            Loans.purposeList("General Working Capital").click();
            //Submit application
            Loans.submitButton().submit();
            Loans.applicationSavedMessage().shouldHave(Condition.text("Application Saved!"));
            Loans.applicationSavedOKbutton().click();
            Loans.submitButton().click();
            Loans.applicationSubmittedMessage().waitUntil(visible, 10000);
            Loans.applicationSubmittedMessage().shouldHave(Condition.text("Application Submitted!"));
            Loans.applicationSubmittedOkButton().click();
            test.log(LogStatus.PASS, "New loan created");


    }

    public static void approveNewLoan() throws Exception {


            //Approve loan
            LoansInfoTab.loanInfo().waitUntil(Condition.visible, 12000, 5000);
            Loans.checkUnderwriteStatus();

//            while (Loans.disabledButtonsDiv().is(visible)) {
//
//                Selenide.refresh();
//                Thread.sleep(3000);
//                System.out.println("I went into while ");
//            }

//            while (Loans.disabledButtonsDiv().is(visible)) {
//
//                Selenide.refresh();
//                Thread.sleep(3000);
//                System.out.println("I went into while ");
//            }
//            while (!Loans.requestApprovalButton().is(enabled)) {
//
//                Selenide.refresh();
//                Thread.sleep(7000);
//                System.out.println("I went into while 2");
//            }
            Loans.requestApprovalButton().waitUntil(appears, 12000);
            Loans.requestApprovalButton().shouldHave(Condition.text("Request Approval")).click();
            LoansInfoTab.loanInfo().waitUntil(Condition.visible, 5000, 4000);
            Loans.approveButton().waitUntil(visible, 12000);
            Loans.approveButton().shouldHave(Condition.text("APPROVE")).click();
            LoansInfoTab.statusTypeText().waitUntil(text("APPROVED"), 10000);
            currentLoan = LoansInfoTab.getLoanID().getText();
            test.log(LogStatus.PASS, "Loan Approved");

    }


    public static void disburseNewLoan(String disburseMethod) throws Exception {


            // Send Documents
            LegalDocumentsTab.legalDocTab().click();
            LegalDocumentsTab.generateDocsButton().click();
            LegalDocumentsTab.docsSentSuccess().waitUntil(Condition.text("Success!"), 30000);
            LegalDocumentsTab.docsSentOKButton().click();

            //Get documents ID and post SIGNED status
            String LoanID = LoansInfoTab.getLoanID().getText();
            String DocumentID = LegalDocumentsTab.getDocumentID(LoanID);
            LegalDocumentsTab.postSignedStatus(DocumentID);
            Selenide.refresh();
            LegalDocumentsTab.legalDocTab().waitUntil(visible, 4000).click();
            LegalDocumentsTab.sentDocumentsID().waitUntil(visible, 5000, 400);
            LegalDocumentsTab.sentDocumentsID().shouldHave(Condition.text(DocumentID));
            LegalDocumentsTab.sentDocumentsStatus().get(3).shouldHave(Condition.text("Signed"));

            switch (disburseMethod) {

                case "InsideLoan":

                    LoansInfoTab.loanInfoTab().click();
                    Loans.disburseButton().waitUntil(text("Disburse"), 8000);
                    Loans.disburseButton().click();
                    LoansInfoTab.loanInfo().waitUntil(Condition.visible, 8000, 5000);
                    LoansInfoTab.checkStatus("ACTIVE");
                    test.log(LogStatus.PASS, "Loan disbursed successfully");

                case "Admin":
                    System.out.println("from admin");

                    $(By.linkText("Admin")).shouldHave(text("Admin")).click();
                    $(By.linkText("Disbursements")).shouldHave(text("Disbursements")).click();
                    Thread.sleep(2000);
                    $$("table tr").shouldHave(CollectionCondition.sizeNotEqual(1));
                    Loans.disburseButtonAdmin().click();
                    Alert myAlert = Selenide.switchTo().alert();
                    myAlert.accept();
                    commonOps.errorBox().shouldHave(text("Disbursed!"));
                    commonOps.errorBoxOKbutton().click();
                    Thread.sleep(7000);
                    Selenide.refresh();
                    $$("table tr").shouldHave(CollectionCondition.size(1));


            }

    }

    public static void createNewLoanFromContactEdit() throws InterruptedException {


            // Go to Contacts
            Thread.sleep(3000);
            $(By.linkText("Contacts")).shouldHave(Condition.text("Contacts")).click();
            Thread.sleep(1000);
            //Edit specific contact by account ID
            Contacts.editContactButton(Contacts.currentUsername).click();
            Loans.loansTab().click();
            Loans.createNewLoanButton().click();
            test.log(LogStatus.PASS, "Create new loan for existing contact");


    }

    public static void submitApplication() {


            //Submit application
            Loans.submitButton().submit();
            Loans.applicationSavedMessage().shouldHave(Condition.text("Application Saved!"));
            Loans.applicationSavedOKbutton().click();
            Loans.submitButton().click();
            Loans.applicationSubmittedMessage().waitUntil(visible, 8000);
            Loans.applicationSubmittedMessage().shouldHave(Condition.text("Application Submitted!"));
            Loans.applicationSubmittedOkButton().click();
            test.log(LogStatus.PASS, "Application submitted");

    }


    public static void confirmDeclinedLoan() throws Exception {

            //Wait for company name to load
            $(By.xpath("//div[contains(.,'FLYING SOLO PROPERTIES')]")).waitUntil(Condition.visible, 5000, 100);
            //Input ID with <300 score
            Loans.idNumberInput().doubleClick();
            Loans.idNumberInput().sendKeys("12ASFHJ");
            //Submit application
            Loans.submitApplication();
            //check closed_rejected status received
            LoansInfoTab.checkStatus("CLOSED");
            test.log(LogStatus.PASS, "Application declined , Status CLOSED_REJECT confirmed");


    }

}

