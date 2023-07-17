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

public class HomeCategory extends Category{
    public final ArrayList <Television> televisions=new ArrayList<>();
    public final ArrayList <Refrigerator> refrigerators=new ArrayList<>();
    public final ArrayList <GasStove> gasStoves=new ArrayList<>();

    private static final HomeCategory homeCategory=new HomeCategory();

    private HomeCategory(){}
    //sigelton object

    static public HomeCategory getCategory(){
        return homeCategory;
    }

    public void sort(){
        Collections.sort(televisions);
        Collections.sort(refrigerators);
        Collections.sort(gasStoves);
    }

    public void readProducts() throws SQLException, ClassNotFoundException {
        ReadInfo.readTv(televisions);
        ReadInfo.readRefrigerator(refrigerators);
        ReadInfo.readGasStove(gasStoves);
    }

    public void setTV(Television t) throws SQLException, ClassNotFoundException {
        t.setCode(WriteInfo.getProductCode());
        televisions.add(t);
    }


    public void setRefrigerator(Refrigerator r) throws SQLException, ClassNotFoundException {
        r.setCode(WriteInfo.getProductCode());
        refrigerators.add(r);
    }

    public void setGasStove(GasStove g) throws SQLException, ClassNotFoundException {
        g.setCode(WriteInfo.getProductCode());
        gasStoves.add(g);
    }

    public Television getTV(int index){
        return televisions.get(index);
    }

    public GasStove getGasStove(int index){
        return gasStoves.get(index);
    }

    public Refrigerator getRegrigerator(int index){
        return refrigerators.get(index);
    }

    public String getList(){
        return list;
    }

    public int tvSize(){
        return televisions.size();
    }

    public int refrigeratorSize(){
        return refrigerators.size();
    }

    public int gasStoveSize(){
        return gasStoves.size();
    }

    public void showTelevisionList(DefaultListModel<String> hold, int counter){
        int size;

        if(televisions.size()-counter>=10){
            size=10;
        }
        else{
            size=televisions.size()-counter;
        }

        for(int i=counter;i<size+counter;i++){
            hold.addElement((i+1)+"-"+televisions.get(i).toString());
            hold.addElement("\n");
        }
    }

    public void showRefrigeratorList(DefaultListModel<String> hold, int counter){
        int size;

        if(refrigerators.size()-counter>=10){
            size=10;
        }
        else{
            size=refrigerators.size()-counter;
        }

        for(int i=counter;i<size+counter;i++){
            hold.addElement((i+1)+"-"+refrigerators.get(i).toString());
            hold.addElement("\n");
        }
    }

    public void showGasStoveList(DefaultListModel<String> hold, int counter){
        int size;

        if(gasStoves.size()-counter>=10){
            size=10;
        }
        else{
            size=gasStoves.size()-counter;
        }

        for(int i=counter;i<size+counter;i++){
            hold.addElement((i+1)+"-"+gasStoves.get(i).toString());
            hold.addElement("\n");
        }
    }

    //filters
    boolean filterWarranty=false;
    boolean filterTemp=false;
    String filterTVQuality=null;

    public void setFilterTvQuality(String quality){
        this.filterTVQuality=quality;
    }

    public void setFilterWarranty(boolean warranty){
        this.filterWarranty=warranty;
    }

    public void setFilterTemp(Boolean check){
        this.filterTemp=check;
    }

    public void showTVFilterly(ArrayList<Goods> products){
        for (Television t:televisions){
            boolean checkInfo=true;

            if(filterBrand!=null){
                if(!t.getBrand().equals(filterBrand)){
                    checkInfo=false;
                }
            }

            if(checkInfo && filterProductCode!=-1){
                if(t.getCode()!=filterProductCode){
                    checkInfo=false;
                }
            }

            if(checkInfo && exist){
                if(t.getNumber()==0){
                    checkInfo=false;
                }
            }

            if(checkInfo && filterPriceEnd!=0){
                if(t.getPrice()<filterPriceStart || t.getPrice()>filterPriceEnd){
                    checkInfo=false;
                }
            }

            if(checkInfo && filterTVQuality!=null){
                if(!t.getQuality().equals(filterTVQuality)){
                    checkInfo=false;
                }
            }

            if(checkInfo && filterWarranty){
                if(!t.getWarranty()){
                    checkInfo=false;
                }
            }

            if(checkInfo && filterDiscount){
                if(t.getDiscountPercent()==0){
                    checkInfo=false;
                }
            }

            if(checkInfo){
                products.add(t);
            }
        }
    }

