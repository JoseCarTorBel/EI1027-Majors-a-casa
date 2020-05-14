package es.uji.ei1027.majorsacasa.model;

public class Hobbie {

    private String dni;
    private String hobbie;

    public Hobbie(String dni, String hobbie) {
        this.dni = dni;
        this.hobbie = hobbie;
    }

    public Hobbie() {
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getHobbie() {
        return hobbie;
    }

    public void setHobbie(String hobbie) {
        this.hobbie = hobbie;
    }
}
