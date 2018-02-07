package audiotrack.controller.command.impl;

import audiotrack.controller.PageEnum;
import audiotrack.controller.command.ActionCommand;
import audiotrack.controller.command.impl.util.CommentHelper;
import audiotrack.controller.command.impl.util.ForwardRedirectHelper;
import audiotrack.controller.command.impl.util.ParameterEnum;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BeforeAddCommentCommand implements ActionCommand{
    private static Logger logger = Logger.getLogger(BeforeAddCommentCommand.class);
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp)  {
        String trackIdStr=req.getParameter(ParameterEnum.TRACK_ID.toString());
         req.setAttribute(ParameterEnum.TRACK_ID_FOR_REQUEST.toString(), trackIdStr);

        int trackId=Integer.parseInt(trackIdStr);
        CommentHelper.prepareCommentsList(req, trackId,logger);

        String path = PageEnum.BEFORE_ADD_COMMENT.toString();
        ForwardRedirectHelper.provideForward(req,resp,path);
    }



}
