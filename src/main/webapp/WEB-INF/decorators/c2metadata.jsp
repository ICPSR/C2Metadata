<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
<head>

	<title><decorator:title default="Continuous Metadata Capture Project" /></title>

	<%-- begin code.mkup --%>

	<%-- The following 3 meta tags *must* come first in the head; any other head content must come *after* these tags --%>
	<meta charset="UTF-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<meta name="viewport" content="width=device-width, initial-scale=1" />

	<link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet" />
	<link href="<c:url value="/resources/css/bootstrap-theme.min.css" />" rel="stylesheet" />
	<link href="<c:url value="/resources/css/screen-css.jsp" />" type="text/css" rel="stylesheet" media="screen" />
	<link href="<c:url value="/resources/css/splash-css.jsp" />" type="text/css" rel="stylesheet" media="screen" />
	<link href="<c:url value="/resources/css/print-css.jsp" />" type="text/css" rel="stylesheet" media="print" />
	<script src="<c:url value="/resources/scripts/jquery-ui-1.12.0/external/jquery/jquery.js" />"></script>	
	<link href="<c:url value="/resources/scripts/jquery-ui-1.12.0/jquery-ui.min.css" />" type="text/css" rel="stylesheet" />
	<link href="<c:url value="/resources/scripts/jquery-ui-1.12.0/jquery-ui.theme.min.css" />" type="text/css" rel="stylesheet" />
	<script src="<c:url value="/resources/scripts/jquery-ui-1.12.0/jquery-ui.min.js" />"></script>
	<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
	<link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i" rel="stylesheet">

	<decorator:head />
	
	<%-- end code.mkup --%>

</head>
<body>

<%-- begin header.mkup --%>

<header id="navbar" role="banner" class="navbar navbar-static-top navbar-default">
	<div class="container">
		<div class="navbar-header">
			<a class="logo navbar-btn pull-left" href="/c2metadata/index" title="Home">
				<img src="<c:url value="/resources/images/c2-4.png" />" alt="Home" />
			</a>
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar-collapse">
          		<span class="sr-only">Toggle navigation</span>
          		<span class="icon-bar"></span>
          		<span class="icon-bar"></span>
          		<span class="icon-bar"></span>
        		</button>
		</div>
		<div class="navbar-collapse collapse" id="navbar-collapse">
        		<nav role="navigation">
				<ul class="nav navbar-nav navbar-right">
					<c:choose>
						<c:when test="${AUTH_USER!=null}">
							<li class="dropdown">
								<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
								aria-expanded="false">${AUTH_USER.email}<span class="caret"></span></a>
								<ul class="dropdown-menu">
									<c:url var="editAcctUrl" value="/oauth/edit">
									</c:url>
									<li><a href="${editAcctUrl}" target="_blank">View/Edit Profile</a></li>
									<c:url var="logoutUrl" value="/logout">
										<c:param name="path" value="ICPSR" />
									</c:url>
									<li><a href="${logoutUrl}">Logout</a></li>
								</ul>
							</li>
						</c:when>
						<c:otherwise>
							<c:url var="loginUrl" value="/login">
								<c:param name="request_uri" value="${pageContext.request.requestURL}?${pageContext.request.queryString}" />
							</c:url>
			       			<li><a href="${loginUrl}" id="login">Sign In</a></li>
						</c:otherwise>
					</c:choose>
					
				</ul> 
				<c:if test="${AUTH_USER.securityCache.isActivitiGroupMember('c2m-admins')}">
				<ul class="nav navbar-nav navbar-right">
					<li class="dropdown">
					
					<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
								aria-expanded="false"> Admin <span class="caret"></span></a>
									<ul class="dropdown-menu">
						<li><a href="${pageContext.request.contextPath}/admin/index">Admin Jobs</a></li>
						<li><a href="${pageContext.request.contextPath}/admin/adminupload">Admin Upload</a></li>
						</ul>
						</li>
					</ul> </c:if>  
					   			
			</nav>
      </div>
	</div>
</header>
<section id="skipto">

<div class="container-fluid" id="mainContent">

<%-- end header.mkup --%>

	<c:if test="${AUTH_USER!=null}">
		<div class="alert alert-danger">Please note that all files submitted on this site are retained for testing software performance. 
		Do not submit files that contain personal identifiers and/or are sensitive in nature.</div>
	</c:if>

	<decorator:body />
	
	
	

<%-- begin footer.mkup --%>

</div>

</section>

<footer class="container-fluid">
      
      <div class="footer container">
      
      <div class="region region-footer">
    		

<h2 class="block-title">Partners</h2>
    
  <p class="footer-logos">
  <a href="https://www.umich.edu/"><img alt="University of Michigan" height="80" src="<c:url value="/resources/images/um-logo.jpg" />" width="83" /></a>
  <a href="http://icpsr.umich.edu"><img alt="ICPSR" src="<c:url value="/resources/images/icpsr-logo.png" />" width="190" /></a>
  <a href="http://www.electionstudies.org/"><img alt="ICPSR" src="<c:url value="/resources/images/anes-logo.jpg" />" width="150" /></a>
  <a href="http://norc.org"><img alt="NORC at the University of Chicago" src="<c:url value="/resources/images/NORC_75_Logo_1920.jpg" />" width="200" /></a><br>
  <a href="http://www.colectica.com/"><img alt="Colectica" height="48" src="<c:url value="/resources/images/coletica-text-large.png" />" width="200" /></a>
  <a href="http://www.nsd.uib.no/nsd/english/index.html"><img alt="Norwegian Center for Research Data" height="50" src="<c:url value="/resources/images/nsd-logo.png" />" width="96" /></a>
  <a href="https://www.mtna.us"><img alt="Metadata Technology North America" height="80" src="<c:url value="/resources/images/mtna_logo-300x168.png" />" width="143" /></a>
  <a href="https://www.nsf.gov/"><img alt="National Science Foundation" style="height:80px;width:83px;" src="<c:url value="/resources/images/nsf.png" />" /></a>
  </p>

 
  <p style="text-align:center;">C2Metadata is supported by Data Infrastructure Building Blocks (DIBBs) program of the National Science Foundation through grant NSF ACI-1640575.</p>

</div>

  </div>
  </footer>

<%-- end footer.mkup --%>

</body>
</html>
