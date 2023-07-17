package Classes.Categories;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

import Classes.Data.ReadInfo;
import Classes.Data.WriteInfo;
import Classes.Products.*;
import Classes.User.*;

import javax.swing.*;


public class ClothingCategory extends Category{
    public final ArrayList <Clothes> clothes=new ArrayList<>();
    public final ArrayList <Shoes> shoes=new ArrayList<>();

    private static final ClothingCategory clothingCategory=new ClothingCategory();

    private ClothingCategory(){}
    //singleton object

    static public ClothingCategory getCategory(){
        return clothingCategory;
    }

    public void sort(){
        Collections.sort(shoes);
        Collections.sort(clothes);
    }

    public void readProducts() throws SQLException, ClassNotFoundException {
        ReadInfo.readClothe(clothes);
        ReadInfo.readShoe(shoes);
    }

    //filters
    String filterCountry=null;
    String filterMaterial=null;
    String filterType=null;

    public void setFilterType(String type){
        this.filterType=type;
    }

    public void setFilterMaterial(String material){
        this.filterMaterial=material;
    }

    public void setFilterCountry(String country){
        this.filterCountry=country;
    }

    public void setClothe(Clothes c){
        c.setCode(setClothesCode());
        clothes.add(c);
    }

    public void setShoes(Shoes s){
        s.setCode(setShoesCode());
        shoes.add(s);
    }

    public int setClothesCode(){
        int big=0;

        for(Clothes c:clothes){
            if(c.getCode()>big){
                big=c.getCode();
            }
        }
        return big+1;
    }

    public int setShoesCode(){
        int big=0;

        for(Shoes s:shoes){
            if(s.getCode()>big){
                big=s.getCode();
            }
        }
        return big+1;
    }

    public Clothes getClothes(int index){
        return clothes.get(index);
    }

    public Shoes getShoes(int index){
        return shoes.get(index);
    }

    public String getList(){
        return list;
    }

    public int clotheSize(){
        return clothes.size();
    }

    public int shoesSize(){
        return shoes.size();
    }

    public void showClothesList(DefaultListModel<String> hold, int counter){
        int size;

        if(clothes.size()-counter>=10){
            size=10;
        }
        else{
            size=clothes.size()-counter;
        }

        for(int i=counter;i<size+counter;i++){
            hold.addElement((i+1)+"-"+clothes.get(i).toString());
            hold.addElement("\n");
        }
    }

    public void showShoesList(DefaultListModel<String> hold, int counter){
        int size;

        if(clothes.size()-counter>=10){
            size=10;
        }
        else{
            size=clothes.size()-counter;
        }

        for(int i=counter;i<size+counter;i++){
            hold.addElement((i+1)+"-"+clothes.get(i).toString());
            hold.addElement("\n");
        }
    }

    public void showClothesFilterly(ArrayList<Goods> products){
        for(Clothes c:clothes){
            boolean checkInfo=true;

            if(filterBrand!=null){
                if(!c.getBrand().equals(filterBrand)){
                    checkInfo=false;
                }
            }

            if(checkInfo && filterProductCode!=-1){

                if(c.getCode()!=filterProductCode){
                    checkInfo=false;
                }
            }

            if(checkInfo && exist){

                if(c.getNumber()==0){
                    checkInfo=false;
                }
            }

            if(checkInfo && filterPriceEnd!=0){

                if(c.getPrice()<filterPriceStart || c.getPrice()>filterPriceEnd){
                    checkInfo=false;
                }
            }

            if(checkInfo && filterCountry!=null){

                if(!c.getCountry().equals(filterCountry)){
                    checkInfo=false;
                }
            }

            if(checkInfo && filterMaterial!=null){

                if(!c.getClothingMaterial().equals(filterMaterial)){
                    checkInfo=false;
                }
            }

            if(checkInfo && filterType!=null){

                if(!c.getType().equals(filterType)){
                    checkInfo=false;
                }
            }

            if(checkInfo && filterDiscount){
                if(c.getDiscountPercent()==0){
                    checkInfo=false;
                }
            }

            if(checkInfo){
                products.add(c);
            }
        }
    }

    public void showShoesFilterly(ArrayList<Goods> products){
        for(Shoes s:shoes){
            boolean checkInfo=true;
            
            if(filterBrand!=null){
                if(!s.getBrand().equals(filterBrand)){
                    checkInfo=false;
                }
            }

            if(checkInfo && filterProductCode!=-1){
                if(s.getCode()!=filterProductCode){
                    checkInfo=false;
                }
            }

            if(checkInfo && exist){
                if(s.getNumber()==0){
                    checkInfo=false;
                }
            }

            if(checkInfo && filterPriceEnd!=0){
                if(s.getPrice()<filterPriceStart || s.getPrice()>filterPriceEnd){
                    checkInfo=false;
                }
            }

            if(checkInfo && filterCountry!=null){
                if(!s.getCountry().equals(filterCountry)){
                    checkInfo=false;
                }
            }

            if(checkInfo && filterMaterial!=null){
                if(!s.getClothingMaterial().equals(filterMaterial)){
                    checkInfo=false;
                }
            }

            if(checkInfo && filterType!=null){
                if(!s.getType().equals(filterType)){
                    checkInfo=false;
                }
            }

            if(checkInfo && filterDiscount){
                if(s.getDiscountPercent()==0){
                    checkInfo=false;
                }
            }

            if(checkInfo){
                products.add(s);
            }
        }
    }

    public void changeProduct(Goods real,Goods change) throws SQLException, ClassNotFoundException {
        if(real instanceof Clothes) {
            for (int i = 0; i < clothes.size(); i++) {
                if (clothes.get(i).getCode() == real.getCode()) {
                    clothes.remove(i);
                    clothes.add(i, (Clothes) change);
                    change.setCode(real.getCode());
                    WriteInfo.changeClothe((Clothes) change);
                    return;
                }
            }
        }

        else if(real instanceof Shoes) {
            for (int i = 0; i < shoes.size(); i++) {
                if (shoes.get(i).getCode() == real.getCode()) {
                    shoes.remove(i);
                    shoes.add(i, (Shoes) change);
                    change.setCode(real.getCode());
                    WriteInfo.changeShoe((Shoes) change);
                    return;
                }
            }
        }
    }

    public void deleteProduct(Goods g) throws SQLException, ClassNotFoundException {
        if(g instanceof Clothes){
            clothes.remove(g);
            WriteInfo.deleteProduct(g.getCode(),"clothe");
        }

        else if(g instanceof Shoes){
            shoes.remove(g);
            WriteInfo.deleteProduct(g.getCode(),"shoe");
        }
    }
}
