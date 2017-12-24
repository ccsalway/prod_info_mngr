<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tlds/functions.tld" %>
<!DOCTYPE html>
<html lang="en" class="has-navbar-fixed-top">
<head>
    <jsp:include page="fragments/header.jsp"/>
    <title>Variants</title>
</head>
<body>

<!-- navbar -->
<jsp:include page="fragments/navbar.jsp"/>

<!-- content -->
<div class="section">
    <div class="container is-fluid">

        <!-- title and buttons -->
        <div class="is-pulled-right">
            <a class="button is-info" href="<s:url value="/variants/${product.id}/add"/>">Add</a>
        </div>
        <h1 class="title">Variants</h1>

        <!-- breadcrumbs -->
        <nav class="breadcrumb" aria-label="breadcrumbs">
            <ul>
                <li><a href="<s:url value="/variants"/>">Products</a></li>
                <li class="is-active"><a href="#" aria-current="page">${fn:htmlEscape(product.name)}</a></li>
            </ul>
        </nav>

        <hr>

        <!-- table -->
        <table id="dataTable" class="table is-hoverable is-fullwidth" style="cursor: pointer;">
            <thead>
            <tr>
                <th>Variant</th>
            </tr>
            </thead>
            <tbody>
            <c:choose>
                <c:when test="${variants.totalElements > 0}">
                    <c:forEach items="${variants.content}" var="vari">
                        <tr data-id="${vari.id}">
                            <td>${fn:htmlEscape(vari.name)}</td>
                        </tr>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <tr>
                        <td class="has-text-grey-light" colspan="2">[Click Add to add a Variant]</td>
                    </tr>
                </c:otherwise>
            </c:choose>
            </tbody>
        </table>

        <!-- paging -->
        <c:set var="page" value="${variants}"/>
        <%@ include file="fragments/navigation.jsp" %>

    </div> <!-- /container -->
</div> <!-- /section -->

<!-- footer -->
<jsp:include page="fragments/footer.jsp"/>


<script>
    $("#dataTable").find("> tbody > tr").on("click", function () {
        var id = $(this).attr("data-id");
        if (id !== undefined) {
            window.location.href = "<s:url value="/variant/"/>" + id;
        }
    });
</script>

</body>
</html>
