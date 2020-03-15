package es.uji.ei1027.majorsacasa.model;

import java.time.LocalDate;

public class Person {

    private String dni;
    private String name;
    private String secondName;
    private String phone;
    private LocalDate dateOfBirth;
    private String postAddress;
    private String email;
    private String username;
    private String passwd;

    public Person(){}

    public Person(String dni, String name, String secondName, String phone, LocalDate dateOfBirith, String postAddress,String email, String username, String passwd) {
        this.dni = dni;
        this.email=email;
        this.name = name;
        this.secondName = secondName;
        this.phone = phone;
        this.dateOfBirth = dateOfBirith;
        this.postAddress = postAddress;
        this.username = username;
        this.passwd = passwd;
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

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setPostAddress(String postAddress) {
        this.postAddress = postAddress;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
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

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getPostAddress() {
        return postAddress;
    }

    public String getUsername() {
        return username;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setEmail(String mail) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
