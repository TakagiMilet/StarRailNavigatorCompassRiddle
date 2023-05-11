<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
    <%
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
    %>
    <form action="HelloServlet" method="post" target="_self">
        初始刻度:(9点方向为0度，12点方向为90度，只需输入数字)<br>
        外圈:<input type="text" name="initBigScale"><br>
        中圈:<input type="text" name="initMidScale"><br>
        内圈:<input type="text" name="initSmallScale"><br>
        旋转刻度数:（顺时针为正，逆时针为负）<br>
        外圈:<input type="text" name="bigMoveScale"><br>
        中圈:<input type="text" name="midMoveScale"><br>
        内圈:<input type="text" name="smallMoveScale"><br>
        控制:（顺序不固定）<br>
        控制1:
        <select name="controlSelect1">
            <option value="外">外</option>
            <option value="中">中</option>
            <option value="内">内</option>
            <option value="外中">外中</option>
            <option value="外内">外内</option>
            <option value="中内">中内</option>
        </select>
        控制2:
        <select name="controlSelect2">
            <option value="外">外</option>
            <option value="中">中</option>
            <option value="内">内</option>
            <option value="外中">外中</option>
            <option value="外内">外内</option>
            <option value="中内">中内</option>
        </select>
        控制3:
        <select name="controlSelect3">
            <option value="外">外</option>
            <option value="中">中</option>
            <option value="内">内</option>
            <option value="外中">外中</option>
            <option value="外内">外内</option>
            <option value="中内">中内</option>
        </select>
        <input type="submit" name="提交">
    </form>
</body>
</html>