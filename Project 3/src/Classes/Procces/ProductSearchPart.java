package Classes.Procces;

import java.awt.*;
import java.util.ArrayList;

import Classes.Exeptions.*;
import Classes.Products.*;
import Classes.User.*;

import javax.swing.*;

public class ProductSearchPart extends JFrame {
    Admin admin=Admin.getAdmin();

    int user;
    Customer customer;

    int counter=0;
    JButton btnNext=new JButton("Next");
    JButton btnCancel=new JButton("Cancel");

    DefaultListModel<String> hold=new DefaultListModel<>();
    JList<String> list=new JList<>(hold);

    ArrayList <Goods> products=new ArrayList<>();
    ShopingCart temp;

    public ProductSearchPart(int user,Customer customer,String text,JFrame preFrame,MainPart part){
        this.user=user;
        this.customer=customer;
        showProductsSearchly(text);

        if(products.size()==0){
            JOptionPane.showMessageDialog(null,"There is no product");
            dispose();
            preFrame.setVisible(true);
        }

        else{
            showProduct();

            this.setTitle("Search");
            this.setSize(500, 500);
            this.setDefaultCloseOperation(EXIT_ON_CLOSE);
            this.setResizable(false);
            this.setLocationRelativeTo(null);

            Container container=this.getContentPane();

            SpringLayout layout=new SpringLayout();
            this.setLayout(layout);

            layout.putConstraint(SpringLayout.WEST,list,100,SpringLayout.WEST,container);
            layout.putConstraint(SpringLayout.NORTH,list,50,SpringLayout.NORTH,container);

            layout.putConstraint(SpringLayout.WEST,btnNext,150,SpringLayout.WEST,container);
            layout.putConstraint(SpringLayout.NORTH,btnNext,20,SpringLayout.SOUTH,list);
            layout.putConstraint(SpringLayout.WEST,btnCancel,300,SpringLayout.WEST,container);
            layout.putConstraint(SpringLayout.NORTH,btnCancel,20,SpringLayout.SOUTH,list);

            btnCancel.addActionListener(e -> {
                dispose();
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

            this.add(btnNext);
            this.add(btnCancel);
            this.add(list);
            this.setVisible(true);
        }
    }

    void showProductsSearchly(String text){
        for(Goods g:admin.products){
            if(g.checkSearch(text)){
                products.add(g);
            }
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
}
