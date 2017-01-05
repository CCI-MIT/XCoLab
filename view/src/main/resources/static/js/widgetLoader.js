jQuery(function() {
    jQuery( ".colabwidget" ).each(function( index ) {
        var articleId = jQuery( this ).attr("data-article-id");
        if(articleId){
            jQuery( this ).load( "/contentdisplay?contentArticleId="+ articleId);
        }else{
            var dataurl = jQuery( this ).attr("data-url");
            jQuery( this ).load( ""+ dataurl);
        }
    });
});