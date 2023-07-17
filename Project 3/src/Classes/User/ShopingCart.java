package Classes.User;

import java.util.ArrayList;

import Classes.Products.*;

public class ShopingCart{

    ArrayList <Goods> types=new ArrayList<>();
    ArrayList <Integer> number=new ArrayList<>();

    private double price=0;
    private double discountPrice=0;

    public void newProduct(Goods type){
        boolean setCheck=false;

        for(int i=0;i<types.size();i++){
            if(types.get(i).getCode()==type.getCode()){
                int temp=number.get(i)+1;
                number.remove(i);
                number.add(i,temp);
                price+=types.get(i).getPrice();
                setCheck=true;
                break;
            }
        }

        if(!setCheck){
            types.add(type);
            number.add(1);
            price+=type.getPrice();
        }
    }

    public void addProduct(Goods good,int num){
        types.add(good);
        number.add(num);
    }

    public Goods getProduct(int index){
        return types.get(index);
    }

    public void updatePrice(Goods good){
        this.price-=good.getPrice();
    }

    public double getPrice(){
        return this.price-this.discountPrice;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDiscount(){
        this.discountPrice=0;
    }

    public void setDiscountPrice(double price,double percent){
        discountPrice+=price*percent;
    }
}
