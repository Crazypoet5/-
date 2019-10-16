package dao;

import java.sql.SQLException;
import java.util.List;

public interface CategoryDao{
  List findAll() throws SQLException;
}

