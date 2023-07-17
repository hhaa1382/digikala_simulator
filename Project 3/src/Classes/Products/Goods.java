package Classes.Products;

import java.sql.SQLException;
import java.util.ArrayList;

import Classes.Data.WriteInfo;
import Classes.Exeptions.ProductNotExist;
import Classes.User.Admin;
import Classes.User.Seller;

public abstract class Goods implements Comparable<Goods>{
    private int commodityCode=0;

    private String name,brand;
    private double price;
    private int number;
    private boolean exist;
    private Seller seller;
    private String description;
    private double discountPercent=0;

    transient private final ArrayList <Discount> discounts=new ArrayList<>();
    transient ArrayList <Opinion> opinions=new ArrayList<>();
    Grade grade=new Grade();

    Goods(String name,String brand,double price,int number,Seller seller,String description){
        this.name=name;
        this.brand=brand;
        this.price=price;
        this.number=number;
        this.setExist();
        this.seller=seller;
        this.description=description;
    }

    public int getCode(){
        return this.commodityCode;
    }

    public String getName(){
        return this.name;
    }

    public String getBrand(){
        return this.brand;
    }

    public double getPrice(){
        return price*(1-discountPercent);
    }

    public int getNumber(){
        return this.number;
    }

    public Seller getSeller(){
        return this.seller;
    }

    public String getDescription(){
        return this.description;
    }

    public double getDiscountPercent(){
        return this.discountPercent;
    }

    public void setCode(int code){
        this.commodityCode=code;
    }

    public void setName(String name){
        this.name=name;
    }

    public void setBrand(String brand){
        this.brand=brand;
    }

    public void setSeller(Seller seller){
        this.seller=seller;
    }

    public void setPrice(double price){
        this.price=price;
    }

    public void setNumber(int number){
        this.number=number;
    }

    public void setExist(){
        if(number>0){
            exist=true;
        }
        else{
            exist=false;
        }
    }

    public void setDescription(String description){
        this.description=description;
    }

    public void setDiscountPercent(double discount){
        this.discountPercent=discount;
    }

    public void takeDiscount(){
        discountPercent=0;
    }

    public String toString(){
        return "Name : "+this.getName()+"  -  Brand : "+this.getBrand()+
                "  -  Prices : "+this.getPrice()+"  -  Exit : "+this.exist;
    }

    public abstract String showInfo();

    public String showOpinions(){
        StringBuilder temp=new StringBuilder();

        for(Opinion i:opinions){
            temp.append(i.showInfo()+"\n\n");
        }

        return temp.toString();
    }

    public void setOpinion(Opinion o){
        opinions.add(o);
    }

    public void setGrade(int number){
        grade.setGrade(number);
    }

    public void setGradeInfo(int number,int sum){
        grade.number=number;
        grade.sum=sum;
    }

    public int getGradeNumber(){
        return grade.number;
    }

    public int getGradeSum(){
        return grade.sum;
    }

    public void setDiscount(Discount discount){
        discounts.add(discount);
    }

    public void deleteDiscount(int index) throws SQLException, ClassNotFoundException {
        Admin admin=Admin.getAdmin();
        admin.discounts.remove(this.discounts.get(index));
        WriteInfo.deleteDiscount(discounts.get(index).getDiscountCode());
        discounts.remove(index);
    }

    public Discount getDiscount(int index){
        return discounts.get(index);
    }

    public int getDiscountSize(){
        return discounts.size();
    }

    public Discount codeCheck(String code){
        for(Discount i:discounts){
            if(i.getDiscountCode().equals(code)){
                return i;
            }
        }
        return null;
    }

    public boolean CheckCodeUse(String code){
        for(Discount i:discounts){
            if(i.getDiscountCode().equals(code)){
                return true;
            }
        }
        return false;
    }

    public boolean discountCheck(Discount discount){
        if(discounts.contains(discount)){
            return true;
        }
        else{
            return false;
        }
    }

    public String showDiscountCode(){
        StringBuilder list=new StringBuilder();
        list.append("0-Back\n");

        for(int i=0;i<discounts.size();i++){
            list.append((i+1)+"-"+discounts.get(i).toString()+"\n");
        }

        return list.toString();
    }

    public void checkNumber(){
        if(this.number<1){
            throw new ProductNotExist();
        }
    }

    @Override
    public int compareTo(Goods good){
        if(this.grade.getGrade()<good.grade.getGrade()){
            return 1;
        }

        else if(this.grade.getGrade()>good.grade.getGrade()){
            return -1;
        }

        else{
            if(this.price<good.getPrice()){
                return 1;
            }

            else if(this.price>good.getPrice()){
                return -1;
            }

            else{
                if(!this.exist && good.exist){
                    return 1;
                }

                else if(this.exist && !good.exist){
                    return -1;
                }

                else{
                    return 0;
                }
            }
        }
    }

    public boolean checkSearch(String text){
        String check=name+" "+brand+" "+price+" "+seller.getFirstName()+" "+seller.getLastName();

        if(check.contains(text)){
            return true;
        }

        else{
            return false;
        }
    }
}
