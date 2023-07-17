package Classes.User;

import java.io.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import Classes.Data.WriteInfo;
import Classes.Exeptions.InvalidDiscountCode;
import Classes.Exeptions.NotEnoughMoney;
import Classes.Products.Discount;
import Classes.Products.Goods;

import javax.swing.*;

public class Customer extends User{
    private double accountCredit=0;

    final ArrayList <BuyHistory> buyHistories=new ArrayList<>();
    final ArrayList <Goods> products=new ArrayList<>();

    ShopingCart shopingCart=new ShopingCart();

    int counter;

    Admin admin=Admin.getAdmin();

    public Customer(){}

    public Customer(String firstName,String lastName,String email,String phoneNumber,String userName,char[] passWord) throws SQLException, ClassNotFoundException {
        super(firstName, lastName, email, phoneNumber, userName, passWord);
        admin.customers.add(this);
    }

    public String infoForSave(){
        return "-"+super.infoForSave()+"-"+accountCredit;
    }

    public String personalInformation(){
        return "Name : "+ this.getFirstName() +" "+ this.getLastName() +"\n"+"Email : "+ this.getEmail() +"\n"+"Phone Number : "+
                this.getPhoneNumber() +"\n"+"Username : "+ this.getUserName() +"\n"+"Password : "+ this.getPassWord();
    }

    public double getAccountCredit(){
        return accountCredit;
    }

    public void setAccountCredit(double money) throws SQLException, ClassNotFoundException {
        if(money<0) {
            this.accountCredit=0;
        }
        else {
            this.accountCredit = money;
        }

        WriteInfo.updateCustomer(this);
    }

    public String addShoppingCart(Goods temp){
        if(shopingCart.types.size()<10){
            shopingCart.newProduct(temp);
            return "The product added to shopping cart\n\n";
        }
        else {
            return "Shoping cart is full\n\n";
        }
    }

    public void showShoppingCart(DefaultListModel<String> hold,JLabel money){
        for(int i=0;i<shopingCart.types.size();i++){
            hold.addElement((i+1)+"-"+shopingCart.types.get(i).toString());
            hold.addElement("\n");
        }

        money.setText("Price : "+shopingCart.getPrice());
    }

    public Discount checkDiscountCode(String code){
        for(Goods i:shopingCart.types){
            Discount temp=i.codeCheck(code);
            if(temp!=null){
                if(temp.getCapacity()>0){
                    LocalDate now=LocalDate.now();
                    LocalDate check=temp.getDiscountValue();

                    if(check.getYear()>now.getYear()){
                        return temp;
                    }

                    else if(check.getYear()==now.getYear()){
                        if(check.getMonthValue()>now.getMonthValue()){
                            return temp;
                        }

                        else if(check.getMonthValue()==now.getMonthValue()){
                            if(check.getDayOfMonth()>=now.getDayOfMonth()){
                                return temp;
                            }

                            else{
                                break;
                            }
                        }

                        else{
                            break;
                        }
                    }

                    else{
                        break;
                    }
                }
                else{
                    break;
                }
            }
        }
        throw new InvalidDiscountCode();
    }

    public void setShopingCart(Goods good,int num){
        shopingCart.addProduct(good,num);
    }

    public void setShoppingCartDiscount(){
        shopingCart.setDiscount();
    }

    public int checkDiscountNumber(String code){
        for(int i=0;i<shopingCart.types.size();i++){
            if(shopingCart.types.get(i).CheckCodeUse(code)){
                return shopingCart.number.get(i);
            }
        }

        return 0;
    }

    public void setDiscountPrice(Discount discount){
        for(Goods i:shopingCart.types){
            if(i.discountCheck(discount)){
                shopingCart.setDiscountPrice(i.getPrice(), discount.getDiscountPercent());
            }
        }
    }

    public Goods showProduct(int index){
        return shopingCart.types.get(index);
    }

    public int getShoppingSize(){
        return shopingCart.types.size();
    }

    public void deleteProduct(int index) throws SQLException, ClassNotFoundException {
        WriteInfo.saveShoppingCart(this,shopingCart.types.get(index),false);
        if(shopingCart.number.get(index)>1){
            shopingCart.number.set(index,shopingCart.number.get(index)-1);
            shopingCart.updatePrice(shopingCart.types.get(index));
        }

        else{
            shopingCart.updatePrice(shopingCart.types.get(index));
            shopingCart.number.remove(index);
            shopingCart.types.remove(index);
        }
    }

