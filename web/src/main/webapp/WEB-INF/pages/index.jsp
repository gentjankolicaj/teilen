<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<!-- Website Template by freewebsitetemplates.com -->
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>



<link href="<c:url value="/css/style.css" />" rel="stylesheet"
	type="text/css" />

</head>
<body>
	<div id="header">

		<div class="navigation">
			<ul>
				<li class="selected"><a href="home">home</a></li>

				<li><a href="about">about</a></li>
				<li><a href="contact">contact</a></li>

				<li class="booking"><a href="signin">signin</a></li>
				<li class="booking"><a href="signup">signup</a></li>
				<li class="booking"><a href="organization">organization</a></li>
			</ul>
		</div>

	</div>

	<div id="body">
		<div class="content">
			
					<h2>Welcome to teamchat</h2>
					<p>Teamchat is a web application for chatting.</p>
					<ul>
					<span>To be implemented functionalities :</span>
					<li>Input validation</li>
					<li>Spring security</li>
					<li>Control panel</li>
					<li>User display according to organization</li>
					<li>Contact page message</li>
					<li>And many others for which backend services are ready...</li>
					</ul>
					
			
		</div>
	</div>
	<div id="footer">
		<div>
			<div class="contact">
				<h3>contact information</h3>
				<ul>
					<li><b>address:</b> <span>426 London UK,Doe Palace</span></li>
					<li><b>phone:</b> <span>101</span></li>
					<li><b>fax:</b> <span>202</span></li>
					<li><b>email:</b> <span>johndoe@email.com</span></li>
				</ul>
			</div>
			<div class="connect">
				<h3>stay in touch</h3>
				<p>Social media</p>
				<ul>
					<li id="facebook"><a href="http://www.facebook.com">facebook</a>
					</li>
					<li id="twitter"><a href="http://www.twitter.com">twitter</a>
					</li>
				</ul>
			</div>
		</div>
		<div class="section">
			<p>
				&copy; Copyright
			</p>
			<ul>
				<li class="selected"><a href="home">home</a></li>

				<li><a href="about">about</a></li>
				<li><a href="contact">contact</a></li>

				<li class="booking"><a href="signin">signin</a></li>
				<li class="booking"><a href="signup">signup</a></li>
			</ul>

		</div>
	</div>
</body>
</html>