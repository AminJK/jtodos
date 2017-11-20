<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>jTodos</title>
    <link href="https://cdn.bootcss.com/semantic-ui/2.2.13/semantic.min.css" rel="stylesheet">
</head>
<body>
<div class="ui two column centered relaxed  grid">
    <div class="column row"></div>
    <div class="column ">
        <h2 class="ui huge header center violet aligned">jTodos!</h2>
        <div class="ui raised segment">
            <form action="/todos/${userid}" method="post"  class="ui fluid action input">
                <input type="text" name="todo" placeholder="请输入一个备忘录项目"/>
                <input type="hidden" name="user" value="${userid}"/>
                <button type="submit" class="ui button">OK</button>
            </form>
            <div class="ui aligned huge selection divided list">
                <c:if test="${fn:length(todos)>0}">
                    <c:forEach var="todo" varStatus="status" items="${todos}">
                        <div class="item">
                            <span class="right floated content"><fmt:formatDate value="${todo.createTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/></span>
                            <span class="left floated header">${status.index+1}.${todo.item}</span>
                        </div>
                    </c:forEach>
                </c:if>
            </div>
        </div>
    </div>
</div>
<script src="https://cdn.bootcss.com/semantic-ui/2.2.13/semantic.min.js"></script>
</body>
</html>
