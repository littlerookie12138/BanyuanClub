package club.banyuan;

import club.banyuan.Service.UserService;
import club.banyuan.Service.impl.UserServiceImpl;
import club.banyuan.pojo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "RegisterServlet", urlPatterns = "/register.do")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 修改字节码
        response.setCharacterEncoding("utf-8");
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String loginName = request.getParameter("loginName");
        String password = request.getParameter("pwd");
        String checkPwd = request.getParameter("confirmPwd");
        //if()
        String email = request.getParameter("email");
        String mobile = request.getParameter("phone");
        User user = new User();
        user.setLoginName(loginName);
        user.setPassword(password);
        user.setEmail(email);
        user.setMobile(mobile);

        UserService userService = new UserServiceImpl();
        try {
            User newUser = userService.register(user);
            response.sendRedirect("购物街原型/login.html");
        } catch (Exception throwables) {
            //throwables.printStackTrace();
            response.sendRedirect("购物街原型/regist.html");
        }

    }
}
