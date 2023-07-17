package Classes.Products;

public class Options {
    private final static String home_Menu="\nHome\n\n1-User part\n2-Products list\n3-Exit\n\nEnter a option : ";

    private final static String user_Part_Menu="\nUser\n\n1-Register\n2-Login\n3-Back\n\nEnter a option : ";

    private final static String register_Menu="\nRegister\n\n1-Customer\n2-Seller\n\nEnter a option : ";

    private final static String login_Menu="\nLogin\n\n1-Manager\n2-Seller\n3-Customer\n\nEnter a option : ";

    private final static String customer_Menu="\nCustomer\n\n1-Information\n2-Shopping cart\n3-Shopping history\n"+
            "4-Account Credit\n5-Change information\n6-Products\n7-Logout\n\nEnter a option : ";

    private final static String Admin_Menu="\nAdmin\n\n1-Information\n2-Change information\n3-Request list\n"+
            "4-Add/Remove product\n5-Users list\n6-Remove user\n7-Product list\n8-Logout\n\nEnter a option : ";

    private final static String seller_Menu="\nSeller\n\n1-Information\n2-Sales history list\n3-Change information\n"+
            "4-Product list/Discount part\n5-Add product request\n6-Logout\n\nEnter a option : ";

    private final static String change_Info="\n1-Yes\n2-No\n\n";

    private final static String show_Requests="\n1-Accept  2-Delete  3-Next  4-Cancel\n\nEnter a option : ";

    private final static String show_Information="\nList\n\n1-Customer\n2-Seller\n3-Cancel\n\nEnter a option : ";

    private final static String show_User_List="\n1-Next\n2-Cancel\n\n";

    private final static String goods_list_category_Menu="\nCategories\n\n1-Digital Commodity\n"+
            "2-Clothing Material\n3-Home Appliances\n4-Food\n5-Back\n\nEnter a option : ";

    private final static String digital_Menu="\nDigitals category\n\n1-Mobile\n2-Laptop\n\nEnter a option : ";

    private final static String clothing_Menu="\nClothing category\n\n1-Clothes\n2-Shoes\n\nEnter a option : ";

    private final static String home_Material_Menu="\nHome Products category\n\n1-TV\n2-Refrigerator\n3-Gas Stove\n\nEnter a option : ";

    private final static String food_Menu="\nFood category\n\n1-Foods\n2-Search\n\nEnter a option : ";

    private final static String filter_menu="\nFilter by :\n\n0-Show list\n1-Brand\n2-Product Code\n3-Exist\n4-Price\n";

    private final static String products_list_part="-1-Back\n0-Set Changes\n1-Name\n2-Brand\n3-Price\n4-Number\n5-Description\n";

    private final static String mobile_List=products_list_part+"6-Memory Capacity\n7-Ram\n8-Operating System\n"+
            "9-Weight\n10-Has Warranty\n11-Dimensions\n12-Number of SIM\n13-Camera Quality\n\nEnter a option : ";

    private final static String laptop_List=products_list_part+"6-Memory Capacity\n7-Ram\n"+
            "8-Operating System\n9-Weight\n10-Has Warranty\n11-Dimensions\n12-Cpu Model\n13-Is gaming\n\nEnter a option : ";

    private final static String clothes_List=products_list_part+"6-Country\n7-Clothing Material\n8-Size\n9-Type\n\nEnter a option : ";

    private final static String shoes_List=products_list_part+"6-Country\n7-Clothing Material\n8-Size\n9-Type\n\nEnter a option : ";

    private final static String tv_List=products_list_part+"6-Energy Degree\n7-Has warranty\n8-Tv Quality\n9-Size\n\nEnter a option : ";

    private final static String refrigerator_List=products_list_part+"6-Energy Degree\n7-Has warranty\n"+
            "8-Capacity\n9-Model\n10-Has freezer\n\nEnter a option : ";

    private final static String gasStove_List=products_list_part+"6-Energy Degree\n7-Has warranty\n"+
            "8-Gas flame number\n9-Type\n10-Has gas oven\n\nEnter a option : ";

    private final static String food_List=products_list_part+"6-Production date\n7-Expiry date\n\nEnter a option : ";

    static public String getHomeMenu(){
        return home_Menu;
    }

    static public String getUserPartMenu(){
        return user_Part_Menu;
    }

    static public String getRegisterMenu(){
        return register_Menu;
    }

    static public String getLoginMenu(){
        return login_Menu;
    }

    static public String getCustomerMenu(){
        return customer_Menu;
    }

    static public String getAdminMenu(){
        return Admin_Menu;
    }

    static public String getSellerMenu(){
        return seller_Menu;
    }

    static public String getChangeOptions(){
        return change_Info;
    }

    static public String getShowRequestsOptions(){
        return show_Requests;
    }

    static public String getShowListOptions(){
        return show_Information;
    }

    static public String getListOptions(){
        return show_User_List;
    }

    static public String getGoodsListMenu(){
        return goods_list_category_Menu;
    }

    static public String getDigitalMenu(){
        return digital_Menu;
    }

    static public String getClotheOptions(){
        return clothing_Menu;
    }

    static public String getHomeMaterialOptions(){
        return home_Material_Menu;
    }

    static public String getFoodMenu(){
        return food_Menu;
    }

    static public String getFilterMenu(){
        return filter_menu;
    }

    static public String getMobileMenu(){
        return mobile_List;
    }

    static public String getLaptopMenu(){
        return laptop_List;
    }

    static public String getClothesMenu(){
        return clothes_List;
    }

    static public String getShoesMenu(){
        return shoes_List;
    }

    static public String getTvMenu(){
        return tv_List;
    }

    static public String getRefrigeratorMenu(){
        return refrigerator_List;
    }

    static public String getGasStoveMenu(){
        return gasStove_List;
    }

    static public String getFoodChangeMenu(){
        return food_List;
    }
}
