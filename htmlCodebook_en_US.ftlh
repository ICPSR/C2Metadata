<!DOCTYPE html>
<html lang="en">
<head>
	<#setting datetime_format="MM/dd/yyyy hh:mm a">
	<#setting locale="en_US">
	<#setting url_escaping_charset='UTF-8'>
	<#assign codeMaxSize=6>
	<title>HTML Codebook</title>

	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous" />

	<style type="text/css" media="all">
	
		body {
  			font-family: "Trebuchet MS", "Lucida Grande", "Lucida Sans Unicode", "Lucida Sans", Tahoma, sans-serif;
  			line-height: 1.5;
  			font-size: 9pt;
		}
		
		h4.variableHeader {
			color: #0a37a5;
			font-size: 10pt;
			font-leading: 12pt;
			margin: 15px 0px 9px 0px;
		}
		
		h4.variableHeader:before {
    		content: ' ';
    		display: block;
    		border: 1px solid #0a37a5;
    		margin-bottom: 5px;
		}
		
		.indent {
			margin-left: 16px;
		}
		
		table.categories {
			width: 100%;
			margin-bottom: 10px;
		}
		
		table.categories thead tr th, 
		table.categories tr.categoryHeader {
			background-color: #dedede;
		}
		
		table.categories td, 
		table.categories th {
			padding: 5px;
			margin: 1px;
		}
		
		table.categories tr:nth-child(even) {
			background-color: #efefef;
		}
			
	</style>


	<style type="text/css" media="screen">
	
		body {
			background-color: #efefef;
			margin: 0px;
		}

		article {
			width: 8.5in;
			margin: 0px;
			padding: 0.75in 0.5in 0.5in 0.5in;
			border-right: 1px solid #dedede;
			background-color: #ffffff;
		}
	
	</style>


	<style type="text/css" media="print">

		@page{
			size: letter portrait;
			margin: 0.75in 0.5in 0.5in 0.5in;
 		  	@top-center { content: "Codebook" }
  			@bottom-center {
    			content: "Page " counter(page) " of " counter(pages);
  			}
		}

		h1, h2, h3, h4, h5 {
 			page-break-after: avoid;
		}

		table {
  			page-break-inside: avoid;
		}
		
	</style>	
	
	<script src="https://code.jquery.com/jquery-3.3.1.min.js" integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=" crossorigin="anonymous"></script>	
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
	
	
</head>
<body>

<article>

<#if codebook.stdyDscr?has_content && codebook.stdyDscr[0].citation?has_content && codebook.stdyDscr[0].citation[0].titlStmt?has_content && codebook.stdyDscr[0].citation[0].titlStmt.titl?has_content>
	<h1>${codebook.stdyDscr[0].citation[0].titlStmt.titl.content}</h1>
<#elseif codebook.docDscr?has_content && codebook.docDscr[0].citation?has_content && codebook.docDscr[0].citation[0].titlStmt?has_content && codebook.docDscr[0].citation[0].titlStmt.titl?has_content>
	<h1>${codebook.docDscr[0].citation[0].titlStmt.titl.content}</h1>
<#else>
	<h1>Codebook</h1>
</#if>

<#if codebook.docDscr?has_content>
	<h2 class="sectionHeader">Document Description</h2>
</#if>

