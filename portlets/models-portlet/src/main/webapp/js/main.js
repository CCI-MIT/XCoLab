/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

function initEditForms() {
	/* Inputs ordering */
	if (!jQuery("#inputsOrder").length) {
    jQuery(".subInputsOrder").each(function() {
    	var ul = jQuery(this);
    	var itemsContainer = ul.next();
    	ul.html(itemsContainer.html());
    	itemsContainer.remove();
    });

    var inputsForOrdering = jQuery(".inputOrderDef").html();
    jQuery(".inputOrderDef").html('<ul id="inputsOrder" class="inputsOrder">' + inputsForOrdering + "</ul>");
    
    jQuery("#inputsOrder, .subInputsOrder").sortable({stop: function() {
    
    	var counter = 0; 
    	jQuery(".singleInputOrderDef input").each(function() {
    		this.value = counter++;
    	});
    }});
	}
	
	/* Inputs grouping */
	if (! jQuery(".availableGroupItemsa").length) {
		
		jQuery(".availableItemsCell .listItems").each(function() {
			//var container = jQuery(this);
			//ul.html("");
			//container.html("<ul class='availableGroupItems ui-widget-content ui-corner-all'>" + container.html() + "</ul>");
			
			//log.debug("creating available items ");
		});
		
		jQuery(".inputGroup .listItems").each(function() {
			//var container = jQuery(this);
			//var parentId = container.attr("class").replace(/[^\d]*/, "");
			//container.append("<ul class='groupedInputs ui-widget-header' title='" + parentId + "'>" + container.html() + "</ul>");

			//log.debug("creating grouped items ");
			
		});
		
		//, .inputGroup
		jQuery(".individualInput").each(function(){
			var parentId = 0;
			var input = jQuery(this);
			if (input.parent().hasClass("groupedInputs")) {
				parentId = input.parent().attr("title");
			}
			input.find("input").val(parentId);
		});
		
		jQuery(".individualInput").draggable({helper: "original"});
		jQuery(".groupedInputs, .availableGroupItems").droppable({
			activeClass: 'ui-state-highlight',
			drop: function(event, ui) {
				jQuery(this).find(".individualInputPlaceholder").remove();
				ui.draggable.css("top", null);
				ui.draggable.css("left", null);
				ui.draggable.appendTo(this);

				// input has been added to concrete group, set its input to group id
				var parentId = -1;
				try {
				if (ui.draggable.parent().hasClass("groupedInputs")) {
					parentId = ui.draggable.parent().attr("title");
				}
				ui.draggable.find("input").val(parentId);
				}
				catch (e) {
					log.error(e);
				}
				jQuery(this).append("<li class='individualInputPlaceholder'></li>");
				//ui.draggable.draggable();
				
		}});

		
	}
	
	if (true) {

	    //var inputsForOrdering = jQuery(".inputOrderDef").html();
	    //jQuery(".inputOrderDef").html('<ul id="inputsOrder" class="inputsOrder">' + inputsForOrdering + "</ul>");
	    function updateItemsOrder() {
	    	var counter = 0; 
	    	jQuery(".orderList li input").each(function() {
	    		this.value = counter++;
	    	});
	    }
	    	
	    jQuery(".orderList, .orderSubList").sortable({stop: function() {
	    	updateItemsOrder();
	    }});
	    
	    updateItemsOrder();
	}
    
}


function initInputsConfig() {
	//$(".draggableItem").draggable();
	//$(".dropTarget").droppable({accept: ".draggableItem"});
	
	function updateGroups() {
		$(".groupContainer").each(function() {
			var groupId = $(this).attr('data-id');
			console.log("updating group id", groupId);
			$(this).find(".parentGroupId").val(groupId);
		});
		$(".submitChangesButton").click();
	}
	
	function updateOrder() {
		var order = 1;
		$(".inputOrder").each(function() {
			$(this).val(order++);
		});
		$("submitChangesButton").click();
	}
	
	
	
	$(".dropTarget").each(function() {
		
		var dropTarget = $(this);
		dropTarget.sortable({stop: updateOrder, items: "> .draggableItem" });
		/*
		dropTarget.prepend("<div class='actionButtons'>" + 
				"<button class='toggleEdit'>Edit</button>" + 
				"<button class='addInput'>Add input</button>" +
				"</div><div class='clear'></div>");
		*/
		
		dropTarget.find(".addInput").off("click");
		dropTarget.find(".addInput").click(function() {
			$("#availableInputs").html("");
			
			$("#availableInputs").dialog({modal: true});
			var html = [];
			$(".individualInput").each(function() {
				html.push("<div class='draggableItem' data-id='" + this.id + "'>" + $(this).find(".inputName").text() + "</div>");
			});
			$("#availableInputs").html(html.join(""));
			$("#availableInputs .draggableItem").off("click");
			$("#availableInputs .draggableItem").click(function() {
				dropTarget.append($('#' + $(this).attr('data-id')));
				$("#availableInputs").dialog("close");
				updateGroups();
				updateOrder();
				
			});
			return false;
		});
		dropTarget.find(".toggleEdit").off("click");
		dropTarget.find(".toggleEdit").click(function() {
			if (dropTarget.find(".groupEditFormContainer").is(":visible")) {
				$(".groupEditFormContainer").hide();
			}
			else {
				$(".groupEditFormContainer").hide();
				dropTarget.find(".groupEditFormContainer").slideDown();
			}
			return false;
		});
		dropTarget.find(".addGroup").click(function() {
			if (dropTarget.find(".groupAddFormContainer").is(":visible")) {
				$(".groupAddFormContainer").hide();
			}
			else {
				$(".groupAddFormContainer").hide();
				dropTarget.find(".groupAddFormContainer").slideDown();
			}
			return false;
		});
	});	
	
	$(".addTabToggle").off("click").click(function() {
		$(".tabAddFormContainer").slideToggle();
		return false;
	});
}

function populateParentGroupId(target) {
	
	var editForm = $(target).parent();
	
	console.log(editForm, editForm.find(".groupTypeValue"), editForm.find(".groupParentGroupIdValue"));
	editForm.find(".groupTypeValue").val(editForm.find(".groupType").val());
	editForm.find(".groupParentGroupIdValue").val(editForm.find(".groupParentGroupId").val());
	
}
