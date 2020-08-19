package club.banyuan.servlet;

import club.banyuan.pojo.User;
import club.banyuan.service.UserService;
import club.banyuan.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "RegistServlet",urlPatterns = "/regist.do")
public class RegistServlet extends HttpServlet {
    // 处理Post方式提交的请求
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
// 处理Get方式提交请求
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String loginName = request.getParameter("loginName");
        String password = request.getParameter("password");
        String checkPwd = request.getParameter("checkPwd");
        //if()
        String email = request.getParameter("email");
        String mobile = request.getParameter("mobile");
        User user = new User();
        user.setLoginName(loginName);
        user.setPassword(password);
        user.setEmail(email);
        user.setMobile(mobile);

        UserService userService = new UserServiceImpl();
        try {
            User newUser = userService.register(user);
            response.sendRedirect("login.jsp");
        } catch (Exception throwables) {
            //throwables.printStackTrace();
            response.sendRedirect("regist.jsp");
        }

//        System.out.println(loginName+"  "+password+"   "+checkPwd);

//        PrintWriter out = response.getWriter();
//        out.println("<html><body>");
//        out.println("RegistServlet");
//        out.println("</body></html>");
//        out.flush();
//        out.close();
//        response.sendRedirect("c.html");
    }
}
