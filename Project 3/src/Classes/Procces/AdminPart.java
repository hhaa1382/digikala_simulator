package Classes.Procces;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.regex.Pattern;

import Classes.Data.WriteInfo;
import Classes.Exeptions.*;
import Classes.User.*;

import javax.swing.*;

public class AdminPart extends JFrame{
    MenuBar tempMenu=new MenuBar();
    Menu menu=new Menu("Panel");
    Menu subMenu=new Menu("Personal information");

    MenuItem showSubItem=new MenuItem("Show");
    MenuItem changeSubItem=new MenuItem("Change");
    MenuItem logoutItem=new MenuItem("Logout");

    JLabel info=new JLabel("Hamid Mehranfar");
    JLabel MenuText=new JLabel("Menu");

    JButton btnAddSeller=new JButton("Add Seller Requests");
    JButton btnProductRequest=new JButton("Add/Remove/Change Product Request");
    JButton btnUsers=new JButton("Users List");
    JButton btnDeleteUsers=new JButton("Delete Users");
    JButton btnProducts=new JButton("Products");

    Admin admin=Admin.getAdmin();

    public AdminPart(JFrame mainPart){
        this.setTitle("Admin");
        this.setSize(500,500);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setLayout(null);

        subMenu.add(showSubItem);
        subMenu.add(changeSubItem);

        menu.add(subMenu);
        menu.add(logoutItem);
        tempMenu.add(menu);

        showSubItem.addActionListener(e ->
                JOptionPane.showMessageDialog(null,admin.personalInformation()));

        changeSubItem.addActionListener(e -> {
            ChangeInformation changePart=new ChangeInformation();
            changePart.setVisible(true);
        });

        logoutItem.addActionListener(e -> {
            dispose();
            mainPart.setVisible(true);
        });

        this.setMenuBar(tempMenu);

        info.setBounds(10,20,100,30);
        this.add(info);

        MenuText.setBounds(240,70,100,20);
        btnAddSeller.setBounds(150,110,200,20);
        btnProductRequest.setBounds(150,140,200,20);
        btnUsers.setBounds(150,170,200,20);
        btnDeleteUsers.setBounds(150,200,200,20);
        btnProducts.setBounds(150,250,200,50);

        SellerRequests sellerPart=new SellerRequests();
        btnAddSeller.addActionListener(sellerPart);

        ProductRequests productPart=new ProductRequests();
        btnProductRequest.addActionListener(productPart);

        UsersPart usersPart=new UsersPart();
        btnUsers.addActionListener(usersPart);

        btnProducts.addActionListener(e -> {
            setVisible(false);
            AllProductPart allProductPart=new AllProductPart(1,null,this,null);
        });

        btnDeleteUsers.addActionListener(e -> {
            String[] groups={"Seller","Customer"};
            String choice=(String) JOptionPane.showInputDialog(null,"Choose a group","Users",JOptionPane.PLAIN_MESSAGE,null,groups,null);

            if(choice!=null) {
                if (choice.equals("Customer")) {
                    String userName = JOptionPane.showInputDialog("Enter Username :");

                    try {
                        if (admin.deleteCustomer(userName)) {
                            JOptionPane.showMessageDialog(null, "Customer Deleted Successfully");
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "There is no person with this username!");
                        }
                    }
                    catch (SQLException | ClassNotFoundException ignore) {}
                }

                else if (choice.equals("Seller")) {
                    String userName = JOptionPane.showInputDialog("Enter Username :");

                    try {
                        if (admin.deleteSeller(userName)) {
                            JOptionPane.showMessageDialog(null, "Seller Deleted Successfully");
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "There is no person with this username!");
                        }
                    }
                    catch (SQLException | ClassNotFoundException ignore) {}
                }
            }
        });

        this.add(MenuText);
        this.add(btnAddSeller);
        this.add(btnProductRequest);
        this.add(btnUsers);
        this.add(btnDeleteUsers);
        this.add(btnProducts);
        this.setVisible(true);
    }
}

class ChangeInformation extends JFrame{
    Admin admin=Admin.getAdmin();

