package Classes.User;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;

import Classes.Data.WriteInfo;
import Classes.Products.*;

import javax.swing.*;

public class Seller extends User{
    private final ArrayList <Goods> products=new ArrayList<>();
    final ArrayList <SellHistory> sellHistories=new ArrayList<>();

    int counter=0;
    String list;

    private String companyName;

    Admin admin=Admin.getAdmin();

    public Seller(){}

    public Seller(String firstName,String lastName,String email,String phoneNumber,String userName,char[] passWord,String companyName) throws SQLException, ClassNotFoundException {
        super(firstName, lastName, email, phoneNumber, userName, passWord);
        this.companyName=companyName;
    }

    public String infoForSave(){
        return "-"+super.infoForSave();
    }

    public void setCompanyName(String name){
        this.companyName=name;
    }

    public String getCompanyName(){
        return this.companyName;
    }

    public String personalInformation(){
        return "Name : "+this.getFirstName()+" "+this.getLastName()+"\n"+"Email : "+this.getEmail()+"\n"+"Phone Number : "+
                this.getPhoneNumber()+"\n"+"Username : "+this.getUserName()+"\n"+"Password : "+this.getPassWord()+"\n"+"Company : "+this.companyName;
    }

    public void setProduct(Goods g){
        this.products.add(g);
    }

    public void removeProduct(Goods g){
        this.products.remove(g);
    }

    public void setList(String list){
        this.list=list;
    }

    public String getList(){
        return this.list;
    }

    public void setCounter(int counter){
        this.counter=counter;
    }

    public int getProductsSize(){
        return products.size();
    }

    public void showProductList(DefaultListModel<String> hold, int counter){
        int size;

        if(products.size()-counter>=10){
            size=10;
        }
        else{
            size=products.size()-counter;
        }

        for(int i=counter;i<size+counter;i++){
            hold.addElement((i+1)+"-"+products.get(i).toString());
            hold.addElement("\n");
        }
    }

    public Goods getProduct(int index){
        return products.get(index);
    }

    public void setSellHistory(SellHistory s){
        sellHistories.add(s);
    }

    public SellHistory checkCode(int code){
        for(int i=0;i<sellHistories.size();i++){
            if(sellHistories.get(i).getCode()==code){
                return sellHistories.get(i);
            }
        }
        return null;
    }

    public String showSellHistory(){
        if(sellHistories.size()!=0 && counter<sellHistories.size()){
            return sellHistories.get(counter).showInfo();
        }
        else{
            counter=0;
            return null;
        }
    }

    public void deleteFacture(){
        sellHistories.remove(counter);
        if(sellHistories.size()<=counter && counter!=0){
            counter--;
        }
    }

    public void deleteProduct(Goods good){
        products.remove(good);
    }

    public String showProductInfo(int index){
        return sellHistories.get(counter).products.get(index).showInfo();
    }

    public void changeProduct(Goods real,Goods change){
        for(int i=0;i<products.size();i++){
            if(products.get(i)==real){
                products.remove(i);
                products.add(i,change);
                return;
            }
        }
    }

    public void increaseCounter(){
        counter++;
    }

    public Integer [] getValues(){
        Integer[] val=new Integer[sellHistories.get(counter).products.size()];

        for(int i=0;i<sellHistories.get(counter).products.size();i++){
            val[i]=i+1;
        }

        return val;
    }

    public String showProductSellHistory(){
        StringBuilder temp=new StringBuilder();

        for(int i=0;i<sellHistories.get(counter).products.size();i++){
            temp.append((i+1)+"-"+sellHistories.get(counter).products.get(i).toString());
        }

        return temp.toString();
    }
}
