<jsp:root xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:jsp="http://java.sun.com/JSP/Page"
	version="2.0">

	<jsp:directive.include file="./init.jspx" />

	<div id="content">
		<jsp:directive.include file="./contestsIndex/header.jspx" />
		<h1>Mam outline!</h1>

		<c:choose>
			<c:when test="${viewType == 'GRID' }">
				<jsp:directive.include file="./contestsIndex/grid.jspx" />		
			</c:when>
			<c:otherwise>
				<jsp:directive.include file="./contestsIndex/list.jspx" />
			</c:otherwise>
		</c:choose>
		
	</div>
	<script>
		// set contest view type, see https://issues.liferay.com/browse/LPS-25733 
		setContestsViewTypeCookie('${viewType}');
	</script>
</jsp:root>