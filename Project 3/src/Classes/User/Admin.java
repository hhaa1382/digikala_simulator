package Classes.User;

import java.sql.SQLException;
import java.util.ArrayList;

import Classes.Categories.*;
import Classes.Data.WriteInfo;
import Classes.Products.*;

public class Admin extends User{
    public final ArrayList<Goods> products=new ArrayList<>();
    public final ArrayList<Discount> discounts=new ArrayList<>();

    public final ArrayList<Customer> customers=new ArrayList<>();
    public final ArrayList<Seller> sellers=new ArrayList<>();

    private final ArrayList<Seller> requests=new ArrayList<>();

    private final ArrayList<Goods> change_product=new ArrayList<>();
    private final ArrayList<Goods> real_product=new ArrayList<>();

    private final ArrayList<Goods> add_product=new ArrayList<>();

    private final ArrayList<Goods> delete_product=new ArrayList<>();

    private final ArrayList <Opinion> opinion_request=new ArrayList<>();

    private final static Admin oneManager=new Admin();

    private int counter=-1;

    private Admin(){}

    public static Admin getAdmin(){
        return oneManager;
    }

    public void setCounter(int counter){
        this.counter=counter;
    }

    public String personalInformation(){
        return "Name : "+this.getFirstName()+" "+this.getLastName()+"\n"+"Email : "+this.getEmail()+"\n"+"Phone Number : "+
                this.getPhoneNumber()+"\n"+"Username : "+this.getUserName()+"\n";
    }

    public String checkRequests(){
        if(counter<requests.size()-1 && requests.size()!=0){
            this.counter++;
            return requests.get(counter).personalInformation();
        }

        else{
            counter=-1;
            return null;
        }
    }

    public void acceptRequest() throws SQLException, ClassNotFoundException {
        Seller temp=requests.get(counter);

        sellers.add(temp);
        deleteRequest();
        WriteInfo.acceptSeller(temp);
    }

    public void deleteRequest() throws SQLException, ClassNotFoundException {
        WriteInfo.deleteRequest(requests.get(counter),null,"seller",null);
        requests.remove(counter);
        counter--;
    }

    public String showCustomerList(){
        if(counter<customers.size()-1 && customers.size()!=0){
            counter++;
            return customers.get(counter).personalInformation();
        }

        else {
            counter=-1;
            return null;
        }
    }

    public String showSellerList(){
        if(counter<sellers.size()-1 && sellers.size()!=0){
            counter++;
            return sellers.get(counter).personalInformation();
        }

        else {
            counter=-1;
            return null;
        }
    }

