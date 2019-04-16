var formatDate = function(dt){
	  if(dt == null){
		  return "";
	  }
	  return new Date(dt).toLocaleDateString()+" "+new Date(dt).toLocaleTimeString();
}

var tasksView = React.createClass({		

	componentDidMount: function(){
		if(this.isMounted()){
		
		this.loadTasks();
		}
	},
	loadTasks:function(){
		
		var updateTasksTable= this.updateTasksTable;
		var url = this.props.appUrl+"/admin/tasks?jobId="+this.props.jobId
		var jobId=this.props.jobId;
		$.ajax({
			type: "GET",
			url :url,	
			dataType: "json",
			cache: false,
			success: function(data){
				console.log(data);
				updateTasksTable(data);
			}
		});
		
	},
	updateTasksTable: function(data){
		console.log(data);
		var dTable;
        var appUrl = this.props.appUrl;
		
			dTable = $('#tasksTable').DataTable({
				
		      
				order: [[ 1, "desc" ]],
				data: data,
				columns:[				
					{data:"id","defaultContent": "-"	},
					{data:"jobId","defaultContent": "-"	},
					
					{data:"error","defaultContent": "-"},
					
					
					{data:"startDate",render:function(data,type,row){
						return formatDate(data);
					}},
					{data:"endDate",render:function(data,type,row){
						return formatDate(data);
					}},
					{data:"id","defaultContent": "-",render: function ( data, type, row ) {
						if(row.outputType){
						if(row.error == null ||row.error.trim()==''){
							if(row.outputType==null||row.outputType.trim()=="null"||row.outputType.trim()==""){
								return "-";
							}else{
								var filename="";
								var displayName="";
									if("ddi"==row.outputType.trim()){
										filename="revised-ddi.xml";
										displayName ="Revised DDI XML"
									}else if ("spss"==row.outputType.trim()||"stata"==row.outputType.trim()||"sdtl"==row.outputType.trim()){
										filename="sdtl.json";
										displayName="SDTL";
									}else if ("html"==row.outputType.trim()){
										filename="codebook.html";
										displayName="HTML Codebook";										
									}
										
								if(filename!=""){
									var outputfile= "/download/jobs/";
							outputfile+=row.jobId+"/output/"+filename;
							outputfile=appUrl+outputfile;
									return "<a target="+row.outputType+" href="+outputfile+">"+displayName+"</a>";
								}else{
									return "-";
								}
							}
						}
					 }else{
						 return "-";
					 }
						
					}	
					}
				]
			});
			
			dTable.columns().every( function () {
		        var that = this;
		        $( 'input', this.header() ).on( 'keyup change', function () {
		            if ( that.search() !== this.value ) {
		                that
		                    .search( this.value )
		                    .draw();
		            }
		        } );
		        $( this.header()).find('input').on( 'click', function (e) {
					e.stopPropagation();
				} );
		    } );
			
		
		
		$('#tasksTable_length').append('\u00A0\u00A0\u00A0');
		$('#tasksTable_info').append('\u00A0\u00A0\u00A0');
		dTable.buttons().container().insertBefore( '#tasksTable_filter' );
	},
	render: function() {
	var	Failedheader=(
				<thead>
					<tr>
						<th scope="col">Id</th>
						<th scope="col">JobId</th>	
						<th scope="col">Error</th>
						<th scope="col">Start Time</th>
						<th scope="col">End Time</th>
						
						<th scope="col">Output Files</th>
						
					</tr>
				</thead>
			);
	
		return (
		<div>
			
		<br/>
		
		<h3>Failed Jobs</h3>

		<table  className="table table-bordered" id="tasksTable" width="100%" >
		{Failedheader}
		<tbody>
				
		</tbody>
		</table>
		
		<br/>
		
			
			</div>);
	}
});