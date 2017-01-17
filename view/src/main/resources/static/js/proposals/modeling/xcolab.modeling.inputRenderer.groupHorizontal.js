if (typeof(XCoLab) == 'undefined') 
	throw new Error("XCoLab isn't defined");
if (typeof(XCoLab.modeling) == 'undefined')
	throw new Error("XCoLab.modeling isn't defined");

(function() {
	/**
	 * Renderer for horizontal group
	 */
	function DefaultGroupHorizontalInputRenderer(modelingWidget) {
	}
	
	DefaultGroupHorizontalInputRenderer.prototype = new XCoLab.modeling.BaseXCoLabModelingItemRenderer();
	DefaultGroupHorizontalInputRenderer.prototype.containerHtml = "<div class='actInputDef'></div>";
	DefaultGroupHorizontalInputRenderer.prototype.containerHtmlEdit = "<table class='inputDefTable'></table>";

	DefaultGroupHorizontalInputRenderer.prototype.canRender = function(input) {
		return input.displayItemType == 'GROUP' && input.groupType == 'HORIZONTAL'; 
	};

	DefaultGroupHorizontalInputRenderer.prototype.renderEdit = function(container, input, modelingWidget, idx, parent) {
		var verticalGroupContainer = jQuery("<table></table>").appendTo(container);
		var groupRow = jQuery("<tr></tr>");
		verticalGroupContainer.append(groupRow);
		
		jQuery.each(input.inputs, function(idx, childInput) {
			var itemCell = jQuery("<td></td>");
			groupRow.append(itemCell);
			modelingWidget.getInputRenderer(childInput).render(itemCell, childInput, modelingWidget, idx, input);
		});
	};
	
	DefaultGroupHorizontalInputRenderer.prototype.renderView = function(container, input, modeling, idx, parent) {
		jQuery.each(input.inputs, function(idx, childInput) {
			modeling.getInputRenderer(childInput).render(container, childInput, modeling, idx, input);
			container.append(" ");
		});
	};
	

	XCoLab.modeling.inputItemRenderers.push(new DefaultGroupHorizontalInputRenderer());
}());