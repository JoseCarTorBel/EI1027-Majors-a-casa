package es.uji.ei1027.majorsacasa.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Volunteer extends Person{

    private List<String> hobbies;
    private List<Disponibility> disponibilities;
    private Date endDate;
    private char state;

    public Volunteer(){
        super();

    }

    public Volunteer(String dni, String name, String secondName, String phone, Date dateOfBirith, Date endDate,String postAddress, char state, String email, String username, String passwd) {
        super(dni, name, secondName, phone, dateOfBirith, postAddress, email, username, passwd);
        this.hobbies = new ArrayList<String>();
        this.disponibilities=new ArrayList<Disponibility>();
        this.endDate=endDate;
        this.state=state;
    }

    public List<String> getHobbies() {
        return hobbies;
    }

    public boolean addHobbies(String hobbie){
        return hobbies.add(hobbie);
    }

    public boolean addDisponibility(Date dayOfWeek, Date initialTime, Date finalTime){
        return disponibilities.add(new Disponibility(dayOfWeek,initialTime, finalTime));
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public char getState() {
        return state;
    }

    public void setState(char state) {
        this.state = state;
    }
}
