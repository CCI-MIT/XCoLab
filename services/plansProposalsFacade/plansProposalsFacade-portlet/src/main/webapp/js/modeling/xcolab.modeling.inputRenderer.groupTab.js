if (typeof(XCoLab) == 'undefined') 
	throw new "XCoLab isn't defined";
if (typeof(XCoLab.modeling) == 'undefined')
	throw new "XCoLab.modeling isn't defined"; 
if (typeof(XCoLab.modeling.serieRenderers) == 'undefined')
	XCoLab.modeling.serieRenderers = [];

var groupInputVerticalRenderer = {

	canRender: function(input) {
		return input.displayItemType == 'GROUP' && input.groupType == 'TAB'; 
	},
	render: function(target, input, modeling) {
		var tabContainer = jQuery("<div><h3>tab: " + input.name + "</h3></div>");
		target.append(tabContainer);
		var self = this;
		jQuery.each(input.inputs, function(idx, childInput) {
			modeling.getInputRenderer(childInput).render(tabContainer, childInput, modeling, idx, self);
		});
	}
};

XCoLab.modeling.inputRenderers.push(groupInputVerticalRenderer);
