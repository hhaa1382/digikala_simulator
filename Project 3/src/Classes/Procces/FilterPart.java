package Classes.Procces;

import java.awt.*;
import java.util.ArrayList;

import Classes.Categories.*;
import Classes.Products.*;
import Classes.User.Admin;
import Classes.User.Customer;

import javax.swing.*;

public class FilterPart extends JFrame {
    int productChoice, categoryChoice;
    Category category;
    String choice;

    int counter=0;
    JButton btnNext=new JButton("Next");
    JButton btnCancel=new JButton("Cancel");
    JButton btnSetFilter=new JButton("Set Filter");
    JButton btnTakeFilter=new JButton("Take Filter");

    DefaultListModel<String> hold=new DefaultListModel<>();
    JList<String> list=new JList<>(hold);

    ArrayList<Goods> products=new ArrayList<>();

    public FilterPart(int user, Customer customer, Category category, int productChoice, int categoryChoice,JFrame preFrame,MainPart part){
        this.category=category;
        this.productChoice=productChoice;
        this.categoryChoice=categoryChoice;

        String[] values=filters();
        choice=(String) JOptionPane.showInputDialog(null,"Choose a filter","Filters",JOptionPane.PLAIN_MESSAGE,null,values,null);

        if(choice==null){
            dispose();
            preFrame.setVisible(true);
        }

        if(choice!=null) {
            setFilter(choice,values);

            this.setTitle("Filter");
            this.setSize(500, 500);
            this.setDefaultCloseOperation(EXIT_ON_CLOSE);
            this.setResizable(false);
            this.setLocationRelativeTo(null);

            while(true){
                choice=null;
                choice=(String) JOptionPane.showInputDialog(null,"Choose a filter","Filters",JOptionPane.PLAIN_MESSAGE,null,values,null);

                if(choice==null){
                    setProducts();
                    showProduct();
                    break;
                }
                else {
                    setFilter(choice, values);
                }
            }

            Container container=this.getContentPane();

            SpringLayout layout=new SpringLayout();
            this.setLayout(layout);

            layout.putConstraint(SpringLayout.WEST,btnSetFilter,10,SpringLayout.WEST,container);
            layout.putConstraint(SpringLayout.NORTH,btnSetFilter,15,SpringLayout.NORTH,container);
            layout.putConstraint(SpringLayout.WEST,btnTakeFilter,20,SpringLayout.EAST,btnSetFilter);
            layout.putConstraint(SpringLayout.NORTH,btnTakeFilter,15,SpringLayout.NORTH,container);

            layout.putConstraint(SpringLayout.WEST,list,100,SpringLayout.WEST,container);
            layout.putConstraint(SpringLayout.NORTH,list,50,SpringLayout.NORTH,container);

            layout.putConstraint(SpringLayout.WEST,btnNext,150,SpringLayout.WEST,container);
            layout.putConstraint(SpringLayout.NORTH,btnNext,20,SpringLayout.SOUTH,list);
            layout.putConstraint(SpringLayout.WEST,btnCancel,300,SpringLayout.WEST,container);
            layout.putConstraint(SpringLayout.NORTH,btnCancel,20,SpringLayout.SOUTH,list);

            btnSetFilter.addActionListener(e -> {
                while(true){
                    choice=null;
                    choice=(String) JOptionPane.showInputDialog(null,"Choose a filter","Filters",JOptionPane.PLAIN_MESSAGE,null,values,null);

                    if(choice==null){
                        products.clear();
                        hold.clear();
                        setProducts();
                        showProduct();
                        break;
                    }
                    else {
                        setFilter(choice, values);
                    }
                }
            });

            btnTakeFilter.addActionListener(e -> {
                while(true){
                    choice=null;
                    choice=(String) JOptionPane.showInputDialog(null,"Choose a filter","Filters",JOptionPane.PLAIN_MESSAGE,null,values,null);

                    if(choice==null){
                        products.clear();
                        hold.clear();
                        setProducts();
                        showProduct();
                        break;
                    }
                    else {
                        filterFix(category,choice,values);
                    }
                }
            });

            btnCancel.addActionListener(e -> {
                dispose();
                endBuy(category);
                preFrame.setVisible(true);
            });

            btnNext.addActionListener(e -> {
                if(products.size()-counter>10) {
                    counter+=10;
                    hold.clear();
                    showProduct();
                }
            });

            list.addListSelectionListener(e -> {
                Admin admin=Admin.getAdmin();
                int index=list.getSelectedIndex();
                if(index % 2 == 0){
                    if(!e.getValueIsAdjusting()) {
                        index/=2;

                        if(user==0){
                            Products product = new Products(null, this , products.get(counter+(index)), false,part);
                        }

                        else if(user==1) {
                            Products product = new Products(null, this , products.get(counter+(index)), true,null);
                        }

                        else if(user==3){
                            Products product = new Products(customer, this , products.get(counter+(index)), true,null);
                        }
                    }
                }
            });

            layout.putConstraint(SpringLayout.SOUTH, container, 50, SpringLayout.SOUTH, btnNext);
            layout.putConstraint(SpringLayout.EAST, container, 100, SpringLayout.EAST, list);
            this.pack();

            this.add(btnSetFilter);
            this.add(btnTakeFilter);
            this.add(btnNext);
            this.add(btnCancel);
            this.add(list);
            this.setVisible(true);
        }
    }

