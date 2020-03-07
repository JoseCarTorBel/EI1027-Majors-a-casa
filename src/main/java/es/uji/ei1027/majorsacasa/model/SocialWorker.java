package es.uji.ei1027.majorsacasa.model;

import java.util.ArrayList;
import java.util.List;

public class SocialWorker {

    private String name;
    private int phone;
    private String dni;
    private String userName;
    private String passwd;

    private List<ElderlyPeople> elderlyPeopleAssit;

    public SocialWorker(){}



    public SocialWorker(String name, int phone, String dni, String userName, String passwd) {
        this.name = name;
        this.phone = phone;
        this.dni = dni;
        this.userName=userName;
        this.passwd=passwd;
        this.elderlyPeopleAssit = new ArrayList<ElderlyPeople>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
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

    // TODO mirar si tiene el control del día para asistir.
    /**
     * Devuelve si se le puede asignar al social worker el elderly people para asistirle.
     * @param elderly
     * @return boolean
     */
    public boolean elderlyPeopleToAssisted(ElderlyPeople elderly){
        return elderlyPeopleAssit.add(elderly);
    }


//    /**
//     * Elimina de la lista el elderly people una vez haya sido asistido
//     * @param elderlyPeople
//     * @return
//     */
//    public boolean elderlyPeopleAssistComplete(ElderlyPeople elderlyPeople){
//        return elderlyPeopleAssit.remove(elderlyPeople);
//    }

    /**
     * Devuelve el siguiente elderly a asistir por el social worker y se borra de su lista de pendientes.
     * @return ElderlyPeople si se puede, null si no se le puede asistir
     *
     */
    public ElderlyPeople nextElderlyToAssist(){

        ElderlyPeople nextElderly =  elderlyPeopleAssit.get(0);
        if(elderlyPeopleAssit.remove(nextElderly)){
            return nextElderly;
        }
        return null;


    }

}
