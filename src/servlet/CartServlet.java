package servlet;

import domain.Cart;
import domain.CartItems;
import domain.Product;
import service_impl.ProductServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
@WebServlet(urlPatterns={"/cart"})
public class CartServlet extends BaseServlet {
    public Cart getCart(HttpServletRequest request){
        Cart cart =(Cart)request.getSession ().getAttribute ("cart");
        if(cart==null){
            cart=new Cart();
            request.getSession().setAttribute ("cart",cart);
        }
        return cart;
    }

    public String add(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int count=Integer.valueOf (request.getParameter("quantity"));
             String  pid=request.getParameter ("pid");

             Product pro=new ProductServiceImpl ().getById (pid);

             CartItems ci=new CartItems (pro,count);

              Cart cart=getCart (request);

              cart.add2Cart (ci   );

              request.getSession ().setAttribute ("cart",cart);


            response.sendRedirect(request.getContextPath()+"/jsp/cart.jsp");
return null;
    }
    public String del(HttpServletRequest request,HttpServletResponse response){

        Cart cart=getCart (request);
        String id=request.getParameter ("pid");
        if(cart!=null)
        cart.delete (id);
       return "/jsp/cart.jsp";
    }
    public String clear(HttpServletRequest request,HttpServletResponse resopose) {

        Cart cart=getCart (request);

        request.getSession ().removeAttribute ("cart");
     return "/jsp/cart.jsp";
    }
}
