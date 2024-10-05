<%@ page import="se.kth.faisalo.webappdemo2.ui.CartInfo" %>
<%@ page import="java.util.List" %>
<%@ page import="se.kth.faisalo.webappdemo2.bo.Handler" %>
<%@ page import="se.kth.faisalo.webappdemo2.bo.Cart" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cart List</title>
    <h1>Page only for workers</h1>
        <%
        // Fetch the list of carts from the database
        List<CartInfo> cartInfos = Handler.getCarts();
    %>

    <table border="1">
        <tr>
            <th>Cart Id</th>
            <th>User Id</th>
            <th>Status</th>
            <th>Packed or Unpacked</th>
            <th>View Cart Items</th>
        </tr>
        <% for (CartInfo cartInfo : cartInfos) { %>
        <tr>
            <td><%= cartInfo.getIdCart() %></td>
            <td><%= cartInfo.getIdUser() %></td>
            <td><%= cartInfo.getStatus() %></td>
            <td><%= cartInfo.getPackedstatus() %></td>
            <td>
                <%
                    // Only show the link if the cart is FINALIZED and UNPACKED
                    if (Cart.Status.FINALIZED.equals(cartInfo.getStatus()) && Cart.Packedstatus.UNPACKED.equals(cartInfo.getPackedstatus())) {
                %>
                <a href="viewItemsForLagerpersonal.jsp?cartId=<%= cartInfo.getIdCart() %>">View Items</a>
                <% } %>
            </td>
        </tr>
        <% } %>
    </table>
</html>
