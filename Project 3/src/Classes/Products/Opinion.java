package Classes.Products;

import java.io.Serializable;

import Classes.User.Customer;

public class Opinion{
    public Customer customer;
    public Goods good;
    public String text;
    public String condition;
    public boolean buyCheck;

    public String showInfo(){
        return "\nCustomer name : "+customer.getFirstName()+" "+
                customer.getLastName()+"\nOpinion : "+text+"\nCustomer buy product : "+buyCheck;
    }
}
