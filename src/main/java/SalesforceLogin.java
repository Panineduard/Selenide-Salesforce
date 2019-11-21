import com.force.sdk.connector.ForceConnectorConfig;
import com.force.sdk.connector.ForceConnectorUtils;
import com.force.sdk.connector.ForceServiceConnector;
import com.sforce.soap.partner.GetUserInfoResult;
import com.sforce.soap.partner.PartnerConnection;
import com.sforce.ws.ConnectionException;

import java.net.URI;
import java.net.URISyntaxException;

public class SalesforceLogin {

    public String loginSample() throws URISyntaxException {
        String username = "Uername";
        String password = "PWD + Token";
        String endPoint = "https://login.salesforce.com";

        try {
            ForceConnectorConfig config = new ForceConnectorConfig();
            config.setUsername(username);
            config.setPassword(password);

            config.setAuthEndpoint(ForceConnectorUtils.buildForceApiEndpoint(endPoint));

            ForceServiceConnector connector = new ForceServiceConnector(config);

            // Print user and session info
            String endpoint = connector.getConnection().getConfig().getServiceEndpoint();
            URI uri = new URI(endpoint);
            PartnerConnection pa = connector.getConnection();
            GetUserInfoResult userInfo = pa.getUserInfo();
//            System.out.println("UserID: " + userInfo.getUserId());
//            System.out.println("User Full Name: " + userInfo.getUserFullName());
//            System.out.println("User Email: " + userInfo.getUserEmail());
//            System.out.println();
//            System.out.println("Auth End Point: " + config.getAuthEndpoint());
//            System.out.println("Service End Point: " + config.getServiceEndpoint());
//            System.out.println();
            return "https://" + uri.getHost() + "/secur/frontdoor.jsp?sid=" + pa.getSessionHeader().getSessionId()+ "&startURL=/home/home.jsp";
        } catch (ConnectionException ce) {
            ce.printStackTrace();
        }

        return null;
    }
}
