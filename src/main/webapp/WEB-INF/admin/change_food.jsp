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


<div class="container">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <h1>
                <fmt:message key="messages.title.change.account"/>
            </h1>

            <form action="${pageContext.request.contextPath}/foodtracker/admin/change_food?foodId=${foodInfo.food.id}"
                  method="post"
                  style="margin-bottom: 30px" id="addFoodForm"
                  autocomplete="off">
                <div class="form-group">
                    <label for="new-food-name">
                        <fmt:message key="messages.enter.food.name"/>
                    </label>
                    <input class="form-control form-control-lg" id="new-food-name" type="text"
                           placeholder="" name="name" value="${foodInfo.food.name}">
                    <c:if test="${not empty error_name}">
                        <div class="alert alert-danger">
                            <p><fmt:message key="${error_name}"/></p>
                        </div>
                    </c:if>
                </div>

                <div class="form-group">
                    <label for="food-name-ua">
                        <fmt:message key="messages.enter.food.name.ua"/>
                    </label>
                    <input class="form-control form-control-lg" id="food-name-ua" type="text" placeholder=""
                           name="nameUa" value="${foodInfo.food.nameUa}">
                    <c:if test="${not empty error_nameUa}">
                        <div class="alert alert-danger">
                            <p><fmt:message key="${error_nameUa}"/></p>
                        </div>
                    </c:if>
                </div>

                <div class="form-group">
                    <label for="carbs">
                        <fmt:message key="messages.enter.food.carbs"/>
                    </label>
                    <input class="form-control form-control-lg" id="carbs" type="number" step="0.1" placeholder=""
                           name="carbs" value="${foodInfo.food.carbs/1000.}">
                </div>
                <c:if test="${not empty error_carbs}">
                    <div class="alert alert-danger"><p>
                        <fmt:message key="${error_carbs}"/></p>
                    </div>
                </c:if>

                <div class="form-group">
                    <label for="protein">
                        <fmt:message key="messages.enter.food.protein"/>
                    </label>
                    <input class="form-control form-control-lg" id="protein" type="number" step="0.1" placeholder=""
                           name="protein" value="${foodInfo.food.protein/1000.}">
                </div>
                <c:if test="${not empty error_protein}">
                    <div class="alert alert-danger"><p>
                        <fmt:message key="${error_protein}"/></p>
                    </div>
                </c:if>

                <div class="form-group">
                    <label for="fat">
                        <fmt:message key="messages.enter.food.fat"/>
                    </label>
                    <input class="form-control form-control-lg" id="fat" type="number" step="0.1" placeholder=""
                           name="fat" value="${foodInfo.food.fat/1000.}">
                </div>
                <c:if test="${not empty error_fat}">
                    <div class="alert alert-danger"><p>
                        <fmt:message key="${error_fat}"/></p>
                    </div>
                </c:if>

                <div class="form-group">
                    <label for="calories">
                        <fmt:message key="messages.enter.food.calories"/>
                    </label>
                    <input class="form-control form-control-lg" id="calories" type="number" placeholder=""
                           name="calories" value="${foodInfo.food.calories}">
                </div>
                <c:if test="${not empty error_calories}">
                    <div class="alert alert-danger">
                        <p><fmt:message key="${error_calories}"/></p>
                    </div>
                </c:if>

                <div class="form-check">
                    <input type="checkbox" class="form-check-input" id="exampleCheck1" name="isGlobal"
                           value="${foodInfo.isGlobal}">
                    <label class="form-check-label" for="exampleCheck1">
                        <fmt:message key="messages.enter.is.global"/>
                    </label>
                </div>

                <button type="submit" class="btn btn-primary"
                        style="margin-top:30px">
                    <fmt:message key="messages.button.submit"/>
                </button>

            </form>
        </div>
    </div>
</div>

</body>
</html>