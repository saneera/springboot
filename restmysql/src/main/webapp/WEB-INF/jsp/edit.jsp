<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<<jsp:include page="header.jsp"></jsp:include>

<div class="container">

	<h3>Employee</h3>
	<form action='/employee/update/${employee.id}' method='post'>

		<table class='table table-hover table-responsive table-bordered'>

			<tr>
				<td><b>Name</b></td>
				<td><input type='text' name='name' class='form-control'
					value="${employee.name}" /></td>
			</tr>

			<tr>
				<td><b>Surname</b></td>
				<td><input type='text' name='surname' class='form-control'
					value="${employee.surname}" /></td>
			</tr>


			<input type='hidden' id='id' rows='5' class='form-control' name='id'
				value="${employee.id}" />
			<tr>
				<td></td>
				<td>
					<button type="submit" class="btn btn-primary">Save</button>
				</td>
			</tr>

		</table>
	</form>


</div>

<jsp:include page="footer.jsp"></jsp:include>