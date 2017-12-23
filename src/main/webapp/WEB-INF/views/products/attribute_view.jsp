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
        <div class="level">
            <div class="level-left">
                <div class="level-item">
                    <h1 class="title">Attribute</h1>
                </div>
            </div>
            <div class="level-right">
                <div class="level-item">
                    <div class="field has-addons">
                        <div class="control">
                            <a class="button is-text" href="<s:url value="/product/${product.id}/attribute/${attribute.id}/delete"/>">
                                Delete
                            </a>
                        </div>
                        <div class="control">
                            <a class="button is-info" href="<s:url value="/product/${product.id}/attribute/${attribute.id}/edit"/>">
                                Edit
                            </a>
                        </div>
                        <div class="control" style="margin-left: 1em; padding-left: 1em; border-left: 1px solid #000;">
                            <a class="button" href="<s:url value="/product/${product.id}/attribute/add"/>">
                                New
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <hr/>
        <div class="field">
            <label class="label">Product Name</label>
            <div class="content">
                <p>
                    <a href="<c:url value="/product/${product.id}"/>">${fn:htmlEscape(product.name)}</a>
                </p>
            </div>
        </div>
        <div class="field">
            <label class="label">Attribute Name</label>
            <div class="content">
                <p>
                    ${fn:htmlEscape(attribute.name)}
                </p>
            </div>
        </div>
        <div class="field">
            <div class="is-pulled-right">
                <a class="button is-info is-small" href="<s:url value="/product/${product.id}/attribute/${attribute.id}/option/add"/>">
                    Add
                </a>
            </div>
            <label class="label">Attribute Options</label>
            <div class="content">
                <table id="optTable" class="table is-hoverable is-striped is-fullwidth" style="cursor:pointer;">
                    <thead>
                    <tr>
                        <th>Option</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:choose>
                        <c:when test="${options.totalElements > 0}">
                            <c:forEach items="${options.content}" var="opt">
                                <tr data-id="${opt.id}">
                                    <td>${fn:htmlEscape(opt.name)}</td>
                                </tr>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <tr>
                                <td class="has-text-grey-light">[Click Add to add an Option]</td>
                            </tr>
                        </c:otherwise>
                    </c:choose>
                    </tbody>
                </table>
            </div>
            <c:set var="page" value="${options}" />
            <%@ include file="../fragments/navigation.jsp" %>
        </div>
    </div>
</div>
<%@ include file="../fragments/footer.jsp" %>

<script>
    $("#optTable").find("> tbody > tr").on("click", function () {
        var id = $(this).attr("data-id");
        window.location.href = "<s:url value="/product/${product.id}/attribute/${attribute.id}/option/"/>" + id;
    });
</script>

</body>
</html>
