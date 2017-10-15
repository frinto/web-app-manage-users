/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
@WebServlet(name = "LoginServlet", urlPatterns =
{
    "/LoginServlet"
})
public class LoginServlet extends HttpServlet
{

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        
        String action = request.getParameter("action");
        
        if(session.getAttribute("regularUserSession")!= null && (action == null))
        {
            response.sendRedirect("user");
            return;
        }
        if(session.getAttribute("adminUserSession") != null && (action==null))
        {
            response.sendRedirect("admin");
            return;
        }
        if(action != null)
        {
            session.invalidate();
            request.getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        }
        else
        {
            request.getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        }
        
        
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException
    {
        String path = getServletContext().getRealPath("/WEB-INF/users.txt");
        // to read file
        BufferedReader br = new BufferedReader(new FileReader(new File(path)));

        String action = request.getParameter("action");
        String username = request.getParameter("usernameTextField");
        String password = request.getParameter("passwordTextField");
        String line = null;

        while ((line = br.readLine()) != null)
        {

            String[] values = line.split(",");

            String usernameFromTextFile = values[0];
            String passwordFromTextFile = values[1];
            int isAdminFromTextFile = Integer.parseInt(values[2]);

            if (usernameFromTextFile.equals(username) && passwordFromTextFile.equals(password))
            {
                if (isAdminFromTextFile == 0)
                {

                    HttpSession session = request.getSession();
                    User user = new User(username, password, 0);
                    session.setAttribute("regularUserSession", user);

                    response.sendRedirect("user");
                    return;
                }
                if (isAdminFromTextFile == 1)
                {
                    HttpSession session = request.getSession();
                    User user = new User(username, password, 1);
                    session.setAttribute("adminUserSession", user);
                    
                    response.sendRedirect("admin");
                    return;
                }
            }
        }
        request.setAttribute("errorLogin", "Invalid Username Or Password Try Again");
        request.getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        br.close();

    }

}
