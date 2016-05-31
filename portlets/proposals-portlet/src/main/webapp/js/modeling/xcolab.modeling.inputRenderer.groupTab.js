if (typeof(XCoLab) == 'undefined') 
	throw new Error("XCoLab isn't defined");
if (typeof(XCoLab.modeling) == 'undefined')
	throw new Error("XCoLab.modeling isn't defined");

(function() {
	function DefaultGroupTabRenderer(modelingWidget) {
	}
	
	DefaultGroupTabRenderer.prototype = new XCoLab.modeling.BaseXCoLabModelingItemRenderer();
	DefaultGroupTabRenderer.prototype.containerHtmlEdit = function(container, input, modelingWidget, idx, parent) {
		return "<div class='inputTabContent' id='inputTabContent_" + idx + "'></div>";
	};
	
	DefaultGroupTabRenderer.prototype.containerHtml = function(container, input, modelingWidget, idx, parent) {
		return "<div class='d'><a href='javascript:;'>" + input.name + "</a></div>";
	};
	
	DefaultGroupTabRenderer.prototype.canRender = function(input) {
		return input.displayItemType == 'GROUP' && input.groupType == 'TAB'; 
	};

	DefaultGroupTabRenderer.prototype.renderEdit = function(container, input, modelingWidget, idx, parent) {
		jQuery.each(input.inputs, function(idx, childInput) {
			modelingWidget.getInputRenderer(childInput).render(container, childInput, modelingWidget, idx, input);
			jQuery(container.append("<div class='control_div'></div>"));
		});
	};
	
	DefaultGroupTabRenderer.prototype.renderView = function(container, input, modelingWidget, idx, parent) {
		jQuery.each(input.inputs, function(idx, childInput) {
			var inputContainer = jQuery("<div></div>").appendTo(container);
			modelingWidget.getInputRenderer(childInput).render(inputContainer, childInput, modelingWidget, idx, input);
		});
	};

	XCoLab.modeling.inputItemRenderers.push(new DefaultGroupTabRenderer());
}());