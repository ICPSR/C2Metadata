<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
<head>
<meta charset="UTF-8" />
<title>c2metadata</title>
<!-- 
		File input code pulled from https://www.abeautifulsite.net/whipping-file-inputs-into-shape-with-bootstrap-3
	-->

<style type="text/css">
.btn-file {
	position: relative;
	overflow: hidden;
}

.btn-file input[type=file] {
	position: absolute;
	top: 0;
	right: 0;
	min-width: 100%;
	min-height: 100%;
	font-size: 100px;
	text-align: right;
	filter: alpha(opacity = 0);
	opacity: 0;
	outline: none;
	background: white;
	cursor: inherit;
	display: block;
}

article form {
	margin-bottom: 10px;
}

.header {
	background-color: #31708f;
	color: white;
}
</style>

</head>
<body>
	<article>
	
	
			<c:if test="${status=='Error'}">
			
			<div class="alert alert-danger"><h4>${message}</h4></div>
			</c:if>
			
			<c:if test="${status==null}">
			<c:if test="${message != null}">
			<div class="alert alert-success"><h4>${message}</h4></div>
			</c:if>
			</c:if>
			
	<c:choose>
			<c:when test="${AUTH_USER==null}">
				<p>
					<strong>In order to test the software, you will need to
						sign in first.</strong>
				</p>
			</c:when>
			<c:otherwise>

				<form method="POST" action="<c:url value="/admin/uploaddocs" />"
					class="form" enctype="multipart/form-data">
					<div class="col-sm-6 col-md-6 col-lg-6">
						<div class="row">
							<div class="form-group">
								<label class="col-sm-3 control-label" for="functionsLibrary">Upload
									Function Library:</label>
								<div class="col-sm-9">
									<input type="file" class="form-control" name="functionsLibrary"
										id="functionsLibrary" required="required" accept=".json"/>
								</div>
							</div>
						</div>
						<br/>
						<div class="row">
							<div class="form-group">
								<label class="col-sm-3 control-label" for="expressionsLibrary">Upload
									Expressions Library:</label>
								<div class="col-sm-9">
									<input type="file" class="form-control" name="expressionsLibrary"
										id="expressionsLibrary"  required="required" accept=".json" />
								</div>
							</div>
						</div>


						<div class="row">

							<div class="col-md-2">
								<input type="submit" value="GO" class="btn btn-primary" />
							</div>
						</div>
					</div>
				</form>




			</c:otherwise>
		</c:choose>


	</article>
</body>
</html>