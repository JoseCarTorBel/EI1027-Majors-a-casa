package es.uji.ei1027.majorsacasa.model;

import java.util.ArrayList;
import java.util.List;

public class SocialWorker {

    private String name;
    private Integer phone;
    private String dni;
    private String userName;
    private String passwd;


    public SocialWorker(){}

    public SocialWorker(String name, Integer phone, String dni, String userName, String passwd) {
        this.name = name;
        this.phone = phone;
        this.dni = dni;
        this.userName=userName;
        this.passwd=passwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public String getDni() {
        return this.dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getUserName() { return userName; }

    public void setUserName(String userName) { this.userName = userName; }

    public String getPasswd() { return passwd; }

    public void setPasswd(String passwd) { this.passwd = passwd; }


}
