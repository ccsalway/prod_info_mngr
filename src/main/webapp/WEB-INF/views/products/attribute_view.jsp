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
            <a class="button is-text" href="<s:url value="/product/${product.id}/attribute/add"/>">
                New Attribute
            </a>
            <div style="display: inline-block; border-right: 1px solid black; margin: 0 8px; height: 34px;"></div>
            <a class="button is-text" href="<s:url value="/product/${product.id}/attribute/${attribute.id}/delete"/>">
                Delete
            </a>
            <a class="button" href="<s:url value="/product/${product.id}/attribute/${attribute.id}/edit"/>">
                Edit
            </a>
        </div>
        <h1 class="title">Attribute</h1>
        <hr/>
        <div class="field">
            <label class="label">Product Name</label>
            <div class="control">
                <a href="<c:url value="/product/${product.id}"/>" class="input is-static">${fn:htmlEscape(product.name)}</a>
            </div>
        </div>
        <div class="field">
            <label class="label">Attribute Name</label>
            <div class="control">
                <p class="input is-static">${fn:htmlEscape(attribute.name)}</p>
            </div>
        </div>
        <div class="field">
            <div class="is-pulled-right">
                <a class="button is-info is-small" href="<s:url value="/product/${product.id}/attribute/${attribute.id}/option/add"/>">
                    Add
                </a>
            </div>
            <label class="label">Options</label>
            <div class="control">
                <table id="optTable" class="table is-hoverable is-striped is-fullwidth" style="cursor:pointer;">
                    <thead>
                    <tr>
                        <th>Name</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${options}" var="opt">
                        <tr data-id="${opt.id}">
                            <td>${fn:htmlEscape(opt.name)}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<script>
    $("#optTable").find("> tbody > tr").on("click", function () {
        var id = $(this).attr("data-id");
        window.location.href = "<s:url value="/product/${product.id}/attribute/${attribute.id}/option/"/>" + id;
    });
</script>

</body>
</html>
