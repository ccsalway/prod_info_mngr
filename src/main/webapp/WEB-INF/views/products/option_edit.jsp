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
        <a class="button is-pulled-right" href="<s:url value="/product/${product.id}/attribute/${attribute.id}/option/${option.id}"/>">
            Cancel
        </a>
        <h1 class="title">Edit Option</h1>
        <hr/>
        <form method="post" action="<s:url value="/product/${product.id}/attribute/${attribute.id}/option/${option.id}/edit"/>" autocomplete="off">
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
                <div class="content">
                    <p>
                        ${fn:htmlEscape(attribute.name)}
                    </p>
                </div>
            </div>
            <div class="field">
                <label class="label">Option Name</label>
                <div class="control">
                    <input class="input" type="text" name="name" value="${fn:htmlEscape(option.name)}" placeholder="Attribute name" autofocus>
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
            </div>
        </form>
    </div>
</div>
</body>
</html>
