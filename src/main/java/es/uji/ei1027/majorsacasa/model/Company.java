package es.uji.ei1027.majorsacasa.model;

public class Company {
    private String cif;
    private String name;
    private String personalContact;
    // TODO -> PERSONAL CONTACT PUEDE SER UNA PERSON ?????????
    //private Person pesonalContact

    private String phoneContact;
    private String address;
    private String mail;
    //TODO CREAR CLASE ADDRESS
    //private Address address;

    public Company(){}

    public Company(String cif, String name, String personalContact, String phoneContact, String address, String mail) {
        this.cif = cif;
        this.name = name;
        this.personalContact = personalContact;
        this.phoneContact = phoneContact;
        this.address = address;
        this.mail=mail;
    }

    // GETER
    public String getCif() {
        return cif;
    }

    public String getName() {
        return name;
    }

    public String getPersonalContact() {
        return personalContact;
    }

    public String getPhoneContact() {
        return phoneContact;
    }

    public String getAddress() {
        return address;
    }

    public String getMail() {
        return mail;
    }

    // SETTER
    public void setCif(String cif) {
        this.cif = cif;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPersonalContact(String personalContact) {
        this.personalContact = personalContact;
    }

    public void setPhoneContact(String phoneContact) {
        this.phoneContact = phoneContact;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
