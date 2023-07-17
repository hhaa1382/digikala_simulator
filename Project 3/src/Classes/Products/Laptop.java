package Classes.Products;

import java.sql.SQLException;
import java.util.Random;

import Classes.Data.WriteInfo;
import Classes.User.Seller;

public class Laptop extends DigitalCommodity{
    private String cpuModel;
    private boolean gaming;

    public Laptop(String name,String brand,double price,int number,Seller seller,String description,int memoryCapacity,int ram,
                  String operatingSystem,double weight,int length,int width,boolean warranty,String cpuModel,boolean gaming){

        super(name, brand, price, number, seller, description, memoryCapacity, ram, operatingSystem, weight, length, width,warranty);

        this.cpuModel=cpuModel;
        this.gaming=gaming;
        super.setWarrantyValue();
    }

    public String showInfo(){
        return super.toString()+"\nDescription : "+super.getDescription()+"\n\nInformation :\n\nOperating System : "+super.getOperatingSystem()+
                "\nMemory Capacity : "+super.getMemoryCapacity()+"\nRam : "+super.getRam()+"\nCpu Model : "+this.cpuModel+"\nIs Gaming : "+this.gaming+
                "\nWeight : "+super.getWeight()+"\nDimensions : "+super.getDimensions()+"\nWarranty : "+super.warrantyInfo();
    }

    public void setCpuModel(String model){
        this.cpuModel=model;
    }

    public void setGamig(Boolean check){
        this.gaming=check;
    }

    public String getCpuModel(){
        return this.cpuModel;
    }

    public boolean getGamig(){
        return this.gaming;
    }

    public int compareTo(Goods good){
        Laptop laptop=(Laptop)good;

        if(this.getName().compareTo(laptop.getName())>0){
            return 1;
        }

        else if(this.getName().compareTo(laptop.getName())<0){
            return -1;
        }

        else{
            if(this.getRam()<laptop.getRam()){
                return 1;
            }

            else if(this.getRam()>laptop.getRam()){
                return -1;
            }

            else{
                if(!this.getGamig() && laptop.getGamig()){
                    return 1;
                }

                else if(this.getGamig() && !laptop.getGamig()){
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
        return "lap"+String.valueOf(number);
    }
}
