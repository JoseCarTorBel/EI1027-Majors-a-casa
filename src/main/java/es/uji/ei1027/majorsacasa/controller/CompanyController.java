package es.uji.ei1027.majorsacasa.controller;


import es.uji.ei1027.majorsacasa.dao.CompanyDao;
import es.uji.ei1027.majorsacasa.model.Company;
import es.uji.ei1027.majorsacasa.model.Contract;
import es.uji.ei1027.majorsacasa.model.ElderlyPeople;
import es.uji.ei1027.majorsacasa.model.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;


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

        Company company = companyDao.getCompany(user.getDni());

        Contract contractCurrent = companyDao.getCurrentContract(user.getDni());

        String dia = diaSemana(LocalDate.now());
        String[] dias =  contractCurrent.getDaysOfWeek().trim().split(",");
        List<ElderlyPeople> servicesToDo=null;
        for(int i =0; i<dias.length; i++){
            if(dias[i].equals(dia)){
                servicesToDo = companyDao.getServicesToDo(user.getDni());
                break;
            }
        }

        model.addAttribute("servicesToDo",servicesToDo);


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

        throw  new MajorsACasaException("En breu es ficarem en contacte.","Success","../company/main");
    }

    /**
     * Contrato actual y los que ha tenido.
     * @param session
     * @param model
     * @return
     */
    @RequestMapping(value="/mycontract",method=RequestMethod.GET)
    public String getMyContracts(HttpSession session, Model model) {
        String isSession = checkSession(model, session);
        if (isSession != null) {
            return isSession;
        }

        UserDetails user = (UserDetails) session.getAttribute("user");

        List<Contract> contractsPast = companyDao.getPastContracts(user.getDni());

        model.addAttribute("contractsPast",contractsPast);


        Contract contractCurrent = companyDao.getCurrentContract(user.getDni());
        model.addAttribute("contractCurrent",contractCurrent);

        return "/company/mycontract";
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


    private String diaSemana (LocalDate today)
    {
        String dia="";
        DayOfWeek diaSemana = today.getDayOfWeek();

        switch (diaSemana.getValue()){
            case 1:
                return "Dilluns";
            case 2:
                return "Dimarts";
            case 3:
                return "Dimecres";
            case 4:
                return "Dijous";
            case 5:
                return "Divendres";
            case 6:
                return "Dissabte";
            case 7:
                return "diumenge";
        }
        return "";
    }







}
