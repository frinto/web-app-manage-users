/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
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
@WebServlet(name = "UserServlet", urlPatterns =
{
    "/UserServlet"
})
public class UserServlet extends HttpServlet
{

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException
    {
        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("regularUserSession");

        if (user == null)
        {
            response.sendRedirect("login");
        } else
        {
            String username = user.getUsername();
            request.setAttribute("usernameELAttribute", username);
            request.getServletContext().getRequestDispatcher("/WEB-INF/user.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException
    {
        String newPassword = request.getParameter("changePasswordTextField");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("regularUserSession");

        String path = getServletContext().getRealPath("/WEB-INF/users.txt");
        // to write to files
        BufferedWriter bw = new BufferedWriter(new FileWriter(new File(path),true));
        PrintWriter pw = new PrintWriter(bw);

        if (newPassword.equals(""))
        {
            request.setAttribute("errorPassChangeFailed", "Password value must be provided for change");
            request.getServletContext().getRequestDispatcher("/WEB-INF/user.jsp").forward(request, response);
        } else
        {
            String userAllValues = user.getUsername().trim() + "," + newPassword.trim() + "," + Integer.toString(user.isIsAdmin()).trim();
            pw.print("\n"+userAllValues.trim());
            pw.close();
            bw.close();
            
            request.setAttribute("successPassChangeMsg", "Password updated");
            request.getServletContext().getRequestDispatcher("/WEB-INF/user.jsp").forward(request, response);
        }
        

    }

}
