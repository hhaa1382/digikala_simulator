package Classes.User;

public abstract class User{
    private String firstName,lastName;
    private String email,phoneNumber;

    private String userName,passWord;

    User(){}

    User(String firstName,String lastName,String email,String phoneNumber,String userName,char[] passWord){
        this.firstName=firstName;
        this.lastName=lastName;
        this.email=email;
        this.phoneNumber=phoneNumber;
        this.userName=userName;
        this.passWord=String.valueOf(passWord);
    }

    public abstract String personalInformation();

    public String infoForSave(){
        return firstName+"-"+lastName+"-"+email+"-"+phoneNumber+"-"+userName+"-"+passWord;
    }

    public void setFirstName(String name){
        this.firstName=name;
    }

    public void setLastName(String name){
        this.lastName=name;
    }

    public void setEmail(String email){
        this.email=email;
    }

    public void setPhoneNumber(String number){
        this.phoneNumber=number;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassWord(char[] passWord){ this.passWord=String.valueOf(passWord); }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassWord() {
        return passWord;
    }
}
