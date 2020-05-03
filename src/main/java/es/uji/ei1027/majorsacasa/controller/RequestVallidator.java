package es.uji.ei1027.majorsacasa.controller;

import es.uji.ei1027.majorsacasa.model.Request;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;

public class RequestVallidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return Request.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        Request request = (Request) o;

        if(request.getCodRequest().equals("")){
            errors.rejectValue("codRequest","Obligatori", "Cal introduir el codi de la sol·licitud.");
        }
        if(request.getInitialDate().equals("")){
            errors.rejectValue("initialDate","Obligatori", "Cal introduir la data inicial del servei");
        }else if(request.getEndDate().equals("")){
            errors.rejectValue("endDate","Obligatori", "Cal introduir la data de finalització del servei.");
        }else if(   request.getEndDate().isBefore(request.getInitialDate()) &&
                request.getInitialDate().isBefore(LocalDate.now())         &&
                request.getEndDate().isBefore(LocalDate.now())   ){
            errors.rejectValue("endDate","Obligatori", "Cal introduir la data de finalització del servei vàlida.");
        }
        if(request.getCodContract().equals("")){
            errors.rejectValue("codContract","Obligatori", "Cal introduir el codi del contracte.");
        }


    }
}
