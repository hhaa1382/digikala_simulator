package Classes.Data;

import Classes.Products.*;
import Classes.User.*;

import javax.swing.*;
import java.sql.*;

public class WriteInfo {
    private static final String url="jdbc:mysql://localhost/project";
    private static final String username="root";
    private static final String password="hhaa1382";

    public static void saveSeller(Seller seller) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connect= DriverManager.getConnection(url,username,password);

        String sql="insert into users (user,firstName,lastName,username,password,credit,company) values ('seller','"
                + seller.getFirstName()+"' , '"+seller.getLastName()+ "' , '"+seller.getUserName()+
                "' , '"+seller.getPassWord() + "' ,0,'"+seller.getCompanyName()+"')";

        saveEmail(seller.getEmail(),"seller",seller.getUserName());
        savePhone(seller.getPassWord(),"seller",seller.getUserName());

        Statement st=connect.prepareStatement(sql);
        st.execute(sql);
    }

    public static void saveEmail(String email,String user,String userName) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connect= DriverManager.getConnection(url,username,password);

        String sql="insert into emails values ('"+user+"' , '"+userName+"' , '"+email+"')";

        Statement st=connect.prepareStatement(sql);
        st.execute(sql);
    }

    public static void savePhone(String phone,String user,String userName) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connect= DriverManager.getConnection(url,username,password);

        String sql="insert into phones values ('"+user+"' , '"+userName+"' , '"+phone+"')";

        Statement st=connect.prepareStatement(sql);
        st.execute(sql);
    }

    public static void saveSellerRequest(Seller seller) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connect= DriverManager.getConnection(url,username,password);

        String requestSql="insert into sellerrequest values ('" + seller.getFirstName()+
                "' , '"+seller.getLastName()+ "' , '"+seller.getEmail()+"' , '"+seller.getPhoneNumber()+
                "' , '"+seller.getUserName()+ "' , '"+seller.getPassWord() + "' , '"+
                seller.getCompanyName()+"')";

        Statement st=connect.prepareStatement(requestSql);
        st.execute(requestSql);
    }

    public static void saveCustomer(Customer customer) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connect= DriverManager.getConnection(url,username,password);

        String sql="insert into users (user,firstName,lastName,username,password,credit) values ('customer','"
                +customer.getFirstName()+"' , '"+customer.getLastName()+"' , '"+customer.getUserName()+
                "' , '"+ customer.getPassWord()+"',0)";

        saveEmail(customer.getEmail(),"customer",customer.getUserName());
        savePhone(customer.getPassWord(),"customer",customer.getUserName());

        Statement st=connect.prepareStatement(sql);
        st.execute(sql);
    }

    public static void acceptSeller(Seller seller) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connect= DriverManager.getConnection(url,username,password);

        String sql="delete from sellerrequest where username='"+seller.getUserName()+"'";

        Statement st=connect.prepareStatement(sql);
        st.execute(sql);

        saveSeller(seller);
    }

    public static void updateSeller(Seller seller) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connect= DriverManager.getConnection(url,username,password);

        String sql="update users set firstName='"+seller.getFirstName()+"',lastName='"+seller.getLastName()+
                "',password='"+ seller.getPassWord()+"',company='"+seller.getCompanyName()+
                "' where username='"+ seller.getUserName()+"' AND user='seller'";

        updateEmail(seller.getEmail(),seller.getUserName(),"seller");
        updatePhone(seller.getPhoneNumber(),seller.getUserName(),"seller");

        Statement st=connect.prepareStatement(sql);
        st.execute(sql);
    }

    public static void updateEmail(String email,String userName,String user) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connect= DriverManager.getConnection(url,username,password);

        String sql="update emails set email='"+email+"' where username='"+ userName+"' AND user='"+user+"'";

        Statement st=connect.prepareStatement(sql);
        st.execute(sql);
    }

    public static void updatePhone(String phone,String userName,String user) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connect= DriverManager.getConnection(url,username,password);

        String sql="update phones set phone='"+phone+"' where username='"+ userName+"' AND user='"+user+"'";

        Statement st=connect.prepareStatement(sql);
        st.execute(sql);
    }

    public static void deleteSeller(Seller seller) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connect= DriverManager.getConnection(url,username,password);

        String sql="delete from user where username='"+seller.getUserName()+"' AND user='seller'";

        deleteEmail(seller.getUserName(),"seller");
        deletePhone(seller.getUserName(),"seller");

        Statement st=connect.prepareStatement(sql);
        st.execute(sql);
    }

    public static void deleteEmail(String userName,String user) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connect= DriverManager.getConnection(url,username,password);

        String sql="delete from emails where user='"+user+"' AND username='"+userName+"'";

        Statement st=connect.prepareStatement(sql);
        st.execute(sql);
    }

    public static void deletePhone(String userName,String user) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connect= DriverManager.getConnection(url,username,password);

        String sql="delete from phones where user='"+user+"' AND username='"+userName+"'";

        Statement st=connect.prepareStatement(sql);
        st.execute(sql);
    }

    public static void saveRequest(Goods good,String productName,String type,Opinion opinion) throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connect= DriverManager.getConnection(url,username,password);

        StringBuilder sql=new StringBuilder();

        if(good!=null){
            if(type.equals("add")){
                sql.append("insert into requests values ('add',"+good.getCode()+",'"+productName+"')");
            }
            else if(type.equals("delete")){
                sql.append("insert into requests values ('delete',"+good.getCode()+",'"+productName+"')");
            }
            else if(type.equals("change")){
                sql.append("insert into requests values ('change',"+good.getCode()+",'"+productName+"')");
            }
        }

        else if(opinion!=null){
            Goods g=opinion.good;
            sql.append("insert into requests values ('opinion',"+g.getCode()+",'"+opinion.text+"')");
        }

        Statement st=connect.prepareStatement(sql.toString());
        st.execute(sql.toString());
    }

    public static void deleteRequest(Seller seller,Goods good,String type,Opinion opinion) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connect= DriverManager.getConnection(url,username,password);

        StringBuilder sql=new StringBuilder();

        if(seller!=null){
            sql.append("delete from sellerrequest where Username='"+seller.getUserName()+"'");
        }

        else if(good!=null){
            if(type.equals("add")){
                sql.append("delete from requests where type='add' AND code="+good.getCode());
            }

            else if(type.equals("delete")){
                sql.append("delete from requests where type='delete' AND code="+good.getCode());
            }

            else if(type.equals("change")){
                sql.append("delete from requests where type='change' AND code="+good.getCode());
            }
        }

        else if(opinion!=null){
            Goods g=opinion.good;
            sql.append("delete from requests where type='opinion' AND code="+g.getCode());
        }

        Statement st=connect.prepareStatement(sql.toString());
        st.execute(sql.toString());
    }

    public static void updateCustomer(Customer customer) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connect= DriverManager.getConnection(url,username,password);

        String sql="update users set firstName='"+customer.getFirstName()+"',lastName='"+customer.getLastName()+
                "',password='"+ customer.getPassWord()+"',credit="+customer.getAccountCredit()+
                " where username='"+customer.getUserName()+"'";

        updateEmail(customer.getEmail(),customer.getUserName(),"customer");
        updatePhone(customer.getPhoneNumber(),customer.getUserName(),"customer");

        Statement st=connect.prepareStatement(sql);
        st.execute(sql);
    }

    public static void deleteCustomer(Customer customer) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connect= DriverManager.getConnection(url,username,password);

        String sql="delete from user where username='"+customer.getUserName()+"' AND user='customer'";

        deleteEmail(customer.getUserName(),"customer");
        deletePhone(customer.getUserName(),"customer");

        Statement st=connect.prepareStatement(sql);
        st.execute(sql);
    }

    public static void saveShoppingCart(Customer customer,Goods good,boolean add) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connect= DriverManager.getConnection(url,username,password);

        String sql="select number from shoppingCart where code="+good.getCode()+"AND username='"+customer.getUserName();
        Statement st=connect.prepareStatement(sql);
        ResultSet rs=st.executeQuery(sql);

        if(rs.next()){
            int number = rs.getInt(1);
            if(add) {
                number++;
                String changeSql = "update shoppingCart set number=" + number + " where code=" +
                        good.getCode()+"AND username='"+customer.getUserName()+"'";

                st = connect.prepareStatement(changeSql);
                st.execute(changeSql);
            }

            else{
                if(number>1){
                    number--;
                    String changeSql = "update shoppingCart set number=" + number + " where code=" +
                            good.getCode() +"AND username='"+customer.getUserName()+"'";

                    st = connect.prepareStatement(changeSql);
                    st.execute(changeSql);
                }

                else {
                    String changeSql = "delete from shoppingCart where code=" + good.getCode()+
                            "AND username='"+customer.getUserName()+"'";

                    st = connect.prepareStatement(changeSql);
                    st.execute(changeSql);
                }
            }
        }

        else{
            String addSql="insert into shoppingCart values ('"+customer.getUserName()+"',"+good.getCode()+
                    ",1,"+good.getPrice()+")";

            st=connect.prepareStatement(addSql);
            st.execute(addSql);
        }
    }

    public static void deleteShoppingCart(Customer customer) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connect = DriverManager.getConnection(url, username, password);

        String sql = "delete from shoppingCart where username='"+customer.getUserName()+"'";
        Statement st = connect.prepareStatement(sql);
        ResultSet rs = st.executeQuery(sql);
    }

    public static void saveBuyHistory(BuyHistory buy,Customer customer) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connect= DriverManager.getConnection(url,username,password);

        String sql="insert into buyHistory values("+buy.getCode()+",'"+customer.getUserName()+"','"+
                buy.getDate()+"','"+buy.getTime()+"',"+buy.getPrice() +","+buy.isRecieve()+")";

        Statement st=connect.prepareStatement(sql);
        st.execute(sql);
    }

    public static void deleteBuyHistory(int code,Customer customer) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connect= DriverManager.getConnection(url,username,password);

        String sql="delete from buyHistory where code="+code+" AND customerUsername='"+customer.getUserName()+"'";

        Statement st=connect.prepareStatement(sql);
        st.execute(sql);
    }

    public static void setProductBuySellHistory(BuyHistory buy,Goods good,Seller seller,Customer customer) throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connect= DriverManager.getConnection(url,username,password);

        String sql="insert into buysellhistory values ("+buy.getCode()+",'"+seller.getUserName()+"','"+
                customer.getUserName()+"',"+good.getCode()+")";

        Statement st=connect.prepareStatement(sql);
        st.execute(sql);
    }

    public static int setBuyHistoryCode() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connect= DriverManager.getConnection(url,username,password);

        String sql="select max(code) from buyHistory";
        Statement st=connect.prepareStatement(sql);
        ResultSet rs=st.executeQuery(sql);

        int code=1;
        if(rs.next()){
            code=rs.getInt(1)+1;
        };

        return code;
    }

    public static void updateAdmin() throws ClassNotFoundException, SQLException {
        Admin admin=Admin.getAdmin();
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connect= DriverManager.getConnection(url,username,password);

        String sql="update users set firstName='"+admin.getFirstName()+"',lastName='"+admin.getLastName()+
                "',password='"+admin.getPassWord()+"'";

        deleteEmail(admin.getUserName(),"admin");
        deletePhone(admin.getUserName(),"admin");

        Statement st=connect.prepareStatement(sql);
        st.execute(sql);
    }

    public static void saveProducts(Goods good,String type) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connect= DriverManager.getConnection(url,username,password);

        String productSql="insert into products values ("+ good.getCode()+",'"+type+"','"+good.getSeller().getUserName()+"')";
        Statement st=connect.prepareStatement(productSql);
        st.execute(productSql);
    }

    public static void deleteProduct(int code,String type) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connect= DriverManager.getConnection(url,username,password);

        String sql="delete from products where code="+code;
        Statement st=connect.prepareStatement(sql);
        st.execute(sql);

        String productSql="delete from "+type+" where code="+code;
        st=connect.prepareStatement(productSql);
        st.execute(productSql);
    }

    public static void saveMobile(Mobile mobile,boolean change) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connect= DriverManager.getConnection(url,username,password);
        int code=getProductCode();
        mobile.setCode(code);

        String sql="insert into mobile values ("+code+ ",'"+mobile.getName()+"','"+mobile.getBrand()+
                "',"+mobile.getPrice()+","+mobile.getNumber() +",'"+mobile.getDescription()+"','"+
                mobile.getSeller().getUserName()+"',"+ mobile.getMemoryCapacity()+","+mobile.getRam()+
                ",'"+mobile.getOperatingSystem()+"',"+ mobile.getWeight()+","+mobile.getLength()+"," +
                mobile.getWidth()+","+mobile.getWarranty()+ ","+mobile.getNumberSIM()+",'"+
                mobile.getCameraQuality()+"',"+change+")";

        Statement st=connect.prepareStatement(sql);
        st.execute(sql);
    }

    public static void changeMobile(Mobile mobile) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connect= DriverManager.getConnection(url,username,password);

        String sql="update mobile set name='"+mobile.getName()+"',brand='"+mobile.getBrand()+"',price="+
                mobile.getPrice()+",number="+mobile.getNumber()+",description='"+mobile.getDescription()+
                "',memory="+mobile.getMemoryCapacity()+",ram="+mobile.getRam()+",operatingSystem='"+
                mobile.getOperatingSystem()+"',weight="+mobile.getWeight()+",length="+mobile.getLength()+
                ",width="+mobile.getWidth()+",warranty="+mobile.getWarranty()+",simNumber="+mobile.getNumberSIM()+
                ",cameraQuality='"+mobile.getCameraQuality()+"' where code="+mobile.getCode();

        Statement st=connect.prepareStatement(sql);
        st.execute(sql);
    }

    public static void saveLaptop(Laptop laptop,boolean change) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connect= DriverManager.getConnection(url,username,password);
        int code=getProductCode();
        laptop.setCode(code);

        String sql="insert into laptop values ("+code+ ",'"+laptop.getName()+"','"+laptop.getBrand()+
                "',"+laptop.getPrice()+","+laptop.getNumber() +",'"+laptop.getDescription()+"','"+
                laptop.getSeller().getUserName()+"',"+ laptop.getMemoryCapacity()+","+laptop.getRam()+
                ",'"+laptop.getOperatingSystem()+"',"+ laptop.getWeight()+","+laptop.getLength()+"," +
                laptop.getWidth()+","+laptop.getWarranty()+ ",'"+laptop.getCpuModel()+"',"
                +laptop.getGamig()+","+change+")";

        Statement st=connect.prepareStatement(sql);
        st.execute(sql);
    }

    public static void changeLaptop(Laptop laptop) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connect= DriverManager.getConnection(url,username,password);

        String sql="update laptop set name='"+laptop.getName()+"',brand='"+laptop.getBrand()+
                "',price="+laptop.getPrice()+",number="+laptop.getNumber() +",description='"+
                laptop.getDescription()+"',memory="+ laptop.getMemoryCapacity()+",ram="+laptop.getRam()+
                ",operatingSystem='"+laptop.getOperatingSystem()+"',weight="+ laptop.getWeight()+",length="+
                laptop.getLength()+",width=" +laptop.getWidth()+",warranty="+laptop.getWarranty()+
                ",cpuModel='"+laptop.getCpuModel()+"',isGaming="+laptop.getGamig()+" where code="+laptop.getCode();

        Statement st=connect.prepareStatement(sql);
        st.execute(sql);
    }

    public static void saveClothe(Clothes clothe,boolean change) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connect= DriverManager.getConnection(url,username,password);
        int code=getProductCode();
        clothe.setCode(code);

        String sql="insert into clothe values ("+code+ ",'"+clothe.getName()+"','"+clothe.getBrand()+
                "',"+clothe.getPrice()+","+clothe.getNumber() +",'"+clothe.getDescription()+"','"+
                clothe.getSeller().getUserName()+"','"+ clothe.getCountry()+"','"+clothe.getClothingMaterial()+
                "',"+clothe.getSize()+",'"+ clothe.getType()+"',"+change+")";

        Statement st=connect.prepareStatement(sql);
        st.execute(sql);
    }

    public static void changeClothe(Clothes clothe) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connect= DriverManager.getConnection(url,username,password);

        String sql="update clothe set name='"+clothe.getName()+"',brand='"+clothe.getBrand()+
                "',price="+clothe.getPrice()+",number="+clothe.getNumber() +",description='"+
                clothe.getDescription()+"',country='"+ clothe.getCountry()+"',material='"+
                clothe.getClothingMaterial()+ "',size="+clothe.getSize()+",type='"+ clothe.getType()+
                "' where code="+clothe.getCode();

        Statement st=connect.prepareStatement(sql);
        st.execute(sql);
    }

    public static void saveShoe(Shoes shoe,boolean change) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connect= DriverManager.getConnection(url,username,password);
        int code=getProductCode();
        shoe.setCode(code);

        String sql="insert into shoe values ("+code+ ",'"+shoe.getName()+"','"+shoe.getBrand()+
                "',"+shoe.getPrice()+","+shoe.getNumber() +",'"+shoe.getDescription()+
                "','"+ shoe.getSeller().getUserName()+"','"+ shoe.getCountry()+"','"+shoe.getClothingMaterial()+
                "',"+shoe.getSize()+",'"+ shoe.getType()+"',"+change+")";

        Statement st=connect.prepareStatement(sql);
        st.execute(sql);
    }

    public static void changeShoe(Shoes shoe) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connect= DriverManager.getConnection(url,username,password);

        String sql="update shoe set name='"+shoe.getName()+"',brand='"+shoe.getBrand()+
                "',price="+shoe.getPrice()+",number="+shoe.getNumber() +",description='"+
                shoe.getDescription()+"',country='"+ shoe.getCountry()+"',material='"+
                shoe.getClothingMaterial()+ "',size="+shoe.getSize()+",type='"+ shoe.getType()+
                "' where code="+shoe.getCode();

        Statement st=connect.prepareStatement(sql);
        st.execute(sql);
    }

    public static void saveTv(Television tv,boolean change) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connect= DriverManager.getConnection(url,username,password);
        int code=getProductCode();
        tv.setCode(code);

        String sql="insert into tv values ("+code+ ",'"+tv.getName()+"','"+tv.getBrand()+
                "',"+tv.getPrice()+","+tv.getNumber() +",'"+tv.getDescription()+"','"+
                tv.getSeller().getUserName()+"',"+ tv.getEnergyDegree()+","+tv.getWarranty()+ ",'"+
                tv.getQuality()+"',"+ tv.getSize()+","+change+")";

        Statement st=connect.prepareStatement(sql);
        st.execute(sql);
    }

    public static void changeTv(Television tv) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connect= DriverManager.getConnection(url,username,password);

        String sql="update tv set name='"+tv.getName()+"',brand='"+tv.getBrand()+
                "',price="+tv.getPrice()+",number="+tv.getNumber() +",description='"+
                tv.getDescription()+"',energyDegree="+tv.getEnergyDegree()+",warranty="+
                tv.getWarranty()+",quality='"+tv.getQuality()+"',size="+tv.getSize()+
                " where code="+tv.getCode();

        Statement st=connect.prepareStatement(sql);
        st.execute(sql);
    }

    public static void saveRefrigerator(Refrigerator refrigerator,boolean change) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connect= DriverManager.getConnection(url,username,password);
        int code=getProductCode();
        refrigerator.setCode(code);

        String sql="insert into refrigerator values ("+code+ ",'"+refrigerator.getName()+"','"+refrigerator.getBrand()+
                "',"+refrigerator.getPrice()+","+refrigerator.getNumber() +",'"+refrigerator.getDescription()+
                "','"+ refrigerator.getSeller().getUserName()+"',"+ refrigerator.getEnergyDegree()+","+
                refrigerator.getWarranty()+","+refrigerator.getCapacity()+ ",'"+refrigerator.getModel()+"',"+
                refrigerator.getFreezer()+","+change+")";

        Statement st=connect.prepareStatement(sql);
        st.execute(sql);
    }

    public static void changeRefrigerator(Refrigerator refrigerator) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connect= DriverManager.getConnection(url,username,password);

        String sql="update refrigerator set name='"+refrigerator.getName()+"',brand='"+refrigerator.getBrand()+
                "',price="+refrigerator.getPrice()+",number="+refrigerator.getNumber() +",description='"+
                refrigerator.getDescription()+"',energyDegree="+refrigerator.getEnergyDegree()+",warranty="+
                refrigerator.getWarranty()+",capacity="+refrigerator.getCapacity()+",model='"+
                refrigerator.getModel()+"',hasFreeze="+refrigerator.getFreezer()+" where code="+refrigerator.getCode();

        Statement st=connect.prepareStatement(sql);
        st.execute(sql);
    }

    public static void saveGasStove(GasStove gasStove,boolean change) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connect= DriverManager.getConnection(url,username,password);
        int code=getProductCode();
        gasStove.setCode(code);

        String sql="insert into gasStove values ("+code+ ",'"+gasStove.getName()+"','"+gasStove.getBrand()+
                "',"+gasStove.getPrice()+","+gasStove.getNumber() +",'"+gasStove.getDescription()+"','"+
                gasStove.getSeller().getUserName()+"',"+ gasStove.getEnergyDegree()+","+gasStove.getWarranty()+
                ","+gasStove.getGasFlame()+ ",'"+gasStove.getType()+"',"+ gasStove.getGasOven()+","+change+")";

        Statement st=connect.prepareStatement(sql);
        st.execute(sql);
    }

    public static void changeGasStove(GasStove gasStove) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connect= DriverManager.getConnection(url,username,password);

        String sql="update gasStove set name='"+gasStove.getName()+"',brand='"+gasStove.getBrand()+
                "',price="+gasStove.getPrice()+",number="+gasStove.getNumber() +",description='"+
                gasStove.getDescription()+"',energyDegree="+gasStove.getEnergyDegree()+",warranty="+
                gasStove.getWarranty()+",flameNumber="+gasStove.getGasFlame()+",type='"+
                gasStove.getType()+"',hasOven="+gasStove.getGasOven()+" where code="+gasStove.getCode();

        Statement st=connect.prepareStatement(sql);
        st.execute(sql);
    }

    public static void saveFood(Food food,boolean change) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connect= DriverManager.getConnection(url,username,password);
        int code=getProductCode();
        food.setCode(code);

        String sql="insert into food values ("+code+ ",'"+food.getName()+"','"+food.getBrand()+
                "',"+food.getPrice()+","+food.getNumber() +",'"+food.getDescription()+"','"+
                food.getSeller().getUserName()+"','"+ food.getProductionDate()+"','"+food.getExpiryDate()+
                "',"+change+")";

        Statement st=connect.prepareStatement(sql);
        st.execute(sql);
    }

    public static void changeFood(Food food) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connect= DriverManager.getConnection(url,username,password);

        String sql="update food set name='"+food.getName()+"',brand='"+food.getBrand()+
                "',price="+food.getPrice()+",number="+food.getNumber() +",description='"+
                food.getDescription()+"',productionDate='"+food.getProductionDate()+
                "',expiryDate='"+food.getExpiryDate()+"' where code="+food.getCode();

        Statement st=connect.prepareStatement(sql);
        st.execute(sql);
    }

    public static int getProductCode() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connect= DriverManager.getConnection(url,username,password);

        String sql="select max(code) from products";
        Statement st=connect.prepareStatement(sql);
        ResultSet rs=st.executeQuery(sql);

        int code=1;
        if(rs.next()){
            code=rs.getInt(1)+1;
        };

        return code;
    }

    public static void saveGrade(Goods good,int grade) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connect= DriverManager.getConnection(url,username,password);

        String sql="select * from grade where code="+good.getCode();
        Statement st=connect.prepareStatement(sql);
        ResultSet rs=st.executeQuery(sql);

        int number=1;
        double grades=grade;

        if(rs.next()){
            number+=rs.getInt("number");
            grades+=rs.getDouble("grade");
            String upSql="update grade set number="+number+",grade="+grades+" where code="+good.getCode();
            st=connect.prepareStatement(upSql);
            st.execute(upSql);
        }

        else {
            String newSql = "insert into grade values (" + good.getCode() + "," + number + "," + grades + ")";
            st = connect.prepareStatement(newSql);
            st.execute(newSql);
        }
    }

    public static void saveDiscount(String code,String date,int productCode,int capacity) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connect= DriverManager.getConnection(url,username,password);

        String sql="insert into discount values("+productCode+",'"+code+"','"+date+"',"+capacity+")";
        Statement st=connect.prepareStatement(sql);
        st.execute(sql);
    }

    public static void updateDiscount(String code,int capacity) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connect= DriverManager.getConnection(url,username,password);

        String sql="update discount set capacity="+capacity+" where discountCode='"+capacity+"'";
        Statement st=connect.prepareStatement(sql);
        st.execute(sql);
    }

    public static void deleteDiscount(String code) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connect= DriverManager.getConnection(url,username,password);

        String sql="delete from discount where discountCode='"+code+"'";
        Statement st=connect.prepareStatement(sql);
        st.execute(sql);
    }

    public static void saveAddDiscount(Goods good,String type) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connect= DriverManager.getConnection(url,username,password);

        String sql="insert into setdiscount values("+good.getCode()+",'"+type+"')";
        Statement st=connect.prepareStatement(sql);
        st.execute(sql);
    }

    public static void saveTakeDiscount(Goods good) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connect= DriverManager.getConnection(url,username,password);

        String sql="delete from setdiscount where code="+good.getCode();
        Statement st=connect.prepareStatement(sql);
        st.execute(sql);
    }

    public static void saveAddAuction(Goods good,String type) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connect= DriverManager.getConnection(url,username,password);

        String sql="insert into auction values("+good.getCode()+",'"+type+"')";
        Statement st=connect.prepareStatement(sql);
        st.execute(sql);
    }

    public static void saveTakeAuction(Goods good) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connect= DriverManager.getConnection(url,username,password);

        String sql="delete from auction where code="+good.getCode();
        Statement st=connect.prepareStatement(sql);
        st.execute(sql);
    }

    public static void saveOpinion(Opinion op) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connect= DriverManager.getConnection(url,username,password);

        String sql="insert into opinion values ("+op.good.getCode()+",'"+op.text+"')";
        Statement st=connect.prepareStatement(sql);
        st.execute(sql);
    }
}

