<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${lang}" scope="session"/>
<fmt:setBundle basename="messages"/>

<div id="tabs-3">

    <table class="table">
        <thead class="thead-light">
        <tr>
            <th scope="col">
                <fmt:message key="messages.enter.username"/>
            </th>

            <th scope="col">
                    <fmt:message key="messages.enter.food.name"/>
            </th>

            <th scope="col">
                    <fmt:message key="messages.enter.food.name.ua"/>
            </th>
            <th scope="col">
                <fmt:message key="messages.text.amount"/>
            </th>
            <th scope="col" >
                <fmt:message key="messages.enter.food.date"/>
            </th>
            <th scope="col" >
                <fmt:message key="messages.enter.food.time"/>
            </th>
        </tr>
        </thead>
        <tbody>

        <c:forEach var="meal" items="${meals}" varStatus="status">
            <tr >
                <td>${meal.user.username}</td>
                <c:choose>
                    <c:when test="${not empty meal.food.name}">
                        <td>${meal.food.name}</td>
                    </c:when>
                    <c:otherwise>
                        <td><fmt:message key="messages.absent"/></td>
                    </c:otherwise>
                </c:choose>
                <c:choose>
                    <c:when test="${not empty meal.food.nameUa}">
                        <td>${meal.food.nameUa}</td>
                    </c:when>
                    <c:otherwise>
                        <td><fmt:message key="messages.absent"/></td>
                    </c:otherwise>
                </c:choose>
                <td>${meal.amount}</td>
                <td>${meal.dateTime.toLocalDate()}</td>
                <td>${meal.dateTime.toLocalTime()}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>



</div>





<nav aria-label="Page navigation">
    <ul class="pagination">
        <c:choose>

            <c:when test="${not empty param.page && param.page>1}">
                <li class="page-item">
                    <a class="page-link" href="${pageContext.request.contextPath}\foodtracker\admin?tab=3&page=${param.page-1}"
                       aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                        <span class="sr-only"><fmt:message key="masseges.previous"/></span>
                    </a>
                </li>
            </c:when>

            <c:otherwise>
                <li class="page-item disabled">
                    <a class="page-link" href="" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                        <span class="sr-only"><fmt:message key="masseges.previous"/></span>
                    </a>
                </li>
            </c:otherwise>

        </c:choose>


        <li class="page-item">
            <a class="page-link" href="${pageContext.request.contextPath}\foodtracker\admin?tab=3&page=1">
                <fmt:message key="messages.button.first"/>
            </a>
        </li>
        <li class="page-item">
            <a class="page-link" href="${pageContext.request.contextPath}\foodtracker\admin?tab=3&page=${numOfPages}">
                <fmt:message key="messages.button.last"/>
            </a>
        </li>

        <c:choose>

            <c:when test="${not empty param.page}">
                <c:choose>
                    <c:when test="${param.page < numOfPages}">
                        <li class="page-item">
                            <a class="page-link" href="${pageContext.request.contextPath}\foodtracker\admin?tab=3&page=${param.page+1}"
                               aria-label="Previous">
                                <span aria-hidden="true">&raquo;</span>
                                <span class="sr-only"><fmt:message key="masseges.next"/></span>
                            </a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item disabled">
                            <a class="page-link" href="" aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                                <span class="sr-only"><fmt:message key="masseges.next"/></span>
                            </a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </c:when>

            <c:otherwise>
                <li class="page-item">
                    <a class="page-link" href="${pageContext.request.contextPath}\foodtracker\admin?tab=3&page=${2}" aria-label="Previous">
                        <span aria-hidden="true">&raquo;</span>
                        <span class="sr-only"><fmt:message key="masseges.next"/></span>
                    </a>
                </li>
            </c:otherwise>

        </c:choose>

    </ul>
</nav>
