<%@ taglib prefix="spr_c" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="ctxt" value="${pageContext.request.contextPath}" scope="request"/>

<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Admin Managment</title>
    <link rel="stylesheet" type="text/css"
          href="http://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">
    <script src="https://code.jquery.com/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>

    <link rel="stylesheet" type="text/css" href="<spr_c:url value="/css/index.css"/>">
    <link rel="shortcut icon" href="img/favicon.ico" type="image/x-icon"/>

    <style>
        .col-xs-4 {
            text-align: center;
        }
    </style>
</head>
<body>

<jsp:include page="../common/adminHeader.jsp"/>

<div id="content">
    <h2 style="text-align: center; padding: 0 10px 20px 10px"><spr_c:message code="user.add"/></h2>
    <div class="container" style="padding: 0 10px 20px 10px">
        <table class="table">
            <thead>
            <tr>
                <th><spr_c:message code="user.name"/></th>
                <th><spr_c:message code="user.surname"/></th>
                <th><spr_c:message code="user.login"/></th>
                <th><spr_c:message code="user.password"/></th>
                <th><spr_c:message code="button.manip"/></th>
            </tr>
            </thead>
            <tbody>
            <spring:form method="post" modelAttribute="userJSP" action="${ctxt}/add-user" class="form-horizontal">
                <tr>
                    <td>
                        <div class="form-group">
                            <spring:input path="name" class="form-control" id="inputsignup1" type="text" placeholder="Name"
                                          name="name"
                                          required="required"/>
                        </div>
                    </td>
                    <td>
                        <div class="form-group">
                            <spring:input path="surname" class="form-control" id="inputsignup2" type="text"
                                          placeholder="Surname" name="surname"
                                          required="required"/>
                        </div>
                    </td>
                    <td>
                        <div class="form-group">
                            <spring:input path="login" class="form-control" id="inputsignup3" type="text" placeholder="Login"
                                          name="login"
                                          required="required"/>
                        </div>
                    </td>
                    <td>
                        <div class="form-group">
                            <spring:input path="password" class="form-control" id="inputsignup4" type="password"
                                          placeholder="Password" name="pass"
                                          required="required"/>
                        </div>
                    </td>
                    <td>
                        <div class="form-group">
                            <div class="col-xs-offset-3 col-xs-2">
                                <spring:button type="submit" class="btn btn-success"><spr_c:message code="button.add"/>
                                </spring:button>
                            </div>
                        </div>
                    </td>
                </tr>
            </spring:form>
            </tbody>
        </table>
        <div><h2 style="color: red">${message}</h2></div>
    </div>
</div>

<jsp:include page="../common/footer.jsp"/>
<script type="text/javascript" src="js/validationSignUp.js"></script>
</body>
</html>

