package pageObjects;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class RemiitanceTab {

    public static SelenideElement remittanceTab() {

        return $(By.id("loan-tabs-tab-remittance"));

    }

    public static SelenideElement disburseAmount() {

        return $(By.xpath("//*[@id=\"loan-tabs-pane-remittance\"]/div/div[1]/div[1]/p[1]"));

    }

    public static SelenideElement totalAmount() {

        return $(By.xpath("//*[@id=\"loan-tabs-pane-remittance\"]/div/div[1]/div[1]/p[2]"));

    }

    public static SelenideElement payeeType() {

        return $("td[id*='payee_type_0'] div div div[style^='color: rgba']");

    }

    public static SelenideElement addNewRow() {

        return $("button[id='alexander_row_adder']");

    }

    public static ElementsCollection selectAccountList() {

        return $$("input[type='radio']");

    }


    public static SelenideElement payeeTypeList(String sPayeeType) {

        List<SelenideElement> payeeType = $$("span[role*='menuitem']");

        for (SelenideElement itemElement : payeeType) {

            if (itemElement.getText().equals(sPayeeType)) {
                return itemElement;
            }
        }

        return payeeType.get(0);


    }

}
