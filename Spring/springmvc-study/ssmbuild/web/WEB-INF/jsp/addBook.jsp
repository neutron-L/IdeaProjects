<%--
  Created by IntelliJ IDEA.
  User: 联想
  Date: 2020/2/14
  Time: 10:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>新增书籍</title>

    <!--BootStrap美化界面-->
    <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container">

    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1 align="center">
                    <small>书籍添加</small>
                </h1>
            </div>
        </div>
    </div>

    <form action="${PageContext.request.contextPath}/book/addBook" method="get">
        <div class="form-group">
            <label for="bkname">书籍名称</label>
            <input type="text" name="name" class="form-control" id="bkname" placeholder="BookName" required>
        </div>

        <div class="form-group">
            <label for="bkcount">书籍数量</label>
            <input type="text" name="counts" class="form-control" id="bkcount" placeholder="BookName" required>
        </div>

        <div class="form-group">
            <label for="bkdetail">书籍描述</label>
            <input type="text" name="detail" class="form-control" id="bkdetail" placeholder="BookName" required>
        </div>

        <div class="form-group">
            <input type="submit" class="form-control" value="添加">
        </div>
    </form>

</div>

</body>
</html>
