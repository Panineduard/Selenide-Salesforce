import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.sforce.ws.ConnectionException;
import org.junit.Before;
import org.junit.Test;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class LoginTest {
    @Before
    public void setUp() throws URISyntaxException {
        TestSalesforceUtil.loginToSalesforce();
    }

    @Test
    public void userCanLoginByUsername() throws URISyntaxException, ConnectionException, InterruptedException {
        TestSalesforceUtil.openTab("Lead");
        TestSalesforceUtil.clickButton("New");
        Map<String, String> fields = new HashMap<String, String>();
        fields.put("First Name", "Test");
        fields.put("Last Name", "Test");
        fields.put("Entity", "Test");
        fields.put("SSN", "001");
        fillForm(fields);
        TestSalesforceUtil.clickButton("Save");
        System.out.println("Finish");
    }

    private void fillForm(Map<String, String> fields) {
        fields.forEach((k, v) -> {
            $(".slds-modal__container").waitUntil(Condition.appear, 5000).find(byText(k)).parent().parent().lastChild().setValue(v);
        });
    }
}
