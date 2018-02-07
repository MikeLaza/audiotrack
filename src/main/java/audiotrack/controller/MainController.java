package audiotrack.controller;


import audiotrack.controller.command.ActionCommand;
import audiotrack.controller.command.impl.util.CookieHelper;
import audiotrack.controller.command.impl.util.ParameterEnum;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MainController extends HttpServlet {
    private  final CommandProvider commandProvider=new CommandProvider();

    private static Logger logger = Logger.getLogger(MainController.class);

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handleRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handleRequest(req, resp);
    }

    private void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String commandName=req.getParameter(ParameterEnum.COMMAND.toString());
        handleCommandNameCooke(req, resp, commandName);
        ActionCommand mainCommand=commandProvider.takeCommand(commandName);
        mainCommand.execute(req,resp);
    }

    private void handleCommandNameCooke(HttpServletRequest req, HttpServletResponse resp, String commandName) {
        final String commandUpCase = commandName.toUpperCase();
        if(commandUpCase.equals(Commands.ADD_TRACK.toString())||
           commandUpCase.equals(Commands.SHOW_TRACKS.toString())||
           commandUpCase.equals(Commands.SHOW_USERS.toString()) ||
           commandUpCase.equals(Commands.SHOW_CLIENT_TRACKS.toString())
                ){

            String cookieCommand = ParameterEnum.COOKIE_COMMAND.toString();

            Cookie cookie = CookieHelper.getCookie(req, cookieCommand);
            if(cookie!=null){
                cookie.setValue(commandName);
                resp.addCookie(cookie);
            }else {
                Cookie newCookie=new Cookie(cookieCommand, commandName);
                resp.addCookie(newCookie);
            }
        }
    }
}
