<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!-- Website Template by freewebsitetemplates.com -->
<html>
<head>
<meta charset="UTF-8">
<title>Account</title>

<link href="<c:url value="/css/account.css" />" rel="stylesheet"
	type="text/css" />
</head>

</head>
<body>

	<div id="account-body">

		<div style="width: 30%; float: left;">

			<div class="panel">

				<div class="links" style="margin-bottom: 80px;">
				     <h3>
				     Login as: ${user.firstName} ${user.lastName}, id: ${user.id }.
				     </h3>

					<h3>
						<a href="<c:url value="/signout"/>">- Signout</a>
					</h3>
				</div>


				<div class="members" style="color: white;">


					<div class="div2">
						<section>
							<!--for demo wrap-->
							<h1>Members</h1>
							<div class="tbl-header">
								<table cellpadding="0" cellspacing="0" border="0">
									<thead>
										<tr>
											<th>Id</th>
											<th>Name</th>
											<th>Surname</th>
										</tr>
									</thead>
								</table>
							</div>
							<div class="tbl-content">
								<table cellpadding="0" cellspacing="0" border="0">
									<tbody>

										<c:forEach var="user" items="${uList}">
											<tr>
												<td><c:out value="${user.id}" /></td>
												<td><c:out value="${user.firstName}" /></td>
												<td><c:out value="${user.lastName}" /></td>

											</tr>
										</c:forEach>

									</tbody>
								</table>
							</div>
						</section>



					</div>

				</div>
			</div>

		</div>

		<div style="width: 70%; float: right;">


			<div class="message-div" style="height: 50px; margin-bottom: 130px;">

					<form action="sent" method="get">
						
						<ul>
							<li><label for="name"> <span>Receiver :</span>
				
								<select name="receiverId">
								<c:forEach items="${uList }" var="receiver">
								 <option value="${receiver.id }">ID:[${receiver.id}] - ${receiver.firstName } ${receiver.lastName }</option>
								</c:forEach>
                                </select></label></li>
                               
                              
							<li><label for="email"> <span>Message :</span> <textarea
									rows="7" cols="55" name="message"> </textarea></label></li>
							<li><button type="submit" class="button-submit2">Send</button></li>
                         </ul>
					</form>
				</div>



				<div class="messsages">

					<div class="sent" style="width: 50%; float: left;">
						<section>
							<h1>Sent :</h1>
							<div class="tbl-header">
								<table cellpadding="0" cellspacing="0" border="0">
									<thead>
										<tr>
											<th>To</th>
											<th>Message</th>
										</tr>
									</thead>
								</table>
							</div>
							<div class="tbl-content1">
								<table cellpadding="0" cellspacing="0" border="0">
									<tbody>

										<c:forEach var="sentMessage" items="${sentMessages}">
											<tr>
												<td><span>${sentMessage.receiver.firstName}
														${sentMessage.receiver.lastName}</span></td>
												<td><span>${sentMessage.message}</span></td>
											</tr>
										</c:forEach>

									</tbody>
								</table>
							</div>
						</section>

					</div>

					<div class="received" style="width: 50%; float: right;">
						<section>
							<h1>Received :</h1>
							<div class="tbl-header">
								<table cellpadding="0" cellspacing="0" border="0">
									<thead>
										<tr>
											<th>From</th>
											<th>Message</th>
										</tr>
									</thead>
								</table>
							</div>
							<div class="tbl-content1">
								<table cellpadding="0" cellspacing="0" border="0">
									<tbody>

										<c:forEach var="receivedMessage" items="${receivedMessages}">
											<tr>
												<td><span>${receivedMessage.sender.firstName}
														${receivedMessage.sender.lastName}</span></td>
												<td><span>${receivedMessage.message}</span></td>
											</tr>
										</c:forEach>

									</tbody>
								</table>
							</div>
						</section>





					</div>

				</div>

			</div>



		</div>

	</div>

</body>
</html>