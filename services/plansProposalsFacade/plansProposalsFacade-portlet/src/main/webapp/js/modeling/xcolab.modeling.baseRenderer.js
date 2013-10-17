if (typeof(XCoLab) == 'undefined') 
	throw new "XCoLab isn't defined";
if (typeof(XCoLab.modeling) == 'undefined')
	throw new "XCoLab.modeling isn't defined"; 

(function() {
	XCoLab.modeling.BaseXCoLabModelingRenderer = {
			render: function(container) {
				var renderFunc = this.renderView;
				if (this.modelingWidget.inEditMode) {
					renderFunc = this.renderEdit;
				}
				var elementContainer = jQuery(this.containerHtml).appendTo(container);
				
				console.log(arguments);
				var newArguments = [elementContainer].concat(Array.prototype.slice.call(arguments, 1));
				console.log(newArguments);
				renderFunc.apply(this, newArguments);
			}
	}
	
})();