<#if codebook.stdyDscr?has_content>
	<h2 class="sectionHeader">Study Description</h2>

	<#list codebook.stdyDscr as stdyDscr>

		<#if stdyDscr.citation?has_content>
		
			<#list stdyDscr.citation as citation>
	
				<h3 class="sectionHeader">Citation</h3>
				
				<#if citation.titlStmt?has_content>
					<#if citation.titlStmt.titl?has_content>
						<p><strong>Title:</strong> ${citation.titlStmt.titl.content}</p>
					</#if>
					<#if citation.titlStmt.subTitl?has_content>
						<#list citation.titlStmt.subTitl as subTitl>
							<p><strong>Subtitle:</strong> ${subTitl.content}</p>
						</#list>
					</#if>
					<#if citation.titlStmt.altTitl?has_content>
						<#list citation.titlStmt.altTitl as altTitl>
							<p><strong>Alternative Title:</strong> ${altTitl.content}</p>
						</#list>
					</#if>
					<#if citation.titlStmt.parTitl?has_content>
						<#list citation.titlStmt.parTitl as parTitl>
							<p><strong>Parallel Title:</strong> ${parTitl.content}</p>
						</#list>
					</#if>
					<#if citation.titlStmt.IDNo?has_content>
						<#list citation.titlStmt.IDNo as IDNo>
							<p><strong>Identification Number:</strong> ${IDNo.content}
								<#if IDNo.agency?has_content>
									(${IDNo.agency})
								</#if>
							</p>
						</#list>
					</#if>
				</#if>
				
				<#if citation.rspStmt?has_content>
					<#if citation.rspStmt.AuthEnty?has_content> <!-- not working for some reason -->
						<p><strong>Principal Investigator(s)</strong></p>
						<ul>
							<#list citation.rspStmt.AuthEnty as AuthEnty>
								<li>
									${AuthEnty.content}
									<#if AuthEnty.affiliation?has_content>
										, ${AuthEnty.affiliation}
									</#if>
								</li>
							</#list>
						</ul>
					</#if>
					<#if citation.rspStmt.othId?has_content>
						<p><strong>Other Identifications/Acknowledgements</strong></p>
						<ul>
							<#list citation.rspStmt.othId as othId>
									${othId.content}
									<#if othId.affiliation?has_content>
										, ${othId.affiliation}
									</#if>
									<#if othId.role?has_content>
										(${othId.role})
									</#if>
							</#list>
						</ul>
					</#if>
				</#if>
	
				<#if citation.prodStmt?has_content>
					<#if citation.prodStmt.producer?has_content>
						<#list citation.prodStmt.producer as producer>
							<p><strong>Producer:</strong> ${producer.content}</p>
						</#list>
					</#if>
					<#if citation.prodStmt.copyright?has_content>
						<#list citation.prodStmt.copyright as copyright>
							<p><strong>Copyright:</strong> ${copyright.content}</p>
						</#list>
					</#if>
					<#if citation.prodStmt.prodDate?has_content>
						<#list citation.prodStmt.prodDate as prodDate>
							<p><strong>Date of Production:</strong> ${prodDate.content}</p>
						</#list>
					</#if>
					<#if citation.prodStmt.prodPlac?has_content>
						<#list citation.prodStmt.prodPlac as prodPlac>
							<p><strong>Place of Production:</strong> ${prodPlac.content}</p>
						</#list>
					</#if>
					<#if citation.prodStmt.software?has_content>
						<#list citation.prodStmt.software as software>
							<p><strong>Software:</strong> ${software.content}</p>
						</#list>
					</#if>
					<#if citation.prodStmt.fundAg?has_content>
						<p><strong>Funding Source(s)</strong></p>
						<ul>
							<#list citation.prodStmt.fundAg as fundAg>
								<li>${fundAg.content} ${getGrantNo(citation.prodStmt fundAg.content)}</li>
							</#list>
						</ul>
					</#if>
				</#if>
	
				<#if citation.distStmt?has_content>

				</#if>

				<#if citation.serStmt?has_content>

				</#if>

			</#list>

		</#if>

	</#list>
	
</#if>

<#if codebook.fileDscr?has_content>
	${displayFileDscr(codebook)}
</#if>

<#if codebook.dataDscr?has_content>
	${displayDataDscr(codebook)}
</#if>


</article>

</body>
</html>

<#function displayFileDscr codebook>
	<#assign output = ''>
	<#list codebook.fileDscr as fileDscr>
		<#assign output = output + '<h2 class=\"sectionHeader\">File Description ' + (fileDscr?index + 1)+ '</h2>'>
		<#list fileDscr.fileTxt as fileTxt>
			<#assign output = output + displayFileTxt(fileTxt)>
		</#list>
		<#if fileDscr.notes?has_content>
			<#list fileDscr.notes as note>
				<#assign output = output + displayNotes(note)>
			</#list>
		</#if>
	</#list>
	<#return output>
</#function>

