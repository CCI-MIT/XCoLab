$(function() {
    $(".js-PostLink").click(function (eventObject) {
        var confirmText = $(this).data('confirm');
        if (confirmText && !confirm(confirmText)) {
            return false;
        }
        eventObject.preventDefault();
        var form = document.createElement("form");
        form.method = "POST";
        form.action = this.href;
        document.body.appendChild(form);
        form.submit();
    });
});
