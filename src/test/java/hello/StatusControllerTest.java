package hello;

import org.junit.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Map;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

/**
 * Created by CT021730 on 10/04/2018.
 */
public class StatusControllerTest {

    @Test
    public void IsActiveReturnsActive() {
        StatusController controller = new StatusController();
        assertThat(controller.isActive(), is("ACTIVE"));
    }

    @Test
    public void Should_NotThrowException() {
        StatusController controller = new StatusController();

        try {
            controller.buildInfo();
        } catch (IOException e) {
            fail("IOException thrown when one was not expected");
        }
    }

}
