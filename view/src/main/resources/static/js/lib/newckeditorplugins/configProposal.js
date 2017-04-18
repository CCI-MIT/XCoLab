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
