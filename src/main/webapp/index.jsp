<%@ page import="java.text.DateFormat" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>jTodos</title>
</head>
<body>

<h2>jTodos!</h2>
<form action="index.jsp" method="post">
    <input type="text" name="todo" placeholder="请输入一个备忘录项目">&nbsp;
    <input type="submit" value="ok">
</form>
<%
    request.setCharacterEncoding("utf-8");
    List<String> todos=(List<String>) application.getAttribute("todos");
    if(todos==null){
        todos=new ArrayList<>();
        application.setAttribute("todos",todos);
    }
    if(request.getParameter("todo")!=null){
        todos.add(request.getParameter("todo"));
    }
%>
    <ul>
        <c:forEach var="item" items="${todos}">
         <li>${item}</li>
        </c:forEach>
    </ul>
</body>
</html>
