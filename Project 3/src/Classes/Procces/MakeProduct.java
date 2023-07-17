package Classes.Procces;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

import Classes.Data.WriteInfo;
import Classes.Exeptions.InvalidInput;
import Classes.Products.*;
import Classes.User.*;

import javax.swing.*;

public class MakeProduct extends JFrame {
    Admin admin=Admin.getAdmin();
    Seller seller;
    JFrame preFrame;

    String categoryChoice,ProductChoice;
    String[] categories={"Digital Category","Clothing Category","Home Appliance Category","Food Category"};
    String[] digitalCategory={"Mobile","Laptop"};
    String[] clothingCategory={"Clothes","Shoes"};
    String[] homeCategory={"Television","Refrigerator","Gas Stove"};

    JLabel lblName=new JLabel("Name");
    JLabel lblBrand=new JLabel("Brand");
    JLabel lblPrice=new JLabel("Price");
    JLabel lblNumber=new JLabel("Number");
    JLabel lblDescription=new JLabel("Description");
    JLabel lblWarranty=new JLabel("Has Warranty");

    JTextField txtName=new JTextField(15);
    JTextField txtBrand=new JTextField(15);
    JTextField txtPrice=new JTextField(15);
    JTextField txtNumber=new JTextField(15);
    JTextField txtDescription;
    JRadioButton btnYes=new JRadioButton("Yes");
    JRadioButton btnNo=new JRadioButton("No");
    boolean warrantyCheck=false;
    boolean gaming;
    String clothingType;
    boolean hasFreeze;
    boolean hasGasOven;

    Container container=this.getContentPane();
    SpringLayout sp=new SpringLayout();

    JButton btnSave=new JButton("Save");
    JButton btnCancel=new JButton("Cancel");

    public MakeProduct(Seller seller,JFrame preFrame){
        this.preFrame=preFrame;

        categoryChoice = (String) JOptionPane.showInputDialog(null, "Choose a category",
                "Categories", JOptionPane.PLAIN_MESSAGE, null, categories, null);

        if(categoryChoice==null){
            dispose();
            preFrame.setVisible(true);
        }

        else {
            if (categoryChoice.equals(categories[0])) {
                ProductChoice = (String) JOptionPane.showInputDialog(null, "Choose a Product",
                        "Products", JOptionPane.PLAIN_MESSAGE, null, digitalCategory, null);
            }

            else if (categoryChoice.equals(categories[1])) {
                ProductChoice = (String) JOptionPane.showInputDialog(null, "Choose a Product",
                        "Products", JOptionPane.PLAIN_MESSAGE, null, clothingCategory, null);
            }

            else if (categoryChoice.equals(categories[2])) {
                ProductChoice = (String) JOptionPane.showInputDialog(null, "Choose a Product",
                        "Products", JOptionPane.PLAIN_MESSAGE, null, homeCategory, null);
            }

            else if(categoryChoice.equals(categories[3])){
                ProductChoice="Food";
            }

            this.seller = seller;

            if(ProductChoice==null){
                dispose();
                preFrame.setVisible(true);
            }

            else {
                this.setTitle(ProductChoice);
                this.setSize(600, 600);
                this.setDefaultCloseOperation(EXIT_ON_CLOSE);
                this.setResizable(false);
                this.setLocationRelativeTo(null);

                this.setLayout(sp);

                if(categoryChoice.equals(categories[1]) || categoryChoice.equals(categories[3])) {
                    txtDescription = new JTextField(53);
                }

                else{
                    txtDescription = new JTextField(15);
                    ButtonGroup radioGroup=new ButtonGroup();
                    btnNo.setSelected(true);
                    radioGroup.add(btnYes);
                    radioGroup.add(btnNo);
                    this.add(lblWarranty);
                    this.add(btnNo);
                    this.add(btnYes);
                }

                this.add(lblName);
                this.add(txtName);
                this.add(lblBrand);
                this.add(txtBrand);
                this.add(lblPrice);
                this.add(txtPrice);
                this.add(lblNumber);
                this.add(txtNumber);
                this.add(lblDescription);
                this.add(txtDescription);
                this.add(btnSave);
                this.add(btnCancel);

                btnYes.addActionListener(e ->{
                    warrantyCheck=true;
                });

                btnNo.addActionListener(e ->{
                    warrantyCheck=false;
                });

                btnCancel.addActionListener(e -> {
                    dispose();
                    preFrame.setVisible(true);
                });

                sp.putConstraint(SpringLayout.WEST,lblName,70,SpringLayout.WEST,container);
                sp.putConstraint(SpringLayout.NORTH,lblName,20,SpringLayout.NORTH,container);
                sp.putConstraint(SpringLayout.WEST,txtName,20,SpringLayout.WEST,container);
                sp.putConstraint(SpringLayout.NORTH,txtName,20,SpringLayout.NORTH,lblName);

                sp.putConstraint(SpringLayout.WEST,lblBrand,450,SpringLayout.WEST,container);
                sp.putConstraint(SpringLayout.NORTH,lblBrand,20,SpringLayout.NORTH,container);
                sp.putConstraint(SpringLayout.WEST,txtBrand,400,SpringLayout.WEST,container);
                sp.putConstraint(SpringLayout.NORTH,txtBrand,20,SpringLayout.NORTH,lblBrand);

                sp.putConstraint(SpringLayout.WEST,lblPrice,70,SpringLayout.WEST,container);
                sp.putConstraint(SpringLayout.NORTH,lblPrice,40,SpringLayout.NORTH,txtName);
                sp.putConstraint(SpringLayout.WEST,txtPrice,20,SpringLayout.WEST,container);
                sp.putConstraint(SpringLayout.NORTH,txtPrice,20,SpringLayout.NORTH,lblPrice);

                sp.putConstraint(SpringLayout.WEST,lblNumber,450,SpringLayout.WEST,container);
                sp.putConstraint(SpringLayout.NORTH,lblNumber,40,SpringLayout.NORTH,txtBrand);
                sp.putConstraint(SpringLayout.WEST,txtNumber,400,SpringLayout.WEST,container);
                sp.putConstraint(SpringLayout.NORTH,txtNumber,20,SpringLayout.NORTH,lblNumber);

                sp.putConstraint(SpringLayout.WEST,lblDescription,70,SpringLayout.WEST,container);
                sp.putConstraint(SpringLayout.NORTH,lblDescription,40,SpringLayout.NORTH,txtPrice);
                sp.putConstraint(SpringLayout.WEST,txtDescription,20,SpringLayout.WEST,container);
                sp.putConstraint(SpringLayout.NORTH,txtDescription,20,SpringLayout.NORTH,lblDescription);

                if(categoryChoice.equals(categories[0]) || categoryChoice.equals(categories[2])){
                    sp.putConstraint(SpringLayout.WEST,lblWarranty,440,SpringLayout.WEST,container);
                    sp.putConstraint(SpringLayout.NORTH,lblWarranty,40,SpringLayout.NORTH,txtNumber);
                    sp.putConstraint(SpringLayout.WEST,btnYes,420,SpringLayout.WEST,container);
                    sp.putConstraint(SpringLayout.NORTH,btnYes,20,SpringLayout.NORTH,lblWarranty);
                    sp.putConstraint(SpringLayout.WEST,btnNo,500,SpringLayout.WEST,container);
                    sp.putConstraint(SpringLayout.NORTH,btnNo,20,SpringLayout.NORTH,lblWarranty);
                }

                if(categoryChoice.equals(categories[0])){
                    digitalPart(ProductChoice);
                }

                else if(categoryChoice.equals(categories[1])){
                    clothingPart(ProductChoice);
                }

                else if(categoryChoice.equals(categories[2])){
                    homePart(ProductChoice);
                }

                else if(categoryChoice.equals(categories[3])){
                    foodPart();
                }

                sp.putConstraint(SpringLayout.SOUTH,container,50,SpringLayout.SOUTH,btnSave);
                sp.putConstraint(SpringLayout.EAST,container,20,SpringLayout.EAST,txtBrand);
                this.pack();

                this.setVisible(true);
            }
        }
    }

