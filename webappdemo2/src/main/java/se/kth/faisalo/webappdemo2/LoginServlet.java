package se.kth.faisalo.webappdemo2;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import se.kth.faisalo.webappdemo2.db.DBuser;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form data
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Hardcoded validation (you should replace this with database validation)
        if (DBuser.CheckUser(username, password)) {
            // Successful login
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html><body>");
            out.println("<h2>Welcome, " + username + "!</h2>");
            out.println("</body></html>");
        } else {
            // Failed login
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html><body>");
            out.println("<h2>Login failed. Invalid username or password.</h2>");
            out.println("<a href='index.jsp'>Try again</a>");
            out.println("</body></html>");
        }
    }
}