    JLabel lblFirstName=new JLabel("First Name :");
    JLabel lblLastName=new JLabel("Last Name :");
    JLabel lblEmail=new JLabel("Email :");
    JLabel lblPhone=new JLabel("Phone :");
    JLabel lblPassword=new JLabel("Password :");

    JTextField txtFirstName=new JTextField(20);
    JTextField txtLastName=new JTextField(20);
    JTextField txtEmail=new JTextField(20);
    JTextField txtPhone=new JTextField(20);
    JPasswordField txtPassword=new JPasswordField(20);

    JButton btnSave=new JButton("Save");
    JButton btnBack=new JButton("Back");

    ChangeInformation() {
        this.setTitle("Change Info");
        this.setSize(500, 500);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        SpringLayout sp = new SpringLayout();
        this.setLayout(sp);

        Container c = this.getContentPane();

        this.add(lblFirstName);
        this.add(lblLastName);
        this.add(lblEmail);
        this.add(lblPhone);
        this.add(lblPassword);
        this.add(txtFirstName);
        this.add(txtLastName);
        this.add(txtEmail);
        this.add(txtPhone);
        this.add(txtPassword);
        this.add(btnSave);
        this.add(btnBack);

        sp.putConstraint(SpringLayout.WEST, lblFirstName, 50, SpringLayout.WEST, c);
        sp.putConstraint(SpringLayout.WEST, lblLastName, 50, SpringLayout.WEST, c);
        sp.putConstraint(SpringLayout.WEST, lblEmail, 50, SpringLayout.WEST, c);
        sp.putConstraint(SpringLayout.WEST, lblPhone, 50, SpringLayout.WEST, c);
        sp.putConstraint(SpringLayout.WEST, lblPassword, 50, SpringLayout.WEST, c);

        sp.putConstraint(SpringLayout.NORTH, lblFirstName, 50, SpringLayout.NORTH, c);
        sp.putConstraint(SpringLayout.NORTH, lblLastName, 50, SpringLayout.NORTH, lblFirstName);
        sp.putConstraint(SpringLayout.NORTH, lblEmail, 50, SpringLayout.NORTH, lblLastName);
        sp.putConstraint(SpringLayout.NORTH, lblPhone, 50, SpringLayout.NORTH, lblEmail);
        sp.putConstraint(SpringLayout.NORTH, lblPassword, 50, SpringLayout.NORTH, lblPhone);

        sp.putConstraint(SpringLayout.WEST, txtFirstName, 200, SpringLayout.WEST, c);
        sp.putConstraint(SpringLayout.WEST, txtLastName, 200, SpringLayout.WEST, c);
        sp.putConstraint(SpringLayout.WEST, txtEmail, 200, SpringLayout.WEST, c);
        sp.putConstraint(SpringLayout.WEST, txtPhone, 200, SpringLayout.WEST, c);
        sp.putConstraint(SpringLayout.WEST, txtPassword, 200, SpringLayout.WEST, c);

        sp.putConstraint(SpringLayout.NORTH, txtFirstName, 50, SpringLayout.NORTH, c);
        sp.putConstraint(SpringLayout.NORTH, txtLastName, 50, SpringLayout.NORTH, txtFirstName);
        sp.putConstraint(SpringLayout.NORTH, txtEmail, 50, SpringLayout.NORTH, txtLastName);
        sp.putConstraint(SpringLayout.NORTH, txtPhone, 50, SpringLayout.NORTH, txtEmail);
        sp.putConstraint(SpringLayout.NORTH, txtPassword, 50, SpringLayout.NORTH, txtPhone);

        sp.putConstraint(SpringLayout.WEST, btnSave, 100, SpringLayout.WEST, c);
        sp.putConstraint(SpringLayout.WEST, btnBack, 100, SpringLayout.WEST, btnSave);
        sp.putConstraint(SpringLayout.NORTH, btnSave, 100, SpringLayout.NORTH, txtPassword);
        sp.putConstraint(SpringLayout.NORTH, btnBack, 100, SpringLayout.NORTH, txtPassword);

        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (!txtFirstName.getText().isBlank()) {
                        admin.setFirstName(txtFirstName.getText());
                    }
                    else if (!txtLastName.getText().isBlank()) {
                        admin.setLastName(txtLastName.getText());
                    }
                    else if (!txtEmail.getText().isBlank()) {
                        checkEmail(txtEmail.getText());
                        admin.setEmail(txtEmail.getText());
                    }
                    else if (!txtPhone.getText().isBlank()) {
                        checkPhoneNumber(txtPhone.getText());
                        admin.setPhoneNumber(txtPhone.getText());
                    }
                    else if (txtPassword.getPassword().length > 0) {
                        admin.setPassWord(txtPassword.getPassword());
                    }

                    JOptionPane.showMessageDialog(null, "Information Changed Successfully");
                    WriteInfo.updateAdmin();
                }
                catch (InvalidEmail | InvalidPhoneNumber ex){
                    JOptionPane.showMessageDialog(null,ex.getMessage());
                }

