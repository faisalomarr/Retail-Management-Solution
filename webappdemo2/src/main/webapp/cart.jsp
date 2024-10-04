<%@ page import="se.kth.faisalo.webappdemo2.bo.Item" %>
<%@ page import="java.util.List" %>
<%@ page import="se.kth.faisalo.webappdemo2.ui.ItemInfo" %>
<%@ page import="se.kth.faisalo.webappdemo2.bo.Handler" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>Cart Items</title>
</head>
<body>
<h1>Items in Your Cart</h1>
<p>by Faisal and Johannes</p>

<%
  // Assuming you have a cartId variable passed from somewhere (e.g., session or request)
  int userId = (Integer) session.getAttribute("cartId");
  // Fetch the list of items in the cart
  List<ItemInfo> itemInfos = Handler.getItemsInCart(userId);
%>

<table border="1">
  <tr>
    <th>Name</th>
    <th>Description</th>
  </tr>
  <% for (ItemInfo item : itemInfos) { %>
  <tr>
    <td><%= item.getName() %></td>
    <td><%= item.getDescription() %></td>
  </tr>
  <% } %>
</table>

<br/>
<a href="hello-servlet">Hello Servlet</a>
<a href="Login.jsp">Go to Login Page</a> <!-- Reference to Login.jsp -->
<a href="additems.jsp">Go to addItems Page</a>
</body>
</html>
