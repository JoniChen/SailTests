package pageObjects;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class ApplicantTab {


    public static SelenideElement applicantTab() {

        return $(By.id("loan-tabs-tab-applicant"));

    }

    public static SelenideElement applicantEmail() {

        return $("a[href*='mailto']");

    }

    public static SelenideElement attachmentsTab() {

        return $(By.id("stakeholder-tabs-tab-attachments"));

    }

    public static SelenideElement creditReportTab() {

        return $(By.id("stakeholder-tabs-tab-creditReport"));

    }

    public static SelenideElement scoreProgress() {

        return $("div[role*='progress']");

    }
}
