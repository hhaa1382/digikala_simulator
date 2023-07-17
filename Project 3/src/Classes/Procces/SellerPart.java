package Classes.Procces;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Ref;
import java.sql.SQLException;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

import Classes.Data.WriteInfo;
import Classes.Exeptions.*;
import Classes.Products.*;
import Classes.User.*;

import javax.swing.*;

public class SellerPart extends JFrame {
    Seller seller;
    String choice;

    MenuBar tempMenu=new MenuBar();
    Menu menu=new Menu("Panel");
    Menu subMenu=new Menu("Personal information");

    MenuItem showSubItem=new MenuItem("Show");
    MenuItem changeSubItem=new MenuItem("Change");
    MenuItem logoutItem=new MenuItem("Logout");

    JLabel info=new JLabel();
    JLabel lblMenu=new JLabel("Menu");

    JButton btnSellHistory=new JButton("Sales Histories");
    JButton btnAddRequest=new JButton("Add Product");
    JButton btnProducts=new JButton("Products");

    public SellerPart(Seller seller,JFrame mainPart){
        this.seller=seller;

        this.setTitle("Seller");
        this.setSize(500,340);
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
            JOptionPane.showMessageDialog(null,seller.personalInformation()));

        changeSubItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChangeSellerInformation changePart=new ChangeSellerInformation(seller);
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

        info.setText(seller.getFirstName()+" "+seller.getLastName());
        info.setBounds(10,20,100,30);
        this.add(info);

        lblMenu.setBounds(240,100,100,20);
        btnSellHistory.setBounds(250,20,200,20);
        btnAddRequest.setBounds(150,140,200,20);
        btnProducts.setBounds(150,180,200,50);

        btnAddRequest.addActionListener(e -> {
            setVisible(false);
            MakeProduct make=new MakeProduct(seller,this);
        });

        btnProducts.addActionListener(e -> {
            setVisible(false);
            SellerProduct product=new SellerProduct(seller,this);
        });

        btnSellHistory.addActionListener(e ->{
            String text=seller.showSellHistory();

            while(text!=null) {
                String[] values = {"Products info", "Delete facture", "Next", "Home"};
                choice = (String) JOptionPane.showInputDialog(null, text,
                    "Sell Histories", JOptionPane.YES_NO_OPTION, null, values, null);

                if (choice != null) {
                    if (choice.equals(values[0])) {
                        Integer[] proValues = seller.getValues();
                        Integer choose = (Integer) JOptionPane.showInputDialog(null, seller.showProductSellHistory()
                                , "Products", JOptionPane.PLAIN_MESSAGE, null, proValues, null);

                        if (choose != null) {
                            JOptionPane.showMessageDialog(null, seller.showProductInfo(choose-1));
                        }
                    }

                    else if (choice.equals(values[1])) {
                        seller.deleteFacture();
                        text=seller.showSellHistory();
                    }

                    else if(choice.equals(values[2])){
                        seller.increaseCounter();
                        text=seller.showSellHistory();
                    }

                    else if (choice.equals(values[3])) {
                        seller.setCounter(0);
                        break;
                    }
                }
            }
        });

        this.add(lblMenu);
        this.add(btnSellHistory);
        this.add(btnAddRequest);
        this.add(btnProducts);

        this.setVisible(true);
    }
}

class ChangeSellerInformation extends JFrame{
    Seller seller;

    JLabel lblFirstName=new JLabel("First Name :");
    JLabel lblLastName=new JLabel("Last Name :");
    JLabel lblEmail=new JLabel("Email :");
    JLabel lblPhone=new JLabel("Phone :");
    JLabel lblPassword=new JLabel("Password :");
    JLabel lblCompany=new JLabel("Company :");

    JTextField txtFirstName=new JTextField(20);
    JTextField txtLastName=new JTextField(20);
    JTextField txtEmail=new JTextField(20);
    JTextField txtPhone=new JTextField(20);
    JPasswordField txtPassword=new JPasswordField(20);
    JTextField txtCompany=new JTextField(20);

    JButton btnSave=new JButton("Save");
    JButton btnBack=new JButton("Back");

