<%@ page import="club.banyuan.pojo.Product" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: edz
  Date: 2020/8/18
  Time: 11:01 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>查询结果</h1>
<%
    List<Product> productList = (List<Product>) request.getAttribute("productList");
    for(Product product : productList){
        out.println(product.getId()+"  "+product.getName()+"<br/>");
    }
%>
</body>
</html>
