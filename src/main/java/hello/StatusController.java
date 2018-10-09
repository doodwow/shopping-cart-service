package hello;

import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.Properties;

/**
 * Created by CT021730 on 10/04/2018.
 *
 * Rest Controller providing endpoints for status/management information
 *
 */
@RestController
public class StatusController {

    private static final String ISACTIVERESPONSE = "ACTIVE";

    private static final String VERSIONLABEL = "version";
    private static final String PROPERTYFILENAME = "git.properties";
    private static final String PROPERTYNAME = "git.commit.id";

    @GetMapping("/isActive")
    public String isActive() {
        return ISACTIVERESPONSE;
    }

    /**
     * Relies on a git.properties file existing on the classpath.
     *
     * The file is generated by a build plugin included in the pom (git-commit-id-plugin)
     *
     * @return
     */
    @GetMapping("/buildInfo")
    public Map buildInfo() throws IOException {
        Properties properties = new Properties();
        properties.load(getClass().getClassLoader().getResourceAsStream(PROPERTYFILENAME));

        return Collections.singletonMap(VERSIONLABEL, String.valueOf(properties.get(PROPERTYNAME)));
    }

}
