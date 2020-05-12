<nav aria-label="breadcrumb">
    <ol class="breadcrumb">
        <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}?lang=en">English</a></li>
        <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}?lang=ua">Українська</a></li>
    </ol>
</nav>

<nav aria-label="breadcrumb">
    <ol class="breadcrumb">
        <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/api/home" >
            <p><fmt:message key="messages.menu.home"/></p>
        </a></li>
        <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/api/account" >
            <p><fmt:message key="messages.menu.account"/></p>
        </a></li>
        <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/api/statistics" >
            <p><fmt:message key="messages.menu.statistics"/></p>
        </a></li>
        <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/api/logout" >
            <p><fmt:message key="messages.menu.logout"/></p>
        </a></li>

        <c:if test="${isAdmin}">
            <a href="${pageContext.request.contextPath}/api/admin">
                <p><fmt:message key="messages.menu.admin"/></p></a>
        </c:if>

        <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}?lang=en">English</a></li>
        <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}?lang=ua">Українська</a></li>
    </ol>
</nav>