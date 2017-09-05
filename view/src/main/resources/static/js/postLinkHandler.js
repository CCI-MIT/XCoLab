$(function() {
    $(document).on("click", "a.js-PostLink", function (eventObject) {
        eventObject.preventDefault();
        var form = document.createElement("form");
        form.method = "POST";
        form.action = this.href;
        document.body.appendChild(form);
        form.submit();
    });
});
