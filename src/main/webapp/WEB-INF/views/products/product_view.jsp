<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions" %>
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
                    <h1 class="title">Product</h1>
                </div>
            </div>
            <div class="level-right">
                <div class="level-item">
                    <div class="field has-addons">
                        <div class="control">
                            <a class="button is-text" href="<s:url value="/product/${product.id}/delete"/>">
                                Delete
                            </a>
                        </div>
                        <div class="control">
                            <a class="button is-info" href="<s:url value="/product/${product.id}/edit"/>">
                                Edit
                            </a>
                        </div>
                        <div class="control" style="margin-left: 1em; padding-left: 1em; border-left: 1px solid #000;">
                            <a class="button" href="<s:url value="/product/add"/>">
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
                    ${fn:htmlEscape(product.name)}
                </p>
            </div>
        </div>
        <div class="field">
            <label class="label">Product Description</label>
            <div class="content">
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
        </div>
        <div class="field">
            <div class="is-pulled-right">
                <a class="button is-info is-small" href="<s:url value="/product/${product.id}/attribute/add"/>">
                    Add
                </a>
            </div>
            <label class="label">Attributes</label>
            <div class="content">
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
                            <td>${fn:htmlEscape(attr.name)}</td>
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
<%@ include file="../fragments/footer.jsp" %>

<script>
    $("#attrTable").find("> tbody > tr").on("click", function () {
        var id = $(this).attr("data-id");
        window.location.href = "<s:url value="/product/${product.id}/attribute/"/>" + id;
    });
</script>

</body>
</html>
