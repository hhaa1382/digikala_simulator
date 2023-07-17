package Classes.Products;

import java.sql.SQLException;
import java.util.Random;

import Classes.Data.WriteInfo;
import Classes.User.Seller;

enum clothe{
    T_shirt,Pants,Shirt,Suit,Socks;
}

public class Clothes extends Clothing{
    private clothe Type;

    public Clothes(String name,String brand,double price,int number,Seller seller,String description,
                   String country,String clothingMaterial,int size,String Type){

        super(name, brand, price, number, seller, description, country, clothingMaterial,size);

        this.Type=clothe.valueOf(Type);
    }

    public String showInfo(){
        return super.toString()+"\nDescription : "+super.getDescription()+"\n\nInformation :\n\nCountry : "+super.getCountry()+
                "\nClothing Material : "+super.getClothingMaterial()+"\nSize : "+this.getSize()+"\nType : "+this.Type;
    }

    public void setType(String type){
        this.Type=clothe.valueOf(type);
    }

    public String getType(){
        return this.Type.toString();
    }

    public int compareTo(Goods good){
        Clothes clothes=(Clothes)good;

        if(this.getName().compareTo(clothes.getName())>0){
            return 1;
        }

        else if(this.getName().compareTo(clothes.getName())<0){
            return -1;
        }

        else{
            if(this.getSize()>clothes.getSize()){
                return 1;
            }

            else if(this.getSize()<clothes.getSize()){
                return -1;
            }

            else{
                return super.compareTo(good);
            }
        }
    }

    public String makeDiscountCode() {
        Random random=new Random();
        int number=random.nextInt(9000)+1000;
        return "clo"+String.valueOf(number);
    }
}
