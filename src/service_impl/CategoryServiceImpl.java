package service_impl;

import dao.CategoryDao;
import dao_impl.CategoryDaoImpl;
import service.CategoryService;

import java.sql.SQLException;
import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    @Override
    public List findAll() throws SQLException {
        CategoryDao categoryDao = new CategoryDaoImpl ();

        List all = categoryDao.findAll ();

        return all;
    }
}
