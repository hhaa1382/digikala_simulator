package Classes.Procces;

import Classes.Data.WriteInfo;
import Classes.Products.*;
import Classes.User.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;

public class Products extends JFrame {
    JTextArea info=new JTextArea();
    JButton btnOpinion=new JButton("Opinions");
    JButton btnBack=new JButton("Back");
    JButton btnBuy=new JButton("Buy");
    JButton btnAddOpinion=new JButton("Add Opinion");

    Products(Customer customer, JFrame preFrame , Goods good,boolean register,MainPart part){
        info.setText(good.showInfo());

        this.setTitle(good.getClass().getSimpleName());
        this.setSize(500,500);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        Container container=this.getContentPane();

        SpringLayout layout=new SpringLayout();
        this.setLayout(layout);

        info.setEditable(false);
        layout.putConstraint(SpringLayout.WEST,info,100,SpringLayout.WEST,container);
        layout.putConstraint(SpringLayout.NORTH,info,50,SpringLayout.NORTH,container);

        this.add(info);

        if(!register){
            this.add(btnBuy);

            layout.putConstraint(SpringLayout.WEST,btnBuy,100,SpringLayout.WEST,container);
            layout.putConstraint(SpringLayout.NORTH,btnBuy,30,SpringLayout.SOUTH,info);
            layout.putConstraint(SpringLayout.WEST,btnOpinion,40,SpringLayout.EAST,btnBuy);
            layout.putConstraint(SpringLayout.NORTH,btnOpinion,30,SpringLayout.SOUTH,info);
            layout.putConstraint(SpringLayout.WEST,btnBack,40,SpringLayout.EAST,btnOpinion);
            layout.putConstraint(SpringLayout.NORTH,btnBack,30,SpringLayout.SOUTH,info);

            btnBuy.addActionListener(e -> {
                part.temp=good;
                dispose();
                preFrame.setVisible(true);
            });
        }

        else if(customer!=null){
            this.add(btnBuy);
            this.add(btnAddOpinion);

            layout.putConstraint(SpringLayout.WEST,btnBuy,50,SpringLayout.WEST,container);
            layout.putConstraint(SpringLayout.NORTH,btnBuy,30,SpringLayout.SOUTH,info);
            layout.putConstraint(SpringLayout.WEST,btnOpinion,30,SpringLayout.EAST,btnBuy);
            layout.putConstraint(SpringLayout.NORTH,btnOpinion,30,SpringLayout.SOUTH,info);
            layout.putConstraint(SpringLayout.WEST,btnAddOpinion,30,SpringLayout.EAST,btnOpinion);
            layout.putConstraint(SpringLayout.NORTH,btnAddOpinion,30,SpringLayout.SOUTH,info);
            layout.putConstraint(SpringLayout.WEST,btnBack,30,SpringLayout.EAST,btnAddOpinion);
            layout.putConstraint(SpringLayout.NORTH,btnBack,30,SpringLayout.SOUTH,info);

            btnBuy.addActionListener(e -> {
                customer.addShoppingCart(good);
                try {
                    WriteInfo.saveShoppingCart(customer,good,true);
                }
                catch (ClassNotFoundException | SQLException ignore) {}

                JOptionPane.showMessageDialog(null,"Product Added to Shopping Cart");
            });

            btnAddOpinion.addActionListener(e -> {
                Admin admin=Admin.getAdmin();
                Opinion o=new Opinion();

                String opinion=JOptionPane.showInputDialog(null,"Enter Opinion :");

                if(opinion!=null && !opinion.isBlank()){
                    o.good=good;
                    o.customer=customer;
                    o.buyCheck=customer.buyCheck(good);
                    o.text=opinion;

                    admin.setOpinionRequest(o);

                    try {
                        WriteInfo.saveRequest(good,good.getClass().getSimpleName(),"opinion",o);
                    }
                    catch (ClassNotFoundException | SQLException ignore) {}

                    o.condition="awaiting accept";

                    JOptionPane.showMessageDialog(null,"Your opinion will reveiwed by the administer");
                }
            });
        }

        else{
            layout.putConstraint(SpringLayout.WEST,btnOpinion,100,SpringLayout.WEST,container);
            layout.putConstraint(SpringLayout.NORTH,btnOpinion,30,SpringLayout.SOUTH,info);
            layout.putConstraint(SpringLayout.WEST,btnBack,30,SpringLayout.EAST,btnOpinion);
            layout.putConstraint(SpringLayout.NORTH,btnBack,30,SpringLayout.SOUTH,info);
        }

        btnOpinion.addActionListener(e -> {
            ShowOpinions show=new ShowOpinions(good);
        });

        btnBack.addActionListener(e -> {
            dispose();
        });

        this.add(btnBack);
        this.add(btnOpinion);
        this.setVisible(true);
    }
}

class ShowOpinions extends JFrame{
    JButton btnBack=new JButton("Back");
    ShowOpinions(Goods temp){
        this.setTitle("Opinions");
        this.setSize(500,500);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        SpringLayout layout=new SpringLayout();
        this.setLayout(layout);

        btnBack.addActionListener(e -> {
            dispose();
        });

        Container container=this.getContentPane();

        JScrollPane sc=new JScrollPane();
        JTextArea text=new JTextArea(temp.showOpinions());
        sc.add(text);

        layout.putConstraint(SpringLayout.WEST,sc,10,SpringLayout.WEST,container);
        layout.putConstraint(SpringLayout.NORTH,sc,30,SpringLayout.NORTH,container);

        layout.putConstraint(SpringLayout.WEST,btnBack,100,SpringLayout.WEST,container);
        layout.putConstraint(SpringLayout.NORTH,btnBack,30,SpringLayout.SOUTH,sc);

        this.add(sc);
        this.add(btnBack);
        this.setVisible(true);
    }
}
