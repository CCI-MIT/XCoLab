if (typeof(XCoLab) == 'undefined') 
	throw new Error("XCoLab isn't defined");
if (typeof(XCoLab.modeling) == 'undefined')
	throw new Error("XCoLab.modeling isn't defined");
if (typeof(XCoLab.modeling.serieRenderers) == 'undefined')
	XCoLab.modeling.serieRenderers = [];

(function() {
	function DefaultIndividualSelectInputRenderer(modelingWidget) {
	}
	
	DefaultIndividualSelectInputRenderer.prototype = new XCoLab.modeling.BaseXCoLabModelingItemRenderer();
	DefaultIndividualSelectInputRenderer.prototype.containerHtml = "<div class='actInputDef'></div>";
	DefaultIndividualSelectInputRenderer.prototype.containerHtmlEdit = "<table class='control_definition innerLayout '></table>";
	
	DefaultIndividualSelectInputRenderer.prototype.canRender = function(input) {
		return input.displayItemType == 'INDIVIDUAL' && input.widgetType == 'RADIO'; 
	};

	DefaultIndividualSelectInputRenderer.prototype.renderEdit = function(container, input, modelingWidget, idx, parent) {
		var val = modelingWidget.getInputValue(input);
		var containerRow = jQuery("<tr></tr>").appendTo(container);
		//containerRow.append("<td class='label'>" + input.metaData.labels[0] + "</td>");
		
		var userInput = jQuery("<div />");
		
		var bindingInput = jQuery("<input type='text' class='value control_input valueBinding hidden' value='" + 
				val + "' data-id='" + input.metaData.id + "' />");
		
		for (var idx in input.metaData.categories) {
			var inputId = Math.floor(Math.abs(1000000 * Math.random())) + "_" + Math.floor(Math.abs(1000000 * Math.random())); 
			userInput.append("<div class='modelInput_radio'><input type='radio' name='" + input.metaData.id + "' value='" + input.metaData.categories[idx] + "' id='" + inputId + "'/><label for='" + inputId + "'>" + input.metaData.categories[idx] + "</label></div>" );
		}
		userInput.find("input").change(function() { bindingInput.val(jQuery(this).val()); });
		jQuery("<td class='field'></td>").append(userInput).append(bindingInput).appendTo(containerRow);
	};
	
	DefaultIndividualSelectInputRenderer.prototype.renderView = function(container, input, modelingWidget, idx, parent) {
		container.append("<span class='actInputDef'><span class='input_def_inner_label'>" + input.metaData.labels[0] + 
				"</span> " + modelingWidget.getInputValue(input) + "</span>");
	};
	
	
	
	

	XCoLab.modeling.inputItemRenderers.push(new DefaultIndividualSelectInputRenderer());
}());