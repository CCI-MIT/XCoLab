if (typeof(XCoLab) == 'undefined') 
	throw new "XCoLab isn't defined";
if (typeof(XCoLab.modeling) == 'undefined')
	throw new "XCoLab.modeling isn't defined"; 

(function() {
	function CustomInputsRenderer(modelingWidget) {
		this.modelingWidget = modelingWidget;		
		this.modelId = -1;
		var that = this;
		
		jQuery(modelingWidget).on('scenarioRendered', function(event) {
			that.updateSelectedOptionsInfo(true);
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
		this.container = container;
		this.definition = {};
		this.values = {};
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
						"<button class='btn btn-primary btn-success wizardRunTheModel'>View model run</button>" +
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
						screenHtml.push("'></i>");
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
					if (option.descriptionLong && $.trim(option.descriptionLong).length > 0) {
						screenHtml.push(" <a href='javascript:;' class='see-more'>see more</a>");
					}
					screenHtml.push("</div><div class='option-description-long'>");
					screenHtml.push(option.descriptionLong);
					if (option.descriptionLong && $.trim(option.descriptionLong).length > 0) {
						screenHtml.push(" <a href='javascript:;' class='see-less'>see less</a>");
					}
					screenHtml.push("</div></td>");
					screenHtml.push(option.additionalColumns);
					screenHtml.push("</tr>");
				});
				screenHtml.push("</table>");
				screenHtml.push(screen.inputsFooter);
				screenHtml.push("<div class='wizardControls'>" +
						"<button class='btn wizardNavigateBack'>Back</button> " +
						"<button class='btn btn-primary wizardNavigateNext'>Next</button> " + 
						"<button class='btn btn-primary btn-success wizardRunTheModel'>View model run</button>" +
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
					
					/*screenElem.find(".selected").removeClass('selected');*/
					
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
				if (screen.name == currentScreen /*|| screen.name == previousScreen*/) {
					for (var k in screen.options) {
						if (screen.options[k].name in self.values) {
							delete self.values[screen.options[k].name];
							self.container.find('input[name="' + screen.options[k].name + '"]').removeAttr('checked').removeClass('selected');
							$("#option_" + screen.name + "_" + screen.options[k].name + "_" + screen.options[k].value).removeClass('selected');
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
			window.location.hash = escape(JSON.stringify({screens: self.screensStack, values: self.values}));
		}
		
		this.processTransition = function(transition) {
			if (transition.targetScreen) {
				self.showScreen(transition.targetScreen);
			}
			if (transition.showRunButton) {
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
		}
		
		this.findCurrentResult = function() {
			var defaultResult = null;
			for (var i = 0; i < self.definition.results.length; i++) {
				var result = self.definition.results[i];
				if (result.isDefault) {
					defaultResult = result;
				}
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
					return result;
				}
			}
			return defaultResult;
		}
		
		this.updateWizardOutputsValues = function() {
			var outputs = self.findCurrentResult().outputs;
			
			// remove all valueBinding inputs to update it with new ones
				
			self.container.find(".valueBinding").remove();
			for (var key in outputs) {
				// find model input for given name
				var found = false;
				for (var i = 0; i < self.model.inputs.length; i++) {
					var input = self.model.inputs[i];
					if (input.metaData.internalName == key) {
						container.append("<input type='hidden' data-id='" + input.metaData.id + "' value='" + outputs[key] + "' class='valueBinding' />");
						found = true; 
						break;
					}
				}
				if (!found) {
					console.error("Can't find model input for name: " + key);
				}
			}
			
			
		}
		
		function findScreenAndOptionForNameValue(name, value) {
			for (var i = 0; i < self.definition.screens.length; i++) {
				var screen = self.definition.screens[i];
				for (var k = 0; k < screen.options.length; k++) {
					var option = screen.options[k];
					if (option.name == name && option.value == value) return [screen, option]; 
				}
			}
			return null;
		}
		this.updateSelectedOptionsInfo = function(wasModelRun) {
			var selectedOptionsHtml = [];
			selectedOptionsHtml.push("<ul class='wizardSelectedOptionsInfo'>");
			
			if (wasModelRun) {
				// get result and render its options
				var result = self.findCurrentResult();
				if (result != null) {
					for (var key in result.values) {
						var screenAndOption = findScreenAndOptionForNameValue(key, result.values[key]);
						if (screenAndOption != null) {
							selectedOptionsHtml.push("<li><strong>");
							selectedOptionsHtml.push(screenAndOption[0].title);
							selectedOptionsHtml.push("</strong>: ");
							selectedOptionsHtml.push(screenAndOption[1].title);
							selectedOptionsHtml.push("</li>");
						}
					}
				}
			}
			else {
				for (var key in self.values) {
					var screenAndOption = findScreenAndOptionForNameValue(key, self.values[key].value);
					if (screenAndOption != null) {
						selectedOptionsHtml.push("<li><strong>");
						selectedOptionsHtml.push(screenAndOption[0].title);
						selectedOptionsHtml.push("</strong>: ");
						selectedOptionsHtml.push(screenAndOption[1].title);
						selectedOptionsHtml.push("</li>");
					}
				}
			}
			
			selectedOptionsHtml.push("</ul>");
			
			$(".act-edit_right .wizardSelectedOptionsInfo").remove();
			$(".act-edit_right").append(selectedOptionsHtml.join(''));
		};

		this.container.on('click', '.option-description-short .see-more', function(event) {
			
			event.preventDefault();
			var shortDescriptionElem = $(this).parents('.option-description-short');
			var isExpanded = shortDescriptionElem.next().is(':visible');
			var hasContent = $.trim(shortDescriptionElem.next().text()).length > 0;
			self.container.find('.option-description-long:visible').slideUp();
			if (!isExpanded && hasContent) {
				shortDescriptionElem.next().slideDown();
			
			}
			return false;

		});
		
		this.container.on('click', '.option-description-long .see-less', function(event) {
			event.preventDefault();
			self.container.find('.option-description-long:visible').slideUp();
			return false;

		});
		
		this.container.on('click', '.optionDef', function(event, val1, val2, val3) {
			self.updateWizardOutputsValues();
			var currentScreen = self.screensStack[self.screensStack.length-1];
			
			var input = $(event.currentTarget);
			input.siblings().removeClass('selected');
			input.addClass('selected');
			self.values[input.attr('name')] = {
					value: input.attr('value'), 
					screen: input.parents(".wizardScreen").find("h3").text(), 
					screenName: input.parents(".wizardScreen").attr('id').substring('screen_'.length),
					name: input.find("h6").text()
			};

			window.location.hash = escape(JSON.stringify({screens: self.screensStack, values: self.values}));
			self.updateCurrentScreenButtons();
			self.updateSelectedOptionsInfo();
			return false;
		});
		

		this.container.on('click', '.wizardNavigateNext', function() {
			self.navigateNext();
			return false;
		});
		
		this.container.on('click', '.wizardNavigateBack', function() {
			self.navigateBack();
			return false;
		});
		
		this.container.on('click', '.wizardRunTheModel', function() {
			self.updateWizardOutputsValues();
			self.modelingWidget.runTheModel();
			return false;
		});
		
		this.renderDefinition(eval("(" + model.customInputsDefinition + ")"));
		
		function processHashChange() {
			var obj = {screens: [], values: {}};
			try {
				if (window.location.hash.length > 1) {
					obj = $.parseJSON(unescape(window.location.hash.substring(1)));
				}
			}
			catch (e) {
				console.error(e);
			}
			if (typeof(obj) != 'object') {
				obj = {};
			}
			if (!('screens' in obj) || obj.screens.length == 0) {
				obj.screens = [self.definition.defaultScreen];
			}
			if (!('values' in obj)) {
				obj.values = {};
			}
			
			// check if there is any value that should be deselected
			for (var key in self.values) {
				if (! (key in obj.values) || obj.values[key] != self.values[key]) {
					$("#option_" + self.values[key].screenName + "_" + key + "_" + self.values[key].value).removeClass('selected');
				}
			}
				
			// make sure that all values got selected
			for (var key in obj.values) {
				$("#option_" + obj.values[key].screenName + "_" + key + "_" + obj.values[key].value).addClass('selected');
			}

			console.log('setting screens', self.screensStack, obj.screens);
			self.screensStack = obj.screens;
			console.log('setting values', self.values, obj.values);
			self.values = obj.values;
			self.showScreen(self.screensStack[self.screensStack.length-1], true);
			
			
			self.updateWizardOutputsValues();
			self.updateSelectedOptionsInfo();
			self.updateCurrentScreenButtons();
		}

		$(".runSimulationButtonHighlight").html("<span>VIEW</span> model run");
		
		$(window).on('hashchange', function() {
			processHashChange();
			
		});
		
		processHashChange();
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