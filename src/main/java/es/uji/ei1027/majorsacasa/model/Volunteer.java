package es.uji.ei1027.majorsacasa.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Volunteer extends Person{

    @DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
    private LocalDate endDate;
    private Character state;
    private String passwdCheck;

    public Volunteer(){
        super();

    }

    public Volunteer(String dni, String name, String secondName, String phone, LocalDate dateOfBirth, LocalDate endDate,String postAddress, char state, String email, String username, String passwd) {
        super(dni, name, secondName, phone, dateOfBirth, postAddress, email, username, passwd);
        this.endDate=endDate;
        this.state=state;
        this.passwdCheck=passwd;
    }


    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Character getState() {
        return state;
    }

    public void setState(Character state) {
        this.state = state;
    }

    public void setPasswdCheck(String passwdCheck){
        this.passwdCheck=passwdCheck;
    }

    public String getPasswdCheck(){
        return this.passwdCheck;
    }

    @Override
    public String toString() {
        return "Volunteer{" +
                ", endDate=" + endDate +
                ", state=" + state +
                ", passwdCheck='" + passwdCheck + '\'' +
                "} " + super.toString();
    }
}
