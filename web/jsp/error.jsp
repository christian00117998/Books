<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Error Page</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
    <h2 style="color:red;">Oops! An error occurred.</h2>
    
    <c:choose>
        <c:when test="${not empty requestScope.result}">
            <h3>${result}</h3>
        </c:when>
        <c:otherwise>
            <h3>Unknown error occurred. Please try again later.</h3>
        </c:otherwise>
    </c:choose>

    <c:if test="${not empty sessionScope}">
        <c:remove var="cart" scope="session"/>
        <c:remove var="books" scope="session"/>
        <c:remove var="user" scope="session"/>
    </c:if>
</body>
</html>
