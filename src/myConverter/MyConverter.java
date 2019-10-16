package myConverter;

import org.apache.commons.beanutils.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyConverter implements Converter {

    @Override
    public Object convert(Class aClass, Object o) {
        SimpleDateFormat sdf=new SimpleDateFormat ("yyyy-mm-dd");
     if(o==null){
         return null;
     }
        try {
            Date date=sdf.parse ((String)o);
              return date;
        } catch (ParseException e) {
            e.printStackTrace ();
        }
       return null;
    }
}
