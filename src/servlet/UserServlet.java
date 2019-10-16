package servlet;

import com.mchange.v2.beans.BeansUtils;
import domain.User;
import myConverter.MyConverter;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import service.UserService;
import service_impl.UserServiceImpl;
import utils.MD5Utils;
import utils.UUIDUtils;

import javax.servlet.DispatcherType;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
    public String login(HttpServletRequest request, HttpServletResponse response) throws SQLException {
          String username=request.getParameter ("username");

          String password=request.getParameter("password");

          password = MD5Utils.md5 (password);
        System.out.println (password);

          UserService userService=new UserServiceImpl ();

          User login = userService.login (username, password);

        if(login==null){

           request.setAttribute ("msg","用户账户名或者密码错误");
           return "/jsp/login.jsp";
        }
       else {
            if (login.getState ()==0) {
                request.setAttribute ("msg", "请前往邮箱激活");
                return "/jsp/login.jsp";
            }
            else
            request.setAttribute ("msg", "登陆成功");
       }
     request.getSession ().setAttribute ("username",username);
            return "/jsp/index.jsp";
    }
    public String logOut(HttpServletRequest request, HttpServletResponse response){

        HttpSession session=request.getSession ();

        session.removeAttribute ("username");

        return "/jsp/index.jsp";
    }
}
