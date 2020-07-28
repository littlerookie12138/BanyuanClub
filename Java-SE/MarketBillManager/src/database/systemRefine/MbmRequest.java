package database.systemRefine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class MbmRequest {
    private String method;
    private String path;
    private String host;
    private int contentLength;
    private String payload;

    public MbmRequest(String method, String path, String host, int contentLength, String payload) {
        this.method = method;
        this.path = path;
        this.host = host;
        this.contentLength = contentLength;
        this.payload = payload;
    }

    public static MbmRequest parse(BufferedReader bufferedReader) throws IOException {
        String line = bufferedReader.readLine();
        if (line == null) {
            return null;
        }

        // 生成一个保存客户端发送过来的请求的对象,保存某次请求的method path以及host
        StringTokenizer tokenizer = new StringTokenizer(line);
        MbmRequest mbmRequest = new MbmRequest();
        mbmRequest.setMethod(tokenizer.nextToken());
        mbmRequest.setPath(tokenizer.nextToken());

        while (line.length() != 0) {
            if (line.startsWith("Content-Length:")) {
                mbmRequest.setContentLength(Integer.parseInt(line.replace("Content-Length: ", "")));
            } else if (line.startsWith("Host:")) {
                mbmRequest.setHost(line.replace("Host:", ""));
            }
            line = bufferedReader.readLine();
        }

        if (mbmRequest.getContentLength() > 0) {
            char[] buf = new char[mbmRequest.getContentLength()];
            bufferedReader.read(buf, 0, buf.length);
            mbmRequest.setPayload(new String(buf));
        }

        return mbmRequest;
    }

    public Map<String, String> getFormData() throws UnsupportedEncodingException {
        StringTokenizer tokenizer = new StringTokenizer(payload, "&,");
        Map<String, String> map = new HashMap<>();
        while (tokenizer.hasMoreTokens()) {
            String[] split = processStr(tokenizer.nextToken()).split("[:=]");
            if (split.length == 1) {
                map.put(split[0], "");
            } else {
                map.put(split[0], URLDecoder.decode(split[1], "utf-8").trim());
            }
        }

        return map;
    }

    public static String processStr(String target) {
        target = target.replace("{", "");
        target = target.replace("}", "");
        target = target.replace("\"", "");

        return target;
    }

    @Override
    public String toString() {
        return "database.system.MbmRequest{" +
                "method='" + method + '\'' +
                ", path='" + path + '\'' +
                ", host='" + host + '\'' +
                ", contentLength=" + contentLength +
                ", payload='" + payload + '\'' +
                '}';
    }

    public MbmRequest() {
    }

    public String getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public String getHost() {
        return host;
    }

    public int getContentLength() {
        return contentLength;
    }

    public void setContentLength(int contentLength) {
        this.contentLength = contentLength;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setHost(String host) {
        this.host = host;
    }
}
