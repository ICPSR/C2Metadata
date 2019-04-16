<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
<head>
	<title>Build HTML Codebook from DDI XML</title>
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

<h1>Build HTML Codebook from DDI XML</h1>

<form method="post" action="<c:url value="/codebook/html" />" class="form" enctype="multipart/form-data">
	<div class="row">
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
		<div class="col-md-2">
			<input type="submit" value="Generate HTML" class="btn btn-primary" />
		</div>
	</div>
</form>

<jsp:include page="test-cases.jsp" />

<h2>Notes</h2>

<p>This tool is designed for DDI 2.5; it does not support other flavors of DDI XML. If you're working with versions prior to 2.5, you can convert them fairly easily:</p>

<ul>
	<li>Remove the DTD reference just before the opening codeBook element.</li>
	<li>Change the opening codeBook element to:<br />
	<code>&lt;codeBook xmlns="ddi:codebook:2_5"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="ddi:codebook:2_5 http://www.ddialliance.org/Specification/DDI-Codebook/2.5/XMLSchema/codebook.xsd" 
  xmlns:html="http://www.w3.org/1999/xhtml" 
  version="2.5"&gt;</code>
	</li>
	<li>Validate your XML. DDI 2.5 is mostly backwards-compatible; you shouldn't have any problems.</li>
</ul>

<p>Also note that it can be a bit slow for very large files.</p>


</article>
</body>
</html>
