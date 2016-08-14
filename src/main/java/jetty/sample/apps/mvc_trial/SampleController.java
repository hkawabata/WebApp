package jetty.sample.apps.mvc_trial;

import java.io.*;
import java.net.URLEncoder;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

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
            switch (forwardOrRedirect) {
                case "forward":
                    // view ページへフォワード
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/mvc_trial/view/forward");
                    request.setAttribute("attr", bean.getAfterString());
                    dispatcher.forward(request, response);
                    break;
                case "redirect":
                    // view ページへリダイレクト
                    String url = "/mvc_trial/view/redirect?attr=" + URLEncoder.encode(bean.getAfterString(), "UTF-8");
                    response.sendRedirect(url);
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
