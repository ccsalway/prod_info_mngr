<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tlds/functions.tld" %>
<!DOCTYPE html>
<html lang="en" class="has-navbar-fixed-top">
<head>
    <jsp:include page="fragments/header.jsp"/>
    <title>Product</title>
</head>
<body>

<!-- navbar -->
<jsp:include page="fragments/navbar.jsp"/>

<!-- content -->
<div class="section">
    <div class="container is-fluid">

        <!-- title -->
        <h1 class="title">Product</h1>

        <!-- breadcrumbs -->
        <nav class="breadcrumb" aria-label="breadcrumbs">
            <ul>
                <li><a href="<s:url value="/products"/>">Products</a></li>
                <li class="is-active"><a href="#" aria-current="page">New</a></li>
            </ul>
        </nav>

        <hr>

        <!-- form -->
        <form method="post" action="<s:url value="/product/add"/>" autocomplete="off">
            <div class="block">
                <h5 class="title is-5">Name</h5>
                <input class="input" type="text" name="name" value="${fn:htmlEscape(product.name)}" placeholder="Product name" autofocus>
                <c:forEach items="${result.getFieldErrors('name')}" var="error">
                    <p class="help is-danger">${error.getDefaultMessage()}</p>
                </c:forEach>
            </div>

            <div class="block">
                <h5 class="title is-5">Description</h5>
                <textarea class="textarea" name="description" placeholder="Product description">${fn:htmlEscape(product.description)}</textarea>
                <c:forEach items="${result.getFieldErrors('description')}" var="error">
                    <p class="help is-danger">${error.getDefaultMessage()}</p>
                </c:forEach>
            </div>

            <div class="block">
                <h5 class="title is-5">Displayed</h5>
                <p>New Products are, by default, not displayed.</p>
            </div>

            <div class="block">
                <button type="submit" class="button is-info">Submit</button>
                <a class="button is-text" href="<s:url value="/products"/>">Cancel</a>
            </div>
        </form>

    </div> <!-- /container -->
</div> <!-- /section -->

<!-- footer -->
<jsp:include page="fragments/footer.jsp"/>

</body>
</html>
