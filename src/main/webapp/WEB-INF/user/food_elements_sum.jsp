<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<%--<c:if test="${not empty param.lang}">
    <fmt:setLocale value="${param.lang}" scope="session"/>
</c:if>--%>

<fmt:setLocale value="${lang}" scope="session"/>
<fmt:setBundle basename="messages"/>


<h2 class="page-header">
    <fmt:message key="messages.title.statistics.counting"/>
</h2>
<table class="table" >
    <thead class="thead-light" >
    <tr>
        <th scope="col">
            <fmt:message key="messages.text.element"/>
        </th>
        <th scope="col" >
            <fmt:message key="messages.text.amount"/>
        </th>

    </tr>
    </thead>
    <tbody>
    <tr>
        <td >
            <fmt:message key="messages.enter.food.carbs"/>
        </td>
        <td>${userStat.carbs}</td>
    </tr>
    <tr>
        <td>
            <fmt:message key="messages.enter.food.protein"/>
        </td>
        <td>${userStat.protein}</td>
    </tr>
    <tr>
        <td>
            <fmt:message key="messages.enter.food.fat"/>
        </td>
        <td>${userStat.fat}</td>
    </tr>
    <tr>
        <td>
            <fmt:message key="messages.enter.food.calories"/>
        </td>
        <td>${userStat.calories}</td>
    </tr>
    <tr>
        <td>
            <fmt:message key="messages.calories.norm"/>
        </td>
        <td>${caloriesNorm}</td>
    </tr>
    </tbody>
</table>