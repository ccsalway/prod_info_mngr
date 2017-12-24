<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tlds/functions.tld" %>
<!DOCTYPE html>
<html lang="en" class="has-navbar-fixed-top">
<head>
    <jsp:include page="../fragments/header.jsp"/>
    <title>Option</title>
</head>
<body>

<!-- navbar -->
<jsp:include page="../fragments/navbar.jsp"/>

<!-- content -->
<div class="section">
    <div class="container is-fluid">

        <!-- title and buttons -->
        <div class="is-pulled-right">
            <button class="button is-text" data-delete="${option.id}">Delete</button>
            <a class="button is-info" href="<s:url value="/product/${product.id}/attribute/${attribute.id}/option/${option.id}/edit"/>">Edit</a>
        </div>
        <h1 class="title">Option</h1>

        <!-- breadcrumbs -->
        <nav class="breadcrumb" aria-label="breadcrumbs">
            <ul>
                <li><a href="<s:url value="/products"/>">Products</a></li>
                <li><a href="<c:url value="/product/${product.id}"/>">${fn:htmlEscape(product.name)}</a></li>
                <li><a href="<c:url value="/product/${product.id}/attribute/${attribute.id}"/>">${fn:htmlEscape(attribute.name)}</a></li>
                <li class="is-active"><a href="#" aria-current="page">${fn:htmlEscape(option.name)}</a></li>
            </ul>
        </nav>

        <hr>

        <!-- items -->
        <div class="block">
            <h5 class="title is-5">Name</h5>
            <p>${fn:htmlEscape(option.name)}</p>
        </div>

        <div class="block">
            <h5 class="title is-5">Displayed</h5>
            <p>${option.displayed ? "Yes" : "No"}</p>
        </div>

    </div> <!-- /container -->
</div> <!-- /section -->

<!-- footer -->
<jsp:include page="../fragments/footer.jsp"/>


<script>
    $("[data-delete]").on("click", function () {
        var id = $(this).attr("data-delete");
        if (id !== undefined && confirm("Are you sure?")) {
            window.location = "<s:url value="/product/${product.id}/attribute/${attribute.id}/option/"/>" + id + "/delete";
        }
    });
</script>

</body>
</html>
