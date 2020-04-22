package es.uji.ei1027.majorsacasa.controller;

import es.uji.ei1027.majorsacasa.model.Company;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class CompanyVallidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return Company.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Company company = (Company) o;

        if(company.getCif().trim().equals("")){
            errors.rejectValue("cif","Obligatori", "Cal introduir el CIF.");
        }

        if (company.getName().trim().equals("")){
            errors.rejectValue("name","Obligatori","Cal introduir el nom de l'empressa.");
        }

        if(company.getNifContact().trim().equals("")){
            errors.rejectValue("nifContact","Obligatori","Cal introduir el NIF de la persona de contacte.");

        }

        if (company.getPersonalContact().trim().equals("")){
            errors.rejectValue("personalContact","Obligatori","Cal introduir el una persona de contacte.");
        }

        if(company.getPhoneContact().equals("")){
            errors.rejectValue("phoneContact","Obligatori","Cal introduir el telèfon de contacte.");
        }else{
            try{
                Integer.parseInt(company.getPhoneContact());
                if(company.getPhoneContact().length()!=9){
                    errors.rejectValue("phoneContact","Obligatori","Cal introduir un telèfon vàlid.");
                }
            }catch (Exception ex){
                errors.rejectValue("phoneContact","Obligatori","Cal introduir un telèfon vàlid.");
            }
        }

        if(company.getPostAddress().trim().equals("")){
            errors.rejectValue("postaddress","Obligatori","Cal introduir l'adreça de l'empressa. ");
        }

        if(company.getEmail().trim().equals("")){
            errors.rejectValue("email","Obligatori","Cal introduir l'adreça electrónica de l'empressa. ");
        }
    }
}
