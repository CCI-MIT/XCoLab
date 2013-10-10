if (typeof (XCoLab) == 'undefined') 
	XCoLab = {};

XCoLab['modeling'] = {
		modelingUrl: '/plansProposalsFacade-portlet/api/jsonws/modelrunner/get-scenario',
		container: jQuery('#xcolabModelingContainer'),
		
		loadScenario: function(scenarioId) {
			var modeling = this;
			jQuery.ajax({
				url: this.modelingUrl, 
				data: {scenarioId: scenarioId}, 
				dataType: 'jsonp',
			}).done(function(data, textStatus, jqXHR) {
				console.log('received data', data);
				
				jQuery.each(data.outputs, function() {
					modeling.container.append('<h2>' + this.name + "</h2>");
					var chartContainer = jQuery("<div></div>");
					modeling.container.append(chartContainer);
					
					if (this.chartType == 'TIME_SERIES') {
						var valuesCombined = [];
						var yaxis = {labelRenderer: jQuery.jqplot.CanvasAxisLabelRenderer};
						var xaxis = {autoscale: false, tickOptions:{formatString:'%d'}, ticks: xaxisTicks, labelRenderer: jQuery.jqplot.CanvasAxisLabelRenderer, label: 'Year'};
						
						
						jQuery.each(this.series, function() {
					
							modeling.container.append('<h3>' + this.name + "</h3>");
							var val = [];
							for (var i = 0; i < this.variable.values.length; i++) {
								val.push([parseInt(this.variable.values[i][0]), parseFloat(this.variable.values[i][1])]);
							}
							valuesCombined.push(val);
							
							try {
								console.log(this);
							}
							catch (e) {
								console.log(e);
							}
						});
						
						function pickTickInterval(min, max,preferredTicks) {
							// probably here...
						     var interval = (max - min)/preferredTicks;
						    
						    var divideback = .1;
						     while (interval != Math.floor(interval)) {
						    	 //log.debug("loop: " + interval + "\tdivideback: " + divideback);
						          interval*=10;
						          divideback*=10;
						     }
						     interval=(Math.round((interval / 10)*2)/2)/divideback ;
						     return interval;
						}
						
						if (min != null && max != null) {
							yaxis.min = min;
							yaxis.max = max;

							if (max - min > 2000) {
								yaxis.tickInterval = 1000;
								yaxis.tickOptions = {formatString:"%d"};	
							}
							else if (max - min > 100) {
								yaxis.tickInterval = 100;
								yaxis.tickOptions = {formatString:"%d"};
							}
							else if (max - min > 5 && max - min < 20) {
								//yaxis.tickInterval = pickTickInterval(min, max, parseInt(max-min));//((label == "Atmospheric CO2 Concentration") ? 100 : 30);// pickTickInterval(min,max,5)
								yaxis.tickInterval = 1;
								yaxis.tickOptions = {formatString:"%d"};
							}
							else if (max - min > 20 && max-min < 30) {
								yaxis.tickInterval = 2;
								yaxis.tickOptions = {formatString:"%.1f"};
							}
							else if (max - min == 20) {
								yaxis.tickOptions = {formatString:"%d"};
							}
								
								
								
							//yaxis.tickInterval = 1;
							
						}
						if (indexMin != null && indexMax != null) {
							xaxis.min = indexMin;
							xaxis.max = indexMax;
						}
						
						var plotOptions = {
								stackSeries: false,
						    	showMarker: false,
						    	seriesDefaults: {},
						    	grid: {
						    		drawGridLines: true,
						    		background: "#f3f2ec",
						    		shadow: false
						    	},
						    	legend : {
						    		show :true,
						    		location :'nw',
						    		placement: 'insideGrid',
						    		marginTop: "320px",
						    		yoffset :320,
						    		xoffset:0
						    	}
						};
						chartContainer.jqplot(valuesCombined, plotOptions);
						console.log(valuesCombined);
					}
					
					
					
				});
				
				
				
			}).fail(function(data, textStatus, errorThrown) {
				console.log('fail' , data);
				
			});
		},
		init: function(containerSelector) {
			this.container = $(containerSelector);
		}

		
};