if (typeof (console) == 'undefined') {
	window.console = {
			log: function() {},
			debug: function() {},
			error: function() {}
	};
}

if (typeof (XCoLab) == 'undefined') 
	XCoLab = {};

function initActTooltips(container) {
    container.on('mouseover', '.tooltipTrigger', function() {
            var actInputDef = jQuery(this).parents(".actInputDef");
            var self = $(this);
            var tooltip = actInputDef.find(".c-Tooltip__actions");
            tooltip.css('left', self.position().left + self.width() -2);
            tooltip.css('top', -16);
            tooltip.show();
    });
    container.on('mouseout', '.tooltipTrigger', function() {
        var actInputDef = jQuery(this).parents(".actInputDef");
        var tooltip = actInputDef.find(".c-Tooltip__actions");
        tooltip.hide();
    });
}


// -- SPINNING (LOADING) WHEEL --

var loadingWheelOpts = {
	lines: 13, // The number of lines to draw
	length: 20, // The length of each line
	width: 10, // The line thickness
	radius: 30, // The radius of the inner circle
	corners: 1, // Corner roundness (0..1)
	rotate: 0, // The rotation offset
	direction: 1, // 1: clockwise, -1: counterclockwise
	color: '#000', // #rgb or #rrggbb or array of colors
	speed: 1, // Rounds per second
	trail: 60, // Afterglow percentage
	shadow: false, // Whether to render a shadow
	hwaccel: false, // Whether to use hardware acceleration
	className: 'spinner', // The CSS class to assign to the spinner
	zIndex: 2e9, // The z-index (defaults to 2000000000)
	top: 'auto', // Top position relative to parent in px
	left: 'auto' // Left position relative to parent in px
};



/** 
 * Base object responsible for handling rendering of ROMA modeling widget.
 * 
 * It provides an API to be used to show the widget, two modes are supported (edit/view).
 * 
 */
function ModelingWidget(selector, options) {
	this.container = $(selector);
	this.spinner = new Spinner(loadingWheelOpts);
	if (typeof(options) == 'undefined') {
		throw "Options have to be defined for modeling widget";
	}
	if (!('renderers' in options) || options.renderers.length == 0) {
		throw "Missing renderers";
	}
	this.inEditMode = 'edit' in options ? options.edit : false;
	this.inputFreezed = this.inEditMode ? false : true;
	var that = this;
	this.renderers = [];
	jQuery(options.renderers).each(function(idx, renderer) {
		that.renderers.push(new renderer(that));
	});
	
	initActTooltips(this.container);


	jQuery(this).on('scenarioFetched', function(event) {
		that.modelId = event.scenario.modelId;
		that.scenario = event.scenario;
		that.model = event.scenario;
	});

	jQuery(this).on('scenarioFetchedWithErrors', ModelingWidget.prototype.showStackTrace);

	jQuery(this).on('modelFetched', function(event) {
		that.modelId = event.model.modelId;
		that.model = event.model;
	});

	this.container.data('modeling', this);
	this.options = options;
	this.options.defaultValues = this.options.defaultValues || {};
}

ModelingWidget.prototype.scenarioUrl = '/web/guest/plans/-/plans/api/modeling/scenarios';
ModelingWidget.prototype.modelUrl = '/web/guest/plans/-/plans/api/modeling/models';

/**
 * returns true if value is valid, false otherwise, 
 * @param input for which value is to be checked 
 * @param newVal new value that is to be used for given input
 * @return true if value is valid false otherwise
 */
ModelingWidget.prototype.isInputValueValid = function(input, newVal) {
	var min = parseFloat(input.metaData.min[0]);
	var max = parseFloat(input.metaData.max[0]);
	
	var newValParsed = jQuery.trim(this.parseInputValue(input, newVal));
	if (! /^\s*(\+|-)?((\d+(\.\d+)?)|(\.\d+))\s*$/.test(newValParsed)) {
		return false;
	}
	
	var newValFloat = parseFloat(newValParsed);
	if (newValFloat < min || newValFloat > max) {
		return false;
	}
	return true;
};

/**
 * Parses value according to unit (currently just removes % sign if unit is a percent)
 * @param input input for which value should be parsed
 * @param value value that should be parsed
 * @return parsed value
 */
ModelingWidget.prototype.parseInputValue = function(input, value) {
	var unit = input.metaData.units[0];
	if (unit.toLowerCase().indexOf("percent") >= 0 || unit.toLowerCase().indexOf("%") >= 0) {
		return value.replace("%", '');
    }
	return value;
};

/**
 * Checks if given data type is an integer type.
 * @param dataType string representation of a data type (java.lang.Integer for example)
 * @return true if dataType represents an integer false otherwise
 */
ModelingWidget.prototype.isInteger = function(dataType) {
	if (dataType.indexOf("java.lang.Integer") >= 0) return true;
	if (dataType.indexOf("java.lang.Long") >= 0) return true;
	return false;
};

/**
 * Checks if given data type is a double type.
 * @param dataType string representation of a data type (java.lang.Double for example)
 * @return true if dataType represents a double false otherwise
 */
