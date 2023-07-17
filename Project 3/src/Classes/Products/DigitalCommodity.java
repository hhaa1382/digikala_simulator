package Classes.Products;

import Classes.Data.WriteInfo;
import Classes.User.Admin;
import Classes.User.Seller;

import java.sql.SQLException;

public abstract class DigitalCommodity extends Goods implements Warranty,DiscountCapability{
    private int memoryCapacity;
    private int ram;
    private String operatingSystem;
    private double weight;
    private int length;
    private int width;
    private String dimensions;
    private boolean warranty;

    public DigitalCommodity(String name,String brand,double price,int number,Seller seller,String description,
                            int memoryCapacity,int ram,String operatingSystem,double weight,int length,int width,boolean warranty){

        super(name, brand, price, number, seller, description);

        this.memoryCapacity=memoryCapacity;
        this.ram=ram;
        this.operatingSystem=operatingSystem;
        this.weight=weight;
        this.length=length;
        this.warranty=warranty;
        this.width=width;
        this.dimensions=String.valueOf(length)+"*"+String.valueOf(width);
    }

    public int getMemoryCapacity(){
        return this.memoryCapacity;
    }

    public int getRam(){
        return this.ram;
    }

    public String getOperatingSystem(){
        return this.operatingSystem;
    }

    public double getWeight(){
        return this.weight;
    }

    public int getLength(){
        return this.length;
    }

    public int getWidth(){
        return this.width;
    }

    public String getDimensions(){
        return this.dimensions;
    }

    public boolean getWarranty(){
        return warranty;
    }

    public void setMemoryCapacity(int memoryCapacity){
        this.memoryCapacity=memoryCapacity;
    }

    public void setRam(int ram){
        this.ram=ram;
    }

    public void setOperatingSystem(String operatingSystem){
        this.operatingSystem=operatingSystem;
    }

    public void setWeight(double weight){
        this.weight=weight;
    }

    public void setDimensions(int length,int width){
        this.dimensions=length +"*"+ width;
    }

    public void setWarranty(boolean warranty){
        this.warranty=warranty;
    }

    public void setLength(int length) {
        setDimensions(length,width);
        this.length = length;
    }

    public void setWidth(int width) {
        setDimensions(length,width);
        this.width = width;
    }

    public void setWarrantyValue(){
        if(this.warranty){
            calculateGuaranteeTime();
            calculateGuaranteeValue();
        }
    }

    public double calculateGuaranteeValue(){
        if(warranty){
            return getPrice()*4.0/10.0;
        }
        return 0;
    }

    public int calculateGuaranteeTime(){
        if(warranty){
            return (this.memoryCapacity/10)+(this.ram*10)+((int)(weight));
        }
        return 0;
    }

    public String warrantyInfo(){
        return "Value : "+calculateGuaranteeValue()+" , Days : "+calculateGuaranteeTime();
    }

    public void addDiscount(){
        super.setDiscountPercent(0.1);
    }

    public Discount addDiscountByCode(String value, int capacity) throws SQLException, ClassNotFoundException {
        Discount discount=new Discount(0.2, value, capacity);
        String discountCode=makeDiscountCode();

        while(CheckCodeUse(discountCode)){
            discountCode=makeDiscountCode();
        }

        discount.setDiscountCode(discountCode);
        Admin admin=Admin.getAdmin();
        admin.discounts.add(discount);
        WriteInfo.saveDiscount(discountCode,discount.getDiscountValue().toString(),getCode(),capacity);

        return discount;
    }
}
