<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:useBean id="user" class="ua.nure.cs.huriev.usermanagement.User" scope="session"></jsp:useBean>
<html>
<head><title>User management. Delete</title></head>
<body>
<form action="<%=request.getContextPath()%>/delete" method="post">
    <p>First name: ${user.firstName}</p>
    <p>Last name: ${user.lastName}</p>
    <p>Date of birth: <fmt:formatDate value="${user.dateOfBirth}" type="date" dateStyle="medium"/></p>
    <input type="submit" name="okButton" value="Ok">
    <input type="submit" name="cancelButton" value="Cancel">
</form>
<c:if test='${request.Scope.error != null}'>
    <script type="text/javascript">
        alert('${request.Scope.error}');
    </script>
</c:if>
</body>
</html>