<#function displayFileTxt fileTxt>
	<#assign output = '<p><strong>'>
	<#list fileTxt.fileName as fileName>
		<#assign output = output + fileName.content><#sep>, </#sep>	
	</#list>
	<#assign output = output + '</strong>'>
	<#if fileTxt.fileType?has_content>
		<#assign output = output + ' ('>
		<#list fileTxt.fileType as fileType>
			<#assign output = output + fileType.content><#sep>, </#sep>	
		</#list>
		<#assign output = output + ')'>
	</#if>
	<#assign output = output + '</p>'>
	<#if fileTxt.dimensns?has_content && (fileTxt.dimensns.caseQnty?has_content || fileTxt.dimensns.varQnty?has_content)>
		<#assign output = output + '<ul>'>
		<#if fileTxt.dimensns.caseQnty?has_content>
			<#assign output = output + '<li>Number of Cases: '>
			<#list fileTxt.dimensns.caseQnty as caseQnty>
				<#assign output = output + caseQnty.content><#sep>, </#sep>	
			</#list>
			<#assign output = output + '</li>'>
		</#if>
		<#if fileTxt.dimensns.varQnty?has_content>
			<#assign output = output + '<li>Variable Count: '>
			<#list fileTxt.dimensns.varQnty as varQnty>
				<#assign output = output + varQnty.content><#sep>, </#sep>	
			</#list>
			<#assign output = output + '</li>'>
		</#if>
		<#assign output = output + '</ul>'>
	</#if>

	<#return output>
</#function>

<#function displayNotes note>
	<#assign output = ''>
	<#assign output = output + '<p>' + notes.content + '</p>'>
	<#return output>
</#function>







<#function displayDataDscr codebook>
	<#assign output = ''>
	<#list codebook.dataDscr as dataDscr>
		<#assign collapseId = 'dataDscr' + dataDscr?index>
		<#assign expanded = 'false'>
		<#assign title = 'Current Variable Descriptions'>
		<#if dataDscr?index = 0>
			<#assign expanded = 'true'>
			<#assign title = 'Variable Descriptions Before Transformation'>
		</#if>
		<#assign collapseLink = '<a href=\"#' + collapseId + '\" data-toggle=\"collapse\" aria-expanded=\"' + expanded + '\" aria-controls=\"' + collapseId + '\" role=\"button\">'>
		<#assign output = output + '<h2 class=\"sectionHeader\">' + collapseLink + title + '</a></h2>'>
		<#assign style = 'collapse'>
		<#if dataDscr?index != 0>
			<#assign style = 'collapse in'>
		</#if>
		<#assign output = output + '<div class=\"' + style + '\" id=\"' + collapseId + '\"><h3 class=\"subSectionHeader\">Variables (' + dataDscr.var?size + ')</h3>'>
		<#list dataDscr.var as var>
			<#assign output = output + displayVar(var)>
		</#list>
		<#assign output = output + '</div>'>
	</#list>
	<#return output>
</#function>

<#function displayVar variable>
	<#assign output = '<h4 id="' + variable.ID + '" class="variableHeader">' + variable.name>
	<#if variable.labl?has_content>
		<#assign output = output + displayLabel(variable)>
	</#if>
	<#assign output = output + '</h4>'>
	<#assign output = output + '<div class=\"indent\">'>
	<#if variable.qstn?has_content>
		<#list variable.qstn as question>
			<#assign output = output + displayQstn(question)>
		</#list>
	</#if>	
	<#if variable.catgry?has_content>
		<#assign output = output + displayCategoryTable(variable)>
	</#if>
	<#if variable.location?has_content>
		<#assign output = output + displayLocation(variable)>
	</#if>
	<#if variable.valrng?has_content>
		<#assign output = output + displayValidRange(variable)>
	</#if>
	<#if variable.invalrng?has_content>
		<#assign output = output + displayInvalidRange(variable)>
	</#if>
	<#if variable.sumStat?has_content>
		<#assign output = output + displaySumStats(variable)>
	</#if>
	<#if variable.varFormat??>
		<#assign output = output + displayVarFormat(variable)>
	</#if>		
	<#if variable.derivation??>
		<#assign output = output + displayDerivation(variable)>
	</#if>		
	<#assign output = output + '</div>'>
	<#return output>
</#function>

<#function displayLabel variable>
	<#assign output = ': '>
	<#list variable.labl as label>
		<#if label.content?has_content>
			<#assign output = output + label.content + ' '>
		</#if>
	</#list>
	<#return output>
</#function>

