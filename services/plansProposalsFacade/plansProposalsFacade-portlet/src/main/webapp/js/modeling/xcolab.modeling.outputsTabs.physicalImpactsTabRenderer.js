function PhysicalImpactsTabRenderer(physicalImpacts) {
	console.log('creating physical impacts', physicalImpacts);
	this.getName = function() {
		return "Physical impacts";
	};
	
	this.getOrder = function() {
		return 1<<31;
	};
	
	this.renderContents = function(container) {
		var physicalImpactsContainer = jQuery("<div id='freeOutputPhysical'></div>").appendTo(container);
		physicalImpactsContainer.append("<h1>Physical impacts</h1>");
		var navigation = jQuery("<ul class='chart_sub-menu'></ul>");
		var impactVisible = true, impactIdx = 0;
		
		function showItem(itemNumber) {
			console.log('show impact ' + itemNumber);
			physicalImpactsContainer.find('.physicalImpactContent').hide();
			physicalImpactsContainer.find('.impactTrigger').removeClass('c');
			physicalImpactsContainer.find('#impact_' + itemNumber).show();
			physicalImpactsContainer.find('#impactTrigger_' + itemNumber).addClass('c');
		}
		
		jQuery.each(physicalImpacts, function(name, groupedSeries) {
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
		jQuery.each(physicalImpacts, function(idx, groupedSeries) {
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
}