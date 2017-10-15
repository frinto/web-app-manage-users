/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import users.User;

/**
 *
 * @author Administrator
 */
@WebServlet(name = "AdminServlet", urlPatterns =
{
    "/AdminServlet"
})
public class AdminServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("adminUserSession");
        
        if(user == null)
        {
            response.sendRedirect("login");
        }else
        {
            request.getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);
        }
        
        
        
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException
    {
       
    }
}
