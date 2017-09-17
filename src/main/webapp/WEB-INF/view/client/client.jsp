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

    <title>Client page</title>
    <link rel="stylesheet" type="text/css"
          href="http://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">
    <link rel="stylesheet" type="text/css" href="<spr_c:url value='css/bootstrap-datetimepicker.min.css'/>">
    <link rel="stylesheet" type="text/css" href="<spr_c:url value="/css/index.css"/>">
    <script src="https://code.jquery.com/jquery.min.js"></script>
    <!--  CSS widget "Bootstrap datetimepicker" -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <!-- FAVICON -->
    <link rel="shortcut icon" href="img/favicon.ico" type="image/x-icon"/>
    <!--Skype-->
    <a data-config="commands=*;size=22;status=off;theme=logo;language=ru;bgcolor=#2a92f3" id="skaip-buttons"
       href="http://www.skaip.su/">Skype</a>
    <script src="//apps.skaip.su/buttons/widget/core.min.js" defer="defer"></script>

    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.12/css/jquery.dataTables.min.css">
    <script type="text/javascript" src="https://code.jquery.com/jquery-1.12.3.min.js"></script>
    <script type="text/javascript" src="https://cdn.datatables.net/v/dt/dt-1.10.12/datatables.min.js"></script>

    <!--DATETIMEPICKER-->
    <!--Script moment-with-locales.min.js for work with dates -->
    <script type="text/javascript" src="js/moment-with-locales.min.js"></script>
    <!--Script "Bootstrap datetimepicker" -->
    <script type="text/javascript" src="js/bootstrap-datetimepicker.min.js"></script>
    <script type="text/javascript" src="js/index.js"></script>

</head>

<body>

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
            <div class="navbar-form navbar-left" style="color: #eeeeee">
                <spr_c:message code="content.welcomeMSG"/>${authUser.name}&nbsp;${authUser.surname}!
            </div>
        </div>
        <div id="nbC" class="collapse navbar-collapse">
            <ul class="nav navbar-nav navbar-right">
                <li><img src="${ctxt}/img/client.png" class="img-circle"
                         style="width: 43px; height: 43px; margin-top: 4px">
                </li>
                <li><a href="#">${authUser.name}&nbsp;${authUser.surname}</a></li>
                <li style="margin-top: 6px"><a href="${ctxt}/sign-out" type="submit"
                                               class="the_lame_button plavno1"><spr_c:message code="login.signOut"/></a>
                </li>
            </ul>
            <div class="navbar-form navbar-right">
                <jsp:include page="../common/changeLanguage.jsp"/>
            </div>
        </div>
    </div>
</div>


<jsp:include page="../common/orderManager.jsp"/>

<div id="content">
    <h2 style="text-align: center; padding: 0 10px 20px 10px"><spr_c:message code="user.history"/></h2>
    <div class="container" style="padding: 0 10px 20px 10px">
        <div><h2>${message}</h2></div>
        <table id="orders" width="110%" cellspacing="0">
            <thead>
            <tr>
                <th><spr_c:message code="order.number"/></th>
                <th><spr_c:message code="room.number"/></th>
                <th><spr_c:message code="order.data"/></th>
                <th><spr_c:message code="room.places"/></th>
                <th><spr_c:message code="order.payType"/></th>
                <th><spr_c:message code="order.discount"/>%</th>
                <th><spr_c:message code="order.finalPrice"/>&#36;</th>
                <th><spr_c:message code="order.status"/></th>
                <th><spr_c:message code="button.manip"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${historyOrders}" var="order">
                <tr>
                    <td>${order.id}</td>
                    <td>${order.roomNumber}</td>
                    <td style="width: 30%">${order.arrivalDate}<br>${order.departureDate}</td>
                    <td>${order.placesAmount}</td>
                    <td>${order.payType}</td>
                    <td>${order.discount}</td>
                    <td>${order.finalPrice}</td>
                    <td>${order.orderStatus}</td>
                    <td style="width: 50%">
                        <div class="btn-group btn-group-sm">
                            <a href="${ctxt}/delete-order/${order.id}" class="btn btn-danger"><spr_c:message
                                    code="button.del"/></a>
                        </div>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
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
