package servlet;

import domain.Category;
import service.CategoryService;
import service.ProductService;
import service_impl.CategoryServiceImpl;
import service_impl.ProductServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = {"/index"})
public class IndexServlet extends BaseServlet {

    public String  index(HttpServletRequest request, HttpServletResponse response) throws SQLException {
            ProductService ps=new ProductServiceImpl ();

        List hostProduct = ps.getHostProduct ();

        List newProduct = ps.getNewProduct ();

        request.setAttribute ("hp",hostProduct);

        request.setAttribute ("np",newProduct);

        return "/jsp/index.jsp";
    }
}
