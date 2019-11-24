import com.force.sdk.connector.ForceConnectorConfig;
import com.force.sdk.connector.ForceConnectorUtils;
import com.force.sdk.connector.ForceServiceConnector;
import com.sforce.soap.partner.PartnerConnection;
import com.sforce.ws.ConnectionException;

import java.net.URI;
import java.net.URISyntaxException;

class SalesforceUtil {
    private static ForceConnectorConfig config = new ForceConnectorConfig();

    static String getShareLink() throws URISyntaxException, ConnectionException {

        String endPoint = Boolean.parseBoolean(PropertyUtil.getProperty("sf.isSandbox")) ? "https://test.salesforce.com" : "https://login.salesforce.com";

        config.setUsername(PropertyUtil.getProperty("sf.user"));
        config.setPassword(PropertyUtil.getProperty("sf.password") + PropertyUtil.getProperty("sf.token"));
        config.setAuthEndpoint(ForceConnectorUtils.buildForceApiEndpoint(endPoint));

        ForceServiceConnector connector = new ForceServiceConnector(config);
        String endpoint = connector.getConnection().getConfig().getServiceEndpoint();
        URI uri = new URI(endpoint);
        PartnerConnection pa = connector.getConnection();

        return "https://" + uri.getHost() + "/secur/frontdoor.jsp?sid=" + pa.getSessionHeader().getSessionId() + "&startURL=/home/home.jsp";
    }

}
