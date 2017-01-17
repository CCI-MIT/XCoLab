if (typeof(XCoLab) == 'undefined') 
	throw new Error("XCoLab isn't defined");
if (typeof(XCoLab.modeling) == 'undefined')
	throw new Error("XCoLab.modeling isn't defined");

(function() {
	function DefaulPhysicalImpactsRenderer() {
	}
	
	DefaulPhysicalImpactsRenderer.prototype.getName = function() {
		return "Physical impacts";
	};
	
	DefaulPhysicalImpactsRenderer.prototype.getOrder = function() {
		return 1<<30;
	};

	DefaulPhysicalImpactsRenderer.prototype.canRender = function(output) {
		return output.chartType == 'PHYSICAL_IMPACTS';
	};
	


	DefaulPhysicalImpactsRenderer.prototype.render = function(container, output, modelingWidget, parent) {
		console.log("output", output);
		var physicalImpactsContainer = jQuery("<div id='freeOutputPhysical'></div>").appendTo(container);
		container.append("<div class='clearfix'></div>");
		var navigation = jQuery("<ul class='chart_sub-menu'></ul>");
		var impactVisible = true, impactIdx = 0;
		
		function showItem(itemNumber) {
			physicalImpactsContainer.find('.physicalImpactContent').hide();
			physicalImpactsContainer.find('.impactTrigger').removeClass('c');
			physicalImpactsContainer.find('#impact_' + itemNumber).show();
			physicalImpactsContainer.find('#impactTrigger_' + itemNumber).addClass('c');
		}
		
		jQuery.each(output, function(name, groupedSeries) {
			var itemTrigger = jQuery("<li class='impactTrigger " + (impactVisible ? 'c' : '') + "' id='impactTrigger_" + impactIdx + "'><a>" + name + "</a></li>").appendTo(navigation);
			var itemId = impactIdx;
			itemTrigger.click(function() { showItem(itemId); });
			impactIdx++;
			impactVisible = false;
		});
		
		var html = [];
		impactIdx = 0;
		impactVisible = true;
		physicalImpactsContainer.append(navigation);
		jQuery.each(output, function(idx, groupedSeries) {
			jQuery.each(groupedSeries, function(idx, series) {
				html.push("<div class='physicalImpactContent chart_sub-description " + (impactVisible ? '' : 'hidden') + "' id='impact_" + impactIdx + "'>");
				jQuery.each(series.series, function(singleSerieIdx, singleSerie) {
					html.push("<p>");
					html.push("<span>" + singleSerie.variable.metaData.labels[1] + " impacts at " + singleSerie.variable.values[0][0] + "</span> ");
					html.push(singleSerie.variable.values[0][1]);
				});
				html.push("</p></div>");
				impactVisible = false;
			});
			impactIdx++;
			
		});
		physicalImpactsContainer.append(html.join(''));
		
		physicalImpactsContainer.append("<div class='clearfix'></div>");
	};
	
	
	

	XCoLab.modeling.outputItemRenderers.push(new DefaulPhysicalImpactsRenderer());
})();