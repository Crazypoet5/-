package dao_impl;

import dao.CategoryDao;
import domain.Category;
import domain.Product;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import utils.DataSourceUtils;

import java.sql.SQLException;
import java.util.List;

public class CategoryDaoImpl implements CategoryDao {
    @Override
    public List findAll() throws SQLException {
        QueryRunner qr=new QueryRunner (DataSourceUtils.getDataSource ());
         String sql= "select * from category";
         return qr.query(sql,new BeanListHandler <>( Category.class ));
    }

    @Override
    public List<Product> getProductByCid(String cid, int currentPage, int size) throws SQLException {
        QueryRunner qr=new QueryRunner (DataSourceUtils.getDataSource ());
        int start=(currentPage-1)*size;
        int offset=size;
        String sql= "select * from product where cid=? limit ?,?";
        return  qr.query(sql,new BeanListHandler <>(Product.class),cid,start,offset);
    }

    @Override
    public long getTotalCount(String cid) throws SQLException {
        QueryRunner qr=new QueryRunner (DataSourceUtils.getDataSource ());

        String sql= "select count(*) from product where cid=?";

        return (long)qr.query(sql,new ScalarHandler (),cid);


    }
}
