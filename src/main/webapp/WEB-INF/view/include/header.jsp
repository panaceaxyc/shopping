<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>青橙购物</title>

    <%-- 引入JQ和Bootstrap--%>
    <script src="${pageContext.request.contextPath}/static/js/jquery/2.0.0/jquery.min.js"></script>
    <link href="${pageContext.request.contextPath}/static/assets/css/bootstrap.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/static/js/bootstrap/3.3.6/bootstrap.min.js"></script>
    <link href="${pageContext.request.contextPath}/static/css/fore/style.css" rel="stylesheet">
    <%-- sweetelert--%>
    <script src="${pageContext.request.contextPath}/static/assets/js/sweetalert.min.js"></script>
    <link href="${pageContext.request.contextPath}/static/css/fore/sweetalert.css" rel="stylesheet">
    <!--地区选择器-->
    <script src="${pageContext.request.contextPath}/static/js/distpicker.js"></script>
</head>
<body>