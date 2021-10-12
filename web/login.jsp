<%--
  Created by IntelliJ IDEA.
  User: 天下第一苗人凤
  Date: 2021/10/8
  Time: 15:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="loginBean" class="save.data.Login" scope="session"/>
<html>

<style>
    #ok{
        font-size: 30px;
        font-family: 宋体;
        color: black;
    }
</style>
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
<head>
    <title>登录页面</title>
</head>
<body bgcolor="#ffc0cb" id="ok">


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



<center >
    <form action="HandleLogin" method="post">
        登录用户：<input type="text" id="ok" name="logname" size="12"/><br>
        输入密码：<input type="password" id="ok" name="password" size="12"/><br>

        <input type="submit" id="ok" value="提交"><<br>
        登录反馈信息：<<br>
        登录名称：<<br>
        <jsp:getProperty name="loginBean" property="logname"/>
        <jsp:getProperty name="loginBean" property="backNews"/>
    </form>
</center>
</body>
</html>
