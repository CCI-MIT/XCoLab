if (typeof(XCoLab) == 'undefined') 
	throw new "XCoLab isn't defined";
if (typeof(XCoLab.modeling) == 'undefined')
	throw new "XCoLab.modeling isn't defined"; 
if (typeof(XCoLab.modeling.serieRenderers) == 'undefined')
	XCoLab.modeling.serieRenderers = [];

var groupInputRenderer = {

	canRender: function(input) {
		return input.displayItemType == 'GROUP' && input.groupType == 'HORIZONTAL'; 
	},
	render: function(target, input, modeling) {
		console.log('rendering group vertical input', input);
		target.append("<h4>horizontal group: " + input.name + "</h4>");
		var verticalGroupContainer = jQuery("<table></table>");
		var groupRow = jQuery("<tr></tr>");
		verticalGroupContainer.append(groupRow);
		target.append(verticalGroupContainer);
		jQuery.each(input.inputs, function(idx, childInput) {
			var itemCell = jQuery("<td></td>");
			groupRow.append(itemCell);
			modeling.getInputRenderer(childInput).render(itemCell, childInput, modeling);
		});
	}
};

XCoLab.modeling.inputRenderers.push(groupInputRenderer);
