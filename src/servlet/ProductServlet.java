package servlet;

import domain.PageBean;
import domain.Product;
import service.CategoryService;
import service.ProductService;
import service_impl.CategoryServiceImpl;
import service_impl.ProductServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

@WebServlet(urlPatterns = {"/product"})
public class ProductServlet extends BaseServlet {

    public String getById(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        ProductService ps = new ProductServiceImpl ();
        String id = request.getParameter ("id");
        Product p = ps.getById (id);

        if (p != null) {
            request.setAttribute ("p", p);
        }

        return "/jsp/product_info.jsp";

    }

    public String findByPage(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        CategoryService ps = new CategoryServiceImpl ();

        String cid = request.getParameter ("cid");

        int currentPage = Integer.parseInt (request.getParameter ("currentPage").trim ());

        PageBean pb = ps.findByPage (cid, currentPage);

        request.setAttribute ("pb", pb);

        return "/jsp/product_list.jsp";
    }

}
