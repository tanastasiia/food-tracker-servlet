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

                <c:if test="${formSuccess == 'mealAdded'}">
                    <div class="alert alert-info"><p>
                        <fmt:message key="messages.alert.food.added"/></p></div>
                </c:if>
                <c:if test="${formSuccess=='mealNotAdded'}">
                    <div class="alert alert-danger"><p>
                        <fmt:message key="messages.error.no.such.food.in.db"/></p></div>
                </c:if>


                <div class="form-group">
                    <label for="food-name">
                        <fmt:message key="messages.enter.food.name"/>
                    </label>
                    <input class="form-control form-control-lg" id="food-name" type="text" placeholder=""
                           name="foodName" required>
                </div>

                <div class="form-group">
                    <label for="food-amount">
                        <fmt:message key="messages.enter.food.amount"/>
                    </label>
                    <input class="form-control form-control-lg" id="food-amount" type="number" placeholder=""
                           name="amount" required>
                </div>

                <button type="submit" class="btn btn-primary" style="margin-top:30px">
                    <p><fmt:message key="messages.button.submit"/></p>
                </button>
            </form>

        </div>
    </div>


    <div class="row">
        <div class="col-md-8 col-md-offset-2">
            <h2 class="page-header" >
                <fmt:message key="messages.title.food.to.db"/>
            </h2>

            <c:if test="${formSuccess == 'foodAdded'}">
                <div class="alert alert-info"><p>
                    <fmt:message key="messages.alert.food.added"/></p></div>
            </c:if>
            <c:if test="${formSuccess=='foodNotAdded'}">
                <div class="alert alert-info"><p>
                    <fmt:message key="messages.error.such.food.in.db"/></p></div>
            </c:if>

            <form action="${pageContext.request.contextPath}/api/add_food" method="post" style="margin-bottom: 30px" id="addFoodForm"
                  autocomplete="off">

                <div class="form-group">
                    <label for="new-food-name">
                        <fmt:message key="messages.enter.food.name"/>
                    </label>
                    <input class="form-control form-control-lg" id="new-food-name" type="text"
                           placeholder="" name="name" >
                </div>

                <div class="form-group">
                    <label for="food-name-ua">
                        <fmt:message key="messages.enter.food.name.ua"/>
                    </label>
                    <input class="form-control form-control-lg" id="food-name-ua" type="text" placeholder=""
                           name="name_ua" required>
                </div>

                <div class="form-group">
                    <label for="carbs" >
                        <fmt:message key="messages.enter.food.carbs"/>
                    </label>
                    <input class="form-control form-control-lg" id="carbs" type="number" placeholder=""
                           name="carbs">
                </div>

                <div class="form-group">
                    <label for="protein" >
                        <fmt:message key="messages.enter.food.protein"/>
                    </label>
                    <input class="form-control form-control-lg" id="protein" type="number" placeholder=""
                           name="protein">
                </div>

                <div class="form-group">
                    <label for="fat" >
                        <fmt:message key="messages.enter.food.fat"/>
                    </label>
                    <input class="form-control form-control-lg" id="fat" type="number" placeholder=""
                           name="fat">
                </div>

                <div class="form-group">
                    <label for="calories" >
                        <fmt:message key="messages.enter.food.calories"/>
                    </label>
                    <input class="form-control form-control-lg" id="calories" type="number" placeholder=""
                           name="calories">
                </div>

                <button type="submit" class="btn btn-primary"
                        style="margin-top:30px" >
                    <fmt:message key="messages.button.submit"/>
                </button>



            </form>

        </div>
    </div>

</div>

</body>
</html>

