package handle.data;

import com.sun.org.apache.xml.internal.security.Init;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import javafx.scene.Scene;
import javafx.scene.control.RadioMenuItem;
import jdk.nashorn.internal.ir.WhileNode;
import save.data.*;
import sun.security.util.Password;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 * @author MXHstrat
 * @create 2021 - 10 - 09  21:17
 */

@WebServlet("/upFile")
public class UpFile extends HttpServlet {

    public void Init(ServletConfig config) throws ServletException {
        super.init(config);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String backMess = "";
        req.setCharacterEncoding("utf-8");

        Login loginBean = null;

        HttpSession session = req.getSession();
        String fileName = null;

        try {
            loginBean = (Login) session.getAttribute("loginBean");
            if (loginBean == null) {
                resp.sendRedirect("login.jsp");
                return;
            } else {
                boolean b = loginBean.getLogname() == null ||
                        loginBean.getLogname().length() == 0;
                if (b) {
                    resp.sendRedirect("login.jsp");
                    return;
                }
            }
        } catch (Exception ee) {
            resp.sendRedirect("login.jsp");
            return;
        }

        try {
            String tempFileName = (String) session.getId();
            String webDir = req.getContextPath();
            webDir = webDir.substring(1);
            File f = new File("");

            String path = f.getAbsolutePath();
            int index = path.indexOf("bin");
            String tomcatDir = path.substring(0, index);


            File dir = new File(tomcatDir + "/web/" + webDir + "/image");
            dir.mkdir();
            File fileTemp = new File(dir, tempFileName);
            RandomAccessFile randomWrite = new RandomAccessFile(fileTemp, "rw");

            InputStream in = req.getInputStream();

            byte b[] = new byte[10000];
            int n;
            while ((n = in.read(b)) != -1) {
                randomWrite.write(b, 0, n);
            }

            randomWrite.close();
            in.close();
            RandomAccessFile randomRead = new RandomAccessFile(fileTemp, "r");
            int second = 1;
            String secondLine = null;
            while (second <= 2) {
                secondLine = randomRead.readLine();
                second++;
            }

            int position = secondLine.lastIndexOf("=");
            fileName = secondLine.substring(position + 2, secondLine.length() - 1);
            randomRead.seek(0);
            long forthEndPosition = 0;
            int forth = 1;
            while ((n = randomRead.readByte()) != -1 && (forth <= 4)) {
                if (n == '\n') {
                    forthEndPosition = randomRead.getFilePointer();
                    forth++;
                }
            }

            byte cc[] = fileName.getBytes("iso-8859-1");
            fileName = new String(cc, "utf-8");
            fileName = (loginBean.getLogname()).concat(fileName);

            File fileUser = new File(dir, fileName);
            randomRead.seek(randomRead.length());

            long endPosition = randomRead.getFilePointer();
            long mark = endPosition;
            int j = 1;

            while ((mark >= 0) && (j <= 6)) {
                mark--;
                randomRead.seek(mark);
                n = randomRead.readByte();
                if (n == '\n') {
                    endPosition = randomRead.getFilePointer();
                    j++;
                }
            }

            randomRead.seek(forthEndPosition);
            long startPoint = randomRead.getFilePointer();
            while (startPoint < endPosition - 1) {
                n = randomRead.readByte();
                randomWrite.write(n);
                startPoint = randomRead.getFilePointer();

            }

            randomWrite.close();
            randomRead.close();
            backMess = "上传成功！";
            fileTemp.delete();


        } catch (Exception ee) {
            backMess = "没有选择文件或者上传失败！";

        }

        resp.setContentType("text/html;charset = utf-8");
        try {
            PrintWriter out = resp.getWriter();
            out.println("<html><body><center>");
            out.println("<h2>" + loginBean.getLogname() + "：" + backMess + "</h2>");
            out.println("<br>返回主页");
            out.println("<br><a href = index.jsp>主页</a>");
            out.println("</center></body></html>");
        } catch (IOException ee) {

        }
    }
}
