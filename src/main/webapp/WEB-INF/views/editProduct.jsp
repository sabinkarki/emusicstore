<%--
  Created by IntelliJ IDEA.
  User: sabin
  Date: 2/27/2017
  Time: 11:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@include file="/WEB-INF/views/template/header.jsp" %>
<div class="container-wrapper">
    <div class="container">
        <div class="page-header">
            <h1>Edit Product</h1>
            <p class="lead">Update the information below!</p>
        </div>
        <div class="container">
            <div class="row">
                <div class="col-md-6 col-md-offset-3">
                    <form:form action="${pageContext.request.contextPath}/admin/productInventory/editProduct?${_csrf.parameterName}=${_csrf.token}"
                               modelAttribute="product" method="POST" enctype="multipart/form-data">
                        <form:hidden path="productId" value="${product.productId}"/>

                        <div class="form-group">
                            <label for="name">Name</label>
                            <form:input path="productName" class="form-control" id="name"
                                        value="${product.productName}"/>
                            <form:errors path="productName" cssStyle="color:red"/>
                        </div>

                        <div class="form-group">
                            <label for="category">Category</label>
                            <form:select path="productCategory">
                                <form:options items="${lstOfCategory}" id="category"/>
                            </form:select>
                        </div>

                        <div class="form-group">
                            <label for="description">Description</label>
                            <form:textarea path="productDescription" class="form-control" id="description"
                                           value="${product.productDescription}"/>
                        </div>

                        <div class="form-group">
                            <label for="price">Amount</label>
                            <div class="input-group">
                                <div class="input-group-addon">$</div>
                                <form:input path="productPrice" class="form-control" id="price"
                                            value="${product.productPrice}"/>
                                <form:errors path="productPrice" cssStyle="color:red"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="productCondition">Condition</label>
                            <form:radiobuttons path="productCondition" items="${lstOfProductCondition}" id="condition"/>
                        </div>

                        <div class="form-group">
                            <label for="productStatus">Status</label>
                            <form:checkboxes path="productStatus" items="${lstOfProductStatus}" id="productStatus"/>
                        </div>

                        <div class="form-group">
                            <label for="unitStock">Unit In stock</label>
                            <form:input path="unitInStock" class="form-control" id="unitStock" onfocus="this.value='' "
                                        value="${product.unitInStock}"/>
                        </div>

                        <div class="form-group">
                            <label for="productManufacturer">Manufacturer</label>
                            <form:input path="productManufacturer" class="form-control" id="productManufacturer"
                                        value="${product.productManufacturer}"/>
                            <form:errors path="productManufacturer" cssStyle="color:red"/>
                        </div>

                     <%--   <input type="hidden" name="${_csrf.parameterName}"
                               value="${_csrf.token}" />--%>

                        <div class="form-group">
                            <label class="control-label" for="productImage">Upload Picture</label>
                            <form:input path="productImage" id="productImage" type="file" class="form:input-large"
                                        value="$"/>
                        </div>
                        <input type="Submit" value="Submit" class="btn btn-default"/>
                        <a class="btn btn-default" href="<c:url value="/admin/productInventory"/>">Cancel</a>
                    </form:form>
                </div>
            </div>
        </div>
        <%@include file="/WEB-INF/views/template/footer.jsp" %>