ModelingWidget.prototype.isDouble = function(dataType) {
	if (dataType.indexOf("java.lang.Double") >= 0) return true;
	if (dataType.indexOf("java.lang.Float") >= 0) return true;
	return false;
};

/**
 * Formats input value in human readable way.
 * 
 * @param input input for which value is to be presented
 * @param value value that should be formatted
 * @return formatted value
 */
ModelingWidget.prototype.formatInputValue = function(input, value) {
	var unit = input.metaData.units[0];
	var dataType = input.metaData.profiles[0];
	
	if (unit.toLowerCase().indexOf("percent") >= 0 || unit.toLowerCase().indexOf("%") >= 0) {
		var numVal = value * 1;
    		if (this.isInteger(dataType)) 
    			return numVal + "%";
	    	else 
    			return numVal.toFixed(1) + "%";
	}
    else if (dataType != null) {
    	if (this.isInteger(dataType)) 
    		return (value *1).toFixed(0);
    	else 
    		return (value * 1).toFixed(2);
    }
	return value;
};

/**
 * Returns a renderer for given input, if concrete renderer can't be found
 * a default renderer is returned (default renderer doesn't present anything
 * to the user)
 * 
 * @param input input for which renderer should be returned
 * @return renderer for an input
 */		
ModelingWidget.prototype.getInputRenderer = function(input) {
	var renderer = null;
	jQuery(XCoLab.modeling.inputItemRenderers).each(function(idx, rendererCandidate) {
		if (rendererCandidate.canRender(input)) {
			renderer = rendererCandidate;
		}
	});
	return renderer != null ? renderer : { render: function(container, input) {console.log('no renderer found for input', input);}};
};

/**
 * Returns a renderer for given output, if concrete renderer can't be found
 * a default renderer is returned (default renderer doesn't present anything
 * to the user)
 * 
 * @param output output for which renderer should be returned
 * @return renderer for an output
 */		
ModelingWidget.prototype.getOutputRenderer = function(output) {
	var renderer = null;
	jQuery(XCoLab.modeling.outputItemRenderers).each(function(idx, rendererCandidate) {
		if (rendererCandidate.canRender(output)) {
			renderer = rendererCandidate;
		}
	});
	return renderer != null ? renderer : { getName : function() { return output.name; }, render: function(container, output) {console.log('no renderer found for output', output);}};
};

/**
 * Returns value for input. If no value is set for given input then default value is returned.
 * 
 * @param input input for which value should be returned.
 * @return value for given input
 */
ModelingWidget.prototype.getInputValue = function(input) {
	if ('value' in input) {
		return parseFloat(input.value.values[0][0]);
	}
	if ('default' in input.metaData && input.metaData['default'].length > 0) {
		return parseFloat(input.metaData['default'][0]);
	}
	
	return 0;
};

/**
 * Loads scenario with given id. When scenario is loaded "scenarioFetched" 
 * event is triggered, if there was an error "scenarioFetchingError" is triggered.
 * 
 * @param scenarioId id of a scenario
 * 
 */
ModelingWidget.prototype.loadScenario = function(scenarioId) {
	this.spinner.spin(document.getElementsByClassName("spinner-area")[0]);
	this.container.fadeOut();
	var modelingWidget = this;

	jQuery(modelingWidget).trigger("fetchingScenario");
    var loadScenarioUrl = this.scenarioUrl + '/' + scenarioId;
    console.debug("Loading scenario from " + loadScenarioUrl);
    jQuery.ajax({
		url: loadScenarioUrl,
		data: {},
		dataType: 'json'
	}).done(function(data, textStatus, jqXHR) {
		console.debug('scenario loaded', scenarioId, data, textStatus, jqXHR);
		var event = jQuery.Event( "scenarioFetched" );
		event.scenario = data;
		modelingWidget.container.fadeIn();
		jQuery(modelingWidget).trigger(event);
		modelingWidget.spinner.stop();
	}).fail(function(data, textStatus, errorThrown) {
        console.log('error');
		console.debug("can't load scenario", scenarioId, data, textStatus, errorThrown);
		var event = jQuery.Event( "scenarioFetchingError" );
		event.scenario = data;
		modelingWidget.container.fadeIn();
		jQuery(modelingWidget).trigger(event);
		modelingWidget.spinner.stop();
	});
};

/**
 * Runs the current model.
 */
