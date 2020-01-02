package salesforce;

import com.codeborne.selenide.Condition;
import com.force.sdk.connector.ForceConnectorConfig;
import com.force.sdk.connector.ForceConnectorUtils;
import com.force.sdk.connector.ForceServiceConnector;
import com.sforce.soap.partner.PartnerConnection;
import com.sforce.ws.ConnectionException;
import org.openqa.selenium.By;
import utils.PropertyUtil;

import java.net.URI;
import java.net.URISyntaxException;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class Security {
    private static ForceConnectorConfig config = new ForceConnectorConfig();

    static String getShareLink() throws URISyntaxException, ConnectionException {
        String endPoint = Boolean.parseBoolean(PropertyUtil.getPropertyByName("sf.isSandbox")) ? "https://test.salesforce.com" : "https://login.salesforce.com";

        config.setUsername(PropertyUtil.getPropertyByName("sf.user"));
        config.setPassword(PropertyUtil.getPropertyByName("sf.password") + PropertyUtil.getPropertyByName("sf.token"));
        config.setAuthEndpoint(ForceConnectorUtils.buildForceApiEndpoint(endPoint));

        ForceServiceConnector connector = new ForceServiceConnector(config);
        String endpoint = connector.getConnection().getConfig().getServiceEndpoint();
        URI uri = new URI(endpoint);
        PartnerConnection pa = connector.getConnection();

        return "https://" + uri.getHost() + "/secur/frontdoor.jsp?sid=" + pa.getSessionHeader().getSessionId() + "&startURL=/home/home.jsp";
    }

    public static void login() {
        String loginUrl = null;
        try {
            loginUrl = getShareLink();
        } catch (URISyntaxException | ConnectionException e) {
            e.printStackTrace();
        }
        open(loginUrl);
        if ($(By.className("switch-to-lightning")).exists()) {
            $(By.className("switch-to-lightning")).should(Condition.appear).click();
        }
    }

}
