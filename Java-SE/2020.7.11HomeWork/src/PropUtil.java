import java.util.Properties;

public class PropUtil {
    private static final Properties properties = new Properties();

    //加载默认配置文件
    static {
        try {
            properties.load(PropUtil.class.getClassLoader().getResourceAsStream("videos.properties"));
        } catch (Exception e) {
            throw new RuntimeException("获取配置文件失败");
        }
    }

    public String getProp (String key) {
        return properties.getProperty(key);
    }

    public static String getPath() {
        return properties.getProperty("path");
    }
}
