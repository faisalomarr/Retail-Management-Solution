package se.kth.faisalo.webappdemo2;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import se.kth.faisalo.webappdemo2.ui.Controller;

import java.io.IOException;

@WebServlet("/packOrderServlet")
public class PackOrderServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the cart ID from the request
        int cartid = Integer.parseInt(request.getParameter("order"));

        // Initialize the controller
        Controller controller = new Controller();

        // Pass the cart ID and response to the controller to handle the business logic
        controller.packOrder(cartid, response);
    }
}
