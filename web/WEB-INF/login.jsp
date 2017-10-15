<%-- 
    Document   : login
    Created on : Oct 13, 2017, 1:06:58 PM
    Author     : Administrator
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>login</title>
        <link rel="stylesheet" href="styles/main.css" type="text/css"/>
    </head>
    <body>
        <h1>Manager Users Login</h1>

        <form action="login?action=login" method="post">
        	Username: <input type="text" name="usernameTextField" value=""/><br>
        	Password: <input type="password" name="passwordTextField" value=""/><br>
        	<input type="submit" value="Login">
        </form>
        ${errorLogin}
    </body>
</html>
