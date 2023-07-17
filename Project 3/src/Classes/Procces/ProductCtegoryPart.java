package Classes.Procces;

import java.awt.*;

import Classes.Categories.*;
import Classes.Exeptions.*;
import Classes.Products.*;
import Classes.User.*;

import javax.swing.*;

public class ProductCtegoryPart extends JFrame {
    int user;
    Customer customer;
    Category category=null;

    ShopingCart temp;

    String[] categories={"Digital","Clothing","Home Appliance","Food"};
    String[] digital={"Mobile","Laptop"};
    String[] clothing={"Clothes","Shoes"};
    String[] homePro={"Television","Refrigerator","Gas Stove"};

    String categoryChoice;
    String productChoice;
    int productChoiceIndex;
    int categoryChoiceIndex;
    int counter=0;

    JButton btnNext=new JButton("Next");
    JButton btnCancel=new JButton("Cancel");
    DefaultListModel<String> hold=new DefaultListModel<>();
    JList<String> list=new JList<>(hold);
    JButton btnFilter=new JButton("Filter");

    public ProductCtegoryPart(int user,Customer customer,JFrame preFrame,MainPart part){
        this.user=user;
        this.customer=customer;

        categoryChoice=(String)JOptionPane.showInputDialog(null,"Choose a Category","Categories",JOptionPane.PLAIN_MESSAGE,null,categories,null);

        if(categoryChoice==null){
            dispose();
            preFrame.setVisible(true);
        }

        else {
            if(categoryChoice.equals(categories[0])) {
                categoryChoiceIndex=1;
                productChoice = (String) JOptionPane.showInputDialog(null, "Choose a Product", categoryChoice, JOptionPane.PLAIN_MESSAGE, null, digital, null);
                category=DigitalCategory.getCategory();
            }

            else if(categoryChoice.equals(categories[1])) {
                categoryChoiceIndex=2;
                productChoice = (String) JOptionPane.showInputDialog(null, "Choose a Product", categoryChoice, JOptionPane.PLAIN_MESSAGE, null, clothing, null);
                category=ClothingCategory.getCategory();
            }

            else if(categoryChoice.equals(categories[2])) {
                categoryChoiceIndex=3;
                productChoice = (String) JOptionPane.showInputDialog(null, "Choose a Product", categoryChoice, JOptionPane.PLAIN_MESSAGE, null, homePro, null);
                category=HomeCategory.getCategory();
            }

            else if(categoryChoice.equals(categories[3])) {
                categoryChoiceIndex=4;
                productChoice = "Food";
                category=FoodCategory.getCategory();
            }

            if(productChoice==null){
                dispose();
                preFrame.setVisible(true);
            }

            else {
                int size=checkProducts();

                if(size>0) {
                    category.sort();
                    showProductList();

                    this.setTitle(productChoice);
                    this.setSize(500, 500);
                    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
                    this.setResizable(false);
                    this.setLocationRelativeTo(null);

                    Container container = this.getContentPane();

                    SpringLayout layout = new SpringLayout();
                    this.setLayout(layout);

                    layout.putConstraint(SpringLayout.NORTH, btnFilter, 10, SpringLayout.NORTH, container);
                    layout.putConstraint(SpringLayout.WEST, btnFilter, 10, SpringLayout.WEST, container);

                    layout.putConstraint(SpringLayout.WEST, list, 100, SpringLayout.WEST, container);
                    layout.putConstraint(SpringLayout.NORTH, list, 30, SpringLayout.NORTH, container);

                    layout.putConstraint(SpringLayout.WEST, btnNext, 150, SpringLayout.WEST, container);
                    layout.putConstraint(SpringLayout.NORTH, btnNext, 20, SpringLayout.SOUTH, list);
                    layout.putConstraint(SpringLayout.WEST, btnCancel, 300, SpringLayout.WEST, container);
                    layout.putConstraint(SpringLayout.NORTH, btnCancel, 20, SpringLayout.SOUTH, list);

                    btnCancel.addActionListener(e -> {
                        dispose();
                        preFrame.setVisible(true);
                    });

                    btnNext.addActionListener(e -> {
                        if (checkNext()) {
                            counter += 10;
                            hold.clear();
                            showProductList();
                        }
                    });

                    btnFilter.addActionListener(e -> {
                        setVisible(false);
                        JOptionPane.showMessageDialog(null, "Product Choice : " + productChoiceIndex + "\nCategory Choice : " + categoryChoiceIndex);
                        FilterPart filter = new FilterPart(user, customer, category, productChoiceIndex,
                                categoryChoiceIndex, this, part);
                    });

                    list.addListSelectionListener(e -> {
                        Admin admin = Admin.getAdmin();
                        int index = list.getSelectedIndex();
                        if (index % 2 == 0) {
                            if (!e.getValueIsAdjusting()) {
                                index /= 2;
                                if (user == 0) {
                                    Products product = new Products(null, preFrame, showProduct(counter + (index)), false, part);
                                } else if (user == 1) {
                                    Products product = new Products(null, preFrame, showProduct(counter + (index)), true, null);
                                } else if (user == 3) {
                                    Products product = new Products(customer, preFrame, showProduct(counter + (index)), true, null);
                                }
                            }
                        }
                    });

                    layout.putConstraint(SpringLayout.SOUTH, container, 50, SpringLayout.SOUTH, btnNext);
                    layout.putConstraint(SpringLayout.EAST, container, 100, SpringLayout.EAST, list);
                    this.pack();

                    this.add(btnNext);
                    this.add(btnCancel);
                    this.add(list);
                    this.add(btnFilter);
                    this.setVisible(true);
                }
                else{
                    JOptionPane.showMessageDialog(null,"There is no product");
                    preFrame.setVisible(true);
                }
            }
        }
    }

