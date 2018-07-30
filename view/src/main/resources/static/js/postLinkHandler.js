$(function() {
    // Register clickHandler on document to support dynamically added anchor tags.
    $(document).on("click", ".js-EnhancedLink", function (eventObject) {

        var isLoginRequired = $(this).data('require-login');
        if (!_isLoggedIn && isLoginRequired) {
            $('#loginModal').modal();
            return false;
        }

        // If confirm data field is available, ask user for confirmation first.
        var confirmText = $(this).data('confirm');
        if (confirmText && !confirm(confirmText)) {
            return false;
        }

        if ($(this).data('method') == 'post') {
            // Prevent default link behavior (GET this.href).
            eventObject.preventDefault();

            // Create a new POST form element.
            var form = document.createElement("form");
            form.method = "POST";
            form.action = $(this).data('url') ? $(this).data('url') : $(this).attr('href');

            // Add a CSRF token as an input field to the form.
            var csrfInput = document.createElement("input");
            csrfInput.value = window._csrf.token;
            csrfInput.name = window._csrf.parameterName;
            csrfInput.type = 'hidden';
            form.appendChild(csrfInput);

            // Add the newly created form to the document body.
            document.body.appendChild(form);

            // Submit the form to send the POST request.
            form.submit();
        }
    });
});
