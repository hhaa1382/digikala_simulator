package Classes.Procces;

import java.sql.SQLException;
import java.util.ArrayList;

import Classes.Data.WriteInfo;
import Classes.Products.*;
import Classes.User.*;

import javax.swing.JOptionPane;

public class ChangeProduct{
    Seller seller;
    Goods good;

    public ChangeProduct(Seller seller, Goods good) throws SQLException, ClassNotFoundException {
        Admin adminSet = Admin.getAdmin();
        this.seller = seller;
        this.good = good;
        ArrayList<String> values = values(good);

        String ans = (String) JOptionPane.showInputDialog(null, "Choose a Info :", "Info",
                JOptionPane.DEFAULT_OPTION, null, values.toArray(), null);

        if (good instanceof Mobile) {
            Mobile hold = (Mobile) good;

            Mobile m = new Mobile(hold.getName(), hold.getBrand(), hold.getPrice(),
                    hold.getNumber(), hold.getSeller(), hold.getDescription(),
                    hold.getMemoryCapacity(), hold.getRam(), hold.getOperatingSystem(),
                    hold.getWeight(), hold.getLength(), hold.getWidth(), hold.getWarranty(),
                    hold.getNumberSIM(), hold.getCameraQuality());

            m.setCode(hold.getCode());

            while (true) {
                if (ans == null) {
                    adminSet.setChangeRequest(m, good);
                    WriteInfo.saveMobile(m,true);
                    JOptionPane.showMessageDialog(null, "Your request will reviewed by admin");
                    break;
                }

                else if (ans.equals(values.get(0))) {
                    String name = JOptionPane.showInputDialog("Enter name :");

                    if(name!=null)
                        m.setName(name);
                }

                else if (ans.equals(values.get(1))) {
                    String brand = JOptionPane.showInputDialog("Enter brand :");

                    if(brand!=null)
                        m.setBrand(brand);
                }

                else if (ans.equals(values.get(2))) {
                    String tempPrice = JOptionPane.showInputDialog("Enter Price :");

                    if(tempPrice!=null) {
                        try {
                            double price = Double.valueOf(tempPrice);
                            m.setPrice(price);
                        }
                        catch (NumberFormatException ex) {
                            JOptionPane.showInputDialog(null, "Enter a Number!!");
                        }
                    }
                }

                else if (ans.equals(values.get(3))) {
                    String tempNumber = JOptionPane.showInputDialog("Enter number :");

                    if(tempNumber!=null) {
                        try {
                            int number = Integer.valueOf(tempNumber);
                            m.setNumber(number);
                        }
                        catch (NumberFormatException ex) {
                            JOptionPane.showInputDialog(null, "Enter a Number!!");
                        }
                    }
                }

                else if (ans.equals(values.get(4))) {
                    String description = JOptionPane.showInputDialog("Enter description :");

                    if(description!=null)
                        m.setDescription(description);
                }

                else if (ans.equals(values.get(5))) {
                    String tempMemory = JOptionPane.showInputDialog("Enter memory :");

                    if(tempMemory!=null) {
                        try {
                            int memory = Integer.valueOf(tempMemory);
                            m.setMemoryCapacity(memory);
                        }
                        catch (NumberFormatException ex) {
                            JOptionPane.showInputDialog(null, "Enter a Number!!");
                        }
                    }
                }

                else if (ans.equals(values.get(6))) {
                    String tempRam = JOptionPane.showInputDialog("Enter ram :");

                    if(tempRam!=null) {
                        try {
                            int ram = Integer.valueOf(tempRam);
                            m.setRam(ram);
                        }
                        catch (NumberFormatException ex) {
                            JOptionPane.showInputDialog(null, "Enter a Number!!");
                        }
                    }
                }

                else if (ans.equals(values.get(7))) {
                    String operatingSystem = JOptionPane.showInputDialog("Enter operating system :");

                    if(operatingSystem!=null)
                        m.setOperatingSystem(operatingSystem);
                }

                else if (ans.equals(values.get(8))) {
                    String tempWeight = JOptionPane.showInputDialog("Enter weight :");

                    if(tempWeight!=null) {
                        try {
                            double weight = Double.valueOf(tempWeight);
                            m.setWeight(weight);
                        }
                        catch (NumberFormatException ex) {
                            JOptionPane.showInputDialog(null, "Enter a Number!!");
                        }
                    }
                }

                else if (ans.equals(values.get(9))) {
                    String tempLength = JOptionPane.showInputDialog("Enter length :");

                    if(tempLength!=null) {
                        try {
                            int length = Integer.valueOf(tempLength);
                            m.setLength(length);
                        }
                        catch (NumberFormatException ex) {
                            JOptionPane.showInputDialog(null, "Enter a Number!!");
                        }
                    }
                }

                else if (ans.equals(values.get(10))) {
                    String tempWidth = JOptionPane.showInputDialog("Enter width :");

                    if(tempWidth!=null) {
                        try {
                            int width = Integer.valueOf(tempWidth);
                            m.setWidth(width);
                        }
                        catch (NumberFormatException ex) {
                            JOptionPane.showInputDialog(null, "Enter a Number!!");
                        }
                    }
                }

                else if (ans.equals(values.get(11))) {
                    String tempWarranty = (String) JOptionPane.showInputDialog(null, "Has warranty",
                            "Warranty", JOptionPane.OK_CANCEL_OPTION, null, new String[]{"True", "False"}, null);

                    if (tempWarranty != null) {
                        if (tempWarranty.equals("True")) {
                            m.setWarranty(true);
                        }
                        else {
                            m.setWarranty(false);
                        }
                    }
                }

                else if (ans.equals(values.get(12))) {
                    String tempNumber = JOptionPane.showInputDialog("Enter sim number :");

                    if(tempNumber!=null) {
                        try {
                            int number = Integer.valueOf(tempNumber);
                            m.setNumberSIM(number);
                        }
                        catch (NumberFormatException ex) {
                            JOptionPane.showInputDialog(null, "Enter a Number!!");
                        }
                    }
                }

                else if (ans.equals(values.get(13))) {
                    String quality = JOptionPane.showInputDialog("Enter camera quality :");

                    if(quality!=null)
                        m.setCameraQuality(quality);
                }

                ans = (String) JOptionPane.showInputDialog(null, "Choose a Info :", "Info",
                        JOptionPane.PLAIN_MESSAGE, null, values.toArray(), null);
            }
        }

        else if (good instanceof Laptop) {
            Laptop hold = (Laptop) good;

            Laptop l = new Laptop(hold.getName(), hold.getBrand(), hold.getPrice()
                    , hold.getNumber(), hold.getSeller(), hold.getDescription()
                    , hold.getMemoryCapacity(), hold.getRam(), hold.getOperatingSystem(), hold.getWeight()
                    , hold.getLength(), hold.getWidth(), hold.getWarranty(), hold.getCpuModel(), hold.getGamig());

            l.setCode(hold.getCode());

            while (true) {
                if (ans == null) {
                    adminSet.setChangeRequest(l, good);
                    WriteInfo.saveLaptop(l,true);
                    JOptionPane.showMessageDialog(null, "Your request will reviewed by admin");
                    break;
                }

                else if (ans.equals(values.get(0))) {
                    String name = JOptionPane.showInputDialog("Enter name :");

                    if(name!=null)
                        l.setName(name);
                }

                else if (ans.equals(values.get(1))) {
                    String brand = JOptionPane.showInputDialog("Enter brand :");

                    if(brand!=null)
                        l.setBrand(brand);
                }

                else if (ans.equals(values.get(2))) {
                    String tempPrice = JOptionPane.showInputDialog("Enter Price :");

                    if(tempPrice!=null) {
                        try {
                            double price = Double.valueOf(tempPrice);
                            l.setPrice(price);
                        }
                        catch (NumberFormatException ex) {
                            JOptionPane.showInputDialog(null, "Enter a Number!!");
                        }
                    }
                }

                else if (ans.equals(values.get(3))) {
                    String tempNumber = JOptionPane.showInputDialog("Enter number :");

                    if(tempNumber!=null) {
                        try {
                            int number = Integer.valueOf(tempNumber);
                            l.setNumber(number);
                        }
                        catch (NumberFormatException ex) {
                            JOptionPane.showInputDialog(null, "Enter a Number!!");
                        }
                    }
                }

                else if (ans.equals(values.get(4))) {
                    String description = JOptionPane.showInputDialog("Enter description :");

                    if(description!=null)
                        l.setDescription(description);
                }

                else if (ans.equals(values.get(5))) {
                    String tempMemory = JOptionPane.showInputDialog("Enter memory :");

                    if(tempMemory!=null) {
                        try {
                            int memory = Integer.valueOf(tempMemory);
                            l.setMemoryCapacity(memory);
                        }
                        catch (NumberFormatException ex) {
                            JOptionPane.showInputDialog(null, "Enter a Number!!");
                        }
                    }
                }

                else if (ans.equals(values.get(6))) {
                    String tempRam = JOptionPane.showInputDialog("Enter ram :");

                    if(tempRam!=null) {
                        try {
                            int ram = Integer.valueOf(tempRam);
                            l.setRam(ram);
                        }
                        catch (NumberFormatException ex) {
                            JOptionPane.showInputDialog(null, "Enter a Number!!");
                        }
                    }
                }

                else if (ans.equals(values.get(7))) {
                    String operatingSystem = JOptionPane.showInputDialog("Enter operating system :");

                    if(operatingSystem!=null)
                        l.setOperatingSystem(operatingSystem);
                }

                else if (ans.equals(values.get(8))) {
                    String tempWeight = JOptionPane.showInputDialog("Enter weight :");

                    if(tempWeight!=null) {
                        try {
                            double weight = Double.valueOf(tempWeight);
                            l.setWeight(weight);
                        }
                        catch (NumberFormatException ex) {
                            JOptionPane.showInputDialog(null, "Enter a Number!!");
                        }
                    }
                }

                else if (ans.equals(values.get(9))) {
                    String tempLength = JOptionPane.showInputDialog("Enter length :");

                    if(tempLength!=null) {
                        try {
                            int length = Integer.valueOf(tempLength);
                            l.setLength(length);
                        }
                        catch (NumberFormatException ex) {
                            JOptionPane.showInputDialog(null, "Enter a Number!!");
                        }
                    }
                }

                else if (ans.equals(values.get(10))) {
                    String tempWidth = JOptionPane.showInputDialog("Enter width :");

                    if(tempWidth!=null) {
                        try {
                            int width = Integer.valueOf(tempWidth);
                            l.setWidth(width);
                        }
                        catch (NumberFormatException ex) {
                            JOptionPane.showInputDialog(null, "Enter a Number!!");
                        }
                    }
                }

                else if (ans.equals(values.get(11))) {
                    String tempWarranty = (String) JOptionPane.showInputDialog(null, "Has warranty",
                            "Warranty", JOptionPane.OK_CANCEL_OPTION, null, new String[]{"True", "False"}, null);

                    if (tempWarranty != null) {
                        if (tempWarranty.equals("True")) {
                            l.setWarranty(true);
                        }
                        else {
                            l.setWarranty(false);
                        }
                    }
                }

                else if (ans.equals(values.get(12))) {
                    String model = JOptionPane.showInputDialog("Enter cpu model :");

                    if(model!=null)
                        l.setCpuModel(model);
                }

                else if (ans.equals(values.get(13))) {
                    String tempGaming = (String) JOptionPane.showInputDialog(null, "Is gaming",
                            "Gaming", JOptionPane.OK_CANCEL_OPTION, null, new String[]{"True", "False"}, null);

                    if (tempGaming != null) {
                        if (tempGaming.equals("True")) {
                            l.setGamig(true);
                        }
                        else {
                            l.setGamig(false);
                        }
                    }
                }

                ans = (String) JOptionPane.showInputDialog(null, "Choose a Info :", "Info",
                        JOptionPane.PLAIN_MESSAGE, null, values.toArray(), null);
            }
        }

        else if(good instanceof Clothes){
            Clothes hold=(Clothes)good;

            Clothes c=new Clothes(hold.getName(), hold.getBrand(), hold.getPrice()
                , hold.getNumber(), hold.getSeller(), hold.getDescription()
                , hold.getCountry(), hold.getClothingMaterial(), hold.getSize(), hold.getType());

            c.setCode(hold.getCode());

            while(true) {
                if (ans == null) {
                    adminSet.setChangeRequest(c, good);
                    WriteInfo.saveClothe(c,true);
                    JOptionPane.showMessageDialog(null, "Your request will reviewed by admin");
                    break;
                }

                else if (ans.equals(values.get(0))) {
                    String name = JOptionPane.showInputDialog("Enter name :");

                    if(name!=null)
                        c.setName(name);
                }

                else if (ans.equals(values.get(1))) {
                    String brand = JOptionPane.showInputDialog("Enter brand :");

                    if(brand!=null)
                        c.setBrand(brand);
                }

                else if (ans.equals(values.get(2))) {
                    String tempPrice = JOptionPane.showInputDialog("Enter Price :");

                    if(tempPrice!=null) {
                        try {
                            double price = Double.valueOf(tempPrice);
                            c.setPrice(price);
                        }
                        catch (NumberFormatException ex) {
                            JOptionPane.showInputDialog(null, "Enter a Number!!");
                        }
                    }
                }

                else if (ans.equals(values.get(3))) {
                    String tempNumber = JOptionPane.showInputDialog("Enter number :");

                    if(tempNumber!=null) {
                        try {
                            int number = Integer.valueOf(tempNumber);
                            c.setNumber(number);
                        }
                        catch (NumberFormatException ex) {
                            JOptionPane.showInputDialog(null, "Enter a Number!!");
                        }
                    }
                }

                else if (ans.equals(values.get(4))) {
                    String description = JOptionPane.showInputDialog("Enter description :");

                    if(description!=null)
                        c.setDescription(description);
                }

                else if (ans.equals(values.get(5))) {
                    String country = JOptionPane.showInputDialog("Enter country :");

                    if(country!=null)
                        c.setCountry(country);
                }

                else if (ans.equals(values.get(6))) {
                    String material = JOptionPane.showInputDialog("Enter material :");

                    if(material!=null)
                        c.setClothingMaterial(material);
                }

                else if (ans.equals(values.get(7))) {
                    String tempSize = JOptionPane.showInputDialog("Enter size :");

                    if(tempSize!=null) {
                        try {
                            int size = Integer.valueOf(tempSize);
                            c.setSize(size);
                        }
                        catch (NumberFormatException ex) {
                            JOptionPane.showInputDialog(null, "Enter a Number!!");
                        }
                    }
                }

                else if (ans.equals(values.get(8))) {
                    String[] clotheTypes={"T_shirt","Pants","Shirt","Suit","Socks"};
                    String tempType = (String) JOptionPane.showInputDialog(null, "Types",
                            "Clothe", JOptionPane.OK_CANCEL_OPTION, null,clotheTypes, null);

                    if (tempType != null) {
                        c.setType(tempType);
                    }
                }

                ans = (String) JOptionPane.showInputDialog(null, "Choose a Info :", "Info",
                    JOptionPane.PLAIN_MESSAGE, null, values.toArray(), null);
            }
        }

        else if(good instanceof Shoes){
            Shoes hold=(Shoes)good;

            Shoes s=new Shoes(hold.getName(), hold.getBrand(), hold.getPrice()
                , hold.getNumber(), hold.getSeller(), hold.getDescription()
                , hold.getCountry(), hold.getClothingMaterial(), hold.getSize(), hold.getType());

            s.setCode(hold.getCode());

            while(true) {
                if (ans == null) {
                    adminSet.setChangeRequest(s, good);
                    WriteInfo.saveShoe(s,true);
                    JOptionPane.showMessageDialog(null, "Your request will reviewed by admin");
                    break;
                }

                else if (ans.equals(values.get(0))) {
                    String name = JOptionPane.showInputDialog("Enter name :");

                    if(name!=null)
                        s.setName(name);
                }

                else if (ans.equals(values.get(1))) {
                    String brand = JOptionPane.showInputDialog("Enter brand :");

                    if(brand!=null)
                        s.setBrand(brand);
                }

                else if (ans.equals(values.get(2))) {
                    String tempPrice = JOptionPane.showInputDialog("Enter Price :");

                    if(tempPrice!=null) {
                        try {
                            double price = Double.valueOf(tempPrice);
                            s.setPrice(price);
                        }
                        catch (NumberFormatException ex) {
                            JOptionPane.showInputDialog(null, "Enter a Number!!");
                        }
                    }
                }

                else if (ans.equals(values.get(3))) {
                    String tempNumber = JOptionPane.showInputDialog("Enter number :");

                    if(tempNumber!=null) {
                        try {
                            int number = Integer.valueOf(tempNumber);
                            s.setNumber(number);
                        }
                        catch (NumberFormatException ex) {
                            JOptionPane.showInputDialog(null, "Enter a Number!!");
                        }
                    }
                }

                else if (ans.equals(values.get(4))) {
                    String description = JOptionPane.showInputDialog("Enter description :");

                    if(description!=null)
                        s.setDescription(description);
                }

                else if (ans.equals(values.get(5))) {
                    String country = JOptionPane.showInputDialog("Enter country :");

                    if(country!=null)
                        s.setCountry(country);
                }

                else if (ans.equals(values.get(6))) {
                    String material = JOptionPane.showInputDialog("Enter material :");

                    if(material!=null)
                        s.setClothingMaterial(material);
                }

                else if (ans.equals(values.get(7))) {
                    String tempSize = JOptionPane.showInputDialog("Enter size :");

                    if(tempSize!=null) {
                        try {
                            int size = Integer.valueOf(tempSize);
                            s.setSize(size);
                        }
                        catch (NumberFormatException ex) {
                            JOptionPane.showInputDialog(null, "Enter a Number!!");
                        }
                    }
                }

                else if (ans.equals(values.get(8))) {
                    String[] clotheTypes={"Formal","Boot","Sports","Sandal"};
                    String tempType = (String) JOptionPane.showInputDialog(null, "Types",
                            "Shoe", JOptionPane.OK_CANCEL_OPTION, null,clotheTypes, null);

                    if (tempType != null) {
                        s.setType(tempType);
                    }
                }

                ans = (String) JOptionPane.showInputDialog(null, "Choose a Info :", "Info",
                        JOptionPane.PLAIN_MESSAGE, null, values.toArray(), null);
            }
        }

        else if(good instanceof Television){
            Television hold=(Television)good;

            Television t=new Television(hold.getName(), hold.getBrand(), hold.getPrice()
                , hold.getNumber(), hold.getSeller(), hold.getDescription()
                , hold.getEnergyDegree(), hold.getWarranty(), hold.getQuality(),hold.getSize());

            t.setCode(hold.getCode());

            while(true) {
                if (ans == null) {
                    adminSet.setChangeRequest(t, good);
                    WriteInfo.saveTv(t,true);
                    JOptionPane.showMessageDialog(null, "Your request will reviewed by admin");
                    break;
                }

                else if (ans.equals(values.get(0))) {
                    String name = JOptionPane.showInputDialog("Enter name :");

                    if(name!=null)
                        t.setName(name);
                }

                else if (ans.equals(values.get(1))) {
                    String brand = JOptionPane.showInputDialog("Enter brand :");

                    if(brand!=null)
                        t.setBrand(brand);
                }

                else if (ans.equals(values.get(2))) {
                    String tempPrice = JOptionPane.showInputDialog("Enter Price :");

                    if(tempPrice!=null) {
                        try {
                            double price = Double.valueOf(tempPrice);
                            t.setPrice(price);
                        }
                        catch (NumberFormatException ex) {
                            JOptionPane.showInputDialog(null, "Enter a Number!!");
                        }
                    }
                }

                else if (ans.equals(values.get(3))) {
                    String tempNumber = JOptionPane.showInputDialog("Enter number :");

                    if(tempNumber!=null) {
                        try {
                            int number = Integer.valueOf(tempNumber);
                            t.setNumber(number);
                        }
                        catch (NumberFormatException ex) {
                            JOptionPane.showInputDialog(null, "Enter a Number!!");
                        }
                    }
                }

                else if (ans.equals(values.get(4))) {
                    String description = JOptionPane.showInputDialog("Enter description :");

                    if(description!=null)
                        t.setDescription(description);
                }

                else if (ans.equals(values.get(5))) {
                    String tempEnergy = JOptionPane.showInputDialog("Enter energy degree :");

                    if(tempEnergy!=null) {
                        try {
                            double energy = Double.valueOf(tempEnergy);
                            t.setEnergyDegree(energy);
                        }
                        catch (NumberFormatException ex) {
                            JOptionPane.showInputDialog(null, "Enter a Number!!");
                        }
                    }
                }

                else if (ans.equals(values.get(6))) {
                    String tempWarranty = (String) JOptionPane.showInputDialog(null, "Has warranty",
                            "Warranty", JOptionPane.OK_CANCEL_OPTION, null, new String[]{"True", "False"}, null);

                    if (tempWarranty != null) {
                        if (tempWarranty.equals("True")) {
                            t.setWarranty(true);
                        } else {
                            t.setWarranty(false);
                        }
                    }
                }

                else if (ans.equals(values.get(7))) {
                    String quality = JOptionPane.showInputDialog("Enter quality :");

                    if(quality!=null)
                        t.setQuality(quality);
                }

                else if (ans.equals(values.get(8))) {
                    String tempSize = JOptionPane.showInputDialog("Enter size :");

                    if(tempSize!=null) {
                        try {
                            double size = Double.valueOf(tempSize);
                            t.setSize(size);
                        } catch (NumberFormatException ex) {
                            JOptionPane.showInputDialog(null, "Enter a Number!!");
                        }
                    }
                }

                ans = (String) JOptionPane.showInputDialog(null, "Choose a Info :", "Info",
                    JOptionPane.PLAIN_MESSAGE, null, values.toArray(), null);
            }
        }

        else if(good instanceof Refrigerator){
            Refrigerator hold=(Refrigerator)good;

            Refrigerator r=new Refrigerator(hold.getName(), hold.getBrand(), hold.getPrice()
                , hold.getNumber(), hold.getSeller(), hold.getDescription()
                , hold.getEnergyDegree(), hold.getWarranty(), hold.getCapacity(), hold.getModel(), hold.getFreezer());

            r.setCode(hold.getCode());

            while(true) {
                if (ans == null) {
                    adminSet.setChangeRequest(r, good);
                    WriteInfo.saveRefrigerator(r,true);
                    JOptionPane.showMessageDialog(null, "Your request will reviewed by admin");
                    break;
                }

                else if (ans.equals(values.get(0))) {
                    String name = JOptionPane.showInputDialog("Enter name :");

                    if(name!=null)
                        r.setName(name);
                }

                else if (ans.equals(values.get(1))) {
                    String brand = JOptionPane.showInputDialog("Enter brand :");

                    if(brand!=null)
                        r.setBrand(brand);
                }

                else if (ans.equals(values.get(2))) {
                    String tempPrice = JOptionPane.showInputDialog("Enter Price :");

                    if(tempPrice!=null) {
                        try {
                            double price = Double.valueOf(tempPrice);
                            r.setPrice(price);
                        }
                        catch (NumberFormatException ex) {
                            JOptionPane.showInputDialog(null, "Enter a Number!!");
                        }
                    }
                }

                else if (ans.equals(values.get(3))) {
                    String tempNumber = JOptionPane.showInputDialog("Enter number :");

                    if(tempNumber!=null) {
                        try {
                            int number = Integer.valueOf(tempNumber);
                            r.setNumber(number);
                        }
                        catch (NumberFormatException ex) {
                            JOptionPane.showInputDialog(null, "Enter a Number!!");
                        }
                    }
                }

                else if (ans.equals(values.get(4))) {
                    String description = JOptionPane.showInputDialog("Enter description :");

                    if(description!=null)
                        r.setDescription(description);
                }

                else if (ans.equals(values.get(5))) {
                    String tempEnergy = JOptionPane.showInputDialog("Enter energy degree :");

                    if(tempEnergy!=null) {
                        try {
                            double energy = Double.valueOf(tempEnergy);
                            r.setEnergyDegree(energy);
                        }
                        catch (NumberFormatException ex) {
                            JOptionPane.showInputDialog(null, "Enter a Number!!");
                        }
                    }
                }

                else if (ans.equals(values.get(6))) {
                    String tempWarranty = (String) JOptionPane.showInputDialog(null, "Has warranty",
                            "Warranty", JOptionPane.OK_CANCEL_OPTION, null, new String[]{"True", "False"}, null);

                    if (tempWarranty != null) {
                        if (tempWarranty.equals("True")) {
                            r.setWarranty(true);
                        } else {
                            r.setWarranty(false);
                        }
                    }
                }

                else if (ans.equals(values.get(7))) {
                    String tempCapacity = JOptionPane.showInputDialog("Enter capacity :");

                    if(tempCapacity!=null) {
                        try {
                            double capacity = Double.valueOf(tempCapacity);
                            r.setCapacity(capacity);
                        }
                        catch (NumberFormatException ex) {
                            JOptionPane.showInputDialog(null, "Enter a Number!!");
                        }
                    }
                }

                else if (ans.equals(values.get(8))) {
                    String model = JOptionPane.showInputDialog("Enter model :");

                    if(model!=null){
                        r.setModel(model);
                    }
                }

                else if (ans.equals(values.get(9))) {
                    String tempFreeze = (String) JOptionPane.showInputDialog(null, "Has freeze",
                            "Refrigerator", JOptionPane.OK_CANCEL_OPTION, null, new String[]{"True", "False"}, null);

                    if (tempFreeze != null) {
                        if (tempFreeze.equals("True")) {
                            r.setFreezer(true);
                        }
                        else {
                            r.setFreezer(false);
                        }
                    }
                }

                ans = (String) JOptionPane.showInputDialog(null, "Choose a Info :", "Info",
                        JOptionPane.PLAIN_MESSAGE, null, values.toArray(), null);
            }
        }

        else if(good instanceof GasStove){
            GasStove hold=(GasStove)good;

            GasStove g=new GasStove(hold.getName(), hold.getBrand(), hold.getPrice()
                , hold.getNumber(), hold.getSeller(), hold.getDescription()
                , hold.getEnergyDegree(), hold.getWarranty(), hold.getGasFlame(), hold.getType(), hold.getGasOven());

            g.setCode(hold.getCode());

            while(true) {
                if (ans == null) {
                    adminSet.setChangeRequest(g, good);
                    WriteInfo.saveGasStove(g,true);
                    JOptionPane.showMessageDialog(null, "Your request will reviewed by admin");
                    break;
                }

                else if (ans.equals(values.get(0))) {
                    String name = JOptionPane.showInputDialog("Enter name :");

                    if(name!=null)
                        g.setName(name);
                }

                else if (ans.equals(values.get(1))) {
                    String brand = JOptionPane.showInputDialog("Enter brand :");

                    if(brand!=null)
                        g.setBrand(brand);
                }

                else if (ans.equals(values.get(2))) {
                    String tempPrice = JOptionPane.showInputDialog("Enter Price :");

                    if(tempPrice!=null) {
                        try {
                            double price = Double.valueOf(tempPrice);
                            g.setPrice(price);
                        }
                        catch (NumberFormatException ex) {
                            JOptionPane.showInputDialog(null, "Enter a Number!!");
                        }
                    }
                }

                else if (ans.equals(values.get(3))) {
                    String tempNumber = JOptionPane.showInputDialog("Enter number :");

                    if(tempNumber!=null) {
                        try {
                            int number = Integer.valueOf(tempNumber);
                            g.setNumber(number);
                        }
                        catch (NumberFormatException ex) {
                            JOptionPane.showInputDialog(null, "Enter a Number!!");
                        }
                    }
                }

                else if (ans.equals(values.get(4))) {
                    String description = JOptionPane.showInputDialog("Enter description :");

                    if(description!=null)
                        g.setDescription(description);
                }

                else if (ans.equals(values.get(5))) {
                    String tempEnergy = JOptionPane.showInputDialog("Enter energy degree :");

                    if(tempEnergy!=null) {
                        try {
                            double energy = Double.valueOf(tempEnergy);
                            g.setEnergyDegree(energy);
                        }
                        catch (NumberFormatException ex) {
                            JOptionPane.showInputDialog(null, "Enter a Number!!");
                        }
                    }
                }

                else if (ans.equals(values.get(6))) {
                    String tempWarranty = (String) JOptionPane.showInputDialog(null, "Has warranty",
                            "Warranty", JOptionPane.OK_CANCEL_OPTION, null, new String[]{"True", "False"}, null);

                    if (tempWarranty != null) {
                        if (tempWarranty.equals("True")) {
                            g.setWarranty(true);
                        }
                        else {
                            g.setWarranty(false);
                        }
                    }
                }

                else if (ans.equals(values.get(7))) {
                    String tempFlame = JOptionPane.showInputDialog("Enter flame number :");

                    if(tempFlame!=null) {
                        try {
                            int flameNumber = Integer.valueOf(tempFlame);
                            g.setGasFlame(flameNumber);
                        }
                        catch (NumberFormatException ex) {
                            JOptionPane.showInputDialog(null, "Enter a Number!!");
                        }
                    }
                }

                else if (ans.equals(values.get(8))) {
                    String type = JOptionPane.showInputDialog("Enter type :");

                    if(type!=null)
                        g.setType(type);
                }

                else if (ans.equals(values.get(9))) {
                    String tempFreeze = (String) JOptionPane.showInputDialog(null, "Has Oven",
                            "Gas Stove", JOptionPane.OK_CANCEL_OPTION, null, new String[]{"True", "False"}, null);

                    if (tempFreeze != null) {
                        if (tempFreeze.equals("True")) {
                            g.setGasOven(true);
                        }
                        else {
                            g.setGasOven(false);
                        }
                    }
                }

                ans = (String) JOptionPane.showInputDialog(null, "Choose a Info :", "Info",
                        JOptionPane.PLAIN_MESSAGE, null, values.toArray(), null);
            }
        }

        else if(good instanceof Food){
            Food hold=(Food)good;

            Food f=new Food(hold.getName(), hold.getBrand(), hold.getPrice()
                , hold.getNumber(), hold.getSeller(), hold.getDescription()
                , hold.getProductionDate(), hold.getExpiryDate());

            f.setCode(hold.getCode());

            while(true) {
                if (ans == null) {
                    adminSet.setChangeRequest(f, good);
                    WriteInfo.saveFood(f,true);
                    JOptionPane.showMessageDialog(null, "Your request will reviewed by admin");
                    break;
                }

                else if (ans.equals(values.get(0))) {
                    String name = JOptionPane.showInputDialog("Enter name :");

                    if(name!=null)
                        f.setName(name);
                }

                else if (ans.equals(values.get(1))) {
                    String brand = JOptionPane.showInputDialog("Enter brand :");

                    if(brand!=null)
                        f.setBrand(brand);
                }

                else if (ans.equals(values.get(2))) {
                    String tempPrice = JOptionPane.showInputDialog("Enter Price :");

                    if(tempPrice!=null) {
                        try {
                            double price = Double.valueOf(tempPrice);
                            f.setPrice(price);
                        }
                        catch (NumberFormatException ex) {
                            JOptionPane.showInputDialog(null, "Enter a Number!!");
                        }
                    }
                }

                else if (ans.equals(values.get(3))) {
                    String tempNumber = JOptionPane.showInputDialog("Enter number :");

                    if(tempNumber!=null) {
                        try {
                            int number = Integer.valueOf(tempNumber);
                            f.setNumber(number);
                        }
                        catch (NumberFormatException ex) {
                            JOptionPane.showInputDialog(null, "Enter a Number!!");
                        }
                    }
                }

                else if (ans.equals(values.get(4))) {
                    String description = JOptionPane.showInputDialog("Enter description :");

                    if(description!=null)
                        f.setDescription(description);
                }

                else if (ans.equals(values.get(5))) {
                    String date = JOptionPane.showInputDialog("Enter production date :");

                    if(date!=null)
                        f.setProductionDate(date);
                }

                else if (ans.equals(values.get(6))) {
                    String date = JOptionPane.showInputDialog("Enter expiry date :");

                    if(date!=null)
                        f.setExpiryDate(date);
                }

                ans = (String) JOptionPane.showInputDialog(null, "Choose a Info :", "Info",
                        JOptionPane.PLAIN_MESSAGE, null, values.toArray(), null);
            }
        }
    }