    ChangeSellerInformation(Seller seller) {
        this.seller=seller;

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
        this.add(lblCompany);
        this.add(txtFirstName);
        this.add(txtLastName);
        this.add(txtEmail);
        this.add(txtPhone);
        this.add(txtPassword);
        this.add(txtCompany);
        this.add(btnSave);
        this.add(btnBack);

        sp.putConstraint(SpringLayout.WEST, lblFirstName, 50, SpringLayout.WEST, c);
        sp.putConstraint(SpringLayout.WEST, lblLastName, 50, SpringLayout.WEST, c);
        sp.putConstraint(SpringLayout.WEST, lblEmail, 50, SpringLayout.WEST, c);
        sp.putConstraint(SpringLayout.WEST, lblPhone, 50, SpringLayout.WEST, c);
        sp.putConstraint(SpringLayout.WEST, lblPassword, 50, SpringLayout.WEST, c);
        sp.putConstraint(SpringLayout.WEST, lblCompany, 50, SpringLayout.WEST, c);

        sp.putConstraint(SpringLayout.NORTH, lblFirstName, 50, SpringLayout.NORTH, c);
        sp.putConstraint(SpringLayout.NORTH, lblLastName, 50, SpringLayout.NORTH, lblFirstName);
        sp.putConstraint(SpringLayout.NORTH, lblEmail, 50, SpringLayout.NORTH, lblLastName);
        sp.putConstraint(SpringLayout.NORTH, lblPhone, 50, SpringLayout.NORTH, lblEmail);
        sp.putConstraint(SpringLayout.NORTH, lblPassword, 50, SpringLayout.NORTH, lblPhone);
        sp.putConstraint(SpringLayout.NORTH, lblCompany, 50, SpringLayout.NORTH, lblPassword);

        sp.putConstraint(SpringLayout.WEST, txtFirstName, 200, SpringLayout.WEST, c);
        sp.putConstraint(SpringLayout.WEST, txtLastName, 200, SpringLayout.WEST, c);
        sp.putConstraint(SpringLayout.WEST, txtEmail, 200, SpringLayout.WEST, c);
        sp.putConstraint(SpringLayout.WEST, txtPhone, 200, SpringLayout.WEST, c);
        sp.putConstraint(SpringLayout.WEST, txtPassword, 200, SpringLayout.WEST, c);
        sp.putConstraint(SpringLayout.WEST, txtCompany, 200, SpringLayout.WEST, c);

        sp.putConstraint(SpringLayout.NORTH, txtFirstName, 50, SpringLayout.NORTH, c);
        sp.putConstraint(SpringLayout.NORTH, txtLastName, 50, SpringLayout.NORTH, txtFirstName);
        sp.putConstraint(SpringLayout.NORTH, txtEmail, 50, SpringLayout.NORTH, txtLastName);
        sp.putConstraint(SpringLayout.NORTH, txtPhone, 50, SpringLayout.NORTH, txtEmail);
        sp.putConstraint(SpringLayout.NORTH, txtPassword, 50, SpringLayout.NORTH, txtPhone);
        sp.putConstraint(SpringLayout.NORTH, txtCompany, 50, SpringLayout.NORTH, txtPassword);

        sp.putConstraint(SpringLayout.WEST, btnSave, 100, SpringLayout.WEST, c);
        sp.putConstraint(SpringLayout.WEST, btnBack, 100, SpringLayout.WEST, btnSave);
        sp.putConstraint(SpringLayout.NORTH, btnSave, 100, SpringLayout.NORTH, txtCompany);
        sp.putConstraint(SpringLayout.NORTH, btnBack, 100, SpringLayout.NORTH, txtCompany);

        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (!txtFirstName.getText().isBlank()) {
                        seller.setFirstName(txtFirstName.getText());
                    }

                    else if (!txtLastName.getText().isBlank()) {
                        seller.setLastName(txtLastName.getText());
                    }

                    else if (!txtEmail.getText().isBlank()) {
                        checkEmail(txtEmail.getText());
                        seller.setEmail(txtEmail.getText());
                    }

                    else if (!txtPhone.getText().isBlank()) {
                        checkPhoneNumber(txtPhone.getText());
                        seller.setPhoneNumber(txtPhone.getText());
                    }

                    else if (txtPassword.getPassword().length > 0) {
                        seller.setPassWord(txtPassword.getPassword());
                    }

                    else if (!txtCompany.getText().isBlank()) {
                        seller.setCompanyName(txtCompany.getText());
                    }

                    WriteInfo.updateSeller(seller);
                    JOptionPane.showMessageDialog(null, "Information Changed Successfully");
                }
                catch (InvalidEmail | InvalidPhoneNumber ex){
                    JOptionPane.showMessageDialog(null,ex.getMessage());
                }

