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
		var containerTable = jQuery("<table></table>").appendTo(target);
		var containerRow = jQuery("<tr></tr>").appendTo(containerTable);
		containerRow.append("<td class='label'>" + input.metaData.labels[0] + "</td>");
		var userInput = jQuery("<input type='text' class='value control_input ' value='" + modeling.formatInputValue(input, val) + "' data-id='" + input.metaData.id + "' />");
		var bindingInput = jQuery("<input type='text' class='value control_input valueBinding hidden' value='" + val + "' data-id='" + input.metaData.id + "' />");
		
		userInput.change(function() {
			if (!modeling.isInputValueValid(input, userInput.val())) {
				// value is invalid
				userInput.val(modeling.formatInputValue(input, val));
				return;
			}
			val = modeling.parseInputValue(input, userInput.val());
			userInput.val(modeling.formatInputValue(input, val));
			bindingInput.val(val);
			
		});
		jQuery("<td class='value'></td>").append(userInput).append(bindingInput).appendTo(containerRow);
	}
};

XCoLab.modeling.inputRenderers.push(individualTextInputRenderer);
