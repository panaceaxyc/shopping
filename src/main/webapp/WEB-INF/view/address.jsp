<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@include file="include/header.jsp" %>
<%@include file="include/top.jsp" %>
<%@include file="include/mini-search.jsp" %>
<script type="text/javascript">
    $(document).ready(function () {
        var addresId;
        $("[name='changeAddr']").click(function () {
            $("#update-addr").modal({
                backdrop: 'static'
            });

            $("#name").val($(this).parents("#parent").find("#conname").text());
            $("#telephone").val($(this).parents("#parent").find("#contel").text());
            $("#detailaddress").val($(this).parents("#parent").find("#detailaddr").text());
            addresId = $(this).parents("#parent").find("#table").attr("address-id");

        });

        $("#saveAddr").click(function () {
            var saveAddr = {};
            saveAddr.id = addresId;
            saveAddr.province = $("#provinceUpdate").val();
            saveAddr.city = $("#cityUpdate").val();
            saveAddr.county = $("#countyUpdate").val();
            saveAddr.detailAddress = $("#detailaddress").val();
            saveAddr.conName = $("#name").val();
            saveAddr.conTel = $("#telephone").val();

            $.ajax({
                type: "POST",
                url: "${pageContext.request.contextPath}/admin/saveAddr",
                contentType: "application/x-www-form-urlencoded; charset=utf-8",
                data: saveAddr,
                dateType: "json",
                success: function (result) {
                    if (result.msg == "更新失败") {
                        swal(result.msg);
                    }
                    else {
                        $("#update-info").modal('hide');
                        swal("修改成功", "", "success");
                        $("button").click(function () {
                            location.reload();
                        });
                    }
                },
                error: function () {
                    swal({
                        title: "操作",
                        text: "更新失败",
                        type: "error"
                    })
                }
            });
        });

        $("[name='deleteAddr']").click(function () {
            addresId = $(this).parents("#parent").find("#table").attr("address-id");
            var address = {};
            address.id = addresId;
            swal(
                {
                    title: "您确定要删除这条收货地址吗?",
                    text: "是否删除",
                    type: "warning",
                    showCancelButton: true,
                    confirmButtonColor: "#DD6B55",
                    confirmButtonText: "删除",
                    cancelButtonText: "取消",
                    closeOnConfirm: false,
                    closeOnCancel: false
                },
                function (isConfirm) {
                    if (isConfirm) {
                        $.ajax({
                            type: "POST",
                            url: "${pageContext.request.contextPath}/admin/deleteAddr",
                            contentType: "application/x-www-form-urlencoded; charset=utf-8",
                            data: address,
                            dateType: "json",
                            success: function (result) {
                                swal(result.msg, "", "success");
                                $("button").click(function () {
                                    location.reload();
                                });
                            },
                            error: function () {
                                swal({
                                    title: "操作",
                                    text: "删除失败",
                                    type: "error"
                                })
                            }
                        });
                    }
                    else {
                        location.reload();
                    }
                }
            )
        });

        $("[name='insertAddr']").click(function () {
            $("#insert-addr").modal({
                backdrop: 'static'
            });
        });

        $("#insertAddr").click(function () {
            var insertAddr = {};
            insertAddr.id = {};
            insertAddr.userId = {};
            insertAddr.province = $("#provinceInsert").val();
            insertAddr.city = $("#cityInsert").val();
            insertAddr.county = $("#countyInsert").val();
            insertAddr.detailAddress = $("#detailaddressInsert").val();
            insertAddr.conName = $("#nameInsert").val();
            insertAddr.conTel = $("#telephoneInsert").val();
            $.ajax({
                type: "POST",
                url: "${pageContext.request.contextPath}/admin/insertAddr",
                contentType: "application/x-www-form-urlencoded; charset=utf-8",
                data: insertAddr,
                dataType: "json",
                success: function (result) {
                    swal(result.msg, "", "success");
                    $("button").click(function () {
                        location.reload();
                    });
                },
                error: function () {
                    swal({
                        title: "操作",
                        text: "添加失败",
                        type: "error"
                    })
                }
            });

        });
    });
</script>
<style>
    .page {
        width: 990px;
        margin: 90px auto;
        color: #3c3c3c;
        font: 400 12px/1.6 arial, 'Hiragino Sans GB', 宋体, sans-serif;
    }

    .content {
        border-top: 2px solid #e6e6e6;
        padding: 50px 0;
    }

    .form-main-list {
        width: 720px;
    }

    .form-list {
        margin: 0 auto;
        font-size: 14px;
    }

    .form-group {
        padding: 10px 0;
    }

    .form-item input {
        float: left;
        width: 300px;
        border: 1px solid #dedede;
        height: 37px;
        line-height: 37px;
        padding: 9px;
        font-size: 14px;
        _margin-left: -3px;
    }

    .registerSuccessMessage {
        width: 300px;
        margin: 20px auto;
        text-align: center;
    }
