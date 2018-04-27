function getCkEditorTextfieldContent(textfieldId) {
    var val = jQuery.trim($('#' + textfieldId).val());
    if (val === '') {
        val = jQuery.trim(CKEDITOR.instances[textfieldId].getData())
    }
    return val;
}

function setCkEditorTextfieldContent(textfieldId, content) {
    $('#' + textfieldId).val(content);
    var ckeditorInstance = CKEDITOR.instances[textfieldId];
    if (ckeditorInstance !== undefined) {
        ckeditorInstance.setData(content);
    }
}

function isAddCommentFormValid() {
    var $thecomment = jQuery(".c-Comment__new");
    var isValid = getCkEditorTextfieldContent('js-Comment__content') !== '';

    if (isValid) {
        $thecomment.find('#js-Comment__error').hide();
    } else {
        $thecomment.find('#js-Comment__error').show();
    }
    return isValid;
}


function getTimeRemaining(endtime) {
    var t = Date.parse(endtime) - Date.parse(new Date());
    var seconds = Math.floor((t / 1000) % 60);
    var minutes = Math.floor((t / 1000 / 60) % 60);
    var hours = Math.floor((t / (1000 * 60 * 60)) % 24);
    var days = Math.floor(t / (1000 * 60 * 60 * 24));
    return {
        'total': t,
        'days': days,
        'hours': hours,
        'minutes': minutes,
        'seconds': seconds
    };
}

function initializeClock(id, endtime) {
    var clock = document.getElementById(id);
    //var daysSpan = clock.querySelector('.days');
    //var hoursSpan = clock.querySelector('.hours');
    var minutesSpan = clock.querySelector('.minutes');
    var secondsSpan = clock.querySelector('.seconds');

    function updateClock() {
        var t = getTimeRemaining(endtime);

        //daysSpan.innerHTML = t.days;
        //hoursSpan.innerHTML = ('0' + t.hours).slice(-2);
        minutesSpan.innerHTML = ('0' + t.minutes).slice(-2);
        secondsSpan.innerHTML = ('0' + t.seconds).slice(-2);

        if (t.total <= 0) {
            clearInterval(timeinterval);
        }
    }

    updateClock();
    var timeinterval = setInterval(updateClock, 1000);
}




function editComment(commentCreationTimestamp,messageId, url){
    var comment = jQuery('#' + 'message_' + messageId).html(); //extractText('message_' + messageId);
    var $message = $('#message_' + messageId);
    $message.empty();
    if (!_isAdmin) {
        $message.append('<div class="alert alert-info">Please make sure you save your edit within 15 minutes of creating this comment. Time left: <span id="clockdiv"> <span class="minutes"></span> minutes <span class="seconds"></span> seconds</span>.</div>');

        var deadline = new Date((commentCreationTimestamp) +  15 * 60 * 1000);
        initializeClock('clockdiv', deadline);
    }
    var formContent = '<form method="post" action="' + url + '">';
    formContent += '<textarea class="rte-editorPlaceholder" id="text_' + messageId + '" name="comment" style="width: 100%; height: 150px;"></textarea>';
    formContent += '<input name="_csrf" type="hidden" value="' + window._csrf.token + '"/>';
    formContent += '<input name="messageId" type="hidden" value="' + messageId + '"/>';
    formContent += '<a class="btn btn-primary" style="margin-left: 320px; margin-top: 10px;" onclick="disableDirtyCheck(); $(this).parents(\'form:first\').submit();" type="submit" href="javascript:;">Save</a>';
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

jQuery(function() {
    //submit button functionality for adding new comments
    $("#addCommentButton").click(handleClickOnDiscussion);
});

function handleClickOnDiscussion(event) {
    if (!window.isAddCommentFormValid()) {
        event.preventDefault();
        return false;
    }

    saveCommentInCookie();
    disableDirtyCheck();
    $('#addCommentForm').submit();
}

function saveCommentInCookie() {
    var commentContent = getCkEditorTextfieldContent('js-Comment__content');
    Cookies.remove("proposal-comment-body");
    Cookies.set("proposal-comment-body", commentContent);
}

$(restoreCommentFromCookie);
function restoreCommentFromCookie() {
    var cookieContent = Cookies.get("proposal-comment-body");
    var $messageContent = $("#js-Comment__content");
    if (cookieContent !== '') {
        if ($messageContent.val() !== '') {
            console.warn("Could not restore comment because text field was already filled.");
        } else  {
            setCkEditorTextfieldContent('js-Comment__content', cookieContent);
        }
    }
}
