<%--
  Created by IntelliJ IDEA.
  User: sabin
  Date: 2/17/2017
  Time: 1:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My Music Store</title>

    <!-- Bootstrap core CSS -->
    <link href="<c:url value="/resources/bower_components/bootstrap/dist/css/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/carousel.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/main.css"/>" rel="stylesheet">
</head>
<!-- NAVBAR
================================================== -->
<body>
<div class="navbar-wrapper">
    <div class="container">

        <nav class="navbar navbar-inverse navbar-static-top">
            <div class="container">
                <div class="navbar-header">
                  <%--  <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                            aria-expanded="false" aria-controls="navbar">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>--%>
                    <a class="navbar-brand" href="<c:url value="/home"/>">My Music Store</a>
                </div>
                <div id="navbar" class="navbar-collapse collapse">
                    <ul class="nav navbar-nav">
                        <li><a href="<c:url value="/home"/>">Home</a></li>
                        <li><a href="<c:url value="/allProducts"/> ">Products</a></li>
                        <li><a href="#contact">Contact</a></li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                               aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="#">Action</a></li>
                                <li role="separator" class="divider"></li>
                                <li><a href="#">One more separated link</a></li>
                            </ul>
                        </li>
                    </ul>
                    <ul class="nav navbar-nav pull-right">
                        <c:if test="${pageContext.request.userPrincipal.name ==null}">
                            <li><a href="<c:url value="/login"/>">Login|Register</a></li>
                        </c:if>
                        <c:if test="${pageContext.request.userPrincipal.name !=null}">
                            <li><a href="#">Welcome ${pageContext.request.userPrincipal.name}!</a></li>
                            <li><a href="<c:url value="/admin"/>">Admin</a></li>
                            <li><a href="<c:url value="/logout"/>">Logout</a></li>
                        </c:if>
                    </ul>
                </div>
            </div>
        </nav>
    </div>
</div>
