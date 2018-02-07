package audiotrack.service;

import audiotrack.bean.User;
import audiotrack.bean.UserEnum;
import audiotrack.service.exception.ServiceException;

import java.util.List;

public interface UserService {
    boolean signUp(String login, String password, String email, String locale, UserEnum userEnum,Double balance, Integer discount) throws ServiceException;

    User signIn(String login, String password) throws ServiceException;

    User getUserByLogin(String login) throws ServiceException;
    User getUserByPk(Integer id) throws ServiceException;

    void updateLocale(String userLogin, String locale) throws ServiceException;

    boolean updateUser(Integer userId,Integer discount) throws ServiceException;

    List<User> getAllUsers() throws ServiceException;
    List<User> getAllUsersPaging(int startIndex, int totalCount) throws ServiceException;
    int getCountUsers() throws ServiceException;
}
