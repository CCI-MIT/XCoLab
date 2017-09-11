$(function() {
    // Register clickHandler on document to support dynamically added anchor tags.
    $(document).on("click", "a.js-PostLink", function (eventObject) {
        // If confirm data field is available, ask user for confirmation first.
        var confirmText = $(this).data('confirm');
        if (confirmText && !confirm(confirmText)) {
            return false;
        }

        // Prevent default link behavior (GET this.href).
        eventObject.preventDefault();

        // Create a new POST form element.
        var form = document.createElement("form");
        form.method = "POST";
        form.action = $(this).data('url');

        // Add a CSRF token as an input field to the form.
        var csrfInput = document.createElement("input");
        csrfInput.value = $("meta[name='_csrf']").attr("content");
        csrfInput.name = '_csrf';
        csrfInput.type = 'hidden';
        form.appendChild(csrfInput);

        // Add the newly created form to the document body.
        document.body.appendChild(form);

        // Submit the form to send the POST request.
        form.submit();
    });
});
