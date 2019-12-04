<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>

<%@include file="modal.jsp" %>

<style>
    div.footer {
        margin: 0px 0px;
        border-top: 1px solid #D1D1DC;
    }

    a:hover {
        text-decoration: none;
    }

    div.tmall-ensure {
        margin-top: 24px;
        margin-bottom: 24px;
        text-align: center;
    }

    div.tmall-desc {
        border-top: 1px solid #ededed;
        padding-top: 22px;
        margin: auto;
        height: 190px;
        width: 1190px;
    }

    div.tmall-desc dl a {
        width: 100px;
        overflow: hidden;
        text-align: left;
        height: 20px;
        line-height: 20px;
        color: #8b8b8b;
    }

    div.tmall-desc dl {
        float: left;
        width: 20%;
        padding-left: 52px;
    }

    div.tmall-desc dl dt {
        color: #646464;
        font-size: 16px;
        font-weight: 700;
        height: 30px;
        line-height: 30px;
        text-align: left;
    }

    div.tmall-desc dl dd {
        text-align: left;
    }

    div.tmall-copyright {
        background-color: black;
        border-top: 2px solid #13CFCF;
    }

    div.tmall-copyright span.slash {
        color: white;
    }

    div.footer div.tmall-copyright div.footer-tmallinfo a {
        color: white;
        padding: 0px 3px;
    }

    div.footer div.tmall-copyright div.footer-tmallinfo {
        padding: 10px 0px;
        margin-left: 10px;
    }

    div.footer-copyright {
        margin-left: 10px;
        padding-bottom: 30px;
        line-height: 35px;
        color: #A4A4A4;
    }

    div.footer-copyright span {
        color: #A4A4A4;
    }

    div.footer-copyright a {
        margin-right: 65px;
    }

    div.footer-copyright a:hover {
        color: #A4A4A4;
    }

    div.footer-copyright div.copyRightYear {
        margin: 5px 0px;
        color: #686868;
    }

    img.tmall-cat {
        margin-left: 20px;
    }
</style>

