<!DOCTYPE html>



<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Online Bookshop</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>

    <h1>Items in your Shopping Cart</h1>
    <table border="1">
        <thead>
            <tr>
                <th>Title</th>
                <th>Quantity</th>
                <th>Subtotal</th>
            </tr>
        </thead>
        <tbody>
            <c:choose>
                <c:when test="${not empty sessionScope.cart}">
                    <c:set var="total" value="0"/>
                    <c:forEach var="entry" items="${sessionScope.cart}">
                        <c:set var="item" value="${entry.value}"/>
                        <tr>
                            <td>${item.book.title}</td>
                            <td>${item.quantity}</td>
                            <td>
                                $<fmt:formatNumber value="${item.orderCost}" type="number" minFractionDigits="2"/>
                            </td>
                        </tr>
                        <c:set var="total" value="${total + item.orderCost}"/>
                    </c:forEach>
                    <tr>
                        <td colspan="2"><strong>Order Total:</strong></td>
                        <td><strong>$<fmt:formatNumber value="${total}" type="number" minFractionDigits="2"/></strong></td>
                    </tr>
                </c:when>
                <c:otherwise>
                    <tr><td colspan="3">No Items in Cart</td></tr>
                </c:otherwise>
            </c:choose>
        </tbody>
    </table>

    <hr>

    <h2>Welcome to the Online Book Store</h2>
    <form method="post" action="./books">
        <input type="hidden" name="action" value="add_to_cart"/>
        <table border="1">
            <thead>
                <tr>
                    <th>ISBN</th>
                    <th>Title</th>
                    <th>Author</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Add</th>
                </tr>
            </thead>
            <tbody>
                <c:choose>
                    <c:when test="${not empty sessionScope.books}">
                        <c:forEach var="book" items="${sessionScope.books}">
                            <tr>
                                <td><c:out value="${book.isbn}"/></td>
                                <td><c:out value="${book.title}"/></td>
                                <td><c:out value="${book.author}"/></td>
                                <td>$<fmt:formatNumber value="${book.price}" type="number" minFractionDigits="2"/></td>
                                <td>
                                    <select name="${book.isbn}">
                                        <option value="1">1</option>
                                        <option value="2">2</option>
                                        <option value="3">3</option>
                                    </select>
                                </td>
                                <td>
                                    <input type="checkbox" name="add" value="${book.isbn}"/>
                                </td>
                            </tr>
                        </c:forEach>
                        <tr>
                            <td colspan="6" align="center">
                                <input type="submit" value="Add to Cart"/>
                            </td>
                        </tr>
                    </c:when>
                    <c:otherwise>
                        <tr><td colspan="6">No Books Available</td></tr>
                    </c:otherwise>
                </c:choose>
            </tbody>
        </table>
    </form>

    <div class="link-container">
        <p><a href="./books?action=view_cart">View Shopping Cart</a></p>
    </div>

</body>
</html>
