<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML>
<!-- Website Template by freewebsitetemplates.com -->
<html>
<head>
<meta charset="UTF-8">
<title>Contact</title>

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
			<div class="section">
				<div class="contact">
					<h2>Contact :</h2>
					<p>
						Website template owner <a
							href="http://www.freewebsitetemplates.com/">Free Website
							Templates</a>.
					</p>
					<br> <br>
					<p>
						App developer email : <a href="mailto:gentjankolicaj@yahoo.com"> gentjankolicaj@yahoo.com</a>.
					</p>
					<p>
						App developer github : <a href="https://github.com/gentjankolicaj">gentjan
							kolicaj</a>.
					</p>

					<form action="index">
						<label for="name"> <span>name</span> <input type="text"
							name="name" id="name">
						</label> <label for="email"> <span>email</span> <input type="text"
							name="email" id="email">
						</label> <label for="subject"> <span>subject</span> <input
							type="text" name="subject" id="subject">
						</label> <label for="message"> <span>comment</span> <textarea
								name="message" id="message"></textarea>
						</label> <input type="submit" name="send" id="send" value="">
					</form>
				</div>
			</div>
			<div class="sidebar">
				<div class="contact">
				
				</div>
				<div class="featured">
					<h3>features</h3>
					<ul>
						<li></li>
						<li></li>
						<li></li>
						<li></li>
						<li></li>
					</ul>
				</div>
			</div>
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
			<p>&copy; Copyright</p>
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