                catch (SQLException | ClassNotFoundException ignore) {}
            }
        });

        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        this.setVisible(true);
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

class SellerRequests implements ActionListener{
    Admin admin=Admin.getAdmin();
    String[] values={"Accept","Delete","Next","Cancel"};
    String choice;

    @Override
    public void actionPerformed(ActionEvent e){
        String text=admin.checkRequests();

        while (text!=null){
            choice=(String) JOptionPane.showInputDialog(null,text,"Request",JOptionPane.YES_NO_OPTION,null,values,null);

            if(choice!=null) {
                if (choice.equals(values[0])) {
                    try {
                        admin.acceptRequest();
                    }

                    catch (SQLException | ClassNotFoundException ignore) {}

                    JOptionPane.showMessageDialog(null, "Seller Added Successfully");
                }

                else if (choice.equals(values[1])) {
                    try {
                        admin.deleteRequest();
                    }
                    catch (SQLException | ClassNotFoundException ex) {}

                    JOptionPane.showMessageDialog(null, "Request Deleted Successfully");
                }

                else if (choice.equals(values[3])) {
                    admin.setCounter(-1);
                    break;
                }

                text = admin.checkRequests();
            }
        }
    }
}

class ProductRequests implements ActionListener{
    Admin admin=Admin.getAdmin();
    String[] values={"Add product requests","Change product request","Delete product requests","Add product opinion request"};
    String[] choiceValues={"Accept","Delete","Next","Cancel"};
    String choice;

