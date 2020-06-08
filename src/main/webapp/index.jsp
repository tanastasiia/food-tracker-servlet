<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<%--<c:if test="${not empty param.lang}">
    <fmt:setLocale value="${param.lang}" scope="session"/>
</c:if>--%>
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


<nav aria-label="breadcrumb">
    <ol class="breadcrumb">
        <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}?lang=en">English</a></li>
        <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}?lang=ua">Українська</a></li>
    </ol>
</nav>

<div class="container" style="margin-top: 60px">
    <div class="row">
        <div class="col-md-8 col-md-offset-2">

            <h1 class="page-header"><fmt:message key="messages.title.food.tracker"/></h1>


            <div class="form-group">
                <div class="form-group">
						<span>
                            <a href="${pageContext.request.contextPath}/foodtracker/login">
                                <fmt:message key="messages.link.login"/></a>
                        </span>
                </div>

                <span>

                            <a href="${pageContext.request.contextPath}/foodtracker/registration">
                                <fmt:message key="messages.link.register"/></a>
                        </span>
            </div>
        </div>
    </div>
</div>
</body>
</html>