package service_impl;

import dao_impl.UserDaoImpl;
import dao.UserDao;
import domain.User;
import service.UserService;
import utils.MailUtils;

import javax.mail.MessagingException;
import java.sql.SQLException;

public class UserServiceImpl implements UserService {

    @Override
    public void add(User user) throws SQLException {
        UserDao userDao = new UserDaoImpl ();

        userDao.addDao (user);

        String msg = "欢迎注册," +
                "<a " +
                "href='http://localhost:8080/store/user?method=active&code=" + user.getCode () + "'>点此激活" +
                "</a>";
        try {
            MailUtils.sendMail (user.getEmail (), msg);
        } catch (MessagingException e) {
            System.out.println ("邮件发送异常");
            e.printStackTrace ();
        }
    }

    @Override
    public User active(String code) throws SQLException {

        UserDao userDao = new UserDaoImpl ();
        User user = userDao.getByCode (code);
        if (user != null) {
            user.setState (1);

            userDao.update (user);
        }
        return user;
    }

    @Override
    public User login(String username, String password) throws SQLException {
        UserDao userDao = new UserDaoImpl ();


       // System.out.println (password);
        User user = userDao.login (username, password);

        return user;
    }
}
