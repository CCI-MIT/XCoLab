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
		
		jQuery(modelingWidget).on('scenarioRendered', function(event) {
			console.log("updating wizard outputs", that);
			that.updateSelectedOptionsInfo();
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
			var screensContainer = $("<div class='wizardScreensContainer'><div class='wizardScreensCarouselContainer'></div></div>");
			$(definition.screens).each(function(key, screen) {
				var screenHtml = [];
				
				screenHtml.push("<div class='wizardScreen' id='screen_" + screen.name + "'>");
				screenHtml.push("<div class='wizardControls'>" +
						"<button class='btn wizardNavigateBack'>Back</button> " +
						"<button class='btn btn-primary wizardNavigateNext'>Next</button> " + 
						"<button class='btn btn-primary btn-success wizardRunTheModel'>Run the model</button>" +
						"</div>");
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
				screenHtml.push("<div class='wizardControls'>" +
						"<button class='btn wizardNavigateBack'>Back</button> " +
						"<button class='btn btn-primary wizardNavigateNext'>Next</button> " + 
						"<button class='btn btn-primary btn-success wizardRunTheModel'>Run the model</button>" +
						"</div>");
				screenHtml.push("</div>");
				screensContainer.find('.wizardScreensCarouselContainer').append(screenHtml.join(''));
			});

			self.container.append(screensContainer);
			self.screensCarousel = screensContainer.jcarousel();
			self.showScreen(self.definition.defaultScreen);
			
			// initialize tooltips
			self.container.find(".wizardTooltipPlaceholder").each(function (key, elem, o3) {
				var elem = $(elem);
				for (var i = 0; i < self.definition.tooltips.length; i++) {
					var tooltipDef = self.definition.tooltips[i];
					if (tooltipDef.id == elem.data('tooltip-id')) {
						elem.attr('data-toggle', 'tooltip');
						elem.attr('data-placement', 'right');
						elem.attr('title', tooltipDef.text);
						elem.tooltip({html: true});
					}
				}
				
			});
			
		}
		this.showScreen = function(screenName, dontPushBack) {
			for (var i in self.definition.screens) {
				var screen = self.definition.screens[i];
				if (screen.name == screenName) {
					var screenElem = $('#screen_' + screenName);
					self.container.find(".wizardScreensContainer").jcarousel('scroll', screenElem);
					if (dontPushBack) {
						// 
					}
					else {
						self.screensStack.push(screenName);
					}
					
					screenElem.find(".selected").removeClass('selected');
					
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
								if (! (key in self.values) || self.values[key].value != option.dontShowIf[key]) {
									// constrained variable doesn't exist in values set - show the option
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
					
					this.screensCarousel.get(0).scrollIntoView();
					
					
				}
			}
			this.updateCurrentScreenButtons();
			this.updateSelectedOptionsInfo();
		}
		
		this.navigateBack = function() {
			// remove all options defined in current screen
			if (self.screensStack.length <= 1) return;
			
			var currentScreen = self.screensStack.pop();
			var previousScreen = self.screensStack[self.screensStack.length-1];
			for (var i in self.definition.screens) {
				var screen = self.definition.screens[i];
				if (screen.name == currentScreen || screen.name == previousScreen) {
					for (var k in screen.options) {
						if (screen.options[k].name in self.values) {
							delete self.values[screen.options[k].name];
							self.container.find('input[name="' + screen.options[k].name + '"]').removeAttr('checked').removeClass('selected');
							self.container.find(".wizardRunTheModel").hide();
						}
					}
				}
			}
			self.showScreen(previousScreen, true);
			
			
			
		}
		this.findCurrentScreenTransition = function() {
			var currentScreen = self.screensStack[self.screensStack.length-1];
			var inputValsKey = [];
			for (var key in self.values) {
				inputValsKey.push(key + "=" + self.values[key].value);
			}
			console.log(inputValsKey, currentScreen);
			inputValsKey.sort();
			
			var transitionKey = inputValsKey.join(',');
			// check if a transition should be made
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
						return transition;
					}
				}
			}
			console.log("no transition can be found", currentScreen, self.values);
		}
		this.navigateNext = function() {
			var transition = this.findCurrentScreenTransition();
			if (transition) {
				this.processTransition(transition);
			}
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
		
		this.updateCurrentScreenButtons = function() {
			var currentScreen = self.screensStack[self.screensStack.length-1];
			var transition = this.findCurrentScreenTransition();
			var screenElem = $("#screen_" + currentScreen);
			

			if (self.screensStack.length > 1) {
				screenElem.find(".wizardNavigateBack").show();
			} else {
				screenElem.find(".wizardNavigateBack").hide();
			}
			
			var showRun = false, showNext = false;
			if (transition) {
				if (transition.showRunButton && screenElem.find('.selected').length > 0) {
					screenElem.find('.wizardRunTheModel').show();
					showRun = true;
				}
			}
			if (!showRun) {
				screenElem.find('.wizardRunTheModel').hide();
				if (screenElem.find('.selected').length > 0) {
					screenElem.find(".wizardNavigateNext").show();
					showNext = true;
				}
			}
			if (!showNext) {
				screenElem.find(".wizardNavigateNext").hide();
			}
			console.log("showing current screen buttons", showNext, showRun);
		}
		
		this.updateWizardOutputsValues = function() {
			var resultToReturn = false;
			for (var i = 0; i < self.definition.results.length; i++) {
				var result = self.definition.results[i];
				var resultFound = true;
				for (var key in self.values) {
					if (!(key in result.values)) {
						resultFound = false;
						break;
					} 
					if (result.values[key] != self.values[key].value) {
						resultFound = false;
						break;
					}
				}
				if (resultFound) {
					resultToReturn = result;
					break;
					
				}
			}
			var outputs = self.definition.defaultOutputs;
			if (resultToReturn) {
				outputs = resultToReturn.outputs;
				
			}
			// remove all valueBinding inputs to update it with new ones
				
			self.container.find(".valueBinding").remove();
			for (var key in outputs) {
				// find model input for given name
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
			
			
		}
		
		this.updateSelectedOptionsInfo = function() {
			var selectedOptionsHtml = [];
			selectedOptionsHtml.push("<ul class='wizardSelectedOptionsInfo'>");
			
			for (var key in self.values) {
				console.log("Update selected options info....: ", self.values, key);
				selectedOptionsHtml.push("<li><strong>");
				selectedOptionsHtml.push(self.values[key].screen);
				selectedOptionsHtml.push("</strong>: ");
				selectedOptionsHtml.push(self.values[key].name);
				selectedOptionsHtml.push("</li>");
			}
			
			selectedOptionsHtml.push("</ul>");
			
			$(".act-edit_right .wizardSelectedOptionsInfo").remove();
			$(".act-edit_right").append(selectedOptionsHtml.join(''));
		};

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
			self.updateWizardOutputsValues();
			var currentScreen = self.screensStack[self.screensStack.length-1];
			console.log('click on option def', event);
			
			var input = $(event.currentTarget);
			input.siblings().removeClass('selected');
			input.addClass('selected');
			self.values[input.attr('name')] = {
					value: input.attr('value'), 
					screen: input.parents(".wizardScreen").find("h3").text(), 
					name: input.find("h6").text()
			};
			self.updateCurrentScreenButtons();
			self.updateSelectedOptionsInfo();
		});
		

		this.container.on('click', '.wizardNavigateNext', function() {
			self.navigateNext();
			
		});
		
		this.container.on('click', '.wizardNavigateBack', function() {
			self.navigateBack();
			
		});
		
		this.container.on('click', '.wizardRunTheModel', function() {
			self.updateWizardOutputsValues();
			self.modelingWidget.runTheModel();
		});
		
		this.renderDefinition(eval("(" + model.customInputsDefinition + ")"));

		self.updateWizardOutputsValues();
		self.updateSelectedOptionsInfo();
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