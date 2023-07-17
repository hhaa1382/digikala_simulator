package Classes.Products;

import java.sql.SQLException;
import java.util.Random;

import Classes.Data.WriteInfo;
import Classes.User.Admin;
import Classes.User.Seller;

public class Food extends Goods implements DiscountCapability{
    private String productionDate;
    private String expiryDate;
    private boolean checkAuction=false;

    public Food(String name,String brand,double price,int number,
                Seller seller,String description,String productionDate,String expiryDate){

        super(name, brand, price, number, seller, description);

        this.productionDate=productionDate;
        this.expiryDate=expiryDate;
    }

    public String showInfo(){
        return super.toString()+"\nDescription : "+super.getDescription()+"\n\nInformation :\n\nProduction Date : "+this.productionDate+
                "\nExpiry Date : "+this.expiryDate;
    }

    public void setProductionDate(String date){
        this.productionDate=date;
    }

    public void setExpiryDate(String date){
        this.expiryDate=date;
    }

    public void setAuction(boolean auction){
        this.checkAuction=auction;
    }

    public String getProductionDate(){
        return productionDate;
    }

    public String getExpiryDate(){
        return expiryDate;
    }

    public boolean getAuctionCheck(){
        return this.checkAuction;
    }

    public String makeDiscountCode() {
        Random random=new Random();
        int number=random.nextInt(9000)+1000;
        return "foo"+String.valueOf(number);
    }

    public Discount addDiscountByCode(String value, int capacity) throws SQLException, ClassNotFoundException {
        Discount discount=new Discount(0.3, value, capacity);
        String discountCode=makeDiscountCode();

        while(CheckCodeUse(discountCode)){
            discountCode=makeDiscountCode();
        }

        discount.setDiscountCode(discountCode);
        Admin admin=Admin.getAdmin();
        admin.discounts.add(discount);
        WriteInfo.saveDiscount(discountCode,discount.getDiscountValue().toString(),getCode(),capacity);
        return discount;
    }

    public void addDiscount(){
        super.setDiscountPercent(0.2);
    }
}
