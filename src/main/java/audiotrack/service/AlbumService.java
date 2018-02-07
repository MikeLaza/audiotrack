package audiotrack.service;

import audiotrack.service.exception.ServiceException;

public interface AlbumService {
    boolean addAlbum(String singerIdStr, String nameAlbum) throws ServiceException;
    boolean isExistAlbumById(int id)throws ServiceException;
}
