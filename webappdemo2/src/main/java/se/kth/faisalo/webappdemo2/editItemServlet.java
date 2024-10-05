package se.kth.faisalo.webappdemo2;

import se.kth.faisalo.webappdemo2.bo.Handler;
import se.kth.faisalo.webappdemo2.db.DbItem;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/updateItemsServlet")
public class editItemServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Iterate over all items and update them based on the input values
        for (int itemId = 1; itemId <= 100; itemId++) { // Assuming max item ID is 100
            String itemName = request.getParameter("name_" + itemId);
            String itemType = request.getParameter("type_" + itemId);
            String itemDescription = request.getParameter("description_" + itemId);
            String itemPrice = request.getParameter("price_" + itemId);
            String itemQuantity = request.getParameter("quantity_" + itemId);

            // If the item exists (i.e., input fields were not empty)
            if (itemName != null && itemType != null) {
                // Convert price and quantity to integers
                int price = Integer.parseInt(itemPrice);
                int quantity = Integer.parseInt(itemQuantity);

                // Call the update method from DbItem class
                Handler.editItem(itemId, itemName, itemType, itemDescription, price, quantity);
            }
        }

        // Redirect back to the edit items page or a success page
        response.sendRedirect("editItems.jsp");
    }
}
