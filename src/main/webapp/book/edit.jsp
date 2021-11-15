<%--
  Created by IntelliJ IDEA.
  User: Phong Vu
  Date: 11/14/2021
  Time: 9:45 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Edit View</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <style>
        a {
            text-decoration: none;
            color: black;
        }

        div {
            margin-left: 500px;
            width: 500px;
        }

        .title {
            width: 75px;
        }

        #select {
            width: 150px;
        }

        body {

            background-image: url("https://cdn11.bigcommerce.com/s-ka7ofex/images/stencil/2000x1000/uploaded_images/how-rfid-is-making-libraries-smarter.jpg?t=1580145106");
        }

    </style>
</head>
<body>
<div align="center">
    <h2><a href="/books" style="color: white">Book Management</a></h2>
    <fieldset>
        <legend style="color: white">Edit Information</legend>
        <form method="post">
            <table border="1" class="table table-warning table-striped table-hover">
                <tr>
                    <td class="title">Name</td>
                    <td><input type="text" name="name" id="name" value="${book.name}"></td>
                </tr>
                <tr>
                    <td class="title">Price</td>
                    <td><input type="text" name="price" id="price" value="${book.price}"></td>
                </tr>
                <tr>
                    <td class="title">Description</td>
                    <td><input type="text" name="des" id="des" value="${book.des}"></td>
                </tr>
                <tr>
                    <td class="title"></td>
                    <td>
                        <select name="categories" id="categories" class="form-select" multiple>
                            <%--                           <c:forEach items="${book.getCategories()}" var="cas">--%>
                            <%--                               <option value="${cas.id}" selected="selected">${cas.name}</option>--%>
                            <%--                           </c:forEach>--%>
                            <c:forEach items="${subList}" var="cate" varStatus="loop">
                                <%--                             <c:forEach items="${cateListOfBook}" var="clob">--%>
                                <%--                                 <c:if test="${cate.id!=clob.id}">--%>
                                <c:forEach items="${book.getCategories()}" var="cas">--%>
                                    <%! boolean isSelected = false;%>
                                    <% pageContext.setAttribute("isSelected", isSelected);%>
                                    <c:if test="${cate.id == cas.getId()}">
                                        <% isSelected = true;%>
                                        <option value="${cate.id}" selected="selected">${cate.name}</option>
                                    </c:if>
                                </c:forEach>
                                <c:if test="${isSelected == false}">
                                    <option value="${cate.id}">${cate.name}</option>
                                </c:if>
                                <%--                                 </c:if>--%>
                                <%--                             </c:forEach>--%>
                            </c:forEach>
                        </select>
                    </td>

                </tr>
                <tr>
                    <td class="title"></td>
                    <td id="select"><input type="submit" value="Save" class="btn btn-outline-secondary"></td>
                </tr>
            </table>
        </form>
    </fieldset>
    <h3><c:if test="${message!=null}">
        <span style="color: white">${message}</span>
    </c:if></h3>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"
        integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"
        integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13"
        crossorigin="anonymous"></script>
</body>
</html>
