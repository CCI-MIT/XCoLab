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
	render: function(target, input, modeling, idx) {
		var verticalGroupContainer = jQuery("<div></div>");
		target.append(verticalGroupContainer);
		var self = this;
		jQuery.each(input.inputs, function(idx, childInput) {
			modeling.getInputRenderer(childInput).render(verticalGroupContainer, childInput, modeling, self);
		});
	}
};

XCoLab.modeling.inputRenderers.push(groupInputVerticalRenderer);
