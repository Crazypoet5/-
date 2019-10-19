package service_impl;

import dao.CategoryDao;
import dao_impl.CategoryDaoImpl;
import domain.PageBean;
import domain.Product;
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

    @Override
    public PageBean findByPage(String cid, int currentPage) throws SQLException {
        CategoryDao categoryDao = new CategoryDaoImpl ();
        int size=new PageBean<> ().getPageSize ();

        List<Product> plist=categoryDao.getProductByCid(cid,currentPage,size);
        long totalCount=categoryDao.getTotalCount(cid);

        return new PageBean (plist,currentPage,totalCount);
    }
}
