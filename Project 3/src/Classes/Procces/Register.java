package Classes.Procces;

import java.awt.*;
import java.sql.SQLException;
import java.util.regex.*;

import Classes.Data.WriteInfo;
import Classes.Products.*;
import Classes.User.*;
import Classes.Exeptions.*;

import javax.swing.*;

public class Register extends JFrame{
    Goods temp;
    Customer customer=null;
    Seller seller=null;
    String userCheck;

    JLabel lblName=new JLabel("First Name :");
    JTextField txtName=new JTextField(20);

    JLabel lblFamily=new JLabel("Last Name :");
    JTextField txtFamily=new JTextField(20);

    JLabel lblEmail=new JLabel("Email :");
    JTextField txtEmail=new JTextField(20);

    JLabel lblPhone=new JLabel("Phone :");
    JTextField txtPhone=new JTextField(20);

    JLabel lblUserName=new JLabel("Username :");
    JTextField txtUserName=new JTextField(20);

    JLabel lblPassword=new JLabel("Password :");
    JPasswordField txtPass=new JPasswordField(20);

    JLabel lblCompany=new JLabel("Company Name :");
    JTextField txtCompany=new JTextField(20);

    JButton btnSave=new JButton("Save");
    JButton btnBack=new JButton("Back");

    public Register(JFrame tempMain, Goods temp,MainPart part){
        this.temp=temp;

        String[] registerMenu={"Seller","Customer"};
        while (userCheck==null) {
            userCheck = (String) JOptionPane.showInputDialog(this, "Choose User", "Register",
                    JOptionPane.PLAIN_MESSAGE, null, registerMenu, null);
        }

        this.setTitle("Register");
        this.setSize(500, 500);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        Container container=this.getContentPane();

        SpringLayout layout=new SpringLayout();
        this.setLayout(layout);

        this.add(lblName);
        this.add(txtName);

        this.add(lblFamily);
        this.add(txtFamily);

        this.add(lblEmail);
        this.add(txtEmail);

        this.add(lblPhone);
        this.add(txtPhone);

        this.add(lblUserName);
        this.add(txtUserName);

        this.add(lblPassword);
        this.add(txtPass);

        if(userCheck.equals("Seller")){
            this.add(lblCompany);
            this.add(txtCompany);
        }

        this.add(btnSave);
        this.add(btnBack);

        layout.putConstraint(SpringLayout.WEST,lblName,50,SpringLayout.WEST,container);
        layout.putConstraint(SpringLayout.NORTH,lblName,50,SpringLayout.NORTH,container);

        layout.putConstraint(SpringLayout.WEST,txtName,200,SpringLayout.WEST,container);
        layout.putConstraint(SpringLayout.NORTH,txtName,50,SpringLayout.NORTH,container);

        layout.putConstraint(SpringLayout.WEST,lblFamily,50,SpringLayout.WEST,container);
        layout.putConstraint(SpringLayout.NORTH,lblFamily,50,SpringLayout.NORTH,lblName);

        layout.putConstraint(SpringLayout.WEST,txtFamily,200,SpringLayout.WEST,container);
        layout.putConstraint(SpringLayout.NORTH,txtFamily,50,SpringLayout.NORTH,txtName);

        layout.putConstraint(SpringLayout.WEST,lblEmail,50,SpringLayout.WEST,container);
        layout.putConstraint(SpringLayout.NORTH,lblEmail,50,SpringLayout.NORTH,lblFamily);

        layout.putConstraint(SpringLayout.WEST,txtEmail,200,SpringLayout.WEST,container);
        layout.putConstraint(SpringLayout.NORTH,txtEmail,50,SpringLayout.NORTH,txtFamily);

        layout.putConstraint(SpringLayout.WEST,lblPhone,50,SpringLayout.WEST,container);
        layout.putConstraint(SpringLayout.NORTH,lblPhone,50,SpringLayout.NORTH,lblEmail);

        layout.putConstraint(SpringLayout.WEST,txtPhone,200,SpringLayout.WEST,container);
        layout.putConstraint(SpringLayout.NORTH,txtPhone,50,SpringLayout.NORTH,txtEmail);

        layout.putConstraint(SpringLayout.WEST,lblUserName,50,SpringLayout.WEST,container);
        layout.putConstraint(SpringLayout.NORTH,lblUserName,50,SpringLayout.NORTH,lblPhone);

        layout.putConstraint(SpringLayout.WEST,txtUserName,200,SpringLayout.WEST,container);
        layout.putConstraint(SpringLayout.NORTH,txtUserName,50,SpringLayout.NORTH,txtPhone);

        layout.putConstraint(SpringLayout.WEST,lblPassword,50,SpringLayout.WEST,container);
        layout.putConstraint(SpringLayout.NORTH,lblPassword,50,SpringLayout.NORTH,lblUserName);

        layout.putConstraint(SpringLayout.WEST,txtPass,200,SpringLayout.WEST,container);
        layout.putConstraint(SpringLayout.NORTH,txtPass,50,SpringLayout.NORTH,txtUserName);

        if(userCheck.equals("Seller")){
            layout.putConstraint(SpringLayout.WEST,lblCompany,50,SpringLayout.WEST,container);
            layout.putConstraint(SpringLayout.NORTH,lblCompany,50,SpringLayout.NORTH,lblPassword);

            layout.putConstraint(SpringLayout.WEST,txtCompany,200,SpringLayout.WEST,container);
            layout.putConstraint(SpringLayout.NORTH,txtCompany,50,SpringLayout.NORTH,txtPass);

            layout.putConstraint(SpringLayout.WEST,btnSave,200,SpringLayout.WEST,container);
            layout.putConstraint(SpringLayout.NORTH,btnSave,50,SpringLayout.NORTH,lblCompany);

            layout.putConstraint(SpringLayout.WEST,btnBack,300,SpringLayout.WEST,container);
            layout.putConstraint(SpringLayout.NORTH,btnBack,50,SpringLayout.NORTH,txtCompany);
        }

        else {
            layout.putConstraint(SpringLayout.WEST, btnSave, 200, SpringLayout.WEST, container);
            layout.putConstraint(SpringLayout.NORTH, btnSave, 50, SpringLayout.NORTH, lblPassword);

            layout.putConstraint(SpringLayout.WEST, btnBack, 300, SpringLayout.WEST, container);
            layout.putConstraint(SpringLayout.NORTH, btnBack, 50, SpringLayout.NORTH, txtPass);
        }

        btnBack.addActionListener(e -> {
            dispose();
            tempMain.setVisible(true);
        });

        btnSave.addActionListener(e -> {
            try{
                checkText();
                if(userCheck.equals("Customer")) {
                    customer = new Customer(txtName.getText(), txtFamily.getText(), txtEmail.getText()
                    , txtPhone.getText(), txtUserName.getText(), txtPass.getPassword());

                    WriteInfo.saveCustomer(customer);

                    JOptionPane.showMessageDialog(null,"Customer added successfully");

                    if(temp!=null){
                        String hold=customer.addShoppingCart(temp);
                        JOptionPane.showMessageDialog(null,hold);
                    }
                    dispose();
                    part.temp=null;
                    CustomerPart customerPart=new CustomerPart(customer,tempMain);
                }

                else if(userCheck.equals("Seller")){
                    seller = new Seller(txtName.getText(), txtFamily.getText(), txtEmail.getText()
                    , txtPhone.getText(), txtUserName.getText(), txtPass.getPassword(),txtCompany.getText());

                    WriteInfo.saveSellerRequest(seller);
                    Admin.getAdmin().setSellerRequest(seller);

                    JOptionPane.showMessageDialog(null,"Request added successfully");
                    dispose();
                    part.temp=null;
                    tempMain.setVisible(true);
                }
            }
            catch (InvalidEmail | InvalidPhoneNumber ex){
                JOptionPane.showMessageDialog(null,ex.getMessage());
            }

            catch (InvalidInput ex){
                JOptionPane.showMessageDialog(null,ex.getMessage());
            }
            catch (SQLException | ClassNotFoundException ex) {}
        });

        this.setVisible(true);
    }

