if (typeof(XCoLab) == 'undefined') 
	throw new Error("XCoLab isn't defined");
if (typeof(XCoLab.modeling) == 'undefined')
	throw new Error("XCoLab.modeling isn't defined");
if (typeof(XCoLab.modeling.serieRenderers) == 'undefined')
	XCoLab.modeling.serieRenderers = [];

(function() {
	function DefaultIndividualTextInputRenderer(modelingWidget) {
	}
	
	DefaultIndividualTextInputRenderer.prototype = new XCoLab.modeling.BaseXCoLabModelingItemRenderer();
	DefaultIndividualTextInputRenderer.prototype.containerHtml = "<div class='actInputDef'></div>";
	DefaultIndividualTextInputRenderer.prototype.containerHtmlEdit = "<table class='control_definition innerLayout '></table>";
	
	DefaultIndividualTextInputRenderer.prototype.canRender = function(input) {
		return input.displayItemType == 'INDIVIDUAL' && input.widgetType == 'TEXT_FIELD'; 
	};

	DefaultIndividualTextInputRenderer.prototype.renderEdit = function(container, input, modelingWidget, idx, parent) {
		var val = modelingWidget.getInputValue(input);
		
		var containerRow = jQuery("<tr></tr>").appendTo(container);
		containerRow.append("<td class='label'>" + input.metaData.labels[0] + "</td>");
		
		var userInput = jQuery("<input type='text' class='value control_input ' value='" + 
				modelingWidget.formatInputValue(input, val) + "' data-id='" + input.metaData.id + "' />");
		
		var bindingInput = jQuery("<input type='text' class='value control_input valueBinding hidden' value='" + 
				val + "' data-id='" + input.metaData.id + "' />");
		
		userInput.change(function() {
			if (!modelingWidget.isInputValueValid(input, userInput.val())) {
				// value is invalid
				userInput.val(modeling.formatInputValue(input, val));
				return;
			}
			val = modelingWidget.parseInputValue(input, userInput.val());
			userInput.val(modelingWidget.formatInputValue(input, val));
			bindingInput.val(val);
			
			var valueChangedEvent = jQuery.Event("valueChanged");
			jQuery(modelingWidget).trigger(valueChangedEvent);
		});
		jQuery("<td class='field'></td>").append(userInput).append(bindingInput).appendTo(containerRow);
	};
	
	DefaultIndividualTextInputRenderer.prototype.renderView = function(container, input, modelingWidget, idx, parent) {
		container.append("<span class='actInputDef'><span class='input_def_inner_label'>" + input.metaData.labels[0] + 
				"</span> " + modelingWidget.formatInputValue(input, modelingWidget.getInputValue(input)) + "</span>");
	};
	
	
	
	

	XCoLab.modeling.inputItemRenderers.push(new DefaultIndividualTextInputRenderer());
}());