<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h1>Employee View Page</h1>
<table border="2" cellpadding="3" width="70%">
<tr>
<th>Id</th>
<th>Name</th>
<th>Password</th>
<th>Email</th>
<th>Mobile number</th>
<th>Gender</th>
<th>Address</th>
<th>City</th>
<th>State</th>
<th>country</th>
</tr>
<tr>
<c:forEach var="emp" items="${employee}">
<td></td>
</c:forEach>
</tr>
</table>