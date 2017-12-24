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
                    <h1 class="title">Products</h1>
                </div>
            </div>
            <div class="level-right">
                <div class="level-item">
                    <a class="button is-info" href="<s:url value="/product/add"/>">
                        New
                    </a>
                </div>
            </div>
        </div>
        <div>
            <table id="dataTable" class="table is-hoverable is-striped is-fullwidth" style="cursor: pointer;">
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
                                <td class="is-narrow has-text-centered">
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
                            <td class="has-text-grey-light" colspan="2">[Click New to add a Product]</td>
                        </tr>
                    </c:otherwise>
                </c:choose>
                </tbody>
            </table>
        </div>
    </div>
    <c:set var="page" value="${products}"/>
    <%@ include file="../fragments/navigation.jsp" %>
</div>
<%@ include file="../fragments/footer.jsp" %>

<script>
    $("#dataTable").find("> tbody > tr").on("click", function () {
        var id = $(this).attr("data-id");
        if (id !== undefined) {
            window.location.href = "<s:url value="/product/"/>" + id;
        }
    });
</script>

</body>
</html>