    static ArrayList<String> values(Goods good){
        ArrayList<String> values=new ArrayList<>();
        values.add("Name");
        values.add("Brand");
        values.add("Price");
        values.add("Number");
        values.add("Description");

        if(good instanceof DigitalCommodity){
            values.add("Memory");
            values.add("Ram");
            values.add("Operating System");
            values.add("Weight");
            values.add("Length");
            values.add("Width");
            values.add("Warranty");

            if(good instanceof Mobile){
                values.add("Sim Number");
                values.add("Camera Quality");
            }
            else if(good instanceof Laptop){
                values.add("Cpu Model");
                values.add("Gaming");
            }
        }

        else if(good instanceof Clothing){
            values.add("Country");
            values.add("Material");
            values.add("Size");
            values.add("Type");
        }

        else if(good instanceof HomeAppliances){
            values.add("Energy Degree");
            values.add("Warranty");

            if(good instanceof Television){
                values.add("Quality");
                values.add("Size");
            }

            else if(good instanceof Refrigerator){
                values.add("Capacity");
                values.add("Model");
                values.add("Freezer");
            }

            else if(good instanceof GasStove){
                values.add("Flame Number");
                values.add("Type");
                values.add("Oven");
            }
        }

        else if(good instanceof Food){
            values.add("Production Date");
            values.add("Expiry Date");
        }

        return values;
    }
}