    int checkProducts(){
        if(categoryChoice.equals(categories[0])){
            DigitalCategory category=DigitalCategory.getCategory();

            if(productChoice.equals(digital[0])){
                return category.mobileSize();
            }
            else{
                return category.laptopSize();
            }
        }

        else if(categoryChoice.equals(categories[1])){
            ClothingCategory category=ClothingCategory.getCategory();

            if(productChoice.equals(clothing[0])){
                category.clotheSize();
            }
            else{
                category.shoesSize();
            }
        }

        else if(categoryChoice.equals(categories[2])){
            HomeCategory category=HomeCategory.getCategory();

            if(productChoice.equals(homePro[0])){
                category.tvSize();
            }
            else if(productChoice.equals(homePro[1])){
                category.refrigeratorSize();
            }
            else{
                category.gasStoveSize();
            }
        }

        else if(categoryChoice.equals(categories[3])){
            FoodCategory category=FoodCategory.getCategory();
            return category.foodSize();
        }

        return 0;
    }

    boolean checkNext(){
        boolean check=false;

        if(categoryChoice.equals(categories[0])){
            DigitalCategory category=DigitalCategory.getCategory();

            if(productChoice.equals(digital[0])){
                if(category.mobileSize()-counter>10){
                    check=true;
                }
            }
            else{
                if(category.laptopSize()-counter>10){
                    check=true;
                }
            }
        }

        else if(categoryChoice.equals(categories[1])){
            ClothingCategory category=ClothingCategory.getCategory();

            if(productChoice.equals(clothing[0])){
                if(category.clotheSize()-counter>10){
                    check=true;
                }
            }
            else{
                if(category.shoesSize()-counter>10){
                    check=true;
                }
            }
        }

        else if(categoryChoice.equals(categories[2])){
            HomeCategory category=HomeCategory.getCategory();

            if(productChoice.equals(homePro[0])){
                if(category.tvSize()-counter>10){
                    check=true;
                }
            }
            else if(productChoice.equals(homePro[1])){
                if(category.refrigeratorSize()-counter>10){
                    check=true;
                }
            }
            else{
                if(category.gasStoveSize()-counter>10){
                    check=true;
                }
            }
        }

        else if(categoryChoice.equals(categories[3])){
            FoodCategory category=FoodCategory.getCategory();

            if(category.foodSize()-counter>10){
                check=true;
            }
        }

        return check;
    }

    Goods showProduct(int index){
        Goods good=null;

        if(categoryChoice.equals(categories[0])){
            DigitalCategory category=DigitalCategory.getCategory();

            if(productChoice.equals(digital[0])){
                good=category.getMobile(index);
            }
            else{
                good=category.getLaptop(index);
            }
        }

        else if(categoryChoice.equals(categories[1])){
            ClothingCategory category=ClothingCategory.getCategory();

            if(productChoice.equals(clothing[0])){
                good=category.getClothes(index);
            }
            else{
                good=category.getShoes(index);
            }
        }

        else if(categoryChoice.equals(categories[2])){
            HomeCategory category=HomeCategory.getCategory();

            if(productChoice.equals(homePro[0])){
                good=category.getTV(index);
            }
            else if(productChoice.equals(homePro[1])){
                good=category.getRegrigerator(index);
            }
            else{
                good=category.getGasStove(index);
            }
        }

        else if(categoryChoice.equals(categories[3])){
            FoodCategory category=FoodCategory.getCategory();
            good=category.getFood(index);
        }

        return good;
    }

    void showProductList(){
        if(categoryChoice.equals(categories[0])){
            DigitalCategory category=DigitalCategory.getCategory();

            if(productChoice.equals(digital[0])){
                productChoiceIndex=1;
                category.showMobileList(hold,counter);
            }
            else{
                productChoiceIndex=2;
                category.showLaptopList(hold,counter);
            }
        }

        else if(categoryChoice.equals(categories[1])){
            ClothingCategory category=ClothingCategory.getCategory();

            if(productChoice.equals(clothing[0])){
                productChoiceIndex=1;
                category.showClothesList(hold,counter);
            }
            else{
                productChoiceIndex=2;
                category.showShoesList(hold,counter);
            }
        }

        else if(categoryChoice.equals(categories[2])){
            HomeCategory category=HomeCategory.getCategory();

            if(productChoice.equals(homePro[0])){
                productChoiceIndex=1;
                category.showTelevisionList(hold,counter);
            }
            else if(productChoice.equals(homePro[1])){
                productChoiceIndex=2;
                category.showRefrigeratorList(hold,counter);
            }
            else{
                productChoiceIndex=3;
                category.showGasStoveList(hold,counter);
            }
        }

        else if(categoryChoice.equals(categories[3])){
            FoodCategory category=FoodCategory.getCategory();
            productChoiceIndex=1;
            category.showFoodList(hold,counter);
        }
    }
}