                catch (SQLException | ClassNotFoundException ignored) {}
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

class SellerProduct extends JFrame{
    Seller seller;

    int counter=0;
    JButton btnNext=new JButton("Next");
    JButton btnCancel=new JButton("Cancel");
    DefaultListModel<String> hold=new DefaultListModel<>();
    JList<String> list=new JList<>(hold);

    SellerProduct(Seller seller,JFrame sellerFrame){
        this.seller=seller;

        if(seller.getProductsSize()==0){
            JOptionPane.showMessageDialog(null,"There is no product !!");
            dispose();
            sellerFrame.setVisible(true);
        }

        else{
            seller.showProductList(hold,counter);

            this.setTitle("Seller Products");
            this.setSize(500,500);
            this.setDefaultCloseOperation(EXIT_ON_CLOSE);
            this.setResizable(false);
            this.setLocationRelativeTo(null);

            SpringLayout layout=new SpringLayout();
            this.setLayout(layout);

            Container container=this.getContentPane();

            layout.putConstraint(SpringLayout.WEST,list,100,SpringLayout.WEST,container);
            layout.putConstraint(SpringLayout.NORTH,list,50,SpringLayout.NORTH,container);

            layout.putConstraint(SpringLayout.WEST,btnNext,150,SpringLayout.WEST,container);
            layout.putConstraint(SpringLayout.NORTH,btnNext,20,SpringLayout.SOUTH,list);
            layout.putConstraint(SpringLayout.WEST,btnCancel,300,SpringLayout.WEST,container);
            layout.putConstraint(SpringLayout.NORTH,btnCancel,20,SpringLayout.SOUTH,list);

            btnCancel.addActionListener(e -> {
                dispose();
                sellerFrame.setVisible(true);
            });

            list.addListSelectionListener(e -> {
                Admin admin=Admin.getAdmin();
                int index=list.getSelectedIndex();
                if(index % 2 == 0){
                    if(!e.getValueIsAdjusting()) {
                        index/=2;
                        ShowSellerProducts show=new ShowSellerProducts(seller,seller.getProduct(index));
                    }
                }
            });

            this.add(btnNext);
            this.add(btnCancel);
            this.add(list);
            this.setVisible(true);
        }
    }
}

class ShowSellerProducts extends JFrame{
    Seller seller;

    JComboBox<String> options;
    JTextArea info=new JTextArea();
    JButton btnChange=new JButton("Change");
    JButton btnBack=new JButton("Back");
    JButton btnOk=new JButton("Ok");
    JButton btnDelete=new JButton("Delete");

