<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spr_c" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="ctxt" value="${pageContext.request.contextPath}" scope="request"/>

<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Payment</title>
    <link rel="stylesheet" type="text/css"
          href="http://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">
    <script src="https://code.jquery.com/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>

    <link rel="stylesheet" type="text/css" href="<spr_c:url value="/css/index.css"/>">
    <link rel="shortcut icon" href="img/favicon.ico" type="image/x-icon"/>

</head>

<body>

<jsp:include page="common/header.jsp"/>

<div class="jumbotron" style="height: 531px;">
    <%--${controller.message}--%>
    <c:if test="${empty authUser}">
        <div class="container" style="text-align: center">
            <h2 style="margin-bottom: 20px"><spr_c:message code="content.details"/></h2>
            <c:if test="${param.authResult == 'false'}">
                <div style="color:red"><spr_c:message code="login.failSignUp"/></div>
            </c:if>


            <c:if test="${param.authResult == 'true'}">
                <div style="color:#5cb85c"><spr_c:message code="login.succSignUp"/></div>
            </c:if>
        </div>


        <div class="row">
            <div class="panel-group col-xs-offset-2 col-xs-4" style="padding: 10px">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title" style="text-align: center">
                            <a href="#signup1" data-toggle="collapse" style="text-decoration: none"><spr_c:message
                                    code="login.signUp"/></a>
                        </h3>
                    </div>
                    <div class="panel-collapse collapse" id="signup1">
                        <div class="panel-body">
                            <spring:form method="post" modelAttribute="userJSP" action="${ctxt}/sign-up"
                                         style="margin-top: 10px"
                                         id="signUp">
                                <div class="form-group">
                                    <div class="row" style="padding: 0 10px 10px 10px">
                                        <label class="col-xs-2 control-label" for="inputsignup1"><spr_c:message
                                                code="user.name"/></label>
                                        <div class="col-xs-4">
                                            <spring:input path="name" type="text" class="form-control"
                                                          placeholder="Name"
                                                          id="inputsignup1"
                                                          required="required" style="margin-left: -18px"/>
                                        </div>
                                        <label class="col-xs-2 control-label" for="inputsignup2"><spr_c:message
                                                code="user.surname"/></label>
                                        <div class="col-xs-4">
                                            <spring:input path="surname" type="text" class="form-control"
                                                          placeholder="Surname" id="inputsignup2"
                                                          required="required"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="row" style="padding: 0 10px 0 10px">
                                        <label class="col-xs-2 control-label" for="inputsignup3"><spr_c:message
                                                code="user.login"/></label>
                                        <div class="col-xs-4">
                                            <spring:input path="login" type="text" class="form-control"
                                                          placeholder="Login"
                                                          id="inputsignup3"
                                                          required="required" style="margin-left: -18px"/>
                                        </div>
                                        <label class="col-xs-2 control-label" for="inputsignup4"><spr_c:message
                                                code="user.password"/></label>
                                        <div class="col-xs-4">
                                            <spring:input path="password" class="form-control" placeholder="Password"
                                                          id="inputsignup4" required="required"/>
                                        </div>
                                        <input type="hidden" name="redir" id="redir" value="/procced-to-check?rb=${rb}">
                                        <div class="col-xs-3" style="margin-top: 20px">
                                            <div class="form-group">
                                                <div class="col-xs-offset-3 col-xs-2">
                                                    <spring:button type="submit" class="btn btn-success"><spr_c:message
                                                            code="login.signUp"/></spring:button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </spring:form>
                        </div>
                    </div>
                </div>
            </div>

            <div class="panel-group col-xs-4" style="padding: 10px">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title" style="text-align: center">
                            <a href="#signup2" data-toggle="collapse"><spr_c:message code="login.signIn"/></a>
                        </h3>
                    </div>
                    <div class="panel-collapse collapse" id="signup2">
                        <div class="panel-body">
                            <spring:form method="post" modelAttribute="userJSP" action="${ctxt}/sign-in"
                                         style="margin-top: 10px"
                                         id="signIn">
                                <div class="form-group">
                                    <div class="row" style="padding: 0 10px 10px 10px">
                                        <label class="col-xs-2 control-label" for="signIn1"><spr_c:message
                                                code="user.login"/></label>
                                        <div class="col-xs-4">
                                            <spring:input path="login" type="text" class="form-control"
                                                          placeholder="Login"
                                                          id="signIn1"
                                                          required="required" style="margin-left: -18px"/>
                                        </div>
                                        <label class="col-xs-2 control-label" for="signIn2"><spr_c:message
                                                code="user.password"/></label>
                                        <div class="col-xs-4">
                                            <spring:input path="password" type="password" class="form-control"
                                                          placeholder="Password"
                                                          id="signIn2" required="required"/>
                                        </div>
                                        <input type="hidden" name="redir" id="redir" value="/procced-to-check?rb=${rb}">
                                        <div class="col-xs-3" style="margin-top: 20px">
                                            <div class="form-group">
                                                <div class="col-xs-offset-3 col-xs-2">
                                                    <spring:button type="submit" class="btn btn-success"><spr_c:message
                                                            code="login.signIn"/></spring:button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </spring:form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </c:if>
    <hr>


    <c:if test="${not empty authUser}">
        <div class="container" style="text-align: center;">
            <h2 style="margin-bottom: 20px"><spr_c:message
                    code="content.payDetails"/></h2>

            <spring:form method="post" action="${ctxt}/add-order">
                <div class="form-group">
                    <div class="row" style="padding: 20px; font-size: 135%">
                        <label class="radio-inline col-xs-offset-3 col-xs-3">
                            <input type="radio" name="rb2" value="${0}" required="required"><spr_c:message
                                code="content.compleate"/>
                        </label>
                        <label class="radio-inline col-xs-3">
                            <input type="radio" name="rb2" value="${1}"><spr_c:message code="content.booking"/>
                        </label>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-xs-3" style="margin-left: 425px">
                        <button type="submit" class="btn btn-success"><spr_c:message
                                code="content.confirm"/></button>
                    </div>
                </div>
            </spring:form>

        </div>
    </c:if>
</div>

<br>


<jsp:include page="common/footer.jsp"/>

<script type="text/javascript" src="js/validSignInPay.js"></script>
<script type="text/javascript" src="js/validationSignUp.js"></script>


</body>
</html>


