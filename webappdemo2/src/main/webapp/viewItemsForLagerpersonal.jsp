<%@ page import="se.kth.faisalo.webappdemo2.ui.ItemInfo" %>
<%@ page import="java.util.List" %>
<%@ page import="se.kth.faisalo.webappdemo2.bo.Handler" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cart Items</title>
</head>
<body>
<h1>Items in Cart</h1>

<%
    // Get the cartId from the URL parameter
    int cartId = Integer.parseInt(request.getParameter("cartId"));

    // Fetch the list of items in the cart using the cartId
    List<ItemInfo> itemInfos = Handler.getItemsInCart(cartId);
%>

<table border="1">
    <tr>
        <th>Item ID</th>
        <th>Name</th>


    </tr>
    <% for (ItemInfo itemInfo : itemInfos) { %>
    <tr>
        <td><%= itemInfo.getName() %></td>
        <td><%= itemInfo.getDescription() %></td>
    </tr>
    <% } %>
</table>

<a href="PersonalPage.jsp">Back to Personal Page</a><!-- Link to go back to the cart list -->
<td>
    <!-- Knapp för att packa ordern -->
    <form action="packOrderServlet" method="post">
        <input type="hidden" name="order" value="<%=cartId%>">
        <input type="submit" value="Pack Order">
    </form>
</td>
<a href="successPacked.jsp">Pack this order</a> <!-- Link to go back to the cart list -->

</body>
</html>
