package es.uji.ei1027.majorsacasa.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Volunteer extends Person{

    private List<String> hobbies;
    private List<Disponibility> disponibilities;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    private Character state;
    private String passwdCheck;

    public Volunteer(){
        super();

    }

    public Volunteer(String dni, String name, String secondName, String phone, LocalDate dateOfBirth, LocalDate endDate,String postAddress, char state, String email, String username, String passwd) {
        super(dni, name, secondName, phone, dateOfBirth, postAddress, email, username, passwd);
        this.hobbies = new ArrayList<String>();
        this.disponibilities=new ArrayList<Disponibility>();
        this.endDate=endDate;
        this.state=state;
        this.passwdCheck=passwd;
    }

    public List<String> getHobbies() {
        return hobbies;
    }

    public boolean addHobbies(String hobbie){
        return hobbies.add(hobbie);
    }

    public boolean addDisponibility(int dayOfWeek, LocalDate initialTime, LocalDate finalTime, boolean open){
        return disponibilities.add(new Disponibility(dayOfWeek,initialTime, finalTime, open));
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


}
