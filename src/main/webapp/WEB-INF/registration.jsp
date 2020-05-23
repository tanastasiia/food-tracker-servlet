
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
    <title><fmt:message key="messages.title.login"/></title>
</head>
<body>


<nav aria-label="breadcrumb">
    <ol class="breadcrumb">
        <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}?lang=en">English</a></li>
        <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}?lang=ua">Українська</a></li>
    </ol>
</nav>


<div class="container">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <h1>
                <fmt:message key="messages.title.registration"/>
            </h1>

            <c:choose>
                <c:when test="${success}">
                    <div class="alert alert-info"><p><fmt:message key="messages.alert.register.success"/></p></div>
                </c:when>
                <c:when test="${success==false}">
                    <div class="alert alert-info"><p><fmt:message key="messages.error.username.exists"/></p></div>
                </c:when>
                <c:otherwise>
                </c:otherwise>
            </c:choose>


            <form action="${pageContext.request.contextPath}/api/registration" method="post">

                <div class="form-group">
                    <label for="firstName" class="control-label">
                        <fmt:message key="messages.enter.first.name"/>
                    </label>
                    <input id="firstName" class="form-control" type=text name="firstName" required/>
                </div>

                <div class="form-group">
                    <label for="lastName" class="control-label">
                        <fmt:message key="messages.enter.last.name"/>
                    </label>
                    <input id="lastName" class="form-control" type=text name="lastName" required/>
                </div>

                <div class="form-group">
                    <label for="username" class="control-label">
                        <fmt:message key="messages.enter.username"/>
                    </label>
                    <input id="username" class="form-control" type=text name="username" required/>
                </div>

                <div class="form-group">
                    <label for="height" class="control-label">
                        <fmt:message key="messages.enter.height"/>
                    </label>
                    <input id="height" class="form-control" type=number name="height" required/>
                </div>

                <div class="form-group">
                    <label for="weight" class="control-label">
                        <fmt:message key="messages.enter.weight"/>
                    </label>
                    <input id="weight" class="form-control" type=number name="weight" required/>

                </div>
                <div class="form-group">
                    <label for="age" class="control-label">
                        <fmt:message key="messages.enter.age"/>
                    </label>
                    <input id="age" class="form-control" type=number name="age" required/>

                </div>


                <label class="control-label">
                    <fmt:message key="messages.enter.activity"/>
                </label>
                <div class="form-check">
                    <input class="form-check-input" type="radio" id="radios1"
                           name="activityLevel" value="FIRST" checked>
                    <label class="form-check-label" for="radios1">
                        <fmt:message key="messages.enter.activity1"/>
                    </label>
                </div>
                <div class="form-check">
                    <input class="form-check-input" type="radio" id="radios2"
                           name="activityLevel" value="SECOND" checked>
                    <label class="form-check-label" for="radios2">
                        <fmt:message key="messages.enter.activity2"/>
                    </label>
                </div>
                <div class="form-check">
                    <input class="form-check-input" type="radio" id="radios3"
                           name="activityLevel" value="THIRD" checked>
                    <label class="form-check-label" for="radios3">
                        <fmt:message key="messages.enter.activity3"/>
                    </label>
                </div>
                <div class="form-check">
                    <input class="form-check-input" type="radio" id="radios4"
                           name="activityLevel" value="FORTH" checked>
                    <label class="form-check-label" for="radios4">
                        <fmt:message key="messages.enter.activity4"/>
                    </label>
                </div>
                <div class="form-check">
                    <input class="form-check-input" type="radio" id="radios5"
                           name="activityLevel" value="FIFTH" checked>
                    <label class="form-check-label" for="radios5">
                        <fmt:message key="messages.enter.activity5"/>
                    </label>
                </div>


                <label class="control-label">
                    <fmt:message key="messages.enter.gender"/>
                </label>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" id="inlineRadio1"
                           name="gender" value="MALE">
                    <label class="form-check-label" for="inlineRadio1">
                        <fmt:message key="messages.enter.male"/>
                    </label>
                </div>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" id="inlineRadio2"
                           name="gender" value="FEMALE">
                    <label class="form-check-label" for="inlineRadio2">
                        <fmt:message key="messages.enter.female"/>
                    </label>
                </div>


                <div class="form-group">
                    <label for="password" class="control-label">
                        <fmt:message key="messages.enter.password"/>
                    </label>
                    <input id="password" class="form-control" type="password" name="password" required/>

                </div>


                <button type="submit" class="btn btn-success">
                    <fmt:message key="messages.button.submit"/>
                </button>
                <div class="form-group">
						<span>
                            <p><fmt:message key="messages.text.already.registered"/></p>
                            <a href="${pageContext.request.contextPath}/api/login">
                                <fmt:message key="messages.link.login"/></a>
                        </span>
                </div>
            </form>
        </div>
    </div>
</div>

</body>
</html>