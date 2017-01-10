if (typeof(XCoLab) == 'undefined') 
	throw new Error("XCoLab isn't defined");
if (typeof(XCoLab.modeling) == 'undefined')
	throw new Error("XCoLab.modeling isn't defined");

(function() {
	function DefaultGroupVerticalRenderer(modelingWidget) {
	}
	
	DefaultGroupVerticalRenderer.prototype = new XCoLab.modeling.BaseXCoLabModelingItemRenderer();
	DefaultGroupVerticalRenderer.prototype.containerHtml = "<div></div>";
	
	DefaultGroupVerticalRenderer.prototype.canRender = function(input) {
		return input.displayItemType == 'GROUP' && input.groupType == 'VERTICAL'; 
	};

	DefaultGroupVerticalRenderer.prototype.renderEdit = function(container, input, modelingWidget, idx, parent) {
		jQuery.each(input.inputs, function(idx, childInput) {
			modelingWidget.getInputRenderer(childInput).render(container, childInput, modelingWidget, input);
		});
	};
	
	DefaultGroupVerticalRenderer.prototype.renderView = function(container, input, modelingWidget, idx, parent) {
		jQuery.each(input.inputs, function(idx, childInput) {
			var inputContainer = jQuery("<div></div>").appendTo(container);
			modelingWidget.getInputRenderer(childInput).render(inputContainer, childInput, modeling, input);
		});
	};

	XCoLab.modeling.inputItemRenderers.push(new DefaultGroupVerticalRenderer());
}());