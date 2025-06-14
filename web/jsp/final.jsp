<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thank You</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
    <h2>Online Bookstore</h2>
    <hr>
    <h3>Thank you for shopping with us.</h3>

    <table>
        <tr>
            <td>
                <c:choose>
                    <c:when test="${not empty requestScope.result}">
                        ${result}
                    </c:when>
                    <c:otherwise>
                        Your order has been processed successfully.
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
    </table>

    <c:if test="${not empty sessionScope}">
        <c:remove var="cart" scope="session"/>
        <c:remove var="books" scope="session"/>
    </c:if>
</body>
</html>
