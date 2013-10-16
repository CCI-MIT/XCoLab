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
	renderEdit: function(target, input, modeling, idx) {
		var verticalGroupContainer = jQuery("<div></div>");
		target.append(verticalGroupContainer);
		var self = this;
		jQuery.each(input.inputs, function(idx, childInput) {
			modeling.getInputRenderer(childInput).render(verticalGroupContainer, childInput, modeling, input);
		});
	},
	renderView: function(target, input, modeling, idx) {
		var verticalGroupContainer = jQuery("<div></div>").appendTo(target);
		jQuery.each(input.inputs, function(idx, childInput) {
			var inputContainer = jQuery("<div></div>").appendTo(verticalGroupContainer);
			modeling.getInputRenderer(childInput).render(inputContainer, childInput, modeling, input);
		});
		
	},
	render: function(target, input, modeling, idx, parent) {
		var renderFunc = this.renderView;
		if (XCoLab.modeling.inEditMode) {
			renderFunc = this.renderEdit;
		}
		renderFunc(target, input, modeling, idx, parent);
	}
	
};

XCoLab.modeling.inputRenderers.push(groupInputVerticalRenderer);
