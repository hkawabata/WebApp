package jetty.sample.apps.keijiban;

import jetty.sample.apps.keijiban.bean.UserInfo;
import jetty.sample.apps.keijiban.model.LoginLogic;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * ログイン用のサーブレット
 *
 * Created by kawabatahiroto on 2016/08/28.
 */
@WebServlet(urlPatterns = "/keijiban/login")
public class KeijibanLogin extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        ServletContext application = getServletContext();
        HttpSession session = request.getSession();

        // UserInfo 生成
        String name = request.getParameter("name");
        String pass = request.getParameter("pass");
        UserInfo userInfo = new UserInfo(name, pass);

        if (LoginLogic.loginCheck(userInfo)) {
            session.setAttribute("userInfo", userInfo);
            String urlPattern = "/keijiban";
            response.sendRedirect(urlPattern);
        } else {
            session.setAttribute("loginError", "true");
            session.setAttribute("loginErrMsg", "ユーザ名あるいはパスワードが間違っています");
            String urlPattern = "/keijiban";
            response.sendRedirect(urlPattern);
        }
    }
}
