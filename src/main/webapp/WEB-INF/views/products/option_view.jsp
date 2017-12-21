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
        <div class="is-pulled-right">
            <a class="button is-text" href="<s:url value="/product/${product.id}/attribute/${attribute.id}/option/add"/>">
                New Option
            </a>
            <div style="display: inline-block; border-right: 1px solid black; margin: 0 8px; height: 34px;"></div>
            <a class="button is-text" href="<s:url value="/product/${product.id}/attribute/${attribute.id}/option/${option.id}/delete"/>">
                Delete
            </a>
            <a class="button" href="<s:url value="/product/${product.id}/attribute/${attribute.id}/option/${option.id}/edit"/>">
                Edit
            </a>
        </div>
        <h1 class="title">Option</h1>
        <hr/>
        <div class="field">
            <label class="label">Product Name</label>
            <div class="control">
                <a href="<c:url value="/product/${product.id}"/>" class="input is-static">${fn:escapeHtml(product.name)}</a>
            </div>
        </div>
        <div class="field">
            <label class="label">Attribute Name</label>
            <div class="control">
                <a href="<c:url value="/product/${product.id}"/>/attribute/${attribute.id}" class="input is-static">${fn:escapeHtml(attribute.name)}</a>
            </div>
        </div>
        <div class="field">
            <label class="label">Option Name</label>
            <div class="control">
                <input class="input is-static" type="text" value="${fn:escapeHtml(option.name)}" readonly>
            </div>
        </div>
    </div>
</div>

</body>
</html>
