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


<nav aria-label="breadcrumb">
    <ol class="breadcrumb">
        <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}?lang=en">English</a></li>
        <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}?lang=ua">Українська</a></li>
    </ol>
</nav>

<div class="container" style="margin-top: 60px">
    <div class="row">
        <div class="col-md-8 col-md-offset-2">

            <h1 class="page-header"><fmt:message key="messages.title.login"/></h1>

            <form action="${pageContext.request.contextPath}/foodtracker/login" method="POST"
                  style="margin-bottom: 30px"
                  name="form"
                  autocomplete="off">

                <c:if test="${not empty param.error}">
                    <div class="alert alert-danger"><p><fmt:message
                            key="messages.error.invalid.username.or.password"/></p>
                    </div>
                </c:if>
                <c:if test="${not empty param.logout}">
                    <div class="alert alert-info"><p><fmt:message key="messages.alert.loged.out"/></p></div>
                </c:if>

                <c:if test="${authError}">
                    <div class="alert alert-info"><p>
                        <fmt:message key="messages.error.invalid.username.or.password"/></p></div>
                </c:if>

                <div class="form-group">
                    <label for="username"><fmt:message key="messages.enter.username"/></label>:
                    <input type="text"
                           id="username"
                           name="username"
                           class="form-control"
                           autofocus="autofocus"
                           placeholder="Username"/>
                </div>

                <div class="form-group">
                    <label for="password"> <fmt:message key="messages.enter.password"/></label>:
                    <input type="password"
                           id="password"
                           name="password"
                           class="form-control"
                           placeholder="Password"/>
                </div>

                <button type="submit" class="btn btn-primary" style="margin-top:30px">
                    <fmt:message key="messages.button.submit"/>
                </button>

                <div class="form-group">
						<span>
                            <p><fmt:message key="messages.text.new.user"/></p>
                            <a href="${pageContext.request.contextPath}/foodtracker/registration">
                                <fmt:message key="messages.link.register"/>
                            </a>
                        </span>
                </div>

            </form>
        </div>
    </div>
</div>
</body>
</html>