package salesforce;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.util.List;
import java.util.Map;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class UIControls {
    public static void fillForm(Map<String, String> fields) {
        fields.forEach((k, v) -> {
            SelenideElement element = $(".slds-modal__container").find(byText(k)).waitUntil(Condition.appear, 5000);
            System.out.println(element);
            if (element.exists()) {
                SelenideElement parentElement = element.parent().parent();

                if (parentElement.has(Condition.cssClass("uiInputSelect"))) {
                    parentElement.find(By.tagName("a")).click();
                    SelenideElement listElements = $$(byXpath("//div[contains(@class, 'select-options')]")).findBy(Condition.cssClass("visible"));
                    if (listElements.exists()) {
                        SelenideElement listElement = listElements.find(By.tagName("ul")).findAll(By.tagName("a")).findBy(Condition.exactText(v));
                        if (listElement.exists()) {
                            listElement.click();
                            listElements.waitUntil(Condition.disappear, 2000);
                        }
                    }
                } else {
                    parentElement.lastChild().waitUntil(Condition.appear, 5000).setValue(v);
                }
            }
        });
    }

    public static void clickButton(String name) {
        $(byTitle(name)).waitUntil(Condition.exist, 5000).click();
    }

    public static void openTab(String name) {
        if ($(byXpath("//one-app-nav-bar-menu-item[contains(@data-id,'" + name + "')]")).exists()) {
            $(byXpath("//one-app-nav-bar-menu-item[contains(@data-id,'" + name + "')]")).click();

        } else if ($(byXpath("//one-app-nav-bar-item-root[contains(@data-id,'" + name + "')]")).exists()) {
            $(byXpath("//one-app-nav-bar-item-root[contains(@data-id,'" + name + "')]")).click();
        }
    }

    public static void clickButtonInForm(String name) {
        SelenideElement element = $(".slds-modal__container").findAll(By.tagName("button")).findBy(Condition.exactText(name));
        if (element.exists()) {
            element.click();
        }

    }
}