    void digitalPart(String product){
        JLabel lblMemory=new JLabel("Memory");
        JLabel lblRam=new JLabel("Ram");
        JLabel lblOperatingSystem=new JLabel("Operating System");
        JLabel lblWeight=new JLabel("Weight");
        JLabel lblLength=new JLabel("Length");
        JLabel lblWidth=new JLabel("Width");
        JLabel lblSimNum=new JLabel("Number of Sim");
        JLabel lblCameraQuality=new JLabel("Camera Quality");
        JLabel lblCpuModel=new JLabel("Cpu Model");
        JLabel lblGaming=new JLabel("Is Gaming");

        JTextField txtMemory=new JTextField(15);
        JTextField txtRam=new JTextField(15);
        JTextField txtOperatingSystem=new JTextField(15);
        JTextField txtWeight=new JTextField(15);
        JTextField txtLength=new JTextField(15);
        JTextField txtWidth=new JTextField(15);
        JTextField txtSimNum=new JTextField(15);
        JTextField txtCameraQuality=new JTextField(15);
        JTextField txtCpuModel=new JTextField(15);
        JRadioButton btnDigitalYes=new JRadioButton("Yes");
        JRadioButton btnDigitalNo=new JRadioButton("No");
        btnNo.setSelected(true);
        ButtonGroup btnGroup=new ButtonGroup();
        btnGroup.add(btnDigitalYes);
        btnGroup.add(btnDigitalNo);

        this.add(lblMemory);
        this.add(txtMemory);
        this.add(lblRam);
        this.add(txtRam);
        this.add(lblOperatingSystem);
        this.add(txtOperatingSystem);
        this.add(lblWeight);
        this.add(txtWeight);
        this.add(lblLength);
        this.add(txtLength);
        this.add(lblWidth);
        this.add(txtWidth);

        sp.putConstraint(SpringLayout.WEST,lblMemory,70,SpringLayout.WEST,container);
        sp.putConstraint(SpringLayout.NORTH,lblMemory,40,SpringLayout.NORTH,txtDescription);
        sp.putConstraint(SpringLayout.WEST,txtMemory,20,SpringLayout.WEST,container);
        sp.putConstraint(SpringLayout.NORTH,txtMemory,20,SpringLayout.NORTH,lblMemory);

        sp.putConstraint(SpringLayout.WEST,lblRam,450,SpringLayout.WEST,container);
        sp.putConstraint(SpringLayout.NORTH,lblRam,40,SpringLayout.NORTH,txtDescription);
        sp.putConstraint(SpringLayout.WEST,txtRam,400,SpringLayout.WEST,container);
        sp.putConstraint(SpringLayout.NORTH,txtRam,20,SpringLayout.NORTH,lblMemory);

        sp.putConstraint(SpringLayout.WEST,lblOperatingSystem,40,SpringLayout.WEST,container);
        sp.putConstraint(SpringLayout.NORTH,lblOperatingSystem,40,SpringLayout.NORTH,txtMemory);
        sp.putConstraint(SpringLayout.WEST,txtOperatingSystem,20,SpringLayout.WEST,container);
        sp.putConstraint(SpringLayout.NORTH,txtOperatingSystem,20,SpringLayout.NORTH,lblOperatingSystem);

        sp.putConstraint(SpringLayout.WEST,lblWeight,450,SpringLayout.WEST,container);
        sp.putConstraint(SpringLayout.NORTH,lblWeight,40,SpringLayout.NORTH,txtRam);
        sp.putConstraint(SpringLayout.WEST,txtWeight,400,SpringLayout.WEST,container);
        sp.putConstraint(SpringLayout.NORTH,txtWeight,20,SpringLayout.NORTH,lblWeight);

        sp.putConstraint(SpringLayout.WEST,lblLength,70,SpringLayout.WEST,container);
        sp.putConstraint(SpringLayout.NORTH,lblLength,40,SpringLayout.NORTH,txtOperatingSystem);
        sp.putConstraint(SpringLayout.WEST,txtLength,20,SpringLayout.WEST,container);
        sp.putConstraint(SpringLayout.NORTH,txtLength,20,SpringLayout.NORTH,lblLength);

        sp.putConstraint(SpringLayout.WEST,lblWidth,450,SpringLayout.WEST,container);
        sp.putConstraint(SpringLayout.NORTH,lblWidth,40,SpringLayout.NORTH,txtWeight);
        sp.putConstraint(SpringLayout.WEST,txtWidth,400,SpringLayout.WEST,container);
        sp.putConstraint(SpringLayout.NORTH,txtWidth,20,SpringLayout.NORTH,lblWidth);

        if(product.equals("Mobile")){
            this.add(lblSimNum);
            this.add(txtSimNum);
            this.add(lblCameraQuality);
            this.add(txtCameraQuality);

            sp.putConstraint(SpringLayout.WEST,lblSimNum,50,SpringLayout.WEST,container);
            sp.putConstraint(SpringLayout.NORTH,lblSimNum,40,SpringLayout.NORTH,txtLength);
            sp.putConstraint(SpringLayout.WEST,txtSimNum,20,SpringLayout.WEST,container);
            sp.putConstraint(SpringLayout.NORTH,txtSimNum,20,SpringLayout.NORTH,lblSimNum);

            sp.putConstraint(SpringLayout.WEST,lblCameraQuality,430,SpringLayout.WEST,container);
            sp.putConstraint(SpringLayout.NORTH,lblCameraQuality,40,SpringLayout.NORTH,txtWidth);
            sp.putConstraint(SpringLayout.WEST,txtCameraQuality,400,SpringLayout.WEST,container);
            sp.putConstraint(SpringLayout.NORTH,txtCameraQuality,20,SpringLayout.NORTH,lblCameraQuality);

            sp.putConstraint(SpringLayout.WEST,btnSave,150,SpringLayout.WEST,container);
            sp.putConstraint(SpringLayout.NORTH,btnSave,60,SpringLayout.NORTH,txtSimNum);
            sp.putConstraint(SpringLayout.WEST,btnCancel,300,SpringLayout.WEST,container);
            sp.putConstraint(SpringLayout.NORTH,btnCancel,60,SpringLayout.NORTH,txtSimNum);

            btnSave.addActionListener(e -> {
                try {
                    productCheck();
                    digitalCheck(txtMemory.getText(), txtRam.getText(), txtOperatingSystem.getText(),
                            txtWeight.getText(), txtLength.getText(), txtWidth.getText());

                    if(txtSimNum.getText().isBlank()){
                        throw new InvalidInput("Enter Sim Numbers!!");
                    }
                    else{
                        try {
                            Integer.valueOf(txtSimNum.getText());
                        }
                        catch (NumberFormatException ex){
                            throw new InvalidInput("Enter Sim Numbers Correctly!!");
                        }
                    }

                    if(txtCameraQuality.getText().isBlank()){
                        throw new InvalidInput("Enter Camera Quality!!");
                    }

                    Mobile m=new Mobile(txtName.getText(),txtBrand.getText(),Double.valueOf(txtPrice.getText()),
                            Integer.valueOf(txtNumber.getText()),seller,txtDescription.getText(),
                            Integer.valueOf(txtMemory.getText()),Integer.valueOf(txtRam.getText()),
                            txtOperatingSystem.getText(),Double.valueOf(txtWeight.getText()),
                            Integer.valueOf(txtLength.getText()),Integer.valueOf(txtWidth.getText()),
                            warrantyCheck,Integer.valueOf(txtSimNum.getText()),txtCameraQuality.getText());

                    admin.setAddRequestProduct(m);
                    WriteInfo.saveMobile(m,false);
                    WriteInfo.saveRequest(m,"mobile","add",null);

                    JOptionPane.showMessageDialog(null,"Product Request Added Successfully");
                    dispose();
                    preFrame.setVisible(true);
                }
                catch (InvalidInput ex){
                    JOptionPane.showMessageDialog(null,ex.getMessage());
                }
                catch (SQLException | ClassNotFoundException ignore) {}
            });
        }

        else{
            this.add(lblCpuModel);
            this.add(txtCpuModel);
            this.add(lblGaming);
            this.add(btnDigitalYes);
            this.add(btnDigitalNo);
            gaming=false;

            btnDigitalYes.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    gaming =true;
                }
            });

            btnDigitalNo.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    gaming =false;
                }
            });

            sp.putConstraint(SpringLayout.WEST,lblCpuModel,40,SpringLayout.WEST,container);
            sp.putConstraint(SpringLayout.NORTH,lblCpuModel,40,SpringLayout.NORTH,txtLength);
            sp.putConstraint(SpringLayout.WEST,txtCpuModel,20,SpringLayout.WEST,container);
            sp.putConstraint(SpringLayout.NORTH,txtCpuModel,20,SpringLayout.NORTH,lblCpuModel);

            sp.putConstraint(SpringLayout.WEST,lblGaming,450,SpringLayout.WEST,container);
            sp.putConstraint(SpringLayout.NORTH,lblGaming,40,SpringLayout.NORTH,txtWidth);
            sp.putConstraint(SpringLayout.WEST,btnDigitalYes,420,SpringLayout.WEST,container);
            sp.putConstraint(SpringLayout.NORTH,btnDigitalYes,20,SpringLayout.NORTH,lblGaming);
            sp.putConstraint(SpringLayout.WEST,btnDigitalNo,500,SpringLayout.WEST,container);
            sp.putConstraint(SpringLayout.NORTH,btnDigitalNo,20,SpringLayout.NORTH,lblGaming);

            sp.putConstraint(SpringLayout.WEST,btnSave,150,SpringLayout.WEST,container);
            sp.putConstraint(SpringLayout.NORTH,btnSave,60,SpringLayout.NORTH,txtCpuModel);
            sp.putConstraint(SpringLayout.WEST,btnCancel,300,SpringLayout.WEST,container);
            sp.putConstraint(SpringLayout.NORTH,btnCancel,60,SpringLayout.NORTH,txtCpuModel);

            btnSave.addActionListener(e -> {
                try {
                    productCheck();
                    digitalCheck(txtMemory.getText(), txtRam.getText(), txtOperatingSystem.getText(),
                            txtWeight.getText(), txtLength.getText(), txtWidth.getText());

                    if(txtCpuModel.getText().isBlank()){
                        throw new InvalidInput("Enter Cpu Model!!");
                    }

                    Laptop l=new Laptop(txtName.getText(),txtBrand.getText(),Double.valueOf(txtPrice.getText()),
                            Integer.valueOf(txtNumber.getText()),seller,txtDescription.getText(),
                            Integer.valueOf(txtMemory.getText()),Integer.valueOf(txtRam.getText()),
                            txtOperatingSystem.getText(),Double.valueOf(txtWeight.getText()),
                            Integer.valueOf(txtLength.getText()),Integer.valueOf(txtWidth.getText()),
                            warrantyCheck,txtCpuModel.getText(), gaming);

                    admin.setAddRequestProduct(l);
                    WriteInfo.saveRequest(l,"laptop","add",null);
                    WriteInfo.saveLaptop(l,false);

                    JOptionPane.showMessageDialog(null,"Product Request Added Successfully");
                    dispose();
                    preFrame.setVisible(true);
                }
                catch (InvalidInput ex){
                    JOptionPane.showMessageDialog(null,ex.getMessage());
                }
                catch (SQLException | ClassNotFoundException ignore) {}
            });
        }
    }

    void clothingPart(String product){
        JLabel lblCountry=new JLabel("Country");
        JLabel lblClothingMaterial=new JLabel("Clothing Material");
        JLabel lblSize=new JLabel("Size");
        JLabel lblType=new JLabel("Type");

        JTextField txtCountry=new JTextField(15);
        JTextField txtClothingMaterial=new JTextField(15);
        JTextField txtSize=new JTextField(15);

        this.add(lblCountry);
        this.add(lblClothingMaterial);
        this.add(lblSize);
        this.add(lblType);
        this.add(txtCountry);
        this.add(txtClothingMaterial);
        this.add(txtSize);

        sp.putConstraint(SpringLayout.WEST,lblCountry,70,SpringLayout.WEST,container);
        sp.putConstraint(SpringLayout.NORTH,lblCountry,40,SpringLayout.NORTH,txtDescription);
        sp.putConstraint(SpringLayout.WEST,txtCountry,20,SpringLayout.WEST,container);
        sp.putConstraint(SpringLayout.NORTH,txtCountry,20,SpringLayout.NORTH,lblCountry);

        sp.putConstraint(SpringLayout.WEST,lblClothingMaterial,430,SpringLayout.WEST,container);
        sp.putConstraint(SpringLayout.NORTH,lblClothingMaterial,40,SpringLayout.NORTH,txtDescription);
        sp.putConstraint(SpringLayout.WEST,txtClothingMaterial,400,SpringLayout.WEST,container);
        sp.putConstraint(SpringLayout.NORTH,txtClothingMaterial,20,SpringLayout.NORTH,lblClothingMaterial);

        sp.putConstraint(SpringLayout.WEST,lblSize,70,SpringLayout.WEST,container);
        sp.putConstraint(SpringLayout.NORTH,lblSize,40,SpringLayout.NORTH,txtCountry);
        sp.putConstraint(SpringLayout.WEST,txtSize,20,SpringLayout.WEST,container);
        sp.putConstraint(SpringLayout.NORTH,txtSize,20,SpringLayout.NORTH,lblSize);

        sp.putConstraint(SpringLayout.WEST,lblType,450,SpringLayout.WEST,container);
        sp.putConstraint(SpringLayout.NORTH,lblType,40,SpringLayout.NORTH,txtClothingMaterial);

        if(product.equals("Clothes")){
            String[] type={"T_shirt","Pants","Shirt","Suit","Socks"};
            JComboBox<String> typeChoice=new JComboBox<>(type);
            typeChoice.setSelectedIndex(0);
            clothingType="T_shirt";

            this.add(typeChoice);

            typeChoice.addActionListener(e -> {
                clothingType=(String) typeChoice.getSelectedItem();
            });

            sp.putConstraint(SpringLayout.WEST,typeChoice,430,SpringLayout.WEST,container);
            sp.putConstraint(SpringLayout.NORTH,typeChoice,20,SpringLayout.NORTH,lblType);

            sp.putConstraint(SpringLayout.WEST,btnSave,150,SpringLayout.WEST,container);
            sp.putConstraint(SpringLayout.NORTH,btnSave,60,SpringLayout.NORTH,typeChoice);
            sp.putConstraint(SpringLayout.WEST,btnCancel,300,SpringLayout.WEST,container);
            sp.putConstraint(SpringLayout.NORTH,btnCancel,60,SpringLayout.NORTH,typeChoice);

            btnSave.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        productCheck();
                        clothingCheck(txtCountry.getText(), txtClothingMaterial.getText(), txtSize.getText());

                        Clothes c=new Clothes(txtName.getText(),txtBrand.getText(),Double.valueOf(txtPrice.getText()),
                                Integer.valueOf(txtNumber.getText()),seller,txtDescription.getText(),
                                txtCountry.getText(),txtClothingMaterial.getText(),
                                Integer.valueOf(txtSize.getText()),clothingType);

                        admin.setAddRequestProduct(c);
                        WriteInfo.saveRequest(c,"clothe","add",null);
                        WriteInfo.saveClothe(c,false);

                        JOptionPane.showMessageDialog(null,"Product Request Added Successfully");
                        dispose();
                        preFrame.setVisible(true);
                    }
                    catch (InvalidInput ex){
                        JOptionPane.showMessageDialog(null,ex.getMessage());
                    }
                    catch (SQLException | ClassNotFoundException ignore) {}
                }
            });
        }

        else{
            String[] type={"Formal","Boot","Sports","Sandal"};
            JComboBox<String> typeChoice=new JComboBox<>(type);
            typeChoice.setSelectedIndex(0);
            clothingType="Formal";

            this.add(typeChoice);

            sp.putConstraint(SpringLayout.WEST,typeChoice,430,SpringLayout.WEST,container);
            sp.putConstraint(SpringLayout.NORTH,typeChoice,20,SpringLayout.NORTH,lblType);

            sp.putConstraint(SpringLayout.WEST,btnSave,150,SpringLayout.WEST,container);
            sp.putConstraint(SpringLayout.NORTH,btnSave,60,SpringLayout.NORTH,typeChoice);
            sp.putConstraint(SpringLayout.WEST,btnCancel,300,SpringLayout.WEST,container);
            sp.putConstraint(SpringLayout.NORTH,btnCancel,60,SpringLayout.NORTH,typeChoice);

            btnSave.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        productCheck();
                        clothingCheck(txtCountry.getText(), txtClothingMaterial.getText(), txtSize.getText());

                        Shoes s=new Shoes(txtName.getText(),txtBrand.getText(),Double.valueOf(txtPrice.getText()),
                                Integer.valueOf(txtNumber.getText()),seller,txtDescription.getText(),
                                txtCountry.getText(),txtClothingMaterial.getText(),
                                Integer.valueOf(txtSize.getText()),clothingType);

                        admin.setAddRequestProduct(s);
                        WriteInfo.saveRequest(s,"shoe","add",null);
                        WriteInfo.saveShoe(s,false);

                        JOptionPane.showMessageDialog(null,"Product Request Added Successfully");
                        dispose();
                        preFrame.setVisible(true);
                    }
                    catch (InvalidInput ex){
                        JOptionPane.showMessageDialog(null,ex.getMessage());
                    }
                    catch (SQLException | ClassNotFoundException ignore) {}
                }
            });
        }
    }

    void homePart(String product){
        JLabel lblEnergyDegree=new JLabel("Energy Degree");
        JLabel lblTvQuality=new JLabel("Quality");
        JLabel lblSize=new JLabel("Size");
        JLabel lblCapacity=new JLabel("Capacity");
        JLabel lblModel=new JLabel("Model");
        JLabel lblhasFreezer=new JLabel("Has Freezer");
        JLabel lblGasFlameNumber=new JLabel("Flame Number");
        JLabel lblHasGasOven=new JLabel("Gas Oven");

        JTextField txtEnergyDegree=new JTextField(15);
        JTextField txtTvQuality=new JTextField(15);
        JTextField txtSize=new JTextField(15);
        JTextField txtCapacity=new JTextField(15);
        JTextField txtModel=new JTextField(15);
        JRadioButton btnHomeYes=new JRadioButton("Yes");
        JRadioButton btnHomeNo=new JRadioButton("No");
        JTextField txtGasFlameNumber=new JTextField(15);

        this.add(lblEnergyDegree);
        this.add(txtEnergyDegree);

        sp.putConstraint(SpringLayout.WEST,lblEnergyDegree,70,SpringLayout.WEST,container);
        sp.putConstraint(SpringLayout.NORTH,lblEnergyDegree,40,SpringLayout.NORTH,txtDescription);
        sp.putConstraint(SpringLayout.WEST,txtEnergyDegree,20,SpringLayout.WEST,container);
        sp.putConstraint(SpringLayout.NORTH,txtEnergyDegree,20,SpringLayout.NORTH,lblEnergyDegree);

        if(product.equals("Television")){
            this.add(lblTvQuality);
            this.add(txtTvQuality);
            this.add(lblSize);
            this.add(txtSize);

            sp.putConstraint(SpringLayout.WEST,lblTvQuality,450,SpringLayout.WEST,container);
            sp.putConstraint(SpringLayout.NORTH,lblTvQuality,40,SpringLayout.NORTH,btnYes);
            sp.putConstraint(SpringLayout.WEST,txtTvQuality,400,SpringLayout.WEST,container);
            sp.putConstraint(SpringLayout.NORTH,txtTvQuality,20,SpringLayout.NORTH,lblTvQuality);

            sp.putConstraint(SpringLayout.WEST,lblSize,70,SpringLayout.WEST,container);
            sp.putConstraint(SpringLayout.NORTH,lblSize,40,SpringLayout.NORTH,txtEnergyDegree);
            sp.putConstraint(SpringLayout.WEST,txtSize,20,SpringLayout.WEST,container);
            sp.putConstraint(SpringLayout.NORTH,txtSize,20,SpringLayout.NORTH,lblSize);

            sp.putConstraint(SpringLayout.WEST,btnSave,150,SpringLayout.WEST,container);
            sp.putConstraint(SpringLayout.NORTH,btnSave,60,SpringLayout.NORTH,txtSize);
            sp.putConstraint(SpringLayout.WEST,btnCancel,300,SpringLayout.WEST,container);
            sp.putConstraint(SpringLayout.NORTH,btnCancel,60,SpringLayout.NORTH,txtSize);

            btnSave.addActionListener(e -> {
                try {
                    productCheck();
                    homeCheck(txtEnergyDegree.getText());

                    if(txtTvQuality.getText().isBlank()){
                        throw new InvalidInput("Enter Quality!!");
                    }

                    if(txtSize.getText().isBlank()){
                        throw new InvalidInput("Enter Size!!");
                    }

                    else{
                        try {
                            Double.valueOf(txtSize.getText());
                        }
                        catch (NumberFormatException ex){
                            throw new InvalidInput("Enter Size Correctly!!");
                        }
                    }

                    Television t=new Television(txtName.getText(),txtBrand.getText(),Double.valueOf(txtPrice.getText()),
                            Integer.valueOf(txtNumber.getText()),seller,txtDescription.getText(),
                            Double.valueOf(txtEnergyDegree.getText()),warrantyCheck,
                            txtTvQuality.getText(),Double.valueOf(txtSize.getText()));

                    admin.setAddRequestProduct(t);
                    WriteInfo.saveRequest(t,"tv","add",null);
                    WriteInfo.saveTv(t,false);

                    JOptionPane.showMessageDialog(null,"Product Request Added Successfully");
                    dispose();
                    preFrame.setVisible(true);
                }
                catch (InvalidInput ex){
                    JOptionPane.showMessageDialog(null,ex.getMessage());
                }
                catch (SQLException | ClassNotFoundException ignore) {}
            });
        }

        else if(product.equals("Refrigerator")){
            this.add(lblCapacity);
            this.add(txtCapacity);
            this.add(lblModel);
            this.add(txtModel);
            this.add(lblhasFreezer);

            ButtonGroup group=new ButtonGroup();
            group.add(btnHomeYes);
            group.add(btnHomeNo);
            btnHomeNo.setSelected(true);
            hasFreeze=false;

            this.add(btnHomeYes);
            this.add(btnHomeNo);

            btnHomeYes.addActionListener(e ->{
                hasFreeze=true;
            });

            btnHomeNo.addActionListener(e ->{
                hasFreeze=false;
            });

            sp.putConstraint(SpringLayout.WEST,lblCapacity,450,SpringLayout.WEST,container);
            sp.putConstraint(SpringLayout.NORTH,lblCapacity,40,SpringLayout.NORTH,btnNo);
            sp.putConstraint(SpringLayout.WEST,txtCapacity,400,SpringLayout.WEST,container);
            sp.putConstraint(SpringLayout.NORTH,txtCapacity,20,SpringLayout.NORTH,lblCapacity);

            sp.putConstraint(SpringLayout.WEST,lblModel,70,SpringLayout.WEST,container);
            sp.putConstraint(SpringLayout.NORTH,lblModel,40,SpringLayout.NORTH,txtEnergyDegree);
            sp.putConstraint(SpringLayout.WEST,txtModel,20,SpringLayout.WEST,container);
            sp.putConstraint(SpringLayout.NORTH,txtModel,20,SpringLayout.NORTH,lblModel);

            sp.putConstraint(SpringLayout.WEST,lblhasFreezer,450,SpringLayout.WEST,container);
            sp.putConstraint(SpringLayout.NORTH,lblhasFreezer,40,SpringLayout.NORTH,txtCapacity);
            sp.putConstraint(SpringLayout.WEST,btnHomeYes,420,SpringLayout.WEST,container);
            sp.putConstraint(SpringLayout.NORTH,btnHomeYes,20,SpringLayout.NORTH,lblhasFreezer);
            sp.putConstraint(SpringLayout.WEST,btnHomeNo,500,SpringLayout.WEST,container);
            sp.putConstraint(SpringLayout.NORTH,btnHomeNo,20,SpringLayout.NORTH,lblhasFreezer);

            sp.putConstraint(SpringLayout.WEST,btnSave,150,SpringLayout.WEST,container);
            sp.putConstraint(SpringLayout.NORTH,btnSave,60,SpringLayout.NORTH,txtModel);
            sp.putConstraint(SpringLayout.WEST,btnCancel,300,SpringLayout.WEST,container);
            sp.putConstraint(SpringLayout.NORTH,btnCancel,60,SpringLayout.NORTH,txtModel);

            btnSave.addActionListener(e -> {
                try {
                    productCheck();
                    homeCheck(txtEnergyDegree.getText());

                    if(txtCapacity.getText().isBlank()){
                        throw new InvalidInput("Enter Capacity!!");
                    }

                    else{
                        try {
                            Double.valueOf(txtCapacity.getText());
                        }
                        catch (NumberFormatException ex){
                            throw new InvalidInput("Enter Capacity Correctly!!");
                        }
                    }

                    if(txtModel.getText().isBlank()){
                        throw new InvalidInput("Enter Model!!");
                    }

                    Refrigerator r=new Refrigerator(txtName.getText(),txtBrand.getText(),Double.valueOf(txtPrice.getText()),
                            Integer.valueOf(txtNumber.getText()),seller,txtDescription.getText(),
                            Double.valueOf(txtEnergyDegree.getText()),warrantyCheck,
                            Double.valueOf(txtCapacity.getText()),txtModel.getText(),hasFreeze);

                    admin.setAddRequestProduct(r);
                    WriteInfo.saveRequest(r,"refrigerator","add",null);
                    WriteInfo.saveRefrigerator(r,false);

                    JOptionPane.showMessageDialog(null,"Product Request Added Successfully");
                    dispose();
                    preFrame.setVisible(true);
                }
                catch (InvalidInput ex){
                    JOptionPane.showMessageDialog(null,ex.getMessage());
                }
                catch (SQLException | ClassNotFoundException ignore) {}
            });
        }

        else{
            this.add(lblGasFlameNumber);
            this.add(txtGasFlameNumber);
            this.add(lblModel);
            this.add(txtModel);
            this.add(lblHasGasOven);

            ButtonGroup group=new ButtonGroup();
            group.add(btnHomeYes);
            group.add(btnHomeNo);
            btnHomeNo.setSelected(true);
            hasGasOven=false;

            this.add(btnHomeYes);
            this.add(btnHomeNo);

            btnHomeYes.addActionListener(e ->{
                hasGasOven=true;
            });

            btnHomeNo.addActionListener(e ->{
                hasGasOven=false;
            });

            sp.putConstraint(SpringLayout.WEST,lblGasFlameNumber,450,SpringLayout.WEST,container);
            sp.putConstraint(SpringLayout.NORTH,lblGasFlameNumber,40,SpringLayout.NORTH,btnNo);
            sp.putConstraint(SpringLayout.WEST,txtGasFlameNumber,400,SpringLayout.WEST,container);
            sp.putConstraint(SpringLayout.NORTH,txtGasFlameNumber,20,SpringLayout.NORTH,lblGasFlameNumber);

            sp.putConstraint(SpringLayout.WEST,lblModel,70,SpringLayout.WEST,container);
            sp.putConstraint(SpringLayout.NORTH,lblModel,40,SpringLayout.NORTH,txtEnergyDegree);
            sp.putConstraint(SpringLayout.WEST,txtModel,20,SpringLayout.WEST,container);
            sp.putConstraint(SpringLayout.NORTH,txtModel,20,SpringLayout.NORTH,lblModel);

            sp.putConstraint(SpringLayout.WEST,lblHasGasOven,450,SpringLayout.WEST,container);
            sp.putConstraint(SpringLayout.NORTH,lblHasGasOven,40,SpringLayout.NORTH,txtGasFlameNumber);
            sp.putConstraint(SpringLayout.WEST,btnHomeYes,420,SpringLayout.WEST,container);
            sp.putConstraint(SpringLayout.NORTH,btnHomeYes,20,SpringLayout.NORTH,lblHasGasOven);
            sp.putConstraint(SpringLayout.WEST,btnHomeNo,500,SpringLayout.WEST,container);
            sp.putConstraint(SpringLayout.NORTH,btnHomeNo,20,SpringLayout.NORTH,lblHasGasOven);

            sp.putConstraint(SpringLayout.WEST,btnSave,150,SpringLayout.WEST,container);
            sp.putConstraint(SpringLayout.NORTH,btnSave,60,SpringLayout.NORTH,txtModel);
            sp.putConstraint(SpringLayout.WEST,btnCancel,300,SpringLayout.WEST,container);
            sp.putConstraint(SpringLayout.NORTH,btnCancel,60,SpringLayout.NORTH,txtModel);

            btnSave.addActionListener(e ->{
                try {
                    productCheck();
                    homeCheck(txtEnergyDegree.getText());

                    if(txtGasFlameNumber.getText().isBlank()){
                        throw new InvalidInput("Enter Gas Flame Number!!");
                    }

                    else{
                        try {
                            Double.valueOf(txtCapacity.getText());
                        }
                        catch (NumberFormatException ex){
                            throw new InvalidInput("Enter Number Correctly!!");
                        }
                    }

                    if(txtModel.getText().isBlank()){
                        throw new InvalidInput("Enter Model!!");
                    }

                    GasStove g=new GasStove(txtName.getText(),txtBrand.getText(),Double.valueOf(txtPrice.getText()),
                            Integer.valueOf(txtNumber.getText()),seller,txtDescription.getText(),
                            Double.valueOf(txtEnergyDegree.getText()),warrantyCheck,
                            Integer.valueOf(txtGasFlameNumber.getText()),txtModel.getText(),hasGasOven);

                    admin.setAddRequestProduct(g);
                    WriteInfo.saveGasStove(g,false);
                    WriteInfo.saveRequest(g,"gasStove","add",null);

                    JOptionPane.showMessageDialog(null,"Product Request Added Successfully");
                    dispose();
                    preFrame.setVisible(true);
                }
                catch (InvalidInput ex){
                    JOptionPane.showMessageDialog(null,ex.getMessage());
                }
                catch (SQLException | ClassNotFoundException ignore) {}
            });
        }
    }

    void foodPart(){
        JLabel lblProductionDate=new JLabel("Production Date");
        JLabel lblExpiryDate=new JLabel("Expiry Date");

        JTextField txtProductionDate=new JTextField(15);
        JTextField txtExpiryDate=new JTextField(15);

        this.add(lblProductionDate);
        this.add(txtProductionDate);
        this.add(lblExpiryDate);
        this.add(txtExpiryDate);

        sp.putConstraint(SpringLayout.WEST,lblProductionDate,70,SpringLayout.WEST,container);
        sp.putConstraint(SpringLayout.NORTH,lblProductionDate,40,SpringLayout.NORTH,txtDescription);
        sp.putConstraint(SpringLayout.WEST,txtProductionDate,20,SpringLayout.WEST,container);
        sp.putConstraint(SpringLayout.NORTH,txtProductionDate,20,SpringLayout.NORTH,lblProductionDate);

        sp.putConstraint(SpringLayout.WEST,lblExpiryDate,450,SpringLayout.WEST,container);
        sp.putConstraint(SpringLayout.NORTH,lblExpiryDate,40,SpringLayout.NORTH,txtDescription);
        sp.putConstraint(SpringLayout.WEST,txtExpiryDate,400,SpringLayout.WEST,container);
        sp.putConstraint(SpringLayout.NORTH,txtExpiryDate,20,SpringLayout.NORTH,lblExpiryDate);

        sp.putConstraint(SpringLayout.WEST,btnSave,150,SpringLayout.WEST,container);
        sp.putConstraint(SpringLayout.NORTH,btnSave,60,SpringLayout.NORTH,txtProductionDate);
        sp.putConstraint(SpringLayout.WEST,btnCancel,300,SpringLayout.WEST,container);
        sp.putConstraint(SpringLayout.NORTH,btnCancel,60,SpringLayout.NORTH,txtProductionDate);

        btnSave.addActionListener(e ->{
            try {
                productCheck();

                if(txtProductionDate.getText().isBlank()){
                    throw new InvalidInput("Enter Production Date!!");
                }

                if(txtExpiryDate.getText().isBlank()){
                    throw new InvalidInput("Enter Expiry Date!!");
                }

                Food f=new Food(txtName.getText(),txtBrand.getText(),Double.valueOf(txtPrice.getText()),
                        Integer.valueOf(txtNumber.getText()),seller,txtDescription.getText(),
                        txtProductionDate.getText(),txtExpiryDate.getText());

                admin.setAddRequestProduct(f);
                WriteInfo.saveRequest(f,"food","add",null);
                WriteInfo.saveFood(f,false);

                JOptionPane.showMessageDialog(null,"Product Request Added Successfully");
                dispose();
                preFrame.setVisible(true);
            }
            catch (InvalidInput ex){
                JOptionPane.showMessageDialog(null,ex.getMessage());
            }
            catch (SQLException | ClassNotFoundException ignore) {}
        });
    }

    void productCheck(){
        if (txtName.getText().isBlank()){
            throw new InvalidInput("Enter Name!!");
        }

        if(txtBrand.getText().isBlank()){
            throw new InvalidInput("Enter Brand!!");
        }

        if(txtDescription.getText().isBlank()){
            throw new InvalidInput("Enter Description!!");
        }

        if(txtNumber.getText().isBlank()){
            throw new InvalidInput("Enter Number");
        }

        else {
            try {
                Integer.valueOf(txtNumber.getText());
            }
            catch (NumberFormatException ex){
                throw new InvalidInput("Enter Numbers Correctly!!");
            }
        }

        if(txtPrice.getText().isBlank()) {
            throw new InvalidInput("Enter Price");
        }
        else{
            try {
                Double.valueOf(txtPrice.getText());
            }
            catch (NumberFormatException ex){
                throw new InvalidInput("Enter Price Correctly");
            }
        }
    }

    void digitalCheck(String memory,String ram,String op,String weight,String length,String width){
        if(memory.isBlank()){
            throw new InvalidInput("Enter Memory!!");
        }

        else{
            try {
                Integer.valueOf(memory);
            }
            catch (NumberFormatException ex){
                throw new InvalidInput("Enter Memory Correctly!!");
            }
        }

        if(ram.isBlank()){
            throw new InvalidInput("Enter Ram!!");
        }

        else{
            try {
                Integer.valueOf(ram);
            }
            catch (NumberFormatException ex){
                throw new InvalidInput("Enter Ram Correctly!!");
            }
        }

        if(op.isBlank()){
            throw new InvalidInput("Enter Operating System!!");
        }

        if(weight.isBlank()){
            throw new InvalidInput("Enter Weight!!");
        }

        else{
            try {
                Double.valueOf(weight);
            }
            catch (NumberFormatException ex){
                throw new InvalidInput("Enter Weight Correctly!!");
            }
        }

        if(length.isBlank()){
            throw new InvalidInput("Enter Length!!");
        }

        else{
            try {
                Integer.valueOf(length);
            }
            catch (NumberFormatException ex){
                throw new InvalidInput("Enter Length Correctly!!");
            }
        }

        if(width.isBlank()){
            throw new InvalidInput("Enter Width!!");
        }

        else{
            try {
                Integer.valueOf(width);
            }
            catch (NumberFormatException ex){
                throw new InvalidInput("Enter Width Correctly!!");
            }
        }
    }

    void clothingCheck(String country,String material,String size){
        if(country.isBlank()){
            throw new InvalidInput("Enter Country!!");
        }

        if(material.isBlank()){
            throw new InvalidInput("Enter Material!!");
        }

        if(size.isBlank()){
            throw new InvalidInput("Enter Size!!");
        }

        else{
            try {
                Integer.valueOf(size);
            }
            catch (NumberFormatException ex){
                throw new InvalidInput("Enter Size Correctly");
            }
        }
    }

    void homeCheck(String energyDegree){
        if(energyDegree.isBlank()){
            throw new InvalidInput("Enter Energy Degree!!");
        }
        else{
            try {
                Double.valueOf(energyDegree);
            }
            catch (NumberFormatException ex){
                throw new InvalidInput("Enter Energy Degree Correctly!!");
            }
        }
    }
}
