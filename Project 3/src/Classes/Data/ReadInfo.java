package Classes.Data;

import java.sql.*;
import java.util.ArrayList;

import Classes.Products.*;
import Classes.Products.Food;
import Classes.User.*;

import javax.swing.*;

public class ReadInfo {
    private static final String url="jdbc:mysql://localhost/project";
    private static final String username="root";
    private static final String password="hhaa1382";

    public static void readSellers() throws ClassNotFoundException, SQLException {
        Admin admin=Admin.getAdmin();

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connect= DriverManager.getConnection(url,username,password);

        String sql="select users.user,firstName,lastName,users.username,password,company,email,phone from users inner join emails"+
                " on users.username=emails.username inner join phones on users.username=phones.username where "+
                "users.user='seller'";

        Statement st=connect.prepareStatement(sql);
        ResultSet rs=st.executeQuery(sql);

        while(rs.next()){
            char[] password = rs.getString("password").toCharArray();
            Seller seller = new Seller(rs.getString("firstName"), rs.getString("lastName"),
                rs.getString("email"), rs.getString("phone"), rs.getString("username"),
                password, rs.getString("company"));

            admin.sellers.add(seller);
        }
    }

    public static Seller readSeller(String user) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connect= DriverManager.getConnection(url,username,password);

        String sql="select users.user,firstName,lastName,users.username,password,company,email,phone from users inner join emails"+
                " on users.username=emails.username inner join phones on users.username=phones.username where "+
                "users.user='seller' AND users.username='"+user+"'";

        Statement st=connect.prepareStatement(sql);
        ResultSet rs=st.executeQuery(sql);

