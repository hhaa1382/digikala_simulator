package Classes.Products;

import Classes.User.Seller;

public class GasStove extends HomeAppliances{
    private int gasFlameNumber;
    private String type;
    private boolean hasGasOven;

    public GasStove(String name,String brand,double price,int number,Seller seller,String description,
                    double energyDegree,boolean warranty,int gasFlameNumber,String type,boolean hasGasOven){

        super(name, brand, price, number, seller, description, energyDegree, warranty);

        this.gasFlameNumber=gasFlameNumber;
        this.type=type;
        this.hasGasOven=hasGasOven;
        super.setWarrantyValue();
    }

    public String showInfo(){
        return super.toString()+"\nDescription : "+super.getDescription()+"\n\nInformation :\n\nEnergy Degree : "+super.getEnergyDegree()+
                "\nGas Flame Number : "+this.gasFlameNumber+"\nType : "+this.type+"\nIs Gas Oven : "+
                this.hasGasOven+"\nWarranty : "+super.warrantyInfo();
    }

    public void setGasFlame(int number){
        this.gasFlameNumber=number;
    }

    public void setType(String type){
        this.type=type;
    }

    public void setGasOven(boolean check){
        this.hasGasOven=check;
    }

    public int getGasFlame(){
        return this.gasFlameNumber;
    }

    public String getType(){
        return this.type;
    }

    public boolean getGasOven(){
        return this.hasGasOven;
    }

    public int compareTo(Goods good){
        GasStove gasStove=(GasStove)good;

        if(this.getName().compareTo(gasStove.getName())>0){
            return 1;
        }

        else if(this.getName().compareTo(gasStove.getName())<0){
            return -1;
        }

        else{
            if(this.getEnergyDegree()<gasStove.getEnergyDegree()){
                return 1;
            }

            else if(this.getEnergyDegree()>gasStove.getEnergyDegree()){
                return -1;
            }

            else{
                if(this.getGasFlame()<gasStove.getGasFlame()){
                    return 1;
                }

                else if(this.getGasFlame()>gasStove.getGasFlame()){
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
            if(hasGasOven){
                days+=50;
            }
            days+=gasFlameNumber*10;
        }

        return days;
    }
}
