import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.sforce.ws.ConnectionException;
import org.openqa.selenium.By;

import java.net.URISyntaxException;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

class TestSalesforceUtil {
    static void loginToSalesforce() {
        String loginUrl = null;
        try {
            loginUrl = SalesforceUtil.getShareLink();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (ConnectionException e) {
            e.printStackTrace();
        }
        open(loginUrl);
        if ($(By.className("switch-to-lightning")).exists()) {
            $(By.className("switch-to-lightning")).should(Condition.appear).click();
        }
    }

    static void clickButton(String name) {
        SelenideElement newButton = $(byXpath("//a[contains(@title,'" + name + "')]"));
        newButton.should(Condition.appear);
        newButton.click();
        System.out.println("hello");
    }

    static void openTab(String name) {
        if ($(byXpath("//one-app-nav-bar-menu-item[contains(@data-id,'" + name + "')]")).exists()) {
            $(byXpath("//one-app-nav-bar-menu-item[contains(@data-id,'" + name + "')]")).click();

        } else if ($(byXpath("//one-app-nav-bar-item-root[contains(@data-id,'" + name + "')]")).exists()) {
            $(byXpath("//one-app-nav-bar-item-root[contains(@data-id,'" + name + "')]")).click();
        }
    }
}
