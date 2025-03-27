package ui.projectconfig;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class ProjectConfig {
    private static Properties properties = new Properties();
    public static final String BROWSER_NAME = System.getProperty("browser.name", getProperty("browser.name"));
    public static final String REMOTE_URL = System.getProperty("remote.url", getProperty("remote.url"));
    public static final String API_URL = System.getProperty("api.url", getProperty("api.url"));

    private static String getProperty(String propertyName){
        try {
            InputStream inputStream = Files.newInputStream(Paths.get("src/test/resources/config.properties"));
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return properties.getProperty(propertyName);
    }
}
