package club.banyuan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginServlet", urlPatterns = "/login.do")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 修改字节码
        response.setCharacterEncoding("utf-8");
//        response.setContentType();


        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String pwd = request.getParameter("pwd");

        PrintWriter writer = response.getWriter();
        writer.println("<html><head><meta charset=\"utf-8\"></head><body><p>");
        writer.println("username:" + username);
        writer.println("pwd:" + pwd);
        writer.println("</p></body></html>");

        writer.flush();
        writer.close();
    }
}