    void checkText(){
        if(txtName.getText().isBlank()){
            throw new InvalidInput("Enter first name !");
        }

        if(txtFamily.getText().isBlank()){
            throw new InvalidInput("Enter last name !");
        }

        if(userCheck.equals("Seller") && checkSellerUsername(txtUserName.getText())){
            throw new InvalidInput("Username has used !");
        }

        if(userCheck.equals("Customer") && checkCustomerUsername(txtUserName.getText())){
            throw new InvalidInput("Username has used !");
        }

        if(txtPass.getPassword().length<8){
            throw new InvalidInput("Password is less than 8 character !");
        }

        if(userCheck.equals("Seller") && txtCompany.getText().isBlank()){
            throw new InvalidInput("Enter company name !");
        }

        checkEmail(txtEmail.getText());
        checkPhoneNumber(txtPhone.getText());
    }

    boolean checkCustomerUsername(String userName){
        Admin admin=Admin.getAdmin();

        for(Customer i:admin.customers){
            if(i.getUserName().equals(userName)){
                return true;
            }
        }
        return false;
    }

    boolean checkSellerUsername(String userName){
        Admin admin=Admin.getAdmin();

        for(Seller i:admin.sellers){
            if(i.getUserName().equals(userName)){
                return true;
            }
        }
        return false;
    }

    void checkEmail(String email){
        if(!Pattern.matches("^(.+)@(.+)$", email)){
            throw new InvalidEmail();
        }
    }

    void checkPhoneNumber(String phoneNumber){
        if(!Pattern.matches("[0-9]{9}", phoneNumber)){
            throw new InvalidPhoneNumber();
        }
    }
}