        if(rs.next()) {
            char[] password = rs.getString("password").toCharArray();
            Seller seller = new Seller(rs.getString("firstName"), rs.getString("lastName"),
                    rs.getString("email"), rs.getString("phone"),
                    rs.getString("username"), password,rs.getString("company"));

            return seller;
        }
        return null;
    }

    public static void readCustomers() throws SQLException, ClassNotFoundException {
        Admin admin=Admin.getAdmin();

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connect= DriverManager.getConnection(url,username,password);

        String sql="select users.user,firstName,lastName,users.username,password,credit,email,phone from users inner join emails"+
                " on users.username=emails.username inner join phones on users.username=phones.username where "+
                "users.user='customer'";
        Statement st=connect.prepareStatement(sql);
        ResultSet rs=st.executeQuery(sql);

        while(rs.next()) {
            char[] password = rs.getString("password").toCharArray();
            Customer customer = new Customer(rs.getString("firstName"), rs.getString("lastName"),
                rs.getString("email"), rs.getString("phone"),
                rs.getString("username"), password);

            customer.setAccountCredit(rs.getDouble("credit"));
        }
    }

    public static void readAdmin() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connect= DriverManager.getConnection(url,username,password);

        String sql="select users.user,firstName,lastName,users.username,password,email,phone from users inner join emails"+
                " on users.username=emails.username inner join phones on users.username=phones.username where "+
                "users.user='admin'";

        Statement st=connect.prepareStatement(sql);
        ResultSet rs=st.executeQuery(sql);

        if(rs.next()){
            Admin admin=Admin.getAdmin();
            char[] password=rs.getString("password").toCharArray();
            admin.setFirstName(rs.getString("firstName"));
            admin.setLastName(rs.getString("lastName"));
            admin.setEmail(rs.getString("email"));
            admin.setPhoneNumber(rs.getString("phone"));
            admin.setUserName(rs.getString("username"));
            admin.setPassWord(password);
        }
    }

    public static void readProducts(ArrayList<Goods> products) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connect= DriverManager.getConnection(url,username,password);

        String productSql="select * from products";
        Statement st=connect.prepareStatement(productSql);
        ResultSet rs=st.executeQuery(productSql);

        while (rs.next()){
            Seller seller=readSeller(rs.getString("sellerUsername"));
            if(rs.getString("type").equals("mobile")){
                String sql="select * from mobile where code="+rs.getString("code");
                st=connect.prepareStatement(sql);
                ResultSet rsMobile=st.executeQuery(sql);

                if(rsMobile.next()){
                    Mobile m=new Mobile(rsMobile.getString("name"),rsMobile.getString("brand"),
                            rsMobile.getDouble("price"),rsMobile.getInt("number"),seller,
                            rsMobile.getString("description"),rsMobile.getInt("memory"),
                            rsMobile.getInt("ram"),rsMobile.getString("operatingSystem"),
                            rsMobile.getDouble("weight"),rsMobile.getInt("length"),
                            rsMobile.getInt("width"),rsMobile.getBoolean("warranty"),
                            rsMobile.getInt("simNumber"),rsMobile.getString("cameraQuality"));

                    m.setCode(rsMobile.getInt("code"));
                    readOp(m);
                    readGrade(m);
                    products.add(m);
                }
            }

            else if(rs.getString("type").equals("laptop")){
                String sql="select * from laptop where code="+rs.getString("code");
                st=connect.prepareStatement(sql);
                ResultSet rsLaptop=st.executeQuery(sql);

                if(rsLaptop.next()){
                    Laptop l=new Laptop(rsLaptop.getString("name"),rsLaptop.getString("brand"),
                            rsLaptop.getDouble("price"),rsLaptop.getInt("number"),seller,
                            rsLaptop.getString("description"),rsLaptop.getInt("memory"),
                            rsLaptop.getInt("ram"),rsLaptop.getString("operatingSystem"),
                            rsLaptop.getDouble("weight"),rsLaptop.getInt("length"),
                            rsLaptop.getInt("width"),rsLaptop.getBoolean("warranty"),
                            rsLaptop.getString("cpuModel"),rsLaptop.getBoolean("isGaming"));

                    l.setCode(rsLaptop.getInt("code"));
                    readOp(l);
                    readGrade(l);
                    products.add(l);
                }
            }

            else if(rs.getString("type").equals("clothe")){
                String sql="select * from clothe where code="+rs.getString("code");
                st=connect.prepareStatement(sql);
                ResultSet rsClothe=st.executeQuery(sql);

                if(rsClothe.next()){
                    Clothes c=new Clothes(rsClothe.getString("name"),rsClothe.getString("brand"),
                            rsClothe.getDouble("price"),rsClothe.getInt("number"),seller,
                            rsClothe.getString("description"),rsClothe.getString("country"),
                            rsClothe.getString("Material"),rsClothe.getInt("size"),rsClothe.getString("type"));

                    c.setCode(rsClothe.getInt("code"));
                    readOp(c);
                    readGrade(c);
                    products.add(c);
                }
            }

            else if(rs.getString("type").equals("shoe")){
                String sql="select * from shoe where code="+rs.getString("code");
                st=connect.prepareStatement(sql);
                ResultSet rsShoe=st.executeQuery(sql);

                if(rsShoe.next()){
                    Shoes s=new Shoes(rsShoe.getString("name"),rsShoe.getString("brand"),
                            rsShoe.getDouble("price"),rsShoe.getInt("number"),seller,
                            rsShoe.getString("description"),rsShoe.getString("country"),
                            rsShoe.getString("Material"),rsShoe.getInt("size"),rsShoe.getString("type"));

                    s.setCode(rsShoe.getInt("code"));
                    readOp(s);
                    readGrade(s);
                    products.add(s);
                }
            }

            else if(rs.getString("type").equals("tv")){
                String sql="select * from tv where code="+rs.getString("code");
                st=connect.prepareStatement(sql);
                ResultSet rsTv=st.executeQuery(sql);

                if(rsTv.next()){
                    Television t=new Television(rsTv.getString("name"),rsTv.getString("brand"),
                            rsTv.getDouble("price"),rsTv.getInt("number"),seller,
                            rsTv.getString("description"),rsTv.getDouble("energyDegree"),
                            rsTv.getBoolean("warranty"),rsTv.getString("quality"),
                            rsTv.getInt("size"));

                    t.setCode(rsTv.getInt("code"));
                    readOp(t);
                    readGrade(t);
                    products.add(t);
                }
            }

            else if(rs.getString("type").equals("refrigerator")){
                String sql="select * from refrigerator where code="+rs.getString("code");
                st=connect.prepareStatement(sql);
                ResultSet rsRefrigerator=st.executeQuery(sql);

                if(rsRefrigerator.next()){
                    Refrigerator r=new Refrigerator(rsRefrigerator.getString("name"),rsRefrigerator.getString("brand"),
                            rsRefrigerator.getDouble("price"),rsRefrigerator.getInt("number"),seller,
                            rsRefrigerator.getString("description"),rsRefrigerator.getDouble("energyDegree"),
                            rsRefrigerator.getBoolean("warranty"),rsRefrigerator.getInt("capacity"),
                            rsRefrigerator.getString("model"),rsRefrigerator.getBoolean("hasFreeze"));

                    r.setCode(rsRefrigerator.getInt("code"));
                    readOp(r);
                    readGrade(r);
                    products.add(r);
                }
            }

            else if(rs.getString("type").equals("gasStove")){
                String sql="select * from gasstove where code="+rs.getString("code");
                st=connect.prepareStatement(sql);
                ResultSet rsGas=st.executeQuery(sql);

                if(rsGas.next()){
                    GasStove g=new GasStove(rsGas.getString("name"),rsGas.getString("brand"),
                            rsGas.getDouble("price"),rsGas.getInt("number"),seller,
                            rsGas.getString("description"),rsGas.getDouble("energyDegree"),
                            rsGas.getBoolean("warranty"),rsGas.getInt("flameNumber"),
                            rsGas.getString("type"),rsGas.getBoolean("hasOven"));

                    g.setCode(rsGas.getInt("code"));
                    readOp(g);
                    readGrade(g);
                    products.add(g);
                }
            }

            else if(rs.getString("type").equals("food")){
                String sql="select * from food where code="+rs.getString("code");
                st=connect.prepareStatement(sql);
                ResultSet rsFood=st.executeQuery(sql);

                if(rsFood.next()){
                    Food f=new Food(rsFood.getString("name"),rsFood.getString("brand"),
                            rsFood.getDouble("price"),rsFood.getInt("number"),seller,
                            rsFood.getString("description"),rsFood.getString("productionDate"),
                            rsFood.getString("expiryDate"));

                    f.setCode(rsFood.getInt("code"));
                    readOp(f);
                    readGrade(f);
                    products.add(f);
                }
            }
        }
    }

    public static void readSellerProducts() throws ClassNotFoundException, SQLException {
        Admin admin=Admin.getAdmin();

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connect= DriverManager.getConnection(url,username,password);

        for(Seller seller:admin.sellers){
            String productSql="select * from products where sellerUsername='"+seller.getUserName()+"'";
            Statement st=connect.prepareStatement(productSql);
            ResultSet rs=st.executeQuery(productSql);

            while (rs.next()){
                if(rs.getString("type").equals("mobile")){
                    String sql="select * from mobile where code="+rs.getString("code");
                    st=connect.prepareStatement(sql);
                    ResultSet rsMobile=st.executeQuery(sql);

                    if(rsMobile.next()){
                        Mobile m=new Mobile(rsMobile.getString("name"),rsMobile.getString("brand"),
                                rsMobile.getDouble("price"),rsMobile.getInt("number"),seller,
                                rsMobile.getString("description"),rsMobile.getInt("memory"),
                                rsMobile.getInt("ram"),rsMobile.getString("operatingSystem"),
                                rsMobile.getDouble("weight"),rsMobile.getInt("length"),
                                rsMobile.getInt("width"),rsMobile.getBoolean("warranty"),
                                rsMobile.getInt("simNumber"),rsMobile.getString("cameraQuality"));

                        m.setCode(rsMobile.getInt("code"));
                        seller.setProduct(m);
                    }
                }

                else if(rs.getString("type").equals("laptop")){
                    String sql="select * from laptop where code="+rs.getString("code");
                    st=connect.prepareStatement(sql);
                    ResultSet rsLaptop=st.executeQuery(sql);

                    if(rsLaptop.next()){
                        Laptop l=new Laptop(rsLaptop.getString("name"),rsLaptop.getString("brand"),
                                rsLaptop.getDouble("price"),rsLaptop.getInt("number"),seller,
                                rsLaptop.getString("description"),rsLaptop.getInt("memory"),
                                rsLaptop.getInt("ram"),rsLaptop.getString("operatingSystem"),
                                rsLaptop.getDouble("weight"),rsLaptop.getInt("length"),
                                rsLaptop.getInt("width"),rsLaptop.getBoolean("warranty"),
                                rsLaptop.getString("cpuModel"),rsLaptop.getBoolean("isGaming"));

                        l.setCode(rsLaptop.getInt("code"));
                        seller.setProduct(l);
                    }
                }

                else if(rs.getString("type").equals("clothe")){
                    String sql="select * from clothe where code="+rs.getString("code");
                    st=connect.prepareStatement(sql);
                    ResultSet rsClothe=st.executeQuery(sql);

                    if(rsClothe.next()){
                        Clothes c=new Clothes(rsClothe.getString("name"),rsClothe.getString("brand"),
                                rsClothe.getDouble("price"),rsClothe.getInt("number"),seller,
                                rsClothe.getString("description"),rsClothe.getString("country"),
                                rsClothe.getString("Material"),rsClothe.getInt("size"),rsClothe.getString("type"));

                        c.setCode(rsClothe.getInt("code"));
                        seller.setProduct(c);
                    }
                }

                else if(rs.getString("type").equals("shoe")){
                    String sql="select * from shoe where code="+rs.getString("code");
                    st=connect.prepareStatement(sql);
                    ResultSet rsShoe=st.executeQuery(sql);

                    if(rsShoe.next()){
                        Shoes s=new Shoes(rsShoe.getString("name"),rsShoe.getString("brand"),
                                rsShoe.getDouble("price"),rsShoe.getInt("number"),seller,
                                rsShoe.getString("description"),rsShoe.getString("country"),
                                rsShoe.getString("Material"),rsShoe.getInt("size"),rsShoe.getString("type"));

                        s.setCode(rsShoe.getInt("code"));
                        seller.setProduct(s);
                    }
                }

                else if(rs.getString("type").equals("tv")){
                    String sql="select * from tv where code="+rs.getString("code");
                    st=connect.prepareStatement(sql);
                    ResultSet rsTv=st.executeQuery(sql);

                    if(rsTv.next()){
                        Television t=new Television(rsTv.getString("name"),rsTv.getString("brand"),
                                rsTv.getDouble("price"),rsTv.getInt("number"),seller,
                                rsTv.getString("description"),rsTv.getDouble("energyDegree"),
                                rsTv.getBoolean("warranty"),rsTv.getString("quality"),
                                rsTv.getInt("size"));

                        t.setCode(rsTv.getInt("code"));
                        seller.setProduct(t);
                    }
                }

                else if(rs.getString("type").equals("refrigerator")){
                    String sql="select * from refrigerator where code="+rs.getString("code");
                    st=connect.prepareStatement(sql);
                    ResultSet rsRefrigerator=st.executeQuery(sql);

                    if(rsRefrigerator.next()){
                        Refrigerator r=new Refrigerator(rsRefrigerator.getString("name"),rsRefrigerator.getString("brand"),
                                rsRefrigerator.getDouble("price"),rsRefrigerator.getInt("number"),seller,
                                rsRefrigerator.getString("description"),rsRefrigerator.getDouble("energyDegree"),
                                rsRefrigerator.getBoolean("warranty"),rsRefrigerator.getInt("capacity"),
                                rsRefrigerator.getString("model"),rsRefrigerator.getBoolean("hasFreeze"));

                        r.setCode(rsRefrigerator.getInt("code"));
                        seller.setProduct(r);
                    }
                }

                else if(rs.getString("type").equals("gasStove")){
                    String sql="select * from gasstove where code="+rs.getString("code");
                    st=connect.prepareStatement(sql);
                    ResultSet rsGas=st.executeQuery(sql);

                    if(rsGas.next()){
                        GasStove g=new GasStove(rsGas.getString("name"),rsGas.getString("brand"),
                                rsGas.getDouble("price"),rsGas.getInt("number"),seller,
                                rsGas.getString("description"),rsGas.getDouble("energyDegree"),
                                rsGas.getBoolean("warranty"),rsGas.getInt("flameNumber"),
                                rsGas.getString("type"),rsGas.getBoolean("hasOven"));

                        g.setCode(rsGas.getInt("code"));
                        seller.setProduct(g);
                    }
                }

                else if(rs.getString("type").equals("food")){
                    String sql="select * from food where code="+rs.getString("code");
                    st=connect.prepareStatement(sql);
                    ResultSet rsFood=st.executeQuery(sql);

                    if(rsFood.next()){
                        Food f=new Food(rsFood.getString("name"),rsFood.getString("brand"),
                                rsFood.getDouble("price"),rsFood.getInt("number"),seller,
                                rsFood.getString("description"),rsFood.getString("productionDate"),
                                rsFood.getString("expiryDate"));

                        f.setCode(rsFood.getInt("code"));
                        seller.setProduct(f);
                    }
                }
            }
        }
    }

    public static void readMobile(ArrayList<Mobile> mobiles) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connect= DriverManager.getConnection(url,username,password);

        String productSql="select * from products where type='mobile'";
        Statement st=connect.prepareStatement(productSql);
        ResultSet rs=st.executeQuery(productSql);

        while (rs.next()) {
            Seller seller=readSeller(rs.getString("sellerUsername"));
            String sql = "select * from mobile where code=" + rs.getString("code");
            st = connect.prepareStatement(sql);
            ResultSet rsMobile = st.executeQuery(sql);

            if (rsMobile.next()) {
                Mobile m = new Mobile(rsMobile.getString("name"), rsMobile.getString("brand"),
                        rsMobile.getDouble("price"), rsMobile.getInt("number"), seller,
                        rsMobile.getString("description"), rsMobile.getInt("memory"),
                        rsMobile.getInt("ram"), rsMobile.getString("operatingSystem"),
                        rsMobile.getDouble("weight"), rsMobile.getInt("length"),
                        rsMobile.getInt("width"), rsMobile.getBoolean("warranty"),
                        rsMobile.getInt("simNumber"), rsMobile.getString("cameraQuality"));

                m.setCode(rsMobile.getInt("code"));
                mobiles.add(m);
            }
        }
    }

    public static void readLaptop(ArrayList<Laptop> laptops) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connect= DriverManager.getConnection(url,username,password);

        String productSql="select * from products where type='laptop'";
        Statement st=connect.prepareStatement(productSql);
        ResultSet rs=st.executeQuery(productSql);

        while (rs.next()) {
            Seller seller=readSeller(rs.getString("sellerUsername"));
            String sql="select * from laptop where code="+rs.getString("code");
            st=connect.prepareStatement(sql);
            ResultSet rsLaptop=st.executeQuery(sql);

            if(rsLaptop.next()){
                Laptop l=new Laptop(rsLaptop.getString("name"),rsLaptop.getString("brand"),
                        rsLaptop.getDouble("price"),rsLaptop.getInt("number"),seller,
                        rsLaptop.getString("description"),rsLaptop.getInt("memory"),
                        rsLaptop.getInt("ram"),rsLaptop.getString("operatingSystem"),
                        rsLaptop.getDouble("weight"),rsLaptop.getInt("length"),
                        rsLaptop.getInt("width"),rsLaptop.getBoolean("warranty"),
                        rsLaptop.getString("cpuModel"),rsLaptop.getBoolean("isGaming"));

                l.setCode(rsLaptop.getInt("code"));
                laptops.add(l);
            }
        }
    }

    public static void readClothe(ArrayList<Clothes> clothes) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connect= DriverManager.getConnection(url,username,password);

        String productSql="select * from products where type='clothe'";
        Statement st=connect.prepareStatement(productSql);
        ResultSet rs=st.executeQuery(productSql);

        while (rs.next()) {
            Seller seller=readSeller(rs.getString("sellerUsername"));
            String sql = "select * from clothe where code=" + rs.getString("code");
            st = connect.prepareStatement(sql);
            ResultSet rsClothe = st.executeQuery(sql);

            if(rsClothe.next()){
                Clothes c=new Clothes(rsClothe.getString("name"),rsClothe.getString("brand"),
                        rsClothe.getDouble("price"),rsClothe.getInt("number"),seller,
                        rsClothe.getString("description"),rsClothe.getString("country"),
                        rsClothe.getString("Material"),rsClothe.getInt("size"),rsClothe.getString("type"));

                c.setCode(rsClothe.getInt("code"));
                clothes.add(c);
            }
        }
    }

    public static void readShoe(ArrayList<Shoes> shoes) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connect= DriverManager.getConnection(url,username,password);

        String productSql="select * from products where type='shoe'";
        Statement st=connect.prepareStatement(productSql);
        ResultSet rs=st.executeQuery(productSql);

        while (rs.next()) {
            Seller seller=readSeller(rs.getString("sellerUsername"));
            String sql = "select * from shoe where code=" + rs.getString("code");
            st = connect.prepareStatement(sql);
            ResultSet rsShoe = st.executeQuery(sql);

            if(rsShoe.next()){
                Shoes s=new Shoes(rsShoe.getString("name"),rsShoe.getString("brand"),
                        rsShoe.getDouble("price"),rsShoe.getInt("number"),seller,
                        rsShoe.getString("description"),rsShoe.getString("country"),
                        rsShoe.getString("Material"),rsShoe.getInt("size"),rsShoe.getString("type"));

                s.setCode(rsShoe.getInt("code"));
                shoes.add(s);
            }
        }
    }

    public static void readTv(ArrayList<Television> tv) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connect= DriverManager.getConnection(url,username,password);

        String productSql="select * from products where type='tv'";
        Statement st=connect.prepareStatement(productSql);
        ResultSet rs=st.executeQuery(productSql);

        while (rs.next()) {
            Seller seller=readSeller(rs.getString("sellerUsername"));
            String sql = "select * from tv where code=" + rs.getString("code");
            st = connect.prepareStatement(sql);
            ResultSet rsTv=st.executeQuery(sql);

            if(rsTv.next()){
                Television t=new Television(rsTv.getString("name"),rsTv.getString("brand"),
                        rsTv.getDouble("price"),rsTv.getInt("number"),seller,
                        rsTv.getString("description"),rsTv.getDouble("energyDegree"),
                        rsTv.getBoolean("warranty"),rsTv.getString("quality"),
                        rsTv.getInt("size"));

                t.setCode(rsTv.getInt("code"));
                tv.add(t);
            }
        }
    }

    public static void readRefrigerator(ArrayList<Refrigerator> refrigerators) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connect= DriverManager.getConnection(url,username,password);

        String productSql="select * from products where type='refrigerator'";
        Statement st=connect.prepareStatement(productSql);
        ResultSet rs=st.executeQuery(productSql);

        while (rs.next()) {
            Seller seller=readSeller(rs.getString("sellerUsername"));
            String sql = "select * from refrigerator where code=" + rs.getString("code");
            st = connect.prepareStatement(sql);
            ResultSet rsRefrigerator=st.executeQuery(sql);

            if(rsRefrigerator.next()){
                Refrigerator r=new Refrigerator(rsRefrigerator.getString("name"),rsRefrigerator.getString("brand"),
                        rsRefrigerator.getDouble("price"),rsRefrigerator.getInt("number"),seller,
                        rsRefrigerator.getString("description"),rsRefrigerator.getDouble("energyDegree"),
                        rsRefrigerator.getBoolean("warranty"),rsRefrigerator.getInt("capacity"),
                        rsRefrigerator.getString("model"),rsRefrigerator.getBoolean("hasFreeze"));

                r.setCode(rsRefrigerator.getInt("code"));
                refrigerators.add(r);
            }
        }
    }

    public static void readGasStove(ArrayList<GasStove> gasStoves) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connect= DriverManager.getConnection(url,username,password);

        String productSql="select * from products where type='gasstove'";
        Statement st=connect.prepareStatement(productSql);
        ResultSet rs=st.executeQuery(productSql);

        while (rs.next()) {
            Seller seller=readSeller(rs.getString("sellerUsername"));
            String sql = "select * from gasstove where code=" + rs.getString("code");
            st = connect.prepareStatement(sql);
            ResultSet rsGas=st.executeQuery(sql);

            if(rsGas.next()){
                GasStove g=new GasStove(rsGas.getString("name"),rsGas.getString("brand"),
                        rsGas.getDouble("price"),rsGas.getInt("number"),seller,
                        rsGas.getString("description"),rsGas.getDouble("energyDegree"),
                        rsGas.getBoolean("warranty"),rsGas.getInt("flameNumber"),
                        rsGas.getString("type"),rsGas.getBoolean("hasOven"));

                g.setCode(rsGas.getInt("code"));
                gasStoves.add(g);
            }
        }
    }

    public static void readFood(ArrayList<Food> foods) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connect= DriverManager.getConnection(url,username,password);

        String productSql="select * from products where type='food'";
        Statement st=connect.prepareStatement(productSql);
        ResultSet rs=st.executeQuery(productSql);

        while (rs.next()) {
            Seller seller=readSeller(rs.getString("sellerUsername"));
            String sql = "select * from food where code=" + rs.getString("code");
            st = connect.prepareStatement(sql);
            ResultSet rsFood=st.executeQuery(sql);

            if(rsFood.next()){
                Food f=new Food(rsFood.getString("name"),rsFood.getString("brand"),
                        rsFood.getDouble("price"),rsFood.getInt("number"),seller,
                        rsFood.getString("description"),rsFood.getString("productionDate"),
                        rsFood.getString("expiryDate"));

                f.setCode(rsFood.getInt("code"));
                foods.add(f);
            }
        }
    }

    public static void readGrade(Goods good) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connect= DriverManager.getConnection(url,username,password);

        String sql="select grade from grade where code="+good.getCode();
        Statement st=connect.prepareStatement(sql);
        ResultSet rs=st.executeQuery(sql);

        if(rs.next()){
            double grade=rs.getDouble("grade");
            int number=rs.getInt("number");
            good.setGradeInfo(number,(int)grade);
        }
    }

    public static void readSellerRequest() throws ClassNotFoundException, SQLException {
        Admin admin=Admin.getAdmin();

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connect= DriverManager.getConnection(url,username,password);

        String sql="select * from sellerrequest";
        Statement st=connect.prepareStatement(sql);
        ResultSet rs=st.executeQuery(sql);

        while (rs.next()){
            char[] password=rs.getString("password").toCharArray();
            Seller seller=new Seller(rs.getString("firstName"),rs.getString("lastName"),
                    rs.getString("email"),rs.getString("phone"),rs.getString("username"),
                    password,rs.getString("company"));

            admin.setSellerRequest(seller);
        }
    }

    public static Mobile getMobile(int code,boolean checkChange) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connect= DriverManager.getConnection(url,username,password);

        String sql;

        if(checkChange){
            sql="select * from mobile where mobile.change=true AND code="+code;
        }
        else{
            sql="select * from mobile where mobile.change=false AND code="+code;
        }

        Statement st=connect.prepareStatement(sql);
        ResultSet rs=st.executeQuery(sql);

        if(rs.next()){
            Seller seller=readSeller(rs.getString("sellerUsername"));

            Mobile m = new Mobile(rs.getString("name"), rs.getString("brand"),
                    rs.getDouble("price"), rs.getInt("number"), seller,
                    rs.getString("description"), rs.getInt("memory"),
                    rs.getInt("ram"), rs.getString("operatingSystem"),
                    rs.getDouble("weight"), rs.getInt("length"),
                    rs.getInt("width"), rs.getBoolean("warranty"),
                    rs.getInt("simNumber"), rs.getString("cameraQuality"));

            m.setCode(rs.getInt("code"));
            return m;
        }

        return null;
    }

    public static Laptop getLaptop(int code,boolean checkChange) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connect= DriverManager.getConnection(url,username,password);

        String sql;

        if(checkChange){
            sql="select * from laptop where laptop.change=true AND code="+code;
        }
        else{
            sql="select * from laptop where laptop.change=false AND code="+code;
        }

        Statement st=connect.prepareStatement(sql);
        ResultSet rs=st.executeQuery(sql);

        if(rs.next()){
            Seller seller=readSeller(rs.getString("sellerUsername"));

            Laptop l=new Laptop(rs.getString("name"),rs.getString("brand"),
                    rs.getDouble("price"),rs.getInt("number"),seller,
                    rs.getString("description"),rs.getInt("memory"),
                    rs.getInt("ram"),rs.getString("operatingSystem"),
                    rs.getDouble("weight"),rs.getInt("length"),
                    rs.getInt("width"),rs.getBoolean("warranty"),
                    rs.getString("cpuModel"),rs.getBoolean("isGaming"));

            l.setCode(rs.getInt("code"));
            return l;
        }

        return null;
    }

    public static Clothes getClothe(int code,boolean checkChange) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connect= DriverManager.getConnection(url,username,password);

        String sql;

        if(checkChange){
            sql="select * from clothe where clothe.change=true AND code="+code;
        }
        else{
            sql="select * from clothe where clothe.change=false AND code="+code;
        }

        Statement st=connect.prepareStatement(sql);
        ResultSet rs=st.executeQuery(sql);

        if(rs.next()){
            Seller seller=readSeller(rs.getString("sellerUsername"));

            Clothes c=new Clothes(rs.getString("name"),rs.getString("brand"),
                    rs.getDouble("price"),rs.getInt("number"),seller,
                    rs.getString("description"),rs.getString("country"),
                    rs.getString("Material"),rs.getInt("size"),rs.getString("type"));

            c.setCode(rs.getInt("code"));
            return c;
        }

        return null;
    }

    public static Shoes getShoe(int code,boolean checkChange) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connect= DriverManager.getConnection(url,username,password);

        String sql;

        if(checkChange){
            sql="select * from shoe where shoe.change=true AND code="+code;
        }
        else{
            sql="select * from shoe where shoe.change=false AND code="+code;
        }

        Statement st=connect.prepareStatement(sql);
        ResultSet rs=st.executeQuery(sql);

        if(rs.next()){
            Seller seller=readSeller(rs.getString("sellerUsername"));

            Shoes s=new Shoes(rs.getString("name"),rs.getString("brand"),
                    rs.getDouble("price"),rs.getInt("number"),seller,
                    rs.getString("description"),rs.getString("country"),
                    rs.getString("Material"),rs.getInt("size"),rs.getString("type"));

            s.setCode(rs.getInt("code"));
            return s;
        }

        return null;
    }

    public static Television getTv(int code,boolean checkChange) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connect= DriverManager.getConnection(url,username,password);

        String sql;

        if(checkChange){
            sql="select * from tv where tv.change=true AND code="+code;
        }
        else{
            sql="select * from tv where tv.change=false AND code="+code;
        }

        Statement st=connect.prepareStatement(sql);
        ResultSet rs=st.executeQuery(sql);

        if(rs.next()){
            Seller seller=readSeller(rs.getString("sellerUsername"));

            Television t=new Television(rs.getString("name"),rs.getString("brand"),
                    rs.getDouble("price"),rs.getInt("number"),seller,
                    rs.getString("description"),rs.getDouble("energyDegree"),
                    rs.getBoolean("warranty"),rs.getString("quality"),
                    rs.getInt("size"));

            t.setCode(rs.getInt("code"));
            return t;
        }

        return null;
    }

    public static Refrigerator getRefrigerator(int code,boolean checkChange) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connect= DriverManager.getConnection(url,username,password);

        String sql;

        if(checkChange){
            sql="select * from refrigerator where refrigerator.change=true AND code="+code;
        }
        else{
            sql="select * from refrigerator refrigerator.change=false AND where code="+code;
        }

        Statement st=connect.prepareStatement(sql);
        ResultSet rs=st.executeQuery(sql);

        if(rs.next()){
            Seller seller=readSeller(rs.getString("sellerUsername"));

            Refrigerator r=new Refrigerator(rs.getString("name"),rs.getString("brand"),
                    rs.getDouble("price"),rs.getInt("number"),seller,
                    rs.getString("description"),rs.getDouble("energyDegree"),
                    rs.getBoolean("warranty"),rs.getInt("capacity"),
                    rs.getString("model"),rs.getBoolean("hasFreeze"));

            r.setCode(rs.getInt("code"));
            return r;
        }

        return null;
    }

    public static GasStove getGas(int code,boolean checkChange) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connect= DriverManager.getConnection(url,username,password);

        String sql;

        if(checkChange){
            sql="select * from gasstove where gasstove.change=true AND code="+code;
        }
        else{
            sql="select * from gasstove where gasstove.change=false AND code="+code;
        }

        Statement st=connect.prepareStatement(sql);
        ResultSet rs=st.executeQuery(sql);

        if(rs.next()){
            Seller seller=readSeller(rs.getString("sellerUsername"));

            GasStove g=new GasStove(rs.getString("name"),rs.getString("brand"),
                    rs.getDouble("price"),rs.getInt("number"),seller,
                    rs.getString("description"),rs.getDouble("energyDegree"),
                    rs.getBoolean("warranty"),rs.getInt("flameNumber"),
                    rs.getString("type"),rs.getBoolean("hasOven"));

            g.setCode(rs.getInt("code"));
            return g;
        }

        return null;
    }

    public static Food getFood(int code,boolean checkChange) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connect= DriverManager.getConnection(url,username,password);

        String sql;

        if(checkChange){
            sql="select * from food where food.change=true AND code="+code;
        }
        else{
            sql="select * from food where food.change=false AND code="+code;
        }

        Statement st=connect.prepareStatement(sql);
        ResultSet rs=st.executeQuery(sql);

        if(rs.next()){
            Seller seller=readSeller(rs.getString("sellerUsername"));

            Food f=new Food(rs.getString("name"),rs.getString("brand"),
                    rs.getDouble("price"),rs.getInt("number"),seller,
                    rs.getString("description"),rs.getString("productionDate"),
                    rs.getString("expiryDate"));

            f.setCode(rs.getInt("code"));
            return f;
        }

        return null;
    }

    public static void readAddRequests() throws ClassNotFoundException, SQLException {
        Admin admin=Admin.getAdmin();

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connect= DriverManager.getConnection(url,username,password);

        String sql="select * from requests where type='add'";
        Statement st=connect.prepareStatement(sql);
        ResultSet rs=st.executeQuery(sql);

        while (rs.next()){
            admin.setAddRequestProduct(getProduct(rs.getInt("code")));
        }
    }

    public static void readDeleteRequest() throws ClassNotFoundException, SQLException {
        Admin admin=Admin.getAdmin();

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connect= DriverManager.getConnection(url,username,password);

        String sql="select * from requests where type='delete'";
        Statement st=connect.prepareStatement(sql);
        ResultSet rs=st.executeQuery(sql);

        while (rs.next()) {
            admin.setDeleteProduct(getProduct(rs.getInt("code")));
        }
    }

    public static void readChangeRequest() throws ClassNotFoundException, SQLException {
        Admin admin=Admin.getAdmin();

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connect= DriverManager.getConnection(url,username,password);

        String sql="select * from requests where type='change'";
        Statement st=connect.prepareStatement(sql);
        ResultSet rs=st.executeQuery(sql);

        while (rs.next()){
            if(rs.getString("product").equals("mobile")){
                Mobile realMobile=getMobile(rs.getInt("code"),false);
                Mobile changeMobile=getMobile(rs.getInt("code"),true);
                admin.setChangeRequest(changeMobile,realMobile);
            }

            else if(rs.getString("product").equals("laptop")){
                Laptop realLaptop=getLaptop(rs.getInt("code"),false);
                Laptop changeLaptop=getLaptop(rs.getInt("code"),true);
                admin.setChangeRequest(changeLaptop,realLaptop);
            }

            else if(rs.getString("product").equals("clothe")){
                Clothes realClothe=getClothe(rs.getInt("code"),false);
                Clothes changeClothe=getClothe(rs.getInt("code"),true);
                admin.setChangeRequest(changeClothe,realClothe);
            }

            else if(rs.getString("product").equals("shoe")){
                Shoes realShoe=getShoe(rs.getInt("code"),false);
                Shoes changeShoe=getShoe(rs.getInt("code"),true);
                admin.setChangeRequest(changeShoe,realShoe);
            }

            else if(rs.getString("product").equals("tv")){
                Television realTv=getTv(rs.getInt("code"),false);
                Television changeTv=getTv(rs.getInt("code"),true);
                admin.setChangeRequest(changeTv,realTv);
            }

            else if(rs.getString("product").equals("refrigerator")){
                Refrigerator realRefrigerator=getRefrigerator(rs.getInt("code"),false);
                Refrigerator changeRefrigerator=getRefrigerator(rs.getInt("code"),true);
                admin.setChangeRequest(changeRefrigerator,realRefrigerator);
            }

            else if(rs.getString("product").equals("gasStove")){
                GasStove realGas=getGas(rs.getInt("code"),false);
                GasStove changeGas=getGas(rs.getInt("code"),true);
                admin.setChangeRequest(changeGas,realGas);
            }

            else if(rs.getString("product").equals("food")){
                Food realFood=getFood(rs.getInt("code"),false);
                Food changeFood=getFood(rs.getInt("code"),false);
                admin.setChangeRequest(changeFood,realFood);
            }
        }
    }

    public static void readShoppingCarts() throws ClassNotFoundException, SQLException {
        Admin admin=Admin.getAdmin();

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connect= DriverManager.getConnection(url,username,password);

        for(Customer customer:admin.customers) {
            String sql = "select * from shoppingCart where username='" + customer.getUserName() + "'";
            Statement st = connect.prepareStatement(sql);
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                Goods g=getProduct(rs.getInt("code"));
                customer.setShopingCart(g, rs.getInt("number"));
            }
        }
    }

    public static Goods getProduct(int code) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connect= DriverManager.getConnection(url,username,password);

        String sql="select * from products where code="+code;
        Statement st=connect.prepareStatement(sql);
        ResultSet rs=st.executeQuery(sql);

        while (rs.next()){
            if(rs.getString("type").equals("mobile")){
                return getMobile(rs.getInt("code"),false);
            }

            else if(rs.getString("type").equals("laptop")){
                return getLaptop(rs.getInt("code"),false);
            }

            else if(rs.getString("type").equals("clothe")){
                return getClothe(rs.getInt("code"),false);
            }

            else if(rs.getString("type").equals("shoe")){
                return getShoe(rs.getInt("code"),false);
            }

            else if(rs.getString("type").equals("tv")){
                return getTv(rs.getInt("code"),false);
            }

            else if(rs.getString("type").equals("refrigerator")){
                return getRefrigerator(rs.getInt("code"),false);
            }

            else if(rs.getString("type").equals("gasStove")){
                return getGas(rs.getInt("code"),false);
            }

            else if(rs.getString("type").equals("food")){
                return getFood(rs.getInt("code"),false);
            }
        }

        return null;
    }

    public static void readBuyHistory() throws ClassNotFoundException, SQLException {
        Admin admin=Admin.getAdmin();

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connect= DriverManager.getConnection(url,username,password);

        for (Customer customer:admin.customers) {
            String sql = "select * from buyhistory where customerUsername='" + customer.getUserName() + "'";
            Statement st = connect.prepareStatement(sql);
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                BuyHistory b = new BuyHistory();
                b.setCode(rs.getInt("code"));
                b.setPrice(rs.getDouble("price"));
                b.setDate(rs.getString("date"));
                b.setTime(rs.getString("time"));
                b.setRecieve(rs.getBoolean("receive"));

                String productSql = "select * from buysellhistory where code=" + b.getCode();
                st = connect.prepareStatement(productSql);
                ResultSet rsProduct = st.executeQuery(productSql);

                while (rsProduct.next()) {
                    b.setProducts(getProduct(rsProduct.getInt("code")));
                }
            }
        }
    }

    public static void readSellHistory() throws ClassNotFoundException, SQLException {
        Admin admin=Admin.getAdmin();

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connect= DriverManager.getConnection(url,username,password);

        for(Seller seller:admin.sellers) {
            String sql = "select * from buysellhistory where sellerUsername='" + seller.getUserName() + "'";
            Statement st = connect.prepareStatement(sql);
            ResultSet rs = st.executeQuery(sql);

            SellHistory sell = new SellHistory();

            if (rs.next()) {
                String sqlProduct = "select * from buyhistory where code=" + rs.getInt("code");
                st = connect.prepareStatement(sqlProduct);
                ResultSet rsProduct = st.executeQuery(sqlProduct);

                if(rsProduct.next()) {
                    sell.setCode(rsProduct.getInt("code"));
                    sell.setPrice(rsProduct.getDouble("price"));
                    sell.setDate(rsProduct.getString("date"));
                    sell.setTime(rsProduct.getString("time"));
                    sell.setRecieve(rsProduct.getBoolean("receive"));
                }
            }
        }
    }

    public static void readDiscountCodes() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connect= DriverManager.getConnection(url,username,password);

        String sql="select * from discount";
        Statement st=connect.prepareStatement(sql);
        ResultSet rs=st.executeQuery(sql);

        while(rs.next()){
            Goods g=getProduct(rs.getInt("code"));

            if(g instanceof Clothing){
                Discount discount=new Discount(0.3,rs.getString("date"),rs.getInt("capacity"));
                discount.setDiscountCode(rs.getString("discountCode"));
                g.setDiscount(discount);
            }

            else if(g instanceof DigitalCommodity){
                Discount discount=new Discount(0.2,rs.getString("date"),rs.getInt("capacity"));
                discount.setDiscountCode(rs.getString("discountCode"));
                g.setDiscount(discount);
            }
        }
    }

    public static void readDiscounts() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connect= DriverManager.getConnection(url,username,password);

        String sql="select * from setdiscount";
        Statement st=connect.prepareStatement(sql);
        ResultSet rs=st.executeQuery(sql);

        while (rs.next()){
            Goods g=getProduct(rs.getInt("code"));
            if(g instanceof DigitalCommodity){
                g.setDiscountPercent(0.1);
            }

            else if(g instanceof Clothing){
                g.setDiscountPercent(0.15);
            }

            else if(g instanceof Food){
                g.setDiscountPercent(0.2);
            }
        }
    }

    public static void readAuction() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connect= DriverManager.getConnection(url,username,password);

        String sql="select * from auction";
        Statement st=connect.prepareStatement(sql);
        ResultSet rs=st.executeQuery(sql);

        while (rs.next()){
            Goods g=getProduct(rs.getInt("code"));
            if(g instanceof Clothing){
                Clothing c=(Clothing) g;
                c.setAuction(true);
            }
            else if(g instanceof Food){
                Food f=(Food) g;
                f.setAuction(true);
            }
        }
    }

    public static void readOp(Goods good) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connect= DriverManager.getConnection(url,username,password);

        String sql="select * from opinion where code="+good.getCode();
        Statement st=connect.prepareStatement(sql);
        ResultSet rs=st.executeQuery(sql);

        while(rs.next()){
            Opinion op=new Opinion();
            op.good=good;
            op.text=rs.getString("text");
            good.setOpinion(op);
        }
    }
}