<#function displayQstn question>
	<#assign output = ''>
	<#if question.qstnLit?has_content>
		<#assign output = output + displayQstnLit(question.qstnLit)>
	</#if>
	<!-- now do the same for preQTxt, postQTxt, forward, backward, ivuInstr -->
	<!-- figure out how to handle mixed content; what if there are no sub-elements...just text? -->
	<#return output>
</#function>

<#function displayQstnLit qstnLit>
	<#assign output = ''>
	<#list question.qstnLit as literalQuestion>
		<#assign output = output + '<p class=\"question\">' + literalQuestion.content + '</p>'>
	</#list>
	<#return output>
</#function>

<#function displayCategoryTable variable>								
	<!-- First we need to interate through the categories to determine what kind of elements are present -->
	<#assign isCodeBig=false>
	<#assign isLabelPresent=false>
	<#assign codeAlign='center'>
	<#assign missingPresent=false>
	<#assign systemMissingPresent=false>
	<#assign itemMissingPresent=false>
	<#list variable.catgry as category>
		<#if category.catValu?has_content && (category.catValu.content?trim?length &gt; codeMaxSize)>
			<#assign isCodeBig=true>
		</#if>
		<#if category.labl?has_content>
			<#assign isLabelPresent=true>
		</#if>
		<#if isCodeBig && !isLabelPresent>
			<#assign codeAlign='left'>
		</#if>
		<#if category.missing?has_content && category.missing=='Y'>
			<#assign missingPresent=true>
		</#if>
		<#if category.missType?has_content && (category.missType=='sysmiss' || category.missType=='system')>
			<#assign systemMissingPresent=true>
		</#if>
		<#if category.missType?has_content && (category.missType=='item' || category.missType=='dkrf')>
			<#assign itemMissingPresent=true>
		</#if>
	</#list>			
	<#assign col1width=10>
	<#assign col2width=70>
	<#assign col3width=10>
	<#assign col4width=10>
	<#if isCodeBig && isLabelPresent>
		<#assign col1width=20>
		<#assign col2width=50>
		<#assign col3width=10>
		<#assign col4width=10>
	<#elseif isCodeBig>
		<#assign col1width=70>
		<#assign col2width=10>
		<#assign col3width=10>
		<#assign col4width=10>
	</#if>		
	<#assign output = '<table class=\"categories\">'>
	<#assign output = output + '<thead>'>
	<#assign output = output + '<tr>'>
	<#assign output = output + '<th style=\"width:' + col1width + '%;text-align:' + codeAlign+ '\">Value</th>'>
	<#assign output = output + '<th style=\"width:' + col2width + '%;text-align:left;\">Label</th>'>
	<#assign output = output + '<th style=\"width:' + col3width + '%;text-align:right;\">Unweighted<br />Frequency</th>'>
	<#assign output = output + '<th style=\"width:' + col4width + '%;text-align:right;\">%</th>'>
	<#assign output = output + '</tr>'>
	<#assign output = output + '</thead>'>
	<#assign output = output + '<tbody>'>
	<!-- non-missing categories first -->
	<#list variable.catgry as category>
		<#if !category.missing?has_content || category.missing=='N'>
			<#assign output = output + displayCategory(category)>
		</#if>
	</#list>
	<!-- now the three missing categories -->
	<#if systemMissingPresent>
		<#assign output = output + '<tr class=\"categoryHeader\">'>
		<#assign output = output + '<th> </th>'>
		<#assign output = output + '<th style=\"text-align:left\" colspan=\"3\">Missing Data (System Missing)</th>'>
		<#assign output = output + '</tr>'>
		<#list variable.catgry as category>
			<#if category.missType?has_content && (category.missType=='sysmiss' || category.missType=='system')>
				<#assign output = output + displayCategory(category)>
			</#if>
		</#list>
	</#if>
	<#if itemMissingPresent>
		<#assign output = output + '<tr class=\"categoryHeader\">'>
		<#assign output = output + '<th> </th>'>
		<#assign output = output + "<th style=\"text-align:left\" colspan=\"3\">Missing Data (Don't Know/Refused)</th>">
		<#assign output = output + '</tr>'>
		<#list variable.catgry as category>
			<#if category.missType?has_content && (category.missType=='item' || category.missType=='dkrf')>
				<#assign output = output + displayCategory(category)>
			</#if>
		</#list>
	</#if>
	<#if missingPresent>
		<#assign output = output + '<tr class=\"categoryHeader\">'>
		<#assign output = output + '<th> </th>'>
		<#assign output = output + '<th style=\"text-align:left\" colspan=\"3\">Missing Data</th>'>
		<#assign output = output + '</tr>'>
		<#list variable.catgry as category>
			<#if category.missing?has_content && category.missing=='Y' && !category.missType?has_content>
				<#assign output = output + displayCategory(category)>
			</#if>
		</#list>
	</#if>
	<#assign output = output + '</tbody>'>
	<#assign output = output + '</table>'>
	<#return output>
