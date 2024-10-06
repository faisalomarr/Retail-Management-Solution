<%@ page import="se.kth.faisalo.webappdemo2.ui.ItemInfo" %>
<%@ page import="se.kth.faisalo.webappdemo2.bo.Handler" %><%--
  Created by IntelliJ IDEA.
  User: faisa
  Date: 2024-10-04
  Time: 17:23
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Purchase Successful</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      background-color: #f9f9f9;
      display: flex;
      justify-content: center;
      align-items: center;
      height: 100vh;
    }
    .success-message {
      text-align: center;
      padding: 20px;
      background-color: #d4edda;
      border: 1px solid #c3e6cb;
      color: #155724;
      border-radius: 5px;
      width: 50%;
    }
  </style>
</head>
<body>

<%
  int cartId = (Integer) session.getAttribute("cartId");
  // Fetch the list of items in the cart
  boolean purchaseState= Handler.purchaseItemsInCart(cartId);
  if (!purchaseState){
    response.sendRedirect("failedPurchase.jsp");
  }
%>

<div class="success-message">
  <h1>Purchase Successful!</h1>
  <p>Thank you for your purchase. A confirmation email has been sent to your registered email address.</p>
  <p>Please check your email for more details about your order.</p>
  <a href="index.jsp">Return to Home</a>
</div>

</body>
</html>