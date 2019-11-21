import com.codeborne.selenide.Condition;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import java.net.URISyntaxException;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.url;

public class LoginTest {
    @Before
    public void setUp() throws URISyntaxException {
//        Configuration.startMaximized = false;
//        Configuration.reportsFolder = "target/surefire-reports";

    }
    @Test
    public void userCanLoginByUsername() throws URISyntaxException {
        SalesforceLogin login = new SalesforceLogin();
        String loginUrl = login.loginSample();
        open(loginUrl);
        if ($(By.className("switch-to-lightning")).exists()) {
            $(By.className("switch-to-lightning")).should(Condition.appear).click();
        }

        String url = url();
        url = url.replace("setup/SetupOneHome/home", "n/ascendix_ep__Search");
        open(url);
//        $(By.name("user.name")).setValue("johny");
//        $("#submit").click();
//        $(".loading_progress").should(disappear); // Само подождёт, пока элемент исчезнет
//        $("#username").shouldHave(text("Hello, Johny!")); // Само подождёт, пока у элемента появится нужный текст
    }
}
