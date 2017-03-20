
function disableAddComment() {
    jQuery(".c-Comment__new").find(".addCommentButton").attr('disabled', true);
}
function isAddCommentFormValid() {
    var $thecomment = jQuery(".c-Comment__new");
    var isValid = (jQuery.trim($thecomment.find(".commentContent").val()) != '');
    if (!isValid) {
        isValid = jQuery.trim(CKEDITOR.instances.messageContent.getData()) != '';
    }

    if (isValid) {
        $thecomment.find('.errorMsg').hide();
    }
    else {
        $thecomment.find('.errorMsg').show();
    }
    return isValid;
}

function editComment(messageId, url){
    var comment = jQuery('#' + 'message_' + messageId).html(); //extractText('message_' + messageId);
    var $message = $('#message_' + messageId);
    $message.empty();
    if (!_isAdmin) {
        $message.append('<div class="c-Alert__info__message">Please make sure you save your edit within 15 minutes of creating this comment.</div>');
    }
    var formContent = '<form method="post" action="' + url + '">';
    formContent += '<textarea class="rte-editorPlaceholder" id="text_' + messageId + '" name="comment" style="width: 100%; height: 150px;"></textarea>';
    formContent += '<input name="messageId" type="hidden" value="' + messageId + '"/>';
    formContent += '<a class="c-Button__primary" style="margin-left: 320px; margin-top: 10px;" onclick="disableDirtyCheck(); $(this).parents(\'form:first\').submit();" type="submit" href="javascript:;">Save</a>';
    formContent += '</form>';
    $message.append(formContent);
    $message.next().remove();

    $('#text_'+messageId).html(comment);
    initializeTextEditors();
}

function extractText(elementId) {
    var $element = jQuery('#' + elementId);
    var html = $element.html();
    html = html.replace(new RegExp('<br></br>', 'g'), '"')
        .replace(new RegExp('<br/>', 'g'), '"')
        .replace(new RegExp('<br>', 'g'), '"');

    console.log("html: " + html);

    $element.html(html);
    console.log("text: " + $element.text());
    var text = $element.text().replace(new RegExp('" ', 'g'), '\n').replace(new RegExp('"', 'g'), '\n');

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
    var $messageContent = $("#messageContent");
    if ($messageContent.length > 0) {
        var baseLocation = window.location.toString();
        if (baseLocation.indexOf("#") >= 0) {
            baseLocation = baseLocation.substring(0, baseLocation.indexOf("#"));
        }
        jQuery(".message_add_this").each(function() {
            var self = jQuery(this);
            self.attr("addthis:url", baseLocation + self.attr("data-hash"));
        });

        //restore comment content from a previously set cookie.
        if ($messageContent.val() == "" && $.cookie("proposal-comment-body")) {
            $messageContent.val($.cookie("proposal-comment-body"));
        }

        //submit button functionality for adding new comments
        $("#addCommentButton").click(function(event) {
            return handleClickOnDiscussion(event);
        });
    }
});
function handleClickOnDiscussion(event){
    //save the comment in a cookie, in case the user is not logged in

    if($("#cke_messageContent iframe") == null || $("#cke_messageContent iframe").contents().find("body").text() == "") {
        $.removeCookie("proposal-comment-body", {path: "/"});
        $.cookie("proposal-comment-body", $("#messageContent").val(), {path: "/"});
    } else {
        $.removeCookie("proposal-comment-body", {path: "/"});
        $.cookie("proposal-comment-body", $("#cke_messageContent iframe").contents().find("body").text(), {path: "/"});
    }

    if ($("#addCommentButton").attr("data-is-deferred") == "true") {
        deferUntilLogin();
    } else {
        if (! window.isAddCommentFormValid()) {
            event.preventDefault();
            return false;
        }
        disableDirtyCheck();
        window.disableAddComment();

        if(getMustFilterContent()) {
            var text = "";
            if(CKEDITOR.instances.messageContent === undefined) {
                var $thecomment = jQuery(".c-Comment__new");
                text = $thecomment.find(".commentContent").val();
            }else{
                text = CKEDITOR.instances.messageContent.getData();
            }
            handleFilteredContent(text,"DISCUSSION", "#filtering_uuid",function () { $('#addCommentForm').submit() });
            event.preventDefault();
            return false;
        } else {
            $('#addCommentForm').submit();
        }

    }
}
function handleFilteredContent(textInput, source, uuidField, callback){

    $("#processedFailed").hide();
    $("#modal_filtering_prof").modal({escapeClose: true, clickClose: false, showClose: true});
    var parameters ={
        fullText: textInput,
        source : source
    };
    $.post("/profanityfiltering/" ,parameters , function (doc, suc, response) {
        var responseData = JSON.parse(response.responseText);

        if (responseData.valid == false) {
            $("#disallowed_words").html(responseData.offensiveTerm)
            $("#processedFailed").show();
            
            $("#loading_filtering_image").hide();
        } else {
            var uuid = responseData.uuid;
            $(uuidField).val(uuid);
            callback.call(null);
        }
    });
}