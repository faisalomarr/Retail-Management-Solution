<%@ page import="se.kth.faisalo.webappdemo2.bo.Item" %>
<%@ page import="java.util.List" %>
<%@ page import="se.kth.faisalo.webappdemo2.db.DbItem" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Item List</title>
</head>
<body>
<h1>Item List</h1>
<p>by Faisal and Johannes</p>

<%
    // Fetch the list of items from the database
    List<Item> items = DbItem.searchItems();
%>

<table border="1">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Type</th>
        <th>Description</th>
        <th>Price</th>
    </tr>
    <% for (Item item : items) { %>
    <tr>
        <td><%= item.getId() %></td>
        <td><%= item.getName() %></td>
        <td><%= item.getType() %></td>
        <td><%= item.getDescription() %></td>
        <td><%= item.getPrice() %></td>
    </tr>
    <% } %>
</table>

<br/>
<a href="hello-servlet">Hello Servlet</a>
<a href="Login.jsp">Go to Login Page</a> <!-- Reference to Login.jsp -->
</body>
</html>
