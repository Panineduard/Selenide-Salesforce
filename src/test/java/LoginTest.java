import com.codeborne.selenide.Condition;
import com.sforce.ws.ConnectionException;
import org.junit.Before;
import org.junit.Test;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class LoginTest {
    @Before
    public void setUp() throws URISyntaxException {
        TestSalesforceUtil.loginToSalesforce();
    }

    @Test
    public void userCanLoginByUsername() throws URISyntaxException, ConnectionException {
        TestSalesforceUtil.openTab("Lead");
        TestSalesforceUtil.clickButton("New");
        Map<String, String> fields = new HashMap<String, String>();
        fields.put("First Name", "Test");
        fields.put("Last Name", "Test");
        fields.put("Entity", "Test");
        fields.put("SSN", "001");
        fillForm(fields);
        System.out.println("Finish");
    }

    private void fillForm(Map<String, String> fields) {
        String field = (String) fields.keySet().toArray()[0];
        $(byText(field)).waitUntil(Condition.appear, 5000);
        fields.forEach((k, v) -> {
            $(byXpath("//span[text()='" + k + "']")).parent().parent().lastChild().setValue(v);
        });
    }
}
