/*
var isDocumentReady = false;

jQuery(document).ready(function() {
    isDocumentReady = true;
});
*/

if (window.collab) {
}
else {
	window.collab = {};
}

Collab = window.collab;

Collab.nav = new function() {
	var navigationItems = {};
	var req = 0;
	var alertUserOnExit = false;
	var queryString = false;
	var initialized = false;

	/**
	 * this field should hold a set callback functions used to determine if contents of currently displayed
	 * page has been marked as "dirty" (that is - user has entered anything in edit boxes etc.)
	 * 
	 * it is set in Collab.nav.setEditorDirtyValidationCallback();
	 */
	var editorDirtyValidationCallbacks = [];
	
	this.navigate = function(token, parameters, block) {
		navigationItems[token] = parameters;
		updateHash(block);
		return false;
	}
	
	this.navigateAddParams = function(token, additionalParameters, block) {
		if (typeof(navigationItems[token]) == 'undefined') {
			navigationItems[token] = {};
		}
		for (var key in additionalParameters) {
			navigationItems[token][key] = additionalParameters[key];
		}

		updateHash(block);
		return false;
	}
	
	this.getHashWithParams = function(token, additionalParameters) {
		var tmp = navigationItems;
		if (typeof(tmp[token]) == 'undefined') {
			tmp[token] = {};
		}
		for (var key in additionalParameters) {
			tmp[token][key] = additionalParameters[key];
		}
		
		return createToken(tmp);
	}
	
	/**
	 * token and params in format of JS object:
	 * {
	 * 	token1: {param1: val1...},
	 * 	token2: {param2: val2...},
	 * 	...
	 * }
	 */
	this.navigateAddParamsMulti = function(tokenAndParams, block) {
		for (var token in tokenAndParams) {
			if (typeof(navigationItems[token]) == 'undefined') {
				navigationItems[token] = {};
			}
			for (var key in tokenAndParams[token]) {
				navigationItems[token][key] = tokenAndParams[token][key];
			}
		}

		updateHash(block);
		
		return false;
	}
	
	this.navigateRemoveParams = function(token, paramsToBeRemoved) {
		if (typeof(navigationItems[token]) == 'undefined') {
			return;
		}
		for (var i=0; i < paramsToBeRemoved.length; i++) {
			delete navigationItems[token][paramsToBeRemoved[i]];
		}
		
		return false;
	}
	
	
	this.pageload = function(hash) {
		var pos = window.location.hash.indexOf("?rvn=");
		if (pos >= 0) {
			var newUrl = window.location.pathname + window.location.hash.substring(pos) + window.location.hash.substring(0, pos-1);
			window.location = newUrl;
		}
		navigationItems = parseToken(hash);
		//alert("after parsing token " + hash + "try to force navigation..." + jQuery(".navigationManagerForm .submit").length + " " + jQuery(".navigationManagerForm .navigationToken").length);
		if(!initialized) {
			initialized = true;
			if (jQuery.trim(hash).length > 0) {
				forceNavigation();
			}
		}
		else {
			forceNavigation();
		}
	}
	
	this.getNavigationItems = function() {
		return navigationItems;
	}
	
	this.refresh = function() {
		forceNavigation();
	}
	
	this.block = function() {
		jQuery("#content_wrap").block({message: "<center><img src='/climatecolab-theme/images/ajax-loader.gif' /></center>"});
	}
	
	this.unblock = function() {
   		jQuery("#content_wrap").unblock();
	}

	function forceNavigation() {
		jQuery(".navigationManagerForm .navigationToken").val(createToken());
		// below is ugly hack, but without it webkit based browsers don't work
		setTimeout(function() {
				jQuery(".navigationManagerForm .submit").click();
			}, 0);
		
		return false;
		
	}
		
	function updateHash(block) {
		removeConditionalText();
		//navigationItems["req"] = req++;
		//alert(navigationItems.req);
		//navigationItems['req'] = {req: req++};
		if (isAnyEditorDirty()) {
			// page contains dirty data, ask user if he really wants to leave
			if (! window.confirm('You have unsaved changes.  If you would like to save your changes click "Cancel" and use the "Save changes" button. If you wish to discard your changes, click "OK"')) {
				return ;
			}
		}
		if (block) {
			Collab.nav.block();
		}
		jQuery.history.load(createToken());
		initialized = true;
	}
	
	
	
	function parseToken(token) {
		tokenNavigationMap = {};
		if (token.indexOf("?rvn=") > 0) {
			token = token.substring(0, token.indexOf("?rvn="));
		}
		var paramGroups = token.split(";");
		for (var i=0; i < paramGroups.length; i++) {
			var paramGroup = paramGroups[i];
			var groupNameVals = paramGroup.split("=");
			if (groupNameVals.length != 2) {
				continue;
			}
			var groupName = groupNameVals[0];
			tokenNavigationMap[groupName] = {};
			var groupVals = groupNameVals[1].split(",");
			for (var k = 0; k < groupVals.length; k++) {
				var keyValue = groupVals[k].split(":");
				if (keyValue.length != 2) {
					continue;
				}
				tokenNavigationMap[groupName][keyValue[0]] = keyValue[1];
			}
		}
		return tokenNavigationMap;
	}
	
	function createToken(params) {
		var navParams = navigationItems;
		if (typeof(params) != 'undefined') {
			navParams = params;
		}
		var navigationToken = [];
		var addSemicolon = false;
		for (var subject in navParams) {
			if (addSemicolon) {
				navigationToken.push(";");
			}
			navigationToken.push(subject);
			navigationToken.push("=");
			var addComa = false;
			for (var key in navParams[subject]) {
				if (addComa) {
					navigationToken.push(",");
				}
				navigationToken.push(key);
				navigationToken.push(":");
				navigationToken.push(navParams[subject][key]);
				addComa = true;
			}
			var addSemicolon = true;
		}
		return navigationToken.join("");
	}
	
	/**
	 * Sets editorDirtyValidationCallback that will be used to check if contents
	 * of the page is dirty.
	 * 
	 * @param callback callback function that should return true when page is dirty
	 * 
	 */
	this.addEditorDirtyValidationCallback = /* void */ function(/* function */ callback) {
		for (var i=0; i < editorDirtyValidationCallbacks.length; i++) {
			if (editorDirtyValidationCallbacks[i] == callback) {
				return;
			}
		}
		editorDirtyValidationCallbacks.push(callback);
	}
	
	/**         
	 * Checks if contents of the page is "dirty" (any editor has new data (entered by user).
	 * To check that editorDirtyValidationCallback is used (which can be set with 
	 * setEditorDirtyValidationCallback, if no callback is set false is returned.
	 * 
	 * @return true if contents of page is dirty false otherwise
	 */
	function /* boolean */ isAnyEditorDirty() {
		for (var i=0; i < editorDirtyValidationCallbacks.length; i++) {
			if (editorDirtyValidationCallbacks[i]()) {
				return true;
			}
		}
		return false;
	}
	
	function removeConditionalText() {
		// remove everything related to conditional text
		delete navigationItems['condText'];
	}
	

	/**
	 * Initialize on before unload event to prevent users from navigating away from edit page
	 * when he has edited something and he has mistakenly clicked a link.
	 * 
	 * This has to be set with a delay because either icefaces or any other JS lib modifies
	 * onbeforeunload and we have to override that.
	 * 
	 */ 
	jQuery(document).ready(function() {
		setTimeout(function() {
			var oldOnBeforeUnload = window.onbeforeunload;

			window.onbeforeunload = function() {
				if (isAnyEditorDirty()) {
					
                    return 'You have modified this page but have not saved your changes.';
				}
				if (oldOnBeforeUnload) {
					return oldOnBeforeUnload();
				}
				return null;
			}
		}, 2000);
	});
		
}

jQuery(document).ready(function() {
     jQuery.history.init(Collab.nav.pageload);
});
Collab.nav.block();
