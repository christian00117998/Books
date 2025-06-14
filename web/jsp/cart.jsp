<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Shopping Cart - Online Bookshop</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>

    <h1>The following items are in your shopping cart</h1>

    <form method="post" action="./books">
        <input type="hidden" name="action" value="update_cart"/>
        <table border="1">
            <thead>
                <tr>
                    <th>ISBN</th>
                    <th>Title</th>
                    <th>Price/unit</th>
                    <th>Quantity</th>
                    <th>Subtotal</th>
                    <th>Remove</th>
                </tr>
            </thead>
            <tbody>
                <c:choose>
                    <c:when test="${not empty sessionScope.cart}">
                        <c:set var="total" value="0"/>
                        <c:forEach var="entry" items="${sessionScope.cart}">
                            <c:set var="isbn" value="${entry.key}"/>
                            <c:set var="item" value="${entry.value}"/>
                            <c:set var="book" value="${item.book}"/>
                            <c:set var="subtotal" value="${item.orderCost}"/>
                            <tr>
                                <td><c:out value="${isbn}"/></td>
                                <td><c:out value="${book.title}"/></td>
                                <td>$<fmt:formatNumber value="${book.price}" type="number" minFractionDigits="2"/></td>
                                <td>
                                    <input type="text" name="${isbn}" size="2" value="${item.quantity}" maxlength="4"/>
                                </td>
                                <td>$<fmt:formatNumber value="${subtotal}" type="number" minFractionDigits="2"/></td>
                                <td align="center">
                                    <input type="checkbox" name="remove" value="${isbn}"/>
                                </td>
                            </tr>
                            <c:set var="total" value="${total + subtotal}"/>
                        </c:forEach>
                        <tr>
                            <td colspan="4">
                                <input type="submit" value="Update Cart"/>
                            </td>
                            <td colspan="2" align="right">
                                <strong>ORDER TOTAL: $<fmt:formatNumber value="${total}" type="number" minFractionDigits="2"/></strong>
                            </td>
                        </tr>
                    </c:when>
                    <c:otherwise>
                        <tr>
                            <td colspan="6">Your cart is empty.</td>
                        </tr>
                    </c:otherwise>
                </c:choose>
            </tbody>
        </table>
    </form>

    <div class="link-container">
        <p><a href="./books?action=continue">Continue Shopping</a></p>
        <p><a href="./books?action=checkout">Check Out</a></p>
    </div>

</body>
</html>
