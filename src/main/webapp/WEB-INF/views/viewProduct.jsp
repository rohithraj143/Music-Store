<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ include file="/WEB-INF/views/template/header.jsp" %>


<div class="container-wrapper">
    <div class="container">
        <div class="page-header">
            <h1>Product Details</h1>

            <p class="lead">Here is the detail information of the product</p>
        </div>

        <div class="container" ng-app="cartApp">
            <div class="row">
                <div class="col-lg-5">
                    <img src="<c:url value="/resources/images/${product.productId}.png"/>" alt="image not found"
                             style="width:100%">
                    <%--<img src="#" alt="image" style="width:430%; height: 150px" />--%>
                </div>

                <div class="col-md-5">
                    <h3>Product Name : ${product.productName}</h3>
                    <p>Product Description : ${product.productName}</p>
                    <p>Manufacturer : ${product.productManufacturer}</p>
                    <p>Category : ${product.productCategory}</p>
                    <p>Condition : ${product.productCondition}</p>
                    <p>Price : ${product.productPrice}</p>

                    <br>

                    <c:set var="role" scope="page" value="${param.role}" />
                    <c:set var="url" scope="page" value="/productList" />
                    <c:if test="${role='admin'}">
                        <c:set var="url" scope="page" value="/admin/productInventory" />
                    </c:if>

                    <p ng-controller="cartCtrl">
                        <a href="<c:url value="${url}" />" class="btn btn-default">Back</a>
                        <a href="#" class="btn btn-warning btn-large"
                           ng-click="addToCart('${product.productId}')"><span
                                class="glyphicon glyphicon-shopping-cart"></span>Order
                            Now</a>
                        <a href="<spring:url value="/cart" />"
                           class="btn btn-default"><span class="glyphicon glyphicon-hand-right"></span>View Cart</a>
                    </p>
                </div>
            </div>
        </div>


        <script src="<c:url value="/resources/js/controller.js" /> "></script>
        <%@ include file="/WEB-INF/views/template/footer.jsp" %>