<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>


<c:if test="${not empty param.lang}">
    <fmt:setLocale value="${param.lang}" scope="session"/>
</c:if>
<fmt:setBundle basename="messages"/>

<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <title><fmt:message key="messages.title.login"/></title>
</head>
<body>

<%--<c:import url="/WEB-INF/user/menu.jsp" charEncoding="utf-8" />--%>


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
            <a href="${pageContext.request.contextPath}/api/statistics" >
                <fmt:message key="messages.menu.statistics"/>
            </a>
        </li>
        <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/api/logout">
            <fmt:message key="messages.menu.logout"/>
        </a></li>

        <c:if test="${isAdmin}">
            <a href="${pageContext.request.contextPath}/api/admin">
                <fmt:message key="messages.menu.admin"/>
            </a>
        </c:if>

        <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}?lang=en">English</a></li>
        <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}?lang=ua">Українська</a></li>
    </ol>
</nav>


<div class="container" style="margin-top: 60px">

    <div class="row">
        <div class="col-md-8 col-md-offset-2">

            <h2 class="page-header">
                <fmt:message key="messages.title.food.consumed"/>
            </h2>

            <%--<div ng-show="showAddUserSuccess">
                <div class="alert alert-info">
                    <p><fmt:message key="messages.alert.food.added"/></p>
                </div>
            </div>--%>

            <%--<div ng-show="showAddUserError">
                <div class="alert alert-danger"><p th:text="#{messages.error.no.such.food.in.db}"></p>
                </div>
            </div>--%>

            <%--<div ng-if="userStat.calories > userStat.caloriesNorm">
                <div class="alert alert-warning">
                    <p th:text="#{messages.alert.norm.exceeded}"></p>
                </div>
            </div>--%>


            <div>
                <div class="alert alert-light">
                    <p><strong><fmt:message key="messages.todays.calories"/></strong>
                        ${userStat.calories}</p>
                    <p><strong><fmt:message key="messages.calories.norm"/></strong>
                        ${userStat.caloriesNorm}</p>
                </div>
            </div>

            <form action="${pageContext.request.contextPath}/api/add_meal"
                  method="post" style="margin-bottom: 30px"
                  name="form" autocomplete="off">


                <%--<div th:if="${param.success}">
                    <div class="alert alert-info"><p th:text="#{messages.alert.food.added}"></p></div>
                </div>

                <div th:if="${param.error}">
                    <div class="alert alert-danger"><p th:text="#{messages.error.no.such.food.in.db}"></p>
                    </div>
                </div>--%>

                <div class="form-group">
                    <label for="food-name">
                        <fmt:message key="messages.enter.food.name"/>
                    </label>
                    <input class="form-control form-control-lg" id="food-name" type="text" placeholder=""
                           name="addMealFood" required>
                </div>

                <div class="form-group">
                    <label for="food-amount">
                        <fmt:message key="messages.enter.food.amount"/>
                    </label>
                    <input class="form-control form-control-lg" id="food-amount" type="number" placeholder=""
                           name="addMealAmount" required>
                </div>

                <button type="submit" class="btn btn-primary" style="margin-top:30px">
                    <p><fmt:message key="messages.button.submit"/></p>
                </button>
            </form>

        </div>
    </div>


</div>

</body>
</html>

