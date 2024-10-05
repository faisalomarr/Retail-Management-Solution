package se.kth.faisalo.webappdemo2;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import se.kth.faisalo.webappdemo2.bo.Handler;

import java.io.IOException;

@WebServlet("/packOrderServlet")
public class PackOrderServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int cartid = Integer.parseInt(request.getParameter("order"));

        // Update order status to 'packed'
        boolean success = Handler.packOrder(cartid);

        if (success) {
            // Redirect to success page
            response.sendRedirect("successPacked.jsp");
        } else {
            // Handle error and print message or redirect to an error page
            response.getWriter().println("Failed to pack the order.");
        }
    }
}
