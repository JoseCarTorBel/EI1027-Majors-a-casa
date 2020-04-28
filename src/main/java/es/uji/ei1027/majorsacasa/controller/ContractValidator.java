package es.uji.ei1027.majorsacasa.controller;

import es.uji.ei1027.majorsacasa.model.Contract;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;


public class ContractValidator  implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Contract.class.equals(aClass);
    }

    /**
     * Casos que valida:
     *  - CIF no siga buit.
     *  - Data inici < final.
     *  - Data inici i final > El dia de hui.
     *
     * @param o     Contracte de l'empresa
     * @param errors   Validació d'errors
     */
    @Override
    public void validate(Object o, Errors errors) {
        Contract contract = (Contract) o;

        if(contract.getCifcompany().equals("")){
            errors.rejectValue("cifcompany","Obligatori", "Cal introduir el CIF de l'empresa a contractar.");
        }

        if(contract.getInitialDate().equals("")){
            errors.rejectValue("initialDate","Obligatori", "Cal introduir la data inicial del contracte.");
        }else if(contract.getFinalDate().equals("")){
            errors.rejectValue("initialDate","Obligatori", "Cal introduir la data de finalització del contracte.");
        }else if(   contract.getFinalDate().isBefore(contract.getInitialDate()) &&
                    contract.getInitialDate().isBefore(LocalDate.now())         &&
                    contract.getFinalDate().isBefore(LocalDate.now())   ){
            errors.rejectValue("finaltime","Obligatori", "Cal introduir la data de finalització del contracte.");
        }

        if(contract.getPrice().equals("")){
            errors.rejectValue("price","Obligatori", "S'ha de marcar el preu del contracte.");
        }else if(contract.getPrice()<0){
            errors.rejectValue("price","Obligatori", "El preu no pot ser menor que 0.");
        }
//        List<String> services = Arrays.asList("")
//        if(.equals(contract.getService())){
//            errors.rejectValue("selService","Obligatori","S'ha d'indicar el tipus de servici.");
//        }
    }
}
