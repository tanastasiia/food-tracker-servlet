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
            <c:if test="${not empty user_input_error}">
                <div class="alert alert-danger"><p>
                    <fmt:message key="${user_input_error}"/></p>
                </div>
            </c:if>


            <form action="${pageContext.request.contextPath}/foodtracker/change_account" method="post">

                <div class="form-group">
                    <label for="username" class="control-label">
                        <fmt:message key="messages.enter.username"/>
                    </label>
                    <input id="username" class="form-control" type=text name="username" value="${user.username}"  required/>
                    <c:if test="${not empty error_username}">
                        <div class="alert alert-danger"><p>
                            <fmt:message key="${error_username}"/></p>
                        </div>
                    </c:if>
                </div>

                <div class="form-group">
                    <label for="firstName" class="control-label">
                        <fmt:message key="messages.enter.first.name"/>
                    </label>
                    <input id="firstName" class="form-control" type=text name="firstName" value="${user.firstName}" required/>
                    <c:if test="${not empty error_firstName}">
                        <div class="alert alert-danger"><p>
                            <fmt:message key="${error_firstName}"/></p>
                        </div>
                    </c:if>
                </div>


                <div class="form-group">
                    <label for="lastName" class="control-label">
                        <fmt:message key="messages.enter.last.name"/>
                    </label>
                    <input id="lastName" class="form-control" type=text name="lastName" value="${user.lastName}" required/>
                    <c:if test="${not empty error_lastName}">
                        <div class="alert alert-danger"><p>
                            <fmt:message key="${error_lastName}"/></p>
                        </div>
                    </c:if>
                </div>


                <div class="form-group">
                    <label for="height" class="control-label">
                        <fmt:message key="messages.enter.height"/>
                    </label>
                    <input id="height" class="form-control" type=number name="height" value="${user.height}" required/>
                    <c:if test="${not empty error_height}">
                        <div class="alert alert-danger"><p>
                            <fmt:message key="${error_height}"/></p>
                        </div>
                    </c:if>
                </div>

                <div class="form-group">
                    <label for="weight" class="control-label">
                        <fmt:message key="messages.enter.weight"/>
                    </label>
                    <input id="weight" class="form-control" type=number name="weight" value="${user.weight}" required/>
                    <c:if test="${not empty error_weight}">
                        <div class="alert alert-danger"><p>
                            <fmt:message key="${error_weight}"/></p>
                        </div>
                    </c:if>

                </div>
                <div class="form-group">
                    <label for="dateOfBirth" class="control-label">
                        <fmt:message key="messages.enter.date.of.birth"/>
                    </label>
                    <input id="dateOfBirth" class="form-control" type=date name="dateOfBirth" value="${user.dateOfBirth}" required/>
                    <c:if test="${not empty error_dateOfBirth}">
                        <div class="alert alert-danger"><p>
                            <fmt:message key="${error_dateOfBirth}"/></p>
                        </div>
                    </c:if>

                </div>

                <div>
                    <label class="control-label">
                        <fmt:message key="messages.enter.activity"/>
                    </label>
                </div>

                <c:choose>
                    <c:when test="${user.activityLevel=='FIRST'}">
                        <div class="form-check">
                            <input class="form-check-input" type="radio" id="radios1checked"
                                   name="activityLevel" value="FIRST" checked>
                            <label class="form-check-label" for="radios1">
                                <fmt:message key="messages.enter.activity1"/>
                            </label>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="form-check">
                            <input class="form-check-input" type="radio" id="radios1"
                                   name="activityLevel" value="FIRST">
                            <label class="form-check-label" for="radios1">
                                <fmt:message key="messages.enter.activity1"/>
                            </label>
                        </div>
                    </c:otherwise>
                </c:choose>

                <c:choose>
                    <c:when test="${user.activityLevel=='SECOND'}">
                        <div class="form-check">
                            <input class="form-check-input" type="radio" id="radios2"
                                   name="activityLevel" value="SECOND" checked>
                            <label class="form-check-label" for="radios2">
                                <fmt:message key="messages.enter.activity2"/>
                            </label>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="form-check">
                            <input class="form-check-input" type="radio" id="radios2checked"
                                   name="activityLevel" value="SECOND">
                            <label class="form-check-label" for="radios2">
                                <fmt:message key="messages.enter.activity2"/>
                            </label>
                        </div>
                    </c:otherwise>
                </c:choose>

                <c:choose>
                    <c:when test="${user.activityLevel=='THIRD'}">
                        <div class="form-check">
                            <input class="form-check-input" type="radio" id="radios3checked"
                                   name="activityLevel" value="THIRD" checked>
                            <label class="form-check-label" for="radios3">
                                <fmt:message key="messages.enter.activity3"/>
                            </label>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="form-check">
                            <input class="form-check-input" type="radio" id="radios3"
                                   name="activityLevel" value="THIRD">
                            <label class="form-check-label" for="radios3">
                                <fmt:message key="messages.enter.activity3"/>
                            </label>
                        </div>
                    </c:otherwise>
                </c:choose>

                <c:choose>
                    <c:when test="${user.activityLevel=='FORTH'}">
                        <div class="form-check">
                            <input class="form-check-input" type="radio" id="radios4checked"
                                   name="activityLevel" value="FORTH" checked>
                            <label class="form-check-label" for="radios4">
                                <fmt:message key="messages.enter.activity4"/>
                            </label>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="form-check">
                            <input class="form-check-input" type="radio" id="radios4"
                                   name="activityLevel" value="FORTH">
                            <label class="form-check-label" for="radios4">
                                <fmt:message key="messages.enter.activity4"/>
                            </label>
                        </div>
                    </c:otherwise>
                </c:choose>

                <c:choose>
                    <c:when test="${user.activityLevel=='FIFTH'}">
                        <div class="form-check">
                            <input class="form-check-input" type="radio" id="radios5checked"
                                   name="activityLevel" value="FIFTH" checked>
                            <label class="form-check-label" for="radios5">
                                <fmt:message key="messages.enter.activity5"/>
                            </label>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="form-check">
                            <input class="form-check-input" type="radio" id="radios5"
                                   name="activityLevel" value="FIFTH">
                            <label class="form-check-label" for="radios5">
                                <fmt:message key="messages.enter.activity5"/>
                            </label>
                        </div>
                    </c:otherwise>
                </c:choose>


                <label class="control-label">
                    <fmt:message key="messages.enter.gender"/>
                </label>

                <div class="form-check form-check-inline">

                    <c:choose>
                        <c:when test="${user.gender=='MALE'}">
                            <input class="form-check-input" type="radio" id="inlineRadio1checked"
                                   name="gender" value="MALE" checked>
                        </c:when>
                        <c:otherwise>
                            <input class="form-check-input" type="radio" id="inlineRadio1"
                                   name="gender" value="MALE">
                        </c:otherwise>
                    </c:choose>

                    <label class="form-check-label" for="inlineRadio1">
                        <fmt:message key="messages.enter.male"/>
                    </label>
                </div>


                <div class="form-check form-check-inline">
                    <c:choose>
                        <c:when test="${user.gender=='FEMALE'}">
                            <input class="form-check-input" type="radio" id="inlineRadio2checked"
                                   name="gender" value="FEMALE" checked>
                        </c:when>
                        <c:otherwise>
                            <input class="form-check-input" type="radio" id="inlineRadio2"
                                   name="gender" value="FEMALE">
                        </c:otherwise>
                    </c:choose>
                    <label class="form-check-label" for="inlineRadio2">
                        <fmt:message key="messages.enter.female"/>
                    </label>
                </div>


                <button type="submit" class="btn btn-success">
                    <fmt:message key="messages.button.submit"/>
                </button>

            </form>
        </div>
    </div>
</div>

</body>
</html>