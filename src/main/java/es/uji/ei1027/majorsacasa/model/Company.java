package es.uji.ei1027.majorsacasa.model;

import org.apache.tomcat.jni.Local;

import java.time.LocalDate;

public class Company {
    private String cif;
    private String name;
    private String nifContact;
    private String personalContact;
    private String phoneContact;
    private String postAddress;
    private String email;
    private String username;
    private String passwd;
    private LocalDate fechaAlta;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public Company(){}

    public Company(String cif, String name, String personalContact, String phoneContact, String postAddress, String email, String username, String passwd, LocalDate fechaAlta) {
        this.cif = cif;
        this.name = name;
        this.personalContact = personalContact;
        this.phoneContact = phoneContact;
        this.postAddress = postAddress;
        this.email=email;
        this.username=username;
        this.passwd=passwd;
        this.fechaAlta=fechaAlta;
    }

    // GETER
    public String getCif() {
        return cif;
    }

    public String getName() {
        return name;
    }

    public String getNifContact() {return nifContact;}

    public String getPersonalContact() {
        return personalContact;
    }

    public String getPhoneContact() {
        return phoneContact;
    }

    public String getPostAddress() {
        return postAddress;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getFechaAlta() { return fechaAlta; }

    // SETTER
    public void setCif(String cif) {
        this.cif = cif;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNifContact(String nifContact){this.nifContact=nifContact;}

    public void setPersonalContact(String personalContact) {
        this.personalContact = personalContact;
    }

    public void setPhoneContact(String phoneContact) {
        this.phoneContact = phoneContact;
    }

    public void setPostAddress(String postAddress) {
        this.postAddress = postAddress;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void  setFechaAlta(LocalDate fechaAlta) {this.fechaAlta = fechaAlta; }
}
