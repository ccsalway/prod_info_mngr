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
    <div class="container is-fluid">
        <div class="level is-mobile">
            <div class="level-left">
                <div class="level-item">
                    <h1 class="title">New Attribute</h1>
                </div>
            </div>
        </div>
        <nav class="breadcrumb">
            <ul>
                <li>/</li>
                <li><a href="<c:url value="/product/${product.id}"/>">${fn:htmlEscape(product.name)}</a></li>
                <li class="is-active"><a href="#">New</a></li>
            </ul>
        </nav>
        <hr class="margin-top-small">
        <form method="post" action="<s:url value="/product/${product.id}/attribute/add"/>" autocomplete="off">
            <div class="field">
                <label class="label">Name</label>
                <div class="control">
                    <input class="input" type="text" name="name" value="${fn:htmlEscape(attribute.name)}" placeholder="Attribute name" autofocus>
                </div>
                <c:forEach items="${result.getFieldErrors('name')}" var="error">
                    <p class="help is-danger">${error.getDefaultMessage()}</p>
                </c:forEach>
            </div>
            <div class="field">
                <label class="label">Displayed</label>
                <div class="content">
                    <p>
                        New Attributes are hidden by default, since they won't have any options.
                    </p>
                </div>
            </div>
            <div class="field is-grouped">
                <div class="control">
                    <button type="submit" class="button is-info">Submit</button>
                </div>
                <div class="control">
                    <a class="button is-text" href="<s:url value="/product/${product.id}"/>">
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
