package Classes.Procces;

import java.awt.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;

import Classes.Products.*;
import Classes.User.*;

import javax.swing.*;

public class AllProductPart extends JFrame {
    Admin products=Admin.getAdmin();
    int user;
    Customer customer;
    ShopingCart temp;

    int counter=0;
    JButton btnNext=new JButton("Next");
    JButton btnCancel=new JButton("Cancel");
    JButton btnSearch=new JButton("Search");
    JTextField txtSearch=new JTextField(15);
    DefaultListModel<String> hold=new DefaultListModel<>();
    JList<String> list=new JList<>(hold);

    MenuBar menuBar=new MenuBar();
    Menu menu=new Menu("Menu");
    MenuItem itemCategories=new MenuItem("Categories");
    MenuItem itemAuction=new MenuItem("Auction");

    public AllProductPart(int user,Customer customer,JFrame preFrame,MainPart part){
        this.user=user;
        this.customer=customer;

        ArrayList <Goods> sorted=new ArrayList<>();
        ProccesOptions.sortProducts(sorted, products.products);

        if(sorted.size()==0){
            JOptionPane.showMessageDialog(null,"There is no product !!");
            dispose();
            preFrame.setVisible(true);
        }

        else{
            showProduct(sorted);

            this.setTitle("Products");
            this.setSize(500,500);
            this.setDefaultCloseOperation(EXIT_ON_CLOSE);
            this.setResizable(false);
            this.setLocationRelativeTo(null);

            menu.add(itemCategories);
            menu.add(itemAuction);
            menuBar.add(menu);
            this.setMenuBar(menuBar);

            Container container=this.getContentPane();

            SpringLayout layout=new SpringLayout();
            this.setLayout(layout);

            layout.putConstraint(SpringLayout.WEST,txtSearch,100,SpringLayout.WEST,container);
            layout.putConstraint(SpringLayout.NORTH,list,30,SpringLayout.NORTH,container);
            layout.putConstraint(SpringLayout.WEST,btnSearch,10,SpringLayout.EAST,txtSearch);
            layout.putConstraint(SpringLayout.NORTH,list,30,SpringLayout.NORTH,container);

            layout.putConstraint(SpringLayout.WEST,list,100,SpringLayout.WEST,container);
            layout.putConstraint(SpringLayout.NORTH,list,40,SpringLayout.NORTH,container);

            layout.putConstraint(SpringLayout.WEST,btnNext,150,SpringLayout.WEST,container);
            layout.putConstraint(SpringLayout.NORTH,btnNext,20,SpringLayout.SOUTH,list);
            layout.putConstraint(SpringLayout.WEST,btnCancel,300,SpringLayout.WEST,container);
            layout.putConstraint(SpringLayout.NORTH,btnCancel,20,SpringLayout.SOUTH,list);

            itemCategories.addActionListener(e -> {
                setVisible(false);
                ProductCtegoryPart categoryPart=new ProductCtegoryPart(user,customer,preFrame,part);
            });

            itemAuction.addActionListener(e -> {
                LocalDate now=LocalDate.now();
                if(now.getDayOfWeek().equals(DayOfWeek.FRIDAY)) {
                    setVisible(false);
                    Auction auction=new Auction(user,customer,preFrame,part);
                }
                else{
                    JOptionPane.showMessageDialog(null,"Just Fridays");
                }
            });

            btnSearch.addActionListener(e -> {
                String text=txtSearch.getText();
                txtSearch.setText("");
                setVisible(false);
                ProductSearchPart productSearchPart=new ProductSearchPart(user,customer,text,this,part);
            });

            btnCancel.addActionListener(e -> {
                dispose();
                preFrame.setVisible(true);
            });

            btnNext.addActionListener(e -> {
                if(sorted.size()-counter>10) {
                    counter+=10;
                    hold.clear();
                    showProduct(sorted);
                }
            });

            list.addListSelectionListener(e -> {
                Admin admin=Admin.getAdmin();
                int index=list.getSelectedIndex();
                if(index % 2 == 0){
                    if(!e.getValueIsAdjusting()) {
                        index/=2;

                        if(user==0){
                            dispose();
                            Products product = new Products(null, preFrame , admin.products.get(counter+(index)), false,part);
                        }

                        else if(user==1) {
                            Products product = new Products(null, this , admin.products.get(counter+(index)), true,null);
                        }

                        else if(user==3){
                            Products product = new Products(customer, this , admin.products.get(counter+(index)), true,null);
                        }
                    }
                }
            });

            layout.putConstraint(SpringLayout.SOUTH,container,50,SpringLayout.SOUTH,btnNext);
            layout.putConstraint(SpringLayout.EAST,container,100,SpringLayout.EAST,list);
            this.pack();

            this.add(btnSearch);
            this.add(txtSearch);
            this.add(btnNext);
            this.add(btnCancel);
            this.add(list);
            this.setVisible(true);
        }
    }

    void showProduct(ArrayList <Goods> goods){
        int size;

        if(goods.size()-counter>=10){
            size=10;
        }
        else{
            size=goods.size()-counter;
        }

        for(int i=counter;i<size+counter;i++){
            hold.addElement((i+1)+"-"+goods.get(i).toString());
            hold.addElement("\n");
        }
    }
}