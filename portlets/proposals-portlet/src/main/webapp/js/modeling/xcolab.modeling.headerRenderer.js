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

		jQuery(modelingWidget).on('scenarioFetched', function(event) {
			that.updateNameDescription(event.scenario);
		});
		
		jQuery(modelingWidget).on('modelFetched', function(event) {
			that.updateNameDescription(event.model);
		});
	}
	
	
	DefaultHeaderRenderer.prototype = new XCoLab.modeling.BaseXCoLabModelingRenderer();
	DefaultHeaderRenderer.prototype.constructor = DefaultHeaderRenderer.prototype;
	
	DefaultHeaderRenderer.prototype.containerHtml = "<div class='modelingWidgetHeader'></div>";

	DefaultHeaderRenderer.prototype.renderEdit = function(container) {
		
		var that = this;
		this.container = container;
		
		container.append('<a class="model_name_description" target="_blank">' +
			'<h2 class="model_name"></h2></a><p class="model_description"></p>' +
			'<div class="act_left">' +
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
		this.container = container;
		container.append("<a class='model_name_description' target='_blank'><h2 class='model_name'></h2><p class='model_description'></p><div class='act_left'><div class='acthead-l'>Actions</div></div>" +
			"<div class='act_right'><div class='acthead-r'>Impacts</div></div>" +
			"<div class='clearfix'></div>" + 
			"<div class='actions_wrap'><div class='act_charts-top2'></div></div>");
	};
	
	DefaultHeaderRenderer.prototype.updateNameDescription = function(model) {
		console.log(this, this.container);
		this.container.find('.model_name').text(model.modelName);
		this.container.find('.model_description').text(model.modelDescription);

		var isModelEMF = model.modelName.toLowerCase().indexOf("emf") != -1;
		var isModelEnRoads = model.modelName.toLowerCase().indexOf("enroads") != -1;
		var modelLink ="";
		var brand = "";
		if (isModelEMF) {
			brand = $("<div class='brand' onclick='location.href=\"https://emf.stanford.edu\"'></div>");
			modelLink = "/resources/-/wiki/Main/EMF27+model+runs";
		} else if(isModelEnRoads){
			brand = $("<div class='brand' onclick='location.href=\"http://www.climateinteractive.org\"'></div>");
			modelLink = "/resources/-/wiki/Main/EnROADS+by+Climate+Interactive";
		}
		this.container.find('.model_name_description').attr("href", modelLink);
		this.container.find(".model_description .brand").remove();
		brand.addClass(model.modelName);
		this.container.find(".model_description").append(brand);
	}
	
	XCoLab.modeling.headerRenderers.push(function (modelingWidget) {
		return new DefaultHeaderRenderer(modelingWidget);
	});
}());