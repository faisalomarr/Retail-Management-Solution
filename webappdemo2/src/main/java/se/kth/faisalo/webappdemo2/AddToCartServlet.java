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
import se.kth.faisalo.webappdemo2.ui.Controller;

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

        // Get userId and cartId from session
        Integer cartId = (Integer) session.getAttribute("cartId");

        // Initialize the controller
        Controller controller = new Controller();

        // Pass the cartId and itemId to the controller to handle the business logic
        controller.addItemToCart(cartId, itemId, response);
    }
}
