<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<title>Action Failed !!</title>

	<link href="https://fonts.googleapis.com/css?family=Maven+Pro:400,900" rel="stylesheet">

	<link type="text/css" rel="stylesheet" href="app/css/style.css" />

	<script type="text/javascript">
		
		function redirectToHome() {
			setTimeout(function () {
				window.location.href = "./"; 
			}, 3000); // 3000 milliseconds = 3 seconds
		}

	
		window.onload = redirectToHome;
	</script>
</head>

<body>

	<div id="notfound">
		<div class="notfound">
			<div class="notfound-404">
				<h1>ACTION FAILED !!</h1>
			</div>
			<h2>please enter valid credentials</h2>
			<p>Auto reloading back to Login.</p>
			<a href="./">Back To Login</a>
		</div>
	</div>

</body>

</html>
