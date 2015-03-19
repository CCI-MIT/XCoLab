
function disableAddComment() {
    jQuery("#thecomment .addCommentButton").attr('disabled', true);
}
function isAddCommentFormValid() {
    var isValid = (jQuery.trim(jQuery("#thecomment .commentContent").val()) != '');

    if (isValid) {
        jQuery('#thecomment .errorMsg').hide();
    }
    else {
        jQuery('#thecomment .errorMsg').show();
    }
    return isValid;
}

function editComment(messageId, url){
    var comment = extractText('message_' + messageId);
    $('#message_' + messageId).empty();
    var formContent = '<form method="post" action="' + url + '">';
    formContent += '<textarea id="text_' + messageId + '" name="comment" style="width: 100%; height: 150px;"></textarea>';
    formContent += '<input name="messageId" type="hidden" value="' + messageId + '"/>';
    formContent += '<div class="blue-button" style="margin-left: 320px; margin-top: 10px;"><a onclick=" $(this).parents(\'form:first\').submit()" type="submit" href="javascript:;">Save</a></div>';
    formContent += '</form>';
    $('#message_' + messageId).append(formContent);
    $('#message_' + messageId).next().remove();

    $('#text_'+messageId).text(comment);
}

function extractText(elementId) {
    var html = jQuery('#' + elementId).html();
    html = html.replace(new RegExp('<br></br>', 'g'), '"')
        .replace(new RegExp('<br/>', 'g'), '"')
        .replace(new RegExp('<br>', 'g'), '"');

    console.log("html: " + html);

    jQuery('#' + elementId).html(html);
    console.log("text: " + jQuery('#' + elementId).text());
    var text = jQuery('#' + elementId).text().replace(new RegExp('" ', 'g'), '\n').replace(new RegExp('"', 'g'), '\n');

    // Fix weird leading white space browser behaviour
    if (text.charAt(0) == " ") {
        return text.substring(1);
    } else {
        return text;
    }
}

/**
 Update add this urls to messages
 **/
jQuery(function() {
    if ($("#messageContent").length > 0) {
        var baseLocation = window.location.toString();
        if (baseLocation.indexOf("#") >= 0) {
            baseLocation = baseLocation.substring(0, baseLocation.indexOf("#"));
        }
        jQuery(".message_add_this").each(function() {
            var self = jQuery(this);
            self.attr("addthis:url", baseLocation + self.attr("data-hash"));
        });

        //restore comment content from a previously set cookie.
        if ($("#messageContent").val() == "" && $.cookie("proposal-comment-body")) {
            $("#messageContent").val($.cookie("proposal-comment-body"));
        }

        //submit button functionality for adding new comments
        $("#addCommentButton").click(function() {
            //save the comment in a cookie, in case the user is not logged in
            $.cookie("proposal-comment-body", $("#messageContent").val(), {path: "/"});

            if ($(this).attr("data-is-deferred") == "true") {
                deferUntilLogin();
            } else {
                if (! window.isAddCommentFormValid()) {
                    return false;
                }
                window.disableAddComment();
                $('#addCommentForm').submit();
            }
        });
    }
});