<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="header.jsp"></jsp:include>

<h3>Employee Registration</h3>
<br>
<form action='/employee/update' method='post'>

	<table class='table table-hover table-responsive table-bordered'>

		<tr>
			<td><b>Name</b></td>
			<td><input type='text' name='name' class='form-control' required /></td>
		</tr>

		<tr>
			<td><b>Surname</b></td>
			<td><input type='text' name='surname' class='form-control'
				required /></td>
		</tr>


		<tr>
			<td></td>
			<td>
				<button type="submit" class="btn btn-primary">Save</button>
			</td>
		</tr>

	</table>

</form>



<h3>List Of Employee</h3>
<br>
<table class="table table-hover">

	<thead>
		<tr>
			<th><b>Name</b></th>
			<th><b>Surname</b></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${list}" var="employee">
			<tr>
				<td><c:out value="${employee.name}"></c:out></td>
				<td><c:out value="${employee.surname}"></c:out></td>

				<td><a href="/employee/edit/${employee.id}">
						<button type="submit" class="btn btn-primary">Edit
							Employee</button>
				</a></td>
				<td><a href="/employee/delete/${employee.id}">
						<button type="submit" class="btn btn-primary">Delete
							Employee</button>
				</a></td>
			</tr>

		</c:forEach>
	</tbody>
</table>
</div>

<jsp:include page="footer.jsp"></jsp:include>