    public boolean deleteCustomer(String username) throws SQLException, ClassNotFoundException {
        for(int i=0;i<customers.size();i++){
            if(customers.get(i).getUserName().equals(username)){
                WriteInfo.deleteCustomer(customers.get(i));
                customers.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean deleteSeller(String username) throws SQLException, ClassNotFoundException {
        for(int i=0;i<sellers.size();i++){
            if(sellers.get(i).getUserName().equals(username)){
                WriteInfo.deleteSeller(sellers.get(i));
                deleteSellerProducts(sellers.get(i));
                sellers.remove(i);
                return true;
            }
        }
        return false;
    }

    public void deleteSellerProducts(Seller seller) throws SQLException, ClassNotFoundException {
        for(int i=0;i<seller.getProductsSize();i++){
            Goods temp=seller.getProduct(i);
            if(temp instanceof DigitalCommodity){
                DigitalCategory d=DigitalCategory.getCategory();
                d.deleteProduct(temp);
            }

            else if(temp instanceof Clothing){
                ClothingCategory c=ClothingCategory.getCategory();
                c.deleteProduct(temp);
            }

            else if(temp instanceof HomeAppliances){
                HomeCategory h=HomeCategory.getCategory();
                h.deleteProduct(temp);
            }

            else if(temp instanceof Food){
                FoodCategory f=FoodCategory.getCategory();
                f.deleteProduct(temp);
            }
            products.remove(temp);
        }
    }

    public String checkAddProductRequests(){
        if(counter<add_product.size()-1 && add_product.size()!=0){
            this.counter++;
            return add_product.get(counter).showInfo();
        }

        else{
            counter=-1;
            return null;
        }
    }

    public String checkChangeProductRequests(){
        if(counter<change_product.size()-1 && change_product.size()!=0){
            this.counter++;

            return "Information before change :\n"+real_product.get(counter).showInfo()+
                    "\n\nInformation after change :\n"+change_product.get(counter).showInfo();
        }

        else{
            counter=-1;
            return null;
        }
    }

    public String checkDeleteProductRequests(){
        if(counter<delete_product.size()-1 && delete_product.size()!=0){
            this.counter++;
            return delete_product.get(counter).showInfo();
        }

        else{
            counter=-1;
            return null;
        }
    }

    public void AddProduct() throws SQLException, ClassNotFoundException {
        if(add_product.get(counter) instanceof Mobile){
            DigitalCategory d=DigitalCategory.getCategory();
            Mobile m=(Mobile)add_product.get(counter);
            d.setMobile(m);
            products.add(m);
            WriteInfo.saveProducts(m,"mobile");
        }

        else if(add_product.get(counter) instanceof Laptop){
            DigitalCategory d=DigitalCategory.getCategory();
            Laptop l=(Laptop)add_product.get(counter);
            d.setLaptop(l);
            products.add(l);
            WriteInfo.saveProducts(l,"laptop");
        }

        else if(add_product.get(counter) instanceof Clothes){
            ClothingCategory category=ClothingCategory.getCategory();
            Clothes c=(Clothes)add_product.get(counter);
            category.setClothe(c);
            products.add(c);
            WriteInfo.saveProducts(c,"clothe");
        }

        else if(add_product.get(counter) instanceof Shoes){
            ClothingCategory category=ClothingCategory.getCategory();
            Shoes s=(Shoes)add_product.get(counter);
            category.setShoes(s);
            products.add(s);
            WriteInfo.saveProducts(s,"shoe");
        }

        else if(add_product.get(counter) instanceof Television){
            HomeCategory h=HomeCategory.getCategory();
            Television t=(Television)add_product.get(counter);
            h.setTV(t);
            products.add(t);
            WriteInfo.saveProducts(t,"tv");
        }

        else if(add_product.get(counter) instanceof Refrigerator){
            HomeCategory h=HomeCategory.getCategory();
            Refrigerator r=(Refrigerator)add_product.get(counter);
            h.setRefrigerator(r);
            products.add(r);
            WriteInfo.saveProducts(r,"refrigerator");
        }

        else if(add_product.get(counter) instanceof GasStove){
            HomeCategory h=HomeCategory.getCategory();
            GasStove g=(GasStove)add_product.get(counter);
            h.setGasStove(g);
            products.add(g);
            WriteInfo.saveProducts(g,"gasStove");
        }

        else if(add_product.get(counter) instanceof Food){
            FoodCategory category=FoodCategory.getCategory();
            Food f=(Food)add_product.get(counter);
            category.setFood(f);
            products.add(f);
            WriteInfo.saveProducts(f,"food");
        }

        WriteInfo.deleteRequest(null,add_product.get(counter),"add",null);
        setProduct(add_product.get(counter));
        add_product.remove(counter);
        counter--;
    }

    public void ChangeProduct() throws SQLException, ClassNotFoundException {
        Seller seller=change_product.get(counter).getSeller();
        seller.changeProduct(real_product.get(counter), change_product.get(counter));
        this.changeProduct(real_product.get(counter), change_product.get(counter));

        if(change_product.get(counter) instanceof DigitalCommodity){
            DigitalCategory d=DigitalCategory.getCategory();
            d.changeProduct(real_product.get(counter),change_product.get(counter));
        }

        else if(change_product.get(counter) instanceof Clothing){
            ClothingCategory c=ClothingCategory.getCategory();
            c.changeProduct(real_product.get(counter),change_product.get(counter));
        }

        else if(change_product.get(counter) instanceof HomeAppliances){
            HomeCategory h=HomeCategory.getCategory();
            h.changeProduct(real_product.get(counter),change_product.get(counter));
        }

        else if(change_product.get(counter) instanceof Food){
            FoodCategory f=FoodCategory.getCategory();
            f.changeProduct(real_product.get(counter),change_product.get(counter));
        }

        WriteInfo.deleteRequest(null,real_product.get(counter),"change",null);
        change_product.remove(counter);
        real_product.remove(counter);
        counter--;
    }

    public void deleteProduct() throws SQLException, ClassNotFoundException {
        Seller seller=delete_product.get(counter).getSeller();
        seller.deleteProduct(delete_product.get(counter));
        products.remove(delete_product.get(counter));

        if(delete_product.get(counter) instanceof DigitalCommodity){
            DigitalCategory d=DigitalCategory.getCategory();
            d.deleteProduct(delete_product.get(counter));
        }

        else if(delete_product.get(counter) instanceof Clothing){
            ClothingCategory c=ClothingCategory.getCategory();
            c.deleteProduct(delete_product.get(counter));
        }

        else if(delete_product.get(counter) instanceof HomeAppliances){
            HomeCategory h=HomeCategory.getCategory();
            h.deleteProduct(delete_product.get(counter));
        }

        else if(delete_product.get(counter) instanceof Food){
            FoodCategory f=FoodCategory.getCategory();
            f.deleteProduct(delete_product.get(counter));
        }

        WriteInfo.deleteRequest(null,delete_product.get(counter),"delete",null);
        delete_product.remove(counter);
    }

    void setProduct(Goods g){
        Seller seller=g.getSeller();
        seller.setProduct(g);
    }

    public void deleteAddRequest() throws SQLException, ClassNotFoundException {
        WriteInfo.deleteRequest(null,add_product.get(counter),"add",null);
        add_product.remove(counter);
        counter--;
    }

    public void deleteChangeRequest() throws SQLException, ClassNotFoundException {
        WriteInfo.deleteRequest(null,real_product.get(counter),"change",null);
        change_product.remove(counter);
        real_product.remove(counter);
        counter--;
    }

    public void deleteDeleteRequest() throws SQLException, ClassNotFoundException {
        WriteInfo.deleteRequest(null,delete_product.get(counter),"delete",null);
        delete_product.remove(counter);
        counter--;
    }

    public void setSellerRequest(Seller s){
        requests.add(s);
    }

    public void setAddRequestProduct(Goods g){
        add_product.add(g);
    }

    public void setChangeRequest(Goods changed,Goods real){
        change_product.add(changed);
        real_product.add(real);
    }

    public void setDeleteProduct(Goods g){
        delete_product.add(g);
    }

    public void setOpinionRequest(Opinion o){
        opinion_request.add(o);
    }

    public String checkOpinion(){
        if(counter<opinion_request.size()-1 && opinion_request.size()!=0){
            this.counter++;
            Goods g=opinion_request.get(counter).good;

            return g.toString()+"\n"+opinion_request.get(counter).showInfo();
        }

        else{
            counter=-1;
            return null;
        }
    }

    public void deleteOpinion() throws SQLException, ClassNotFoundException {
        opinion_request.get(counter).condition="Not accept";
        WriteInfo.deleteRequest(null,opinion_request.get(counter).good,"opinion",opinion_request.get(counter));
        opinion_request.remove(counter);
        counter--;
    }

    public void addOpinion() throws SQLException, ClassNotFoundException {
        opinion_request.get(counter).condition="Accept";
        WriteInfo.deleteRequest(null,opinion_request.get(counter).good,"opinion",opinion_request.get(counter));
        Goods g=opinion_request.get(counter).good;
        g.setOpinion(opinion_request.get(counter));
        WriteInfo.saveOpinion(opinion_request.get(counter));
        opinion_request.remove(counter);
    }

    public void changeProduct(Goods real,Goods change){
        for(int i=0;i<products.size();i++){
            if(real==products.get(i)){
                products.remove(i);
                products.add(i, change);
                return;
            }
        }
    }
}
