<%@ page import="se.kth.faisalo.webappdemo2.bo.Item" %>
<%@ page import="java.util.List" %>
<%@ page import="se.kth.faisalo.webappdemo2.db.DbItem" %>
<%@ page import="se.kth.faisalo.webappdemo2.ui.ItemInfo" %>
<%@ page import="se.kth.faisalo.webappdemo2.bo.Handler" %>
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
   List<ItemInfo> itemInfos = Handler.getItems();
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
</body>
</html>
