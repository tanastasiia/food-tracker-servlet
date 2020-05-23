<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<%--<c:if test="${not empty param.lang}">
    <fmt:setLocale value="${param.lang}" scope="session"/>
</c:if>--%>

<fmt:setLocale value="${lang}" scope="session"/>

<fmt:setBundle basename="messages"/>

<nav aria-label="breadcrumb">
    <ol class="breadcrumb">
        <li class="breadcrumb-item">
            <a href="${pageContext.request.contextPath}/api/home">
                <fmt:message key="messages.menu.home"/>
            </a>
        </li>
        <li class="breadcrumb-item">
            <a href="${pageContext.request.contextPath}/api/account">
                <fmt:message key="messages.menu.account"/>
            </a>
        </li>
        <li class="breadcrumb-item">
            <a href="${pageContext.request.contextPath}/api/statistics">
                <fmt:message key="messages.menu.statistics"/>
            </a>
        </li>
        <li class="breadcrumb-item">
            <a href="${pageContext.request.contextPath}/api/logout">
                <fmt:message key="messages.menu.logout"/>
            </a>
        </li>

        <c:if test="${isAdmin}">
            <li class="breadcrumb-item">
                <a href="${pageContext.request.contextPath}/api/admin">
                    <fmt:message key="messages.menu.admin"/>
                </a>
            </li>
        </c:if>

        <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}?lang=en">English</a></li>
        <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}?lang=ua">Українська</a></li>
    </ol>
</nav>