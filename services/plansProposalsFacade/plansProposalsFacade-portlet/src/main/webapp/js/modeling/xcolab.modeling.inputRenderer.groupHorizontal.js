if (typeof(XCoLab) == 'undefined') 
	throw new "XCoLab isn't defined";
if (typeof(XCoLab.modeling) == 'undefined')
	throw new "XCoLab.modeling isn't defined"; 
if (typeof(XCoLab.modeling.serieRenderers) == 'undefined')
	XCoLab.modeling.serieRenderers = [];

var groupInputVerticalRenderer = {

	canRender: function(input) {
		return input.displayItemType == 'GROUP' && input.groupType == 'VERTICAL'; 
	},
	render: function(target, input, modeling) {
		console.log('rendering group horizontal input', input);
		var verticalGroupContainer = jQuery("<div><h4>vertical group: " + input.name + "</h4></div>");
		target.append(verticalGroupContainer);
		jQuery.each(input.inputs, function(idx, childInput) {
			modeling.getInputRenderer(childInput).render(verticalGroupContainer, childInput, modeling);
		});
	}
};

XCoLab.modeling.inputRenderers.push(groupInputVerticalRenderer);