ModelingWidget.prototype.runTheModel = function() {
	var modelingWidget = this;

	var defaultValues = modelingWidget.options.defaultValues || {};
	var values = {};
	
	function setDefaultIfAvailable(idx, input) {
		if (input.inputs) {
			// this is a group of inputs
			$(input.inputs).each(setDefaultIfAvailable);
		}
		else {
			var inputName = input.name;
			var inputId = input.metaData.id;
			var val = defaultValues[inputId] || defaultValues[inputName];
			if (val) {
				values[inputId] = val;
			}
		}
		
	}

	$(modelingWidget.model.inputs).each(setDefaultIfAvailable);
	
	this.container.find(".valueBinding").each(function() {
		var label = jQuery(this).parent().parent().find("td.label").text();
		var id = jQuery(this).attr('data-id');
		var val = jQuery(this).val();

		if ((val == '' || val == 'NaN') && (defaultValues[id] || defaultValues[label])) {
			val = modelingWidget.options.defaultValues[id] || modelingWidget.options.defaultValues[label];
		}
		values[id] = val;
	});


	jQuery(modelingWidget).trigger("fetchingScenario");
	jQuery(modelingWidget).trigger("runningModel");

	var runModelUrl = modelingWidget.modelUrl + '/' + modelingWidget.modelId + '/run';
	console.debug(runModelUrl, values, modelingWidget.modelId, modelingWidget);
	jQuery.ajax({
		url: runModelUrl,
		data: { inputs: JSON.stringify(values)},
		dataType: 'json'
	}).done(function(data) {
		var event;
		jQuery(modelingWidget).show();
		jQuery(modelingWidget).hide();
        if(data.error){
            event = jQuery.Event( "scenarioFetchedWithErrors" );
        } else{
            event = jQuery.Event( "scenarioFetched" );
        }
		event.scenario = data;
        jQuery(modelingWidget).trigger(event);

	}).fail(function(a, b, c) {
		console.log("model run failed :(", a, b, c);
	});
};

/**
 * Loads model with given id. When scenario is loaded "modelFetched" 
 * event is triggered.
 * 
 * @param modelId id of a model
 * 
 */
ModelingWidget.prototype.loadModel = function(modelId) {
	console.debug('loading model', modelId);
	this.spinner.spin(document.getElementsByClassName("spinner-area")[0]);
	this.container.fadeOut();
	var modelingWidget = this;

	jQuery(modelingWidget).trigger("fetchingModel");
	var getModelUrl = this.modelUrl + '/' + modelId;
	console.debug('Getting model from ' + getModelUrl);
	jQuery.ajax({
		url: getModelUrl,
		data: {},
		dataType: 'json'
	}).done(function(data, textStatus, jqXHR) {
		console.debug('model loaded', modelId, data, textStatus, jqXHR);
		
		var event = jQuery.Event( "modelFetched" );
		event.model = data;
		modelingWidget.container.fadeIn();
		jQuery(modelingWidget).trigger(event);
		modelingWidget.spinner.stop();
	}).fail(function(data, textStatus, errorThrown) {
		console.debug("can't load model", scenarioId, data, textStatus, errorThrown);
		var event = jQuery.Event( "modelFetchingError" );
		event.scenario = data;
		modelingWidget.container.fadeIn();
		jQuery(modelingWidget).trigger(event);
		modelingWidget.spinner.stop();
	});
};


XCoLab.modeling = function(selector, options) {
	options.renderers = 'renderers' in options ? options.renderers : [];
	if (!('headerRenderer' in options)) { 
		options.renderers.push(XCoLab.modeling.headerRenderers[0]);
	}
	if (!('inputsRenderer' in options)) { 
		for (var i = 0; i < XCoLab.modeling.inputsRenderers.length; i++) {
			options.renderers.push(XCoLab.modeling.inputsRenderers[i]);
		}
	}
	if (!('outputsRenderer' in options)) { 
		options.renderers.push(XCoLab.modeling.outputsRenderers[0]);
	}
	return new ModelingWidget(selector, options);
};

/**
 * In case of error presents a modal view with the stacktrace.
 */
ModelingWidget.prototype.showStackTrace = function(data) {
    if (typeof showModalError != 'undefined' && showModalError){
        if(!data.scenario.error) return;
        // Get stack trace
        var tempDom = jQuery('<output>').append(jQuery.parseHTML(data.scenario.errorStackTrace));
        var appContainer = jQuery('#main', tempDom);
        console.log(tempDom, appContainer[0]);

        // Attach Modal
		var $errorModal = jQuery('#errorModal');
		$errorModal.remove();
        jQuery('body').append('<div id="errorModal" class="modal fade" style="width:700px;background: none;border: none;box-shadow: none;"><div class="modal-dialog"><div class="modal-content"><div class="modal-header"><button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button><h4 class="modal-title">Error</h4></div><div class="modal-body"></div><div class="modal-footer"><button type="button" class="btn btn-default" data-dismiss="modal">Close</button></div></div></div></div>');
        $errorModal.modal('show');
        jQuery('.modal-body').html(jQuery('#main', tempDom));
    }
};
/**
 * Toggles the model's edit mode setting
 */
ModelingWidget.prototype.updateEditMaskAppearance = function() {
	if(this.inputFreezed){
		$("div.act-edit_left :input").prop("disabled", true);
		$("div.act-edit_left .sliderCol").hide();
	} else {
		$("div.act-edit_left :input").prop("disabled", false);
		$("div.act-edit_left .sliderCol").show();
	}
};

/**
 * Toggles the model's edit mode setting
 */
ModelingWidget.prototype.toggleEditMask = function(allowInputEdit) {
	this.inputFreezed = !allowInputEdit;
};

XCoLab.modeling.outputItemRenderers = [];
XCoLab.modeling.outputsRenderers = [];
XCoLab.modeling.inputItemRenderers = [];
XCoLab.modeling.inputsRenderers = [];
XCoLab.modeling.headerRenderers = [];

