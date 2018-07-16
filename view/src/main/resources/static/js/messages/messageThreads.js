$(document).ready(function(){
    identifyPreviousMessages();
    removeSpaces();

    jQuery('#reply-button').click(function () {
        $( "#replyContainer" ).show();
    });

    jQuery('#close-reply').click(function () {
        $( "#replyContainer" ).css("display","none");
    });

    jQuery('#replyForm').submit(function(event){
        checkMessageForm(event);
            });

});
function identifyPreviousMessages(){
    var beginPattern="-- original message begin --";
    var endPattern="-- original message end --";
    var message=$("#messageContents")[0].innerHTML;

    while (message.search(beginPattern)!=-1 && message.search(endPattern)!=-1) {
        try {
            message=message.replace(beginPattern,"<div class=\"c-Previous__message\">");
            var pos=message.lastIndexOf(endPattern);
            message=message.substring(0,pos)+"</div>"+message.substring(pos+endPattern.length);

        } catch (e) {
            console.log(e);
        }
    }
    $("#messageContents")[0].innerHTML=message;
}
function removeSpaces(){
    $("#messageContents")[0].innerHTML=$("#messageContents")[0].innerHTML.replace(/<p>(&nbsp;|<br> *)<\/p>/g,'');
}
