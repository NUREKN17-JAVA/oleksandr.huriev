<jsp:useBean id="user" class="ua.nure.cs.huriev.usermanagement.User"
             scope="session"></jsp:useBean>
<html>
<head>
    <title>User management: add</title>
</head>
<body>
<form action="<%=request.getContextPath()%>>/add" method="post">
    <input type="hidden" name="id" value="${user.id} }"> First
    name <input type="text" name="firstName" value=""><br>
    Last name <input type="text" name="lastName" value=""><br>
    Date of birth <input type="text" name="date" value=""> <input
        type="submit" name="okButton" value="Confirm"> <input
        type="submit" name="cancelButton" value="Cancel">
</form>
<c:if test="${requestScope.error != null}">
    <script>
        alert('${requestScope.error}');
    </script>
</c:if>
</body>
</html>