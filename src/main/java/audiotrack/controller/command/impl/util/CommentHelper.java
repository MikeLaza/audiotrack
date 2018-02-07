package audiotrack.controller.command.impl.util;

import audiotrack.bean.Comment;
import audiotrack.service.exception.ServiceException;
import audiotrack.service.factory.ServiceFactory;
import audiotrack.service.impl.CommentServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class CommentHelper {
    private static ServiceFactory serviceFactory = ServiceFactory.getInstance();

    public static void prepareCommentsList(HttpServletRequest req, int trackId,Logger logger) {
        try {
            CommentServiceImpl commentService = serviceFactory.getCommentService();
            List<Comment> comments = commentService.getAllCommentsByTrackId(trackId);
            req.setAttribute(ParameterEnum.COMMENTS_LIST.toString(), comments);
        } catch (ServiceException e) {
            ErrorLogger.logError(logger,req,e);
        }
    }
}
