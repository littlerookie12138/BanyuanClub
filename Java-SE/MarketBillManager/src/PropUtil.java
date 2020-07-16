import java.io.IOException;
import java.util.Properties;

public class PropUtil {
    private static Properties properties = new Properties();

    static {
        try {
            properties.load(PropUtil.class.getClassLoader().getResourceAsStream("user.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProp(String key) {
        return properties.getProperty(key);
    }

    public static String getPath() {
        return getProp("user.store.path");
    }
}
