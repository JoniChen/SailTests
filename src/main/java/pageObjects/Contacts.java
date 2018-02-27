package pageObjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import utils.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class Contacts extends Utils {

    static String currentUsername;


    public static SelenideElement addButton() {

        return $("div button div[style*='height: 36']");
    }
    public static SelenideElement firstName() {

        return $(By.name("account.firstName"));
    }
    public static SelenideElement lastName() {

        return $(By.name("account.lastName"));
    }
    public static SelenideElement email() {

        return $(By.name("account.email"));
    }
    public static SelenideElement mobilePhone() {

        return $(By.name("account.phone"));
    }
    public static SelenideElement referrerPicker() {

        return $("div[id*='Pleaseselect']");
    }
    public static SelenideElement saveButton() {

        return $("button[type=submit]");
    }

    public static SelenideElement referrerList(String sReferrer){

        List<SelenideElement> listReferrer = $$("div div div div span div div div");

        for(SelenideElement itemElement:listReferrer){

            if(itemElement.getText().equals(sReferrer)) {
                return itemElement;
            }
        }

        return listReferrer.get(0);
    }


    public static SelenideElement editContactButton(String username) throws InterruptedException {

        Thread.sleep(1000);
        $("div.card").waitUntil(Condition.visible, 10000, 10000);

        if (currentUsername != null) {
            SelenideElement row = $$("table tr").findBy(Condition.text(username));
            SelenideElement editButton = row.find(By.linkText("Edit"));
            return editButton;
        } else {

            SelenideElement row = $$("table tr").findBy(Condition.text("Joni"));
            SelenideElement editButton = row.find(By.linkText("Edit"));
            return editButton;
        }

    }

    public static String createRandomEmail() {
        Random randomGenerator = new Random();
//        int randomInt = randomGenerator.nextInt(100000);
        String timeStamp = new SimpleDateFormat("dd/MM/yy-HH:mm").format(new Date());

        return "sail " + timeStamp + "@gmail.com";
    }


    public static void createNewContact() {


            Contacts.addButton().click();
            Contacts.firstName().sendKeys("Joni");
            Contacts.lastName().sendKeys("Test");
            Contacts.mobilePhone().sendKeys("0502776663");
            currentUsername = Contacts.createRandomEmail();
            Contacts.email().sendKeys(currentUsername);
            Contacts.referrerPicker().click();
            Contacts.referrerList("Referral").click();
            Contacts.saveButton().submit();
            test.log(LogStatus.PASS, "New contact created");



//        catch (Throwable ex) {
//
//            test.log(LogStatus.FAIL, "Error when creating new contact : " + ex.getMessage());
//            fail("Error when creating new contact , see report");
//        }
    }

}
