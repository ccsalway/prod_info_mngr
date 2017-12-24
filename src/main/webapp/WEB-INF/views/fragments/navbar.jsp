<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<nav class="navbar is-fixed-top" role="navigation" aria-label="main navigation">
    <div class="navbar-brand">
        <div class="navbar-item">
            <img src="https://bulma.io/images/bulma-logo.png" width="112" height="28" alt="Bulma">
        </div>
        <button class="button navbar-burger">
            <span></span>
            <span></span>
            <span></span>
        </button>
    </div>
    <div class="navbar-menu">
        <div class="navbar-start">
            <a class="navbar-item" href="<s:url value="/products"/>">
                Products
            </a>
        </div>
    </div>
</nav>