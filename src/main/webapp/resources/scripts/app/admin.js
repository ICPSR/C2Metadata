var formatDate = function (dt) {
	if (dt == null) {
		return "";
	}
	return new Date(dt).toLocaleDateString() + " " + new Date(dt).toLocaleTimeString();
};

var admin = React.createClass({
	displayName: "admin",


	componentDidMount: function () {
		if (this.isMounted()) {
			this.loadJobs();
			this.searchJobs();
		}
	},
	loadJobs: function () {},
	searchJobs: function () {
		var appUrl = this.props.appUrl;
		var updateFailedJobsTable = this.updateFailedJobsTable;
		$.ajax({
			type: "GET",
			url: appUrl + '/admin/recentFailedJobs',
			dataType: "json",
			cache: false,
			success: function (data) {
				console.log(data);
				updateFailedJobsTable(data);
			}
		});
		var updateCompleteJobsTable = this.updateCompleteJobsTable;
		$.ajax({
			type: "GET",
			url: appUrl + '/admin/recentCompletedJobs',
			dataType: "json",
			cache: false,
			success: function (data) {
				console.log(data);
				updateCompleteJobsTable(data);
			}
		});
	},
	updateFailedJobsTable: function (data) {
		var dTable;
		var appUrl = this.props.appUrl;
		dTable = $('#failedjobsTable').DataTable({

			order: [[1, "desc"]],
			data: data,
			columns: [{ data: "id", "defaultContent": "-" }, { data: "error", "defaultContent": "-" }, { data: "startDate", render: function (data, type, row) {
					return formatDate(data);
				} }, { data: "endDate", render: function (data, type, row) {
					return formatDate(data);
				} }, { data: "tasks", render: function (data, type, row) {

					var codeUrl = appUrl + "/download/jobs/" + row.id + "/input/code.txt";
					var inputfiles;
					if (row.files != null && row.files.length > 0) {
						inputfiles = row.files.map(function (val) {
							var ddiUrl = appUrl + "/download/jobs/" + row.id + "/" + val.fileName + "?path=" + val.fileUrl;
							var filename = val.fileName;
							return "<a target=" + val.id + " href=" + ddiUrl + ">" + filename + "</a><br/>";
						});
					}
					return inputfiles + "<a target=" + row.id + " href=" + codeUrl + ">Code file</a>";
				} }, { data: "tasks", render: function (data, type, row) {

					return "<a target=" + row.id + " href=" + appUrl + "/admin/viewTasks?jobId=" + row.id + ">View Tasks</a>";
				} }]
		});

		dTable.columns().every(function () {
			var that = this;
			$('input', this.header()).on('keyup change', function () {
				if (that.search() !== this.value) {
					that.search(this.value).draw();
				}
			});
			$(this.header()).find('input').on('click', function (e) {
				e.stopPropagation();
			});
		});

		$('#failedjobsTable_length').append('\u00A0\u00A0\u00A0');
		$('#failedjobsTable_info').append('\u00A0\u00A0\u00A0');
		dTable.buttons().container().insertBefore('#failedjobsTable_filter');
	}, updateCompleteJobsTable: function (data) {
		var dTable;
		var appUrl = this.props.appUrl;

		dTable = $('#completedjobsTable').DataTable({

			order: [[1, "desc"]],
			data: data,
			columns: [{ data: "id", "defaultContent": "-" }, { data: "startDate", render: function (data, type, row) {
					return formatDate(data);
				} }, { data: "endDate", render: function (data, type, row) {
					return formatDate(data);
				} }, { data: "tasks", render: function (data, type, row) {

					var codeUrl = appUrl + "/download/jobs/" + row.id + "/input/code.txt";
					var inputfiles = "";
					if (row.files != null && row.files.length > 0) {
						inputfiles = row.files.map(function (val) {
							var ddiUrl = appUrl + "/download/jobs/" + row.id + "/" + val.fileName + "?path=" + val.fileUrl;
							var filename = val.fileName;
							return "<a target=" + val.id + " href=" + ddiUrl + ">" + filename + "</a><br/>";
						});
					}
					return inputfiles + "<a target=" + row.id + " href=" + codeUrl + ">Code file</a>";
				} }, { data: "tasks", render: function (data, type, row) {

					return "<a target=" + row.id + " href=" + appUrl + "/admin/viewTasks?jobId=" + row.id + ">View Tasks</a>";
				} }]
		});

		dTable.columns().every(function () {
			var that = this;
			$('input', this.header()).on('keyup change', function () {
				if (that.search() !== this.value) {
					that.search(this.value).draw();
				}
			});
			$(this.header()).find('input').on('click', function (e) {
				e.stopPropagation();
			});
		});

		$('#completedjobsTable_length').append('\u00A0\u00A0\u00A0');
		$('#completedjobsTable_info').append('\u00A0\u00A0\u00A0');
		dTable.buttons().container().insertBefore('#completedjobsTable_filter');
	},
	render: function () {
		var Failedheader = React.createElement(
			"thead",
			null,
			React.createElement(
				"tr",
				null,
				React.createElement(
					"th",
					{ scope: "col" },
					"JobId"
				),
				React.createElement(
					"th",
					{ scope: "col" },
					"Error Message"
				),
				React.createElement(
					"th",
					{ scope: "col" },
					"Start Time"
				),
				React.createElement(
					"th",
					{ scope: "col" },
					"End Time"
				),
				React.createElement(
					"th",
					{ scope: "col" },
					"Input Files"
				),
				React.createElement(
					"th",
					{ scope: "col" },
					"Tasks"
				)
			)
		);
		var CompletedHeader = React.createElement(
			"thead",
			null,
			React.createElement(
				"tr",
				null,
				React.createElement(
					"th",
					{ scope: "col" },
					"JobId"
				),
				React.createElement(
					"th",
					{ scope: "col" },
					"Start Time"
				),
				React.createElement(
					"th",
					{ scope: "col" },
					"End Time"
				),
				React.createElement(
					"th",
					{ scope: "col" },
					"Input Files"
				),
				React.createElement(
					"th",
					{ scope: "col" },
					"Tasks"
				)
			)
		);
		return React.createElement(
			"div",
			null,
			React.createElement("br", null),
			React.createElement(
				"h3",
				null,
				"Failed Jobs"
			),
			React.createElement(
				"table",
				{ className: "table table-bordered", id: "failedjobsTable", width: "100%" },
				Failedheader,
				React.createElement("tbody", null)
			),
			React.createElement("br", null),
			React.createElement(
				"h3",
				null,
				"Completed Jobs"
			),
			React.createElement(
				"table",
				{ className: "table table-bordered", id: "completedjobsTable", width: "100%" },
				CompletedHeader,
				React.createElement("tbody", null)
			)
		);
	}
});