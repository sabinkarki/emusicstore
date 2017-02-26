<%--
  Created by IntelliJ IDEA.
  User: sabin
  Date: 2/15/2017
  Time: 10:41 AM
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@include file="/WEB-INF/views/template/header.jsp" %>
<div class="container-wrapper">
    <div class="container">
        <div class="page-header">
            <h1>Product Detail</h1>
            <p class="lead">Here is the detail of the product!</p>
        </div>

        <div class="container">
            <div class="row">
                <div class="col-md-5">
                    <img src="#" alt="hello sabin" style="width:100% ; heigh:50px"/>
                </div>
                <div class="col-md-5">
                    <h1>${product.productName}</h1>
                    <p>${product.productDescription}</p>
                    <p><strong>Manufacturer</strong>:${product.productManufacturer}</p>
                    <p><strong>Category</strong>:${product.productCategory}</p>
                    <p><strong>Condition</strong>:${product.productCondition}</p>
                    <p><strong>Price</strong>:${product.productPrice}</p>
                </div>
            </div>
        </div>

<%@include file="/WEB-INF/views/template/footer.jsp" %>