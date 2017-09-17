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
    <h2 style="text-align: center; padding: 0 10px 20px 10px"><spr_c:message code="room.add"/></h2>
    <div class="container" style="padding: 0 10px 20px 10px">
        <table class="table">
            <thead>
            <tr>
                <th><spr_c:message code="room.number"/></th>
                <th><spr_c:message code="room.places"/></th>
                <th><spr_c:message code="room.price"/>&#36;</th>
                <th><spr_c:message code="button.manip"/></th>
            </tr>
            </thead>
            <tbody>
            <spring:form method="post" modelAttribute="roomJSP" action="${ctxt}/add-room" class="form-horizontal">
                <tr>
                    <td>
                        <div class="form-group">
                            <spring:input path="roomNumber" class="form-control" id="check1" type="number"
                                          placeholder="Room Number" name="id" min="1" required="required"/>
                        </div>
                    </td>
                    <td>
                        <div class="form-group">
                            <spring:input path="roomPlaces" class="form-control" id="check2" type="number"
                                          placeholder="Places" name="number" min="1" max="12" required="required"/>
                        </div>
                    </td>

                    <td>
                        <div class="form-group">
                            <spring:input path="price" class="form-control" id="check4" type="number"
                                          placeholder="Total Price" name="price" min="1" step="0.01"
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

</body>
</html>

