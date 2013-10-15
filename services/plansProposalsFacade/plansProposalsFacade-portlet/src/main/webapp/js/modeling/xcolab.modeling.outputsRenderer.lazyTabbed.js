if (typeof(XCoLab) == 'undefined') 
	throw new "XCoLab isn't defined";
if (typeof(XCoLab.modeling) == 'undefined')
	throw new "XCoLab.modeling isn't defined"; 


XCoLab.modeling.outputsRenderer = new function() {
	var tabsHeaders = [];
	var tabsContents = [];
	
	this.render = function(container, scenario) {
		
		// group physical outputs series
		var tabsToRender = []; 
		var physicalOutputs = {};
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

		var headerContainer = jQuery("<div class='actions_chart-head-bg'></div>");
		var contentsContainer = jQuery("<div class='actions_chart'></div>");
		
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
		headerContainer.append("<div class='clearfix'></div><div class='actions_chart-head-shade'></div>");
		headerContainer.appendTo(container);
		contentsContainer.appendTo(container);
		
		// show first tab
		showTab(0, tabsToRender[0]);
	};
	
};