    ShowSellerProducts(Seller seller,Goods good){
        this.seller=seller;
        info.setText(good.showInfo());

        this.setTitle("Product Info");
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

        if(good instanceof HomeAppliances){
            layout.putConstraint(SpringLayout.WEST,btnChange,100,SpringLayout.WEST,container);
            layout.putConstraint(SpringLayout.NORTH,btnChange,30,SpringLayout.SOUTH,info);
            layout.putConstraint(SpringLayout.WEST,btnDelete,40,SpringLayout.EAST,btnChange);
            layout.putConstraint(SpringLayout.NORTH,btnDelete,30,SpringLayout.SOUTH,info);
            layout.putConstraint(SpringLayout.WEST,btnBack,40,SpringLayout.EAST,btnDelete);
            layout.putConstraint(SpringLayout.NORTH,btnBack,30,SpringLayout.SOUTH,info);

            btnBack.addActionListener(e -> {
                dispose();
            });

            btnDelete.addActionListener(e -> {
                Admin admin=Admin.getAdmin();
                admin.setDeleteProduct(good);
                try {
                    if(good instanceof Television){
                        WriteInfo.saveRequest(good,"tv","delete",null);
                    }

                    else if(good instanceof Refrigerator){
                        WriteInfo.saveRequest(good,"refrigerator","delete",null);
                    }

                    else if(good instanceof GasStove){
                        WriteInfo.saveRequest(good,"gasStove","delete",null);
                    }
                }
                catch (SQLException | ClassNotFoundException ignore){}

                JOptionPane.showMessageDialog(null,"Your Request Will Reviewed By Admin");
            });

            btnChange.addActionListener(e -> {
                try {
                    ChangeProduct change=new ChangeProduct(seller,good);
                    if(good instanceof Television){
                        WriteInfo.saveRequest(good,"tv","change",null);
                    }
                    else if(good instanceof Refrigerator){
                        WriteInfo.saveRequest(good,"refrigerator","change",null);
                    }
                    else if(good instanceof GasStove){
                        WriteInfo.saveRequest(good,"gasStove","change",null);
                    }
                }
                catch (SQLException | ClassNotFoundException ignore) {}
            });

            this.add(btnChange);
            this.add(btnDelete);
            this.add(btnBack);
        }

        else  if((good instanceof Clothing) || (good instanceof Food)){
            String[] values={"Back","Change","Delete","Add Discount Code","Discounts","Add Discount"
                    ,"Delete Discount","Add Auction","Delete Auction"};
            options=new JComboBox<>(values);
            options.setSelectedIndex(0);

            layout.putConstraint(SpringLayout.WEST,options,100,SpringLayout.WEST,container);
            layout.putConstraint(SpringLayout.NORTH,options,30,SpringLayout.SOUTH,info);
            layout.putConstraint(SpringLayout.WEST,btnOk,30,SpringLayout.EAST,options);
            layout.putConstraint(SpringLayout.NORTH,btnOk,30,SpringLayout.SOUTH,info);

            btnOk.addActionListener(e -> {
                int index=options.getSelectedIndex();

                if(index==0){
                    dispose();
                }

                else if(index==1){
                    try {
                        ChangeProduct change=new ChangeProduct(seller,good);
                        if(good instanceof Clothes){
                            WriteInfo.saveRequest(good,"clothe","change",null);
                        }
                        else if(good instanceof Shoes){
                            WriteInfo.saveRequest(good,"shoe","change",null);
                        }
                        else if(good instanceof Food){
                            WriteInfo.saveRequest(good,"food","change",null);
                        }
                    }
                    catch (SQLException | ClassNotFoundException ignore) {}
                }

                else if(index==2){
                    Admin admin=Admin.getAdmin();
                    admin.setDeleteProduct(good);
                    try {
                        if(good instanceof Clothes){
                            WriteInfo.saveRequest(good,"clothe","delete",null);
                        }
                        else if(good instanceof Shoes){
                            WriteInfo.saveRequest(good,"shoe","delete",null);
                        }
                        else if(good instanceof Food){
                            WriteInfo.saveRequest(good,"food","delete",null);
                        }
                    }
                    catch (SQLException | ClassNotFoundException ignore) {}

                    JOptionPane.showMessageDialog(null,"Your Request Will Reviewed By Admin");
                }

                else if(index==3){
                    try {
                        String date = JOptionPane.showInputDialog("Enter discount value (e.g. 2022-09-03) : ");
                        String tempCapacity=JOptionPane.showInputDialog("Enter capacity : ");

                        if(date!=null && tempCapacity!=null) {
                            int capacity = Integer.valueOf(tempCapacity);

                            if (good instanceof Clothing) {
                                Clothing clothing = (Clothing) good;
                                good.setDiscount(clothing.addDiscountByCode(date, capacity));
                            }
                            else {
                                Food food = (Food) good;
                                good.setDiscount(food.addDiscountByCode(date, capacity));
                            }

                            JOptionPane.showMessageDialog(null,"Code Added Successfully");
                        }
                    }
                    catch (NumberFormatException ex){
                        JOptionPane.showMessageDialog(null,"Enter a Number!");
                    }

                    catch(DateTimeParseException ex){
                        JOptionPane.showMessageDialog(null,"Enter Date Truly!");
                    }
                    catch (SQLException | ClassNotFoundException ignore) {}
                }

                else if(index==4){
                    String choice= JOptionPane.showInputDialog(null,
                            good.showDiscountCode()+"Enter Number : ","Discounts",
                            JOptionPane.OK_CANCEL_OPTION);
                    int num=Integer.valueOf(choice);

                    if(num<good.getDiscountSize() && num>0){
                        try {
                            good.deleteDiscount(num-1);
                            JOptionPane.showMessageDialog(null,"Code Deleted Successfully");
                        }
                        catch (NumberFormatException ex){
                            JOptionPane.showMessageDialog(null,"Enter a Number!!");
                        }

                        catch (ArrayIndexOutOfBoundsException ex){
                            JOptionPane.showMessageDialog(null,"Enter a Number in Range!");
                        }
                        catch (SQLException | ClassNotFoundException ignore) {}
                    }
                }

                else if(index==5){
                    if(good instanceof Clothing){
                        Clothing clothing=(Clothing)good;
                        clothing.addDiscount();
                        if(good instanceof Clothes){
                            try {
                                WriteInfo.saveAddDiscount(good,"clothe");
                            }
                            catch (ClassNotFoundException | SQLException ignore) {}
                        }

                        else if(good instanceof Shoes){
                            try {
                                WriteInfo.saveAddDiscount(good,"shoes");
                            }
                            catch (ClassNotFoundException | SQLException ignore) {}
                        }
                    }

                    else {
                        Food food=(Food)good;
                        food.addDiscount();
                        try {
                            WriteInfo.saveAddDiscount(good,"food");
                        }
                        catch (ClassNotFoundException | SQLException ignore) {}
                    }
                    JOptionPane.showMessageDialog(null,"Discount Added Successfully");
                }

                else if(index==6){
                    if(good instanceof Clothing){
                        Clothing clothing=(Clothing)good;
                        clothing.takeDiscount();
                        if(good instanceof Clothes){
                            try {
                                WriteInfo.saveTakeDiscount(good);
                            }
                            catch (ClassNotFoundException | SQLException ignore) {}
                        }

                        else if(good instanceof Shoes){
                            try {
                                WriteInfo.saveTakeDiscount(good);
                            }
                            catch (ClassNotFoundException | SQLException ignore) {}
                        }
                    }

                    else {
                        Food food=(Food)good;
                        food.takeDiscount();
                        try {
                            WriteInfo.saveTakeDiscount(good);
                        }
                        catch (ClassNotFoundException | SQLException ignore) {}
                    }
                    JOptionPane.showMessageDialog(null,"Discount Taken Successfully");
                }

                else if(index==7){
                    if(good instanceof Clothing){
                        Clothing hold=(Clothing)good;
                        hold.setAuction(true);
                        if(good instanceof Clothes){
                            try {
                                WriteInfo.saveAddAuction(good,"clothe");
                            }
                            catch (ClassNotFoundException | SQLException ignore) {}
                        }

                        else if(good instanceof Shoes){
                            try {
                                WriteInfo.saveAddAuction(good,"shoes");
                            }
                            catch (ClassNotFoundException | SQLException ignore) {}
                        }
                    }
                    else {
                        Food hold=(Food)good;
                        hold.setAuction(true);
                        try {
                            WriteInfo.saveAddAuction(good,"food");
                        }
                        catch (ClassNotFoundException | SQLException ignore) {}
                    }
                    JOptionPane.showMessageDialog(null,"Product Added to Auction");
                }

                else if(index==8){
                    if(good instanceof Clothing){
                        Clothing hold=(Clothing)good;
                        hold.setAuction(false);
                        if(good instanceof Clothes){
                            try {
                                WriteInfo.saveTakeAuction(good);
                            }
                            catch (ClassNotFoundException | SQLException ignore) {}
                        }

                        else if(good instanceof Shoes){
                            try {
                                WriteInfo.saveTakeAuction(good);
                            }
                            catch (ClassNotFoundException | SQLException ignore) {}
                        }
                    }
                    else {
                        Food hold=(Food)good;
                        hold.setAuction(false);
                        try {
                            WriteInfo.saveTakeAuction(good);
                        }
                        catch (ClassNotFoundException | SQLException ignore) {}
                    }
                    JOptionPane.showMessageDialog(null,"Product Taken to Auction");
                }
            });

            this.add(btnOk);
            this.add(options);
        }

        else if(good instanceof DigitalCommodity){
            String[] values={"Back","Change","Delete","Add Discount Code","Discounts","Add Discount","Delete Discount"};
            options=new JComboBox<>(values);
            options.setSelectedIndex(0);

            layout.putConstraint(SpringLayout.WEST,options,100,SpringLayout.WEST,container);
            layout.putConstraint(SpringLayout.NORTH,options,30,SpringLayout.SOUTH,info);
            layout.putConstraint(SpringLayout.WEST,btnOk,30,SpringLayout.EAST,options);
            layout.putConstraint(SpringLayout.NORTH,btnOk,30,SpringLayout.SOUTH,info);

            btnOk.addActionListener(e -> {
                int index=options.getSelectedIndex();

                if(index==0){
                    dispose();
                }

                else if(index==1){
                    try {
                        ChangeProduct change=new ChangeProduct(seller,good);
                        if(good instanceof Mobile){
                            WriteInfo.saveRequest(good,"mobile","change",null);
                        }
                        else if(good instanceof Laptop){
                            WriteInfo.saveRequest(good,"laptop","change",null);
                        }
                    }
                    catch (SQLException | ClassNotFoundException ignore) {}
                }

                else if(index==2){
                    Admin admin=Admin.getAdmin();
                    admin.setDeleteProduct(good);

                    try {
                        if(good instanceof Mobile){
                            WriteInfo.saveRequest(good,"mobile","delete",null);
                        }
                        else if(good instanceof Laptop){
                            WriteInfo.saveRequest(good,"laptop","delete",null);
                        }
                    }
                    catch (SQLException | ClassNotFoundException ignore) {}

                    JOptionPane.showMessageDialog(null,"Your Request Will Reviewed By Admin");
                }

                else if(index==3){
                    try {
                        String date = JOptionPane.showInputDialog("Enter discount value (e.g. 2022-09-03) : ");
                        String tempCapacity=JOptionPane.showInputDialog("Enter capacity : ");

                        if(date!=null && tempCapacity!=null) {
                            int capacity = Integer.valueOf(tempCapacity);

                            DigitalCommodity digitalCommodity = (DigitalCommodity) good;
                            good.setDiscount(digitalCommodity.addDiscountByCode(date, capacity));

                            JOptionPane.showMessageDialog(null,"Code Added Successfully");
                        }
                    }
                    catch (NumberFormatException ex){
                        JOptionPane.showMessageDialog(null,"Enter a Number!");
                    }

                    catch(DateTimeParseException ex){
                        JOptionPane.showMessageDialog(null,"Enter Date Truly!");
                    }
                    catch (SQLException | ClassNotFoundException ignore) {}
                }

                else if(index==4){
                    String choice= JOptionPane.showInputDialog(null,
                            good.showDiscountCode()+"Enter Number : ","Discounts",
                            JOptionPane.OK_CANCEL_OPTION);

                    int num=Integer.valueOf(choice);
                    if(num<good.getDiscountSize() && num>0){
                        try {
                            good.deleteDiscount(num-1);
                            JOptionPane.showMessageDialog(null,"Code Deleted Successfully");
                        }
                        catch (NumberFormatException ex){
                            JOptionPane.showMessageDialog(null,"Enter a Number!!");
                        }

                        catch (ArrayIndexOutOfBoundsException ex){
                            JOptionPane.showMessageDialog(null,"Enter a Number in Range!");
                        }
                        catch (SQLException | ClassNotFoundException ex) {}
                    }
                }

                else if(index==5){
                    DigitalCommodity digitalCommodity=(DigitalCommodity)good;
                    digitalCommodity.addDiscount();
                    if(good instanceof Mobile){
                        try {
                            WriteInfo.saveAddDiscount(good,"mobile");
                        }
                        catch (ClassNotFoundException | SQLException ignore) {}
                    }

                    else if(good instanceof Laptop){
                        try {
                            WriteInfo.saveAddDiscount(good,"laptop");
                        }
                        catch (ClassNotFoundException | SQLException ignore) {}
                    }
                    JOptionPane.showMessageDialog(null,"Discount Added Successfully");
                }

                else if(index==6){
                    DigitalCommodity digitalCommodity=(DigitalCommodity)good;
                    digitalCommodity.takeDiscount();
                    if(good instanceof Mobile){
                        try {
                            WriteInfo.saveTakeDiscount(good);
                        }
                        catch (ClassNotFoundException | SQLException ignore) {}
                    }

                    else if(good instanceof Laptop){
                        try {
                            WriteInfo.saveTakeDiscount(good);
                        }
                        catch (ClassNotFoundException | SQLException ignore) {}
                    }
                    JOptionPane.showMessageDialog(null,"Discount Taken Successfully");
                }
            });

            this.add(btnOk);
            this.add(options);
        }

        this.add(info);
        this.setVisible(true);
    }
}
