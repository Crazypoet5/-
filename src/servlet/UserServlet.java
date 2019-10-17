package servlet;

import com.mchange.v2.beans.BeansUtils;
import domain.User;
import myConverter.MyConverter;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import service.UserService;
import service_impl.UserServiceImpl;
import utils.CookUtils;
import utils.MD5Utils;
import utils.UUIDUtils;

import javax.mail.Header;
import javax.servlet.DispatcherType;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Date;

@WebServlet(urlPatterns = {"/user"})
public class UserServlet extends BaseServlet {


    public void add(HttpServletRequest request, HttpServletResponse response) {
        System.out.println ("add方法被执行了");
    }

    public String register(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        request.setCharacterEncoding ("utf-8");

        response.setContentType ("text/html;charset=utf-8");

        User user = new User ();

        //设置激活码
        user.setCode (UUIDUtils.getCode ());
        //设置用户id
        user.setUid (UUIDUtils.getId ());

        //因为该转换器无法将字符串转换成Date,因此需要自定义一个转换器，
        //第一个参数是我们的转换器，第二个是转换器转换后类型
        ConvertUtils.register (new MyConverter (), Date.class);
        try {
            BeanUtils.populate (user, request.getParameterMap ());
        } catch (IllegalAccessException e) {
            System.out.println ("包装出错");
            e.printStackTrace ();
        } catch (InvocationTargetException e) {
            System.out.println ("包装出错");
            e.printStackTrace ();
        }
        user.setPassword (MD5Utils.md5 (user.getPassword ()));
        UserService us = new UserServiceImpl ();
        try {
            us.add (user);
        } catch (SQLException e) {
            System.out.println ("添加到数据库中出错");
            e.printStackTrace ();
        }

        String msg = "用户已注册成功，请去邮箱激活";
        request.setAttribute ("msg", msg);
        return "/jsp/msg.jsp";
    }

    public String active(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        String code = request.getParameter ("code");
        UserService userService = new UserServiceImpl ();

        User user = userService.active (code);
        if (user == null) {
            request.setAttribute ("msg", "激活失败，请重新注册");

        } else
            request.setAttribute ("msg", "激活成功<a href=http://localhost:8080/store/jsp/login.jsp>点这里登陆</a>");
        return "/jsp/msg.jsp";
    }

    public String login(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {

          //获取浏览器中存储的cookies
        Cookie cookies[] = request.getCookies ();
      //通过CookUtils获取相应的cookie
        Cookie cookie = CookUtils.getCookieByName ("username", cookies);
        Cookie cookiep = CookUtils.getCookieByName ("password", cookies);
      //判断相应的cookie是否为空
        if (cookie != null && cookiep != null) {
            //如果不为空，说明之前已经登陆过且选择了自动登陆
            //开始调用服务方法
            UserService userService = new UserServiceImpl ();

            User login = userService.login (cookie.getValue (), cookiep.getValue ());
            if (login == null) {
                request.setAttribute ("msg", "用户账户名或者密码错误");
                return "/jsp/login.jsp";
            }
            request.getSession ().setAttribute ("username", cookie.getValue ());

        } else {
            //如果不存在
            String username = request.getParameter ("username");

            String password = request.getParameter ("password");

            String password1 = password;
            password = MD5Utils.md5 (password);


            UserService userService = new UserServiceImpl ();

            User login = userService.login (username, password);


            if (login == null) {

                request.setAttribute ("msg", "用户账户名或者密码错误");
                return "/jsp/login.jsp";
            } else {
                if (login.getState () == 0) {
                    request.setAttribute ("msg", "请前往邮箱激活");
                    return "/jsp/login.jsp";
                } else {
                    {

                        request.getSession ().setAttribute ("last_login_name", username);
                        request.getSession ().setAttribute ("last_login_pw", password1);
                        String flag = request.getParameter ("autoLogin");
                         //如果用户勾选了自动登陆
                        //我们可以利用cookie去实现它
                        if ("yes".equals (flag)) {
                            Cookie cname = new Cookie ("username", username);

                            Cookie cpw = new Cookie ("password", password);

                            cname.setPath (request.getRequestURI () + "/");

                            cpw.setPath (request.getRequestURI () + "/");
                            cname.setMaxAge (60 * 60);
                            cpw.setMaxAge (60 * 60);
                            response.addCookie (cname);

                            response.addCookie (cpw);

                        }

                    }

                }
            }
            request.getSession ().setAttribute ("username", username);
        }
        //请求重定向到index页面，这里不要用请求转发，有个bug,因为要用到的信息都被我保存在了session中，
        //请求重定向也可以
           response.sendRedirect (request.getContextPath ()+"/index");
        return null;
    }

    public String logOut(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession ();

        session.removeAttribute ("username");

        return "/jsp/index.jsp";
    }
}
