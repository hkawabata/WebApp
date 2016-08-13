package jetty.sample.apps;

import java.io.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

@WebServlet(urlPatterns = "/request/response")
public class RequestResponseApp extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException{
        response.setContentType("application/json; charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String pass = request.getParameter("pass");
            String resString = String.format("your ID is %d, your password is %s", id, pass);
            out.println(String.format("{\"id\": %d, \"pass\": \"%s\", \"resString\": \"%s\"}", id, pass, resString));
        } catch(Exception e) {
            //e.printStackTrace();
            String cls = e.getClass().getName();
            String msg = e.getLocalizedMessage();
            out.println(String.format("{'error': '%s: %s'}", cls, msg));
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException{
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String pass = request.getParameter("pass");
            String resString = String.format("your ID is %d, your password is %s", id, pass);
            out.println("<html><body>" + resString + "</body></html>");
        } catch(Exception e) {
            //e.printStackTrace();
            String cls = e.getClass().getName();
            String msg = e.getLocalizedMessage();
            out.println(String.format("<html><body>internal server error:<br/>%s<br/>%s</body></html>", cls, msg));
        }
    }
}
