package audiotrack.controller.command.impl.util;

import audiotrack.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ErrorLogger {

    public static void logError(Logger logger,HttpServletRequest req, ServiceException e){
        String errorInfo = ParameterEnum.ERROR_INFO.toString();
        Throwable cause = e.getCause();
        String message = cause.getMessage();
        req.setAttribute(errorInfo, message);

        HttpSession session = req.getSession();
        String login = ParameterEnum.LOGIN.toString();
        Object attribute = session.getAttribute(login);
        logger.error(attribute, e);
    }

}
