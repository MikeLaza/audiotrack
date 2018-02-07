package audiotrack.service.impl;


import audiotrack.bean.User;
import audiotrack.bean.UserEnum;
import audiotrack.dao.exception.DAOException;
import audiotrack.dao.factory.DaoFactory;
import audiotrack.dao.impl.UserDAO;
import audiotrack.service.UserService;
import audiotrack.service.exception.ServiceException;

import java.util.List;

public class UserServiceImpl implements UserService {

    private static final UserServiceImpl instance = new UserServiceImpl();
    private DaoFactory daoFactory = DaoFactory.getInstance();


    private UserServiceImpl() {

    }

    public static UserServiceImpl getInstance() {
        return instance;
    }

    @Override
    public boolean signUp(String login, String password, String email, String locale, UserEnum userEnum,Double balance, Integer discount) throws ServiceException {
        boolean isValid=true;
        try {
                add(login, password, email, locale, userEnum, balance, discount);

        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return isValid;


    }

    private void add(String login, String password, String email, String locale, UserEnum userEnum, Double balance, Integer discount) throws DAOException {
        User user = new User(login,password, email, locale, userEnum, balance,discount);
        UserDAO userDAO = daoFactory.getUserDAO();
        userDAO.addEntity(user);
    }


    @Override
    public User signIn(String login, String password) throws ServiceException {
        User user;
        User authorizedUser;
        try {
                UserDAO userDAO = daoFactory.getUserDAO();
                user = new User(login,password);
                authorizedUser = userDAO.signIn(user);
        } catch (DAOException  e) {
            throw new ServiceException(e);
        }
        return authorizedUser;
    }



    @Override
    public User getUserByLogin(String login) throws ServiceException {
        final UserDAO userDAO = daoFactory.getUserDAO();
        User user;
        try {
            user = userDAO.getUserByLogin(login);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return user;
    }

    @Override
    public User getUserByPk(Integer id) throws ServiceException {

        final UserDAO userDAO = daoFactory.getUserDAO();
        User user;
        try {
            user = userDAO.getById(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return user;
    }

    @Override
    public void updateLocale(String userLogin, String locale) throws ServiceException {
        try {
            UserDAO userDAO = daoFactory.getUserDAO();
            userDAO.updateLocale(userLogin, locale);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

    }


    @Override
    public boolean updateUser(Integer userId, Integer discount) throws ServiceException {
        UserDAO userDAO=daoFactory.getUserDAO();
        boolean isUpdated=true;
        try {

            boolean isValidDiscount=isValidDiscount(discount);
            if(isValidDiscount){
                User user=getUserByPk(userId);
                user.setDiscount(discount);
                userDAO.update(user);
            }else {
                isUpdated=false;
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return isUpdated;
    }

    private boolean isValidDiscount(int discount){
        boolean isValid=true;
        if(discount<0){
            isValid=false;
        }
        return isValid;
    }


    @Override
    public List<User> getAllUsers() throws ServiceException {

        List<User> users;
        try {
            UserDAO userDAO = daoFactory.getUserDAO();
            users = userDAO.getAllEntities();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return users;

    }

    @Override
    public List<User> getAllUsersPaging(int startIndex, int totalCount) throws ServiceException {
        List<User> users;
        try {
            UserDAO userDAO = daoFactory.getUserDAO();
            users = userDAO.getAllEntitiesPaging(startIndex,totalCount);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return users;
    }

    @Override
    public int getCountUsers() throws ServiceException {
        int count;
        try {
            UserDAO userDAO = daoFactory.getUserDAO();
            count = userDAO.getCountOfRecords();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return count;
    }
}
