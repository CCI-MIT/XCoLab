
function disableAddComment() {
    jQuery(".c-Comment__new .addCommentButton").attr('disabled', true);
}
function isAddCommentFormValid() {
    var isValid = (jQuery.trim(jQuery(".c-Comment__new .commentContent").val()) != '');

    if (isValid) {
        jQuery('.c-Comment__new .errorMsg').hide();
    }
    else {
        jQuery('.c-Comment__new .errorMsg').show();
    }
    return isValid;
}

function editComment(messageId, url){
    var comment = extractText('message_' + messageId);
    $('#message_' + messageId).empty();
    var formContent = '<form method="post" action="' + url + '">';
    formContent += '<textarea id="text_' + messageId + '" name="comment" style="width: 100%; height: 150px;"></textarea>';
    formContent += '<input name="messageId" type="hidden" value="' + messageId + '"/>';
    formContent += '<div class="c-Button__primary" style="margin-left: 320px; margin-top: 10px;"><a onclick=" $(this).parents(\'form:first\').submit()" type="submit" href="javascript:;">Save</a></div>';
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
                    event.preventDefault();
                    return false;
                }
                window.disableAddComment();


                if(getMustFilterContent()) {
                    var text = jQuery(".c-Comment__new .commentContent").val();


                    return false;;
                } else {
                    $('#addCommentForm').submit();
                }

            }
        });
    }
});

function handleFilteredContent(textInput, source, uuidField, callback){

    $("#processedFailed").hide();
    $("#modal_filtering_prof").modal({escapeClose: true, clickClose: false, showClose: true});
    var parameters ={
        fullText: textInput,
        source : source
    };
    $.post("/profanityfiltering/" ,parameters , function ( doc, suc, response) {
        var responseData = JSON.parse(response.responseText);

        if (responseData.valid == "false") {
            $("#processedFailed").show();
        } else {
            var uuid = responseData.uuid;
            $(uuidField).val(uuid);
            callback.call(null);
        }
    });
}