    public void filterFix(Category category,String filter,String[] values){
        if(filter.equals(values[0])){
            category.setFilterBrand(null);
        }

        else if(filter.equals(values[1])){
            category.setFilterCode(-1);
        }

        else if(filter.equals(values[2])){
            category.setFilterExist(false);
        }

        else if(filter.equals(values[3])){
            category.setFilterPrice(0, 0);
        }

        if(categoryChoice==1){
            DigitalCategory d=DigitalCategory.getCategory();

            if(filter.equals(values[4])){
                d.setFilterMemoryCapacity(0);
            }

            if(productChoice==1){
                if(filter.equals(values[5])){
                    d.setFilterCameraQuality(null);
                }

                else if(filter.equals(values[6])){
                    d.setFilterDiscount(false);
                }
            }

            else if(productChoice==2){
                if(filter.equals(values[5])){
                    d.setFilterRam(0);
                }

                else if(filter.equals(values[6])){
                    d.setFilterCpuModel(null);
                }

                else if(filter.equals(values[7])){
                    d.setFilterDiscount(false);
                }
            }
        }

        else if(categoryChoice==2){
            ClothingCategory c=ClothingCategory.getCategory();

            if(filter.equals(values[4])){
                c.setFilterCountry(null);
            }

            else if(filter.equals(values[5])){
                c.setFilterType(null);
            }

            else if(filter.equals(values[6])){
                c.setFilterDiscount(false);
            }
        }

        else if(categoryChoice==3){
            HomeCategory h=HomeCategory.getCategory();

            if(filter.equals(values[4])){
                h.setFilterWarranty(false);
            }

            if(productChoice==1) {
                if (filter.equals(values[5])) {
                    h.setFilterTvQuality(null);
                }
            }

            else if(productChoice==2 || productChoice==3){
                if(filter.equals(values[5])) {
                    h.setFilterTemp(false);
                }
            }
        }

        else if(categoryChoice==4){
            FoodCategory f=FoodCategory.getCategory();
            if(filter.equals(values[4])){
                f.setFilterDiscount(false);
            }
        }
    }

    public void endBuy(Category category){
        category.setFilterBrand(null);
        category.setFilterCode(-1);
        category.setFilterExist(false);
        category.setFilterPrice(0, 0);
        category.setFilterDiscount(false);

        if(category instanceof DigitalCategory){
            DigitalCategory d=DigitalCategory.getCategory();

            d.setFilterMemoryCapacity(0);
            d.setFilterCameraQuality(null);
            d.setFilterRam(0);
            d.setFilterCpuModel(null);
        }

        else if(category instanceof ClothingCategory){
            ClothingCategory c=ClothingCategory.getCategory();

            c.setFilterCountry(null);
            c.setFilterType(null);
        }

        else if(category instanceof HomeCategory){
            HomeCategory h=HomeCategory.getCategory();

            h.setFilterWarranty(false);
            h.setFilterTvQuality(null);
            h.setFilterTemp(false);
        }
    }

    void showProduct(){
        int size;

        if(products.size()-counter>=10){
            size=10;
        }
        else{
            size=products.size()-counter;
        }

        for(int i=counter;i<size+counter;i++){
            hold.addElement((i+1)+"-"+products.get(i).toString());
            hold.addElement("\n");
        }
    }

    void setProducts(){
        if(categoryChoice==1){
            DigitalCategory d=DigitalCategory.getCategory();

            if(productChoice==1){
                d.showMobileFilterly(products);
            }
            else if(productChoice==2){
                d.showLaptopFilterly(products);
            }
        }

        else if(categoryChoice==2){
            ClothingCategory c=ClothingCategory.getCategory();

            if(productChoice==1){
                c.showClothesFilterly(products);
            }
            else if(productChoice==2){
                c.showShoesFilterly(products);
            }
        }

        else if(categoryChoice==3){
            HomeCategory h=HomeCategory.getCategory();

            if(productChoice==1){
                h.showTVFilterly(products);
            }
            else if(productChoice==2){
                h.showRefrigeratorFilterly(products);
            }
            else if(productChoice==3){
                h.showGasStoveFilterly(products);
            }
        }

        else if(categoryChoice==4){
            FoodCategory f=FoodCategory.getCategory();
            f.showFoodFilterly(products);
        }
    }

