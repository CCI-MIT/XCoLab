function insertParamAndGo(key, value) {
    key = encodeURI(key);
    value =  encodeURI(value);
    var loc = window.location;

    var finalUrl = loc.protocol + "//" + loc.host + loc.pathname;
    var paramstr = loc.search.replace("?","");
    var params = paramstr.split("&");
    var finalParams = [];

    for(var param in params){
        if(params[param].indexOf(key+"=")==-1){
            finalParams.push(params[param]);
        }
    }
    finalParams.push(key+"="+value);
    var allParams = "";
    for(var param in finalParams){
        allParams += ((param==0)?("?"):("&")) + finalParams[param];
    }
    finalUrl = finalUrl + allParams;
    window.location.href =finalUrl + loc.hash;

}

function initTreeWithDynatree() {
    var treeContainer = jQuery(".jsTreeContainer");
    if (treeContainer.length > 0) {
	    var nodeKey = location.hash.toString();

	    if (nodeKey.length > 0) {
    		var node = jQuery(nodeKey);
    		if (node.length > 0) {
    			// remove expanded, active, selected from all nodes
    			jQuery(".active, .expanded, .selected", treeContainer).removeClass("active").removeClass("expanded").removeClass("selected");
    			node.addClass("expanded").addClass("selected");
    			node.parents("li").addClass("expanded");
    		}
    	}
    	treeContainer.dynatree({onActivate: function(node) { 
            if( node.data.href ){
               //   use href attribute
                window.location.href = node.data.href; 
        	}
    	}});
    	
    	treeContainer.find(".externallink").each(function() {
    		var linkSpan = jQuery(this);
    		var linkAnchor = jQuery("<a href=\"" + linkSpan.attr("href") + "\" class=\"externallink\">" + linkSpan.text() + "</a>");
    		linkAnchor.click(function() {
    			window.location.href = linkAnchor.attr("href");
    		});
    		linkSpan.replaceWith(linkAnchor);
    	});
    	jQuery(".jsTreeExpandDefaultToggle a").click(expandDynatree);
    }
}

function expandDynatree() { 
	var treeContainer = jQuery(".jsTreeContainer");
	if (treeContainer.hasClass('expanded')) window.location.reload();
	treeContainer.addClass("expanded");
	treeContainer.dynatree("getTree").visit(function(node) {node.expand(true); }, true); 
	jQuery(".jsTreeExpandDefaultToggle").toggle();
	treeContainer.find(".externallink").each(function() {
    		var linkSpan = jQuery(this);
    		var linkAnchor = jQuery("<a href=\"" + linkSpan.attr("href") + "\" class=\"externallink\">" + linkSpan.text() + "</a>");
    		linkAnchor.click(function() {
    			window.location.href = linkAnchor.attr("href");
    		});
    		linkSpan.replaceWith(linkAnchor);
    	});
	
	return false;
}

function removeMSWordChars(str) {
    var myReplacements = new Array();
    var myCode, intReplacement;
    myReplacements[8216] = 39;
    myReplacements[8217] = 39;
    myReplacements[8220] = 34;
    myReplacements[8221] = 34;
    myReplacements[8212] = 45;
    var result = [];
    for(c=0; c<str.length; c++) {
        var currentChar = str.charCodeAt(c);
        if(myReplacements[currentChar] != undefined) {
            currentChar = myReplacements[currentChar];
        }
        result.push(currentChar);
    }
    return result.join("");
}

function deferUntilLogin(source) {
 
    if (_isLoggedIn) {
        return true;
    } else {
        if (source != null) {
            var $source = $(source);
            jQuery("#signInForm_form input[name=redirect]").val($source.attr('href'));
        }
        $('#loginModal').modal();
    }
}

function deferUntilLoginTargeted(targetLocation) {

    if (_isLoggedIn) {
        return true;
    } else {
        if (targetLocation != null) {
            jQuery("#signInForm_form input[name=redirect]").val(targetLocation);
        }
        $('#loginModal').modal();
    }
}

function showSharedColabLogin(fn) {

	if (_isLoggedIn) {
		return true;
	} else {
		jQuery('#loginModal').modal('hide');
		jQuery('#ssoLoginModal').modal();
	}
}
var sharedContestAutoRegContestId = null;
var sharedContestAutoRegCallbackFunction = null;
function showSharedContestAutoRegPopUp(fn, contestId) {

	if (_isLoggedIn) {
		//if has cookie for contestId return true;
		var sharedContestConfirm = Cookies.get("sharedColab_" + contestId);
		if(sharedContestConfirm === undefined) {
			sharedContestConfirm = false;
		}
		if(sharedContestConfirm){
			return true;
		}

		sharedContestAutoRegCallbackFunction = fn;
		sharedContestAutoRegContestId = contestId;

		jQuery('#loinModal').modal('hide');
		jQuery('#autoRegistrationModal').modal();
		return false;
	} else {
		return deferUntilLogin();
	}
}

function handleOkForSharedColabAutoReg() {
	Cookies.set("sharedColab_"+sharedContestAutoRegContestId, "true");
	if (sharedContestAutoRegCallbackFunction != null) {
		sharedContestAutoRegCallbackFunction.call(null);
	}
}
function handleNoForSharedColabAutoReg() {
	jQuery('#autoRegistrationModal').modal('hide');
}


function showForgotPasswordPopup() {
	jQuery('#loginModal').modal('hide');
	jQuery('#forgotPasswordModal').modal();
}
// Start in separate init functions to isolate failure

jQuery(function() {
    initTreeWithDynatree();
});

function submitenter(myfield,e)	{
    var keycode;
    if (window.event) keycode = window.event.keyCode;
    else if (e) keycode = e.which;
    else return true;

    if (keycode == 13) {
        jQuery(myfield.form).submit();
        return false;
    } else {
        return true;
    }
}

$(function() {
    $('.js-Tooltip').tooltipster({
        interactive: true,
        contentAsHtml: true
    });
});
