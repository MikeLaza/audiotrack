package audiotrack.controller.command.impl;

import audiotrack.bean.User;
import audiotrack.controller.PageEnum;
import audiotrack.controller.command.ActionCommand;
import audiotrack.controller.command.impl.util.ErrorLogger;
import audiotrack.controller.command.impl.util.ForwardRedirectHelper;
import audiotrack.controller.command.impl.util.ParameterEnum;
import audiotrack.service.exception.ServiceException;
import audiotrack.service.factory.ServiceFactory;
import audiotrack.service.impl.UserServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowAllUsersCommand implements ActionCommand{


    private static Logger logger = Logger.getLogger(ShowAllUsersCommand.class);
    private static final int RECORDS_PER_PAGE = 5;
    private static final int FIRST = 1;

    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp)  {
        prepareUserList(req);
        String path = PageEnum.SHOW_USERS.toString();
        ForwardRedirectHelper.provideForward(req,resp,path);
    }

    private void prepareUserList(HttpServletRequest req) {
        try {

            int page = FIRST;
            String pageStr = req.getParameter(ParameterEnum.PAGE.toString());
            if(pageStr != null){
                page = Integer.parseInt(pageStr);
            }
            handleUsersPaging(req, page);

        } catch (ServiceException e) {
            ErrorLogger.logError(logger,req,e);
        }
    }

    private void handleUsersPaging(HttpServletRequest req, int page) throws ServiceException {
        UserServiceImpl userService = serviceFactory.getUserService();
        int startIndex = (page - 1) * RECORDS_PER_PAGE;
        List<User> userList = userService.getAllUsersPaging(startIndex,RECORDS_PER_PAGE);

        int countRecords=userService.getCountUsers();

        int countPages = (int) Math.ceil(countRecords * 1.0 / RECORDS_PER_PAGE);

        req.setAttribute(ParameterEnum.USER_LIST.toString(), userList);

        req.setAttribute(ParameterEnum.COUNT_PAGES.toString(),countPages);
        req.setAttribute(ParameterEnum.CORRENT_PAGE.toString(),page);
        String command=req.getParameter(ParameterEnum.COMMAND.toString());
        req.setAttribute(ParameterEnum.COMMAND.toString(),command);
    }
}
