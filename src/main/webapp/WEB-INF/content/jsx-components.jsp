<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<spring:eval var="buildNumber"
	expression="@config.getValue('release.build.number')" />
<spring:eval var="isLocal" expression="@config.isLocal()" />
<spring:eval var="isProd" expression="@config.isProduction()" />

		<!-- when in dev -->
		<link href="<c:url value="/resources/scripts/jquery-ui/jquery-ui.min.css" />" rel="stylesheet" />
		<link href="<c:url value="/resources/scripts/bootstrap/css/bootstrap.min.css" />" rel="stylesheet" />
		<link href="<c:url value="/resources/scripts/bootstrap/css/bootstrap-theme.min.css" />" rel="stylesheet" />
		<link href="<c:url value="/resources/scripts/bootstrap3-editable/css/bootstrap-editable.css" />" rel="stylesheet" />
		
	<link
	href="<c:url value="/resources/scripts/datatable/dataTables.bootstrap.min.css" />"
	type="text/css" rel="stylesheet" />
<link
	href="<c:url value="/resources/scripts/datatable/buttons.dataTables.min.css" />"
	type="text/css" rel="stylesheet" />

		<link href="<c:url value="/resources/css/suggest.css" />" rel="stylesheet" />
		
		<link href='https://fonts.googleapis.com/css?family=Open+Sans:400,300,700,400italic' rel='stylesheet' type='text/css' />
		
		
		<%-- putting the js at the end of the document to speed load time will prevent react from working correctly --%>
		<script	src="<c:url value="/resources/scripts/jquery/jquery-1.11.2.min.js" />"></script>
		<script	src="<c:url value="/resources/scripts/bootstrap/js/bootstrap.min.js" />"></script>
		<script	src="<c:url value="/resources/scripts/jquery-ui/jquery-ui.min.js" />"></script>
		<script src="<c:url value="/resources/scripts/react-0.14.7/react.js" />"></script>
		<script	src="<c:url value="/resources/scripts/react-0.14.7/react-dom.js" />"></script>
		
		<%-- have not tested which of the following js can be moved to the bottom of the file --%>

		<script	src="<c:url value="/resources/scripts/bootstrap3-editable/js/bootstrap-editable.min.js" />"></script>
	<script
	src="<c:url value="/resources/scripts/datatable/jquery.dataTables.min.js" />"></script>
<script
	src="<c:url value="/resources/scripts/datatable/dataTables.buttons.min.js" />"></script>
<script
	src="<c:url value="/resources/scripts/datatable/dataTables.select.min.js" />"></script>
		<script	src="${pageContext.request.contextPath}/resources/scripts/app/admin.js"></script>
		<script	src="${pageContext.request.contextPath}/resources/scripts/app/tasksView.js"></script>
	
		
	
	