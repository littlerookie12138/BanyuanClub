import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class MbmRequest {
    private String method;
    private String path;
    private int contentLength;
    private String payLoad;
    private String host;

    @Override
    public String toString() {
        return "MbmRequest{" +
                "method='" + method + '\'' +
                ", path='" + path + '\'' +
                ", contentLength=" + contentLength +
                ", payLoad='" + payLoad + '\'' +
                ", host='" + host + '\'' +
                '}';
    }

    public static MbmRequest solveRequest(BufferedReader bufferedReader) {
        try {
            String line = bufferedReader.readLine();
            if (line == null) {
                return null;
            }
            StringTokenizer stringTokenizer = new StringTokenizer(line);
            MbmRequest mbmRequest = new MbmRequest();

            mbmRequest.setMethod(stringTokenizer.nextToken());
            mbmRequest.setPath(stringTokenizer.nextToken());

            while (line.length() != 0) {
                if (line.startsWith("Content-Length: ")) {
                    mbmRequest.setContentLength(Integer.parseInt(line.replace("Content-Length: ", "")));
                } else if (line.startsWith("Host: ")) {
                    mbmRequest.setHost(line.replace("Host: ", ""));
                }
                line = bufferedReader.readLine();
            }

            // 如果这里大于0，说明收到了数据
            if (mbmRequest.getContentLength() > 0) {
                char[] data = new char[mbmRequest.getContentLength()];
                bufferedReader.read(data, 0, data.length);
                mbmRequest.setPayLoad(new String(data));
            }
            System.out.println(mbmRequest);

            return mbmRequest;
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }

    public static Map<String, String> getFormData(MbmRequest mbmRequest) {
        String payLoad = mbmRequest.getPayLoad();
        StringTokenizer stringTokenizer = new StringTokenizer(payLoad, "&");
        Map<String, String> data = new HashMap<>();
        while (stringTokenizer.hasMoreTokens()) {
            String[] split = stringTokenizer.nextToken().split("=");
            data.put(split[0], split[1]);
        }


        return data;
    }

    public String getMethod() {
        return method;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getContentLength() {
        return contentLength;
    }

    public void setContentLength(int contentLength) {
        this.contentLength = contentLength;
    }

    public String getPayLoad() {
        return payLoad;
    }

    public void setPayLoad(String payLoad) {
        this.payLoad = payLoad;
    }
}
