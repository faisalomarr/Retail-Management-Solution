<%@ page import="se.kth.faisalo.webappdemo2.ui.ItemInfo" %>
<%@ page import="java.util.List" %>
<%@ page import="se.kth.faisalo.webappdemo2.bo.Handler" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Items</title>
</head>
<body>
<%
    // Fetch the list of items from the database
    List<ItemInfo> itemInfos = Handler.getItems();
%>

<table border="1">
    <tr>
        <th>Name</th>
        <th>Description</th>
        <th>Action</th> <!-- Add this column for the Add to Cart button -->
    </tr>
    <% for (ItemInfo item : itemInfos) { %>
    <tr>
        <td><%= item.getName() %></td>
        <td><%= item.getDescription() %></td>
        <td>
            <!-- Add to Cart button sends itemId to the addToCartServlet -->
            <form action="addToCartServlet" method="post">
                <input type="hidden" name="itemId" value="<%= item.getId() %>">
                <input type="submit" value="Add to Cart">
            </form>
        </td>
    </tr>
    <% } %>
</table>
</body>
</html>