    @Override
    public void actionPerformed(ActionEvent e) {
        choice=(String) JOptionPane.showInputDialog(null,"Choose Request","Product Requests",JOptionPane.PLAIN_MESSAGE,null,values,null);

        if(choice!=null){
            if(choice.equals(values[0])){
                String text=admin.checkAddProductRequests();
                String addChoice;

                while (text!=null){
                    addChoice=(String) JOptionPane.showInputDialog(null,text,"Request",
                            JOptionPane.YES_NO_OPTION,null,choiceValues,null);

                    if(addChoice!=null) {
                        try {
                            if (addChoice.equals(choiceValues[0])) {
                                admin.AddProduct();
                                JOptionPane.showMessageDialog(null, "Product Added Successfully");
                            }
                            else if (addChoice.equals(choiceValues[1])) {
                                admin.deleteAddRequest();
                                JOptionPane.showMessageDialog(null, "\"Request Deleted Successfully");
                            }
                            else if (addChoice.equals(choiceValues[3])) {
                                admin.setCounter(-1);
                                break;
                            }

                            text = admin.checkAddProductRequests();
                        }
                        catch (SQLException | ClassNotFoundException ignore) {}
                    }
                }
            }

            else if(choice.equals(values[1])){
                String text=admin.checkChangeProductRequests();
                String addChoice;

                while (text!=null){
                    addChoice=(String) JOptionPane.showInputDialog(null,text,"Request",JOptionPane.YES_NO_OPTION,null,choiceValues,null);

                    if(addChoice!=null) {
                        try {
                            if (addChoice.equals(choiceValues[0])) {
                                admin.ChangeProduct();

                                JOptionPane.showMessageDialog(null, "Product Changed Successfully");
                            }
                            else if (addChoice.equals(choiceValues[1])) {
                                admin.deleteChangeRequest();
                                JOptionPane.showMessageDialog(null, "\"Request Deleted Successfully");
                            }
                            else if (addChoice.equals(choiceValues[3])) {
                                admin.setCounter(-1);
                                break;
                            }

                            text = admin.checkChangeProductRequests();
                        }
                        catch (SQLException | ClassNotFoundException ignore) {}
                    }
                }
            }

            else if(choice.equals(values[2])){
                String text=admin.checkDeleteProductRequests();
                String addChoice;

                while (text!=null){
                    addChoice=(String) JOptionPane.showInputDialog(null,text,"Request",JOptionPane.YES_NO_OPTION,null,choiceValues,null);

                    if(addChoice!=null){
                        try {
                            if (addChoice.equals(choiceValues[0])) {
                                admin.deleteProduct();
                                JOptionPane.showMessageDialog(null, "Product Deleted Successfully");
                            }
                            else if (addChoice.equals(choiceValues[1])) {
                                admin.deleteDeleteRequest();
                                JOptionPane.showMessageDialog(null, "\"Request Deleted Successfully");
                            }
                            else if (addChoice.equals(choiceValues[3])) {
                                admin.setCounter(-1);
                                break;
                            }

                            text = admin.checkDeleteProductRequests();
                        }
                        catch (SQLException | ClassNotFoundException ignore) {}
                    }
                }
            }

            else if(choice.equals(values[3])){
                String text=admin.checkOpinion();
                String addChoice;

                while (text!=null){
                    addChoice=(String) JOptionPane.showInputDialog(null,text,"Request",JOptionPane.YES_NO_OPTION,null,choiceValues,null);

                    if(addChoice!=null){
                        try {
                            if (addChoice.equals(choiceValues[0])) {
                                admin.addOpinion();
                                JOptionPane.showMessageDialog(null, "Opinion Added Successfully");
                            }
                            else if (addChoice.equals(choiceValues[1])) {
                                admin.deleteOpinion();

                                JOptionPane.showMessageDialog(null, "\"Request Deleted Successfully");
                            }
                            else if (addChoice.equals(choiceValues[3])) {
                                admin.setCounter(-1);
                                break;
                            }

                            text = admin.checkOpinion();
                        }
                        catch (SQLException | ClassNotFoundException ignore) {}
                    }
                }
            }
        }
    }
}

class UsersPart implements ActionListener{
    Admin admin=Admin.getAdmin();
    String[] groups={"Seller","Customer"};
    String[] options={"Next","Cancel"};
    String choice;

    @Override
    public void actionPerformed(ActionEvent e) {
        choice=(String) JOptionPane.showInputDialog(null,"Choose a group","Request",JOptionPane.PLAIN_MESSAGE,null,groups,null);

        if(choice!=null) {
            if (choice.equals(groups[1])) {
                String text = admin.showCustomerList();
                String btnChoice;

                while (text != null) {
                    btnChoice = (String) JOptionPane.showInputDialog(null, text, "Customer", JOptionPane.YES_NO_OPTION, null, options, null);

                    if (btnChoice != null) {
                        if (btnChoice.equals(options[1])) {
                            admin.setCounter(-1);
                            break;
                        }

                        text = admin.showCustomerList();
                    }
                }
            }
            else if (choice.equals(groups[0])) {
                String text = admin.showSellerList();
                String btnChoice;

                while (text != null) {
                    btnChoice = (String) JOptionPane.showInputDialog(null, text, "Customer", JOptionPane.YES_NO_OPTION, null, options, null);

                    if (btnChoice != null) {
                        if (btnChoice.equals(options[1])) {
                            admin.setCounter(-1);
                            break;
                        }

                        text = admin.showSellerList();
                    }
                }
            }
        }
    }
}