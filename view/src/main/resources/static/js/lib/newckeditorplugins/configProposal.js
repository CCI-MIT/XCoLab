CKEDITOR.editorConfig = function( config )
{
    // Define changes to default configuration here. For example:
    // config.language = 'fr';
    // config.uiColor = '#AADC6E';
    config.toolbar = 'Colab';
    config.extraPlugins = 'justify,colorbutton,uploadimage,copyformatting,font,indentblock,proposalLink';
    config.extraPlugins = 'resize,proposalLink,menubutton,dialog';
    config.toolbar_Colab =
        [
            { name: 'controls', items : [ 'Bold', 'Italic', 'Underline','NumberedList', 'BulletedList', 'Anchor', 'Link', 'Image', 'ProposalLink'] }
        ];

    config.toolbar = 'Colab';
    //config.skin = "colab";
    config.toolbarCanCollapse = false;
    config.contentsCss = '/css/themes/climateColab/main.css';
    config.resize_enabled = 'true';
    
    config.disableNativeSpellChecker = false;
    
};

CKEDITOR.on('dialogDefinition', function (e) {
    var dialogName = e.data.name;
    var dialogDefinition = e.data.definition;
    dialogDefinition.onShow = function () {

        if(dialogName == "image"){
            //data-tooltip-content="#js-Tooltip__content__points"
            //var fieldId = $(".cke_dialog_image_url input").attr("id");
            var dialog = CKEDITOR.dialog.getCurrent().getContentElement( 'info', 'txtUrl' ).getInputElement();
            var fieldId = $(dialog).attr("id");
            

            var entryId ="ckimageDialogHelp_"+fieldId;
            if($("#"+entryId).length!=1){

                $('<span>&#160;&#160;</span><span id="'+entryId+'" style="cursor: pointer;text-decoration: underline;">need help?<img   style="width: 15px; height: 15px;" src="/images/icon-addprop-question-bar.png" /></span><br/><br/><a href="http://imgur.com" target="_blank" class="cke_dialog_ui_button cke_dialog_ui_button_ok"><span class="cke_dialog_ui_button cke_dialog_ui_button_ok">UPLOAD</span></a>')
                        .insertAfter($("#"+fieldId));

                $('#'+entryId).tooltipster({
                    contentCloning: false,
                    contentAsHTML: true,
                    side:'right',
                    trigger: 'custom',
                    triggerOpen: {
                        mouseenter: true
                    },
                    triggerClose: {
                        click: true
                    },
                    content : $("#ckImageUploadTooltip").html()
                });
            }

        }
        if(dialogName == "anchor"){
            var dialog = CKEDITOR.dialog.getCurrent().getContentElement( 'info', 'txtName' ).getInputElement();
            var fieldId = $(dialog).attr("id");


            var entryId ="ckanchorHelp_"+fieldId;


            if($("#"+entryId).length!=1){
                $('<span>&#160;&#160;</span><span id="'+entryId+'" style="cursor: pointer;text-decoration: underline;">need help?<img   style="width: 15px; height: 15px;" src="/images/icon-addprop-question-bar.png" /></span>').insertAfter($("#"+fieldId).parent().parent().parent().find("label"));

                $('#'+entryId).tooltipster({
                    contentCloning: false,
                    contentAsHTML: true,
                    side:'right',
                    trigger: 'custom',
                    triggerOpen: {
                        mouseenter: true
                    },
                    triggerClose: {
                        click: true
                    },
                    content : $("#ckAnchorTooltip").html()
                });
            }
        }
    };
});