</#function>


<#function displayCategory category>
	<#assign output = '<tr>'>
	<#assign output = output + '<td style=\"text-align:center;\">' + getCode(category) + '</td>'>
	<#assign output = output + '<td>' + getLabel(category) + '</td>'>
	<#assign output = output + '<td style=\"text-align:right;\">'+ getFreq(category) + '</td>'>
	<#assign output = output + '<td style=\"text-align:right;\">' + getPercent(category) + '</td>'>
	<#assign output = output + '</tr>'>
	<#return output>
</#function>

<#function getCode category>
	<#if category.catValu?has_content>
		<#return category.catValu.content>
	<#else>
		<#return '-'>
	</#if>
</#function>

<#function getLabel category>
	<#assign output=''>
	<#if category.labl?has_content>
		<#list category.labl as label>
			<#assign output=output + label.content?trim + ' '>
		</#list>
	</#if>
	<#if output?length==0>
		<#assign output='-'>
	</#if>	
	<#return output>
</#function>

<#function getFreq category>
	<#assign freq='-'>
	<#if category.catStat?has_content>
		<#list category.catStat as stat>
			<#if stat.type=='freq'>
				<#assign freq=stat.content>
			</#if>
		</#list>
	</#if>
	<#return freq>
</#function>

<#function getPercent category>
	<#assign percent='-'>
	<#if category.catStat?has_content>
		<#list category.catStat as stat>
			<#if stat.type=='percent'>
				<#assign percent=stat.content?trim + '%'>
			</#if>
		</#list>
	</#if>
	<#return percent>
</#function>

<#function displayLocation variable>
	<#assign output = '<p><strong>Location</strong></p>'>
	<#assign output = output + '<ul>'>
	<#if variable.location?has_content>
		<#list variable.location as location>
			<#if location.width?has_content>
				<#assign output = output + '<li>width:' + location.width + '</li>'>
			</#if>
		</#list>
	</#if>
	<#assign output = output + '</ul>'>
	<#return output>
</#function>

<#function displayValidRange variable>
	<#assign output = '<p><strong>Valid Range:</strong> '>
	<#list variable.valrng as valrng>
		<#if valrng.itemOrRange?has_content>
			<#list valrng.itemOrRange as itemOrRange>
				<#if itemOrRange.min?has_content>
					<#assign output = output + itemOrRange.min>
				</#if>
				<#if itemOrRange.max?has_content>
					<#assign output = output + '-' + itemOrRange.max>
				</#if>
				<#if itemOrRange.VALUE?has_content>
					<#assign output = output + itemOrRange.VALUE>
				</#if>
				<#sep><#assign output = output + ', '></#sep>	
			</#list>			
		</#if>
		<#sep><#assign output = output + '; '></#sep>
	</#list>
	<#return output>
</#function>

<#function displayInvalidRange variable>
	<#assign output = '<p><strong>Invalid Range:</strong> '>
		<#list variable.invalrng as invalrng>
			<#if invalrng.itemOrRange?has_content>
				<#list invalrng.itemOrRange as itemOrRange>
					<#if itemOrRange.min?has_content>
						<#assign output = output + itemOrRange.min>
					</#if>
					<#if itemOrRange.max?has_content>
						<#assign output = output + '-' + itemOrRange.max>
					</#if>
					<#if itemOrRange.VALUE?has_content>
						<#assign output = output + itemOrRange.VALUE>
					</#if>
					<#sep><#assign output = output + ', '></#sep>	
				</#list>			
			</#if>
			<#sep><#assign output = output + '; '></#sep>
		</#list>
	<#return output>
