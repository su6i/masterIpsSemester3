<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<!DOCTYPE html>
<html>
<head>
<title>Home</title>
</head>
<body>
	<h1>Hello man!</h1>

	<P>Big brother's watching you</P>
	<div>
		<f:form action="index">
			<input type="submit" value="Recherches" />
		</f:form>
	</div>
</body>
</html>
