

if (typeof (console) == 'undefined') {
	window.console = {
			log: function() {},
			debug: function() {},
			error: function() {}
	};
}

if (typeof (XCoLab) == 'undefined') 
	XCoLab = {};

/** 
 * Base object responsible for handling rendering of ROMA modeling widget.
 * 
 * It provides an API to be used to show the widget, two modes are supported (edit/view).
 * 
 */
XCoLab.modeling = {
		getScenarioUrl: '/plansProposalsFacade-portlet/api/jsonws/modelrunner/get-scenario',
		getModelUrl: '/plansProposalsFacade-portlet/api/jsonws/modelrunner/get-model',
		runModelUrl: '/plansProposalsFacade-portlet/api/jsonws/modelrunner/run-model',
		container: jQuery('#xcolabModelingContainer'),
		serieRenderers: [],
		inputRenderers: [],
		defaultInputRenderer: { render: function(target, input) { console.log('no renderer found for input', input); } },
		simulationId: 10,
		inEditMode: false, // edit or view 
		
		/**
		 * returns true if value is valid, false otherwise, 
		 * @param input for which value is to be checked 
		 * @param newVal new value that is to be used for given input
		 * @return true if value is valid false otherwise
		 */
		isInputValueValid: function(input, newVal) {
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
		},
		
		/**
		 * Parses value according to unit (currently just removes % sign if unit is a percent)
		 * @param input input for which value should be parsed
		 * @param value value that should be parsed
		 * @return parsed value
		 */
		parseInputValue: function(input, value) {
			var unit = input.metaData.units[0];
			if (unit.toLowerCase().indexOf("percent") >= 0 || unit.toLowerCase().indexOf("%") >= 0) {
				return value.replace("%", '');
		    }
			return value;
		},
		
		/**
		 * Checks if given data type is an integer type.
		 * @param dataType string representation of a data type (java.lang.Integer for example)
		 * @return true if dataType represents an integer false otherwise
		 */
		isInteger: function(dataType) {
			if (dataType.indexOf("java.lang.Integer") >= 0) return true;
			if (dataType.indexOf("java.lang.Long") >= 0) return true;
			return false;
		},
		
		/**
		 * Checks if given data type is a double type.
		 * @param dataType string representation of a data type (java.lang.Double for example)
		 * @return true if dataType represents a double false otherwise
		 */
		isDouble: function(dataType) {
			if (dataType.indexOf("java.lang.Double") >= 0) return true;
			if (dataType.indexOf("java.lang.Float") >= 0) return true;
			return false;
		},
		
		/**
		 * Formats input value in human readable way.
		 * 
		 * @param input input for which value is to be presented
		 * @param value value that should be formatted
		 * @return formatted value
		 */
		formatInputValue: function(input, value) {
			var unit = input.metaData.units[0];
			var dataType = input.metaData.profiles[0];
			
			if (unit.toLowerCase().indexOf("percent") >= 0 || unit.toLowerCase().indexOf("%") >= 0) {

				return (value * 1).toFixed(0) + "%";
			}
		    else if (dataType != null) {
		    	if (this.isInteger(dataType)) 
		    		return (value *1).toFixed(0);
		    	else 
		    		return (value * 1).toFixed(2);
		    }
			return value;
		},
		
		/**
		 * Returns a renderer for given input, if concrete renderer can't be found
		 * a default renderer is returned (default renderer doesn't present anything
		 * to the user)
		 * 
		 * @param input input for which renderer should be returned
		 * @return renderer for an input
		 */		
		getInputRenderer: function(input) {
			var renderer = null;
			jQuery(this.inputRenderers).each(function(idx, rendererCandidate) {
				if (rendererCandidate.canRender(input)) {
					renderer = rendererCandidate;
				}
			});
			return renderer == null ? this.defaultInputRenderer : renderer;
		},
		
		/**
		 * Returns value for input. If no value is set for given input then default value is returned.
		 * 
		 * @param input input for which value should be returned.
		 * @return value for given input
		 */
		getInputValue: function(input) {
			if ('value' in input) {
				return parseFloat(input.value.values[0][0]);
			}
			if ('default' in input.metaData && input.metaData['default'].length > 0) {
				return parseFloat(input.metaData['default'][0]);
			}
			
			return 0;
			
		},
		
		/**
		 * Loads scenario with given id. When scenario is loaded "scenarioFetched" 
		 * event is triggered, if there was an error "scenarioFetchingError" is triggered.
		 * 
		 * @param scenarioId id of a scenario
		 * 
		 */
		loadScenario: function(scenarioId) {
			var modeling = this;

			jQuery.ajax({
				url: this.getScenarioUrl, 
				data: {scenarioId: scenarioId}, 
				dataType: 'jsonp',
			}).done(function(data, textStatus, jqXHR) {
				modeling.simulationId = data.modelId;

				var event = jQuery.Event( "scenarioFetched" );
				event.scenario = data;
				jQuery(document).trigger(event);
				
			}).fail(function(data, textStatus, errorThrown) {
				var event = jQuery.Event( "scenarioFetchingError" );
				event.scenario = data;
				jQuery(document).trigger(event);
				
			});
		},
		
		/**
		 * Runs the current model.
		 */
		runTheModel: function() {
			var values = {};
			this.container.find(".valueBinding").each(function() {
				values[jQuery(this).attr('data-id')] = jQuery(this).val();
			});
			
			jQuery(document).trigger("fetchingScenario");
			
			jQuery.ajax({
				url: this.runModelUrl, 
				data: {modelId: this.simulationId, inputs: JSON.stringify(values)}, 
				dataType: 'jsonp',
			}).done(function(data) {
				this.simulationId = data.modelId;
				var event = jQuery.Event( "scenarioFetched" );
				event.scenario = data;
				jQuery(document).trigger(event);
			}).fail(function(a, b, c) {
				console.log("model run failed :(", a, b, c);
			});
			
		},
		init: function(containerSelector, mode) {
			this.inEditMode = mode == 'edit';
			this.container = $(containerSelector);
			this.outputsRenderer.container = this.container;
			this.inputsRenderer.container = this.container;
			
			this.headerRenderer.render(this.container);
			
			
			function initActTooltips() {
		        XCoLab.modeling.container.on('mouseover', '.actInputDef', function() {
		                var actInputDef = jQuery(this);
		                var tooltip = actInputDef.find(".act_tooltip");
		                tooltip.show();
		        });
		        XCoLab.modeling.container.on('mouseout', '.actInputDef', function() {
	                var actInputDef = jQuery(this);
	                var tooltip = actInputDef.find(".act_tooltip");
                    tooltip.hide();
		        });

			}
			initActTooltips();

			
		}

		
};