package domain;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cart {
  private Map<String ,CartItems > cartItemsMap=new LinkedHashMap<> ();
  private  Double price=0.0;

  public Collection<CartItems> getItems(){
          return cartItemsMap.values ();
  }
  public void add2Cart(CartItems items){

      String pid=items.getPro ().getPid ();
      if(cartItemsMap.containsKey(pid)){
          CartItems oitems=cartItemsMap.get (pid);
           oitems.setBuyCount (oitems.getBuyCount ()+items.getBuyCount ());
      }
      else {
          cartItemsMap.put (pid,items);
      }
     price+=items.getSubtotal ();
  }
  public void delete(String id){
      price-=cartItemsMap.get (id).getSubtotal ();
      cartItemsMap.remove (id);
  }
  public void clear(){
      price=0.0;
  }

  public Map<String, CartItems> getCartItemsMap() {
        return cartItemsMap;
    }

    public void setCartItemsMap(Map<String, CartItems> cartItemsMap) {
        this.cartItemsMap = cartItemsMap;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