</style>
<hr/>
<div class="registerSuccessMessage">
    <%--修改地址模态框--%>
    <!-- Modal -->
    <div class="modal fade" id="update-addr" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content" id="parentModal">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">修改地址</h4>
                </div>
                <div class="modal-body">
                    <form class="form-inline mt-2 mb-4">
                        <div data-toggle="distpicker" data-autoselect="3">
                            <label for="telephone" class="col-sm-2 control-label"
                                   style="padding-left: 28px;padding-top: 10px">省市区</label>
                            <select class="form-control" id="provinceUpdate"></select>
                            <select class="form-control" id="cityUpdate"></select>
                            <select class="form-control" id="countyUpdate"></select>
                        </div>
                    </form>
                    <form class="form-horizontal" id="update-form" name="update-form" method="post">
                        <div class="form-group">
                            <label for="detailaddress" class="col-sm-2 control-label">详细地址</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" name="detailaddress" id="detailaddress"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="name" class="col-sm-2 control-label">收货人</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" name="name" id="name">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="telephone" class="col-sm-2 control-label">手机号</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" name="telephone" id="telephone">
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-primary" id="saveAddr">保存</button>
                </div>
            </div>
        </div>
    </div>

    <%--添加地址模态框--%>
    <!-- Modal -->
    <div class="modal fade" id="insert-addr" tabindex="-1" role="dialog" aria-labelledby="myModalLabelInsert">
        <div class="modal-dialog" role="document">
            <div class="modal-content" id="parentModalInsert">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabelInsert">添加地址</h4>
                </div>
                <div class="modal-body">
                    <form class="form-inline mt-2 mb-4">
                        <div data-toggle="distpicker" data-autoselect="3">
                            <label for="telephone" class="col-sm-2 control-label"
                                   style="padding-left: 28px;padding-top: 10px">省市区</label>
                            <select class="form-control" id="provinceInsert"></select>
                            <select class="form-control" id="cityInsert"></select>
                            <select class="form-control" id="countyInsert"></select>
                        </div>
                    </form>
                    <form class="form-horizontal" id="insert-form" name="insert-form" method="post">
                        <div class="form-group">
                            <label for="detailaddress" class="col-sm-2 control-label">详细地址</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" name="detailaddressInsert"
                                       id="detailaddressInsert"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="name" class="col-sm-2 control-label">收货人</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" name="nameInsert" id="nameInsert">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="telephone" class="col-sm-2 control-label">手机号</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" name="telephoneInsert" id="telephoneInsert">
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-primary" id="insertAddr">保存</button>
                </div>
            </div>
        </div>

    </div>


        <div class="demo-layout mdl-layout mdl-js-layout mdl-layout--fixed-drawer mdl-layout--fixed-header">
            <header class="demo-header mdl-layout__header mdl-color--grey-100 mdl-color-text--grey-600">
                <div class="mdl-layout__header-row">
                    <span class="mdl-layout-title">收货地址</span>
                    <div class="mdl-layout-spacer">
                    </div>
                    <div class="mdl-textfield mdl-js-textfield mdl-textfield--expandable">
                        <button class="templatemo-blue-button" name="insertAddr"><h5>添加地址</h5></button>
                    </div>
                </div>
            </header>
            <main class="mdl-layout__content mdl-color--grey-100">
                <c:forEach items="${addressList}" var="item">
                <div class="mdl-grid demo-content" id="parent">
                    <div class="demo-charts mdl-color--white mdl-shadow--2dp mdl-cell mdl-cell--12-col mdl-grid">
                        <div class="tab-content">
                            <table class="table table-striped"  address-id="${item.id}" id="table">
                                <thead>
                                <th style="border: 0px solid transparent">
                                        <%--<h1>个人信息</h1>--%>
                                </th>
                                </thead>
                                <tbody >
                                <tr >
                                    <th style="border: 0px solid transparent" class="tl">收货人</th>
                                    <td style="border: 0px solid transparent" class="tr" id="conname">${item.conName}</td>
                                </tr>
                                <tr>
                                    <th style="border: 0px solid transparent" class="tl">手机号</th>
                                    <td style="border: 0px solid transparent" class="tr" id="contel">${item.conTel}</td>
                                </tr>
                                <tr>
                                    <th style="border: 0px solid transparent" class="tl">省</th>
                                    <td style="border: 0px solid transparent" class="tr" id="province">${item.province}</td>
                                </tr>
                                <tr>
                                    <th style="border: 0px solid transparent" class="tl">市</th>
                                    <td style="border: 0px solid transparent" class="tr" id="city">${item.city}</td>
                                </tr>
                                <tr>
                                    <th style="border: 0px solid transparent" class="tl">县/区</th>
                                    <td style="border: 0px solid transparent" class="tr" id="county">${item.county}</td>
                                </tr>
                                <tr>
                                    <th style="border: 0px solid transparent" class="tl">详细地址</th>
                                    <td style="border: 0px solid transparent" class="tr" id="detailaddr">${item.detailAddress}</td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                        <div class="mdl-card__actions mdl-card--border">
                            <button class="templatemo-blue-button" name="changeAddr"><h5>修改地址</h5></button>
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <button class="templatemo-blue-button finish-btn" name="deleteAddr"><h5>删除地址</h5></button>
                        </div>
                    </div>
                </div>
                </c:forEach>
        </div>



<%--

        <h4 class="modal-title" name="insertAddr">添加地址</h4>
        <table class="table table-striped">
            <thead>
            <tr>
                <th>收货人</th>
                <th>手机号</th>
                <th>省</th>
                <th>市</th>
                <th>区/县</th>
                <th>详细地址/乡镇</th>
            </tr>
            </thead>
            <tbody>

            <c:forEach items="${addressList}" var="item">
                <tr>
                    <td style="border: 0px solid transparent" class="tr" id="address-id">${item.id}</td>
                    <td style="border: 0px solid transparent" class="tr" id="conname">${item.conName}</td>
                    <td style="border: 0px solid transparent" class="tr" id="contel">${item.conTel}</td>
                    <td style="border: 0px solid transparent" class="tr" id="province">${item.province}</td>
                    <td style="border: 0px solid transparent" class="tr" id="city">${item.city}</td>
                    <td style="border: 0px solid transparent" class="tr" id="county">${item.county}</td>
                    <td style="border: 0px solid transparent" class="tr" id="detailaddr">${item.detailAddress}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        --%>
    </main>
</div>
</div>
<%@include file="include/footer.jsp" %>
