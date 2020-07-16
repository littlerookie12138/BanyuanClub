public class Test {
    public static void main(String[] args) {
        String filePath = HttpServer.class.getClassLoader().getResource("") + "pages" + "/Welcome.html";
        System.out.println(filePath);
    }
}
