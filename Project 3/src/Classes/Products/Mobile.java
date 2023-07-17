package Classes.Products;

import java.sql.SQLException;
import java.util.Random;

import Classes.Data.WriteInfo;
import Classes.User.Seller;

public class Mobile extends DigitalCommodity{
    private int numberOfSIM;
    private String cameraQuality;

    public Mobile(String name,String brand,double price,int number,Seller seller,String description,int memoryCapacity,int ram,
                  String operatingSystem,double weight,int length,int width,boolean warranty,int numberOfSIM,String cameraQuality){

        super(name, brand, price, number, seller, description, memoryCapacity, ram, operatingSystem, weight, length, width,warranty);

        this.numberOfSIM=numberOfSIM;
        this.cameraQuality=cameraQuality;
        super.setWarrantyValue();
    }

    public String showInfo(){
        return super.toString()+"\nDescription : "+super.getDescription()+"\n\nInformation :\nOperating System : "
                +super.getOperatingSystem()+ "\nRam : "+super.getRam()+"\nMemory Capacity : "+super.getMemoryCapacity()
                +"\nSIM Number : "+this.numberOfSIM+ "\nCamera Quality : "+this.cameraQuality+ "\nWeight : "+super.getWeight()
                +"\nDimensions : "+super.getDimensions()+"\nWarranty : "+super.warrantyInfo();
    }

    public void setCameraQuality(String quality){
        this.cameraQuality=quality;
    }

    public void setNumberSIM(int number){
        this.numberOfSIM=number;
    }

    public String getCameraQuality(){
        return this.cameraQuality;
    }

    public int getNumberSIM(){
        return this.numberOfSIM;
    }

    public int compareTo(Goods good){
        Mobile mobile=(Mobile)good;

        if(this.getName().compareTo(mobile.getName())>0){
            return 1;
        }

        else if(this.getName().compareTo(mobile.getName())<0){
            return -1;
        }

        else{
            if(this.getMemoryCapacity()<mobile.getMemoryCapacity()){
                return 1;
            }

            else if(this.getMemoryCapacity()>mobile.getMemoryCapacity()){
                return -1;
            }

            else{
                if(this.getNumberSIM()<mobile.getNumberSIM()){
                    return 1;
                }

                else if(this.getNumberSIM()>mobile.getNumberSIM()){
                    return -1;
                }

                else{
                    return super.compareTo(good);
                }
            }
        }
    }

    public String makeDiscountCode() {
        Random random=new Random();
        int number=random.nextInt(9000)+1000;
        return "mob"+String.valueOf(number);
    }
}

