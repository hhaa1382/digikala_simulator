package Classes.Procces;

import java.awt.*;
import java.util.ArrayList;

import Classes.Products.*;
import Classes.User.*;

import javax.swing.*;

public class Auction extends JFrame {
    Admin product=Admin.getAdmin();

    int user;
    Customer customer;

    int counter=0;
    JButton btnNext=new JButton("Next");
    JButton btnCancel=new JButton("Cancel");

    DefaultListModel<String> hold=new DefaultListModel<>();
    JList<String> list=new JList<>(hold);

    ArrayList<Goods> products=new ArrayList<>();

    public Auction(int user,Customer customer,JFrame preFrame,MainPart part){
        this.user=user;
        this.customer=customer;

        setProducts();

        this.setTitle("Filter");
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

    void setProducts(){
        for(Goods g:product.products){
            if(g instanceof Clothing){
                Clothing c=(Clothing) g;
                if(c.getAuctionCheck()){
                    products.add(g);
                }
            }

            else if(g instanceof Food){
                Food f=(Food) g;
                if(f.getAuctionCheck()){
                    products.add(g);
                }
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
