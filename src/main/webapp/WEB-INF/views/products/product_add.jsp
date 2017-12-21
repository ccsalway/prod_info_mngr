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
        <a class="button is-pulled-right" href="<s:url value="/products"/>">
            Cancel
        </a>
        <h1 class="title">New Product</h1>
        <hr/>
        <form method="post" action="<s:url value="/product/add"/>" autocomplete="off">
            <div class="field">
                <label class="label">Product Name</label>
                <div class="control">
                    <input class="input" type="text" name="name" value="${fn:escapeHtml(product.name)}" placeholder="Product name" autofocus>
                </div>
            </div>
            <c:if test="${not empty errorMsg}">
                <p>${errorMsg}</p>
            </c:if>
            <div class="field is-grouped">
                <div class="control">
                    <button type="submit" class="button is-info">Submit</button>
                </div>
                <div class="control">
                    <a class="button is-text" href="<s:url value="/products"/>">
                        Cancel
                    </a>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>
