package Classes.Categories;

import Classes.Data.*;
import Classes.Products.*;
import Classes.User.*;

import javax.swing.*;
import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

public class DigitalCategory extends Category{
    public final ArrayList <Mobile> mobiles=new ArrayList<>();
    public final ArrayList <Laptop> laptops=new ArrayList<>();

    private static final DigitalCategory digitalCategory=new DigitalCategory();

    private DigitalCategory(){}
    //sigelton object

    static public DigitalCategory getCategory(){
        return digitalCategory;
    }

    public void sort(){
        Collections.sort(mobiles);
        Collections.sort(laptops);
    }

    public void readProducts() throws SQLException, ClassNotFoundException {
        ReadInfo.readMobile(mobiles);
        ReadInfo.readLaptop(laptops);
    }

    public void setMobile(Mobile m) throws SQLException, ClassNotFoundException {
        m.setCode(WriteInfo.getProductCode());
        mobiles.add(m);
    }

    public void setLaptop(Laptop l) throws SQLException, ClassNotFoundException {
        l.setCode(WriteInfo.getProductCode());
        laptops.add(l);
    }

    public Mobile getMobile(int index){
        return mobiles.get(index);
    }

    public Laptop getLaptop(int index){
        return laptops.get(index);
    }

    public String getList(){
        return list;
    }

    public int mobileSize(){
        return mobiles.size();
    }

    public int laptopSize(){
        return laptops.size();
    }

    public void showMobileList(DefaultListModel<String> hold,int counter){
        int size;

        if(mobiles.size()-counter>=10){
            size=10;
        }
        else{
            size=mobiles.size()-counter;
        }

        for(int i=counter;i<size+counter;i++){
            hold.addElement((i+1)+"-"+mobiles.get(i).toString());
            hold.addElement("\n");
        }
    }

    public void showLaptopList(DefaultListModel<String> hold,int counter){
        int size;

        if(laptops.size()-counter>=10){
            size=10;
        }
        else{
            size=laptops.size()-counter;
        }

        for(int i=counter;i<size+counter;i++){
            hold.addElement((i+1)+"-"+laptops.get(i).toString());
            hold.addElement("\n");
        }
    }

    String filterCameraQuality=null;
    int filterMemoryCapacity=0;
    int filterRam=0;
    String filterCpuModel=null;

    public void setFilterMemoryCapacity(int memoryCapacity){
        this.filterMemoryCapacity=memoryCapacity;
    }

    public void setFilterRam(int ram){
        this.filterRam=ram;
    }

    public void setFilterCameraQuality(String quality){
        this.filterCameraQuality=quality;
    }

    public void setFilterCpuModel(String model){
        this.filterCpuModel=model;
    }

    public void showMobileFilterly(ArrayList<Goods> products) {
        for (Mobile m : mobiles) {
            boolean checkInfo = true;

            if (filterBrand != null) {
                if (!m.getBrand().equals(filterBrand)) {
                    checkInfo = false;
                }
            }

            if (checkInfo && filterProductCode != -1) {
                if (m.getCode() != filterProductCode) {
                    checkInfo = false;
                }
            }

            if (checkInfo && exist) {
                if (m.getNumber() == 0) {
                    checkInfo = false;
                }
            }

            if (checkInfo && filterPriceEnd != 0) {
                if (m.getPrice() < filterPriceStart || m.getPrice() > filterPriceEnd) {
                    checkInfo = false;
                }
            }

            if (checkInfo && filterCameraQuality != null) {
                if (!m.getCameraQuality().equals(filterCameraQuality)) {
                    checkInfo = false;
                }
            }

            if (checkInfo && filterMemoryCapacity != 0) {
                if (m.getMemoryCapacity() != filterMemoryCapacity) {
                    checkInfo = false;
                }
            }

            if (checkInfo && filterDiscount) {
                if (m.getDiscountPercent() == 0) {
                    checkInfo = false;
                }
            }

            if (checkInfo) {
                products.add(m);
            }
        }
    }

    public void showLaptopFilterly(ArrayList<Goods> products){
        for(Laptop l:laptops){
            boolean checkInfo=true;

            if(filterBrand!=null){
                if(!l.getBrand().equals(filterBrand)){
                    checkInfo=false;
                }
            }

            if(checkInfo && filterProductCode!=-1){
                if(l.getCode()!=filterProductCode){
                    checkInfo=false;
                }
            }

            if(checkInfo && exist){
                if(l.getNumber()==0){
                    checkInfo=false;
                }
            }

            if(checkInfo && filterPriceEnd!=0){
                if(l.getPrice()<filterPriceStart || l.getPrice()>filterPriceEnd){
                    checkInfo=false;
                }
            }

            if(checkInfo && filterRam!=0){
                if(l.getRam()!=filterRam){
                    checkInfo=false;
                }
            }

            if(checkInfo && filterMemoryCapacity!=0){
                if(l.getMemoryCapacity()!=filterMemoryCapacity){
                    checkInfo=false;
                }
            }

            if(checkInfo && filterCpuModel!=null){
                if(l.getCpuModel()!=filterCpuModel){
                    checkInfo=false;
                }
            }

            if(checkInfo && filterDiscount){
                if(l.getDiscountPercent()==0){
                    checkInfo=false;
                }
            }

            if(checkInfo){
                products.add(l);
            }
        }
    }

    public void changeProduct(Goods real,Goods change) throws SQLException, ClassNotFoundException {
        if(real instanceof Clothes) {
            for (int i = 0; i < mobiles.size(); i++) {
                if (mobiles.get(i).getCode() == real.getCode()) {
                    mobiles.remove(i);
                    mobiles.add(i, (Mobile) change);
                    change.setCode(real.getCode());
                    WriteInfo.changeMobile((Mobile) change);
                    return;
                }
            }
        }

        else if(real instanceof Shoes) {
            for (int i = 0; i < laptops.size(); i++) {
                if (laptops.get(i).getCode() == real.getCode()) {
                    laptops.remove(i);
                    laptops.add(i, (Laptop) change);
                    change.setCode(real.getCode());
                    WriteInfo.changeLaptop((Laptop) change);
                    return;
                }
            }
        }
    }

    public void deleteProduct(Goods g) throws SQLException, ClassNotFoundException {
        if(g instanceof Mobile){
            mobiles.remove(g);
            WriteInfo.deleteProduct(g.getCode(),"mobile");
        }

        else if(g instanceof Laptop){
            laptops.remove(g);
            WriteInfo.deleteProduct(g.getCode(),"laptop");
        }
    }
}