    public void showRefrigeratorFilterly(ArrayList<Goods> products){
        for(Refrigerator r:refrigerators){
            boolean checkInfo=true;

            if(filterBrand!=null){
                if(!r.getBrand().equals(filterBrand)){
                    checkInfo=false;
                }
            }

            if(checkInfo && filterProductCode!=-1){
                if(r.getCode()!=filterProductCode){
                    checkInfo=false;
                }
            }

            if(checkInfo && exist){
                if(r.getNumber()==0){
                    checkInfo=false;
                }
            }

            if(checkInfo && filterPriceEnd!=0){
                if(r.getPrice()<filterPriceStart || r.getPrice()>filterPriceEnd){
                    checkInfo=false;
                }
            }

            if(checkInfo && filterWarranty){
                if(!r.getWarranty()){
                    checkInfo=false;
                }
            }

            if(checkInfo && filterTemp){
                if(!r.getFreezer()){
                    checkInfo=false;
                }
            }

            if(checkInfo && filterDiscount){
                if(r.getDiscountPercent()==0){
                    checkInfo=false;
                }
            }

            if(checkInfo){
                products.add(r);
            }
        }
    }

    public void showGasStoveFilterly(ArrayList<Goods> products){
        for(GasStove g:gasStoves){
            boolean checkInfo=true;

            if(filterBrand!=null){
                if(!g.getBrand().equals(filterBrand)){
                    checkInfo=false;
                }
            }

            if(checkInfo && filterProductCode!=-1){
                if(g.getCode()!=filterProductCode){
                    checkInfo=false;
                }
            }

            if(checkInfo && exist){
                if(g.getNumber()==0){
                    checkInfo=false;
                }
            }

            if(checkInfo && filterPriceEnd!=0){
                if(g.getPrice()<filterPriceStart || g.getPrice()>filterPriceEnd){
                    checkInfo=false;
                }
            }

            if(checkInfo && filterWarranty){
                if(!g.getWarranty()){
                    checkInfo=false;
                }
            }

            if(checkInfo && filterTemp){
                if(!g.getGasOven()){
                    checkInfo=false;
                }
            }

            if(checkInfo && filterDiscount){
                if(g.getDiscountPercent()==0){
                    checkInfo=false;
                }
            }

            if(checkInfo){
                products.add(g);
            }
        }
    }

    public void changeProduct(Goods real,Goods change) throws SQLException, ClassNotFoundException {
        if(real instanceof Clothes) {
            for (int i = 0; i < televisions.size(); i++) {
                if (televisions.get(i).getCode() == real.getCode()) {
                    televisions.remove(i);
                    televisions.add(i, (Television) change);
                    change.setCode(real.getCode());
                    WriteInfo.changeTv((Television) change);
                    return;
                }
            }
        }

        else if(real instanceof Refrigerator) {
            for (int i = 0; i < refrigerators.size(); i++) {
                if (refrigerators.get(i).getCode() == real.getCode()) {
                    refrigerators.remove(i);
                    refrigerators.add(i, (Refrigerator) change);
                    change.setCode(real.getCode());
                    WriteInfo.changeRefrigerator((Refrigerator) change);
                    return;
                }
            }
        }

        else if(real instanceof GasStove) {
            for (int i = 0; i < gasStoves.size(); i++) {
                if (gasStoves.get(i).getCode() == real.getCode()) {
                    gasStoves.remove(i);
                    gasStoves.add(i, (GasStove) change);
                    change.setCode(real.getCode());
                    WriteInfo.changeGasStove((GasStove) change);
                    return;
                }
            }
        }
    }

    public void deleteProduct(Goods g) throws SQLException, ClassNotFoundException {
        if(g instanceof Television){
            televisions.remove(g);
            WriteInfo.deleteProduct(g.getCode(),"tv");
        }

        else if(g instanceof Refrigerator){
            refrigerators.remove(g);
            WriteInfo.deleteProduct(g.getCode(),"refrigerator");
        }

        else if(g instanceof GasStove){
            gasStoves.remove(g);
            WriteInfo.deleteProduct(g.getCode(),"gasstove");
        }
    }
}

