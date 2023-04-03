<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!-- Website Template by freewebsitetemplates.com -->
<html>
<head>
<meta charset="UTF-8">
<title>Signin</title>

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
					<h2>user signin</h2>
					<form action="signin/auth" method="get">

						<spring:bind path="authData.email">
							<label for="name"> <span>email</span> <input type="text"
								id="name" required name="${status.expression}"
								value="${status.value}"></label>
						</spring:bind>

						<spring:bind path="authData.password">
							<label for="email"> <span>password</span> <input
								type="password" id="email" required name="${status.expression}"
								value="${status.value}"></label>
						</spring:bind>

						<br> <br>
						<div id="div-submit">
							<button type="submit" id="button-submit">Signin</button>
						</div>
					</form>
				</div>
			</div>

			<div class="sidebar">
				<div class="contact"></div>
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