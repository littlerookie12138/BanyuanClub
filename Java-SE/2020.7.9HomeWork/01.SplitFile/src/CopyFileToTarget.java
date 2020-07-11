import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CopyFileToTarget {

    public static void main(String[] args) throws IOException {
        File mp3 = new File("/Users/edz/BanyuanClub/Java-SE/2020.7.9HomeWork/01.SplitFile/WIN.mp3");
        File targetFile = new File("/Users/edz/BanyuanClub/Java-SE/2020.7.9HomeWork/01.SplitFile/TargetFiles");
        File combineFile = new File("/Users/edz/BanyuanClub/Java-SE/2020.7.9HomeWork/01.SplitFile/CombineMp3File");
        split(mp3, 1024, targetFile);
        combine(targetFile, combineFile);
    }

    public static void split(File sourceFile, int size, File targetFolder) {
        if (targetFolder == null) {
            throw new RuntimeException("目标路径不存在!");
        }

        if (sourceFile == null) {
            throw new RuntimeException("源文件不合法!");
        }
        if (!targetFolder.exists()) {
            targetFolder.mkdirs();
        }
        long total =0L;
        OutputStream os = null;
        try (InputStream is = new FileInputStream(sourceFile);
        ) {
            byte[] mp3File = new byte[size * 9];

            int count = 0;
            int countMp3Files = 1;
            while ((count = is.read(mp3File, 0, mp3File.length)) > 0) {
                total += count;
                os = new FileOutputStream(new File(targetFolder.getAbsolutePath(), "WIN.mp3." + countMp3Files));

                countMp3Files++;
                System.out.println(count);
                os.write(mp3File, 0, count);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

        System.out.println("total="+total);

    }

    public static void combine(File sourceFolder, File targetFolder) throws IOException {
        if (targetFolder == null) {
            throw new RuntimeException("目标路径不存在!");
        }

        if (sourceFolder == null) {
            throw new RuntimeException("源文件不合法!");
        }
        if (!targetFolder.exists()) {
            targetFolder.mkdirs();
        }


        List<Byte> list = new ArrayList<>();
        InputStream is = null;
        OutputStream os = null;
        for (String s : sourceFolder.list()) {
            //这个流有没有更好的方法开启？
            is = new FileInputStream(new File(sourceFolder, s));

            byte[] bytes = new byte[1024 * 9];
            int count = is.read(bytes);
            for (int i = 0; i < count; i++) {
                list.add(bytes[i]);
            }
            is.close();
        }

        os = new FileOutputStream(new File(targetFolder, "WIN.mp3"));
        for (Byte aByte : list) {
            os.write(aByte);
        }

        try {
            is.close();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

