package resources.webService.endpoint;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ws.test.server.MockWebServiceClient;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import static org.springframework.ws.test.server.RequestCreators.withPayload;
import static org.springframework.ws.test.server.ResponseMatchers.payload;

/**
 * Created with IntelliJ IDEA.
 * User: Julien
 * Date: 02/04/13
 * Time: 17:15
 */
@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext()
@ContextConfiguration("application-context.xml")
public class EnregistrementEndPointIntegrationTest {


    @Autowired
    private ApplicationContext applicationContext;

    private MockWebServiceClient mockClient;

    @Before
    public void createClient() throws Exception {
        mockClient = MockWebServiceClient.createClient(applicationContext);
    }

    @Test
    public void enregistrementEndpoint() throws Exception {
        Source requestPayload;
        Source responsePayload;

        requestPayload = new StreamSource(new ClassPathResource("user1.xml").getInputStream());//resources/user/request/
        responsePayload = new StreamSource(new ClassPathResource("enregistrementResponseOK.xml").getInputStream()); //resources/user/response/

        mockClient.sendRequest(withPayload(requestPayload)).andExpect(payload(responsePayload));
    }

}
