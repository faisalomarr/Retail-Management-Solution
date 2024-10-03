<%@ page import="se.kth.faisalo.webappdemo2.ui.ItemInfo" %>
<%@ page import="se.kth.faisalo.webappdemo2.bo.Handler" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: faisa
  Date: 2024-10-01
  Time: 17:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
