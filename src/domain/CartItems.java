package domain;

public class CartItems {
    private Product pro;
    private Integer buyCount=0;
    private Double subtotal=0.0;
public CartItems(){

}
    public CartItems(Product pro ,Integer count){
       this.pro=pro;
       buyCount=count;
}

    public Product getPro() {
        return pro;
    }

    public void setPro(Product pro) {
        this.pro = pro;
    }

    public Integer getBuyCount() {
        return buyCount;
    }

    public void setBuyCount(Integer buyCount) {
        this.buyCount = buyCount;
    }

    public Double getSubtotal() {
        return pro.getShop_price ()*buyCount;
    }







}
