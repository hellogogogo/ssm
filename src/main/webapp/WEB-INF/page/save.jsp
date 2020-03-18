<%--
  Created by IntelliJ IDEA.
  User: my-deepin
  Date: 18-4-14
  Time: 下午4:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<html>
<link rel="stylesheet" href="<%=basePath%>/lib/bootstrap.min.css"/>
<head>
    <title>添加客户功能页面</title>
</head>
<body>
<!-- 导航栏 -->
<div class="sidebar text-left">
    <nav class="navbar navbar-default" role="navigation">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand">SSM整合</a>
            </div>
            <div>
                <ul class="nav navbar-nav">
                    <li><a href="<%=basePath%>/customer/toSavePage"><strong>添加信息功能</strong></a></li>
                    <li><a href="<%=basePath%>/customer/toListPage"><strong>分页查询功能</strong></a></li>
                    <li><a>Create by TyCoding</a></li>
                </ul>
            </div>
        </div>
    </nav>
</div>
<div class="container">
    <h1 class="text-center">客户信息添加页面</h1>
    <hr/>
    <br/>
    <form class="form-inline text-center" action="<%=basePath%>/customer/save" method="post">
        <div class="form-group form-inline">
            <label>客户姓名：</label>
            <input type="text" name="name" class="form-control"/>
        </div>
        <br/>
        <br/>
        <div class="form-group form-inline">
            <label>客户电话：</label>
            <input type="text" name="telephone" class="form-control"/>
        </div>
        <br/>
        <br/>
        <div class="form-group form-inline">
            <label>客户住址：</label>
            <input type="text" name="address" class="form-control"/>
        </div>
        <br/>
        <br/>
        <div class="form-group form-inline">
            <label>客户备注：</label>
            <input type="text" name="remark" class="form-control"/>
        </div>
        <br/>
        <br/>
        <br/>
        <input type="submit" class="btn btn-info text-center"/>
        <input type="reset" class="btn btn-info text-center"/>
    </form>

    <%--上传--%>
    <form action="<%=basePath%>/load/upload" method="post" enctype="multipart/form-data">
        <center>
            <input type="file" name="image">
            <input type="submit" value="提交上传文件">
        </center>
        <form>
            <center>
                <h2>文件下载</h2>
                <a href="<%=basePath%>/load/download?fileName=GG123.jpg">点击我下载图片</a>
            </center>
            <br>
            <center>
                <img src="<%=basePath%>/resources/upload/GG123.jpg">
            </center>
            <center>
                <div>
                    <a href="<%=basePath%>/Redis/insert?key=time&value=2020年3月8日00:06:18">插入缓存</a>
                </div>
                <div>
                    <a href="<%=basePath%>/Redis/delete?key=time">删除缓存</a>
                </div>
                <div>
                    <a href="<%=basePath%>/Redis/query?key=time">查询缓存11</a>
                </div>
            </center>
            <br>
            <center>
                <div>
                    <a href="<%=basePath%>/sync/test?s=yes">并发测试1</a>
                </div>
                <div>
                    <a href="<%=basePath%>/sync/test?s=no">并发测试2</a>
                </div>
            </center>

</div>

</body>
</html>
