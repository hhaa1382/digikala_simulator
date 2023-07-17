package Classes.Exeptions;

public class InvalidInput extends RuntimeException{
    public InvalidInput(String text){
        super("Error : Input is not valid!!"+text);
    }

    public InvalidInput(){
        super("Error : Input is not valid!!");
    }
}
