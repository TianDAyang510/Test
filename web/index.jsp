<%@ page pageEncoding="utf-8" %>

<%--
  Created by IntelliJ IDEA.
  User: 天下第一苗人凤
  Date: 2021/10/8
  Time: 11:36
  To change this template use File | Settings | File Templates.

--%>


<%--<%@ page contentType="text/html;charset=gb2312" language="java" %>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <title>小星星广告网</title>


    <%--<%@include file="head.txt"%>--%>

    <style>
        #jerry {
            font-size: 58px;
            font-family: 隶书;
            color: blue;
        }

        #tom {
            font-family: 楷体;
            font-size: 33px;
            color: blue;
        }

    </style>



</head>
<style>
    #ok {
        font-family: 楷体;
        font-size: 50px;
        color: green;
    }
</style>
<html>


<body bgcolor="#ffc0cb">
<div align="center">
    <p id="jerry">小星星广告网</p>

    <table width="600" align="center" border="0">
        <tr valign="bottom">
            <td id="tom"><a href="register.jsp">注册</a></td>
            <td id="tom"><a href="login.jsp">登录</a></td>
            <td id="tom"><a href="publishAdver.jsp">发布广告</a></td>
            <td id="tom"><a href="browseAdver.jsp">浏览广告</a></td>
            <td id="tom"><a href="index.jsp">主页</a></td>
        </tr>
    </table>
</div>

<center id="ok">欢迎注册，发布广告</center>

</body>
</html>
