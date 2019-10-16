package dao_impl;

import dao.CategoryDao;
import domain.Category;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
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
}
