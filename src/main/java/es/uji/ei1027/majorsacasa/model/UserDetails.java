package es.uji.ei1027.majorsacasa.model;

public class UserDetails {
    String username;
    String password;
    String rol;
    String dni;

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getRol() {
        return rol;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMainPage(){
        if (this.rol.equals("Volunteer"))
            return "volunteer/main";
        if (this.rol.equals("Elderly"))
            return "elderlyPeople/main";
        if (this.rol.equals("SocialWorker"))
            return "socialworker/main";
        if (this.rol.equals("Company"))
            return "company/main";
        if (this.rol.equals("ResponsableContratacion"))
            return "cascompany/main";
        if (this.rol.equals("Comite"))
            return "comite/main";
        if (this.rol.equals("SupervisorVolunatris"))
            return "supervisorVoluntaris/main";
        return "/";
    }



}
