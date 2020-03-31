package es.uji.ei1027.majorsacasa.controller;
import es.uji.ei1027.majorsacasa.model.Volunteer;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;


public class VolunteerVallidator implements Validator{
    @Override
    public boolean supports(Class<?> cls) {
        return Volunteer.class.equals(cls);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Volunteer volunteer = (Volunteer)o;
        if(volunteer.getName().trim().equals("")){
            errors.rejectValue("name","Obligatori", "Cal introduir el nom.");
        }

        if(volunteer.getDni().trim().equals("")){
            errors.rejectValue("dni","Obligatori", "Cal introduir el DNI.");
        }

        if(volunteer.getEmail().trim().equals("")){
            errors.rejectValue("email","Obligatori", "Cal introduir un email.");
        }

        if(volunteer.getSecondName().trim().equals("")){
            errors.rejectValue("secondName","Obligatori","Cal introduir el cognom");
        }

        if(volunteer.getPhone().trim().equals("")){
            errors.rejectValue("phone","Obligatori","Cal introduir el telèfon");
        }else if(volunteer.getPhone().length()!=9){
            errors.rejectValue("phone","Obligatori","Cal introduir un telèfon vàlid.");
        }

        if(volunteer.getDateOfBirth() == null || volunteer.getDateOfBirth().isBefore(LocalDate.now())){
            errors.rejectValue("dateOfBirth","Obligatori","Cal introduir una data de naixement vàlida");
        }

        if(volunteer.getPostAddress().trim().equals("")){
            errors.rejectValue("postAddress","Obligatori","Cal introduir una direccio valida");
        }

        if(volunteer.getUsername().trim().equals("")){
            errors.rejectValue("username","Obligatori","Cal introduir un usuari vàlid.");
        }

        if(volunteer.getPasswd().trim().equals("")){
            errors.rejectValue("passwd","Obligatori","Cal introduir una contrasenya.");
        }



    }
}
