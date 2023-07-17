package Classes.Exeptions;

public class InvalidBuy extends RuntimeException{
    public InvalidBuy(String text){
        super("Error : Product is not valid!!"+text);
    }

    public InvalidBuy(){
        super("Error : Product is not valid!!");
    }
}
