<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 29907
  Date: 2019/10/13
  Time: 18:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<div class="container-fluid">
    <div class="col-md-4">
        <img src="${pageContext.request.contextPath}/img/logo2.png"/>
    </div>
    <div class="col-md-5">
        <img src="${pageContext.request.contextPath}/img/header.png"/>
    </div>
    <div class="col-md-3" style="padding-top:20px">
        <ol class="list-inline">
            <c:if test="${username==null}">
                <li><a href="${pageContext.request.contextPath}/jsp/login.jsp">

                    登录

                </a>
                </li>
            </c:if>
            <c:if test="${username!=null}">
                ${username}:你好   &nbsp; <a href="${pageContext.request.contextPath}/user?method=logOut"> 退出</a>
            </c:if>

            <c:if test="${username==null}">
                <li>

                    <a href="${pageContext.request.contextPath}/jsp/register.jsp">
                        注册
                    </a>
                </li>
            </c:if>

            <c:if test="${username!=null}">
             <a href="${pageContext.request.contextPath}/jsp/order_list.jsp">我的订单</a>
            </c:if>

            <li><a href="${pageContext.request.contextPath}/jsp/cart.jsp">购物车</a></li>
        </ol>
    </div>
</div>
<div class="container-fluid">
    <nav class="navbar navbar-inverse">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">首页</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul  id="menuId" class="nav navbar-nav">
                    <%--<c:forEach var="c" items="${c}">--%>
                        <%--<li><a href="#">${c.cname}</a></li>--%>
                        <%--</c:forEach>--%>
                </ul>
                <form class="navbar-form navbar-right" role="search">
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="Search">
                    </div>
                    <button type="submit" class="btn btn-default">Submit</button>
                </form>

            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container-fluid -->
    </nav>
</div>
<%--<script>--%>
  <%--$(function () {--%>
      <%--$.get("${pageContext.request.contextPath}/category?method=findAll",function (data) {--%>
              <%--var t=$("#menuId");--%>

             <%--alert(data)--%>
             <%--// $(data).each( function () {--%>
             <%--//        t.append("<li><a href='#'>"+this.cname+"</a></li>")--%>
             <%--//      })--%>
      <%--},"json");--%>

  <%--});--%>



<%--</script>--%>
<script>
    $(function(){
        //发送ajax请求
        $.get("${pageContext.request.contextPath}/category?method=findAll",function(data){
            //获取menu的ul标签
            var $ul=$("#menuId");

            //遍历数组
            $(data).each(function(){
                $ul.append($("<li><a href='${pageContext.request.contextPath}/product?method=findByPage&cid="+this.cid+"&currentPage=1'>"+this.cname+"</a></li>"));
            });
        },"json");
    });
</script>