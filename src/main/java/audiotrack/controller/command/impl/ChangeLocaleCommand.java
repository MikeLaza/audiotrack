package audiotrack.controller.command.impl;

import audiotrack.controller.PageEnum;
import audiotrack.controller.command.ActionCommand;
import audiotrack.controller.command.impl.util.CookieHelper;
import audiotrack.controller.command.impl.util.ErrorLogger;
import audiotrack.controller.command.impl.util.ForwardRedirectHelper;
import audiotrack.controller.command.impl.util.ParameterEnum;
import audiotrack.service.UserService;
import audiotrack.service.exception.ServiceException;
import audiotrack.service.factory.ServiceFactory;
import org.apache.log4j.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ChangeLocaleCommand implements ActionCommand {
    private static Logger logger = Logger.getLogger(ChangeLocaleCommand.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private static final String COMMAND="audiotrack?command=";

    public ChangeLocaleCommand() {
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)  {
        PageEnum page = handleQuery(request, response);
        Cookie lastCommandCookie=CookieHelper.getCookie(request,ParameterEnum.COOKIE_COMMAND.toString());
        if(lastCommandCookie!=null){
            handleQueryWithLastCommand(response, lastCommandCookie);
        } else {
            ForwardRedirectHelper.provideForward(request, response,page.toString());
        }

    }

    private void handleQueryWithLastCommand(HttpServletResponse response, Cookie lastCommandCookie) {
        String commandName=lastCommandCookie.getValue();
        String commandReq= COMMAND+commandName;
        ForwardRedirectHelper.provideRedirect(response,commandReq);
    }

    private PageEnum handleQuery(HttpServletRequest request, HttpServletResponse response) {
        String locale = request.getParameter(ParameterEnum.LOCAL.toString());
        HttpSession session = request.getSession();
        String login = (String) session.getAttribute(ParameterEnum.LOGIN.toString());


        String pageStr = request.getParameter(ParameterEnum.PAGE.toString());
        String pageUpperCase = pageStr.toUpperCase();
        PageEnum page = PageEnum.valueOf(pageUpperCase);
        handle(request, response, locale, session, login, page);
        return page;
    }

    private void handle(HttpServletRequest request, HttpServletResponse response, String locale, HttpSession session, String login, PageEnum page) {
        try {
            if (login != null) {
                handleAuthenticatedClient( locale, session, login);
            }
            else {
                handleUnauthenticatedClient(request, response, locale, page);
            }
        } catch (ServiceException e) {
            ErrorLogger.logError(logger,request,e);
        }
    }

    private void handleAuthenticatedClient( String locale, HttpSession session, String login) throws ServiceException {
        UserService userService = serviceFactory.getUserService();
        userService.updateLocale(login, locale);
        session.setAttribute(ParameterEnum.USER_LOCAL.toString(), locale);
    }

    private void handleUnauthenticatedClient(HttpServletRequest request, HttpServletResponse response, String locale, PageEnum page) throws ServiceException {
        Cookie cookie = CookieHelper.getCookie(request, ParameterEnum.LOGIN.toString());
        HttpSession session = request.getSession();
        if (cookie != null) {
            handleAuthorizedClient(locale, cookie, session);
        }else {
            handleUnauthorizedClient(response, locale, session);
        }



    }

    private void handleAuthorizedClient(String locale, Cookie cookie, HttpSession session) throws ServiceException {
        UserService userService = serviceFactory.getUserService();
        userService.updateLocale(cookie.getValue(), locale);
        session.setAttribute(ParameterEnum.WELCOME_LOCAL.toString(), locale);
    }

    private void handleUnauthorizedClient(HttpServletResponse response, String locale, HttpSession session) {
        PageEnum page;
        session.setAttribute(ParameterEnum.WELCOME_LOCAL.toString(), locale);
        Cookie cookieWelcomeLocale = new Cookie(ParameterEnum.WELCOME_LOCAL.toString(), locale);
        response.addCookie(cookieWelcomeLocale);
        page = PageEnum.WELCOME_PAGE;
    }
}
