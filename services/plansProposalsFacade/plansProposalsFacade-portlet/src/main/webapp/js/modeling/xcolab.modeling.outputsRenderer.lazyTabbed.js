if (typeof(XCoLab) == 'undefined') 
	throw new "XCoLab isn't defined";
if (typeof(XCoLab.modeling) == 'undefined')
	throw new "XCoLab.modeling isn't defined"; 


XCoLab.modeling.outputsRenderer = new function() {
	var tabsHeaders = [];
	var tabsContents = [];

	var self = this;
	
	
	this.render = function(container, scenario) {
		this.container = container;
		this.scenario = scenario;
		
		// group physical outputs series
		var tabsToRender = []; 
		var physicalOutputs = {};

		container.find(".outputsContainer").remove();
		
		container = jQuery("<div class='outputsContainer act" + (XCoLab.modeling.inEditMode ? '-edit' : '') + "_right'></div>").appendTo(container);
		
		jQuery.each(scenario.outputs, function(idx, output) {
			if (output.series.length == 0) {
				return;
			} 
				
			if (output.chartType == 'FREE' ) {
				// group outputs by name
				if (!(output.name in physicalOutputs)) {
					physicalOutputs[output.name] = [];
				}
				physicalOutputs[output.name].push(output);
			}
			else if (output.chartType == 'TIME_SERIES') {
				tabsToRender.push(new ChartTabRenderer(output));
			}
			else {
				throw new "Unknown chart type";
			}
		});
		tabsToRender.push(new PhysicalImpactsTabRenderer(physicalOutputs));

		var headerContainer = jQuery("<div class='actions" + (XCoLab.modeling.inEditMode ? '-edit' : '') + "_chart-head-bg'></div>");
		var contentsContainer = jQuery("<div class='actions" + (XCoLab.modeling.inEditMode ? '-edit' : '') + "_chart'></div>");
		
		function showTab(idx, tab) {
			jQuery('.outputTab').hide();
			jQuery('.outputTabTrigger').removeClass('c');
			jQuery('#outputTabTrigger_' + idx).addClass('c');
			
			if (container.find('#outputTab_' + idx).length == 0) {
				var outputTabContainer = jQuery("<div class='outputTab' id='outputTab_" + idx + "'></div>").appendTo(contentsContainer);
				tab.renderContents(outputTabContainer);
			}
			
			jQuery('#outputTab_' + idx).show();
			
		}

		jQuery.each(tabsToRender, function(idx, tab) {
			var tabTriggerContainer = jQuery("<div class='rounded_button blugrid outputTabTrigger' id='outputTabTrigger_" + idx + "'></div>").appendTo(headerContainer);
			tabTriggerContainer.append("<a href='javascript:;'>" + tab.getName() + "</a>");
			tabTriggerContainer.click(function() {
				showTab(idx, tab);
			});
			
		});
		headerContainer.append("<div class='clearfix'></div>");
		headerContainer.appendTo(container);
		container.append("<div class='actions" + (XCoLab.modeling.inEditMode ? '-edit' : '') + "_chart-head-shade'></div>");
		contentsContainer.appendTo(container);
		
		// show first tab
		showTab(0, tabsToRender[0]);
	};
	
	jQuery(document).on("fetchingScenario", function(event) {
		jQuery(self.container.find('.outputsContainer').block());
		
	});

	jQuery(document).on("scenarioFetched", function(event) {
		console.log("new scenario fetched", event);
		self.render(self.container, event.scenario);
	});
	
};