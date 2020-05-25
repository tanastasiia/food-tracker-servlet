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
    <title><fmt:message key="messages.menu.home"/></title>
</head>
<body>

<c:import url="/WEB-INF/user/menu.jsp" charEncoding="utf-8" />


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
                        ${todaysCalories}</p>
                    <p><strong><fmt:message key="messages.calories.norm"/></strong>
                        ${caloriesNorm}</p>
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
                <c:if test="${isAdmin}">
                    <div class="form-group">
                        <label for="food-name-ua">
                            <fmt:message key="messages.enter.food.name.ua"/>
                        </label>
                        <input class="form-control form-control-lg" id="food-name-ua" type="text" placeholder=""
                               name="nameUa" required>
                    </div>
                </c:if>


                <div class="form-group">
                    <label for="carbs" >
                        <fmt:message key="messages.enter.food.carbs"/>
                    </label>
                    <input class="form-control form-control-lg" id="carbs" type="number" step="0.1" placeholder=""
                           name="carbs">
                </div>

                <div class="form-group">
                    <label for="protein" >
                        <fmt:message key="messages.enter.food.protein"/>
                    </label>
                    <input class="form-control form-control-lg" id="protein" type="number" step="0.1" placeholder=""
                           name="protein">
                </div>

                <div class="form-group">
                    <label for="fat" >
                        <fmt:message key="messages.enter.food.fat"/>
                    </label>
                    <input class="form-control form-control-lg" id="fat" type="number"step="0.1"  placeholder=""
                           name="fat">
                </div>

                <div class="form-group">
                    <label for="calories" >
                        <fmt:message key="messages.enter.food.calories"/>
                    </label>
                    <input class="form-control form-control-lg" id="calories" type="number" placeholder=""
                           name="calories">
                </div>
                <c:if test="${isAdmin}">
                <div class="form-check">
                    <input type="checkbox" class="form-check-input" id="exampleCheck1" name="isGlobal">
                    <label class="form-check-label" for="exampleCheck1"><fmt:message key="messages.enter.is.global"/></label>
                </div>
                </c:if>

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

