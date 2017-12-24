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

        <!-- title and buttons -->
        <div class="is-pulled-right">
            <button class="button is-text" data-delete="${product.id}">Delete</button>
            <a class="button is-info" href="<s:url value="/product/${product.id}/edit"/>">Edit</a>
        </div>
        <h1 class="title">Product</h1>

        <!-- breadcrumbs -->
        <nav class="breadcrumb" aria-label="breadcrumbs">
            <ul>
                <li><a href="<s:url value="/products"/>">Products</a></li>
                <li class="is-active"><a href="#" aria-current="page">${fn:htmlEscape(product.name)}</a></li>
            </ul>
        </nav>

        <hr>

        <!-- items -->
        <div class="block">
            <h5 class="title is-5">Name</h5>
            <p>${fn:htmlEscape(product.name)}</p>
        </div>

        <div class="block">
            <h5 class="title is-5">Description</h5>
            <c:choose>
                <c:when test="${not empty product.description}">
                    <c:set var="product_description" value="${fn:htmlEscape(product.description)}"/>
                    <p>${fn:nl2br(product_description)}</p>
                </c:when>
                <c:otherwise>
                    <p class="has-text-grey-light">[Click Edit to add a description]</p>
                </c:otherwise>
            </c:choose>
        </div>

        <div class="block">
            <h5 class="title is-5">Displayed</h5>
            <p>${product.displayed ? "Yes" : "No"}</p>
        </div>

        <!-- attributes -->
        <div class="is-pulled-right">
            <a class="button is-info" href="<s:url value="/product/${product.id}/attribute/add"/>">Add</a>
        </div>
        <h5 class="title is-5">Attributes</h5>

        <table id="attrTable" class="table is-hoverable is-fullwidth" style="cursor:pointer;">
            <thead>
            <tr>
                <th>Attribute</th>
                <th class="is-narrow has-text-centered">Displayed</th>
                <th class="is-narrow has-text-centered">Position</th>
            </tr>
            </thead>
            <tbody>
            <c:choose>
                <c:when test="${attributes.totalElements > 0}">
                    <c:forEach items="${attributes.content}" var="attr">
                        <tr data-id="${attr.id}">
                            <td>${fn:htmlEscape(attr.name)}</td>
                            <td class="is-narrow has-text-centered">
                                <c:choose>
                                    <c:when test="${attr.displayed}">
                                        <i class="fa fa-eye"></i>
                                    </c:when>
                                    <c:otherwise>
                                        <i class="fa fa-eye-slash has-text-grey-light"></i>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td class="is-narrow">
                                <c:choose>
                                    <c:when test="${attr.position == 0}">
                                        <button class="button" style="padding:5px 12px;" disabled>&#10005;</button>
                                    </c:when>
                                    <c:otherwise>
                                        <button class="button">&#9650;</button>
                                    </c:otherwise>
                                </c:choose>
                                <c:choose>
                                    <c:when test="${attr.position == attributes.totalElements - 1}">
                                        <button class="button" style="padding:5px 13px;" disabled>&#10005;</button>
                                    </c:when>
                                    <c:otherwise>
                                        <button class="button">&#9660;</button>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <tr>
                        <td class="has-text-grey-light" colspan="3">[Click Add to add an Attribute]</td>
                    </tr>
                </c:otherwise>
            </c:choose>
            </tbody>
        </table>

        <!-- paging -->
        <c:set var="page" value="${attributes}"/>
        <%@ include file="fragments/navigation.jsp" %>

    </div> <!-- /container -->
</div> <!-- /section -->

<!-- footer -->
<jsp:include page="fragments/footer.jsp"/>


<script>
    $("[data-delete]").on("click", function () {
        var id = $(this).attr("data-delete");
        if (id !== undefined && confirm("Are you sure?")) {
            window.location = "<s:url value="/product/"/>" + id + "/delete";
        }
    });

    $("#attrTable").find("> tbody > tr").on("click", function () {
        var id = $(this).attr("data-id");
        if (id !== undefined) {
            window.location.href = "<s:url value="/product/${product.id}/attribute/"/>" + id;
        }
    });
</script>

</body>
</html>
