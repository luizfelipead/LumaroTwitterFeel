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
			<span>To begin your search, fill the form below:</span>
			</br>
			</br>
			<div class="row">
				<form method="get" action="/lumaro-twitter-feel/search">
					<div class="col-md-4">
						<label>Text:</label>
						</br>
						<input name="topic" type="text"/>
						</br>
						</br>
						<label>Tweets Quantity:</label>     <span class="badge">Numbers Only</span>
						</br>
						<input name="tweetQuantity" type="text"/>
					</div>
					<div class="col-md-4">
						<label>Language:</label>     
						<select name="language">
							<option value="en">en</option>
						</select>
						</br>
						</br>
						<label>Consider repeated tweets?</label>     
						<input type="checkbox" name="addRepeated">
						</br>
						</br>
						<input type="submit" class="btn btn-sm btn-success"/>
					</div>
				</form>
			</div>
		</div>
	</body>
</html>