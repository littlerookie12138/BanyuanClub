package club.banyuan;

import java.util.Properties;

public class ProUtil {
    private static final Properties properties = new Properties();

    static {
        try {
            properties.load(ProUtil.class.getClassLoader().getResourceAsStream("Vehicle.properties"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getCarPath() {
        return properties.getProperty("carPath");
    }

    public static String getVanPth() {
        return properties.getProperty("vanPath");
    }

    public String getProp (String key) {
        return properties.getProperty(key);
    }
}
