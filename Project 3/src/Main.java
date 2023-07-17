import java.sql.SQLException;
import java.util.ArrayList;

import Classes.Categories.ClothingCategory;
import Classes.Categories.DigitalCategory;
import Classes.Categories.FoodCategory;
import Classes.Categories.HomeCategory;
import Classes.Data.ReadInfo;
import Classes.Data.WriteInfo;
import Classes.Procces.AdminPart;
import Classes.Procces.MainPart;
import Classes.Products.*;
import Classes.User.Admin;
import Classes.User.Customer;
import Classes.User.Seller;

import javax.swing.*;

public class Main{
    public static void main(String[] args)throws SQLException, ClassNotFoundException {
        readInfo();
        MainPart mainPart=new MainPart();
    }

    static void readInfo() throws SQLException, ClassNotFoundException {
        ReadInfo.readAdmin();
        Admin admin=Admin.getAdmin();
        ReadInfo.readProducts(admin.products);

        ReadInfo.readCustomers();
        ReadInfo.readSellers();
        ReadInfo.readSellerProducts();
        ReadInfo.readSellerRequest();
        ReadInfo.readAddRequests();
        ReadInfo.readDeleteRequest();
        ReadInfo.readChangeRequest();
        ReadInfo.readShoppingCarts();
        ReadInfo.readBuyHistory();
        ReadInfo.readSellHistory();
        ReadInfo.readDiscountCodes();
        ReadInfo.readDiscounts();
        ReadInfo.readAuction();


        DigitalCategory d=DigitalCategory.getCategory();
        ReadInfo.readMobile(d.mobiles);
        ReadInfo.readLaptop(d.laptops);

        ClothingCategory c=ClothingCategory.getCategory();
        ReadInfo.readClothe(c.clothes);
        ReadInfo.readShoe(c.shoes);

        HomeCategory h=HomeCategory.getCategory();
        ReadInfo.readTv(h.televisions);
        ReadInfo.readRefrigerator(h.refrigerators);
        ReadInfo.readGasStove(h.gasStoves);

        FoodCategory f=FoodCategory.getCategory();
        ReadInfo.readFood(f.foods);
    }
}


