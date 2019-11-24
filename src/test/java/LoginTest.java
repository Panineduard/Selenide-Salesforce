import com.sforce.ws.ConnectionException;
import org.junit.Before;
import org.junit.Test;

import java.net.URISyntaxException;

public class LoginTest {
    @Before
    public void setUp() throws URISyntaxException {
        TestSalesforceUtil.loginToSalesforce();
    }

    @Test
    public void userCanLoginByUsername() throws URISyntaxException, ConnectionException {
        TestSalesforceUtil.openTab("Lead");
        TestSalesforceUtil.clickButton("New");
    }
}
