package audiotrack.controller.command.impl.util;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ForwardRedirectHelper {

    public static  void provideForward(HttpServletRequest request, HttpServletResponse response,String path) {
        RequestDispatcher dispatcher = request.getRequestDispatcher(path);
        if (dispatcher != null) {
            try {
                dispatcher.forward(request, response);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }

        }else {
            writeErrorPage(response);
        }
    }

    public static void provideRedirect( HttpServletResponse response, String path)  {
        try {
            response.sendRedirect(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void writeErrorPage(HttpServletResponse response){

        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        out.write("<html><head><title>Error</title></head><body>");


    }

}
