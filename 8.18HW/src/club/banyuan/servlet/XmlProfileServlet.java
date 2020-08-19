package club.banyuan.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class XmlProfileServlet extends HttpServlet {

    public XmlProfileServlet(){
        System.out.println("XmlProfileServlet Constructor");
    }

    @Override
    public void init(){
        System.out.println("XmlProfileServlet init()");
    }

    @Override
    public void destroy(){
        System.out.println("XmlProfileServlet destroy");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("XmlProfileServlet");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
