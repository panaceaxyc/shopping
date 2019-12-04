<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>

<style>
    div.headerCon {
        width: auto;
        height: 120px;
        padding: 30px 0 0;
        margin: 0 auto;
        display: block;
    }

    div.fullSearchDiv {
        width: 550px;
        height: 40px;
        padding: 1px;
        float: left;
        margin: 10px 25px 10px 140px;
    }

    .searchFrom {
        background-color: #13CFCF;
        width: 460px;
        padding: 1px;
        height: 40px;
        display: block;
        margin-top: 70px;
    }

    .searchFrom input {
        width: 367px !important;
        padding: 5px 3px 5px 5px;
        border: 1px solid transparent;
        height: 34px;
        margin: 2px;
        font-size: 15px;
        outline: 0;
        /*position: relative;*/
    }

    button.searchButton {
        width: 80px;
        border: 1px solid transparent;
        background-color: #13CFCF;
        color: white;
        height: 34px;
        font-size: 16px;
        font-weight: bold;
        cursor: pointer;
    }

    button.shopButton {
        width: 86px;
        border: 1px solid transparent;
        background-color: #333;
        color: white;
        height: 40px;
        font-size: 16px;
        font-weight: bold;
        cursor: pointer;
        position: absolute;
        margin: 1px auto;
        top: 0;
        right: 0;
    }


    div.searchBelow span {
        color: #999;
    }

    div.searchBelow a {
        padding: 0px 10px 0px 10px;
        font-size: 14px;
    }

    div.searchBelow a:hover {
        color: #999;
        text-decoration: underline;
    }


    .logo img {
        height: 30px;
        width: 145px;
    }
</style>

<!-- 搜索栏 -->
<div class="workArea">
    <div class="headerCon">
        <div class="logo">
            <a href="/home">
                <img src="${pageContext.request.contextPath}/static/img/fore/tmallLogo.png" id="logo"  >
            </a>
        </div>
        <div class="fullSearchDiv">
            <form class="searchFrom" action="searchProduct">
                <input type="text" value="${param.keyword}" placeholder="搜索 青橙 商品/品牌/店铺" name="keyword">
                <button class="searchButton" type="submit">搜青橙</button>
            </form>
            <div>
            </div>
        </div>
        <a href="#">
            <img class="pull-right" src="${pageContext.request.contextPath}/static/img/fore/buyflow.png" >
        </a>
    </div>
</div>
<div style="clear: both;"></div>