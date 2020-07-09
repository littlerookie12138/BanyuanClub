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
        if (!targetFolder.exists()) {
            targetFolder.mkdirs();
        }
        long total =0L;
        try (InputStream is = new FileInputStream(sourceFile);
        ) {
            byte[] mp3File = new byte[size * 9];

            int count = 0;
            int countMp3Files = 1;
            while ((count = is.read(mp3File, 0, mp3File.length)) > 0) {
                total += count;
                OutputStream os = new FileOutputStream(new File(targetFolder.getAbsolutePath(), "WIN.mp3." + countMp3Files));
                if (!new File(targetFolder.getAbsolutePath(), "WIN.mp3." + countMp3Files).exists()) {
                    new File(targetFolder.getAbsolutePath(), "WIN.mp3." + countMp3Files).createNewFile();
                }
                countMp3Files++;
                System.out.println(count);
                os.write(mp3File, 0, count);
                os.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("total="+total);

    }

    public static void combine(File sourceFolder, File targetFolder) throws IOException {
        if (!targetFolder.exists()) {
            targetFolder.mkdirs();
        }

        List<Byte> list = new ArrayList<>();
        for (String s : sourceFolder.list()) {
            InputStream is = new FileInputStream(new File(sourceFolder, s));

            byte[] bytes = new byte[1024 * 9];
            int count = is.read(bytes);
            for (int i = 0; i < count; i++) {
                list.add(bytes[i]);
            }
            is.close();
        }

        OutputStream os = new FileOutputStream(new File(targetFolder, "WIN.mp3"));
        for (Byte aByte : list) {
            os.write(aByte);
        }
        os.close();
    }
}

