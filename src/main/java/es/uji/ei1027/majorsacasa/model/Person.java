package es.uji.ei1027.majorsacasa.model;

import java.sql.Date;

public class Person {

    private String dni;
    private String name;
    private String secondName;
    private String phone;
    private Date dateOfBirith;
    private String postaddress;
    private char state;
    private String mail;
    private String username;
    private String password;

    public Person(){}

    public Person(String dni, String name, String secondName, String phone, Date dateOfBirith, String postaddress,
                  char state,String mail, String username, String password) {
        this.dni = dni;
        this.mail=mail;
        this.name = name;
        this.secondName = secondName;
        this.phone = phone;
        this.dateOfBirith = dateOfBirith;
        this.postaddress = postaddress;
        this.state = state;
        this.username = username;
        this.password = password;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setDateOfBirith(Date dateOfBirith) {
        this.dateOfBirith = dateOfBirith;
    }

    public void setPostaddress(String postaddress) {
        this.postaddress = postaddress;
    }

    public void setState(char state) {
        this.state = state;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDni() {
        return dni;
    }

    public String getName() {
        return name;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getPhone() {
        return phone;
    }

    public Date getDateOfBirith() {
        return dateOfBirith;
    }

    public String getPostaddress() {
        return postaddress;
    }

    public char getState() {
        return state;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }


    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMail() {
        return mail;
    }
}
