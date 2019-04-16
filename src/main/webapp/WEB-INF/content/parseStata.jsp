<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
<head>
	<title>Convert Stata Code to SDTL</title>
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
		    filter: alpha(opacity=0);
		    opacity: 0;
		    outline: none;
		    background: white;
		    cursor: inherit;
		    display: block;
		}
		
		article form {
			margin-bottom: 10px;
		}

	</style>
	<script>

		$(document).on('change', ':file', function() {
		    var input = $(this),
		        numFiles = input.get(0).files ? input.get(0).files.length : 1,
		        label = input.val().replace(/\\/g, '/').replace(/.*\//, '');
		    input.trigger('fileselect', [numFiles, label]);
		});

		$(document).ready( function() {
		      $(':file').on('fileselect', function(event, numFiles, label) {
		          var input = $(this).parents('.input-group').find(':text'),
		              log = numFiles > 1 ? numFiles + ' files selected' : label;
		          if( input.length ) {
		              input.val(log);
		          } else {
		              if( log ) alert(log);
		          }
		      });
		  });

	</script>
</head>
<body>
<article>

<h1>Convert Stata Code to SDTL</h1>

<form method="POST" action="<c:url value="/parse/stata" />" class="form" enctype="multipart/form-data">
	<div class="row" style="margin-bottom:10px;">
		<div class="col-md-4">
			<div class="input-group">
                <label class="input-group-btn">
                    <span class="btn btn-default">
                        Select DDI XML... <input type="file" name="ddiFile" id="ddiFile" accept=".xml,application/xml" style="display:none;" multiple="" />
                    </span>
                </label>
                <input type="text" class="form-control" readonly="" />
            </div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-4">
			<div class="input-group">
                <label class="input-group-btn">
                    <span class="btn btn-default">
                        Select Stata Code... <input type="file" name="codeFile" id="codeFile" accept=".txt,.do,text/plain" style="display:none;" multiple="" />
                    </span>
                </label>
                <input type="text" class="form-control" readonly="" />
            </div>
		</div>
		<div class="col-md-2">
			<input type="submit" value="Convert to SDTL" class="btn btn-primary" />
		</div>
	</div>
</form>

<jsp:include page="test-cases.jsp" />

</article>
</body>
</html>
