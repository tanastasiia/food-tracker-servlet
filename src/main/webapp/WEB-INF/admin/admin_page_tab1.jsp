<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>


<fmt:setLocale value="${lang}" scope="session"/>
<fmt:setBundle basename="messages"/>


<div id="tabs-1">

    <table class="table">
        <thead class="thead-light">
        <tr>
            <th scope="col">
                <fmt:message key="messages.enter.username"/>
            </th>
            <th scope="col">
                <fmt:message key="messages.enter.first.name"/>
            </th>
            <th scope="col">
                <fmt:message key="messages.enter.last.name"/>
            </th>
            <th scope="col">
                <fmt:message key="messages.enter.height"/>
            </th>
            <th scope="col">
                <fmt:message key="messages.enter.weight"/>
            </th>
            <th scope="col">
                <fmt:message key="messages.enter.age"/>
            </th>
            <th scope="col">
                <fmt:message key="messages.enter.activity"/>
            </th>

            <th scope="col">
                <fmt:message key="messages.role"/>
            </th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${users}" varStatus="status">
            <tr>
                <td>${user.username}</td>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
                <td>${user.height}</td>
                <td>${user.weight}</td>
                <td>${user.age}</td>
                <td><fmt:message key="${user.activityLevel}"/></td>
                <td>
                    <div><fmt:message key="${user.role}"/></div>
                    <div>
                        <button type="submit" class="btn btn-success"  onclick="location.href='/foodtracker/change_role?id=${user.id}&role=${user.role}'">
                            <fmt:message key="messages.button.change.role"/>
                        </button>
                    </div>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</div>


<nav aria-label="Page navigation">
    <ul class="pagination">
        <c:choose>

            <%--previous--%>
            <c:when test="${not empty param.page && param.page>1}">
                <li class="page-item">
                    <a class="page-link" href="${pageContext.request.contextPath}\foodtracker\admin?tab=1&page=${param.page-1}"
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

        <%--first and last--%>
        <li class="page-item">
            <a class="page-link" href="${pageContext.request.contextPath}\foodtracker\admin?tab=1&page=1">
                <fmt:message key="messages.button.first"/>
            </a>
        </li>
        <li class="page-item">
            <a class="page-link" href="${pageContext.request.contextPath}\foodtracker\admin?tab=1&page=${numOfPages}">
                <fmt:message key="messages.button.last"/>
            </a>
        </li>

        <%--next--%>
        <c:choose>
            <c:when test="${not empty param.page}">
                <c:choose>
                    <c:when test="${param.page < numOfPages}">
                        <li class="page-item">
                            <a class="page-link"
                               href="${pageContext.request.contextPath}\foodtracker\admin?tab=1&page=${param.page+1}"
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
                            <a class="page-link" href="${pageContext.request.contextPath}\foodtracker\admin?tab=1&page=${2}"
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
