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
    <title><fmt:message key="messages.menu.account"/></title>
</head>
<body>

<c:import url="/WEB-INF/user/menu.jsp" charEncoding="utf-8" />


<div class="container" style="margin-top: 60px" ng-init="getUser()">
    <p>
        Welcome <span></span>
    </p>

    <table class="table" ng-model="user">
        <tbody>
        <tr>
            <th scope="col" >
                <fmt:message key="messages.enter.name"/>
            </th>
            <td> ${user.firstName}</td>
        </tr>
        <tr>
            <th >
                <fmt:message key="messages.enter.last.name"/>
            </th>
            <td>${user.lastName}</td>
        </tr>
        <tr>
            <th >
                <fmt:message key="messages.enter.username"/>
            </th>
            <td>${user.username}</td>
        </tr>
        <tr>
            <th >
                <fmt:message key="messages.enter.height"/>
            </th>
            <td>${user.height}</td>
        </tr>
        <tr>
            <th >
                <fmt:message key="messages.enter.weight"/>
            </th>
            <td>${user.weight}</td>
        </tr>
        <tr>
            <th >
                <fmt:message key="messages.enter.age"/>
            </th>
            <td>${user.age}</td>
        </tr>
        <tr>
            <th>
                <fmt:message key="messages.enter.activity"/>
            </th>
            <td><fmt:message key="${user.activityLevel}"/></td>
        </tr>
        <tr>
            <th>
                <fmt:message key="messages.enter.gender"/>
            </th>
            <td>${user.gender}</td>
        </tr>
        </tbody>
    </table>

    <!--<button class="btn btn-primary" style="margin-top:30px"
            onclick="location.href='/account/change'" type="button"
            th:text=>
    </button>-->

    <button class="btn btn-primary" style="margin-top:30px"
            onclick="location.href='/api/change_account'" type="button">
        <fmt:message key="messages.button.change.account"/>
    </button>

    <button class="btn btn-primary" style="margin-top:30px"
            onclick="location.href='/account/change/password'" type="button">
        <fmt:message key="messages.button.change.password"/>
    </button>

</body>
</html>