package audiotrack.controller.command.impl;

import audiotrack.bean.User;
import audiotrack.controller.command.ActionCommand;
import audiotrack.controller.command.impl.util.ErrorLogger;
import audiotrack.controller.command.impl.util.ForwardRedirectHelper;
import audiotrack.controller.command.impl.util.ParameterEnum;
import audiotrack.controller.command.impl.util.ParameterValidator;
import audiotrack.service.CommentService;
import audiotrack.service.exception.ServiceException;
import audiotrack.service.factory.ServiceFactory;
import audiotrack.service.impl.UserServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AddCommentCommand implements ActionCommand {
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private static Logger logger = Logger.getLogger(AddCommentCommand.class);
    public static final String COMMAND= "audiotrack?command=show_tracks";
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp)  {

        String trackIdStr = req.getParameter(ParameterEnum.TRACK_ID.toString());
        int trackId=Integer.parseInt(trackIdStr);

        HttpSession session = req.getSession();
        String login=(String) session.getAttribute(ParameterEnum.LOGIN.toString());

        addComment(req, trackId, login);
        ForwardRedirectHelper.provideRedirect(resp,COMMAND);


    }



    private void addComment(HttpServletRequest req, int trackId, String login) {
        try {
            UserServiceImpl userService = serviceFactory.getUserService();
            User user;
            user=userService.getUserByLogin(login);
            int userId=user.getId();
            String text=req.getParameter(ParameterEnum.TEXT_COMMENT.toString());
            CommentService commentService=serviceFactory.getCommentService();

            boolean isValid;
            isValid=checkParameters(text);
            if(isValid){
                commentService.addComment(userId,trackId,text);
            }

        } catch (ServiceException e) {
            ErrorLogger.logError(logger,req,e);
        }
    }

    private boolean checkParameters(String text)  {
        boolean notNulls = ParameterValidator.isNotNulls(text);
        boolean isValid=false;
        if(notNulls){
            boolean notEmptyParameters = ParameterValidator.isNotEmptyParameters(text);
            if(notEmptyParameters){
                isValid=true;
            }
        }
        return isValid;
    }


}
