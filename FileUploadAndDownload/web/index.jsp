<%--
  Created by IntelliJ IDEA.
  User: xiaoaxiao
  Date: 2019/4/21
  Time: 14:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>文件上传下载</title>
  </head>
  <body>
    <form action="upload" enctype="multipart/form-data" method="post">
      请选择文件：<input type="file" id="myfile" name="myfile"/>
      <input type="submit" value="upload"/>
    </form>
  </body>
</html>
