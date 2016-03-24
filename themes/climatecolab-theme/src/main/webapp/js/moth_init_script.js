function initSearchUpperBox() {
	jQuery("#searchPopupTrigger").click(function() {
		jQuery("#searchPopupContainer").fadeIn("fast");
		jQuery("#searchinput").focus();
	});
	
	function hideIfSearchNotUsed() {
		if (! jQuery('#searchinput').hasClass('focus') && ! jQuery('#searchPopupContainer').hasClass('mouseover')) {
			jQuery("#searchPopupContainer").fadeOut("fast");
		}
	}
	jQuery("#searchPopupContainer").mouseleave(function() {
		jQuery("#searchPopupContainer").removeClass('mouseover');
		hideIfSearchNotUsed();
	});
	jQuery("#searchPopupContainer").mouseenter(function() {
		jQuery("#searchPopupContainer").addClass('mouseover');
	});
	
	
	jQuery("#searchinput").focus(function() {
		jQuery(this).addClass("focus");
	});
	
	jQuery("#searchinput").blur(function() {
		jQuery(this).removeClass("focus");
		hideIfSearchNotUsed();
	});
	
	// submit on enter
	jQuery("#searchinput").keypress(function(e){
		if(e.which == 13){
			jQuery('#searchsubmit').click();
			return false;
	    }
	});
	
	
	jQuery("#searchsubmit").click(function() {
		var searchPhrase = encodeURI(jQuery('#searchinput').val());
		if (searchPhrase == 'Search') {
			searchPhrase = '';
		}
		window.location = "/web/guest/search/-/search/for/" + searchPhrase;
	});
		
}

function initLoginPopupUpper() {
	jQuery("#loginPopupTrigger").click(function() {
		jQuery("#loginPopupContainer").fadeIn("fast");
		jQuery("#loginPopupContainer .username").focus();
	});
	
	function hideIfLoginNotUsed() {
		if (! jQuery('#loginPopupContainer').hasClass('mouseover') && jQuery('#loginPopupContainer .focus').length == 0) {
			jQuery("#loginPopupContainer").fadeOut("fast");
		}
	}
	
	jQuery("#loginPopupContainer").mouseenter(function() {
		jQuery("#loginPopupContainer").addClass('mouseover');
	});

	jQuery("#loginPopupContainer input, #loginPopupContainer a").focus(function() {
		jQuery(this).addClass('focus');
	});
	
	jQuery("#loginPopupContainer input, #loginPopupContainer a").blur(function() {
		jQuery(this).removeClass('focus');
		setTimeout(hideIfLoginNotUsed, 10);
	});
	
	jQuery("#loginPopupContainer").mouseleave(function() {
		jQuery("#loginPopupContainer").removeClass('mouseover');
		setTimeout(hideIfLoginNotUsed, 10);
		
	});
	
	jQuery("#loginPopupTopSubmit").click(function() {
		jQuery("#signInFormPopup input[name=redirect]").val(location.toString());
	});
}

function initUserInfoPopup() {
	jQuery("#userPopupTrigger").click(function() {
		jQuery("#userPopupContainer").fadeIn("fast");
		jQuery("#userPopupContainer").focus();
	});
	
	function hideIfLoginNotUsed() {
		if (! jQuery('#userPopupContainer').hasClass('mouseover') && jQuery('#userPopupContainer a.focus').length == 0) {
			jQuery("#userPopupContainer").fadeOut("fast");
		}
	}
	
	jQuery("#userPopupContainer").mouseenter(function() {
		jQuery("#userPopupContainer").addClass('mouseover');
	});

	jQuery("#userPopupContainer a").focus(function() {
		jQuery("#userPopupContainer").addClass('focus');
	});
	
	jQuery("#userPopupContainer a").blur(function() {
		jQuery("#userPopupContainer").removeClass('focus');
		setTimeout(hideIfLoginNotUsed, 10);
	});
	
	jQuery("#userPopupContainer").mouseleave(function() {
		jQuery("#userPopupContainer").removeClass('mouseover');
		setTimeout(hideIfLoginNotUsed, 10);
		
	});
}

