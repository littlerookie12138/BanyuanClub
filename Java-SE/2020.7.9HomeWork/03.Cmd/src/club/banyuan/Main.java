package club.banyuan;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    //获取当前程序所在路径
    String currunntPath = System.getProperty("user.dir");

    for (int i = 0; i < args.length; i++) {
      System.out.println("参数：" + args[i]);
      String parameter = args[i].trim();
      switch (parameter) {
        case "ls":
          File dir=new File(currunntPath);
          String[] filelist=dir.list();
          for (String string : filelist)
          {
            System.out.printf("%-20s",string);
          }
          break;
        case "cp":
          break;
        case "rm":
          break;
        case "cat":
          if(!parameter.equals("")&&parameter!=null)
          {
            File file=new File(currunntPath+"\\"+parameter);
            if(file.exists()&&file.isFile())
            {
              try
              {
                Scanner scanner=new Scanner(file);
                scanner.useDelimiter("\r\n");
                String line=null;
                while(scanner.hasNext())
                {
                  line=scanner.next();
                  System.out.println(line);
                }
              } catch (FileNotFoundException e)
              {
                // TODO Auto-generated catch block
                e.printStackTrace();
              }
            }
          }
          break;
      }
    }
  }
}
