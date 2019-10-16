package servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.sql.SQLException;

/*
 通用类的设计

 也是对servlet类的抽取，避免servlet过多代码显得臃肿，

 这里主要是利用了反射的思想

 2019/10/11 20:16
 */
public class BaseServlet extends HttpServlet {
   @Override
   public void service(HttpServletRequest request, HttpServletResponse response){
       try {
           //获得调用该方法的类的字节码对象
           Class c=this.getClass ();
      //    System.out.println (c);
        //获取到方法名称
           String m=request.getParameter( "method");
      //    System.out.println (m);
           if(m==null||m.isEmpty ()){
               m="index";
           }
        //获取方法对象
           Method method=c.getMethod (m,HttpServletRequest.class,HttpServletResponse.class);

             String s= (String)method.invoke (this,request,response);
             if(s!=null){
             request.getRequestDispatcher(s).forward(request,response);
         }
       } catch (Exception e) {
           e.printStackTrace ();
       }


   }
 public String index (HttpServletRequest request,HttpServletResponse response) throws SQLException {
       return request.getContextPath()+"/index";
 }
}
