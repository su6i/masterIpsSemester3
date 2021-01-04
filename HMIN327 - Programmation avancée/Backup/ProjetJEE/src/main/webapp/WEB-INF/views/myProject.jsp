<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Getion de territoir</title>
</head>
<body>
	Test OK
	<div>
		<f:form modelAttribute="departementForm" method="post"
			action="chargerDepartement">
			<table>
				<tr>
					<td>Numero Departement:</td>
					<td><f:input path="numDep" /></td>
					<td><f:errors path="numDep"></f:errors></td>
				</tr>
				<tr>
					<td><input type="submit" value="OK" /></td>
				</tr>
			</table>
		</f:form>
	</div>
	<c:if test="${not empty departementForm.departement}">
		<div>
			<table>
				<tr>
					<td>Nom du departement:</td>
					<td>${departementForm.departement.nomDep}</td>
				</tr>
				<tr>
					<td>Chef Lieu:</td>
					<td>${departementForm.departement.chefLieu}</td>
				</tr>
				<tr>
					<td>Region:</td>
					<td>${departementForm.departement.region}</td>
				</tr>
			</table>
		</div>
	</c:if>
	<div>
		<f:form action="/projet/">
			<input type="submit" value="Retour" />
		</f:form>
	</div>
</body>
</html>