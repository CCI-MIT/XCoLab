if (typeof(XCoLab) == 'undefined') 
	throw new "XCoLab isn't defined";
if (typeof(XCoLab.modeling) == 'undefined')
	throw new "XCoLab.modeling isn't defined"; 

(function() {
	/**
	 * A header renderer for modeling widget
	 */
	function DefaultHeaderRenderer(modelingWidget) {
		this.modelingWidget = modelingWidget;		
		this.render(modelingWidget.container);
		var that = this;
		
		jQuery(modelingWidget).on("valueChanged", function() {
			that.container.find(".runSimulationButtonHighlight").effect("highlight", {}, 2000);
		});
	}
	
	
	DefaultHeaderRenderer.prototype = new XCoLab.modeling.BaseXCoLabModelingRenderer();
	DefaultHeaderRenderer.prototype.constructor = DefaultHeaderRenderer.prototype;
	
	DefaultHeaderRenderer.prototype.containerHtml = "<div class='modelingWidgetHeader'></div>";

	DefaultHeaderRenderer.prototype.renderEdit = function(container) {
		
		var that = this;
		this.container = container;
		container.append('<div class="act_left">' + 
			'<div class="acthead-l">Actions</div>' + 
			'</div> <!-- /act_left -->' +
			'<div class="act_right">' + 
			'<div class="acthead-r edit">' + 
			'Impacts' +
			'<div class="runmodel"><div class="runSimulationButton_bdr"><a href="javascript:;" class="runSimulationButton">' +
			'<div class="runSimulationButtonHighlight">' +
			'<span>RUN</span> the model' +
			'</div>' + 
			'</a></div></div></div></div> <!-- /act_right -->' + 
			'<div class="clearfix"></div>' +
			"<div class='actions_wrap'><div class='act_charts-top2'></div></div>");
		
		container.find(".runmodel").click(function() { that.modelingWidget.runTheModel(); });
	};
	
	DefaultHeaderRenderer.prototype.renderView = function(container) {
		container.append("<div class='act_left'><div class='acthead-l'>Actions</div></div>" + 
			"<div class='act_right'><div class='acthead-r'>Impacts</div></div>" + 
			"<div class='clearfix'></div>" + 
			"<div class='actions_wrap'><div class='act_charts-top2'></div></div>");
	};
	
	XCoLab.modeling.headerRenderers.push(function (modelingWidget) {
		return new DefaultHeaderRenderer(modelingWidget);
	});
}());