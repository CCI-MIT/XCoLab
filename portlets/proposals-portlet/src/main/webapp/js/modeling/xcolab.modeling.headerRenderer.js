if (typeof(XCoLab) == 'undefined') 
	throw new "XCoLab isn't defined";
if (typeof(XCoLab.modeling) == 'undefined')
	throw new "XCoLab.modeling isn't defined"; 


XCoLab.modeling.headerRenderer = new function() {
	var self = this;
	this.scenarioId = null;
	this.modelId = null;
	
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