import java.io.*;

public class Decode {

  public static void main(String[] args) throws IOException {
    BufferedReader inStream = null;
    BufferedWriter outStream = null;

    String inputFilePath = args[0];
    String outputFilePath = args[1];

    System.out.println("输入文件：" + inputFilePath);
    System.out.println("输出文件：" + outputFilePath);
    // TODO
    //完成此部分代码，调用 caesarDecode 对传入的inputFilePath文件进行解密
    //将解密后的文本输出到 outputFilePath 文件中
    //尝试将alice.code进行解密
    try {
      inStream = new BufferedReader(new FileReader(inputFilePath));
      outStream = new BufferedWriter(new FileWriter(outputFilePath));

//      String s = inStream.readLine();
//      while (s != null) {
//
//      }

    } catch (IOException e) {
      e.printStackTrace();
    }

    int num = 0;
    char ch;
    String result = "";

    while ((num = inStream.read()) != -1) {
      ch = (char) num;
      outStream.write(caesarDecode(ch));
    }
    outStream.close();
    System.out.println("解密成功！");
  }

  public static char caesarDecode(char ch) {
    if (Character.isUpperCase(ch)) {
      return (char) ((ch - Encode.FIRST_UPPER + Encode.NUM_CHARS - Encode.OFFSET) % Encode.NUM_CHARS
          + Encode.FIRST_UPPER);
    } else if (Character.isLowerCase(ch)) {
      return (char) ((ch - Encode.FIRST_LOWER + Encode.NUM_CHARS - Encode.OFFSET) % Encode.NUM_CHARS
          + Encode.FIRST_LOWER);
    } else {
      return ch;
    }
  }

}

