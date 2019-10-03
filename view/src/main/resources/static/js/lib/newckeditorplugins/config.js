CKEDITOR.editorConfig = function( config ) {

    config.language = 'en';

    config.extraPlugins = 'justify,colorbutton,uploadimage,copyformatting,font,indentblock';
    config.uploadUrl = '/image/uploadCkEditor';
    config.filebrowserImageUploadUrl = '/image/uploadCkEditor?' + _csrf.parameterName + '=' + _csrf.token ;
    config.toolbar = 'custom';
    config.toolbar_custom = [
        { name: 'clipboard', groups: [ 'clipboard', 'undo' ], items: [ 'Cut', 'Copy', 'Paste', 'PasteText', 'PasteFromWord', '-', 'Undo', 'Redo' ,'-','CopyFormatting'] },
        { name: 'editing', groups: [ 'find', 'selection', 'spellchecker' ], items: [ 'Scayt' ] },
        { name: 'links', items: [ 'Link', 'Unlink', 'Anchor' ] },
        { name: 'insert', items: [ 'Image', 'Table', 'HorizontalRule', 'SpecialChar' ] },
        { name: 'tools', items: [ 'Maximize' ] },
        { name: 'document', groups: [ 'mode', 'document', 'doctools' ], items: [ 'Source' ] },
        { name: 'others', items: [ '-' ] },
        '/',
        { name: 'basicstyles', groups: [ 'basicstyles', 'cleanup' ], items: [ 'Bold', 'Italic', 'Strike', '-', 'RemoveFormat' , 'Subscript', 'Superscript' ] },
        { name: 'alignment', items : [ 'JustifyLeft', 'JustifyCenter', 'JustifyRight', 'JustifyBlock' ] },
        { name: 'paragraph', groups: [ 'list', 'indent', 'blocks', 'align', 'bidi' ], items: [ 'NumberedList', 'BulletedList', '-', 'Outdent', 'Indent', '-', 'Blockquote' ] },
        '/',
        { name: 'styles', items: [ 'Styles', 'Format' ] },

        { name: 'colors', items: [ 'FontSize','TextColor', 'BGColor' ] }

    ];}
