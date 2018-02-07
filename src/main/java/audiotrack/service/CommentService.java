package audiotrack.service;

import audiotrack.bean.Comment;
import audiotrack.service.exception.ServiceException;

import java.util.List;

public interface CommentService {
     boolean addComment(int  userId,int trackId,String text) throws ServiceException;
    List<Comment> getAllCommentsByTrackId(int trackId) throws ServiceException;
}
