import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

class VideoStore {
    // 将包含一个Video数组。

    // VideoStore中定义方法
    // addVideo(String): 添加一个新的电影
    // checkOut(String): 根据片名借出电影
    // returnVideo(String): 归还电影
    // receiveRating(String, int) : 设置用户对电影的评分(1~5)，收到评分之后，计算该电影的平均评分，然后保存到Video的评分属性中
    // listInventory(): 列出整个库存的电影。
    private List<Video> videos = new ArrayList<>();
    private final static String PATH = PropUtil.getPath();

    public List<Video> getVideos() {
        return videos;
    }

    void store() throws IOException {
        File sourcesFile = new File(PATH);
        if (!sourcesFile.exists()) {
            //判断文件的父集目录是否存在
            File itsParent = sourcesFile.getParentFile();
            if (!itsParent.exists()) {
                itsParent.mkdirs();
            } else {
                sourcesFile.createNewFile();
            }

            System.out.println(sourcesFile.createNewFile() ? "成功" : "失败");
        }

        try (FileOutputStream fileOutputStream = new FileOutputStream(sourcesFile)){
            fileOutputStream.write(videos.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //添加一个新的电影，新电影默认未被借出
    Video addVideo(String name, String type) throws IOException {
        Video v = new Video();
        v.setVideoStatus(name, type, false);
        v.setVideoScores(0);
        videos.add(v);
        store();
        return v;
    }

    //根据片名借出电影
    void checkOut(String name) throws IOException {
        for (Video video : videos) {
            if (video.getVideoName().equals(name)) {
                //找到电影后判断是否在店
                if (!video.isRentedOrNot) {
                    video.isRentedOrNot = true;
                    store();
                    System.out.println("片片已经借出，请爱护哦~");
                    System.out.println("欢迎下次再来!");
                    System.out.println();
                    return;
                } else {
                    System.out.println("这部电影太火了已经被借走啦！");
                    System.out.println();
                    return;
                }
            }
        }
        System.out.println("小店还未引进这部电影，敬请期待！");
        System.out.println();

    }

    //归还电影并打分
    void returnAndScore(String name, int score) throws IOException {
        for (Video video : videos) {
            if (video.getVideoName().equals(name)) {
                //找到该电影后判断电影是否在店
                if (!video.isRentedOrNot) {
                    System.out.println("片片不是我们店的哦");
                    System.out.println();
                    return;
                } else {
                    video.isRentedOrNot = false;
                    video.setVideoScores(score);
                    store();
                    System.out.println("感谢借阅~欢迎下次再来");
                    System.out.println();
                    return;
                }

            }
        }
}

    //打印店铺电影列表
    void printVideoList() {
        for (int i = 0; i < videos.size(); i++) {
            Video video = videos.get(i);
            System.out.printf("片名：%s\t\t评分：%d\t是否借出:%s\n", video.getVideoName(), video.getVideoScoresByCus(),
                    video.isRentedOrNot ? "是" : "否");
        }
    }

    //加载配置文件中的资源
    public void load() throws Exception{
        File sourcesFile = new File(PATH);
        if (!sourcesFile.exists()) {
            //判断文件的父集目录是否存在
            File itsParent = sourcesFile.getParentFile();
            if (!itsParent.exists()) {
                itsParent.mkdirs();
            } else {
                System.out.println(sourcesFile.createNewFile() ? "成功" : "失败");
            }

        }

        try (FileInputStream fileInputStream = new FileInputStream(PATH)){
            byte[] bytes = fileInputStream.readAllBytes();
            String jsonString = new String(bytes);
            List<Video> videos = JSONObject.parseArray(jsonString, Video.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}