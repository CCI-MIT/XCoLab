if (typeof(XCoLab) == 'undefined') 
	throw new "XCoLab isn't defined";
if (typeof(XCoLab.modeling) == 'undefined')
	throw new "XCoLab.modeling isn't defined"; 
if (typeof(XCoLab.modeling.serieRenderers) == 'undefined')
	XCoLab.modeling.serieRenderers = [];

var individualTextInputRenderer = {

	canRender: function(input) {
		return input.displayItemType == 'INDIVIDUAL' && input.widgetType == 'TEXT_FIELD'; 
	},
	render: function(target, input, modeling) {
		var val = modeling.getInputValue(input);
		target.append("<span>" + input.name + "</span>");
		target.append("<input type='text' class='value control_input valueBinding' value='" + val + "' data-id='" + input.metaData.id + "' />");
	}
};

XCoLab.modeling.inputRenderers.push(individualTextInputRenderer);
