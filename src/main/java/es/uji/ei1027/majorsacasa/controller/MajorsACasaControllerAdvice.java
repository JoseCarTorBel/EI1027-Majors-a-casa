
package es.uji.ei1027.majorsacasa.controller;

import es.uji.ei1027.majorsacasa.controller.MajorsACasaException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class MajorsACasaControllerAdvice {

    @ExceptionHandler(value = MajorsACasaException.class)
    public ModelAndView handleClubException(MajorsACasaException ex){

        ModelAndView mav = new ModelAndView("errors/exceptionError");
        mav.addObject("message", ex.getMessage());
        mav.addObject("errorName", ex.getErrorName());
        mav.addObject("returnPath", ex.getReturnPath());
        return mav;
    }

}
