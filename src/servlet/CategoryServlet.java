package servlet;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import service.CategoryService;
import service_impl.CategoryServiceImpl;
import utils.JsonUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "CategoryServlet",urlPatterns={"/category"})
public class CategoryServlet extends BaseServlet {

    public void findAll(HttpServletRequest request,HttpServletResponse response) throws SQLException, IOException {
        response.setContentType("text/html;charset=utf-8");
        CategoryService cs=new CategoryServiceImpl ();

        List list=cs.findAll ();

        String json=JsonUtil.list2json (list);

        response.getWriter ().print (json);
    }

}
