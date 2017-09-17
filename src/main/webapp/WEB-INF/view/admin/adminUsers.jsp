<%@ taglib prefix="spr_c" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="pagination" uri="/web/WEB-INF/tld/taglib" %>
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

    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.12/css/jquery.dataTables.min.css">
    <script type="text/javascript" src="https://code.jquery.com/jquery-1.12.3.min.js"></script>
    <script type="text/javascript" src="https://cdn.datatables.net/v/dt/dt-1.10.12/datatables.min.js"></script>

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
    <h2 style="text-align: center; padding: 0 10px 20px 10px"><spr_c:message code="admin.users"/></h2>
    <div class="container" style="padding: 0 10px 20px 10px">
        <div><h2>${user.name} ${user.surname} ${message}</h2></div>
        <%--<c:if test="${not empty error}">
            <div style="color:red;position: absolute;right:350px"><spr_c:message code="content.error"/></div>
            <h1>ERROR</h1>
        </c:if>--%>
        <form action="${ctxt}/admin-users" method="post">
            <table id="users" class="display" width="100%" cellspacing="0">
                <thead>
                <tr>
                    <th><spr_c:message code="user.client"/></th>
                    <th><spr_c:message code="user.login"/></th>
                    <th><spr_c:message code="user.password"/></th>
                    <th><spr_c:message code="user.ban"/></th>
                    <th><spr_c:message code="button.manip"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${users}" var="user">
                    <tr>
                        <c:if test="${param.edit == user.id}">
                            <td>
                                <input type="text" name="name" id="inputsignup1" value="${user.name}"/>
                                <input type="text" name="surname" id="inputsignup2" value="${user.surname}"/>
                                <input type="hidden" name="userId" value="${user.id}"/>
                            </td>
                            <td>
                                <input type="text" name="login" id="inputsignup3" value="${user.login}"/>
                            </td>
                        </c:if>
                        <c:if test="${param.edit != user.id}">
                            <td><a href="#">${user.name} ${user.surname}</a></td>
                            <td>${user.login}</td>
                        </c:if>
                        <td>${user.password}</td>
                        <td>${user.userStatus}</td>
                        <td>
                            <c:if test="${param.edit == user.id}">
                                <button type="submit" class="btn btn-success"><spr_c:message
                                        code="button.save"/></button>
                            </c:if>
                            <c:if test="${param.edit != user.id}">
                                <div class="btn-group">
                                    <a href="${ctxt}/delete-user/${user.id}" class="btn btn-danger"><spr_c:message
                                            code="button.del"/></a>
                                    <a href="${ctxt}/admin-users?edit=${user.id}" class="btn btn-warning"><spr_c:message
                                            code="button.edit"/></a>
                                    <c:if test="${not  user.banned}">
                                        <a href="${ctxt}/set-ban/${user.id}" class="btn btn-success"><spr_c:message
                                                code="button.ban"/></a>
                                    </c:if>
                                    <c:if test="${user.banned}">
                                        <a href="${ctxt}/set-unban/${user.id}" class="btn btn-danger"><spr_c:message
                                                code="button.ban"/></a>
                                    </c:if>
                                </div>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <div style="padding: 5px 57px 0 0">
                <a href="${ctxt}/show-add-user" class="btn btn-success pull-right"><spr_c:message code="user.add"/></a>
            </div>
        </form>
    </div>
</div>

<script>
    $(function(){
        $("#users").dataTable();
    })
</script>

<jsp:include page="../common/footer.jsp"/>
<script type="text/javascript" src="js/validationSignUp.js"></script>

</body>
</html>