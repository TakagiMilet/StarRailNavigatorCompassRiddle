<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: 25428
  Date: 2023/5/11
  Time: 13:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>answer</title>
</head>
<body>
    <%!
        ArrayList<String> ans;
    %>
    <%
        ans = (ArrayList<String>) session.getAttribute("ans");
        for (String s:ans){
            System.out.println(s);
        }
    %>
    <c:forEach items="${ans}" var="li">
        ${li}<br>
    </c:forEach>
</body>
</html>
