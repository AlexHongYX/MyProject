<%--
  Created by IntelliJ IDEA.
  User: xiaoaxiao
  Date: 2019/4/17
  Time: 22:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<html>
  <head>
    <title>scoreBoard</title>
    <script src="index.js"></script>
  </head>
  <body>
    <form>
      <tr>
        <td>id:</td>
        <td><input type="text" id="id" value="" onkeyup="Change(this.value)"/></td>
      </tr><br/>
    </form>
    <p>name: <span id="name"></span></p>
  </body>
</html>
