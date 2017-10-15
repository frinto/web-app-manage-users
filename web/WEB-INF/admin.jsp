<%-- 
    Document   : admin
    Created on : Oct 13, 2017, 1:07:28 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>admin</title>
        <link rel="stylesheet" href="styles/main.css" type="text/css"/>
    </head>
    <body>
        <h1>Admin Page</h1><br>
        <a href="admin?action=refresh">Refresh</a>
        <a href="login?action=logout">Logout</a><br>

        <h1>Add User</h1><br>

        <form action="admin?action=addUser" method="post">
            New username: <input type="text" name="newUsernameTextField" value=""/> <br>
            password: <input type="password" name="newPasswordTextField"value=""/>  <br>
            <input type="submit" value="Add User"/>
        </form> <br>

        <h1>List of Users</h1><br>

        <table>
        	<tr>
        		<th>Username</th>
        		<th>Password</th>
        		<th>Delete</th>
        	</tr>
        	<tr>
        	     <td>test</td>
        	     <td>test</td>
        	     <td>test<input type="radio" name="test"/>
                     </td>
        	</tr>
        </table>










    </body>
</html>
