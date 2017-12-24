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
        <nav class="breadcrumb">
            <ul>
                <li>/</li>
                <li><a href="<s:url value="/products"/>">Products</a></li>
                <li class="is-active"><a href="#">${fn:htmlEscape(product.name)}</a></li>
            </ul>
        </nav>
        <hr class="margin-top-small">
        <div class="field">
            <label class="label">Name</label>
            <div class="content">
                <p>
                    ${fn:htmlEscape(product.name)}
                </p>
            </div>
        </div>
        <div class="field">
            <label class="label">Description</label>
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
            <label class="label">Displayed</label>
            <div class="content">
                <p>
                    ${product.displayed ? "Yes" : "No"}
                </p>
            </div>
        </div>
        <div class="field">
            <div class="is-pulled-right">
                <a class="button is-info is-small" href="<s:url value="/product/${product.id}/attribute/add"/>">
                    Add
                </a>
            </div>
            <label class="label">Attributes</label>
            <div class="content" style="margin-top: 1rem">
                <table id="attrTable" class="table is-hoverable is-striped is-fullwidth" style="cursor:pointer;">
                    <thead>
                    <tr>
                        <th>Name</th>
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
                                        <div class="field is-grouped">
                                            <div class="control">
                                                <c:choose>
                                                    <c:when test="${attr.position == 0}">
                                                        <button class="button" style="padding:5px 12px;" disabled>
                                                            &#10005;
                                                        </button>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <button class="button">&#9650;</button>
                                                    </c:otherwise>
                                                </c:choose>
                                            </div>
                                            <div class="control">
                                                <c:choose>
                                                    <c:when test="${attr.position == attributes.totalElements - 1}">
                                                        <button class="button" style="padding:5px 13px;" disabled>
                                                            &#10005;
                                                        </button>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <button class="button">&#9660;</button>
                                                    </c:otherwise>
                                                </c:choose>
                                            </div>
                                        </div>
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
            </div>
            <c:set var="page" value="${attributes}"/>
            <%@ include file="../fragments/navigation.jsp" %>
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
