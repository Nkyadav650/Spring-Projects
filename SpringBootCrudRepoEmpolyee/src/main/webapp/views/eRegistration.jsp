<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h1 Style="color: pink">Welcome to Employee Registration Page</h1>
<body bgcolor="pink">
	<form:form action="/success">
		<table>
			<tr>
				<td>ID:</td>
				<td><form:input path="id" /></td>
			</tr>
			<tr>
				<td>NAME:</td>
				<td><form:input path="name" /></td>
			</tr>
			<tr>
				<td>PASSWORD:</td>
				<td><form:input path="password" /></td>
			</tr>
			<tr>
				<td>EMAIL:</td>
				<td><form:input path="email" /></td>
			</tr>
			<tr>
				<td>MOBILE NUMBER:</td>
				<td><form:input path="mobile" /></td>
			</tr>
			<tr>
				<td>Please select your gender:</td>
				<td><form:select path="gender">
						<form:option value="" label="Select Gender" />
						<form:options items="${genders}" />
					</form:select></td>
			</tr>
			<tr>
				<td>ADDRESS:</td>
				<td><form:input path="address" /></td>
			</tr>
				<tr>
				<td>Please select your City:</td>
				<td><form:select path="city">
						<form:option value="" label="Select Gender" />
						<form:options items="${city}" />
					</form:select></td>
			</tr>
			<tr>
				<td>Please select your State:</td>
				<td><form:select path="state">
						<form:option value="" label="Select Gender" />
						<form:options items="${state}" />
					</form:select></td>
			</tr>
			<tr>
				<td>COUNTRY:</td>
				<td><form:select path="country">
						<form:option value="" label="select country" />
						<form:option value="" items="${country}" />
					</form:select></td>
			</tr>
			<tr>
				<td><input type="submit" value="save"></td>
			</tr>
		</table>
	</form:form>
</body>