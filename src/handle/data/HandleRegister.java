
package handle.data;

import save.data.*;

import java.sql.*;
import java.io.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.*;
import javax.naming.*;

/**
 * @author MXHstrat
 * @create 2021 - 10 - 08  15:28
 */

@WebServlet("/HandleRegister")
public class HandleRegister extends HttpServlet {
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public void doPost(HttpServletRequest request,
                        HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        Connection con = null;
        PreparedStatement sql = null;
        Register userBean = new Register();
        request.setAttribute("userBean", userBean);

        String logname = request.getParameter("logname").trim();
        String password = request.getParameter("password").trim();
        String again_psw = request.getParameter("again_password").trim();


        if (logname == null) {
            logname = "";
        }
        if (password == null) {
            password = "";
        }
        if (!password.equals(again_psw)) {
            userBean.setBackNews("两次密码不同，注册失败！");
            RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
            dispatcher.forward(request, response);//转发
            return;
        }


        boolean isLD = true;
        for (int i = 0; i < logname.length(); i++) {
            char c = logname.charAt(i);
            if (!(Character.isLetterOrDigit(c) || c == '_')) {
                isLD = false;
            }

            boolean boo = logname.length() > 0 && password.length() > 0 && isLD;
            String backNews = "";
            try {
                Context context = new InitialContext();
                Context contextNeeded=
                        (Context)context.lookup("java:comp/env");

                DataSource ds =
                        (DataSource)contextNeeded.lookup("adverConn");

                con = ds.getConnection();
                String insertCondition  = "INSERT INTO user VALUES(?,?)";
                sql = con.prepareStatement(insertCondition);


                if(boo){
                    sql.setString(1,logname);
                    password =
                            Encrypt.encrypt(password,"javajsp");
                    sql.setString(2,password);

                    int m = sql.executeUpdate();
                    if (m !=0) {

                        backNews = "注册成功！";

                        userBean.setBackNews(backNews);
                        userBean.setLogname(logname);
                    }
                }
                else{
                    backNews = "信息填写不完整或者名字中有非法字符！";
                    userBean.setBackNews(backNews);
                }
                con.close();
            }

            catch (SQLException exp){

                backNews = "该会员名字已经被占用，请重新输入！"+exp;
                userBean.setBackNews(backNews);
            } catch (NamingException e) {
                backNews = "没有设置连接池"+e;
                userBean.setBackNews(backNews);

            }

            finally {
                try {
                    con.close();
                }
                catch (Exception ee){
                    RequestDispatcher dispatcher =
                            request.getRequestDispatcher("register.jsp");
                    dispatcher.forward(request,response);
                }
            }


        }

    }

}

