<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Order</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>

    <h1>Shopping Cart Check Out</h1>

    <form method="post" action="jsp/thankyou.jsp">
        <input type="hidden" name="action" value="validate_credit">
        <table border="1">
            <thead>
                <tr>
                    <th colspan="2">You have selected to purchase the following items:</th>
                </tr>
            </thead>
            <tbody>
                <c:choose>
                    <c:when test="${not empty sessionScope.cart}">
                        <c:set var="totalCost" value="0" />
                        <c:forEach var="entry" items="${sessionScope.cart}">
                            <c:set var="item" value="${entry.value}" />
                            <c:set var="cost" value="${item.orderCost}" />
                            <tr>
                                <td>
                                    <c:out value="${item.book.title}" /> - Qty: <c:out value="${item.quantity}" /> -
                                    $<fmt:formatNumber value="${cost}" type="number" minFractionDigits="2"/>
                                </td>
                            </tr>
                            <c:set var="totalCost" value="${totalCost + cost}" />
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <tr>
                            <td colspan="2">No items in cart.</td>
                        </tr>
                    </c:otherwise>
                </c:choose>
            </tbody>
        </table>

        <br/>
        <p>Please input the following information.</p>

        <table>
            <tr>
                <td>Last name:</td>
                <td><input type="text" name="lastname" size="25"></td>
            </tr>
            <tr>
                <td>Street:</td>
                <td><input type="text" name="street" size="25"></td>
            </tr>
            <tr>
                <td>City:</td>
                <td><input type="text" name="city" size="25"></td>
            </tr>
            <tr>
                <td>State:</td>
                <td><input type="text" name="state" size="2"></td>
            </tr>
            <tr>
                <td>Zip code:</td>
                <td><input type="text" name="zipcode" size="10"></td>
            </tr>
            <tr>
                <td>Phone #:</td>
                <td><input type="text" name="phone" size="12"></td>
            </tr>
            <tr>
                <td>Credit Card #:</td>
                <td><input type="text" name="card_num" size="25"></td>
            </tr>
            <tr>
                <td>Expiration (mm/yy):</td>
                <td>
                    <input type="text" name="expires" size="2"> /
                    <input type="text" name="expires2" size="2">
                </td>
            </tr>
            <tr>
                <td>Order Amount $</td>
                <td>
                    <fmt:formatNumber var="totalStr" value="${totalCost}" type="number" minFractionDigits="2"/>
                    <input type="text" name="amount" value="${totalStr}" readonly>
                </td>
            </tr>
        </table>

        <p><input type="submit" value="Submit"></p>
    </form>

</body>
</html>
