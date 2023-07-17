package Classes.Categories;

import Classes.Data.ReadInfo;
import Classes.Data.WriteInfo;
import Classes.Products.*;
import Classes.User.*;

import javax.swing.*;
import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

public class FoodCategory extends Category{
    public final ArrayList <Food> foods=new ArrayList<>();

    private static final FoodCategory foodCategory=new FoodCategory();

    private FoodCategory(){}
    //sigelton object

    static public FoodCategory getCategory(){
        return foodCategory;
    }

    public void sort(){
        Collections.sort(foods);
    }

    public void readProducts() throws SQLException, ClassNotFoundException {
        ReadInfo.readFood(foods);
    }

    public void setFood(Food f) throws SQLException, ClassNotFoundException {
        f.setCode(WriteInfo.getProductCode());
        foods.add(f);
    }

    public Food getFood(int index){
        return foods.get(index);
    }

    public String getList(){
        return list;
    }

    public int foodSize(){
        return foods.size();
    }

    public void showFoodList(DefaultListModel<String> hold, int counter){
        int size;

        if(foods.size()-counter>=10){
            size=10;
        }
        else{
            size=foods.size()-counter;
        }

        for(int i=counter;i<size+counter;i++){
            hold.addElement((i+1)+"-"+foods.get(i).toString());
            hold.addElement("\n");
        }
    }

    public void showFoodFilterly(ArrayList<Goods> products){
        for(Food f:foods){
            boolean checkInfo=true;

            if(filterBrand!=null){
                if(!f.getBrand().equals(filterBrand)){
                    checkInfo=false;
                }
            }

            if(checkInfo && filterProductCode!=-1){
                if(f.getCode()!=filterProductCode){
                    checkInfo=false;
                }
            }

            if(checkInfo && exist){
                if(f.getNumber()==0){
                    checkInfo=false;
                }
            }

            if(checkInfo && filterPriceEnd!=0){
                if(f.getPrice()<filterPriceStart || f.getPrice()>filterPriceEnd){
                    checkInfo=false;
                }
            }

            if(checkInfo && filterDiscount){
                if(f.getDiscountPercent()==0){
                    checkInfo=false;
                }
            }

            if(checkInfo){
                products.add(f);
            }
        }
    }

    public void changeProduct(Goods real,Goods change) throws SQLException, ClassNotFoundException {
        if(real instanceof Food) {
            for (int i = 0; i < foods.size(); i++) {
                if (foods.get(i).getCode() == real.getCode()) {
                    foods.remove(i);
                    foods.add(i, (Food) change);
                    change.setCode(real.getCode());
                    WriteInfo.changeFood((Food) change);
                    return;
                }
            }
        }
    }

    public void deleteProduct(Goods g) throws SQLException, ClassNotFoundException {
        if(g instanceof Food){
            foods.remove(g);
            WriteInfo.deleteProduct(g.getCode(),"food");
        }
    }
}
