package model;

public class BeanUserPhone {
    private String name;
    private String number;
    private String email;

    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "BeanUserPhone [email=" + email + ", name=" + name + ", number=" + number + "]";
    }
}