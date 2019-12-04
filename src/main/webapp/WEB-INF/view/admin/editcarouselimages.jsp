<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>青橙后台管理</title>
    <!-- Bootstrap Styles-->
    <link href="${pageContext.request.contextPath}/static/assets/css/bootstrap.css" rel="stylesheet"/>
    <!-- FontAwesome Styles-->
    <link href="${pageContext.request.contextPath}/static/assets/css/font-awesome.css" rel="stylesheet"/>
    <!-- Morris Chart Styles-->

    <!-- Custom Styles-->
    <link href="${pageContext.request.contextPath}/static/assets/css/custom-styles.css" rel="stylesheet"/>
    <!-- Google Fonts-->
    <link href='https://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'/>
    <!-- TABLE STYLES-->
    <link href="${pageContext.request.contextPath}/static/assets/js/dataTables/dataTables.bootstrap.css"
          rel="stylesheet"/>
    <style>
        .photo {
            width: 500px;
            height: 200px;
            border: 1px solid rgb(0, 150, 136);
            margin-top: 10px;
            position: relative;
            overflow: hidden;
            background: rgb(0, 150, 136);
        }

        .photo:hover {
            background: #fff;
            border: 1px solid rgb(0, 150, 136);
        }

        #file {
            position: absolute;
            width: 100%;
            height: 100%;
            opacity: 0;
            z-index: 999;
            cursor: pointer;
        }

        .photo p {
            line-height: 280px;
            color: #ccc;
            font-size: 18px;
            position: absolute;
            z-index: 99;
            left: 60px;
        }

        #img {
            width: 500px;
            height: 200px;
        }
    </style>
</head>
<body>
<div id="wrapper">
    <nav class="navbar navbar-default top-navbar" role="navigation">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".sidebar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="listCategory">Tmall</a>
        </div>
    </nav>

    <!--/. NAV TOP  -->
    <nav class="navbar-default navbar-side" role="navigation">
        <div class="sidebar-collapse">
            <ul class="nav" id="main-menu">

                <li>
                    <a href="${pageContext.request.contextPath}/admin/listCategory"><i class="fa fa-bars"></i> 分类管理</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/admin/listUser"><i class="fa fa-user"></i> 用户管理</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/admin/listOrder"><i class="fa fa-list-alt"></i> 订单管理</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/admin/listLink"><i class="fa fa-link"></i> 推荐链接管理</a>
                </li>
                <li>
                    <a class="active-menu" href="${pageContext.request.contextPath}/admin/carousel"><i
                            class="fa fa-camera"></i>首页轮播图管理</a>
                </li>
            </ul>
        </div>

    </nav>
    <!-- /. NAV SIDE  -->
    <div id="page-wrapper">
        <div id="page-inner">
            <div class="row">
                <div class="col-md-12">
                    <h1 class="page-header">
                        轮播图管理
                        <small></small>
                    </h1>
                </div>
            </div>
            <div class="row">
                <div class="col-md-3">
                    <a href="javascript:history.back()" class="btn btn-success">返回上一页</a>
                </div>
            </div>
            <br>
            <div class="row">
                <div class="col-md-12">
                    <!-- Advanced Tables -->
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            首页轮播图如下:
                        </div>
                        <div class="panel-body">
                            <div class="table-responsive">
                                <form action="${pageContext.request.contextPath}/admin/editCarouse" role="form"
                                      method="post" enctype="multipart/form-data">
                                    <table class="table table-striped table-bordered table-hover"
                                           id="dataTables-example">
                                        <thead>
                                        <tr>
                                            <th>原图片预览</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${cc}" var="carousel">
                                            <tr>
                                                <td width="500">
                                                        <%--<div class="photo">
                                                            <input type="file" name="file" accept="image/*" id="file"> <img
                                                                src="${pageContext.request.contextPath}/static/${carousel.images}" id="img" alt="点击更换图片"/>
                                                        </div>--%>
                                                    <img src="${pageContext.request.contextPath}/static/${carousel.images}"
                                                         width="500" height="200"
                                                    />
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>

                                    <table>
                                        <thead>
                                        <tr>
                                            <th>新图片上传</th>
                                        </tr>
                                        <tr>
                                            <th>操作</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td width="500">
                                                    <div class="photo">
                                                        <input type="file" name="file" accept="image/*" id="file"> <img
                                                            src="${pageContext.request.contextPath}/static/img/product/r.png"
                                                            id="img" alt=""/>
                                                    </div>
                                            </tr>
                                        </tbody>
                                    </table>


                                </form>
                            </div>

                        </div>
                    </div>
                    <!--End Advanced Tables -->
                </div>
            </div>

        </div>
    </div>
    <!-- /. PAGE WRAPPER  -->
</div>
<!-- /. WRAPPER  -->
<!-- JS Scripts-->
<script type="text/javascript">
    $("#file").change(function (e) {
        var files = e.target.files[0];
        if (!/image\/\w+/.test(files.type)) {
            alert("请上传图片");
            return;
        }
        var reader = new FileReader();
        reader.readAsDataURL(files);
        reader.onload = function () {
            var url = this.result;
            $("#img").attr('src', url);
            $(".photo>p").html(' ');
        }
        console.log(e.target.files[0])
    })
</script>
<!-- jQuery Js -->
<script src="${pageContext.request.contextPath}/static/assets/js/jquery-1.10.2.js"></script>
<!-- Bootstrap Js -->
<script src="${pageContext.request.contextPath}/static/assets/js/bootstrap.min.js"></script>
<!-- Metis Menu Js -->
<script src="${pageContext.request.contextPath}/static/assets/js/jquery.metisMenu.js"></script>
<!-- DATA TABLE SCRIPTS -->
<script src="${pageContext.request.contextPath}/static/assets/js/dataTables/jquery.dataTables.js"></script>
<script src="${pageContext.request.contextPath}/static/assets/js/dataTables/dataTables.bootstrap.js"></script>
<script>
    $(document).ready(function () {
        $('#dataTables-example').dataTable();
    });
</script>
<!-- Custom Js -->
<script src="${pageContext.request.contextPath}/static/assets/js/custom-scripts.js"></script>


</body>
</html>
