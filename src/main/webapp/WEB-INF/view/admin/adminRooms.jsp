<%@ taglib prefix="spr_c" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
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
    <h2 style="text-align: center; padding: 0 10px 20px 10px"><spr_c:message code="admin.rooms"/></h2>
    <div class="container" style="padding: 0 10px 20px 10px">
        <div><h2>${message}</h2></div>
        <form action="${ctxt}/admin-rooms" method="post">
            <table id="rooms" class="display" width="100%" cellspacing="0">
                <thead>
                <tr>
                    <th><spr_c:message code="room.number"/></th>
                    <th><spr_c:message code="room.places"/></th>
                    <th><spr_c:message code="room.price"/>&#36;</th>
                    <th><spr_c:message code="button.manip"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${rooms}" var="room">
                    <tr>
                        <td>${room.roomNumber}</td>
                        <td>${room.roomPlaces}</td>
                        <c:if test="${param.edit == room.id}">
                            <td>
                                <input type="number" name="price" min="1" step="0.01" value="${room.price}"/>
                                <input type="hidden" name="roomId" value="${room.id}"/>
                            </td>
                        </c:if>
                        <c:if test="${param.edit != room.id}">
                            <td>${room.price}</td>
                        </c:if>
                        <td>
                            <c:if test="${param.edit == room.id}">
                                <button type="submit" class="btn btn-success"><spr_c:message
                                        code="button.save"/></button>
                            </c:if>
                            <c:if test="${param.edit != room.id}">
                                <div class="btn-group">
                                    <a href="${ctxt}/delete-room/${room.id}" class="btn btn-danger"><spr_c:message
                                            code="button.del"/></a>

                                    <a href="${ctxt}/admin-rooms?edit=${room.id}" class="btn btn-warning"><spr_c:message
                                            code="button.edit"/></a>
                                </div>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <div style="padding: 5px 57px 0 0">
                <a href="${ctxt}/show-add-room" class="btn btn-success pull-right"><spr_c:message code="room.add"/></a>
            </div>
        </form>
    </div>
</div>

<script>
    $(function () {
        $("#rooms").dataTable();
    })
</script>

<jsp:include page="../common/footer.jsp"/>

</body>
</html>

