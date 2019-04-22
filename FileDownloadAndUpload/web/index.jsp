<%--
  Created by IntelliJ IDEA.
  User: xiaoaxiao
  Date: 2019/4/22
  Time: 23:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>FileDownloadAndUpload</title>
  </head>
  <body>
    <form action="upload" enctype="multipart/form-data" method="post">
        <input type="file" name="myfile"/><br/>
        <input type="submit" value="uploadFile"/>
    </form>
    <br/>
    <br/>
    <a href="download?file=${filename}">downloadFile</a>
  </body>
</html>
