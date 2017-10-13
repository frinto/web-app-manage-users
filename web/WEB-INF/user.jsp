<%-- 
    Document   : user
    Created on : Oct 13, 2017, 1:16:02 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>user</title>
    </head>
    <body>
        <h1>Your Account</h1>
        <p>
            Hello ${usernameELAttribute}<br>
            <a href="login?action=logout">Logout</a>
        </p> <br>

        <h1>Change Your Password</h1><br>

        <form action="user?action=changePassword" method="post">
            New Password: <input type="password" name ="changePasswordTextField" value=""/>
            <input type="submit" value="Change Password">
        </form>
    </body>
    ${successPassChangeMsg}
    ${errorPassChangeFailed}
</html>
