package es.uji.ei1027.majorsacasa.controller;

import es.uji.ei1027.majorsacasa.model.ElderlyPeople;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ElderlyPeopleVallidator  implements Validator  {
    @Override
    public boolean supports(Class<?> aClass) {
        return ElderlyPeople.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ElderlyPeople elderlyPeople = (ElderlyPeople) o;
        if(elderlyPeople.getJustification().trim().equals("")){
            errors.rejectValue("justificació","obligatori","Cal introduir la justificació del perquè és demana ser atés.");
        }

        if(elderlyPeople.getDni().trim().equals("")){
            errors.rejectValue("dni","obligatori","Cal introduir el DNI per identificar a la persona.");
        }
        if(elderlyPeople.getName().trim().equals("")){
            errors.rejectValue("nom","obligatori","Cal introduir un valor.");
        }
        if (elderlyPeople.getEmail().trim().equals("")){
            errors.rejectValue("Email","obligatori","Cal introduir un valor.");
        }

        if(elderlyPeople.getPasswd().trim().equals("")){
            errors.rejectValue("Contrasenya","Obligatori","Cal introduir un valor");
        }
        if(elderlyPeople.getUsername().trim().equals("")){
            errors.rejectValue("Nom d'usuari","Obligatori","Cal introduir un valor");
        }




    }
}