<div id="footer" class="footer" style="display: block;">

    <div class="workArea">
        <div class="tmall-ensure">
            <a href="#nowhere">
                <img src="${pageContext.request.contextPath}/static/img/fore/footer1.png">
                <img src="${pageContext.request.contextPath}/static/img/fore/footer2.png">
                <img src="${pageContext.request.contextPath}/static/img/fore/footer3.png">
                <img src="${pageContext.request.contextPath}/static/img/fore/footer4.png">
            </a>
        </div>

        <div class="tmall-desc">
            <dl>
                <dt><span>购物指南</span></dt>
                <dd><a href="#nowhere">免费注册</a></dd>
                <dd><a href="#nowhere">开通支付宝</a></dd>
                <dd><a href="#nowhere">支付宝充值</a></dd>
            </dl>
            <dl>
                <dt><span>青橙保障</span></dt>
                <dd><a href="#nowhere">发票保障</a></dd>
                <dd><a href="#nowhere">售后规则</a></dd>
                <dd><a href="#nowhere">缺货赔付</a></dd>
            </dl>
            <dl>
                <dt><span>支付方式</span></dt>
                <dd><a href="#nowhere">快捷支付</a></dd>
                <dd><a href="#nowhere">信用卡</a></dd>
                <dd><a href="#nowhere">余额宝</a></dd>
                <dd><a href="#nowhere">蚂蚁花呗</a></dd>
                <dd><a href="#nowhere">货到付款</a></dd>
            </dl>
            <dl>
                <dt><span>商家服务</span></dt>
                <dd><a href="#nowhere">青橙规则</a></dd>
                <dd><a href="#nowhere">商家入驻</a></dd>
                <dd><a href="#nowhere">商家中心</a></dd>
                <dd><a href="#nowhere">青橙智库</a></dd>
                <dd><a href="#nowhere">物流服务</a></dd>
                <dd><a href="#nowhere">喵言喵语</a></dd>
                <dd><a href="#nowhere">运营服务</a></dd>
            </dl>
            <dl>
                <dt><span>手机青橙</span></dt>
                <dd><a href="#nowhere"><img src="${pageContext.request.contextPath}/static/img/fore/qr-code.png"></a></dd>
            </dl>
        </div>

        <div style="clear:both"></div>
    </div>

    <div class="workArea">
        <img src="/img/fore/tmall-cat.png" class="tmall-cat">
    </div>
    <div class="tmall-copyright">
        <div class="workArea">
            <div class="mui-global-fragment-load">
                <div class="footer-tmallinfo">
                    <a href="#nowhere" style="padding-left:0px">关于青橙</a>
                    <a href="#nowhere">帮助中心</a>
                    <a href="#nowhere">开放平台</a>
                    <a href="#nowhere">诚聘英才</a>
                    <a href="#nowhere">联系我们</a>
                    <a href="#nowhere">网站合作</a>
                    <a href="#nowhere">法律声明</a>
                    <a href="#nowhere">知识产权</a>
                    <a href="#nowhere">廉正举报</a>
                    <a href="#nowhere">规则意见征集</a>
                </div>
                <div class="footer-tmallinfo">
                    <a href="#nowhere" style="padding-left:0px">青橙</a><span class="slash">|</span>
                    <a href="#nowhere">青橙网</a><span class="slash">|</span>
                    <a href="#nowhere">青橙</a><span class="slash">|</span>
                    <a href="#nowhere">聚划算</a><span class="slash">|</span>
                    <a href="#nowhere">全球速卖通</a><span class="slash">|</span>
                    <a href="#nowhere">青橙</a><span class="slash">|</span>
                    <a href="#nowhere">1688</a><span class="slash">|</span>
                    <a href="#nowhere">青橙妈妈</a><span class="slash">|</span>
                    <a href="#nowhere">飞猪</a><span class="slash">|</span>
                    <a href="#nowhere">阿里云计算</a><span class="slash">|</span>
                    <a href="#nowhere">AliOS</a><span class="slash">|</span>
                    <a href="#nowhere">青橙通信</a><span class="slash">|</span>
                    <a href="#nowhere">万网</a><span class="slash">|</span>
                    <a href="#nowhere">高德</a><span class="slash">|</span>
                    <a href="#nowhere">UC</a><span class="slash">|</span>
                    <a href="#nowhere">友盟</a><span class="slash">|</span>
                    <a href="#nowhere">虾米</a><span class="slash">|</span>
                    <a href="#nowhere">阿里星球</a><span class="slash">|</span>
                    <a href="#nowhere">来往</a><span class="slash">|</span>
                    <a href="#nowhere">钉钉</a><span class="slash">|</span>
                    <a href="#nowhere">支付宝</a>
                </div>

                <div class="footer-copyright">
                    增值电信业务经营许可证：<a href="#nowhere">石家庄B2-20110446</a>
                    网络文化经营许可证：<a href="#nowhere">石家庄[2015]0295-065号</a>
                    <a href="#nowhere">12318举报</a>
                    <span>互联网违法和不良信息举报电话：0571-81683755 blxxjb@alibaba-inc.com</span>
                    <br/>
                    互联网药品信息服务资质证书编号：<a href="#nowhere">石家庄-（经营性）-2019-2029</a>
                    <a href="#nowhere"><img src="${pageContext.request.contextPath}/static/img/fore/police-icon.png">石家庄公网安备 33010002000120号</a>
                    <span>（石家庄）网械平台备字[2018]第00002号</span>
                    <div class="copyRightYear">© 2003-2018 TMALL.COM 版权所有</div>
                    <div>
                        <img src="${pageContext.request.contextPath}/static/img/fore/copyRight1.jpg">
                        <img src="${pageContext.request.contextPath}/static/img/fore/copyRight2.jpg">
                    </div>
                </div>
            </div>

        </div>

    </div>
</div>
</body>
</html>