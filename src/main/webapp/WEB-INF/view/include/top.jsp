<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>


<%-- 顶部导航栏 --%>
<nav id="site-nav" role="navigation">
    <div class="workArea">

        <%-- 导航栏左半部分
         如果取不到用户信息则显示如下： --%>
        <c:if test="${empty sessionScope.user}">
            <span>喵，欢迎来青橙</span>
            <span><a href="/home">首页</a></span>
            <span><a href="/loginPage">请登录</a></span>
            <span><a href="/registerPage">免费注册</a></span>
        </c:if>
        <%-- 如果用户成功登录，则显示如下： --%>
        <c:if test="${!empty sessionScope.user}">
            <span><a href="${pageContext.request.contextPath}/home">首页</a></span>
            <span>Hi，${sessionScope.user.name}</span>
            <span><img src="${pageContext.request.contextPath}/static/img/${sessionScope.user.photo}" width="40" height="40" style="border-radius: 50%"/></span>
            <span><a href="#">积分 <strong>${sessionScope.user.points}</strong></a></span>
            <span><a onclick="javascript:logout()"  >退出登录</a></span>
        </c:if>
        <script type="text/javascript">
            function logout() {
                    swal(
                        {title:"您确定要退出登录吗?",
                            text:"退出后,有些功能不能使用!",
                            type:"warning",
                            showCancelButton:true,
                            confirmButtonColor:"#DD6B55",
                            confirmButtonText:"是的，我要退出！",
                            cancelButtonText:"取消",
                            closeOnConfirm:false,
                            closeOnCancel:false
                        },
                        function(isConfirm)
                        {
                            if(isConfirm)
                            {
                                 window.location="${pageContext.request.contextPath}/logout";
                            }
                            else{
                                location.reload();
                            }
                        }
                    )
            }
        </script>

        <%-- 导航栏右半部分：
             使用Bootstrap的pull-right类 --%>
        <span class="pull-right">
            <a href="bought">我的订单</a>
            <a href="${pageContext.request.contextPath}/cart">
                <span class="glyphicon glyphicon-shopping-cart redColor"></span>
                <c:if test="${empty sessionScope.user}">
                    登录后查看购物车.
                </c:if>
                <c:if test="${! empty sessionScope.user}">
                    查看购物车.
                </c:if>
            </a>
        </span>
    </div>
</nav>
