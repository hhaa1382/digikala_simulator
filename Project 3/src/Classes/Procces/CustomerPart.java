package Classes.Procces;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import Classes.Data.WriteInfo;
import Classes.Exeptions.*;
import Classes.Products.*;
import Classes.User.*;

import javax.swing.*;

public class CustomerPart extends JFrame {
    String choice;

    Customer customer;
    MenuBar tempMenu=new MenuBar();
    Menu menu=new Menu("Panel");
    Menu subMenu=new Menu("Personal information");

    MenuItem showSubItem=new MenuItem("Show");
    MenuItem changeSubItem=new MenuItem("Change");
    MenuItem logoutItem=new MenuItem("Logout");

    JLabel info=new JLabel();
    JLabel lblMenu=new JLabel("Menu");

    JButton btnBuyHistory=new JButton("Buy Histories");
    JButton btnShoppingCart=new JButton("ShoppingCart");
    JButton btnProducts=new JButton("Products");
    JButton btnIncrease=new JButton("Increase");
    JTextField txtCredit=new JTextField(15);
    JLabel lblCredit=new JLabel();

    public CustomerPart(Customer customer,JFrame mainPart){
        this.customer=customer;

        this.setTitle("Customer");
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
                JOptionPane.showMessageDialog(null,customer.personalInformation()));

        changeSubItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChangeCustomerInformation changePart=new ChangeCustomerInformation(customer);
                changePart.setVisible(true);
            }
        });

        logoutItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                mainPart.setVisible(true);
            }
        });

        this.setMenuBar(tempMenu);

        info.setText(customer.getFirstName()+" "+customer.getLastName());
        info.setBounds(10,20,100,30);
        this.add(info);

        lblCredit.setText("Credit : "+customer.getAccountCredit());

        lblMenu.setBounds(240,100,100,20);
        lblCredit.setBounds(150,20,150,20);
        txtCredit.setBounds(250,20,80,20);
        btnIncrease.setBounds(350,20,100,20);
        btnShoppingCart.setBounds(150,140,200,20);
        btnBuyHistory.setBounds(150,170,200,20);
        btnProducts.setBounds(150,220,200,50);

        btnIncrease.addActionListener(e -> {
            if(!txtCredit.getText().isBlank()){
                try {
                    double money=Double.valueOf(txtCredit.getText());
                    double tempMoney=customer.getAccountCredit()+money;
                    customer.setAccountCredit(money);
                    lblCredit.setText("Credit : "+tempMoney);
                    JOptionPane.showMessageDialog(null,"Credit Increased");
                }
                catch (NumberFormatException ex){
                    JOptionPane.showMessageDialog(null,"Enter a number!!");
                }
                catch (SQLException | ClassNotFoundException ignore) {
                    JOptionPane.showMessageDialog(null,ignore.getMessage());
                }

                finally {
                    txtCredit.setText("");
                }
            }
        });

        btnBuyHistory.addActionListener(e -> {
            String text=customer.showBuyHistory();

            while(text!=null) {
                String[] values = {"Products info", "Delete facture", "Next", "Home"};
                choice = (String) JOptionPane.showInputDialog(null, text,
                        "Buy Histories", JOptionPane.YES_NO_OPTION, null, values, null);

                if (choice != null) {
                    if (choice.equals(values[0])) {
                        Integer[] proValues = customer.getValues();
                        Integer choose = (Integer) JOptionPane.showInputDialog(null, customer.showProductList()
                                , "Products", JOptionPane.PLAIN_MESSAGE, null, proValues, null);

                        if (choose != null) {
                            String[] options={"Put Grade","Back"};
                            int ans=JOptionPane.showOptionDialog(null,customer.showProductInfo(choose-1),
                                    "Product",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,
                                    options,null);

                            if(ans==0){
                                Integer[] gradeOp={1,2,3,4,5};
                                Integer gradeAns=(Integer)JOptionPane.showInputDialog(null,"Choose Grade :",
                                        "Grade",JOptionPane.DEFAULT_OPTION,null,gradeOp,null);

                                if(gradeAns!=null){
                                    try {
                                        customer.setGrade(gradeAns,choose-1);
                                    }
                                    catch (SQLException | ClassNotFoundException ignore) {}
                                }
                            }
                        }
                    }

                    else if (choice.equals(values[1])) {
                        try {
                            customer.deleteFacture();
                            text=customer.showBuyHistory();
                        }

                        catch (ClassNotFoundException | SQLException ignored) {}
                    }

                    else if(choice.equals(values[2])){
                        customer.increaseCounter();
                        text=customer.showBuyHistory();
                    }

                    else if (choice.equals(values[3])) {
                        customer.setCounter(0);
                        break;
                    }
                }
            }
        });

        btnShoppingCart.addActionListener(e -> {
            this.setVisible(false);
            new ShoppingCartPart(customer,this,lblCredit);
        });

        btnProducts.addActionListener(e -> {
            setVisible(false);
            new AllProductPart(3,customer,this,null);
        });

        this.add(lblMenu);
        this.add(lblCredit);
        this.add(txtCredit);
        this.add(btnIncrease);
        this.add(btnBuyHistory);
        this.add(btnShoppingCart);
        this.add(btnProducts);

        this.setVisible(true);
    }
}

