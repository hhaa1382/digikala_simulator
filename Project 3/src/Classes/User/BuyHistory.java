package Classes.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import Classes.Products.Goods;

public class BuyHistory{
    private int code;
    private String date;
    private String time;
    private double price;
    private boolean recieve=true;

    ArrayList <Goods> products=new ArrayList<>();

    public int getCode() {
        return code;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public double getPrice() {
        return price;
    }

    public boolean isRecieve() {
        return recieve;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setRecieve(boolean recieve) {
        this.recieve = recieve;
    }

    public void setProducts(Goods good) {
        products.add(good);
    }

    void setDateTime(){
        DateTimeFormatter Time=DateTimeFormatter.ofPattern("HH:mm:ss");
        DateTimeFormatter Date=DateTimeFormatter.ofPattern("yyyy/MM/dd");

        LocalDateTime now=LocalDateTime.now();
        time= Time.format(now);
        date= Date.format(now);
    }

    String setList(){
        StringBuilder list=new StringBuilder();
        for(int i=0;i<products.size();i++){
            list.append(products.get(i).toString()+"\n");
        }
        return list.toString();
    }

    public String showInfo(){
        return "Facture code : "+String.valueOf(code)+"\nDate : "+date+"\nTime : "+time+"\nRecieve : "+recieve+
                "\n\nProduct list :\n"+setList()+"\n";
    }
}
