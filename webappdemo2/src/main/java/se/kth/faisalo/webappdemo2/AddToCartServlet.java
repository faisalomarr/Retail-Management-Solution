package se.kth.faisalo.webappdemo2;

import se.kth.faisalo.webappdemo2.bo.Handler;
import se.kth.faisalo.webappdemo2.bo.Item;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import se.kth.faisalo.webappdemo2.db.Dbcart;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/addToCartServlet")
public class AddToCartServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the itemId from the form submission
        int itemId = Integer.parseInt(request.getParameter("itemId"));

        // Retrieve the user's session
        HttpSession session = request.getSession();

        // Get userId from session
        Integer userId = (Integer) session.getAttribute("userId");

        // Retrieve the cartId from session
        Integer cartId = (Integer) session.getAttribute("cartId");


        // Add the item to the cart using the Dbcart.addToCart() method
        boolean success = Dbcart.addToCart(cartId, itemId);

        if (success) {
            // Redirect the user to the cart page to view their cart
            response.sendRedirect("cart.jsp");
        } else {
            // Handle the failure case, such as showing an error message
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html><body>");
            out.println("<h2>Login failed. not able to put in cart.</h2>");
            out.println("<a href='cart.jsp'>Try again</a>");
            out.println("</body></html>");
        };
    }
}
