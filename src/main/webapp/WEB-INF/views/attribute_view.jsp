<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tlds/functions.tld" %>
<!DOCTYPE html>
<html lang="en" class="has-navbar-fixed-top">
<head>
    <jsp:include page="fragments/header.jsp"/>
    <title>Attribute</title>
</head>
<body>

<!-- navbar -->
<jsp:include page="fragments/navbar.jsp"/>

<!-- content -->
<div class="section">
    <div class="container is-fluid">

        <!-- title and buttons -->
        <div class="is-pulled-right">
            <button class="button is-text" data-delete="${attribute.id}">Delete</button>
            <a class="button is-info" href="<s:url value="/product/${product.id}/attribute/${attribute.id}/edit"/>">Edit</a>
        </div>
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

        <!-- items -->
        <div class="block">
            <h5 class="title is-5">Name</h5>
            <p>${fn:htmlEscape(attribute.name)}</p>
        </div>

        <div class="block">
            <h5 class="title is-5">Displayed</h5>
            <p>${attribute.displayed ? "Yes" : "No"}</p>
        </div>

        <!-- options -->
        <div class="is-pulled-right">
            <a class="button is-info" href="<s:url value="/product/${product.id}/attribute/${attribute.id}/option/add"/>">Add</a>
        </div>
        <h5 class="title is-5">Options</h5>

        <table id="optsTable" class="table is-hoverable is-fullwidth" style="cursor:pointer;">
            <thead>
            <tr>
                <th>Option</th>
                <th class="is-narrow has-text-centered">Displayed</th>
                <th class="is-narrow has-text-centered">Position</th>
            </tr>
            </thead>
            <tbody>
            <c:choose>
                <c:when test="${options.totalElements > 0}">
                    <c:forEach items="${options.content}" var="opt">
                        <tr data-id="${opt.id}">
                            <td>${fn:htmlEscape(opt.name)}</td>
                            <td class="is-narrow has-text-centered">
                                <c:choose>
                                    <c:when test="${opt.displayed}">
                                        <i class="fa fa-eye"></i>
                                    </c:when>
                                    <c:otherwise>
                                        <i class="fa fa-eye-slash has-text-grey-light"></i>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td class="is-narrow">
                                <c:choose>
                                    <c:when test="${opt.position == 0}">
                                        <button class="button" style="padding:5px 12px;" disabled>&#10005;</button>
                                    </c:when>
                                    <c:otherwise>
                                        <button class="button">&#9650;</button>
                                    </c:otherwise>
                                </c:choose>
                                <c:choose>
                                    <c:when test="${opt.position == options.totalElements - 1}">
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
                        <td class="has-text-grey-light" colspan="3">[Click Add to add an Option]</td>
                    </tr>
                </c:otherwise>
            </c:choose>
            </tbody>
        </table>

        <!-- paging -->
        <c:set var="page" value="${options}"/>
        <%@ include file="fragments/navigation.jsp" %>

    </div> <!-- /container -->
</div> <!-- /section -->

<!-- footer -->
<jsp:include page="fragments/footer.jsp"/>


<script>
    $("[data-delete]").on("click", function () {
        var id = $(this).attr("data-delete");
        if (id !== undefined && confirm("Are you sure?")) {
            window.location = "<s:url value="/product/${product.id}/attribute/"/>" + id + "/delete";
        }
    });

    $("#optsTable").find("> tbody > tr").on("click", function () {
        var id = $(this).attr("data-id");
        if (id !== undefined) {
            window.location.href = "<s:url value="/product/${product.id}/attribute/${attribute.id}/option/"/>" + id;
        }
    });
</script>

</body>
</html>
