package org.example.Clients;

public class Client {
    public int id;
    public String name;
    public String email;
    public String phone;

    public Client(){};
    public Client(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }
    public Client(int id, String name, String email, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public int getId() {return id;}
    public String getName() {return name;}
    public String getEmail() {return email;}
    public String getPhone() {return phone;}

    @Override
    public String toString(){
        return "Client ID: " + getId() + ". Name: " + getName() + ". Email: " + getEmail() + ". Phone: " +getPhone() + ". ";
    }
}
