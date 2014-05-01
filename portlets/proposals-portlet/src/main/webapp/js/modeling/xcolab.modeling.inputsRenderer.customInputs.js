if (typeof(XCoLab) == 'undefined') 
	throw new "XCoLab isn't defined";
if (typeof(XCoLab.modeling) == 'undefined')
	throw new "XCoLab.modeling isn't defined"; 

(function() {
	function CustomInputsRenderer(modelingWidget) {
		this.modelingWidget = modelingWidget;		
		this.modelId = -1;
		var that = this;
		console.log("creating custom inputs renderer");
		
		jQuery(modelingWidget).on('scenarioFetched', function(event) {
			/*if (event.model.usesCustomInputs) {
			  // do nothing
			}*/
		});
		
		jQuery(modelingWidget).on('modelFetched', function(event) {
			if (event.model.usesCustomInputs) {
				that.model = event.model;
				that.render(modelingWidget.container, event.model);
			}
		});
	}
	
	CustomInputsRenderer.prototype = new XCoLab.modeling.BaseXCoLabModelingRenderer();
	
	
	CustomInputsRenderer.prototype.containerHtml = "<div class='act_left act_left-list'></div>";
	CustomInputsRenderer.prototype.containerHtmlEdit = "<div class='act-edit_left'></div>";

	CustomInputsRenderer.prototype.renderEdit = function(container, model) {
		var self = this;
		this.container = container
		this.definition = {};
		this.values = {};
		this.visibleScreen = {};
		this.screensStack = [];
		
		this.renderDefinition = function(definition) {
			self.definition = definition;
			self.container.empty();
			self.screensStack = [];
			$(definition.screens).each(function(key, screen) {
				var screenHtml = [];
				
				screenHtml.push("<div class='wizardScreen' id='screen_" + screen.name + "'>");
				screenHtml.push("<h3>");
				screenHtml.push(screen.title);
				screenHtml.push("</h3>");
				screenHtml.push(screen.description);
				screenHtml.push("<table class='table'>");
				screenHtml.push(screen.inputsTableHeader);
				

				$(screen.options).each(function(key, option) {
					
					screenHtml.push("<tr class='optionDef' name='");
					screenHtml.push(option.name);
					screenHtml.push("' value='");
					screenHtml.push(option.value);
					screenHtml.push("' id='");
					screenHtml.push("option_" + screen.name + "_" + option.name + "_" + option.value);
					screenHtml.push("'><td class='inputContainer'>");
					
					if (option.type == 'blueArrow') {
						screenHtml.push("<i class='glyphicon glyphicon-circle-arrow-right optionSelector' name='");
						screenHtml.push(option.name);
						screenHtml.push("' value='");
						screenHtml.push(option.value);
						screenHtml.push("'></i>")
					}
					else {
						screenHtml.push("<input class='optionSelector' type='");
						screenHtml.push(option.type);
						screenHtml.push("' name='");
						screenHtml.push(option.name);
						screenHtml.push("' value='");
						screenHtml.push(option.value);
						screenHtml.push("'/>");
					}
					screenHtml.push("</td><td><h6>");
					screenHtml.push(option.title);
					screenHtml.push("</h6><div class='option-description-short'>");
					screenHtml.push(option.description);
					screenHtml.push("</div><div class='option-description-long'>");
					screenHtml.push(option.descriptionLong);
					screenHtml.push("</div></td>");
					screenHtml.push(option.additionalColumns);
					screenHtml.push("</tr>");
				});
				screenHtml.push("</table>");
				screenHtml.push(screen.inputsFooter);
				screenHtml.push("</div>");
				self.container.append(screenHtml.join(''));
			});
			
			self.showScreen(self.definition.defaultScreen);
			self.container.append("<div class='row'><div class='col-md-4'><button class='btn wizardNavigateBack'>Back</button></div>" + 
					"<div class='col-md-4'><button class='btn btn-primary wizardRunTheModel'>Run the model</button></div></div>");
			
		}
		this.showScreen = function(screenName, dontPushBack) {
			for (var i in self.definition.screens) {
				var screen = self.definition.screens[i];
				if (screen.name == screenName) {
					// hide visible screens
					self.container.find('.wizardScreen').hide();
					console.log('showing screen: ', screenName);
					$('#screen_' + screenName).show();
					if (dontPushBack) {
						// 
					}
					else {
						self.screensStack.push(screenName);
					}
					// show hide options
					$(screen.options).each(function(key, option) {
						// check if option is to be displayed
						var hideOption = true;
						if (! ("dontShowIf" in option)) {
							// option should be shown as there are no additional conditions for hiding it
							hideOption = false;
						}
						else {
							for (var key in option.dontShowIf) {
								if (! (key in self.values)) {
									// constrained variable doesn't exist in values set - show the option
									hideOption = false;
								}
								else if (self.values[key] != option.dontShowIf[key]) {
									// constraint on input value exists but value if different - show the option
									hideOption = false;
								}
							}
						}
						var optionElem = jQuery("#option_" + screen.name + "_" + option.name + "_" + option.value);
						if (hideOption) {
							optionElem.hide();
						}
						else {
							optionElem.show();
						}
					});
					
				}
			}
			if (self.screensStack.length > 1) {
				self.container.find(".wizardNavigateBack").show();
			} else {
				self.container.find(".wizardNavigateBack").hide();
			}
		}
		
		this.navigateBack = function() {
			// remove all options defined in current screen
			console.log('navigate back', self.screensStack, self.screensStack.length);
			if (self.screensStack.length <= 1) return;
			
			var currentScreen = self.screensStack.pop();
			var previousScreen = self.screensStack[self.screensStack.length-1];
			for (var i in self.definition.screens) {
				var screen = self.definition.screens[i];
				if (screen.name == currentScreen || screen.name == previousScreen) {
					for (var k in screen.options) {
						if (screen.options[k].name in self.values) {
							console.log('deleting', self.options, screen.options[k].name);
							delete self.values[screen.options[k].name];
							self.container.find('input[name="' + screen.options[k].name + '"]').removeAttr('checked');
							self.container.find(".wizardRunTheModel").hide();
						}
					}
				}
			}
			self.showScreen(previousScreen, true);
			
			
			
		}
		
		this.processTransition = function(transition) {
			console.log('processing transition', transition);
			if (transition.targetScreen) {
				self.showScreen(transition.targetScreen);
			}
			if (transition.showRunButton) {
				console.log('showRunButton');
				self.container.find('.wizardRunTheModel').show();
			}
		}

		this.container.on('click', '.option-description-short', function(event) {
			
			event.preventDefault();
			var shortDescriptionElem = $(this);
			var isExpanded = shortDescriptionElem.next().is(':visible');
			var hasContent = $.trim(shortDescriptionElem.next().text()).length > 0;
			self.container.find('.option-description-long:visible').slideUp();
			if (!isExpanded && hasContent) {
				shortDescriptionElem.next().slideDown();
			
				return false;
			}

		});
		
		this.container.on('click', '.optionDef', function(event, val1, val2, val3) {
			console.log('click on option def', event);
			var currentScreen = self.screensStack[self.screensStack.length-1];
			
			var input = $(event.currentTarget);
			console.log('option selector clicked', input.val(), input.attr('value'));
			//if (input.is(':checked')) {
				// add input to selected values input set
				self.values[input.attr('name')] = input.attr('value');
			/*}
			else {
				// remove input from selected values input set
				delete self.values[input.attr('name')];
				return;
			}*/
			var inputValsKey = [];
			for (var key in self.values) {
				inputValsKey.push(key + "=" + self.values[key]);
			}
			console.log(inputValsKey, currentScreen);
			inputValsKey.sort();
			
			var transitionKey = inputValsKey.join(',');
			// check if a transition should be made
			console.log(transitionKey);
			for (var i in self.definition.transitions) {
				var transition = self.definition.transitions[i];
				if (transition.fromScreen == currentScreen) {
					var transitionMatched = true;
					for (var k in transition.conditions) {
						var condition = transition.conditions[k];
						if (transitionKey.indexOf(condition) < 0) {
							transitionMatched = false;
							break;
						}
					}
					if (transitionMatched) {
						console.log('should execute transition', transition);
						self.processTransition(transition);
						return;
					}
				}
			}
			
		});
		
		this.container.on('click', '.wizardNavigateBack', function() {
			self.navigateBack();
			
		});
		
		this.container.on('click', '.wizardRunTheModel', function() {
			var inputValsKey = [];
			for (var key in self.values) {
				inputValsKey.push(key + "=" + self.values[key]);
			}
			inputValsKey.sort();
			
			var transitionKey = inputValsKey.join(',');
			// find mapping 
			var resultToReturn = false;
			for (var i = 0; i < self.definition.results.length; i++) {
				var result = self.definition.results[i];
				var resultFound = true;
				for (var key in self.values) {
					if (!(key in result.values)) {
						resultFound = false;
						break;
					} 
					if (result.values[key] != self.values[key]) {
						resultFound = false;
						break;
					}
				}
				if (resultFound) {
					resultToReturn = result;
					break;
					
				}
			}
			if (resultToReturn) {
				// remove all valueBinding inputs
				console.log("removing value bindings");
				
				self.container.find(".valueBinding").remove();
				console.log(resultToReturn.outputs);
				for (var key in resultToReturn.outputs) {
					// find input for given name
					var found = false;
					for (var i = 0; i < self.model.inputs.length; i++) {
						var input = self.model.inputs[i];
						if (input.metaData.internalName == key) {
							container.append("<input type='hidden' data-id='" + input.metaData.id + "' value='" + resultToReturn.outputs[key] + "' class='valueBinding' />");
							found = true; 
							break;
						}
					}
					if (!found) {
						console.error("Can't find model input for name: " + key);
					}
					
					
					
				}
				self.modelingWidget.runTheModel();
			}
			else {
				console.log("not found for values: ", self.values);
			}
			
			
			
		});
		
		this.renderDefinition(eval("(" + model.customInputsDefinition + ")"));
	};
	
	CustomInputsRenderer.prototype.renderView = function(container, model) {
		var that = this;
		var inputsContainer = jQuery("<div class='act_left act_left-list'></div>").appendTo(container);
		jQuery.each(model.inputs, function(idx, input) {
			var inputContainer = jQuery("<div></div>").appendTo(inputsContainer);
			that.modelingWidget.getInputRenderer(input).render(inputContainer, input, that.modelingWidget, idx);
		});
	};
	CustomInputsRenderer.prototype.render = function(container, scenario) {
		if (scenario.modelId != this.modelId) {
			XCoLab.modeling.BaseXCoLabModelingRenderer.prototype.render.apply(this, [container, scenario]);
		}
	};
	
	XCoLab.modeling.inputsRenderers.push(function (modelingWidget) {
		return new CustomInputsRenderer(modelingWidget);
	});
}());