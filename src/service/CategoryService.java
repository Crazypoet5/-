package service;

import java.sql.SQLException;
import java.util.List;

public interface CategoryService  {
    List findAll() throws SQLException;
}
