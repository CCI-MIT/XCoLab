if (typeof(XCoLab) == 'undefined') 
	throw new Error("XCoLab isn't defined");
if (typeof(XCoLab.modeling) == 'undefined')
	throw new Error("XCoLab.modeling isn't defined");

(function() {
	function DefaultIndividualSliderInputRenderer(modelingWidget) {
	}
	
	DefaultIndividualSliderInputRenderer.prototype = new XCoLab.modeling.BaseXCoLabModelingItemRenderer();
	DefaultIndividualSliderInputRenderer.prototype.containerHtml = "<div class='actInputDef'></div>";
	DefaultIndividualSliderInputRenderer.prototype.containerHtmlEdit = "<table class='control_definition sliderDef'></table>";

	DefaultIndividualSliderInputRenderer.prototype.SLIDER_MIN = 0;
	DefaultIndividualSliderInputRenderer.prototype.SLIDER_MAX = 1000;
	
	DefaultIndividualSliderInputRenderer.prototype.canRender = function(input) {
		return input.displayItemType == 'INDIVIDUAL' && input.widgetType == 'SLIDER';
	};

	DefaultIndividualSliderInputRenderer.prototype.renderEdit = function(container, input, modeling, idx, parent) {
		var min = parseFloat(input.metaData.min[0]);
		var max = parseFloat(input.metaData.max[0]);
		var defaultVal = parseFloat(input.metaData['default'][0]);
		var dataType = input.metaData.profiles[0];
		var currentValue = modeling.getInputValue(input);
		var interval = 1;
		
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
		
		var inputRow = jQuery("<tr></tr>").appendTo(container);
		
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
					}
					else if (modeling.isDouble(dataType)) {
						valueField.val(modeling.formatInputValue(input, min + interval *  ui.value.toFixed(2)));
					}
					valueField.change();
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

			//if (modeling.isDouble(dataType)) {
				sliderVal = ((sliderVal - min) / (max-min)) * (sliderMax - sliderMin);
			//}
			slider.slider("option", "value", sliderVal);
			bindingInput.val(currentValue);
			
			var valueChangedEvent = jQuery.Event("valueChanged");
			jQuery(modeling).trigger(valueChangedEvent);
			
			return true;
		});
	};
	
	DefaultIndividualSliderInputRenderer.prototype.renderView = function(container, input, modeling, idx, parent) {
		container.append("<span class='actInputDef'><span class='input_def_inner_label'>" + 
				input.metaData.labels[0] + "</span> " + 
				modeling.formatInputValue(input, modeling.getInputValue(input)) + "</span>");
	};
	

	XCoLab.modeling.inputItemRenderers.push(new DefaultIndividualSliderInputRenderer());
}());
