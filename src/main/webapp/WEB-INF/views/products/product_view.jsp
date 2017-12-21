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
        <div class="is-pulled-right">
            <a class="button is-text" href="<s:url value="/product/add"/>">
                New Product
            </a>
            <div style="display: inline-block; border-right: 1px solid black; margin: 0 8px; height: 34px;"></div>
            <a class="button is-text" href="<s:url value="/product/${product.id}/delete"/>">
                Delete
            </a>
            <a class="button" href="<s:url value="/product/${product.id}/edit"/>">
                Edit
            </a>
        </div>
        <h1 class="title">Product</h1>
        <hr/>
        <div class="field">
            <label class="label">Product Name</label>
            <div class="control">
                <p class="input is-static">${fn:escapeHtml(product.name)}</p>
            </div>
        </div>
        <div class="field">
            <div class="is-pulled-right">
                <a class="button is-info is-small" href="<s:url value="/product/${product.id}/attribute/add"/>">
                    Add
                </a>
            </div>
            <label class="label">Attributes</label>
            <div class="control">
                <table id="attrTable" class="table is-hoverable is-striped is-fullwidth" style="cursor:pointer;">
                    <thead>
                    <tr>
                        <th>Name</th>
                        <th>Options</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${product.attributes}" var="attr">
                        <tr data-id="${attr.id}">
                            <td>${fn:escapeHtml(attr.name)}</td>
                            <td><c:forEach items="${attr.options}" var="opt" varStatus="loop">
                                ${opt.name}<c:if test="${!loop.last}">,</c:if>
                            </c:forEach></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<script>
    $("#attrTable").find("> tbody > tr").on("click", function () {
        var id = $(this).attr("data-id");
        window.location.href = "<s:url value="/product/${product.id}/attribute/"/>" + id;
    });
</script>

</body>
</html>