</#function>

<#function displaySumStats variable>
	<#assign output =''>
	<#assign invalid=''>
	<#assign valid=''>
	<#list variable.sumStat as sumStat>
		<#if sumStat.type=='invd'>
			<#assign invalid=sumStat.content?trim>
		<#elseif sumStat.type=='vald'>
			<#assign valid=sumStat.content?trim>
		</#if>
	</#list>
	<#if invalid?has_content && valid?has_content>
		<#assign output = output + '<p>Based upon ' + valid?number + ' valid cases out of ' + (valid?number + invalid?number) + ' total cases.</p>'>
	</#if>
	<#assign output = output + '<ul>'>
		<#list variable.sumStat as sumStat>
			<#if sumStat.type!='invd' && sumStat.type!='vald'>
				<#assign output = output + '<li>' + getSumStatLabel(sumStat) + ': ' + sumStat.content?trim + '</li>'>
			</#if>
		</#list>
	<#assign output = output + '</ul>'>
	<#return output>
</#function>

<#function getSumStatLabel sumStat>
	<#if sumStat.type=='mean'>
		<#return 'Mean'>
	<#elseif sumStat.type=='mode'>
		<#return 'Mode'>
	<#elseif sumStat.type=='medn'>
		<#return 'Median'>
	<#elseif sumStat.type=='min'>
		<#return 'Minimum'>
	<#elseif sumStat.type=='max'>
		<#return 'Maximum'>
	<#elseif sumStat.type=='stdev'>
		<#return 'Standard Deviation'>
	<#elseif sumStat.type=='vald'>
		<#return 'Valid'>
	<#elseif sumStat.type=='invd'>
		<#return 'Invalid'>
	<#else>
		<#return 'Other'>
	</#if>
</#function>

<#function displayVarFormat variable>
	<#assign output = '<p><strong>Variable Format:</strong>'>
		<#if variable.varFormat.content?has_content>
			<#assign output = output + variable.varFormat.content>
		</#if>
		<#if variable.varFormat.type?has_content><#assign output = output + ' ' + variable.varFormat.type></#if>
		<#if variable.varFormat.schema?has_content><#assign output = output + ' (' + variable.varFormat.schema + ')'></#if>
	<#return output>
</#function>

<#function displayDerivation variable>
	<#assign output = ''>
	<#if variable.derivation?has_content>
		<#assign varLink = ''>	
		<#if variable.derivation.var?has_content>
			<#list variable.derivation.var as var>
				<#assign varLink = varLink + '<a href=\"#' + var.ID + '\">' + var.ID + '</a>'>
				<#sep><#assign varLink = varLink + ', '></#sep>
			</#list>
		</#if>
		<#assign output = output + '<p><strong>Derivation</strong>'>
		<#if variable.derivation.var?has_content>
			<#assign output = output + ' (from ' + varLink + ')'>
		</#if>
		<#assign output = output + '</p>'>
		<#if variable.derivation.drvdesc?has_content>	
			<#list variable.derivation.drvdesc as description>
				<#assign output = output + displayDerivationDescription(description)>
			</#list>
		</#if>
		<#if variable.derivation.drvcmd?has_content>	
			<#list variable.derivation.drvcmd as command>
				<#assign output = output + displayDerivationCommand(command)>
			</#list>
		</#if>
	</#if>
	<#return output>
</#function>

<#function displayDerivationCommand command>
	<#assign output = '<p><em>Command</em></p>'>
	<#if command.syntax?has_content>
		<#assign output = '<p><em>Command (' + command.syntax + ')</em></p>'>
	</#if>
	<#assign output = output + '<pre>' + command.content + '</pre>'>
	<#return output>
</#function>

<#function displayDerivationDescription description>
	<#assign output = '<p><em>Description:</em> ' + description.content + '</p>'>
	<#return output>
</#function>

<#function getGrantNo prodStmt fundAg>
	<#assign output=''>
	<#list prodStmt.grantNo as grantNo>
		<#if grantNo.agency?has_content && grantNo.agency==fundAg>
			<#assign output=output + grantNo.content?trim + ' '>
		</#if>
	</#list>
	<#if output?has_content>
		<#assign output=' (' + output +')'>
	</#if>
	<#return output>
</#function>


