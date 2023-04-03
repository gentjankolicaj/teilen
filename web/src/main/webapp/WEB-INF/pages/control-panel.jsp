<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!-- Website Template by freewebsitetemplates.com -->
<html>
<head>
<meta charset="UTF-8">
<title>Account</title>

<link href="<c:url value="/css/control-panel.css" />" rel="stylesheet"
	type="text/css" />
</head>

</head>
<body>

	<div id="body">

		<div style="width: 30%; float: left;">

			<div class="panel">

				<div class="links" style="margin-bottom: 80px;">
				     <h3>
				     Login as: ${user.firstName} ${user.lastName}, id: ${user.id }.
				     </h3>

					<h3>
						<a href="<c:url value="/cpanel/out"/>">- Signout</a>
					</h3>
				</div>


				</div>
		</div>

		<div style="width: 70%; float: right;">



			



		</div>

	</div>

</body>
</html>