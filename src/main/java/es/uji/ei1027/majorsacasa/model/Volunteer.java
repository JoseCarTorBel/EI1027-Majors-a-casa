package es.uji.ei1027.majorsacasa.model;

import java.sql.Date;
import java.util.List;

public class Volunteer extends Person{

    private List<String> hobbies;

    public Volunteer(){
        super();

    }

    public Volunteer(String dni, String name, String secondName, String phone, Date dateOfBirith, String postaddress, char state, String mail, String username, String password, List<String> hobbies) {
        super(dni, name, secondName, phone, dateOfBirith, postaddress, state, mail, username, password);
        this.hobbies = hobbies;
    }

    public List<String> getHobbies() {
        return hobbies;
    }

    public boolean addHobbies(String hobbie){
        return hobbies.add(hobbie);
    }
}
