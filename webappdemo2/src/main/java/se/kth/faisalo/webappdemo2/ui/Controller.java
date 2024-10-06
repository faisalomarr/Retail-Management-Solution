package se.kth.faisalo.webappdemo2.ui;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import se.kth.faisalo.webappdemo2.bo.Handler;
import se.kth.faisalo.webappdemo2.bo.User;

import java.io.IOException;
import java.io.PrintWriter;

public class Controller {

    // Method to handle adding an item to the cart
    public void addItemToCart(int cartId, int itemId, HttpServletResponse response) throws IOException, IOException {
        boolean success = Handler.addToCart(cartId, itemId);

        if (success) {
            // Redirect to the cart page if successful
            response.sendRedirect("cart.jsp");
        } else {
            // Handle the failure case by returning an error page
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html><body>");
            out.println("<h2>Failed to add item to cart.</h2>");
            out.println("<a href='cart.jsp'>Try again</a>");
            out.println("</body></html>");
        }
    }

    // Method to handle adding an item to stock
    public void addItemToStock(String name, String type, String description, int price, int quantity, HttpServletResponse response) throws IOException {
        // Call the Handler to add the item to the stock
        boolean success = Handler.addItemToStock(name, type, description, price, quantity);

        // Respond with success or failure message
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        if (success) {
            out.println("<html><body>");
            out.println("<h2>Item added successfully!</h2>");
            out.println("<a href='AddItemToStock.jsp'>Add another item</a>");
            out.println("<a href='viewitems.jsp'>View items in stock</a>");
            out.println("</body></html>");
        } else {
            out.println("<html><body>");
            out.println("<h2>Failed to add item. Please try again.</h2>");
            out.println("<a href='AddItemToStock.jsp'>Try again</a>");
            out.println("</body></html>");
        }
    }

    public void editItem(int itemId, String name, String type, String description, int price, int quantity, HttpServletResponse response) throws IOException {
        // Call the Handler to edit the item
        boolean success = Handler.editItem(itemId, name, type, description, price, quantity);
    }

    public void handleLogin(String username, String password, HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserInfo userInfo = Handler.ValidUser(username, password, Handler.getUsers());

        if (userInfo != null) {
            // Successful login
            int cartIdFromUser = Handler.getCartIdFromUser(userInfo);

            // Set session attributes
            HttpSession session = request.getSession();
            session.setAttribute("userId", userInfo.getUserId());
            session.setAttribute("role", userInfo.getRole());
            session.setAttribute("cartId", cartIdFromUser);

            // Prepare the response
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();

            if (userInfo.getRole() == User.Role.KUND) {
                // Show customer-specific view
                out.println("<html><body>");
                out.println("<h2>Welcome, " + username + "!</h2>");
                out.println("<a href='cart.jsp'>Go to cart</a>");
                out.println("<a href='additems.jsp'>Add items</a>");
                out.println("<a href='viewitems.jsp'>View Iems</a>");
                out.println("</body></html>");
            } else if (userInfo.getRole() == User.Role.LAGERPERSONAL) {
                // Show warehouse personnel-specific view
                out.println("<a href='PersonalPage.jsp'>Personal Page</a>");
            } else if (userInfo.getRole() == User.Role.ADMIN) {
                // Show admin-specific view
                out.println("<html><body>");
                out.println("<h2>Welcome Admin, " + username + "!</h2>");
                out.println("<a href='viewitems.jsp'>View items in stock</a>");
                out.println("<a href='AddItemToStock.jsp'>Add new items to stock</a>");
                out.println("<a href='editItems.jsp'>Edit items</a>");
                out.println("</body></html>");
            }

            // Logging info for debugging
            System.out.println("Cart ID: " + cartIdFromUser);
            System.out.println("User ID: " + userInfo.getUserId());

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

    public void packOrder(int cartid, HttpServletResponse response) throws IOException {
        boolean success = Handler.packOrder(cartid);

        // Set the content type of the response
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        if (success) {
            // Redirect to success page
            response.sendRedirect("successPacked.jsp");
        } else {
            // Handle error and print message or redirect to an error page
            response.getWriter().println("Failed to pack the order.");
        }
    }


}