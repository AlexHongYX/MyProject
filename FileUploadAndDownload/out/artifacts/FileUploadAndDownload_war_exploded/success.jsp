<%--
  Created by IntelliJ IDEA.
  User: xiaoaxiao
  Date: 2019/4/21
  Time: 16:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>上传成功</title>
</head>
<body>
    <h2>Success Upload</h2>
    <a href="download?filename=${filename}">DownLoad</a><br/>
    ${errorResult}
</body>
</html>
