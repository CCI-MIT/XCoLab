function initializeDropDowns() {
    jQuery("select").each(function() {
        eventsToBind = {
            change: function(event) {
                markEditorDirty(jQuery(jQuery("input[type='text'], textarea")[0]));
            }
        };
        jQuery(this).bind(eventsToBind);
    });
}