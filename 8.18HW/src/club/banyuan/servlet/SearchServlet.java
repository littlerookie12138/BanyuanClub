package club.banyuan.servlet;

import club.banyuan.pojo.Product;
import club.banyuan.service.ProductService;
import club.banyuan.service.impl.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "SearchServlet",urlPatterns = "/search.do")
public class SearchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyWords = request.getParameter("keyWords");
        ProductService productService = new ProductServiceImpl();
        try {
            List<Product> productList = productService.getProductByKeyWords(keyWords);
            System.out.println(productList.size());
            request.setAttribute("productList",productList);
            request.getRequestDispatcher("searchList.jsp").forward(request,response);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
