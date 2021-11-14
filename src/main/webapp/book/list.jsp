<%--
  Created by IntelliJ IDEA.
  User: Phong Vu
  Date: 11/14/2021
  Time: 9:44 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Book Management</title>
</head>
<body>
<div align="center">
    <h2><a href="/books">Book Management</a></h2>
    <h2><a href="/books?action=create">Create New Book</a></h2>
    <table border="1">
        <tr>
            <th>Name</th>
            <th>Price</th>
            <th>Description</th>
            <th>Category</th>
            <th>Edit</th>
            <th>Delete</th>
        </tr>

        <c:forEach items="${bookList}" var="book">
            <tr>
                <td><a href="/books?action=view&id=${book.id}">${book.name}</a></td>
                <td>${book.price}</td>
                <td>${book.des}</td>
                <td><c:forEach items="${book.getCategories()}" var="cate">
                    ${cate.name}<br>
                </c:forEach>
                </td>
                <td><a href="/books?action=edit&id=${book.id}">Edit</a></td>
                <td><a href="/books?action=delete&id=${book.id}">Delete</a></td>
            </tr>
        </c:forEach>

    </table>
</div>
</body>
</html>
