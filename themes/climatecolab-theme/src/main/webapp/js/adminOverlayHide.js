
$( document ).ready(function() {
    setIconHelpHandler();
    showAdminOverlay();
});

function showAdminOverlay() {
    var childs = $('.portlet-topper-toolbar').children();
    if (childs.length != 0) {
        $('header.portlet-topper').show();
        $('header.portlet-topper .portlet-topper-toolbar').show();
    }
}

function setIconHelpHandler() {
    // Get all the help icons
    var elements = $('.taglib-icon-help');
    // hide the help text initially
    elements.find('.hide-accessible').hide();

    elements.mouseover(function() {
        $(this).find('.hide-accessible').show();
    });

    elements.mouseleave(function() {
        $(this).find('.hide-accessible').hide();
    });
}