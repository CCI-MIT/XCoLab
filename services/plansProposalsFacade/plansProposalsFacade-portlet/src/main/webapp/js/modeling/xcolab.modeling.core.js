if (typeof (XCoLab) == 'undefined') 
	XCoLab = {};

XCoLab['modeling'] = {
		modelingUrl: '/plansProposalsFacade-portlet/api/jsonws/modelrunner/get-scenario',
		runModelUrl: '/plansProposalsFacade-portlet/api/jsonws/modelrunner/run-model',
		container: jQuery('#xcolabModelingContainer'),
		serieRenderers: [],
		inputRenderers: [],
		defaultInputRenderer: { render: function(target, input) { console.log('no renderer found for input', input); } },
		simulationId: 10,
		inEditMode: false, // edit or view 
		
		/**
		 * returns true if value is valid, false otherwise, 
		 * @param newVal
		 * @param unit
		 * @param min
		 * @param max
		 * @param id
		 * @return
		 */
		isInputValueValid: function(input, newVal) {
			var unit = input.metaData.units[0];
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
		 */
		parseInputValue: function(input, value) {
			var unit = input.metaData.units[0];
			if (unit.toLowerCase().indexOf("percent") >= 0 || unit.toLowerCase().indexOf("%") >= 0) {
				//return parseFloat(value.replace("%")) / 100;
				return value.replace("%", '');

		    }
			return value;
		},
		isInteger: function(dataType) {
			if (dataType.indexOf("java.lang.Integer") >= 0) return true;
			if (dataType.indexOf("java.lang.Long") >= 0) return true;
		},
		isDouble: function(dataType) {
			if (dataType.indexOf("java.lang.Double") >= 0) return true;
			if (dataType.indexOf("java.lang.Float") >= 0) return true;
		},
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
		
		
		getInputRenderer: function(input) {
			var renderer = null;
			jQuery(this.inputRenderers).each(function(idx, rendererCandidate) {
				if (rendererCandidate.canRender(input)) {
					renderer = rendererCandidate;
				}
			});
			return renderer == null ? this.defaultInputRenderer : renderer;
		},
		
		getInputValue: function(input) {
			if ('value' in input) {
				return parseFloat(input.value.values[0][0]);
			}
			if ('default' in input.metaData && input.metaData['default'].length > 0) {
				return parseFloat(input.metaData['default'][0]);
			}
			
			return 0;
			
		},
		
		loadScenario: function(scenarioId) {
			var modeling = this;
			jQuery.ajax({
				url: this.modelingUrl, 
				data: {scenarioId: scenarioId}, 
				dataType: 'jsonp',
			}).done(function(data, textStatus, jqXHR) {
				//console.log('received data', data);
				modeling.simulationId = data.modelId;

				var event = jQuery.Event( "scenarioFetched" );
				event.scenario = data;
				jQuery(document).trigger(event);
				
				/*
				modeling.inputsRenderer.render(modeling.container, data);
				modeling.outputsRenderer.render(modeling.container, data);
				*/
				
			}).fail(function(data, textStatus, errorThrown) {
				console.log('fail' , data);
				
			});
		},
		
		runSimulation: function() {
			var values = {}; 
			jQuery(".valueBinding").each(function(idx, item) { values[$(item).attr('data-id')] = $(item).val(); });
			
			jQuery.ajax({
				url: this.runModelUrl, 
				data: {modelId: this.simulationId, inputs: JSON.stringify(values)}, 
				dataType: 'jsonp',
			}).done(function() {
				console.log("model run!");
			}).fail(function() {
				console.log("model run failed :(");
			});
			
			
		},
		init: function(containerSelector, mode) {
			this.inEditMode = mode == 'edit';
			this.container = $(containerSelector);
			this.outputsRenderer.container = this.container;
			this.inputsRenderer.container = this.container;
			var runTheModelTrigger = jQuery("<div class='runSimulationButton_bdr'>" + 
					"<a href='javascript:;' class='runSimulationButton'><div class='runSimulationButtonHighlight'>" +
					"<span>RUN</span> the model</div></a><div>").appendTo(this.container);
			
			var self = this;
			runTheModelTrigger.click(function() {
				var values = {};
				jQuery(".valueBinding").each(function() {
					values[jQuery(this).attr('data-id')] = jQuery(this).val();
				});
				
				jQuery(document).trigger("fetchingScenario");
				
				jQuery.ajax({
					url: self.runModelUrl, 
					data: {modelId: self.simulationId, inputs: JSON.stringify(values)}, 
					dataType: 'jsonp',
				}).done(function(data) {
					self.simulationId = data.modelId;
					console.log("model run!", data);
					var event = jQuery.Event( "scenarioFetched" );
					event.scenario = data;
					jQuery(document).trigger(event);
				}).fail(function(a, b, c) {
					console.log("model run failed :(", a, b, c);
				});
				
			});
			
			this.headerRenderer.render(this.container);
			
			
		}

		
};