    public void buyProducts(JLabel money) throws SQLException, ClassNotFoundException {
        double prices=shopingCart.getPrice();

        if(prices>accountCredit){
            throw new NotEnoughMoney();
        }

        else if(checkNumber()){
            accountCredit-=prices;
            money.setText("Credit : "+accountCredit);
            BuyHistory b=new BuyHistory();
            b.setCode(WriteInfo.setBuyHistoryCode());
            b.setPrice(prices);

            for(int i=0;i<shopingCart.types.size();i++){
                goodsCheck(shopingCart.types.get(i));
                b.products.add(shopingCart.types.get(i));
            }

            b.setDateTime();
            WriteInfo.saveBuyHistory(b,this);

            setSellHistory(b);
            buyHistories.add(b);
            setNumber();
            deleteAll();
        }
    }

    public void setDiscountCapacities(ArrayList<Discount> copy,ArrayList<Discount> real) throws SQLException, ClassNotFoundException {
        for(int i=0;i<copy.size();i++){
            real.get(i).setCapacity(copy.get(i).getCapacity());
            WriteInfo.updateDiscount(real.get(i).getDiscountCode(),real.get(i).getCapacity());
        }
    }

    public void goodsCheck(Goods g){
        for(int i=0;i<products.size();i++){
            if(products.get(i)==g){
                return;
            }
        }
        products.add(g);
    }

    public void deleteAll() throws SQLException, ClassNotFoundException {
        shopingCart.types.clear();
        shopingCart.number.clear();
        shopingCart.setPrice(0);
        WriteInfo.deleteShoppingCart(this);
    }

    public boolean checkNumber(){
        for(int i=0;i<shopingCart.number.size();i++){
            if(shopingCart.number.get(i)>shopingCart.types.get(i).getNumber()){
                return false;
            }
        }
        return true;
    }

    public void setNumber(){
        for(int i=0;i<shopingCart.number.size();i++){
            int num=shopingCart.types.get(i).getNumber()-shopingCart.number.get(i);
            shopingCart.types.get(i).setNumber(num);
        }
    }

    public void setSellHistory(BuyHistory b) throws SQLException, ClassNotFoundException {
        ArrayList <Seller> temp=new ArrayList<>();
        ArrayList <SellHistory> hold=new ArrayList<>();
        SellHistory sell=new SellHistory();

        for(int i=0;i<shopingCart.types.size();i++){
            if(!temp.contains(shopingCart.types.get(i).getSeller())){
                sell.setCode(b.getCode());
                sell.setDate(b.getDate());
                sell.setTime(b.getTime());
                sell.setPrice(shopingCart.types.get(i).getPrice()*shopingCart.number.get(i));
                sell.products.add(shopingCart.types.get(i));

                Seller s=shopingCart.types.get(i).getSeller();
                temp.add(s);
                hold.add(sell);
                s.setSellHistory(sell);
            }

            else{
                SellHistory s=shopingCart.types.get(i).getSeller().checkCode(b.getCode());
                double tempPrice=s.getPrice()+shopingCart.types.get(i).getPrice()*shopingCart.number.get(i);
                s.setPrice(tempPrice);
                s.products.add(shopingCart.types.get(i));
            }

            WriteInfo.setProductBuySellHistory(b,shopingCart.types.get(i),shopingCart.types.get(i).getSeller(),this);
        }
    }

    public void setCounter(int counter){
        this.counter=counter;
    }

    public String showBuyHistory(){
        if(buyHistories.size()!=0 && counter<buyHistories.size()){
            return buyHistories.get(counter).showInfo();
        }
        else{
            counter=0;
            return null;
        }
    }

    public Integer [] getValues(){
        Integer[] val=new Integer[buyHistories.get(counter).products.size()];

        for(int i=0;i<buyHistories.get(counter).products.size();i++){
            val[i]=i+1;
        }

        return val;
    }

    public void deleteFacture() throws ClassNotFoundException, SQLException {
        WriteInfo.deleteBuyHistory(buyHistories.get(counter).getCode(),this);
        buyHistories.remove(counter);
        if(buyHistories.size()<=counter && counter!=0){
            counter--;
        }
    }

    public String showProductList(){
        StringBuilder temp=new StringBuilder();

        for(int i=0;i<buyHistories.get(counter).products.size();i++){
            temp.append((i+1)+"-"+buyHistories.get(counter).products.get(i).toString());
        }
        return temp.toString();
    }

    public String showProductInfo(int index){
        return buyHistories.get(counter).products.get(index).showInfo();
    }

    public void increaseCounter(){
        counter++;
    }

    public boolean buyCheck(Goods g){
        for(Goods i:products){
            if(i.getCode()==g.getCode()){
                return true;
            }
        }
        return false;
    }

    public void setGrade(int number,int index) throws SQLException, ClassNotFoundException {
        Goods temp=buyHistories.get(counter).products.get(index);
        temp.setGrade(number);
        WriteInfo.saveGrade(temp,number);
    }
}
