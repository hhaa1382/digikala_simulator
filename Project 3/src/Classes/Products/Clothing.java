package Classes.Products;

import java.sql.SQLException;

import Classes.Data.WriteInfo;
import Classes.User.Admin;
import Classes.User.Seller;

public abstract class Clothing extends Goods implements DiscountCapability{

    private String country;
    private String clothingMaterial;
    private int size;
    private boolean checkAuction=false;

    public Clothing(String name,String brand,double price,int number,Seller seller,
                    String description,String country,String clothingMaterial,int size){

        super(name, brand, price, number, seller, description);

        this.country=country;
        this.clothingMaterial=clothingMaterial;
        this.size=size;
    }

    public String getCountry(){
        return country;
    }

    public String getClothingMaterial(){
        return clothingMaterial;
    }

    public int getSize(){
        return this.size;
    }

    public boolean getAuctionCheck(){
        return this.checkAuction;
    }

    public void setCountry(String country){
        this.country=country;
    }

    public void setClothingMaterial(String clothingMaterial){
        this.clothingMaterial=clothingMaterial;
    }

    public void setSize(int size){
        this.size=size;
    }

    public void setAuction(boolean auction){
        this.checkAuction=auction;
    }

    public void addDiscount(){
        super.setDiscountPercent(0.15);
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
}
