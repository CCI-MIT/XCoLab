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
	render: function(target, input, modeling) {
		var min = parseFloat(input.metaData.min[0]);
		var max = parseFloat(input.metaData.max[0]);
		var defaultVal = parseFloat(input.metaData['default'][0]);
		var dataType = input.metaData.profiles[0];
		var currentValue = modeling.getInputValue(input);
		var unit = input.metaData.units[0];
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
		
		
		var slider = jQuery("<div></div>");
		target.append("<span>" + input.name + "</span>");
		target.append(slider);
		var valueField = jQuery("<input type='text' class='value control_input' data-id='" + input.metaData.id + "'  value='" + modeling.formatInputValue(input, modeling.getInputValue(input)) + "' />");
		target.append(valueField);

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


		slider.slider("option", "value", sliderVal);
		
		valueField.change(function(eventObject) {
			
			if (!modeling.isInputValueValid(input, valueField.val())) {
				// value is invalid
				valueField.val(modeling.formatInputValue(input, currentValue));
				return;
			}

			currentValue = modeling.parseInputValue(input, valueField.val());
			var sliderVal = currentValue;
			console.log('current value: ', valueField.val(), modeling.formatInputValue(input, currentValue));
			valueField.val(modeling.formatInputValue(input, currentValue));

			if (modeling.isDouble(dataType)) {
				sliderVal = ((sliderVal - min) / (max-min)) * (sliderMax - sliderMin);
			}

			slider.slider("option", "value", sliderVal);
			
			return true;
		});
		
		console.log('rendered slider', 'current value', currentValue, sliderVal, min, max, sliderMin, sliderMax, input);
	}
};

XCoLab.modeling.inputRenderers.push(individualSliderInputRenderer);
