<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spr_c" uri="http://www.springframework.org/tags" %>
<c:set var="ctxt" value="${pageContext.request.contextPath}" scope="request"/>
<!--Header-->
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
            <c:if test="${not empty authUser}">>
                <div class="navbar-form navbar-left" style="color: #eeeeee">
                    <spr_c:message code="content.welcomeMSG"/>${authUser.name}&nbsp;${authUser.surname}!
                </div>
            </c:if>
        </div>
        <%--<h1>${authUser.name}&nbsp;${authUser.surname}</h1>--%>
        <spring:form method="post" modelAttribute="userJSP" action="${ctxt}/sign-in" id="signIn">
            <div id="nbC" class="collapse navbar-collapse">
                <div class="navbar-form navbar-right">
                    <jsp:include page="changeLanguage.jsp"/>
                    <c:if test="${empty authUser}">
                        <spring:input path="login" type="text" id="login" class="form-control" placeholder="Login"
                                      required="required"/>
                        <spring:input path="password" type="password" id="password" class="form-control"
                                      placeholder="Password"
                                      required="required"/>
                        <input type="hidden" name="redir" value="/">
                        <spring:button type="submit" class="btn btn-success"><spr_c:message
                                code="login.signIn"/></spring:button>
                    </c:if>
                    <c:if test="${not empty authUser}">
                        <a href="${ctxt}/return" type="submit" class="btn btn-success"
                           style="margin-right: 80px"><spr_c:message code="content.returnToAcc"/></a>
                        <a href="${ctxt}/sign-out" type="submit"
                           class="the_lame_button plavno1"><spr_c:message code="login.signOut"/></a>
                    </c:if>
                </div>
            </div>
        </spring:form>
        <c:if test="${not empty noSuchUser}">
            <div style="color:red;position: absolute;right:350px"><spr_c:message code="content.error"/></div>
        </c:if>
    </div>
</div>
<script type="text/javascript" src="js/validationSignIn.js"></script>


