package se.kth.faisalo.webappdemo2;

import se.kth.faisalo.webappdemo2.bo.Handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/AddToStockServlet")
public class AddToStockServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form data
        String name = request.getParameter("name");
        String type = request.getParameter("type");
        String description = request.getParameter("description");
        int price = Integer.parseInt(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        // Call Handler to add the item to the database
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
}
