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
    <title><fmt:message key="messages.menu.admin"/></title>
</head>
<body>

<c:import url="/WEB-INF/user/menu.jsp" charEncoding="utf-8" />




    <div id="tabs" >
        <ul class="nav nav-tabs" id="myTab" role="tablist">
            <li class="nav-item" >
                <a class="nav-link" id="home-tab" data-toggle="tab" href="${pageContext.request.contextPath}\foodtracker\admin?tab=1" role="tab" aria-controls="home"
                   aria-selected="true" >
                    <fmt:message key="messages.users"/>
                </a>
            </li>

            <li class="nav-item">
                <a class="nav-link" id="contact-tab" data-toggle="tab" href="${pageContext.request.contextPath}\foodtracker\admin?tab=2" role="tab" aria-controls="contact"
                   aria-selected="false">
                    <fmt:message key="messages.food"/>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" id="profile-tab" data-toggle="tab" href="${pageContext.request.contextPath}\foodtracker\admin?tab=3" role="tab" aria-controls="profile"
                   aria-selected="false">
                    <fmt:message key="messages.users.food"/>
                </a>
            </li>
        </ul>


        <c:choose>
        <c:when test="${empty param.tab || param.tab==1}">

            <c:import url="/WEB-INF/admin/admin_page_tab1.jsp" charEncoding="utf-8" />

        </c:when>
        <c:when test="${param.tab==2}">

            <c:import url="/WEB-INF/admin/admin_page_tab2.jsp" charEncoding="utf-8" />
        </c:when>
        <c:otherwise>

            <c:import url="/WEB-INF/admin/admin_page_tab3.jsp" charEncoding="utf-8" />

        </c:otherwise>
        </c:choose>

    </div>




</nav>
</body>
</html>