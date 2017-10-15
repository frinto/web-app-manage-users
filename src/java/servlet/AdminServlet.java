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

        if (user == null)
        {
            response.sendRedirect("login");
        } else
        {
            request.getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException
    {
        String newUsername = request.getParameter("newUsernameTextField");
        String newPassword = request.getParameter("newPasswordTextField");

        String path = getServletContext().getRealPath("/WEB-INF/users.txt");
        // to write files
        BufferedWriter bw = new BufferedWriter(new FileWriter(new File(path), true));
        PrintWriter pw = new PrintWriter(bw);

        // to read files
        BufferedReader br = new BufferedReader(new FileReader(new File(path)));
        String line = null;

        while ((line = br.readLine()) != null)
        {

            String[] values = line.split(",");

            String usernameFromTextFile = values[0];
            String passwordFromTextFile = values[1];
            int isAdminFromTextFile = Integer.parseInt(values[2]);

            if (newUsername.equals(usernameFromTextFile))
            {
                request.setAttribute("errorMsgAddUser", "Username already in use, please choose another");
                request.getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);
            }

        }

        String userAllValues = newUsername.trim() + "," + newPassword.trim() + "," + "0".trim();
        pw.print("\n" + userAllValues.trim());
        pw.close();
        bw.close();
        br.close();
        request.setAttribute("successMsgAddUser", "New User Added");
        request.getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);

        

    }
}
