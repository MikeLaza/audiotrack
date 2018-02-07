package audiotrack.service.impl;

import audiotrack.bean.Album;
import audiotrack.controller.command.impl.UpdateUserDiscountCommand;
import audiotrack.controller.command.impl.util.ParameterValidator;
import audiotrack.dao.exception.DAOException;
import audiotrack.dao.factory.DaoFactory;
import audiotrack.dao.impl.AlbumDAO;
import audiotrack.dao.impl.SingerDAO;
import audiotrack.service.AlbumService;
import audiotrack.service.exception.ServiceException;

import org.apache.log4j.Logger;

public class AlbumServiceImpl implements AlbumService {
    private static final AlbumServiceImpl instance = new AlbumServiceImpl();
    private DaoFactory daoFactory = DaoFactory.getInstance();
    private static Logger logger = Logger.getLogger(AlbumServiceImpl.class);

    private AlbumServiceImpl() {
    }

    @Override
    public boolean addAlbum(String singerIdStr, String nameAlbum) throws ServiceException {
        boolean isValid;
        try {
            isValid= checkParameters(singerIdStr, nameAlbum);
            isValid = isValid(singerIdStr, nameAlbum, isValid);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return isValid;
    }

    @Override
    public boolean isExistAlbumById(int id) throws ServiceException {
        boolean isExist;
        try {
            AlbumDAO albumDAO = daoFactory.getAlbumDAO();
            isExist=albumDAO.isExistEntity(id);
        } catch (DAOException e) {
            throw  new ServiceException(e);
        }
        return isExist;
    }

    private boolean isValid(String singerIdStr, String nameAlbum, boolean isValid) throws DAOException {
        if(isValid){
            isValid = isExistSinger(singerIdStr, nameAlbum);
        }
        return isValid;
    }

    private boolean isExistSinger(String singerIdStr, String nameAlbum) throws DAOException {
        SingerDAO singerDAO=daoFactory.getSingerDAO();
        int singerId=Integer.parseInt(singerIdStr);
        boolean isExistSinger= singerDAO.isExistEntity(singerId);

        if(isExistSinger){
            add(singerIdStr, nameAlbum);
        }
        return isExistSinger;
    }

    private void add(String singerIdStr, String nameAlbum) throws DAOException {

        int singerId=Integer.parseInt(singerIdStr);
        AlbumDAO albumDAO=daoFactory.getAlbumDAO();
        Album album= new Album(singerId,nameAlbum);
        albumDAO.addEntity(album);
    }

    private boolean checkParameters(String singerIdStr, String nameAlbum) {
        boolean isValid;
        boolean notNulls = ParameterValidator.isNotNulls(singerIdStr, nameAlbum);
        if(!notNulls){
            isValid=false;
        }else {
            boolean notEmptyParameters = ParameterValidator.isNotEmptyParameters(singerIdStr, nameAlbum);
            isValid = ParameterValidator.isValid(singerIdStr, notEmptyParameters);
        }
        return isValid;
    }


    public static AlbumServiceImpl getInstance() {
        return instance;
    }
}
