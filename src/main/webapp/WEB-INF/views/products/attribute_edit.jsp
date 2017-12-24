<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tlds/functions.tld" %>
<!DOCTYPE html>
<html lang="en" class="has-navbar-fixed-top">
<head>
    <jsp:include page="../fragments/header.jsp"/>
    <title>Attribute</title>
</head>
<body>

<!-- navbar -->
<jsp:include page="../fragments/navbar.jsp"/>

<!-- content -->
<div class="section">
    <div class="container is-fluid">

        <!-- title -->
        <h1 class="title">Attribute</h1>

        <!-- breadcrumbs -->
        <nav class="breadcrumb" aria-label="breadcrumbs">
            <ul>
                <li><a href="<s:url value="/products"/>">Products</a></li>
                <li><a href="<c:url value="/product/${product.id}"/>">${fn:htmlEscape(product.name)}</a></li>
                <li class="is-active"><a href="#" aria-current="page">${fn:htmlEscape(attribute.name)}</a></li>
            </ul>
        </nav>

        <hr>

        <!-- form -->
        <form method="post" action="<s:url value="/product/${product.id}/attribute/${attribute.id}/edit"/>" autocomplete="off">
            <div class="block">
                <h5 class="title is-5">Name</h5>
                <input class="input" type="text" name="name" value="${fn:htmlEscape(form.name)}" placeholder="Attribute name" autofocus>
                <c:forEach items="${result.getFieldErrors('name')}" var="error">
                    <p class="help is-danger">${error.getDefaultMessage()}</p>
                </c:forEach>
            </div>

            <div class="block">
                <h5 class="title is-5">Displayed</h5>
                <label class="radio">
                    <input type="radio" name="displayed" value="true" <c:if test="${form.displayed}">checked</c:if>>
                    Yes
                </label>
                <label class="radio">
                    <input type="radio" name="displayed" value="false" <c:if test="${not form.displayed}">checked</c:if>>
                    No
                </label>
                <c:forEach items="${result.getFieldErrors('displayed')}" var="error">
                    <p class="help is-danger">${error.getDefaultMessage()}</p>
                </c:forEach>
            </div>

            <div class="block">
                <button type="submit" class="button is-info">Submit</button>
                <a class="button is-text" href="<s:url value="/product/${product.id}/attribute/${attribute.id}"/>">Cancel</a>
            </div>
        </form>

    </div> <!-- /container -->
</div> <!-- /section -->

<!-- footer -->
<jsp:include page="../fragments/footer.jsp"/>

</body>
</html>
