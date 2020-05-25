<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>


<fmt:setLocale value="${lang}" scope="session"/>
<fmt:setBundle basename="messages"/>

<div id="tabs-2">



    <table class="table" >
        <thead class="thead-light">
        <tr>
            <th scope="col">
                <fmt:message key="messages.enter.food.name"/>
            </th>
            <th scope="col">
                <fmt:message key="messages.enter.food.name.ua"/>
            </th>
            <th scope="col">
                <fmt:message key="messages.enter.food.carbs"/>
            </th>
            <th scope="col">
                <fmt:message key="messages.enter.food.protein"/>
            </th>
            <th scope="col">
                <fmt:message key="messages.enter.food.fat"/>
            </th>
            <th scope="col">
                <fmt:message key="messages.enter.food.calories"/>
            </th>
            <th scope="col">
                <fmt:message key="messages.enter.username"/>
            </th>
            <th scope="col">
                <fmt:message key="messages.global"/>
            </th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="foodInfo" items="${foods}" varStatus="status">
            <tr>
                <td>${foodInfo.food.name}</td>
                <td>${foodInfo.food.nameUa}</td>
                <td>${foodInfo.food.carbs}</td>
                <td>${foodInfo.food.protein}</td>
                <td>${foodInfo.food.fat}</td>
                <td>${foodInfo.food.calories}</td>

                <td>${foodInfo.user.username}</td>
                <td>${foodInfo.isGlobal}</td>
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
                    <a class="page-link" href="${pageContext.request.contextPath}\api\admin?tab=2&page=${param.page-1}"
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
            <a class="page-link" href="${pageContext.request.contextPath}\api\admin?tab=2&page=1">
                <fmt:message key="messages.button.first"/>
            </a>
        </li>
        <li class="page-item">
            <a class="page-link" href="${pageContext.request.contextPath}\api\admin?tab=2&page=${numOfPages}">
                <fmt:message key="messages.button.last"/>
            </a>
        </li>

        <c:choose>

            <c:when test="${not empty param.page}">
                <c:choose>
                    <c:when test="${param.page < numOfPages}">
                        <li class="page-item">
                            <a class="page-link" href="${pageContext.request.contextPath}\api\admin?tab=2&page=${param.page+1}"
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
                <c:choose>
                    <c:when test="${numOfPages>1}">
                        <li class="page-item">
                            <a class="page-link" href="${pageContext.request.contextPath}\api\admin?tab=2&page=${2}"
                               aria-label="Next">
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
            </c:otherwise>

        </c:choose>

    </ul>
</nav>
