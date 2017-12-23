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
                    <h1 class="title">Option</h1>
                </div>
            </div>
            <div class="level-right">
                <div class="level-item">
                    <div class="field has-addons">
                        <div class="control">
                            <a class="button is-text" href="<s:url value="/product/${product.id}/attribute/${attribute.id}/option/${option.id}/delete"/>">
                                Delete
                            </a>
                        </div>
                        <div class="control">
                            <a class="button is-info" href="<s:url value="/product/${product.id}/attribute/${attribute.id}/option/${option.id}/edit"/>">
                                Edit
                            </a>
                        </div>
                        <div class="control" style="margin-left: 1em; padding-left: 1em; border-left: 1px solid #000;">
                            <a class="button" href="<s:url value="/product/${product.id}/attribute/${attribute.id}/option/add"/>">
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
                    <a href="<c:url value="/product/${product.id}"/>/attribute/${attribute.id}">${fn:htmlEscape(attribute.name)}</a>
                </p>
            </div>
        </div>
        <div class="field">
            <label class="label">Option Name</label>
            <div class="content">
                <p>
                    ${fn:htmlEscape(option.name)}
                </p>
            </div>
        </div>
    </div>
</div>
<%@ include file="../fragments/footer.jsp" %>

</body>
</html>
