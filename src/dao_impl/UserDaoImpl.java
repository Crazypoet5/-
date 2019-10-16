package dao_impl;

import dao.UserDao;
import domain.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import utils.DataSourceUtils;

import java.sql.SQLException;

public class UserDaoImpl implements UserDao {
    @Override
    public void addDao(User user) throws SQLException {

        QueryRunner qr = new QueryRunner (DataSourceUtils.getDataSource ());
        System.out.println ("nihao1");

        String sql = "insert into user values(?,?,?,?,?,?,?,?,?,?)";

        qr.update (sql, user.getUid (), user.getUsername (), user.getPassword (),
                user.getName (), user.getEmail (), user.getTelephone (),
                user.getBirthday (), user.getSex (), user.getState (),
                user.getCode ());

    }

    @Override
    public User getByCode(String code) throws SQLException {
        QueryRunner qr = new QueryRunner (DataSourceUtils.getDataSource ());
        String sql = "select * from user where code=? limit 1";
        return qr.query (sql, new BeanHandler<> (User.class), code);
    }

    @Override
    public void update(User user) throws SQLException {
        QueryRunner qr = new QueryRunner (DataSourceUtils.getDataSource ());

        String sql = "update user set " +
                "username=?,password=?,name=?," +
                "email=?,birthday=?,sex=?,state=?,code=? where uid=?";
        qr.update (sql, user.getUsername (), user.getPassword (), user.getName (),
                user.getEmail (), user.getBirthday (),
                user.getSex (), user.getState (), user.getCode (), user.getUid ());
    }

    @Override
    public User login(String username, String password) throws SQLException {
        QueryRunner qr = new QueryRunner (DataSourceUtils.getDataSource ());

        String sql="select * from user where username=? and password=?";

        return qr.query (sql,new BeanHandler<>(User.class),username,password);


    }

}
