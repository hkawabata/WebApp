package jetty.sample.apps.mvc_trial;

import java.io.*;
import java.net.URLEncoder;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

/**
 * MVC のコントローラに相当するクラス
 *
 * Created by kawabatahiroto on 2016/08/14.
 */
@WebServlet(urlPatterns = "/mvc_trial/controller")
public class SampleController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException{
        String forwardOrRedirect = request.getParameter("forwardOrRedirect");
        String text = request.getParameter("text");
        try {
            SampleBean bean = new SampleBean();
            bean.setBeforeString(text);
            SampleLogic.execute(bean);
            String urlPattern;
            switch (forwardOrRedirect) {
                case "forward":
                    // view ページへフォワード
                    urlPattern = "/mvc_trial/view/forward";
                    RequestDispatcher dispatcher = request.getRequestDispatcher(urlPattern);
                    request.setAttribute("attr", bean);
                    dispatcher.forward(request, response);
                    break;
                case "redirect":
                    // view ページへリダイレクト
                    urlPattern = "/mvc_trial/view/redirect?attr=" + URLEncoder.encode(bean.getAfterString(), "UTF-8");
                    response.sendRedirect(urlPattern);
                    break;
                case "redirect_ss":
                    // view ページへリダイレクト（セッションスコープに Bean を保存）
                    HttpSession session = request.getSession();
                    session.setAttribute("attr", bean);
                    urlPattern = "/mvc_trial/view/redirect_ss";
                    response.sendRedirect(urlPattern);
                    break;
            }
        } catch (NullPointerException e) {
            // 必要なパラメータが指定されていないため元のページへフォワード
            RequestDispatcher dispatcher = request.getRequestDispatcher("/mvc_trial?badParameter=true");
            dispatcher.forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException{
        doGet(request, response);
    }
}
