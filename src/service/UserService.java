package service;

        import domain.User;

        import java.sql.SQLException;

public interface UserService {

      void add(User user) throws SQLException;
      User  active(String code) throws SQLException;
      User login(String username,String password) throws SQLException;
}
