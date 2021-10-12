<%--
  Created by IntelliJ IDEA.
  User: 天下第一苗人凤
  Date: 2021/10/8
  Time: 15:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:useBean id="userBean" class="save.data.Register" scope="request"/>


<html>
<head>
    <title>注册页面</title>
</head>


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
<style>

    #ok{
        font-size: 26px;
        font-family: 宋体;
        color: black;
    }
</style>

<body bgcolor="#ffc0cb"id="ok">

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

<center>


    <form action="HandleRegister" id="ok"  method="post">
        用户名由字母、数字下划线组成，*注释的项必须填写！<br>
        *用户名称：<input type="text" id="ok" name="logname"/><br>
        *用户密码：<input type="password" id="ok" name="password"/><br>
        *再次输入：<input type="password" id="ok" name="again_password"/><br>
        <input type="submit" id="ok" value="提交"><br>

    </form>
    注册反馈：
    <jsp:getProperty name="userBean" property="logname"/>
    <jsp:getProperty name="userBean" property="backNews"/>
</center>
</body>
</html>
