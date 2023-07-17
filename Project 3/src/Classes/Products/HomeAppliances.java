package Classes.Products;

import Classes.User.Seller;

public abstract class HomeAppliances extends Goods implements Warranty{
    private double energyDegree;
    private boolean warranty;

    public HomeAppliances(String name,String brand,double price,int number,
                          Seller seller,String description,double energyDegree,boolean warranty){

        super(name, brand, price, number, seller, description);

        this.energyDegree=energyDegree;
        this.warranty=warranty;
    }

    private double filterEnergyDegree=0;
    private boolean filterWarranty=false;

    public double getEnergyDegree(){
        return energyDegree;
    }

    public boolean getWarranty(){
        return warranty;
    }

    public void setEnergyDegree(double energyDegree){
        this.energyDegree=energyDegree;
    }

    public void setWarranty(boolean warranty){
        this.warranty=warranty;
    }

    public void setWarrantyValue(){
        if(this.warranty){
            calculateGuaranteeTime();
            calculateGuaranteeValue();
        }
    }

    public double getFilterEnergyDegree(){
        return this.filterEnergyDegree;
    }

    public boolean getFilterWarranty(){
        return this.filterWarranty;
    }

    public void setFilterEnergyDegree(double energyDegree){
        this.filterEnergyDegree=energyDegree;
    }

    public void setFilterWarranty(boolean warranty){
        this.filterWarranty=warranty;
    }

    public double calculateGuaranteeValue(){
        if(warranty){
            return (getPrice()*5.0/10.0)-(energyDegree*2.0/10.0);
        }
        return 0;
    }

    public String warrantyInfo(){
        return "Value : "+calculateGuaranteeValue()+" , Days : "+calculateGuaranteeTime();
    }
}
