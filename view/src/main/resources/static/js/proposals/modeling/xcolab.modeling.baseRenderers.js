if (typeof(XCoLab) == 'undefined') 
	throw new Error("XCoLab isn't defined");
if (typeof(XCoLab.modeling) == 'undefined')
	throw new Error("XCoLab.modeling isn't defined");


(function() {
	XCoLab.modeling.BaseXCoLabModelingRenderer = function() {};
	
	/**
	 * Base class for a renderer that provides common render function with support to two different
	 * render modes (edit/view) also if defined it wrapps container into provided html code.  
	 */
	XCoLab.modeling.BaseXCoLabModelingRenderer.prototype.render = function(container) {
		var renderFunc = this.renderView;
		var renderHeaderFunc = null;
		var renderFooterFunc = null;
		if (this.modelingWidget.inEditMode) {
			renderFunc = this.renderEdit;
			renderHeaderFunc = this.renderHeaderEdit;
			renderFooterFunc = this.renderFooterEdit;
		}
		else {
			renderHeaderFunc = this.renderHeader;
			renderFooterFunc = this.renderFooter;
		}

		var elementContainer = container;
		var containerHtml = false;
		if (this.modelingWidget.inEditMode && 'containerHtmlEdit' in this) {
			containerHtml = typeof(this.containerHtmlEdit) == 'function' ? this.containerHtmlEdit.apply(this, arguments) : this.containerHtmlEdit;
		}
		else {
			if ('containerHtml' in this) {
				containerHtml = typeof(this.containerHtml) == 'function' ? this.containerHtml.apply(this, arguments) : this.containerHtml;
			}
		}
		if (containerHtml) {
			elementContainer = jQuery(containerHtml).appendTo(container);
		}

		var newArguments = [elementContainer].concat(Array.prototype.slice.call(arguments, 1));

		if (renderHeaderFunc) {
			renderHeaderFunc.apply(this, newArguments);
		}

		renderFunc.apply(this, newArguments);

		if (renderFooterFunc) {
			renderFooterFunc.apply(this, newArguments);
		}
	};
	
	
	/**
	 * Base renderer for items (like inputs)
	 * 
	 * @returns {XCoLab.modeling.BaseXCoLabModelingItemRenderer}
	 */
	XCoLab.modeling.BaseXCoLabModelingItemRenderer = function() {};
	XCoLab.modeling.BaseXCoLabModelingItemRenderer.prototype = new XCoLab.modeling.BaseXCoLabModelingRenderer();
	
	XCoLab.modeling.BaseXCoLabModelingItemRenderer.prototype.render = function(container, input, modelingWidget, idx, parent) {
		if (input.widgetType == 'HIDDEN') return;
		this.modelingWidget = modelingWidget;
		XCoLab.modeling.BaseXCoLabModelingRenderer.prototype.render.apply(this, arguments);
	};
	
	/**
	 * Renders a header line (in edit mode) for an input if it has no parent or its direct parent is a tab.
	 * 
	 */
	XCoLab.modeling.BaseXCoLabModelingItemRenderer.prototype.renderHeaderEdit = function(container, input, modelingWidget, idx, parent) {
		if (!(input.groupType == 'TAB') && (typeof(parent) == 'undefined' || parent.groupType == 'TAB')) {
			// this input has no parent or its parent is a tab, it should display it's name as an input section header
			var addTooltip = input.description && jQuery.trim(input.description).length > 0; 
			container.append("<tr><td colspan='2'><div class='actInputDef control_title'><span>" + 
					(idx+1) + ".</span> " + input.name + (addTooltip ? "<div class='js-Tooltip' title='"
					+ input.description + "'><div class='qmark'>?</div>" +
					"</div>" : "") +
					"</div></td></tr>");
		}
	};
	
	/**
	 * Renders a header line (in view mode) for an input if it has no parent or its direct parent is a tab.
	 */
	XCoLab.modeling.BaseXCoLabModelingItemRenderer.prototype.renderHeader = function(container, input, modelingWidget, idx, parent) {
		// FIXME - headers disabled as per Rob's request
		if (false && !(input.groupType == 'TAB') && (typeof(parent) == 'undefined' || parent.groupType == 'TAB' )) {
			// this input has no parent or its parent is a tab, it should display it's name as an input section header
			//var addTooltip = input.description && jQuery.trim(input.description).length > 0;
			var addTooltip = false;
			container.append("<div><span class='input_def_header'>" + input.name + (addTooltip ? "<div class='js-Tooltip' title='"
                    + input.description + "'><div class='qmark'>?</div>" : "") + "</span></div>");
		}
	};
	
	
})();