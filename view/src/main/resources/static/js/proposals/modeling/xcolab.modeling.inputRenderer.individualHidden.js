if (typeof(XCoLab) == 'undefined') 
	throw new Error("XCoLab isn't defined");
if (typeof(XCoLab.modeling) == 'undefined')
	throw new Error("XCoLab.modeling isn't defined");
if (typeof(XCoLab.modeling.serieRenderers) == 'undefined')
	XCoLab.modeling.serieRenderers = [];

(function() {
	function DefaultIndividualHiddenInputRenderer(modelingWidget) {
	}
	
	DefaultIndividualHiddenInputRenderer.prototype = new XCoLab.modeling.BaseXCoLabModelingItemRenderer();
	DefaultIndividualHiddenInputRenderer.prototype.containerHtml = "<div class='actInputDef'></div>";
	DefaultIndividualHiddenInputRenderer.prototype.containerHtmlEdit = "<table class='control_definition innerLayout '></table>";
	
	DefaultIndividualHiddenInputRenderer.prototype.canRender = function(input) {
		return input.displayItemType == 'INDIVIDUAL' && input.widgetType == 'HIDDEN'; 
	};

	DefaultIndividualHiddenInputRenderer.prototype.renderEdit = function(container, input, modelingWidget, idx, parent) {
		// this is hidden when editing
	};
	
	DefaultIndividualHiddenInputRenderer.prototype.renderView = function(container, input, modelingWidget, idx, parent) {
		container.append("<span class='actInputDef'><span class='input_def_inner_label'>" + input.metaData.labels[0] + 
				"</span> " + modelingWidget.formatInputValue(input, modelingWidget.getInputValue(input)) + "</span>");
	};
	
	
	
	

	XCoLab.modeling.inputItemRenderers.push(new DefaultIndividualHiddenInputRenderer());
}());