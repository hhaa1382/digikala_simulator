package Classes.Exeptions;

public class InvalidDiscountCode extends RuntimeException{
    public InvalidDiscountCode(String text){
        super(text);
    }

    public InvalidDiscountCode(){
        super("Error : Discount is not valid!!");
    }
}
