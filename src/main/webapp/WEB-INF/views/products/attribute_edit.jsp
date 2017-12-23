<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tlds/functions.tld" %>
<!DOCTYPE html>
<html lang="en" class="has-navbar-fixed-top">
<head>
    <jsp:include page="../fragments/header.jsp"/>
</head>
<body>
<jsp:include page="../fragments/navbar.jsp"/>
<div class="section">
    <div class="container">
        <h1 class="title">Edit Attribute</h1>
        <hr/>
        <form method="post" action="<s:url value="/product/${product.id}/attribute/${attribute.id}/edit"/>" autocomplete="off">
            <div class="field">
                <label class="label">Product Name</label>
                <div class="content">
                    <p>
                        ${fn:htmlEscape(product.name)}
                    </p>
                </div>
            </div>
            <div class="field">
                <label class="label">Attribute Name</label>
                <div class="control">
                    <input class="input" type="text" name="name" value="${fn:htmlEscape(attribute.name)}" placeholder="Attribute name" autofocus>
                </div>
                <c:if test="${result.getFieldErrorCount('name') > 0}">
                    <c:forEach items="${result.getFieldErrors('name')}" var="error">
                        <p class="help is-danger">${error.getDefaultMessage()}</p>
                    </c:forEach>
                </c:if>
            </div>
            <div class="field is-grouped">
                <div class="control">
                    <button type="submit" class="button is-info">Submit</button>
                </div>
                <div class="control">
                    <a class="button is-text" href="<s:url value="/product/${product.id}/attribute/${attribute.id}"/>">
                        Cancel
                    </a>
                </div>
            </div>
        </form>
    </div>
</div>
<%@ include file="../fragments/footer.jsp" %>

</body>
</html>
