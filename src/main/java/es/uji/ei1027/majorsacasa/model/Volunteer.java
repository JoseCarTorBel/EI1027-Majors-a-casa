package es.uji.ei1027.majorsacasa.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Volunteer extends Person{

    private List<String> hobbies;
    private List<Disponibility> disponibilities;

    public Volunteer(){
        super();

    }

    public Volunteer(String dni, String name, String secondName, String phone, Date dateOfBirith, String postaddress, char state, String mail, String username, String password) {
        super(dni, name, secondName, phone, dateOfBirith, postaddress, state, mail, username, password);
        this.hobbies = new ArrayList<String>();
        this.disponibilities=new ArrayList<Disponibility>();
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
}
