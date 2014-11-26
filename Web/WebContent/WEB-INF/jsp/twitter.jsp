<html>
	<head>
		<title>Hello World</title>

	    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap-theme.min.css">
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
	    <script type="text/javascript" src="https://www.google.com/jsapi"></script>
	    <script type="text/javascript">

	      google.load('visualization', '1.0', {'packages':['corechart']});

	      // Set a callback to run when the Google Visualization API is loaded.
	      google.setOnLoadCallback(drawChart);

	      // Callback that creates and populates a data table,
	      // instantiates the pie chart, passes in the data and
	      // draws it.
	      function drawChart() {

	        // Create the data table.
	        var data = new google.visualization.DataTable();
	        data.addColumn('string', 'Topping');
	        data.addColumn('number', 'Slices');
	        data.addRows(
	        [
	         ${chartData}
	        ]
	        );

	        // Set chart options
	        var options = {'title':'LUMARO TwitterFeel Analysis',
	                       'width':800,
	                       'height':600};

	        // Instantiate and draw our chart, passing in some options.
	        var chart = new google.visualization.PieChart(document.getElementById('chart_div'));
	        chart.draw(data, options);
	      }
	    </script>
	</head>
	<body>
		<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
			<div class="container">
				<div class="navbar-header">
					<a class="navbar-brand" href="#">Lumaro Twitter Feel</a>
				</div>
				<div id="navbar" class="collapse navbar-collapse">
					<ul class="nav navbar-nav">
						<li class="active"><a href="#">Home</a></li>
						<li><a href="#about">About</a></li>
						<li><a href="#contact">Contact</a></li>
					</ul>
				</div>
			</div>
		</nav>

		<div class="container">
			<br/>
			<h2>${average}</h2>
			<br/>
			<h2>${oldestText}</h2>
			<br/>
			<h2>${oldestOwner}</h2>
			<br/>
			<h2>${newestText}</h2>
			<br/>
			<h2>${newestOwner}</h2>
			<br/>
			<h2>${chartData}</h2>
			<div id="chart_div"></div>
		</div>
	</body>
</html>