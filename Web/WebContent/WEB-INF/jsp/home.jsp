<html>
	<head>
		<title>Lumaro Twitter Feel</title>
	    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap-theme.min.css">
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
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
			<h2>${welcome}</h2>
			<br/>
			<span>To begin your search, text your topic in the text field below:</span>
			<form method="get" action="/lumaro-twitter-feel/search">
				<input name="topic" type="text"/>
				<input type="submit" class="btn btn-default"/>
			</form>
		</div>
	</body>
</html>