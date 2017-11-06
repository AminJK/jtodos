<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<h2>Todos!</h2>
<form action="index.jsp" method="post">
    <input type="text" name="todo">&nbsp;
    <input type="submit" value="ok">
</form>
<%
    request.setCharacterEncoding("utf-8");
    if(request.getParameter("todo")!=null){
        out.print("Todo:"+request.getParameter("todo"));
    }
%>

</body>
</html>
