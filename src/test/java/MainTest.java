import com.sforce.ws.ConnectionException;
import lead.Lead;
import org.junit.Before;
import org.junit.Test;
import salesforce.Security;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class MainTest {
    @Before
    public void setUp() throws URISyntaxException {
        Security.login();
    }

    @Test
    public void userCanLoginByUsername() throws URISyntaxException, ConnectionException, InterruptedException {
        Lead.createLead();
        Lead.convertLead();
    }
}
