package es.uji.ei1027.majorsacasa.controller;


import es.uji.ei1027.majorsacasa.dao.CompanyDao;
import es.uji.ei1027.majorsacasa.model.Company;
import es.uji.ei1027.majorsacasa.model.ElderlyPeople;
import es.uji.ei1027.majorsacasa.model.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Random;


@Controller
@RequestMapping("/company")
public class CompanyController {
    private CompanyDao companyDao;

    @Autowired
    public void setCompanyDao(CompanyDao companyDao){
        this.companyDao=companyDao;
    }


    @RequestMapping(value="/main")
    public String getCompanyMain(HttpSession session, Model model){
        String isSession = checkSession(model,session);
        if(isSession!=null){ return isSession; }
        UserDetails user =  (UserDetails) session.getAttribute("user");

        // TODO La empresa deberá de tener un correo asignado
        // TODO La empresa no tiene el dni, se supone que es el CIF
        Company company = companyDao.getCompany(user.getDni());

        model.addAttribute("company",company);
        return "company/main";
    }


    @RequestMapping(value = "/update",method=RequestMethod.GET)
    public String update(Model model, HttpSession session){
        String isSession = checkSession(model,session);
        if(isSession!=null){ return isSession; }
        UserDetails user =  (UserDetails) session.getAttribute("user");

        model.addAttribute("company",companyDao.getCompany(user.getDni()));

        return "company/update";
    }

    /**
     * Actualiza la company
     * @param company
     * @param bindingResult
     * @return Página inicial de la companía
     */
    @RequestMapping(value="/update",method=RequestMethod.POST)
    public String processUpdateSubmit(@ModelAttribute("company") Company company,
                                      BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
            return "company/update";
        }
        companyDao.updateCompany(company);
        return "company/update";
    }

    /**
     * Indica qeu servicios tiene que hacer a los elderly.
     * @param session
     * @param model
     * @return Página de servicios para hacer
     */
    @RequestMapping(value="/services")
    public String getServicesToDo(HttpSession session, Model model){
        String isSession = checkSession(model,session);
        if(isSession!=null){ return isSession; }

        UserDetails user =  (UserDetails) session.getAttribute("user");

        List<ElderlyPeople> servicesToDo = companyDao.getServicesToDo(user.getDni());
        model.addAttribute("servicesToDo",servicesToDo);
        return "/company/services";
    }

    @RequestMapping(value = "/contact",method=RequestMethod.GET)
    public String contactWithCas(HttpSession session, Model model) {

        String isSession = checkSession(model,session);
        if(isSession!=null){ return isSession; }
        UserDetails user =  (UserDetails) session.getAttribute("user");

        model.addAttribute("company",companyDao.getCompany(user.getDni()));

        return "company/contact";

    }

    @RequestMapping(value="/contact",method=RequestMethod.POST)
    public String contactSubmit(@ModelAttribute("company") Company company,
                                      BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return "company/contact";

        return "redirect:main";
    }








    // *************************
    // Métodos privados ********
    // *************************

    /**
     *  Chequea si la sesión está activa o no
     * @param model
     * @param session
     * @return página donde redirecciona en caso que no esté activa
     */
    private String checkSession(Model model, HttpSession session){
        if(session.getAttribute("user")==null) {
            model.addAttribute("user", new UserDetails());
            return "login";
        }
        checkRol(session);
        return null;
    }

    /**
     *Mira si el usuario actual puede o no acceder
     * @param session Sesion acutual
     */
    private void checkRol(HttpSession session){
       UserDetails user = (UserDetails) session.getAttribute("user");
        if(!user.getRol().equals("Company")){
            System.out.println("El usuario no puede acceder a esta pagina con este rol");
            throw  new MajorsACasaException("No tens permisos per accedir a aquesta pàgina. " +
                    "Has d'haver iniciat sessió com a Company per a poder accedir-hi.","AccesDenied","../"+user.getMainPage());
        }
    }







}
