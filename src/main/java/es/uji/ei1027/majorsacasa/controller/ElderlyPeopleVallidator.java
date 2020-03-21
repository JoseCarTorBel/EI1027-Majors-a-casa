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
        if(elderlyPeople.getJustification().equals("")){
            errors.rejectValue("justification","obligatori","Cal introduir la justificació del perquè és demana ser atés.");
        }
        if(elderlyPeople.getDni().trim().equals("")){
            errors.rejectValue("dni","obligatori","Cal introduir el DNI per identificar a la persona.");
        }
        if(elderlyPeople.getName().trim().equals("")){
            errors.rejectValue("name","obligatori","Nom obligatori.");
        }
        if (elderlyPeople.getEmail().trim().equals("")){
            errors.rejectValue("email","obligatori","Cal introduir un valor.");
        }

        if(elderlyPeople.getPasswd().trim().equals("") || validatePassword(elderlyPeople.getPasswd())){
            errors.rejectValue("passwd","Obligatori","S'ha d'introduir una contrasenya vàlida.");
        }
        if(elderlyPeople.getUsername().trim().equals("")){
            errors.rejectValue("username","Obligatori","Cal introduir un valor");
        }

        if(elderlyPeople.getSecondName().trim().equals("")){
            errors.rejectValue("secondName","obligatori","Cognom obligatori.");
        }
    }

    /**
     * Una contraseña válida será la que tenga mínimo entre 4 y 10 carácteres, con letras, números
     * y un caracter aleatorio.
     *
     * @param password  Contraseña con la que el usuario se registra.
     * @return boolean  Devuelve si es válida o no.
     */
    private boolean validatePassword(String password){

        int numNumeros=0; int numChar=0; int otro=0;
        for(int i=0;i<password.length();i++){
            if(Character.isLetter(password.charAt(i)))
                numChar++;
            else if(Character.isDigit(password.charAt(i)))
                numNumeros++;
            else
                otro++;
        }

        if(password.length()>4 && numChar>0 && numNumeros>0 && otro>0)
            return true;
        return false;
    }
}
