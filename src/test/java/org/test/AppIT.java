package org.test;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import junit.framework.TestCase;
import ui.twitter.app.TwitterApp;

/**
 * Test to verify HTTP response of generated simple aura application
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(TwitterApp.class)
@WebIntegrationTest(randomPort=true)
public class AppIT extends TestCase {
	
	@Value("${local.server.port}")
	int serverPort;
	
	@Test
	@WithMockUser
    public void testAppLoads() throws Exception {

    	CloseableHttpClient http = HttpClients.createDefault();
        HttpGet get = new HttpGet("http://localhost:"+serverPort+"/auraTestApp/auraTestApp.app");
        CloseableHttpResponse response = http.execute(get);
        
        assertEquals("Failed to load project home page", HttpStatus.SC_OK, response.getStatusLine().getStatusCode());

        HttpEntity entity = response.getEntity();
        if (entity == null) {
            fail("Project page should have response");
        } else {
        	String actualPage = EntityUtils.toString(entity);
            assertTrue("Hello response is wrong", actualPage.contains("Hello web, from the Aura sample app aura-test-app"));
        }
    }
}
