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

        <!-- title -->
        <h1 class="title is-spaced">Variants</h1>

        <h6 class="subtitle is-6" style="padding:10px 0">Select a Product to list its Variants</h6>

        <hr>

        <!-- table -->
        <table id="dataTable" class="table is-hoverable is-fullwidth" style="cursor: pointer;">
            <thead>
            <tr>
                <th>Name</th>
                <th class="is-narrow has-text-centered">Displayed</th>
            </tr>
            </thead>
            <tbody>
            <c:choose>
                <c:when test="${products.totalElements > 0}">
                    <c:forEach items="${products.content}" var="prod">
                        <tr data-id="${prod.id}">
                            <td>${fn:htmlEscape(prod.name)}</td>
                            <td class="is-narrow has-text-centered" style="padding-right:0">
                                <c:choose>
                                    <c:when test="${prod.displayed}">
                                        <i class="fa fa-eye"></i>
                                    </c:when>
                                    <c:otherwise>
                                        <i class="fa fa-eye-slash has-text-grey-light"></i>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <tr>
                        <td class="has-text-grey-light" colspan="2">[No products added]</td>
                    </tr>
                </c:otherwise>
            </c:choose>
            </tbody>
        </table>

        <!-- paging -->
        <c:set var="page" value="${products}"/>
        <%@ include file="fragments/navigation.jsp" %>

    </div> <!-- /container -->
</div> <!-- /section -->

<!-- footer -->
<jsp:include page="fragments/footer.jsp"/>


<script>
    $("#dataTable").find("> tbody > tr").on("click", function () {
        var id = $(this).attr("data-id");
        if (id !== undefined) {
            window.location.href = "<s:url value="/variants/"/>" + id;
        }
    });
</script>

</body>
</html>
