<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spr_c" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="banner" uri="/web/WEB-INF/tld/taglib" %>
<c:set var="ctxt" value="${pageContext.request.contextPath}" scope="request"/>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Hostel&trade;</title>
    <link rel="stylesheet" type="text/css"
          href="http://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">
    <link rel="stylesheet" type="text/css" href="<spr_c:url value="/css/index.css"/>">
    <link rel="stylesheet" type="text/css" href="<spr_c:url value='css/bootstrap-datetimepicker.min.css'/>">
    <script src="https://code.jquery.com/jquery.min.js"></script>
    <!--  CSS widget "Bootstrap datetimepicker" -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <!-- FAVICON -->
    <link rel="shortcut icon" href="img/favicon.ico" type="image/x-icon"/>
    <!--Skype-->
    <a data-config="commands=*;size=22;status=off;theme=logo;language=ru;bgcolor=#2a92f3" id="skaip-buttons"
       href="http://www.skaip.su/">Skype</a>
    <script src="//apps.skaip.su/buttons/widget/core.min.js" defer="defer"></script>

    <!--DATETIMEPICKER-->
    <!--Script moment-with-locales.min.js for work with dates -->
    <script type="text/javascript" src="js/moment-with-locales.min.js"></script>
    <!--Script "Bootstrap datetimepicker" -->
    <script type="text/javascript" src="js/bootstrap-datetimepicker.min.js"></script>
    <script type="text/javascript" src="js/index.js"></script>
</head>
<body>

<jsp:include page="common/header.jsp"/>

<jsp:include page="common/orderManager.jsp"/>

<!--Банер с комнатами-->
<banner:banner/>

<c:if test="${not empty Rooms}">
    <spring:form method="get" modelAttribute="userJSP" action="${ctxt}/procced-to-check" id="payment">
        <!--Расценки на комнаты-->
        <div class="container">
            <h1 style="text-align: center; padding: 10px"><spr_c:message code="room.free"/></h1>

            <table>
                <thead>
                <tr>
                    <th><spr_c:message code="room.number"/></th>
                    <th><spr_c:message code="room.places"/></th>
                    <th><spr_c:message code="room.priceNight"/>&#36;</th>
                    <th><spr_c:message code="mainip.choose"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${Rooms}" var="free">
                    <tr>
                        <td>${free.roomNumber}</td>
                        <td>${free.roomPlaces}</td>
                        <td>${free.price}</td>
                        <td><input type="radio" name="rb" value="${free.id}" required="required"></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>


            <spring:button type="submit" class="btn btn-warning pull-right"
                           style="margin-bottom: 45px"><spr_c:message code="content.proceed"/></spring:button>

        </div>
    </spring:form>
</c:if>

<jsp:include page="common/footer.jsp"/>

</body>
</html>

