package handle.data;

/**
 * @author MXHstrat
 * @create 2021 - 10 - 09  12:22
 */

import save.data.*;

import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import javax.sql.*;

import javax.naming.*;



@WebServlet("/HandleLogin")
public class HandleLogin extends HttpServlet {

    public void Init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public void doPost(HttpServletRequest request,
                        HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        Connection con = null;
        Statement sql;
        String logname = request.getParameter("logname").trim();
        String password = request.getParameter("password").trim();

        password = Encrypt.encrypt(password, "javajsp");
        boolean boo = (logname.length() > 0 && password.length() > 0);

        try {

            Context context = new InitialContext();
            Context contextNeeded =
                    (Context) context.lookup("java:comp/env");

            DataSource ds =
                    (DataSource) contextNeeded.lookup("adverConn");
            con = ds.getConnection();

            String condition = "SELECT * FROM user where logname = '" + logname + "' and password = '" + password + "'";
            sql = con.createStatement();

            if (boo) {
                ResultSet rs = sql.executeQuery(condition);
                boolean m = rs.next();

                if (m == true) {

                    //调用登录成功的方法
                    Success(request, response, logname, password);
                    RequestDispatcher dispatcher =
                            request.getRequestDispatcher("login.jsp");
                    dispatcher.forward(request, response);

                } else {
                    String backNews = "您输入的用户名不存在或者密码不匹配！";
                    //调用登录失败的方法
                    fail(request, response, logname, backNews);
                }
            } else {
                String backNews = "请输入用户名和密码！";
                fail(request, response, logname, backNews);
            }
            con.close();

        } catch (NamingException e) {
            String backNews = "没有设置连接池！" + e;
            fail(request, response, logname, backNews);

        } catch (SQLException e) {
            String backNews = "" + e;
            fail(request, response, logname, backNews);
        } finally {
            try {
                con.close();
            } catch (Exception ee) {

            }
        }


    }

    private void Success(HttpServletRequest request, HttpServletResponse response, String logname, String password) {

        Login loginBean = null;
        HttpSession session = request.getSession(true);
        try {
            loginBean = (Login) session.getAttribute("loginBean");
            if (loginBean == null) {
                loginBean = new Login();
                session.setAttribute("loginBean", loginBean);
                loginBean = (Login) session.getAttribute("loginBean");

            }
            String name = loginBean.getLogname();
            if (name.equals(logname)) {
                loginBean.setBackNews(logname + "已经登录了");
                loginBean.setLogname(logname);
            } else {
                //数据模型存储到新的登录用户
                loginBean.setBackNews(logname + "登录成功");
                loginBean.setLogname(logname);
            }
        } catch (Exception ee) {

            loginBean = new Login();
            session.setAttribute("loginBean", loginBean);
            loginBean.setBackNews("" + ee);
            loginBean.setLogname(logname);
        }
    }

    private void fail(HttpServletRequest request, HttpServletResponse response, String logname, String backNews) {

        response.setContentType("text/html;charset = utf-8");

        try {
            PrintWriter out = response.getWriter();
            out.println("<html><body><center>");
            out.println("<h2>"+logname+"登录反馈结果<br>"+backNews+"</h2>");
            out.println("返回登录页面或者主页<br>");
            out.println("<a href = login.jsp>登录页面</a>");
            out.println("<br><a href = index.jsp>主页</a>");
            out.println("</center></body></html>");


        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
