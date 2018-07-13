package jetty.sample.apps.keijiban;

import jetty.sample.apps.keijiban.bean.Post;
import jetty.sample.apps.keijiban.bean.UserInfo;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * 掲示板への投稿を受けて、リストをアップデート
 *
 * Created by kawabatahiroto on 2016/08/28.
 */
@WebServlet(urlPatterns = "/keijiban/post")
public class KeijibanPost extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        ServletContext application = getServletContext();
        HttpSession session = request.getSession();
        String postText = request.getParameter("post");
        String user = ((UserInfo) session.getAttribute("userInfo")).getName();
        List<Post> posts = (List<Post>) application.getAttribute("posts");
        Post post = new Post(posts.size(), user, postText);
        posts.add(0, post);

        String urlPattern = "/keijiban";
        response.sendRedirect(urlPattern);
    }
}
