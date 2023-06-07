package org.owasp.workshop.candies.administration.dtos;

public class StudentUser {

    private String username;
    private String password;
    private String email;
    private String firstName;
    private String surname;
    private String address;
    private String city;

    public StudentUser(){

    }

    public StudentUser(String username, String password, String email, String firstName, String surname){
        this(username, password, email, firstName, surname, "Unknown Address", "Unknown City");
    }

    public StudentUser(String username, String password, String email, String firstName, String surname, String address, String city) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.surname = surname;
        this.address = address;
        this.city = city;
    }

    public StudentUser(StudentUser studentUser) {
        this.username = studentUser.getUsername();
        this.password = studentUser.getPassword();
        this.email = studentUser.getEmail();
        this.firstName = studentUser.getFirstName();
        this.surname = studentUser.getSurname();
        this.address = studentUser.getAddress();
        this.city = studentUser.city;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
