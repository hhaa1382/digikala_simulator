package Classes.Products;

import Classes.Data.WriteInfo;
import Classes.User.Seller;

import java.sql.SQLException;

public class Refrigerator extends HomeAppliances{
    private double capacity;
    private String model;
    private boolean hasFreezer;

    public Refrigerator(String name,String brand,double price,int number,Seller seller,String description,
                        double energyDegree,boolean warranty,double capacity,String model,boolean hasFreezer){

        super(name, brand, price, number, seller, description, energyDegree, warranty);

        this.capacity=capacity;
        this.model=model;
        this.hasFreezer=hasFreezer;
        super.setWarrantyValue();
    }

    public String showInfo(){
        return super.toString()+"\nDescription : "+super.getDescription()+"\n\nInformation :\n\nEnergy Degree : "
                +super.getEnergyDegree()+"\nCapacity : "+this.capacity+"\nModel : "+this.model+"\nhas freezer : "+
                this.hasFreezer+"\nWarranty : "+super.warrantyInfo();
    }

    public void setCapacity(double capacity){
        this.capacity=capacity;
    }

    public void setModel(String model){
        this.model=model;
    }

    public void setFreezer(boolean check){
        this.hasFreezer=check;
    }

    public double getCapacity(){
        return this.capacity;
    }

    public String getModel(){
        return this.model;
    }

    public boolean getFreezer(){
        return this.hasFreezer;
    }

    public int compareTo(Goods good){
        Refrigerator refrigerator=(Refrigerator)good;

        if(this.getName().compareTo(refrigerator.getName())>0){
            return 1;
        }

        else if(this.getName().compareTo(refrigerator.getName())<0){
            return -1;
        }

        else{
            if(this.getEnergyDegree()<refrigerator.getEnergyDegree()){
                return 1;
            }

            else if(this.getEnergyDegree()>refrigerator.getEnergyDegree()){
                return -1;
            }

            else{
                if(this.getCapacity()<refrigerator.getCapacity()){
                    return 1;
                }

                else if(this.getCapacity()>refrigerator.getCapacity()){
                    return -1;
                }

                else{
                    return super.compareTo(good);
                }
            }
        }
    }

    public int calculateGuaranteeTime(){
        int days=0;

        if(getWarranty()){
            if(hasFreezer){
                days+=50;
            }
            days+=(int)capacity;
        }
        return days;
    }
}

