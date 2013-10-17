if (typeof(XCoLab) == 'undefined') 
	throw new "XCoLab isn't defined";
if (typeof(XCoLab.modeling) == 'undefined')
	throw new "XCoLab.modeling isn't defined"; 

(function() {
	function DefaultHeaderRenderer(modelingWidget) {
		this.modelingWidget = modelingWidget;		
	}
	
	
	
	DefaultHeaderRenderer.prototype = XCoLab.modeling.BaseXCoLabModelingRenderer;
	DefaultHeaderRenderer.prototype.containerHtml = "<div class='modelingWidgetHeader'></div>";

	DefaultHeaderRenderer.prototype.renderEdit = function(container) {
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
		
		container.find(".runmodel").click(function() { XCoLab.modeling.runTheModel(); });
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


/*
XCoLab.modeling.headerRenderer = new function() {
	function renderEdit(container) {
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
			'</a></div></div></div></div> <!-- /act_right -->');
		container.append("<div class='clearfix'></div>");
		container.append("<div class='actions_wrap'><div class='act_charts-top2'></div></div>");
		
		container.find(".runmodel").click(function() { XCoLab.modeling.runTheModel(); });
	};
	
	function renderView(container) {
		container.append("<div class='act_left'><div class='acthead-l'>Actions</div></div>");
		container.append("<div class='act_right'><div class='acthead-r'>Impacts</div></div>");
		container.append("<div class='clearfix'></div>");
		container.append("<div class='actions_wrap'><div class='act_charts-top2'></div></div>");
	};
	
	this.render = function(container) {
		
		var renderFunc = renderView;
		if (XCoLab.modeling.inEditMode) {
			renderFunc = renderEdit;
		}
		renderFunc.apply(this, [container]);
	};
};
*/