<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin</title>
<link
	href="<c:url value="/resources/scripts/datatable/jquery.dataTables.min.css" />"
	type="text/css" rel="stylesheet" />


<jsp:include page="jsx-components.jsp" />

</head>
<body>
<article>
		<div class="row">
	
			<div class="col-sm-9 col-lg-10">
				<div id="JobsForm"></div>
			</div>
		</div>
		<script>
		var appUrl = "${appUrl}";

			ReactDOM.render(React.createElement(admin, {
				appUrl: "${appUrl}"
			}), document.querySelector('#JobsForm'))
		</script>
	</article>
</body>
</html>