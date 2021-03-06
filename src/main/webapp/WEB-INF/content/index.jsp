<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
		.header{
		background-color: #31708f;

		color:white;
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

<h1>Test the Software (V.2.5)</h1>

	<p>The software generated by this project will take as input a Stata/SPSS/R code file and a DDI 2.5 XML file. The software 
	will then update the DDI file to reflect the changes described into the code file and return both the revised DDI file 
	and an html codebook generated from that file. Because processing can take some time, the output will be emailed to the 
	address you used to log in.	</p>

<c:choose>
	<c:when test="${AUTH_USER==null}">
		<p><strong>In order to test the software, you will need to sign in first.</strong></p>
	</c:when>
	<c:otherwise>

		<form method="POST" action="<c:url value="/endToEnd" />" class="form" enctype="multipart/form-data">
		
			<div class="row">
				<div class="col-md-4">
					<div class="input-group">
		                <label class="input-group-btn">
		                    <span class="btn btn-default">
		                        Select Code... <input type="file" name="codeFile" id="codeFile" accept=".txt,.do,.sps,text/plain" style="display:none;" multiple="" />
		                    </span>
		                </label>
		                <input type="text" class="form-control" readonly="" />
		            </div>
				</div>
					<div class="col-md-8 radio">
					<label class="radio-inline">
						<input type="radio" name="mode" value="spss" checked="checked" /> SPSS 
					</label>
					<label class="radio-inline">
						<input type="radio" name="mode" value="stata" /> Stata 
					</label>
					<label class="radio-inline">
						<input type="radio" name="mode" value="sas" /> SAS 
					</label>
					<label class="radio-inline">
						<input type="radio" name="mode" value="r" /> R 
					</label>
				</div>
			</div>
			<div lass="row"> 
			
				<div class="col-md-6 radio">
				<span>  File Type :</span>
					<label class="radio-inline">
						<input type="radio" name="type" value="xml" checked="checked" /> XML 
					</label>
					<label class="radio-inline">
						<input type="radio" name="type" value="eml" /> EML 
					</label>
					</div>
			
			</div>
		
			<div class="row">
			<div class="col-md-8">
			 <table class="table table-bordered" >
    <thead>
      <tr>
        <th class="header" >Input data<br/> filename in <br/>command script(required)</th>
        <th  class="header" >DDI FILE (required)</th>
        <th  class="header" >Filename in DDI <br/>
       (Used when more than one data <br/>file is described in the DDI file)</th>
      </tr>
    </thead>
    <tbody>
      <tr >
        <td>	<div class="col-md-10"> <input name="script_filename0" id="script_filename0" type="text" class="form-control" /></div></td>
        <td>	<div class="col-md-10">
					<div class="input-group">
		                <label class="input-group-btn">
		                    <span class="btn btn-default">
		                        Select DDI... <input type="file" name="ddiFile0" id="ddiFile0" accept=".xml,application/xml" style="display:none;"/>
		                    </span>
		                </label>
		                <input type="text" class="form-control" readonly="" />
		            </div>
				</div></td>
        <td><div class="col-md-10"><input name="ddi_filename0" id="ddi_filename0" type="text" class="form-control"  /></div></td>
      </tr>
      <tr>
        <td> <div class="col-md-10"><input name="script_filename1" id="script_filename1" type="text" class="form-control"  /></div></td>
        <td>	<div class="col-md-10">
					<div class="input-group">
		                <label class="input-group-btn">
		                    <span class="btn btn-default">
		                        Select DDI... <input type="file" name="ddiFile1" id="ddiFile1" accept=".xml,application/xml" style="display:none;"/>
		                    </span>
		                </label>
		                <input type="text" class="form-control" readonly="" />
		            </div>
				</div></td>
        <td><div class="col-md-10"><input name="ddi_filename1" id="ddi_filename1" type="text" class="form-control"  /></div></td>
      </tr>
      <tr>
        <td> <div class="col-md-10"><input name="script_filename2" id=script_filename2 type="text" class="form-control"  /></div></td>
        <td>	<div class="col-md-10">
					<div class="input-group">
		                <label class="input-group-btn">
		                    <span class="btn btn-default">
		                        Select DDI... <input type="file" name="ddiFile2" id="ddiFile2" accept=".xml,application/xml" style="display:none;"/>
		                    </span>
		                </label>
		                <input type="text" class="form-control" readonly="" />
		            </div>
				</div></td>
        <td><div class="col-md-10"><input name="ddi_filename2" id="ddi_filename2" type="text" class="form-control"  /></div></td>
      </tr>
        <tr>
        <td> <div class="col-md-10"><input name="script_filename3" id="script_filename3" type="text" class="form-control"  /></div></td>
        <td>	<div class="col-md-10">
					<div class="input-group">
		                <label class="input-group-btn">
		                    <span class="btn btn-default">
		                        Select DDI... <input type="file" name="ddiFile3" id="ddiFile3" accept=".xml,application/xml" style="display:none;"/>
		                    </span>
		                </label>
		                <input type="text" class="form-control" readonly="" />
		            </div>
				</div></td>
        <td><div class="col-md-10"><input name="ddi_filename3" id="ddi_filename3" type="text" class="form-control" /></div></td>
      </tr>
    </tbody>
  </table>
  </div>
  </div>
			<div class="row">
			
				<div class="col-md-2">
					<input type="submit" value="GO" class="btn btn-primary" />
				</div>
			</div>
		</form>
	<!--  
		<jsp:include page="test-cases.jsp" />

		<%-- add "c2m-admins" only if clause surrounding stuff below this point --%>
		
		<h2>Admin./Developers</h2>
		
		<p>The links below are provided for the project developers and are not particularly useful to end users.</p>
		
		<h3>Individual Steps</h3>
		
		<ul>						
			<li><a href="<c:url value="/static/parseSpss" />">Convert SPSS code to SDTL</a> or <a href="<c:url value="/static/parseStata" />">Convert Stata code to SDTL</a></li>
			<li><a href="<c:url value="/static/update" />">Merge SPSS/Stata Code into DDI File</a></li>
			<li><a href="<c:url value="/static/codebook" />">Build HTML Codebook from DDI XML</a></li>
			<li><a href="<c:url value="/pseudocode" />">Pseudocode</a></li>
		</ul>
		
		<h3>Resources</h3>
		
		<ul>
			<li><a href="http://c2metadata.org/">http://c2metadata.org/</a></li>
			<li><a href="http://c2metadata.gitlab.io/sdtl-docs/">http://c2metadata.gitlab.io/sdtl-docs/</a> (json schema)</li>
			<li><a href="https://github.com/ICPSR/C2Metadata">https://github.com/orgs/ICPSR/C2Metadata</a> (freemarker template)</li>
			<li><a href="https://gitlab.com/c2metadata/sdtl-pojo">https://gitlab.com/c2metadata/sdtl-pojo</a> (java object models)</li>
		</ul>
		
		<h2>Test Cases for Developers</h2>
		
		<ul>
			<li><p>Add Files</p>
				<ul>
					<li><a href="<c:url value="/resources/samples/AddFiles/script.sps" />">SPSS Code</a></li>
					<li><a href="<c:url value="/resources/samples/AddFiles/input_1.ddi25.xml" />">DDI XML</a></li>				
				</ul>
			</li>
			<li><p>Aggregate Variables</p>
				<ul>
					<li><a href="<c:url value="/resources/samples/AggregateVariables/script.sps" />">SPSS Code</a></li>
					<li><a href="<c:url value="/resources/samples/AggregateVariables/input.icpsr.xml" />">DDI XML</a></li>				
				</ul>
			</li>
			<li><p>Compute with Addition*</p>
				<ul>
					<li><a href="<c:url value="/resources/samples/Compute_with_Addition/script.sps" />">SPSS Code</a></li>
					<li><a href="<c:url value="/resources/samples/Compute_with_Addition/script.do" />">Stata Code</a></li>
					<li><a href="<c:url value="/resources/samples/Compute_with_Addition/input.ddi25.xml" />">DDI XML</a></li>				
				</ul>
			</li>
			<li><p>Compute with Log and PFormat</p>
				<ul>
					<li><a href="<c:url value="/resources/samples/Compute_with_Log_and_PFormat/script.sps" />">SPSS Code</a></li>
					<li><a href="<c:url value="/resources/samples/Compute_with_Log_and_PFormat/script.do" />">Stata Code</a></li>
					<li><a href="<c:url value="/resources/samples/Compute_with_Log_and_PFormat/input.ddi25.xml" />">DDI XML</a></li>				
				</ul>
			</li>
			<li><p>Compute with Sum</p>
				<ul>
					<li><a href="<c:url value="/resources/samples/Compute_with_Sum/script.sps" />">SPSS Code</a></li>
					<li><a href="<c:url value="/resources/samples/Compute_with_Sum/input.ddi25.xml" />">DDI XML</a></li>				
				</ul>
			</li>
			<li><p>Compute with Sum Treated as Addition</p>
				<ul>
					<li><a href="<c:url value="/resources/samples/Compute_with_sum_treated_as_addition/script.sps" />">SPSS Code</a></li>
					<li><a href="<c:url value="/resources/samples/Compute_with_sum_treated_as_addition/input.ddi25.xml" />">DDI XML</a></li>				
				</ul>
			</li>
			<li><p>Compute with Sum.N and Formats</p>
				<ul>
					<li><a href="<c:url value="/resources/samples/Compute_with_sum.N_and_Formats/script.sps" />">SPSS Code</a></li>
					<li><a href="<c:url value="/resources/samples/Compute_with_sum.N_and_Formats/input.ddi25.xml" />">DDI XML</a></li>				
				</ul>
			</li>
			<li><p>Delete Variable</p>
				<ul>
					<li><a href="<c:url value="/resources/samples/DeleteVariable/script.sps" />">SPSS Code</a></li>
					<li><a href="<c:url value="/resources/samples/DeleteVariable/input.ddi25.xml" />">DDI XML</a></li>				
				</ul>
			</li>
			<li><p>Do Repeat Loop</p>
				<ul>
					<li><a href="<c:url value="/resources/samples/DoRepeatLoop/script.sps" />">SPSS Code</a></li>
					<li><a href="<c:url value="/resources/samples/DoRepeatLoop/input.icpsr.xml" />">DDI XML</a></li>				
				</ul>
			</li>
			<li><p>If Then Condition</p>
				<ul>
					<li><a href="<c:url value="/resources/samples/IfThenCondition/script.sps" />">SPSS Code</a></li>
					<li><a href="<c:url value="/resources/samples/IfThenCondition/input.icpsr.xml" />">DDI XML</a></li>				
				</ul>
			</li>
			<li><p>Match Files</p>
				<ul>
					<li><a href="<c:url value="/resources/samples/MatchFiles/script.sps" />">SPSS Code</a></li>
					<li><a href="<c:url value="/resources/samples/MatchFiles/input_1.icpsr.xml" />">DDI XML</a></li>				
				</ul>
			</li>
			<li><p>Recode with Value Labels</p>
				<ul>
					<li><a href="<c:url value="/resources/samples/RecodeAndValueLabels/script.sps" />">SPSS Code</a></li>
					<li><a href="<c:url value="/resources/samples/RecodeAndValueLabels/script.do" />">Stata Code</a></li>
					<li><a href="<c:url value="/resources/samples/RecodeAndValueLabels/input.ddi25.xml" />">DDI XML</a></li>				
				</ul>
			</li>
			<li><p>Recode as Missing and Rename</p>
				<ul>
					<li><a href="<c:url value="/resources/samples/MissingAndRename/script.sps" />">SPSS Code</a></li>
					<li><a href="<c:url value="/resources/samples/MissingAndRename/script.do" />">Stata Code</a></li>
					<li><a href="<c:url value="/resources/samples/MissingAndRename/input.ddi25.xml" />">DDI XML</a></li>				
				</ul>
			</li>
			<li><p>Rename Variables</p>
				<ul>
					<li><a href="<c:url value="/resources/samples/Rename/script.sps" />">SPSS Code</a></li>
					<li><a href="<c:url value="/resources/samples/Rename/input.ddi25.xml" />">DDI XML</a></li>				
				</ul>
			</li>
			<li><p>Select Cases</p>
				<ul>
					<li><a href="<c:url value="/resources/samples/SelectIf/script.sps" />">SPSS Code</a></li>
					<li><a href="<c:url value="/resources/samples/SelectIf/input.icpsr.xml" />">DDI XML</a></li>				
				</ul>
			</li>
			<li><p>Sort Cases</p>
				<ul>
					<li><a href="<c:url value="/resources/samples/SortCasesAndKeep/script.sps" />">SPSS Code</a></li>
					<li><a href="<c:url value="/resources/samples/SortCasesAndKeep/script.do" />">Stata Code</a></li>
					<li><a href="<c:url value="/resources/samples/SortCasesAndKeep/input.icpsr.xml" />">DDI XML</a></li>				
				</ul>
			</li>
			<li><p>Sort Variables</p>
				<ul>
					<li><a href="<c:url value="/resources/samples/SortAndReorderVariables/script.sps" />">SPSS Code</a></li>
					<li><a href="<c:url value="/resources/samples/SortAndReorderVariables/input.icpsr.xml" />">DDI XML</a></li>				
				</ul>
			</li>
		</ul>
-->
	</c:otherwise>
</c:choose>


	</article>
</body>
</html>