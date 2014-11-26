<html>
	<head>
		<title>Lumaro Twitter Feel</title>
	    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap-theme.min.css">
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="https://www.google.com/jsapi"></script>
	    <script type="text/javascript">

	      google.load('visualization', '1.0', {'packages':['corechart']});
	      google.setOnLoadCallback(drawChart);
	      function drawChart() {
	        var data = new google.visualization.DataTable();
	        data.addColumn('string', 'Topping');
	        data.addColumn('number', 'Slices');
	        data.addRows(
	        [
	         ${chartData}
	        ]
	        );
	        var options = {'title':'Twitter Feel Analysis',
	                       'width':650,
	                       'height':650};
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
						<li class="active"><a href="/lumaro-twitter-feel/home">Home</a></li>
						<li><a href="/lumaro-twitter-feel/about">About</a></li>
					</ul>
				</div>
			</div>
		</nav>

		<div class="container">
			<br/>
			<br/>
			<br/>
			<div class="row">
				<div class="col-md-6">
					<br/>
					<br/>
					<br/>
					<br/>
					<h4>Oldest Tweet:</h4>
					<span>${oldestText}</span>
					<br/>
					<h4>Oldest Tweet By:</h4>
					<span>${oldestOwner}</span>
					<br/>
					<hr>
					<h4>Newest Tweet:</h4>
					<span>${newestText}</span>
					<br/>
					<h4>Newest Tweet By:</h4>
					<span>${newestOwner}</span>
				</div>
				<div class="col-md-6">
					<div id="chart_div"></div>
				</div>
			</div>
		</div>
	</body>
</html>