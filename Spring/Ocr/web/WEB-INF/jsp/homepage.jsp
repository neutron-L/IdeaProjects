<%--
  Created by IntelliJ IDEA.
  User: lilin
  Date: 2021/5/24
  Time: 下午4:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script src="jquery.js"></script>

</head>
<body>
<div>
    <form method="post" action="${pageContext.request.contextPath}/record/upload">
        <div id="result" align="left">
            <div id="imgDiv" style="display: none;">
                <img id="img" name="photo" style="height: 300px;width: 250px;margin-left: 10%;"/>
            </div>
        </div>
        <div id="info" align="right">
            choose photo:
            <input type="file" id="file" name="file" capture="camera" accept="image/jpeg,image/png,image/jpeg,image/gif">
        </div>
        <input name="url" type="submit" value="method1">
        <input name="url" type="submit" value="method2">
        <input name="url" type="submit" value="method3">
    </form>
    <textarea>
        ${msg}
    </textarea>
</div>
<script>
    $("#file").change(function(event) {
        var files = event.target.files, file;
        if (files && files.length > 0) {

            file = files[0];
            if (file.size > 1024 * 1024 * 2) {
                alert('图片大小不能超过 2MB!');

                return false;
            }
            var URL = window.URL || window.webkitURL;

            var imgURL = URL.createObjectURL(file);
            $("#img").attr("src", imgURL);
            $("#imgDiv").show();
        }
    });
</script>
</body>
</html>
