<%--
  Created by IntelliJ IDEA.
  User: 天下第一苗人凤
  Date: 2021/10/8
  Time: 15:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

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
        font-family: 宋体;
        font-size: 26px;
        color: black;
    }
</style>

<head>
    <title>发布广告界面</title>
</head>
<body id="ok" bgcolor="#ffc0cb">


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




<form action="upFile" method="post" ENCTYPE="multipart/form-data">
    <input type="file" name="file" id="ok" size="45"/><<br>
    <input type="submit" value="提交" name="submit" id="ok">



</form>

</body>
</html>
