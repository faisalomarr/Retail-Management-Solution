package se.kth.faisalo.webappdemo2;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import se.kth.faisalo.webappdemo2.bo.Handler;
import se.kth.faisalo.webappdemo2.bo.User;
import se.kth.faisalo.webappdemo2.db.DBuser;
import se.kth.faisalo.webappdemo2.ui.UserInfo;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form data
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserInfo userInfo=Handler.ValidUser(username,password, Handler.getUsers());

        if (userInfo!=null) {
            // Successful login
            HttpSession session = request.getSession();
            session.setAttribute("userId",userInfo.getUserId());
            session.setAttribute("role",userInfo.getRole());
            session.setAttribute("cartId", userInfo.getCartId());

            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html><body>");
            out.println("<h2>Welcome, " + username + "!</h2>");
            out.println("</body></html>");
            out.println("<a href='cart.jsp'>Go to cart</a>");
            out.println("</body>or</html>");
            out.println("<a href='additems.jsp'>add items</a>");

            if(userInfo.getRole() == User.Role.ADMIN){
                out.println("<a href='AdminPage.jsp'>Admin Page</a>");
            }
            if(userInfo.getRole() == User.Role.LAGERPERSONAL){
                out.println("<a href='PersonalPage.jsp'>Personal Page</a>");
            }

            System.out.println("halååååååååå här cartid" + userInfo.getCartId());
            System.out.println("halååååååååå här userId" + userInfo.getUserId());



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
