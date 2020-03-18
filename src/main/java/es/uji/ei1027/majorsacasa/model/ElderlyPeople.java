package es.uji.ei1027.majorsacasa.model;

import java.time.LocalDate;
import java.util.List;

public class ElderlyPeople extends Person {

    private String justification;
    private String dniSocialWorker;

    public ElderlyPeople(String dni, String name, String secondName, String phone, LocalDate dateOfBirth, String postaddress, String email, String username, String passwd,String justification,String dniSocialWorker) {
        super(dni, name, secondName, phone, dateOfBirth, postaddress, email, username, passwd);
        this.justification=justification;
        this.dniSocialWorker=dniSocialWorker;
    }

    public ElderlyPeople() { }

    public String getDniSocialWorker() {
        return dniSocialWorker;
    }

    public void setDniSocialWorker(String dniSocialWorker) {
        this.dniSocialWorker = dniSocialWorker;
    }

    public void setJustification(String justification) {
        this.justification = justification;
    }

    public String getJustification() {
        return justification;
    }
}
