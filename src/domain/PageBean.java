package domain;

import java.util.List;

public class PageBean<T> {
    private List<T> list;//当前页内容	 	   查询
    private int currentPage;//当前页码 	 	传递

    private int pageSize=12;//每页显示的条数，默认为3   固定
    private long totalCount;//总条数			    查询
    private int totalPage;//总页数			计算
//无参的构造函数是一定要有的
    public PageBean(){

    }
    //提供两个构造函数
    public PageBean(List <T>list,int currtentPage ,long totalCount){
        this.totalCount=totalCount;
        this.list=list;
        this.currentPage=currtentPage;
        setTotalPage ();
    }
    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getcurrentPage() {
        return currentPage;
    }

    public void setcurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    private void setTotalPage() {

        this.totalPage=((int)(totalCount*1.0)/pageSize)+1;
    }
    public int  getTotalPage(){
        return totalPage;
    }

}
