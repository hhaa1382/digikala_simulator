package Classes.Products;

import Classes.Data.WriteInfo;
import Classes.User.Seller;

import java.sql.SQLException;

public class Television extends HomeAppliances{
    private String TvQuality;
    private double size;

    public Television(String name,String brand,double price,int number,Seller seller,String description,
                      double energyDegree,boolean warranty,String TvQuality,double size){

        super(name, brand, price, number, seller, description, energyDegree, warranty);

        this.TvQuality=TvQuality;
        this.size=size;
        super.setWarrantyValue();
    }

    public String showInfo(){
        return super.toString()+"\nDescription : "+super.getDescription()+"\n\nInformation :\n\nEnergy Degree : "+super.getEnergyDegree()+
                "\nQuality : "+this.TvQuality+"\nSize : "+this.size+"\nWarranty : "+super.warrantyInfo();
    }

    public void setQuality(String quality){
        this.TvQuality=quality;
    }

    public void setSize(double size){
        this.size=size;
    }

    public String getQuality(){
        return this.TvQuality;
    }

    public double getSize(){
        return this.size;
    }

    public int compareTo(Goods good){
        Television tv=(Television)good;

        if(this.getName().compareTo(tv.getName())>0){
            return 1;
        }

        else if(this.getName().compareTo(tv.getName())<0){
            return -1;
        }

        else{
            if(this.getEnergyDegree()>tv.getEnergyDegree()){
                return 1;
            }

            else if(this.getEnergyDegree()<tv.getEnergyDegree()){
                return -1;
            }

            else{
                if(this.getSize()<tv.getSize()){
                    return 1;
                }

                else if(this.getSize()>tv.getSize()){
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
            if(this.TvQuality.equals("hd") || TvQuality.equals("Hd")){
                days+=50;
            }

            else if(TvQuality.equals("full hd") || TvQuality.equals("Full Hd")){
                days+=100;
            }
            days+=(int)size;
        }
        return days;
    }
}
