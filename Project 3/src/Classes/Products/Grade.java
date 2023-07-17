package Classes.Products;

import java.io.Serializable;

public class Grade implements Serializable{
    int number=0;
    int sum=0;

    public void setGrade(int number){
        this.number++;
        sum+=number;
    }

    public double getGrade(){
        if(number==0){
            return 0;
        }
        return (double)sum/(double)number;
    }
}
