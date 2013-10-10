if (typeof(XCoLab) == 'undefined') 
	throw new "XCoLab isn't defined";
if (typeof(XCoLab.modeling) == 'undefined')
	throw new "XCoLab.modeling isn't defined"; 
if (typeof(XCoLab.modeling.serieRenderers) == 'undefined')
	XCoLab.modeling.serieRenderers = [];

var freeSerieRenderer = {

	canRender: function(serie) {
		return serie.chartType == 'FREE' && serie.series.length > 0;
	},
	render: function(target, seriesGroup) {
		jQuery.each(seriesGroup.series, function(idx, singleSerie) {
			target.append("<p>");
			target.append("<span>" + singleSerie.variable.metaData.labels[1] + " impacts at " + singleSerie.variable.values[0][0] + "</span> ");
			target.append(singleSerie.variable.values[0][1]);
			target.append("</p>");
			
		});
	}
};

XCoLab.modeling.serieRenderers.push(freeSerieRenderer);
