if (typeof(XCoLab) == 'undefined') 
	throw new Error("XCoLab isn't defined");
if (typeof(XCoLab.modeling) == 'undefined')
	throw new Error("XCoLab.modeling isn't defined");

(function() {
	function DefaultOutputsRenderer(modelingWidget) {
		this.modelingWidget = modelingWidget;		
		this.modelId = -1;
		var that = this;
		
		jQuery(modelingWidget).on('scenarioFetched', function(event) {
			modelingWidget.container.find(".outputsContainer").remove();
			that.render(modelingWidget.container, event.scenario);
			var event = jQuery.Event( "scenarioRendered" );
			event.scenario = event.scenario;
			jQuery(modelingWidget).trigger(event);
			
		});

		jQuery(modelingWidget).on('modelFetched', function(event) {
			modelingWidget.container.find(".outputsContainer").remove();
			that.render(modelingWidget.container, event.model);
		});

		jQuery(modelingWidget).on("fetchingScenario", function(event) {
			modelingWidget.container.find('.outputsContainer').block();
		});

	}
	
	DefaultOutputsRenderer.prototype = new XCoLab.modeling.BaseXCoLabModelingRenderer();
	DefaultOutputsRenderer.prototype.containerHtmlEdit = "<div class='outputsContainer act-edit_right'></div>";
	DefaultOutputsRenderer.prototype.containerHtml = "<div class='outputsContainer act_right'></div>";
	
	DefaultOutputsRenderer.prototype.renderTabs = function(container, scenario) {
		
		if (!('scenarioId' in scenario)) {
			container.append(
				"<div class='actions-edit_chart'>" +
					"<div class='runTheModelToSee_container'>" +
						"<div class='runTheModelToSee'>" +
							"<div class='runTheModelToSee_box'>" +
								'Use the "RUN the model" button to show results' +
							"</div>" +
						"</div>" +
					"</div>" +
				"</div>");	
			jQuery(this.modelingWidget).trigger('runTheModelMessageRendered');
			return;
		}
		
		var modelingWidget = this.modelingWidget;
		// group physical outputs series
		var tabsToRender = []; 
		var hasPhysicalOutputs = false;
		var physicalOutputs = {chartType: 'PHYSICAL_IMPACTS', name: "Physical impacts", outputs: {}};
		
		jQuery.each(scenario.outputs, function(idx, output) {
			if (output.series.length == 0) {
				return;
			} 
		
			if (output.chartType == 'FREE' ) {
				// group outputs by name
				if (!(output.name in physicalOutputs.outputs)) {
					physicalOutputs.outputs[output.name] = [];
				}
				physicalOutputs.outputs[output.name].push(output);
				hasPhysicalOutputs = true;
			}
			else {
				tabsToRender.push({renderer: modelingWidget.getOutputRenderer(output), output: {outputs: output}});
			}
		});
		if (hasPhysicalOutputs) {
			tabsToRender.push({renderer: modelingWidget.getOutputRenderer(physicalOutputs), output: physicalOutputs});
		}

		var headerContainer = jQuery("<div class='actions" + (this.modelingWidget.inEditMode ? '-edit' : '') + "_chart-head-bg'></div>");
		var contentsContainer = jQuery("<div class='actions" + (this.modelingWidget.inEditMode ? '-edit' : '') + "_chart'></div>");
		
		function showTab(idx, tab) {
			jQuery('.outputTab').hide();
			jQuery('.outputTabTrigger').removeClass('c');
			jQuery('#outputTabTrigger_' + idx).addClass('c');
			
			if (container.find('#outputTab_' + idx).length == 0) {
				var outputTabContainer = jQuery("<div class='outputTab' id='outputTab_" + idx + "'></div>").appendTo(contentsContainer);
				tab.renderer.render(outputTabContainer, tab.output.outputs, modelingWidget, idx);
			}
			
			jQuery('#outputTab_' + idx).show();
			
		}

		jQuery.each(tabsToRender, function(idx, tab) {
			var tabTriggerContainer = jQuery("<div class='rounded_button blugrid outputTabTrigger' id='outputTabTrigger_" + idx + "'></div>").appendTo(headerContainer);
			tabTriggerContainer.append("<a href='javascript:;'>" + tab.renderer.getName(tab.output.outputs) + "</a>");
			tabTriggerContainer.click(function() {
				showTab(idx, tab);
			});
			
		});
		headerContainer.append("<div class='clearfix'></div>");
		headerContainer.appendTo(container);
		container.append("<div class='actions" + (this.modelingWidget.inEditMode ? '-edit' : '') + "_chart-head-shade'></div>");
		contentsContainer.appendTo(container);
		
		// show first tab
		showTab(0, tabsToRender[0]);
	};
	
	DefaultOutputsRenderer.prototype.renderView = function() { this.renderTabs.apply(this, arguments); };
	DefaultOutputsRenderer.prototype.renderEdit = function() { this.renderTabs.apply(this, arguments); };
	
	
	
	XCoLab.modeling.outputsRenderers.push(function (modelingWidget) {
		return new DefaultOutputsRenderer(modelingWidget);
	});
}());