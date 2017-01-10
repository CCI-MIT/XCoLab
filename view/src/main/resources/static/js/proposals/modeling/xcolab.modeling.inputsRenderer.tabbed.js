if (typeof(XCoLab) == 'undefined') 
	throw new Error("XCoLab isn't defined");
if (typeof(XCoLab.modeling) == 'undefined')
	throw new Error("XCoLab.modeling isn't defined");


(function() {
	function DefaultInputsRenderer(modelingWidget) {
		this.modelingWidget = modelingWidget;		
		this.modelId = -1;
		this.scenarioId = -1;
		var that = this;
		
		jQuery(modelingWidget).on('scenarioFetched', function(event) {
			if (! event.scenario.usesCustomInputs) {
				that.render(modelingWidget.container, event.scenario);
			}
			that.modelId = event.scenario.modelId;
			that.scenarioId = event.scenario.scenarioId;
		});
		
		jQuery(modelingWidget).on('modelFetched', function(event) {
			if (! event.model.usesCustomInputs) {
				that.render(modelingWidget.container, event.model);
			}
			that.modelId = event.model.modelId;
			that.scenarioId = -1;
		});
	}
	
	DefaultInputsRenderer.prototype = new XCoLab.modeling.BaseXCoLabModelingRenderer();
	
	
	DefaultInputsRenderer.prototype.containerHtml = "<div class='act_left act_left-list'></div>";
	DefaultInputsRenderer.prototype.containerHtmlEdit = "<div class='act-edit_left'></div>";

	DefaultInputsRenderer.prototype.renderEdit = function(container, model) {
		this.modelId = model.modelId;
		var hasTabs = false;
		var tabHeaders = [];
		var modelingWidget = this.modelingWidget;
		jQuery.each(model.inputs, function(idx, input) {
			if ('groupType' in input && input.groupType == 'TAB') {
				hasTabs = true;
				tabHeaders.push(input.name);
			} 
		});
			
		function showTab(idx) {
			jQuery('.inputTabContent').hide();
			jQuery('#inputTabContent_' + idx).show();
			jQuery('.inputTabTrigger').removeClass('c');
			jQuery('#inputTabTrigger_' + idx).addClass('c');
		}
		
		if (hasTabs) {
			// render tab headers
			var headerContainer = jQuery("<div class='act-edit_left-top'></div>").appendTo(container);
			
			jQuery.each(tabHeaders, function(idx, name) {
				var tabTrigger = jQuery("<div class='rounded_button blugrid inputTabTrigger' id='inputTabTrigger_" + idx + "'><a href='javascript:;'>" + name + "</a></div>");
				tabTrigger.click(function () { showTab(idx); });
				headerContainer.append(tabTrigger);
			});
			
			headerContainer.append("<div class='clearfix'></div>");
			container.append("<div class='actions_chart-head-shade'></div>");
		}

		jQuery.each(model.inputs, function(idx, input) {
			modelingWidget.getInputRenderer(input).render(container, input, modelingWidget, idx);
			if (hasTabs) {
				jQuery('#inputTabContent_' + idx).hide();
			}
			else {
				if (idx < model.inputs.length - 1) {
					jQuery(container.append("<div class='control_div'></div>"));
				}
			}
			
			
		});
		if (hasTabs) {
			showTab(0);
		}
	};
	
	DefaultInputsRenderer.prototype.renderView = function(container, model) {
		var that = this;
		var inputsContainer = jQuery("<div class='act_left act_left-list'></div>").appendTo(container);
		jQuery.each(model.inputs, function(idx, input) {
			var inputContainer = jQuery("<div></div>").appendTo(inputsContainer);
			that.modelingWidget.getInputRenderer(input).render(inputContainer, input, that.modelingWidget, idx);
		});
	};
	DefaultInputsRenderer.prototype.render = function(container, scenario) {
		var updateScenario = scenario.scenarioId != this.scenarioId;
		if(updateScenario){
			if(updateScenario && container.find(".act-edit_left").length > 0){
				container.find(".act-edit_left").remove();
			}
		}
		if (scenario.modelId != this.modelId || updateScenario) {
			XCoLab.modeling.BaseXCoLabModelingRenderer.prototype.render.apply(this, [container, scenario, updateScenario]);
		}
	};
	
	XCoLab.modeling.inputsRenderers.push(function (modelingWidget) {
		return new DefaultInputsRenderer(modelingWidget);
	});
}());