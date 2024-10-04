package se.kth.faisalo.webappdemo2;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import se.kth.faisalo.webappdemo2.bo.Handler;
import se.kth.faisalo.webappdemo2.bo.User;
import se.kth.faisalo.webappdemo2.ui.UserInfo;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "createServlet", value = "/create-servlet")
public class CreateServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Retrieve form data
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String roleString = request.getParameter("role");

        // Convert roleString to the User.Role enum
        User.Role role = User.Role.valueOf(roleString.toUpperCase());

        // Create a UserInfo object (UI layer)
        UserInfo userInfo = new UserInfo(username, password, -10,role);

        // Call the business logic to create the user
        boolean userCreated = Handler.createUser(userInfo);

        // Set up the response type
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        if (userCreated) {
            // Successful user creation
            out.println("<html><body>");
            out.println("<h2>User " + username + " created successfully!</h2>");
            out.println("<a href='index.jsp'>Go to login</a>");
            out.println("</body></html>");
        } else {
            // Failed user creation
            out.println("<html><body>");
            out.println("<h2>Failed to create user. Please try again.</h2>");
            out.println("<a href='createUser.jsp'>Try again</a>");
            out.println("</body></html>");
        }
    }

}