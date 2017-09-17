<%@ taglib prefix="spr_c" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctxt" value="${pageContext.request.contextPath}" scope="request"/>

<div class="jumbotron">

    <div class="container" style="text-align: center">
        <h1 style="margin-bottom: 50px"><spr_c:message code="content.welcome"/><br>
        ${authUser.name}&nbsp;${authUser.surname}</h1>
    </div>


    <hr>

    <div class="container" style="margin-top: 50px">
        <div class="row">
            <div class="col-xs-6">
                <spring:form class="form-horizontal" method="post" modelAttribute="Order" action="${ctxt}/free-rooms">
                    <div class="form-group">
                        <label class="control-label col-xs-3" for="datetimepicker1"><spr_c:message
                                code="order.checkIn"/></label>
                        <div class="col-xs-6">
                            <div class="input-group date" id="datetimepicker1">
                                <spring:input path="arrivalDate" type="text" class="form-control"
                                              placeholder="Check in Date:" name="dateIn"
                                              required="required"/>
                                <span class="input-group-addon">
                    <span class="glyphicon glyphicon-calendar"></span>
                  </span>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-xs-3" for="datetimepicker2"><spr_c:message
                                code="order.checkOut"/></label>
                        <div class="col-xs-6">
                            <div class="input-group date" id="datetimepicker2">
                                <spring:input path="departureDate" type="text" class="form-control"
                                              placeholder="Check out Date:" name="dateOut"
                                              required="required"/>
                                <span class="input-group-addon">
                  <span class="glyphicon glyphicon-calendar"></span>
                </span>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-xs-3" for="check3"><spr_c:message code="order.guests"/> </label>
                        <div class="col-xs-6">
                            <spring:input path="placesAmount" class="form-control" id="check3" type="number"
                                          placeholder="guest's number"
                                          name="numOfClient" min="1" max="15" required="required"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-offset-3 col-xs-2">
                            <spring:button type="submit" class="btn btn-success"><spr_c:message
                                    code="content.existance"/></spring:button>
                        </div>
                    </div>
                </spring:form>
            </div>
            <div class="col-xs-6">
                <ul class="list-unstyled pull-right" style="font-size: 150%">
                    <li><a href="tel: +375291234567">&#9742; +375 (29) 123-45-67</a></li>
                    <li>&#9993; <a href="mailto:hostelTravelBelarus@gmail.com">hostelTravelBelarus@gmail.com</a></li>
                    <li><a href="skype:HostelTravel">HostelTravel</a></li>
                    <li><spr_c:message code="content.address"/></li>
                </ul>
            </div>
        </div>
    </div>

</div>

