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
    <h2 style="text-align: center; padding: 0 10px 20px 10px"><spr_c:message code="admin.orders"/></h2>
    <div class="container" style="padding: 0 10px 20px 10px">
        <div><h2>${message}</h2></div>
        <form action="${ctxt}/admin-orders" method="post">
            <table id="orders" style="width: 113%;max-width: none" cellspacing="0">
                <thead>
                <tr>
                    <th><spr_c:message code="order.number"/></th>
                    <th><spr_c:message code="user.client"/></th>
                    <th><spr_c:message code="order.data"/></th>
                    <th><spr_c:message code="room.number"/></th>
                    <th><spr_c:message code="order.payType"/></th>
                    <th><spr_c:message code="order.discount"/>%</th>
                    <th><spr_c:message code="order.finalPrice"/>&#36;</th>
                    <th><spr_c:message code="order.status"/></th>
                    <th><spr_c:message code="button.manip"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${orders}" var="order">
                    <tr>
                        <td>${order.id}</td>
                        <td><a href="#">${order.name}
                            <div>${order.surname}</div>
                        </a></td>
                        <td style="width: 30%">${order.arrivalDate}<br>${order.departureDate}</td>
                        <td><a href="#">${order.roomNumber}</a></td>
                        <td>${order.payType}</td>
                        <c:if test="${param.edit == order.id}">
                            <td>
                                <input type="number" name="discount" min="1" max="100" step="0.01"
                                       value="${order.discount}"/>
                                <input type="hidden" name="orderId" value="${order.id}"/>
                            </td>
                        </c:if>
                        <c:if test="${param.edit != order.id}">
                            <td>${order.discount}</td>
                        </c:if>
                        <td>${order.finalPrice}</td>
                        <td>${order.orderStatus}</td>

                        <td style="width: 50%">
                            <c:if test="${param.edit == order.id}">
                                <button type="submit" class="btn btn-success"><spr_c:message
                                        code="button.save"/></button>
                            </c:if>
                            <c:if test="${param.edit != order.id}">
                                <div class="btn-group btn-group-sm">
                                    <a href="${ctxt}/delete-order/${order.id}" class="btn btn-danger"><spr_c:message
                                            code="button.del"/></a>
                                    <a href="${ctxt}/admin-orders?edit=${order.id}"
                                       class="btn btn-warning"><spr_c:message code="button.edit"/></a>
                                    <c:if test="${not order.archive}">
                                        <a href="${ctxt}/set-confirm/${order.id}" class="btn btn-success"><spr_c:message
                                                code="button.conf"/></a>
                                        <a href="${ctxt}/set-reject/${order.id}" class="btn btn-success"><spr_c:message
                                                code="button.rej"/></a>
                                    </c:if>
                                </div>
                                <div class="btn-group btn-group-sm">
                                    <c:if test="${order.confirm or order.reject}">
                                        <a href="${ctxt}/set-archive/${order.id}" class="btn btn-success"><spr_c:message
                                                code="button.arch"/></a>
                                    </c:if>
                                </div>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </form>
    </div>
</div>


<script>
    $(function () {
        $("#orders").dataTable();
    })
</script>


<jsp:include page="../common/footer.jsp"/>

</body>
</html>
