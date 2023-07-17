package Classes.Products;

import java.sql.SQLException;
import java.util.Random;

import Classes.Data.WriteInfo;
import Classes.User.Seller;

enum shoe{
    Formal,Boot,Sports,Sandal;
}

public class Shoes extends Clothing{
    private shoe Type;

    public Shoes(String name,String brand,double price,int number,Seller seller,String description,
                 String country,String clothingMaterial,int size,String Type){

        super(name, brand, price, number, seller, description, country, clothingMaterial,size);

        this.Type=shoe.valueOf(Type);
    }

    public String showInfo(){
        return super.toString()+"\nDescription : "+super.getDescription()+"\n\nInformation :\n\nCountry : "+super.getCountry()+
                "\nClothing Material : "+super.getClothingMaterial()+"\nSize : "+this.getSize()+"\nType : "+this.Type;
    }

    public void setType(String check){
        this.Type=shoe.valueOf(check);
    }

    public String getType(){
        return this.Type.toString();
    }

    public int compareTo(Goods good){
        Shoes shoes=(Shoes)good;

        if(this.getName().compareTo(shoes.getName())>0){
            return 1;
        }

        else if(this.getName().compareTo(shoes.getName())<0){
            return -1;
        }

        else{
            if(this.getSize()<shoes.getSize()){
                return 1;
            }

            else if(this.getSize()>shoes.getSize()){
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
        return "sho"+String.valueOf(number);
    }
}
