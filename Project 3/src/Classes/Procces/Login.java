package Classes.Procces;

import java.awt.*;

import Classes.Products.Goods;
import Classes.User.*;

import javax.swing.*;

public class Login extends JFrame{
    Goods temp;
    Customer customer=null;
    Seller seller=null;
    String userCheck;

    JLabel lblUsername=new JLabel("Username :");
    JTextField txtUsername=new JTextField(20);

    JLabel lblPassword=new JLabel("Password :");
    JTextField txtPassword=new JTextField(20);

    JButton btnLogin=new JButton("Login");
    JButton btnBack=new JButton("Back");

    public Login(JFrame main, Goods temp,MainPart part){
        this.temp=temp;

        String[] loginMenu={"Manager","Seller","Customer"};
        while(userCheck==null) {
            userCheck = (String) JOptionPane.showInputDialog(this, "Choose User", "Login",
                    JOptionPane.PLAIN_MESSAGE, null, loginMenu, null);
        }

        this.setTitle("Login");
        this.setSize(500, 500);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        Container container=this.getContentPane();

        SpringLayout layout=new SpringLayout();
        this.setLayout(layout);

        this.add(lblUsername);
        this.add(txtUsername);

        this.add(lblPassword);
        this.add(txtPassword);

        this.add(btnLogin);
        this.add(btnBack);

        layout.putConstraint(SpringLayout.WEST,lblUsername,50,SpringLayout.WEST,container);
        layout.putConstraint(SpringLayout.NORTH,lblUsername,100,SpringLayout.NORTH,container);

        layout.putConstraint(SpringLayout.WEST,txtUsername,200,SpringLayout.WEST,container);
        layout.putConstraint(SpringLayout.NORTH,txtUsername,100,SpringLayout.NORTH,container);

        layout.putConstraint(SpringLayout.WEST,lblPassword,50,SpringLayout.WEST,container);
        layout.putConstraint(SpringLayout.NORTH,lblPassword,100,SpringLayout.NORTH,lblUsername);

        layout.putConstraint(SpringLayout.WEST,txtPassword,200,SpringLayout.WEST,container);
        layout.putConstraint(SpringLayout.NORTH,txtPassword,100,SpringLayout.NORTH,txtUsername);

        layout.putConstraint(SpringLayout.WEST,btnLogin,50,SpringLayout.WEST,container);
        layout.putConstraint(SpringLayout.NORTH,btnLogin,100,SpringLayout.NORTH,lblPassword);

        layout.putConstraint(SpringLayout.WEST,btnBack,150,SpringLayout.WEST,container);
        layout.putConstraint(SpringLayout.NORTH,btnBack,100,SpringLayout.NORTH,txtPassword);

        btnBack.addActionListener(e -> {
            dispose();
            main.setVisible(true);
        });

        btnLogin.addActionListener(e -> {
            if(userCheck.equals("Manager")){
                if(loginAdmin(txtUsername.getText(),txtPassword.getText())){
                    dispose();
                    part.temp=null;
                    AdminPart adminPart=new AdminPart(main);
                }
            }

            else if(userCheck.equals("Seller")){
                Admin check=Admin.getAdmin();
                int index=loginSellers(txtUsername.getText(),txtPassword.getText());

                if(index==-1){
                    JOptionPane.showMessageDialog(null,"Username or Password is false!");
                }
                else{
                    seller=check.sellers.get(index);
                    dispose();
                    part.temp=null;
                    SellerPart sellerPart=new SellerPart(seller,main);
                }
            }

            else if(userCheck.equals("Customer")){
                Admin check=Admin.getAdmin();
                int index=loginCustomers(txtUsername.getText(),txtPassword.getText());

                if(index==-1){
                    JOptionPane.showMessageDialog(null,"Username or Password is false!");
                }
                else{
                    customer=check.customers.get(index);

                    if(temp!=null){
                        String hold=customer.addShoppingCart(temp);
                        JOptionPane.showMessageDialog(null,hold);
                    }

                    dispose();
                    part.temp=null;
                    CustomerPart customerPart=new CustomerPart(customer,main);
                }
            }
        });

        this.setVisible(true);
    }

    boolean loginAdmin(String userName,String passWord){
        if((userName.equals("admin") || userName.equals("Admin")) && (passWord.equals("admin") || passWord.equals("Admin"))){
            return true;
        }

        return false;
    }

    int loginCustomers(String userName,String passWord){
        Admin admin=Admin.getAdmin();

        for(int i=0;i<admin.customers.size();i++){
            if(admin.customers.get(i).getUserName().equals(userName) && admin.customers.get(i).getPassWord().equals(passWord)){
                return i;
            }
        }
        return -1;
    }

    int loginSellers(String userName,String passWord){
        Admin admin=Admin.getAdmin();

        for(int i=0;i<admin.sellers.size();i++){
            if(admin.sellers.get(i).getUserName().equals(userName) && admin.sellers.get(i).getPassWord().equals(passWord)){
                return i;
            }
        }
        return -1;
    }
}
