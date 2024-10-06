package se.kth.faisalo.webappdemo2;

import se.kth.faisalo.webappdemo2.ui.Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/AddToStockServlet")
public class AddToStockServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form data
        String name = request.getParameter("name");
        String type = request.getParameter("type");
        String description = request.getParameter("description");
        int price = Integer.parseInt(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        // Initialize the controller
        Controller controller = new Controller();

        // Delegate the logic to the controller
        controller.addItemToStock(name, type, description, price, quantity, response);
    }
}
