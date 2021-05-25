<%--
  Created by IntelliJ IDEA.
  User: 联想
  Date: 2020/2/14
  Time: 12:05
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改信息</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- 引入 Bootstrap -->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">

    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1>
                    <small>修改信息</small>
                </h1>
            </div>
        </div>
    </div>

    <form action="${pageContext.request.contextPath}/book/updateBook" method="post">

        <div class="form-group">
            <label for="bkname">书籍名称</label>
            <input type="text" name="name" class="form-control" id="bkname" value="${book.getName()}" placeholder="BookName" required>
        </div>

        <div class="form-group">
            <label for="bkcount">书籍数量</label>
            <input type="text" name="counts" class="form-control" id="bkcount" value="${book.getCounts()}" placeholder="BookName" required>
        </div>

        <div class="form-group">
            <label for="bkdetail">书籍描述</label>
            <input type="text" name="detail" class="form-control" id="bkdetail" value="${book.getDetail() }" placeholder="BookName" required>
        </div>

        <div class="form-group">
            <input type="submit" class="form-control" value="修改">
        </div>

        <input type="hidden" name="id" value="${book.getId()}"/>
    </form>

</div>
