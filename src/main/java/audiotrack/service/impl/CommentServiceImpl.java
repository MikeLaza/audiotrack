package audiotrack.service.impl;

import audiotrack.bean.Comment;
import audiotrack.controller.command.impl.util.ParameterValidator;
import audiotrack.dao.exception.DAOException;
import audiotrack.dao.factory.DaoFactory;
import audiotrack.dao.impl.CommentDAO;
import audiotrack.service.CommentService;
import audiotrack.service.exception.ServiceException;

import java.util.List;

public class CommentServiceImpl implements CommentService {
    private final static CommentServiceImpl instance=new CommentServiceImpl();
    private DaoFactory daoFactory = DaoFactory.getInstance();

    private CommentServiceImpl() {
    }




    public boolean addComment(int userId,int trackId,String text) throws ServiceException {
        boolean isValid;
        try {
            isValid=checkParameters(text);
            if(isValid){
                CommentDAO commentDAO= daoFactory.getCommentDAO();
                Comment comment= new Comment(userId,trackId,text);
                commentDAO.addEntity(comment);
            }
        } catch (DAOException e) {
            throw  new ServiceException(e);
        }
        return isValid;


    }


    private void add(int userId, int trackId, String text) throws DAOException {
        CommentDAO commentDAO= daoFactory.getCommentDAO();
        Comment comment= new Comment(userId,trackId,text);
        commentDAO.addEntity(comment);
    }

    private boolean checkParameters(String text)  {
        boolean notNulls = ParameterValidator.isNotNulls(text);
        boolean isValid=false;
        if(notNulls ){
            boolean notEmptyParameters = ParameterValidator.isNotEmptyParameters(text);
            if(notEmptyParameters){
                isValid=true;
            }
        }
        return isValid;
    }

    @Override
    public List<Comment> getAllCommentsByTrackId(int trackId) throws ServiceException {
        final CommentDAO commentDAO = daoFactory.getCommentDAO();
        List<Comment> comments;
        try {
            comments = commentDAO.getAllCommentsTrack(trackId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return comments;
    }

    public static CommentServiceImpl getInstance() {
        return instance;
    }
}
