<%@ page import="se.kth.faisalo.webappdemo2.ui.ItemInfo" %>
<%@ page import="java.util.List" %>
<%@ page import="se.kth.faisalo.webappdemo2.bo.Handler" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Edit Items</title>
</head>
<body>
<h1>Edit Items</h1>

<%
    // Fetch the list of items from the database
    List<ItemInfo> itemInfos = Handler.getItems();
%>

<form action="updateItemsServlet" method="post">
    <table border="1">
        <tr>
            <th>Name</th>
            <th>Type</th>
            <th>Description</th>
            <th>Price</th>
            <th>Quantity</th>
        </tr>
        <% for (ItemInfo item : itemInfos) { %>
        <tr>
            <td><input type="text" name="name_<%= item.getId() %>" value="<%= item.getName() %>" /></td>
            <td><input type="text" name="type_<%= item.getId() %>" value="<%= item.getType() %>" /></td>
            <td><input type="text" name="description_<%= item.getId() %>" value="<%= item.getDescription() %>" /></td>
            <td><input type="number" name="price_<%= item.getId() %>" value="<%= item.getPrice() %>" /></td>
            <td><input type="number" name="quantity_<%= item.getId() %>" value="<%= item.getQuantity() %>" /></td>
            <!-- Hidden input to send item ID -->
            <input type="hidden" name="id_<%= item.getId() %>" value="<%= item.getId() %>" />
        </tr>
        <% } %>
    </table>
    <input type="submit" value="Save Changes" />
</form>

</body>
</html>
