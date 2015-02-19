<jsp:root xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:fn="http://java.sun.com/jsp/jstl/functions"
	xmlns:fmt="http://java.sun.com/jsp/jstl/fmt"
	xmlns:spring="http://www.springframework.org/tags"
	xmlns:form="http://www.springframework.org/tags/form"
	xmlns:collab="http://climatecolab.org/tags/collab_1.0"
	xmlns:portlet="http://java.sun.com/portlet_2_0" version="2.0">
	<jsp:directive.include file="../init.jspx" />
	<jsp:directive.include file="./header.jspx"/>

	<portlet:actionURL var="updateContestOntologyURL">
		<portlet:param name="action_forwardToPage" value="ontologyTab" />
		<portlet:param name="action_errorForwardToPage" value="ontologyTab" />
		<portlet:param name="tab" value="ONTOLOGY" />
		<portlet:param name="contestId" value="${contestWrapper.contestPK }" />
		<portlet:param name="action" value="updateContestOntology" />
	</portlet:actionURL>


	<div class="cmsDetailsBox">

		<p>Each contest on the Climate CoLab has a place inside of our climate change taxonomy, which we use to organize and help people easily locate contests.  Please select where your contest falls in each of the four dimensions below.  If you have questions about where your contest best fits, please submit a comment below for the Climate CoLab team.</p>

		<form action="${updateContestOntologyURL }" id="editForm" method="post">
			<input type="hidden" name="selectedOntologyTerms" id="selectedOntologyTerms"/>
		</form>

		<h2>Contest ontology</h2>
		<div class="contest-outline-left">
			<div class="expand">
				<a href="#" class="showall" style="display: inline;">Expand All</a>
				<a href="#" class="hideall" style="display: none;">Collapse All</a>
			</div>
			<ul class="level0 accordion">
				<c:forEach var="ontologySpace" items="${ontologySpaces }" varStatus="status">
					<li class="contest-section${status.index+1 }" data-space-id="${ontologySpace.id }">
						<a href="#${ontologySpace.id }" class="section-head trigger" data-space-id="${ontologySpace.id }" id='space_trigger_${ontologySpace.id }' >
							<span class="active-icon"><!--  --></span>
							<h2>${ontologySpace.name } <span>${ontologySpace.description}</span></h2>
							<span class="section-icon"><!--  --></span>
						</a>
						<collab:outline_ontologyTermWithChildren terms="${ontologySpace.rootTerms }" section="${status.index+1 }" level="1" />
					</li>
				</c:forEach>
			</ul>
		</div>
	</div>

	<script>
		var ontologyTerms = {};
		var ontologySpaces = {};
		var selectedOntologyTerms = [];

		<c:forEach var="ontologySpace" items="${ontologySpaces}">
			ontologySpaces[${ontologySpace.id}] = {id: ${ontologySpace.id}, name: "${ontologySpace.name}", order: ${ontologySpace.order}, contests: [], otherContests: [], isSpace: true};
		</c:forEach>

		<c:forEach var="term" items="${ontologyTerms}" varStatus='termStatus'>
			ontologyTerms[${term.id}] = {id: ${term.id}, parentId: ${term.parentId}, name: "${term.name}", contests: [], otherContests: [], space: ontologySpaces[${term.ontologySpaceId}], order: ${term.order != 0 ? term.order * 1000000 : termStatus.index}  };
		</c:forEach>

		for (var termId in ontologyTerms) {
			if (ontologyTerms[termId].parentId > 0) {
				ontologyTerms[termId].parent = ontologyTerms[ontologyTerms[termId].parentId ];
			}
			else {
				ontologyTerms[termId].parent = null;
			}
		}

		<c:forEach var="contestOntologyTerm" items="${contestOntologyTerms}">
			selectedOntologyTerms.push(${contestOntologyTerm});
		</c:forEach>

	</script>

	<script>
		<![CDATA[

		function addOntologyTermToSelection(term) {
			var indexOfTermId = selectedOntologyTerms.indexOf(parseInt(term));
			if (indexOfTermId > 0) return;
			selectedOntologyTerms.push(parseInt(term));
			console.log("add -> selectedOntologyTerms", selectedOntologyTerms);
			jQuery("#selectedOntologyTerms").val(selectedOntologyTerms.toString());
		}

		function removeOntologyTermFromSelection(term) {
			var indexOfTermId = selectedOntologyTerms.indexOf(parseInt(term));
			//console.log("remove -> indexOfTermId", indexOfTermId);
			if (indexOfTermId < 0) return;
			selectedOntologyTerms.splice(indexOfTermId, 1);
			console.log("remove -> selectedOntologyTerms", selectedOntologyTerms);
			jQuery("#selectedOntologyTerms").val(selectedOntologyTerms.toString());
		}


		jQuery(function () {

			// show first space
			jQuery(".level0 .section-head.trigger").eq(0).click();

			for (var termId in selectedOntologyTerms) {
				var ontologyTermIdSelector = "#ontologyTerm_" + selectedOntologyTerms[termId];
				//console.log("ontologyTermIdSelector", jQuery(ontologyTermIdSelector + " a:first input"));
				jQuery(ontologyTermIdSelector).click();
				jQuery(ontologyTermIdSelector + " a input").prop('checked', true);
			}

			jQuery(".contest-outline-left").on('click', 'a', function (event) {
				var that = this;
				var self = jQuery(this);
				var container = self.parent();

				if (event.target.type == "checkbox") {
					var termId = self.closest("a").attr('data-term-id');

					if (!jQuery(event.target).prop("checked")) {

						// remove itself from selection
						removeOntologyTermFromSelection(termId);

						// remove children from selection
						container.find("> ul > li > a input").prop('checked', false);
						container.find("> ul > li > a").map(function () {
							var termId = jQuery(this).attr("data-term-id");
							removeOntologyTermFromSelection(termId);
						})

						// remove parent selection from selection
						container.parent().closest("li").find("a:first input:first").prop('checked', false);
						var parentTermId = container.parent().closest("li").find("a:first")[0];
						parentTermId = jQuery(parentTermId).attr("data-term-id");
						console.log("parentTermId", parentTermId);
						if (parentTermId) {
							removeOntologyTermFromSelection(parentTermId);
						}

						// add remaining siblings to selection
						var parentContainer = container.parent();
						parentContainer.find("> li > a").map(function () {
							if (this.getElementsByTagName("input")[0].checked) {
								var termId = jQuery(this).attr("data-term-id");
								addOntologyTermToSelection(termId);
							}
						})
					} else {
						addOntologyTermToSelection(termId);
						//self.find("a input").prop('checked', true);
						container.find("> ul > li > a input").prop('checked', true);

						//jQuery(ontologyTermIdSelector + " a input").prop('checked', true);

						container.find("> ul > li > a").map(function () {
						//self.find("a").map(function () {
							var termId = jQuery(this).attr("data-term-id");
							removeOntologyTermFromSelection(termId);
						})

					}
					event.stopPropagation();
				} else {
					event.preventDefault();
				}

				if (self.hasClass('showall') || self.hasClass('hideall')) {
					return;
				}

				container.addClass('currentlyClicked');

				jQuery('.contest-outline-left li.active').not('.currentlyClicked').removeClass('active');

				/*
				jQuery('.contest-outline-left a.open').each(function () {
					if (that == this) return;

					var parent = jQuery(this).parent();
					if (!parent.hasClass('currentlyClicked') && parent.find(".currentlyClicked").length == 0) {
						// this is different branch than the one currently clicked, close it
						//parent.find(".open").removeClass("open");
						//parent.find("> ul").slideUp();
					}
				});*/

				container.removeClass('currentlyClicked').toggleClass('active');

				if (!container.hasClass('active')) {
					container.find("> ul").slideUp();
					self.removeClass('open');
				}
				else {
					self.addClass('open');
					container.find("> ul").slideDown();

					if (container.find("> ul > li").length == 1) {
						container.find("> ul > li > a").not(".open").addClass('open');
						container.find("> ul > li > ul").slideDown();
					}
				}

			});

			jQuery(".expand a").click(function () {
				jQuery(".expand a").toggle();
				if (jQuery(this).hasClass('showall')) {
					jQuery(".contest-outline-left a").addClass('open');
					jQuery(".contest-outline-left .level0 ul").slideDown();
				}
				else {
					jQuery(".contest-outline-left a").removeClass('open');
					jQuery(".contest-outline-left .level0 ul").slideUp();
				}
			});

		});

		]]>
	</script>

	<jsp:directive.include file="./footer.jspx"/>


</jsp:root>