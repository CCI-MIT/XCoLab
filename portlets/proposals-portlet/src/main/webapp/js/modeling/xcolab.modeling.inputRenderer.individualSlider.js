if (typeof(XCoLab) == 'undefined') 
	throw new "XCoLab isn't defined";
if (typeof(XCoLab.modeling) == 'undefined')
	throw new "XCoLab.modeling isn't defined"; 
if (typeof(XCoLab.modeling.serieRenderers) == 'undefined')
	XCoLab.modeling.serieRenderers = [];

var individualSliderInputRenderer = {
    SLIDER_MIN: 0,
	SLIDER_MAX: 1000,
	canRender: function(input) {
		return input.displayItemType == 'INDIVIDUAL' && input.widgetType == 'SLIDER'; 
	},
	renderEdit: function(target, input, modeling, idx, parent) {
		var min = parseFloat(input.metaData.min[0]);
		var max = parseFloat(input.metaData.max[0]);
		var defaultVal = parseFloat(input.metaData['default'][0]);
		var dataType = input.metaData.profiles[0];
		var currentValue = modeling.getInputValue(input);
		var interval;
		
		if (isNaN(currentValue)) {
			if (isNaN(defaultVal)) {
				defaultVal = (max+min)/2;
				currentValue = defaultVal;
			}
		}
		var sliderMax = max;
		var sliderMin = min;
		
		if (modeling.isInteger(dataType)) {
			interval = 1;
			sliderMax = max - min;
			sliderMin = 0;
		}
		
		if (modeling.isDouble(dataType)) {
			sliderMax = this.SLIDER_MAX;
			sliderMin = this.SLIDER_MIN;
			interval = (max-min)/(sliderMax - sliderMin);
		} 
		
		var inputContainer = jQuery("<table class='control_definition sliderDef'></table>").appendTo(target);

		if (typeof(parent) == 'undefined' || parent.groupType == 'TAB') {
			// this input has no parent or its parent is a tab, it should display it's name as an input section header
			inputContainer.append("<tr><td colspan='2'><div class='actInputDef control_title'><span>" + (idx+1) + ".</span> " + input.name +
					"<div class='act_tooltip'><div class='act_tt-wrap'><div class='act_tt-txt'>" +
					input.description + 
					"</div></div><div class='act_tt-bot'></div></div>" +
					"</div></td></tr>");
		}
		var inputRow = jQuery("<tr></tr>").appendTo(inputContainer);
		
		
		var slider = jQuery("<div></div>");
		
		var valueField = jQuery("<input type='text' class='value control_input' data-id='" + input.metaData.id + "'  value='" + modeling.formatInputValue(input, currentValue) + "' />");
		var bindingInput = jQuery("<input type='text' class='value control_input valueBinding hidden' value='" + currentValue + "' data-id='" + input.metaData.id + "' />");
		
		if ('minLabel' in input && 'maxLabel' in input) {
			var minMaxLabels = jQuery("<div class='slider-labels'><table><tr><td class='left-label'>" + input.minLabel + "</td><td class='right-label'>" + input.maxLabel + "</td></tr></table></div>");
			jQuery("<td class='sliderCol'></td>").append(slider).append(minMaxLabels).appendTo(inputRow);
		}
		else {
			jQuery("<td class='sliderCol'></td>").append(slider).appendTo(inputRow);
		}
		
		
		jQuery("<td></td>").append(valueField).append(bindingInput).appendTo(inputRow);
		

		slider.slider({
				min: sliderMin,
				max: sliderMax,
				slide: function(event, ui) {
					
					if (modeling.isInteger(dataType)) {
						valueField.val(modeling.formatInputValue(input, min + interval * ui.value));
						//inputValues[id] = formatFieldValue(min + interval * ui.value.toFixed(2), unit,null);
					}
					else if (modeling.isDouble(dataType)) {
						valueField.val(modeling.formatInputValue(input, min + interval *  ui.value.toFixed(2)));
						//inputValues[id] = formatFieldValue(min + interval * ui.value.toFixed(2), unit,null);
					}
					valueField.change();
					
					//valueBinding.val(valueField.val());
				},		
		
		} );
		
		var sliderVal = currentValue;
		if (modeling.isDouble(dataType)) {
			sliderVal = parseInt(((currentValue-min) / (max-min)) * (sliderMax - sliderMin));
		}
		if (modeling.isInteger(dataType)) {
			sliderVal = parseInt(((currentValue-min) / (max-min)) * (sliderMax - sliderMin));
		}
		slider.slider("option", "value", sliderVal);
		
		valueField.change(function(eventObject) {
			
			if (!modeling.isInputValueValid(input, valueField.val())) {
				// value is invalid
				valueField.val(modeling.formatInputValue(input, currentValue));
				return;
			}

			currentValue = modeling.parseInputValue(input, valueField.val());
			var sliderVal = currentValue;
			valueField.val(modeling.formatInputValue(input, currentValue));

			if (modeling.isDouble(dataType)) {
				sliderVal = ((sliderVal - min) / (max-min)) * (sliderMax - sliderMin);
			}

			slider.slider("option", "value", sliderVal);
			bindingInput.val(currentValue);
			return true;
		});
		
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

XCoLab.modeling.inputRenderers.push(individualSliderInputRenderer);
