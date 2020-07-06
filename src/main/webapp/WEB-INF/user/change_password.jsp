<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${lang}" scope="session"/>
<fmt:setBundle basename="messages"/>

<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <title><fmt:message key="messages.title.login"/></title>
</head>
<body>

<c:import url="/WEB-INF/user/menu.jsp" charEncoding="utf-8"/>

<div class="container" style="margin-top: 60px">

        <c:if test="${success==true}">
            <div class="alert alert-info"><p><fmt:message key="messages.password.changed"/></p></div>
        </c:if>
        <c:if test="${not empty success and success==false}">
            <div class="alert alert-info"><p><fmt:message key="messages.password.invalid"/></p></div>
        </c:if>

    <form action="${pageContext.request.contextPath}/foodtracker/change_password" method="post" >

        <div class="form-group">
            <label for="password" class="control-label"><fmt:message key="messages.password.old"/></label>
            <input id="password" class="form-control" type="password" name="oldPassword"/>
        </div>

        <div class="form-group">
            <label for="new-password" class="control-label"><fmt:message key="messages.password.new"/></label>
            <input id="new-password" class="form-control" type="password" name="newPassword"/>
        </div>
        <button type="submit" class="btn btn-success"><fmt:message key="messages.button.submit"/></button>
    </form>


</div>
</body>
</html>