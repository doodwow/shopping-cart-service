package hello;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URL;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

/**
 * Created by CT021730 on 10/04/2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StatusControllerIT {

    @LocalServerPort
    private int port;

    private URL base;

    @Autowired
    private TestRestTemplate template;

    @Before
    public void setUp() throws Exception {
        this.base = new URL("http://localhost:" + this.port + "/");
    }

    @Test
    public void Should_IsActive_ReturnActive() throws Exception {
        String endpoint = this.base.toString() + "isActive";
        ResponseEntity<String> response = template.getForEntity(endpoint, String.class);
        assertThat(response.getBody(), equalTo("ACTIVE"));
    }

    @Test
    public void Should_BuildInfo_ReturnGitCommitHash() throws Exception {
        String endpoint = this.base.toString() + "buildInfo";
        ResponseEntity<String> response = template.getForEntity(endpoint, String.class);
        String body = response.getBody();
        String gitCommit = body.substring(body.indexOf(":")+2, body.length()-2);

        assertThat(gitCommit, is(not(equalTo("unknown"))));
    }

}
