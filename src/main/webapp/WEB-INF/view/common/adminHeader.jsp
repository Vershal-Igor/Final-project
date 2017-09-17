<%@ taglib prefix="spr_c" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="ctxt" value="${pageContext.request.contextPath}" scope="request"/>

<link rel="stylesheet" type="text/css" href="<spr_c:url value="/css/index.css"/>">
<link rel="shortcut icon" href="img/favicon.ico" type="image/x-icon"/>

<div class="navbar navbar-inverse navbar-static-top">
    <div class="container">
        <div class="navbar-header">
            <button type="submit" data-target="#nbC" data-toggle="collapse" class="navbar-toggle">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a href="${ctxt}/" class="navbar-brand">HOSTEL&trade;</a>
        </div>
        <div id="nbC" class="collapse navbar-collapse">
            <ul class="nav navbar-nav navbar-right">
                <li><img src="${ctxt}/img/admin.png" class="img-circle"
                         style="width: 43px; height: 43px; margin-top: 4px"></li>
                <li><a href="#">${authUser.name}</a></li>
                <li style="margin-top: 6px"><a href="${ctxt}/sign-out" type="submit"
                                               class="the_lame_button plavno1"><spr_c:message code="login.signOut"/></a>
                </li>
            </ul>
            <div class="navbar-form navbar-left" style="color: #eeeeee">
                <spr_c:message code="content.welcomeMSG"/>${authUser.name}&nbsp;${authUser.surname}!
            </div>
            <div class="navbar-form navbar-right">
                <jsp:include page="changeLanguage.jsp"/>
            </div>
        </div>
    </div>
</div>

<div class="jumbotron">

    <div class="container" style="text-align: center">
        <h2 style="margin-bottom: 50px"><i class="fa fa-arrow-down"></i> <spr_c:message code="admin.manipulation"/> <i
                class="fa fa-arrow-down"></i></h2>
    </div>
    <div class="container">
        <div class="row">
            <div class="col-xs-4">
                <a href="${ctxt}/admin-rooms" class="btn btn-success btn-lg"><spr_c:message code="admin.rooms"/></a>
            </div>
            <div class="col-xs-4">
                <a href="${ctxt}/admin-users" class="btn btn-success btn-lg"><spr_c:message code="admin.users"/></a>
            </div>
            <div class="col-xs-4">
                <a href="${ctxt}/admin-orders" class="btn btn-success btn-lg"><spr_c:message code="admin.orders"/></a>
            </div>
        </div>
    </div>
</div>
