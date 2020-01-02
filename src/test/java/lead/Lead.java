package lead;

import com.codeborne.selenide.Condition;
import salesforce.UIControls;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selectors.byTitle;
import static com.codeborne.selenide.Selenide.$;

public class Lead {
    public static void createLead() {
        UIControls.openTab("Lead");
        UIControls.clickButton("New");
        if (!$(".slds-modal__container").waitUntil(Condition.exist, 2000).exists()) {
            UIControls.clickButton("New");
        }

        Map<String, String> fields = new HashMap<String, String>();
        fields.put("First Name", "Marisol");
        fields.put("Last Name", "Testcase");
        fields.put("Entity", "Ascendix");
        fields.put("SSN", "000000001");
        fields.put("Lead Source", "Web");
        fields.put("Street", "220 Locust Ave, Anthill, MO 65488");
        fields.put("State/Province", "Montana");
        fields.put("Zip/Postal Code", "65488");
        UIControls.fillForm(fields);
        UIControls.clickButtonInForm("Save");

    }

    public static void convertLead() {
        UIControls.clickButton("Convert");
        UIControls.clickButtonInForm("Convert");
        UIControls.clickButtonInForm("Convert");
        UIControls.clickButtonInForm("Convert");
        System.out.println("Finish");
    }
}
