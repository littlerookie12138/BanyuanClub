package club.banyuan.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "TestServlet",urlPatterns = "/test.do")
public class TestServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doPost");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        一个表单name对应多个value
        String[] fav = request.getParameterValues("fav");

        System.out.println("doGet");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>TestServlet  --- doGet()</h1>");
        for(String s : fav){
            out.println("<p>"+s+"</p>");
        }
        out.println("</body></html>");
        out.flush();
        out.close();
    }
}
