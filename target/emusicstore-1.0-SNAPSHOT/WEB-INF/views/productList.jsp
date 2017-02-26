<%--
  Created by IntelliJ IDEA.
  User: sabin
  Date: 2/15/2017
  Time: 10:41 AM
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/WEB-INF/views/template/header.jsp" %>
<div class="container-wrapper">
    <div class="container">
        <div class="page-header">
            <h1>All Products</h1>
            <p class="lead">Check out all the awesome products vailable now!</p>
        </div>
        <table class="table table-striped table-hover">
            <thead>
            <tr class="bg-success">
                <th>Photo Thumb</th>
                <th>Product Name</th>
                <th>Category</th>
                <th>Condition</th>
                <th>Price</th>
                <th></th>
            </tr>

            </thead>
            <c:forEach var="product" items="${products}">
                <tbody>
                <tr>
                    <td><img src="#" alt="Image"></td>
                    <td>${product.productName}</td>
                    <td>${product.productCategory}</td>
                    <td>${product.productCondition}</td>
                    <td>${product.productPrice}USD</td>
                    <td>
                        <a href="<spring:url value="/allProducts/viewProduct/${product.productId}"/>">
                            <span class="glyphicon glyphicon-info-sign"></span>
                        </a>
                    </td>
                </tr>
                </tbody>
            </c:forEach>
        </table>
<%@include file="/WEB-INF/views/template/footer.jsp" %>