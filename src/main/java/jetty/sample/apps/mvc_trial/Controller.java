package jetty.sample.apps.mvc_trial;

import java.io.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

@WebServlet(urlPatterns = "/mvc_trial/controller")
public class Controller extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException{
        RequestDispatcher dispatcher = request.getRequestDispatcher("/mvc_trial/view");
        dispatcher.forward(request, response);
    }
}
