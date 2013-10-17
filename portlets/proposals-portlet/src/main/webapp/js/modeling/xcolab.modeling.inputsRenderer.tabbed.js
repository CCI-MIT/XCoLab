if (typeof(XCoLab) == 'undefined') 
	throw new "XCoLab isn't defined";
if (typeof(XCoLab.modeling) == 'undefined')
	throw new "XCoLab.modeling isn't defined"; 


XCoLab.modeling.inputsRenderer = new function() {
	var self = this;
	this.scenarioId = null;
	this.modelId = null;
	
	function renderEdit(container, model) {
		
		var hasTabs = false;
		
		var tabHeaders = [];
		
		
		jQuery.each(model.inputs, function(idx, input) {
			if ('groupType' in input && input.groupType == 'TAB') {
				hasTabs = true;
				tabHeaders.push(input.name);
			} 
		});
			
		function showTab(idx) {
			jQuery('.inputTabContent').hide();
			jQuery('#inputTabContent_' + idx).show();
			jQuery('.inputTabTrigger').removeClass('c');
			jQuery('#inputTabTrigger_' + idx).addClass('c');
		}
		
		var inputsContainer = jQuery("<div class='act" + (XCoLab.modeling.inEditMode ? '-edit' : '') + "_left'></div>").appendTo(container);
		if (hasTabs) {
			// render tab headers
			var headerContainer = jQuery("<div class='act" + (XCoLab.modeling.inEditMode ? '-edit' : '') + "_left-top'></div>").appendTo(inputsContainer);
			
			jQuery.each(tabHeaders, function(idx, name) {
				var tabTrigger = jQuery("<div class='rounded_button blugrid inputTabTrigger' id='inputTabTrigger_" + idx + "'><a href='javascript:;'>" + name + "</a></div>");
				tabTrigger.click(function () { showTab(idx); });
				headerContainer.append(tabTrigger);
			});
			headerContainer.append("<div class='clearfix'></div>");
			if (XCoLab.modeling.inEditMode) {
				inputsContainer.append("<div class='actions_chart-head-shade'></div>");
			}
		}

		jQuery.each(model.inputs, function(idx, input) {
			XCoLab.modeling.getInputRenderer(input).render(inputsContainer, input, XCoLab.modeling, idx);
			if (hasTabs) {
				jQuery('#inputTabContent_' + idx).hide();
			}
			else {
				jQuery(inputsContainer.append("<div class='control_div'></div>"));
			}
			
			
		});
		if (hasTabs) {
			showTab(0);
		}
			 
	};
	
	function renderView(container, model) {
		var inputsContainer = jQuery("<div class='act_left act_left-list'></div>").appendTo(container);
		jQuery.each(model.inputs, function(idx, input) {
			var inputContainer = jQuery("<div></div>").appendTo(inputsContainer);
			XCoLab.modeling.getInputRenderer(input).render(inputContainer, input, XCoLab.modeling, idx);
		});
		
	};
	
	this.render = function(container, model) {
		if ('modelId' in model && self.modelId == model.modelId) {
			// if we are showing inputs from the same model, don't rerender them
			return;
		}
		self.modelId = model.modelId;
		
		var renderFunc = renderView;
		if (XCoLab.modeling.inEditMode) {
			renderFunc = renderEdit;
		}
		renderFunc.apply(this, [container, model]);
	};
	
	jQuery(document).on("scenarioFetched", function(event) {
		self.render(self.container, event.scenario);
	});
	
	
};