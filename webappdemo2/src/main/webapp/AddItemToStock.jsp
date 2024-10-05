<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add New Item to Stock</title>
</head>
<body>
<h1>Add New Item to Stock</h1>

<form action="AddToStockServlet" method="post">
    <label for="name">Item Name:</label>
    <input type="text" id="name" name="name" required><br><br>

    <label for="type">Item Type:</label>
    <input type="text" id="type" name="type" required><br><br>

    <label for="description">Description:</label>
    <textarea id="description" name="description" required></textarea><br><br>

    <label for="price">Price:</label>
    <input type="number" id="price" name="price" required><br><br>

    <label for="quantity">Quantity:</label>
    <input type="number" id="quantity" name="quantity" required><br><br>

    <input type="submit" value="Add Item">
</form>

</body>
</html>
