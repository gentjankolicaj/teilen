<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE HTML>
<!-- Website Template by freewebsitetemplates.com -->
<html>
<head>
<meta charset="UTF-8">
<title>Signup</title>
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
				<div class="booking">
					<h2>Create an organization</h2>
					<h3>Please fill in details below :</h3>
					<form action="signup/new" method="get">


						<div class="form1">

							<spring:bind path="user.firstName">
								<label for="fname"> <span>first name *</span> <input
									type="text" name="${status.expression}" value="${status.value}"
									id="fname" required>
								</label>
							</spring:bind>

							<spring:bind path="user.lastName">
								<label for="lname"> <span>last name *</span> <input
									type="text" name="${status.expression}" value="${status.value}"
									id="lname" required>
								</label>
							</spring:bind>

							<spring:bind path="userContact.email">
								<label for="email3"> <span>email *</span> <input
									type="text" value="<c:out value="${status.value}"/>"
									name="<c:out value="${status.expression}"/>" id="email3"
									required>
								</label>
							</spring:bind>

							<spring:bind path="userContact.telephone">
								<label for="phone"> <span>phone number</span> <input
									type="text" name="${status.expression}" value="${status.value}"
									id="phone">
								</label>
							</spring:bind>

							<spring:bind path="userContact.postalCode">
								<label for="email3"> <span>postal code</span> <input
									type="text" name="${status.expression}" value="${status.value}"
									id="email3">
								</label>
							</spring:bind>


							<spring:bind path="userAdress.street">
								<label for="lname"> <span>address *</span> <input
									type="text" name="${status.expression}" value="${status.value}"
									id="lname" required>
								</label>
							</spring:bind>

							<spring:bind path="userAdress.city">
								<label for="email3"> <span>city</span> <input
									type="text" name="${status.expression}" value="${status.value}"
									id="email3">
								</label>
							</spring:bind>

							<spring:bind path="user.username">
								<label for="fname"> <span>Username *</span> <input
									type="text" name="${status.expression}" value="${status.value}"
									id="fname" required>
								</label>
							</spring:bind>
							<spring:bind path="passwordModel.password">
								<label for="email3"> <span>password *</span> <input
									type="password" name="${status.expression}" value="${status.value}"
									id="email3" required>
								</label>
							</spring:bind>

							<spring:bind path="passwordModel.rePassword">
								<label for="phone"> <span>re-password *</span> <input
									type="password" name="${status.expression}" value="${status.value}"
									id="phone" required>
								</label>
							</spring:bind>


						</div>

						<div class="form1">
							<div>

								<spring:bind path="organization.name">
									<label for="fname"> <span>Organization name *</span> <input
										type="text" name="${status.expression}"
										value="${status.value}" id="fname" required>
									</label>
								</spring:bind>

								<spring:bind path="organization.adress">
									<label for="fname"> <span>Organization adress *</span>
										<input type="text" name="${status.expression}"
										value="${status.value}" id="fname" required>
									</label>
								</spring:bind>

								<spring:bind path="organization.url">
									<label for="lname"> <span>organization url</span> <input
										type="text" name="${status.expression}"
										value="${status.value}" id="lname">
									</label>
								</spring:bind>

								<spring:bind path="organization.organizationEmail">
									<label for="email3"> <span>organization email *</span>
										<input type="text" name="${status.expression}"
										value="${status.value}" id="email3" required>
									</label>
								</spring:bind>

								<spring:bind path="organization.phone">
									<label for="phone"> <span>organization phone</span> <input
										type="text" name="${status.expression}"
										value="${status.value}" id="phone">
									</label>
								</spring:bind>


								<span>Country</span> <select name="countryId" required>
									<c:forEach var="country" items="${countries}">
										<option value="${country.id}">${country.countryName}</option>
									</c:forEach>

								</select>
							</div>

							<spring:bind path="organization.description">
								<label for="message2"> <span>description</span> <textarea
										name="${status.expression}" value="${status.value}"
										id="message2" cols="30" rows="10"></textarea>
								</label>
							</spring:bind>

						</div>

						<br>
						<div id="div-submit">
							<button type="submit" id="button-submit2">Sigup</button>
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