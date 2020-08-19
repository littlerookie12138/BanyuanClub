package club.banyuan.servlet;

import club.banyuan.dao.impl.UserDaoImpl;
import club.banyuan.pojo.User;
import club.banyuan.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = "/login.do")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String loginName = request.getParameter("loginName");
        String password = request.getParameter("password");

        User user = new User();
        user.setLoginName(loginName);
        user.setPassword(password);

        UserServiceImpl userService = new UserServiceImpl();
        try {
            if (userService.login(user) != null) {
                request.setAttribute("loginUser", user);
                request.getRequestDispatcher("result.jsp").forward(request, response);
            } else {
                response.sendRedirect("login.html");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("login.html");
        }
    }
}
