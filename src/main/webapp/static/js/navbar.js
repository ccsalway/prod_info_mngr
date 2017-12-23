$(document).ready(function () {

    $(".navbar-burger").on("click", function () {
        $(this).toggleClass("is-active")
            .closest(".navbar")
            .find(".navbar-menu")
            .toggleClass("is-active");
    })

});