    void setFilter(String filter,String[] values){
        if(filter.equals(values[0])){
            String brand=JOptionPane.showInputDialog("Enter brand :");
            if(brand!=null){
                category.setFilterBrand(brand);
            }
        }

        else if(filter.equals(values[1])){
            try {
                String tempCode = JOptionPane.showInputDialog("Enter code :");
                int code=Integer.valueOf(tempCode);
                category.setFilterCode(code);
            }
            catch (NumberFormatException ex){
                JOptionPane.showMessageDialog(null,"Enter a Number");
            }
        }

        else if(filter.equals(values[2])){
            category.setFilterExist(true);
        }

        else if(filter.equals(values[3])){
            try {
                String price = JOptionPane.showInputDialog("Enter price (e.g. 1000 - 2000) :");
                if (price != null) {
                    String[] prices = price.split("-");
                    if (prices.length == 2) {
                        double startPrice=Double.valueOf(prices[0]);
                        double endPrice=Double.valueOf(prices[1]);
                        category.setFilterPrice(startPrice,endPrice);
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"Enter Prices Formatly!!");
                    }
                }
            }
            catch (NumberFormatException ex){
                JOptionPane.showMessageDialog(null,"Enter Numbers!!");
            }
        }

        if(categoryChoice==1){
            DigitalCategory digital=(DigitalCategory) category;

            if(filter.equals(values[4])){
                try {
                    String memory = JOptionPane.showInputDialog("Enter memory capacity :");
                    if (memory != null) {
                        int capacity=Integer.valueOf(memory);
                        digital.setFilterMemoryCapacity(capacity);
                    }
                }
                catch (NumberFormatException ex){
                    JOptionPane.showMessageDialog(null,"Enter Numbers!!");
                }
            }

            if(productChoice==1){
                if(filter.equals(values[5])){
                    String quality=JOptionPane.showInputDialog("Enter camera quality :");
                    if(quality!=null){
                        digital.setFilterCameraQuality(quality);
                    }
                }

                else if(filter.equals(values[6])){
                    digital.setFilterDiscount(true);
                }
            }

            else if(productChoice==2){
                if(filter.equals(values[5])){
                    try {
                        String tempRam = JOptionPane.showInputDialog("Enter ram :");
                        if (tempRam != null) {
                            int ram=Integer.valueOf(tempRam);
                            digital.setFilterRam(ram);
                        }
                    }
                    catch (NumberFormatException ex){
                        JOptionPane.showMessageDialog(null,"Enter Numbers!!");
                    }
                }

                else if(filter.equals(values[6])){
                    String model=JOptionPane.showInputDialog("Enter cpu model :");
                    if(model!=null){
                        digital.setFilterCpuModel(model);
                    }
                }

                else if(filter.equals(values[7])){
                    digital.setFilterDiscount(true);
                }
            }
        }

        else if(categoryChoice==2){
            ClothingCategory clothing=(ClothingCategory) category;

            if(filter.equals(values[4])){
                String country=JOptionPane.showInputDialog("Enter country :");
                if(country!=null){
                    clothing.setFilterCountry(country);
                }
            }

            else if(filter.equals(values[5])){
                String type=JOptionPane.showInputDialog("Enter type :");
                if(type!=null){
                    clothing.setFilterType(type);
                }
            }

            else if(filter.equals(values[6])){
                clothing.setFilterDiscount(true);
            }
        }

        else if(categoryChoice==3){
            HomeCategory home=(HomeCategory) category;

            if(filter.equals(values[4])){
                home.setFilterWarranty(true);
            }

            if(productChoice==1){
                if(filter.equals(values[5])){
                    String quality=JOptionPane.showInputDialog("Enter quality :");
                    if(quality!=null){
                        home.setFilterTvQuality(quality);
                    }
                }
            }

            else if(productChoice==2){
                if(filter.equals(values[5])){
                    home.setFilterTemp(true);
                }
            }

            else if(productChoice==3){
                if(filter.equals(values[5])){
                    home.setFilterTemp(true);
                }
            }
        }

        else if(categoryChoice==4){
            FoodCategory food=(FoodCategory) category;

            if(filter.equals(values[4])){
                food.setFilterDiscount(true);
            }
        }
    }

    String[] filters(){
        ArrayList<String> filters=new ArrayList<>();
        filters.add("Brand");
        filters.add("Product Code");
        filters.add("Exist");
        filters.add("Price");

        if(categoryChoice==1){
            filters.add("Memory capacity");

            if(productChoice==1){
                filters.add("Camera quality");
                filters.add("Discount");
            }
            else if(productChoice==2){
                filters.add("Ram");
                filters.add("Cpu model");
                filters.add("Discount");
            }
        }

        else if(categoryChoice==2){
            filters.add("Country");
            filters.add("Type");
            filters.add("Discount");
        }

        else if(categoryChoice==3){
            filters.add("Have Warranty");

            if(productChoice==1){
                filters.add("TV Quality");
            }

            else if(productChoice==2){
                filters.add("Have Freezer");
            }

            else if(productChoice==3){
                filters.add("Have Gas Oven");
            }
        }

        else if(categoryChoice==4){
            filters.add("Discount");
        }

        String[] ans=new String[filters.size()];
        for(int i=0;i<filters.size();i++){
            ans[i]=filters.get(i);
        }

        return ans;
    }
}
