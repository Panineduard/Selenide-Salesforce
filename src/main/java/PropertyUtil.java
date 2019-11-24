import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

class PropertyUtil {
    private static Properties property = getProperties();

    public static String getProperty(String name) {
        return property.getProperty(name);
    }

    private static Properties getProperties() {
        Properties properties = new Properties();

        try {
            FileInputStream fileInputStream = new FileInputStream("src/main/resources/config.properties");
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return properties;
    }
}
