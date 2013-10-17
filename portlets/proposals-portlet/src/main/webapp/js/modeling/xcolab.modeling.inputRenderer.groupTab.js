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
	renderEdit: function(target, input, modeling, idx) {
		var tabContainer = jQuery("<div class='inputTabContent' id='inputTabContent_" + idx + "'></div>");
		target.append(tabContainer);
		var self = this;
		jQuery.each(input.inputs, function(idx, childInput) {
			modeling.getInputRenderer(childInput).render(tabContainer, childInput, modeling, idx, input);
			jQuery(tabContainer.append("<div class='control_div'></div>"));
		});
	},
	renderView: function(target, input, modeling, idx) {
		var tabContainer = jQuery("<div class='d'><a href='javascript:;'>" + input.name + "</a></div>");
		target.append(tabContainer);
		jQuery.each(input.inputs, function(idx, childInput) {
			var inputContainer = jQuery("<div></div>").appendTo(tabContainer);
			modeling.getInputRenderer(childInput).render(inputContainer, childInput, modeling, idx, input);
		});
	},
	render: function(target, input, modeling, idx, parent) {
		var renderFunc = this.renderView;
		if (XCoLab.modeling.inEditMode) {
			renderFunc = this.renderEdit;
		}
		renderFunc.apply(this, [target, input, modeling, idx, parent]);
	}
};

XCoLab.modeling.inputRenderers.push(groupInputVerticalRenderer);
