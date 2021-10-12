<%--
  Created by IntelliJ IDEA.
  User: 天下第一苗人凤
  Date: 2021/10/8
  Time: 15:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.sql.*" %>
<%@ page import="javax.sql.DataSource" %>
<%@ page import="javax.naming.*" %>
<%@page import="java.lang.*" %>


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
        font-size: 26px;
        color: black;
    }

</style>

<html>
<head>
    <title>浏览广告页面</title>
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


<%
    Context context = new InitialContext();
    Context contextNeeded = (Context) context.lookup("java:comp/env");
    DataSource ds = (DataSource) contextNeeded.lookup("adverConn");
    Connection con = null;
    Statement sql;
    ResultSet rs;

    try {

        con = ds.getConnection();
        sql = con.createStatement();
        String SQL = "SELECT logname FROM user";
        rs = sql.executeQuery(SQL);

        while (rs.next()){
            String logname = rs.getString(1);

            out.print("<br><a href = showAdver.jsp?logname="+logname+">浏览"+
                    logname+"发布的广告</a>");
        }
        con.close();
    }catch (SQLException ee){

        out.print("<h1>"+ee);
    }

    finally {
        try {
            con.close();
        }catch (Exception ee){}
    }
%>

</body>
</html>
