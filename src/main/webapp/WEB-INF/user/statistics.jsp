<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>


<%--<c:if test="${not empty param.lang}">
    <fmt:setLocale value="${param.lang}" scope="session"/>
</c:if>--%>


<fmt:setBundle basename="messages"/>

<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <title><fmt:message key="messages.menu.statistics"/></title>
</head>
<body>

<c:import url="/WEB-INF/user/menu.jsp" charEncoding="utf-8"/>


<div class="container" style="margin-top: 60px">


    <c:import url="/WEB-INF/user/page_pieces/food_elements_sum.jsp" charEncoding="utf-8" />



    <h2 class="page-header">
        <fmt:message key="messages.title.statistics.dates"/>
    </h2>
    <div id="tabs">
        <ul class="nav nav-tabs" id="myTab" role="tablist">
            <li class="nav-item">
                <a class="nav-link" id="home-tab" data-toggle="tab" href="#tabs-1" role="tab" aria-controls="home"
                   aria-selected="true">Today</a>
            </li>

            <li class="nav-item">
                <a class="nav-link" id="contact-tab" data-toggle="tab" href="#tabs-2" role="tab" aria-controls="contact"
                   aria-selected="false">All time</a>
            </li>
        </ul>


        <div id="tabs-1">

            <table class="table">
                <thead class="thead-light">
                <tr>
                    <th scope="col" >
                        <fmt:message key="messages.enter.food.name"/>
                    </th>
                    <th scope="col">
                        <fmt:message key="messages.enter.food.amount"/>
                    </th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="meal" items="${todaysMeals}" varStatus="status">
                    <tr>
                        <td>${meal.foodName}</td>
                        <td>${meal.amount}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

        <%--    <nav aria-label="Page navigation example">
                <ul class="pagination">
                    <li class="page-item">
                        <a class="page-link" href="" aria-label="Previous"
                           ng-click="todaysFoodPage=(todaysFoodPage>0)?todaysFoodPage-1:todaysFoodPage;getTodaysFood()">
                            <span aria-hidden="true">&laquo;</span>
                            <span class="sr-only">Previous</span>
                        </a>
                    </li>
                    <li class="page-item">
                        <a class="page-link" href=""
                                             ng-click="todaysFoodPage=0;getTodaysFood()">
                        <fmt:message key="messages.button.first"/>
                    </a>
                    </li>
                    <li class="page-item">
                        <a class="page-link" href=""
                                             ng-click="todaysFoodPage=todaysFoodPageMax;getTodaysFood()">
                            <fmt:message key="messages.button.last"/>
                        </a>
                    </li>
                    <li class="page-item">
                        <a class="page-link" href="" aria-label="Next"
                           ng-click="todaysFoodPage=(todaysFoodPage<todaysFoodPageMax)?todaysFoodPage+1:todaysFoodPage;getTodaysFood()">
                            <span aria-hidden="true">&raquo;</span>
                            <span class="sr-only">Next</span>
                        </a>
                    </li>
                </ul>
            </nav>--%>

        </div>

        <div id="tabs-2">

            <table class="table">
                <thead class="thead-light">
                <tr>
                    <th scope="col">
                        <fmt:message key="messages.enter.food.name"/>
                    </th>
                    <th scope="col">
                        <fmt:message key="messages.enter.food.amount"/>
                    </th>
                    <th scope="col">
                        <fmt:message key="messages.enter.food.date"/>
                    </th>
                    <th scope="col">
                        <fmt:message key="messages.enter.food.time"/>
                    </th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="meal" items="${allMeals}" varStatus="status">
                    <tr>
                        <td>${meal.foodName}</td>
                        <td>${meal.amount}</td>
                        <td>${meal.date}</td>
                        <td>${meal.time}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>


         <%--   <nav aria-label="Page navigation example">
                <ul class="pagination">
                    <li class="page-item">
                        <a class="page-link" href="" aria-label="Previous"
                           ng-click="foodPage=(foodPage>0)?foodPage-1:foodPage;getAllFood()">
                            <span aria-hidden="true">&laquo;</span>
                            <span class="sr-only">Previous</span>
                        </a>
                    </li>
                    <li class="page-item">
                        <a class="page-link" href=""
                                             ng-click="foodPage=0;getAllFood()">
                            <fmt:message key="messages.button.first"/>
                        </a>
                    </li>
                    <li class="page-item">
                        <a class="page-link" href=""
                                             ng-click="foodPage=foodPageMax;getAllFood()">
                            <fmt:message key="messages.button.last"/>
                        </a>
                    </li>
                    <li class="page-item">
                        <a class="page-link" href="" aria-label="Next"
                           ng-click="foodPage=(foodPage<foodPageMax)?foodPage+1:foodPage;getAllFood()">
                            <span aria-hidden="true">&raquo;</span>
                            <span class="sr-only">Next</span>
                        </a>
                    </li>
                </ul>
            </nav>--%>

        </div>

    </div>


</div>
</body>
</html>
