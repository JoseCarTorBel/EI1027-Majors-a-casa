package es.uji.ei1027.majorsacasa.model;

public class Company {
    private String cif;
    private String name;
    private String personalContact;
    // TODO -> PERSONAL CONTACT PUEDE SER UNA PERSON ?????????
    //private Person pesonalContact

    private String phoneContact;
    private String postAddress;
    private String email;
    //TODO CREAR CLASE ADDRESS
    //private Address address;

    public Company(){}

    public Company(String cif, String name, String personalContact, String phoneContact, String postAddress,String email) {
        this.cif = cif;
        this.name = name;
        this.personalContact = personalContact;
        this.phoneContact = phoneContact;
        this.postAddress = postAddress;
        this.email=email;
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

    public String getPostAddress() {
        return postAddress;
    }

    public String getEmail() {
        return email;
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

    public void setPostAddress(String address) {
        this.postAddress = postAddress;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
