package service;

import domain.PageBean;

import java.sql.SQLException;
import java.util.List;

public interface CategoryService  {
    List findAll() throws SQLException;

    PageBean findByPage(String cid, int currentPage) throws SQLException;
}
