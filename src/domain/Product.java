package domain;

import java.io.Serializable;
import java.util.Date;

public class Product implements Serializable {
    private String pid;

    private String pname;

    private double market_price;

    private  double shop_price;

    private  String pimage;

    private Date   String;

    private Integer is_hot;

    private String  pdesc;

    private int  pflag;

    private String  cid;

    public Product(){

    }

    public java.lang.String getPid() {
        return pid;
    }

    public void setPid(java.lang.String pid) {
        this.pid = pid;
    }

    public java.lang.String getPname() {
        return pname;
    }

    public void setPname(java.lang.String pname) {
        this.pname = pname;
    }

    public double getMarket_price() {
        return market_price;
    }

    public void setMarket_price(double market_price) {
        this.market_price = market_price;
    }

    public double getShop_price() {
        return shop_price;
    }

    public void setShop_price(double shop_price) {
        this.shop_price = shop_price;
    }

    public java.lang.String getPimage() {
        return pimage;
    }

    public void setPimage(java.lang.String pimage) {
        this.pimage = pimage;
    }

    public Date getString() {
        return String;
    }

    public void setString(Date string) {
        String = string;
    }

    public Integer getIs_hot() {
        return is_hot;
    }

    public void setIs_hot(Integer is_hot) {
        this.is_hot = is_hot;
    }

    public java.lang.String getPdesc() {
        return pdesc;
    }

    public void setPdesc(java.lang.String pdesc) {
        this.pdesc = pdesc;
    }

    public int getPflag() {
        return pflag;
    }

    public void setPflag(int pflag) {
        this.pflag = pflag;
    }

    public java.lang.String getCid() {
        return cid;
    }

    public void setCid(java.lang.String cid) {
        this.cid = cid;
    }
}
