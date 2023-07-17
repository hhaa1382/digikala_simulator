package Classes.Procces;

import Classes.Products.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainPart extends JFrame {
    Goods temp;

    JButton btnRegister = new JButton("Register");
    JButton btnLogin = new JButton("Login");
    JButton btnProduct=new JButton("Products");
    JButton btnExit=new JButton("Exit");

    Image icon=Toolkit.getDefaultToolkit().getImage("icon.jpg");
    JLabel lblHello=new JLabel("Welcome to Digi Kala");

    public MainPart(){
        MainPart tempMain=this;

        this.setTitle("Home");
        this.setSize(500,500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        this.setLayout(null);
        this.setIconImage(icon);

        lblHello.setBounds(190,30,300,50);
        btnRegister.setBounds(350,150,100,30);
        btnLogin.setBounds(350,200,100,30);
        btnProduct.setBounds(50,160,200,60);
        btnExit.setBounds(200,400,100,30);

        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);

                Register registerPart=new Register(tempMain,temp, tempMain);
            }
        });

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);

                Login login=new Login(tempMain,temp,tempMain);
            }
        });

        btnProduct.addActionListener(e -> {
            setVisible(false);
            AllProductPart all=new AllProductPart(0,null,tempMain,tempMain);
        });

        btnExit.addActionListener(e -> {
            JOptionPane.showMessageDialog(null,"Good Bye");
            System.exit(0);
        });

        this.add(lblHello);
        this.add(btnRegister);
        this.add(btnLogin);
        this.add(btnProduct);
        this.add(btnExit);
        this.setVisible(true);
    }
}
