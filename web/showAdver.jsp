<%@ page import="com.sun.java.util.jar.pack.ConstantPool" %><%--
  Created by IntelliJ IDEA.
  User: 天下第一苗人凤
  Date: 2021/10/10
  Time: 14:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="play" class="save.data.Play" scope="session"/>
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

    #ok {
        font-family: 宋体;
        font-size: 20px;
        color: blue;
    }

</style>

<%
    request.setCharacterEncoding("utf-8");

%>
<%
    String logname = request.getParameter("logname");
    String webDir = request.getContextPath();
    webDir = webDir.substring(1);
%>
<jsp:setProperty name="play" property="logname" value="<%=logname%>"/>
<jsp:setProperty name="play" property="webDir" value="<%=webDir%>"/>
<jsp:setProperty name="play" property="index" param="index"/>

<head>
    <title>浏览广告图</title>

</head>
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

<center>
    <p id="ok">
        <br><%=play.logname%>的广告：<br>
    </p>
    <img src=
                 image/<jsp:getProperty name="play"
                                   property="showImage"/>  height="300" width="200"/>
    <br>
    <a href="?index=<%=play.getIndex()+1%>&logname= <%=play.logname%>">下一张</a>
    <a href="?index=<%=play.getIndex()-1%>&logname=<%=play.logname%>">上一张</a>

</center>
</body>
</html>
