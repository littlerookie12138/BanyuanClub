import java.io.File;
import java.io.IOException;

public class Files {
    public static void main(String[] args) throws IOException {
        File file = new File(Files.class.getResource("/").getPath());
        System.out.println(file.getAbsolutePath());
        File demo = new File( "demo");
//        demo.createNewFile();

        File Demo = new File(demo.getParent());
        if (!Demo.exists()) {
            System.out.println(Demo.exists());
            Demo.mkdir();
        } else {
            System.out.println(Demo.getAbsolutePath());
        }
        demo.delete();
    }

    public void delete(File file) {

    }
}
