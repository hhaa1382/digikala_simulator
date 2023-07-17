package Classes.Categories;

import java.sql.SQLException;

abstract public class Category {
    String filterBrand=null;
    int filterProductCode=-1;
    double filterPriceStart=0;
    double filterPriceEnd=0;
    boolean exist=false;
    boolean filterDiscount=false;

    String list;

    public void setFilterExist(boolean exist){
        this.exist=exist;
    }

    public void setFilterBrand(String brand){
        this.filterBrand=brand;
    }

    public void setFilterPrice(double priceStart,double priceEnd){
        this.filterPriceStart=priceStart;
        this.filterPriceEnd=priceEnd;
    }

    public void setFilterCode(int code){
        this.filterProductCode=code;
    }

    public void setFilterDiscount(boolean discount){
        this.filterDiscount=discount;
    }

    public void setList(String list){

        this.list=list;
    }

    abstract public String getList();
    abstract public void sort();
    abstract public void readProducts()throws SQLException, ClassNotFoundException ;
}
