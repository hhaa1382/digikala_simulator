package Classes.Products;

import java.time.LocalDate;

public class Discount implements Cloneable{
    private double discountPercent;
    private LocalDate discountValue;
    private int capacity;
    private String discountCode;

    public Discount(double discountPercent,String discountValue,int capacity){
        this.discountPercent=discountPercent;
        this.discountValue=LocalDate.parse(discountValue);
        this.capacity=capacity;
    }

    public void setDiscountPercent(double discountPercent){
        this.discountPercent=discountPercent;
    }

    public void setDiscountValue(String discountValue){
        this.discountValue=LocalDate.parse(discountValue);
    }

    public void setCapacity(int capacity){
        this.capacity=capacity;
    }

    public void setDiscountCode(String discountCode){
        this.discountCode=discountCode;
    }

    public double getDiscountPercent(){
        return this.discountPercent;
    }

    public LocalDate getDiscountValue(){
        return this.discountValue;
    }

    public int getCapacity(){
        return this.capacity;
    }

    public String getDiscountCode(){
        return this.discountCode;
    }

    public String toString(){
        return "code : "+discountCode+"   percent : "+discountPercent+"   Value : "+discountValue.toString()+"   Capacity : "+capacity;
    }

    @Override
    public Object clone(){
        Discount temp=new Discount(this.getDiscountPercent(), this.getDiscountValue().toString(), getCapacity());
        return temp;
    }
}
