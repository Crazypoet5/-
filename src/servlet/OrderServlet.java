package servlet;

import domain.*;
import service.OrderService;
import service_impl.OrderServiceImpl;
import utils.UUIDUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(urlPatterns = {"/order"})
public class OrderServlet extends BaseServlet {

    public String add(HttpServletRequest request, HttpServletResponse response) throws SQLException, ParseException {
        User user = (User) request.getSession ().getAttribute ("user");
        if (user == null) {
            request.setAttribute ("msg", "请先登陆");
            return "/jsp/msg.jsp";
        }
        Cart cart = (Cart) request.getSession ().getAttribute ("cart");
        Order order = new Order ();

        order.setOid (UUIDUtils.getId ());

        SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-mm-dd");

        String str = sdf.format (new Date ());

        Date da = sdf.parse (str);

        System.out.println (da);

        order.setOrderTime (da);

        order.setTotal (cart.getPrice ());
        order.setUser (user);
        order.setUid (user.getUid ());

        for (CartItems ci : cart.getItems ()) {
            OrderItem oi = new OrderItem ();
            oi.setCount (ci.getBuyCount ());
            oi.setItemid (UUIDUtils.getId ());
            oi.setOrder (order);
            oi.setProduct (ci.getPro ());
            oi.setSubTotal (ci.getSubtotal ());
            oi.setOid (order.getOid ());
            oi.setPid (ci.getPro ().getPid ());
            order.getItemList ().add (oi);
        }
        OrderService os = new OrderServiceImpl ();
        os.add (order);
        request.setAttribute ("order",order);

        return "/jsp/order_info.jsp";
    }


}
