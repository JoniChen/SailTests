package utils;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import com.relevantcodes.extentreports.LogStatus;
import junit.framework.Assert;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class commonOps extends Utils {



        public static void LoginAdmin() throws InterruptedException {


                open(Configuration.baseUrl);
                $(By.name("username")).shouldBe(visible).sendKeys("Yoni+1@sail.com.au");
                $(By.name("password")).sendKeys("yoni1234");
                $(By.cssSelector("button[type=submit")).shouldHave(text("SIGN IN")).click();
                Thread.sleep(2000);
                String currentUrl = WebDriverRunner.url();
                Assert.assertEquals(currentUrl, "http://staging.sail.com.au/admin/adminview");
                test.log(LogStatus.PASS, "Logged in as Admin");

        }

        public static void LoginSales() {

                Selenide.clearBrowserLocalStorage();
                open(Configuration.baseUrl);
                $(By.name("username")).shouldBe(visible).sendKeys("yoni+sales@sail.com.au");
                $(By.name("password")).sendKeys("yoni1234");
                $(By.cssSelector("button[type=submit")).shouldHave(text("SIGN IN")).click();
        }

        public static void LoginSalesAdmin() {

                open(Configuration.baseUrl);
                $(By.name("username")).shouldBe(visible).sendKeys("yoni+salesadmin@sail.com.au");
                $(By.name("password")).sendKeys("yoni1234");
                $(By.cssSelector("button[type=submit")).shouldHave(text("SIGN IN")).click();

        }

        public static SelenideElement errorBox() {

                return $("div[style*='font-size: 16px; color']");

        }

        public static SelenideElement errorBoxOKbutton() {

                return $("div span[style*='position: relative; padding']");

        }
}