function initWikiBorderless() {
	jQuery(".wiki-borderless table, .wiki-borderless table td, .wiki-borderless table tr").removeAttr("border").css("border", 0);
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



function deferUntilLogin(fn) {
 
    if (Liferay.ThemeDisplay.isSignedIn()) {
        return true;
    } else {
    	jQuery('#popup_login').show();
    	jQuery("#signInForm_form input[name=redirect]").val(location.toString());
    }
}

function showForgotPasswordPopup() {
	jQuery('#popup_login').hide();
	jQuery('#popup_forgotpassword').show();
}

function deferUntilLoginTargeted(loc) {

    if (Liferay.ThemeDisplay.isSignedIn()) {
        return true;
    } else {
        jQuery('#popup_login').show();
        if (loc!=null) {
            jQuery("#signInForm_form input[name=redirect]").val(loc);
        } else {
            jQuery("#signInForm_form input[name=redirect]").val(location.toString());
        }
    }

}


function insertParam(key, value)
{
    key = escape(key); value = escape(value);

    var kvp = document.location.search.substr(1).split('&');

    if (kvp.length==1 && kvp[0].length==0) {
      kvp=[];
    }
    var i=kvp.length; var x; while(i--)
    {
        x = kvp[i].split('=');

        if (x[0]==key)
        {
                x[1] = value;
                kvp[i] = x.join('=');
                break;
        }
    }

    if(i<0) {kvp[kvp.length] = [key,value].join('=');}
    //this will reload the page, it's likely better to store this until finished
    document.location.search = kvp.length>1?kvp.join('&'):kvp[0];
}

function closePopup(obj) {
	jQuery(".c-Popup__wrapper").hide();
}



jQuery(document).ready(function() {
	initSearchUpperBox();
	initLoginPopupUpper();
	initUserInfoPopup();
	initWikiBorderless();
	initTreeWithDynatree();
	
	jQuery(".popup .close").click(function() {
		
		closePopup(this);
	});
	
	
	if (jQuery(".hp_boxwin").length > 0) {
		jQuery('.hp_boxwin').cycle({
		    fx:      'custom',
		    cssBefore: {  
		        top:  -365,  
		        left: 0,  
		                width: 540,
		                height:335,
		        opacity: 0
		    }, 
		    animIn: {  
		        top: 0,  
		        left: 0,  
		                width: 540,
		                height:335,
		        opacity: 1
		    }, 
		    animOut: {  
		        top:  350,  
		        left: 0,  
		        opacity: 0
		    }, 
		            timeout: 0,
		            pager:  '.hp_boxnav',
		            pagerAnchorBuilder: function(idx, slide) {
		                    // return selector string for existing anchor 
		                    return '.hp_boxnav li:eq(' + idx + ') a';
		                }
		        });
		
	}
	

	//jQuery('.popup_reg').hide();  
		jQuery('.openreg').click(function() {  
			jQuery('.popup_reg').fadeIn(300);
			jQuery('#content').fadeOut(300);
			jQuery('#foot_wrap').fadeOut(300);
			jQuery('.hp_box').fadeOut(300);
		}); 
		jQuery('.closereg').click(function() {  
			jQuery('.popup_reg').fadeOut(300);  
			jQuery('#content').fadeIn(300);
			jQuery('#foot_wrap').fadeIn(300);
			jQuery('.hp_box').fadeIn(300);
		});
	
	var footer = jQuery("#footmenu").next();
	footer.appendTo(jQuery("#foot_wrap"));
	
	jQuery('.close').click(function() { 
		jQuery('.chooseround li:eq(0) a').triggerHandler('click'); 
		return false; 
	});

    setTimeout(function() {
      jQuery("div.contestPhaseInfo:first .details h3").text("Round 1 completed, voting begins Nov. 1  (29 final proposals)");  
    },1000);
    
    
    jQuery(".closepopuplogin a").click(function() {
    	jQuery(".popup_login_form .c-Alert__error__message").remove();
    	jQuery(".popup_login_form .popup_login-message").show();
    	jQuery('.popup_login, .popup_forgotpassword').hide();
    });
    
    
    jQuery("#loginPopupCreateAccount").click(function() {
    	jQuery('.popup_login').hide();
    	
    	/* show registration form */
		jQuery('.popup_reg').fadeIn(300);
		jQuery('#content').fadeOut(300);
		jQuery('#foot_wrap').fadeOut(300);
		jQuery('.hp_box').fadeOut(300);
    });
    
    jQuery("#hdr_signin").mouseover(function() {
    	jQuery("#hdr_signin").show();
    });
    
    jQuery("#hdr_signin").mouseover(function() {
    	jQuery("#hdr_signin").show();
    });
    
    jQuery("#hdr_signin input, #hdr_signin a").focus(function() {
    	jQuery("#hdr_signin").addClass('inFocus');
    });

    jQuery("#hdr_signin input, #hdr_signin a").blur(function(event) {
        jQuery("#hdr_signin").removeClass('inFocus');
	setTimeout(function() {tryHidingSigninForm(event)}, 100);
    });
    
    jQuery("#hdr_signin").mouseout(function(event) {
        tryHidingSigninForm(event);
    });

    function tryHidingSigninForm(event) {
        // if any of the inputs / links has focus, stay opened
        if (jQuery("#hdr_signin").hasClass('inFocus')) return;

        var offset = jQuery('#hdr_signin').offset();
        var left = offset.left;
        var top = offset.top;
        var bottom = top + jQuery('#hdr_signin').height();
        var right = left + jQuery('#hdr_signin').width();
        var pageX = event.pageX;
        var pageY = event.pageY;

	// if cursor is over sign in form, return
        if (left <= pageX && pageX <= right && top <= pageY && pageY <= bottom) {
                return;
        }

	jQuery("#hdr_signin").hide();
    }
});

function initSelectbox() {
	if (jQuery('.selectbox1, .selectbox1-dis-dis').length > 0) {
		var selectboxOnChange = jQuery('.selectbox1').get(0).getAttribute("onchange");

		jQuery('.selectbox1').selectbox({
			inputClass: 'selectbox',
			onChangeCallback: function () {
				jQuery(".selectbox1").change();
				
				}
		});
	}
}

function onBeforeRegister() {
	jQuery('#createAccountForm').append(jQuery('<input type="hidden" value="' + window.location.toString() + '" name="redirect" />'));
}

function onBeforeLogin(formId) {
	jQuery('#' + formId).append(jQuery('<input type="hidden" value="' + window.location.toString() + '" name="redirect" />'));
}

function addRedirectBeforeSubmit(formId) {
	jQuery('#' + formId).append(jQuery('<input type="hidden" value="' + window.location.toString() + '" name="redirect" />'));
}

function processForgotPasswordForm(formId) {
	var screenName = jQuery('#' + formId + ' .screenName').val();
	if (screenName.indexOf('@') > 0) {
		jQuery('#' + formId).append('<input type="hidden" value="' + screenName + '" name="emailAddress" />');
	}
}


function updateBreadcrumb(placeholder, items) {
    var breadcrumb = [];
    for (var i = 0; i < items.length; i++) {
    	var item = items[i];
        breadcrumb[2*i] = '<img width="8" height="8" alt="" src="/climatecolab-theme/images/arrow.gif" /> ';
        breadcrumb[2*i + 1] = '<a href="' + item.href + '" onclick="' + item.onclick + '; return false;">' + item.text + '</a>';
    }

    jQuery(placeholder).html(breadcrumb.join(''));
}

	function submitenter(myfield,e)	{
		var keycode;
		if (window.event) keycode = window.event.keyCode;
		else if (e) keycode = e.which;
		else return true;

		if (keycode == 13) {
			jQuery(myfield.form).submit();
			return false;
		}
		else
			return true;
	}
	
	
function initTooltips() {
    function findTooltip(obj) {
        if (obj.next().hasClass('c-Tooltip')) {
            return obj.next();
        }
		var children = $(obj).children();
		for (i = 0; i < children.length; ++i) {
			if (jQuery(children[i]).hasClass('c-Tooltip')) {
				return jQuery(children[i]);
			}
		}
        if (obj.parent() != null && obj.parent() != obj) {
            return findTooltip(obj.parent());
        }
        return jQuery("<span />");
    }

	const tooltipFields = jQuery(".tooltips div a, .tooltips th a, .fieldWithTooltip");
	tooltipFields.not('.tooltipInitialized').mouseover(function() {
        var self = jQuery(this);
        self.addClass('tooltipInitialized');
        var tooltip = findTooltip(self);

        tooltip.parent().css("position", "relative");
        if (tooltip.text().trim() != "") {
            tooltip.show();
        }

        var offsets = {top: - tooltip.outerHeight() - 10, left: (self.outerWidth() - tooltip.outerWidth()) / 2};
        if (self.hasClass('fieldWithTooltip') && ! self.hasClass('tooltipAbove')) {
        	delete offsets.top;
        	delete offsets.left;
        }
        
        tooltip.css(offsets);
    });
    tooltipFields.mouseleave(function() {
        findTooltip(jQuery(this)).hide();
    });

}


function updateShareThisUrls(selector) {
	jQuery(selector).each(function() {
		var self = jQuery(this);
		var currentUrl = self.attr('addthis:url');
		if (currentUrl == null) {
			currentUrl = window.location;
		}
		else if (currentUrl.startsWith("/")) {
			currentUrl = window.document.location.protocol + "//" + window.document.location.host + currentUrl;
		}
		self.attr('addthis:url', window.document.location.protocol + "//" + window.document.location.host + '?redirect_to=' + escape(currentUrl));
	});
}


/** Monitor connection lost **/

	function doRemoveIcefacesNetworkConnectionErrorInfo() {
   		$(".liferay-faces-bridge-body").attr("style", "");
   		$(".liferay-faces-bridge-body > iframe").remove();
   		$(".ice-status-indicator").remove();
   	}

   	function doShowConnectionLostWarningMessage() {
   		var banner = new Liferay.Notice({type: "warning", closeText: false, content: 'Warning! Due to inactivity, your session has expired. Please save your work before refreshing page.', toggleText: false});
   		banner.show();
   	}
   	
   	function showConnectionErrorMessage() {
   		doRemoveIcefacesNetworkConnectionErrorInfo();
   		setTimeout(doRemoveIcefacesNetworkConnectionErrorInfo, 100);
   		doShowConnectionLostWarningMessage();
   	}
   	
   	jQuery(function() {
   		
   		if (window['ice']) {
   			ice.onSessionExpiry(showConnectionErrorMessage);
   			ice.onServerError(showConnectionErrorMessage);
   			ice.onNetworkError(showConnectionErrorMessage);
   			ice.onBlockingConnectionLost(showConnectionErrorMessage);
   			ice.onBlockingConnectionServerError(showConnectionErrorMessage);
   		}
   	});
   	
   	jQuery(function() {
   		if (! Liferay.Session) {
//   			setInterval(function() {
//   				jQuery.ajax("/c/portal/extend_session");
//   			}, 15000);
   		}
   		else {
   			Liferay.Session._stopTimer();
   		}
   	});