class ChangeCustomerInformation extends JFrame{
    Customer customer;

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

    ChangeCustomerInformation(Customer customer) {
        this.customer=customer;

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
            boolean check=false;
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (!txtFirstName.getText().isBlank()) {
                        customer.setFirstName(txtFirstName.getText());
                        check=true;
                    }

                    else if (!txtLastName.getText().isBlank()) {
                        customer.setLastName(txtLastName.getText());
                        check=true;
                    }

                    else if (!txtEmail.getText().isBlank()) {
                        checkEmail(txtEmail.getText());
                        customer.setEmail(txtEmail.getText());
                        check=true;
                    }

                    else if (!txtPhone.getText().isBlank()) {
                        checkPhoneNumber(txtPhone.getText());
                        customer.setPhoneNumber(txtPhone.getText());
                        check=true;
                    }

                    else if (txtPassword.getPassword().length > 0) {
                        customer.setPassWord(txtPassword.getPassword());
                        check=true;
                    }

                    if(check){
                        WriteInfo.updateCustomer(customer);
                        JOptionPane.showMessageDialog(null, "Information Changed Successfully");
                    }
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

class ShoppingCartPart extends JFrame{
    Customer customer;

    DefaultListModel<String> hold=new DefaultListModel<>();
    JList<String> list=new JList<>(hold);

    JLabel lblMoney=new JLabel();
    JButton btnBack=new JButton("Back");
    JButton btnBuy=new JButton("Buy");
    JButton btnDiscount=new JButton("Discount");

    ShoppingCartPart(Customer customer,JFrame customerFrame,JLabel money){
        ArrayList<Discount> realDiscounts=new ArrayList<>();
        ArrayList<Discount> copyDiscounts=new ArrayList<>();
        ArrayList<Integer> numbers=new ArrayList<>();

        if(customer.getShoppingSize()==0){
            JOptionPane.showMessageDialog(null,"Shopping Cart Is Empty!");
            dispose();
            customerFrame.setVisible(true);
        }

        else {
            customer.showShoppingCart(hold,lblMoney);
            customer.setShoppingCartDiscount();
            this.customer = customer;

            this.setTitle("Shopping Cart");
            this.setSize(500, 500);
            this.setDefaultCloseOperation(EXIT_ON_CLOSE);
            this.setResizable(false);
            this.setLocationRelativeTo(null);

            SpringLayout layout=new SpringLayout();
            this.setLayout(layout);

            Container container=this.getContentPane();

            layout.putConstraint(SpringLayout.WEST,lblMoney,200,SpringLayout.WEST,container);
            layout.putConstraint(SpringLayout.NORTH,lblMoney,20,SpringLayout.NORTH,container);
            layout.putConstraint(SpringLayout.WEST,list,100,SpringLayout.WEST,container);
            layout.putConstraint(SpringLayout.NORTH,list,50,SpringLayout.NORTH,container);
            layout.putConstraint(SpringLayout.WEST,btnBuy,100,SpringLayout.WEST,container);
            layout.putConstraint(SpringLayout.NORTH,btnBuy,20,SpringLayout.SOUTH,list);
            layout.putConstraint(SpringLayout.WEST,btnDiscount,50,SpringLayout.EAST,btnBuy);
            layout.putConstraint(SpringLayout.NORTH,btnDiscount,20,SpringLayout.SOUTH,list);
            layout.putConstraint(SpringLayout.WEST,btnBack,50,SpringLayout.EAST,btnDiscount);
            layout.putConstraint(SpringLayout.NORTH,btnBack,20,SpringLayout.SOUTH,list);

            btnBack.addActionListener(e -> {
                dispose();
                customerFrame.setVisible(true);
            });

            list.addListSelectionListener(e -> {
                int index=list.getSelectedIndex();
                if(index % 2 == 0){
                    if(!e.getValueIsAdjusting()) {
                        index/=2;
                        customerProduct product=new customerProduct(customer,index,hold,lblMoney);
                    }
                }
            });

            btnBuy.addActionListener(e -> {
                String[] values={"Accept","Refuse"};
                String ans=(String) JOptionPane.showInputDialog(null,customer.personalInformation()
                ,"Info",JOptionPane.QUESTION_MESSAGE,null,values,null);

                if(ans!=null && ans.equals(values[0])){
                    try {
                        customer.buyProducts(money);
                        customer.setDiscountCapacities(copyDiscounts, realDiscounts);
                        dispose();
                        customerFrame.setVisible(true);
                        JOptionPane.showMessageDialog(null,"Product Bought Successfully");
                    }
                    catch (NotEnoughMoney ex){
                        JOptionPane.showMessageDialog(null,ex.getMessage());
                    }
                    catch (SQLException | ClassNotFoundException ignore) {}
                }
            });

            btnDiscount.addActionListener(e -> {
                try{
                    String code=JOptionPane.showInputDialog("Enter Code :");
                    if(code!=null) {
                        Discount temp = customer.checkDiscountCode(code);
                        int num = customer.checkDiscountNumber(code);

                        if (!realDiscounts.contains(temp) && temp.getCapacity() > 0) {
                            realDiscounts.add(temp);

                            Discount copyTemp = (Discount) temp.clone();
                            copyTemp.setCapacity(copyTemp.getCapacity() - 1);
                            copyDiscounts.add(copyTemp);

                            numbers.add(num - 1);
                            customer.setDiscountPrice(temp);
                        }

                        else {
                            int index = realDiscounts.indexOf(temp);
                            int capacity = copyDiscounts.get(index).getCapacity();

                            if (numbers.get(index) > 0 && capacity > 0) {
                                copyDiscounts.get(index).setCapacity(capacity - 1);
                                numbers.set(index, numbers.get(index) - 1);
                                customer.setDiscountPrice(temp);
                            }
                            else {
                                throw new InvalidDiscountCode();
                            }
                        }
                    }
                }
                catch(InvalidDiscountCode ex){
                    JOptionPane.showMessageDialog(null,ex.getMessage());
                }
            });

            this.add(lblMoney);
            this.add(list);
            this.add(btnBack);
            this.add(btnBuy);
            this.add(btnDiscount);
            this.setVisible(true);
        }
    }
}

class customerProduct extends JFrame{
    Customer customer;

    JTextArea info=new JTextArea();
    JButton btnBack=new JButton("Back");
    JButton btnDelete=new JButton("Delete");

    customerProduct(Customer customer,int index,DefaultListModel<String> hold,JLabel money){
        Goods good=customer.showProduct(index);
        this.customer=customer;
        info.setText(good.showInfo());
        info.setEditable(false);

        this.setTitle(good.getClass().getSimpleName());
        this.setSize(500,500);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        SpringLayout layout=new SpringLayout();
        this.setLayout(layout);

        Container container=this.getContentPane();

        layout.putConstraint(SpringLayout.WEST,info,100,SpringLayout.WEST,container);
        layout.putConstraint(SpringLayout.NORTH,info,50,SpringLayout.NORTH,container);
        layout.putConstraint(SpringLayout.WEST,btnDelete,100,SpringLayout.WEST,container);
        layout.putConstraint(SpringLayout.NORTH,btnDelete,30,SpringLayout.SOUTH,info);
        layout.putConstraint(SpringLayout.WEST,btnBack,40,SpringLayout.EAST,btnDelete);
        layout.putConstraint(SpringLayout.NORTH,btnBack,30,SpringLayout.SOUTH,info);

        btnBack.addActionListener(e -> {
            dispose();
        });

        btnDelete.addActionListener(e -> {
            try {
                customer.deleteProduct(index);
                JOptionPane.showMessageDialog(null, "Product Deleted");
                dispose();
                hold.clear();
                customer.showShoppingCart(hold, money);
            }
            catch (SQLException | ClassNotFoundException ignore) {}
        });

        this.add(info);
        this.add(btnDelete);
        this.add(btnBack);
        this.setVisible(true);
    }
}
