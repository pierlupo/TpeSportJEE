<%--
  Created by IntelliJ IDEA.
  User: Administrateur
  Date: 13/06/2023
  Time: 13:37
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>Register or Log</title>
    <jsp:include page="../includes/head.jsp" />
</head>

<body>

<jsp:include page="../includes/header.jsp" />

<div class="loginOrRegisterFormContainer">

    <h1>Log In</h1>
    <c:if test="${messageError != null}">
        <div>${messageError}</div>
    </c:if>

    <form action="${pageContext.request.contextPath}/user" method="post">
        <input type="hidden" name="action" value="login">
        <div>
            <label>Login</label>
            <input type="text" name="login">
        </div>
        <div>
            <label>Password</label>
            <input type="password" name="password">
        </div>
        <div>
            <button type="submit">Valider</button>
        </div>
    </form>

    <h1>Register</h1>
    <c:if test="${messageError != null}">
        <div>${messageError}</div>
    </c:if>

    <form action="${pageContext.request.contextPath}/user" method="post">
        <input type="hidden" name="action" value="register">
        <div>
            <label>Register</label>
            <input type="text" name="register">
        </div>
        <div>
            <label>Password</label>
            <input type="password" name="password">
        </div>
        <div>
            <button type="submit">Valider</button>
        </div>
    </form>

</div>
</body>
</html>
