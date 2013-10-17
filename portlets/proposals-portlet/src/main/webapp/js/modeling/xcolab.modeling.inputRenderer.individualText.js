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
	renderEdit: function(target, input, modeling, idx, parent) {
		var val = modeling.getInputValue(input);
		var containerTable = jQuery("<table class='control_definition innerLayout '></table>").appendTo(target);
		
		if (typeof(parent) == 'undefined' || parent.groupType == 'TAB') {
			// this input has no parent or its parent is a tab, it should display it's name as an input section header
			containerTable.append("<tr><td colspan='2'><div class='actInputDef control_title'><span>" + (idx+1) + ".</span> " + input.name +
					"<div class='act_tooltip'><div class='act_tt-wrap'><div class='act_tt-txt'>" +
					input.description + 
					"</div></div><div class='act_tt-bot'></div></div>" + 
					"</div></td></tr>");
		}
		
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
		jQuery("<td class='field'></td>").append(userInput).append(bindingInput).appendTo(containerRow);
	},
	renderView: function(target, input, modeling, idx, parent) {
		var inputContainer = jQuery("<div class='actInputDef'></div>").appendTo(target);
		if (typeof(parent) == 'undefined' || parent.groupType == 'TAB') {
			// this input has no parent or its parent is a tab, it should display it's name as an input section header
			inputContainer.append("<div><span class='input_def_header'>" + input.name + "</span></div>");

			inputContainer.append("<div class='act_tooltip'><div class='act_tt-wrap'><div class='act_tt-txt'>"
					+ input.description + 
					"</div></div><div class='act_tt-bot'></div></div>");
		}
		inputContainer.append("<span class='actInputDef'><span class='input_def_inner_label'>" + input.metaData.labels[0] + "</span> " + modeling.formatInputValue(input, modeling.getInputValue(input)) + "</span>");
	},
	render: function(target, input, modeling, idx, parent) {
		var renderFunc = this.renderView;
		if (XCoLab.modeling.inEditMode) {
			renderFunc = this.renderEdit;
		}
		renderFunc.apply(this, [target, input, modeling, idx, parent]);
	}
};

XCoLab.modeling.inputRenderers.push(individualTextInputRenderer);
